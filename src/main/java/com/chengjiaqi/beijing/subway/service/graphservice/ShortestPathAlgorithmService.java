package com.chengjiaqi.beijing.subway.service.graphservice;

import com.chengjiaqi.beijing.subway.model.po.EdgeNode;
import com.chengjiaqi.beijing.subway.model.po.SubwayGraph;
import com.chengjiaqi.beijing.subway.model.po.VertexNode;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengjiaqi
 */
@Service
public class ShortestPathAlgorithmService {
    private static int INF = 0x3f3f3f3f;

    public List<String> dijkstra(String startStation, String endStation, SubwayGraph subwayGraph) {
        int[] dist = new int[subwayGraph.getVertexNodes().length];
        VertexNode[] vertexNodes = subwayGraph.getVertexNodes();
        // 顶点个数
        int n = subwayGraph.getVertexNodes().length;
        int vs = 0;
        for (int i = 0; i < subwayGraph.getVertexNodes().length; i++) {
            if (subwayGraph.getVertexNodes()[i].getStationName().equals(startStation)){
                vs = i;
                break;
            }
        }
        // 保存start到其他各点最短路径的字符串表示
        String[] path = new String[n];
        for (int i = 0; i < n; i++) {
            path[i] = new String(vs + "-->" + i);
        }
        // flag[i]=true表示"顶点vs"到"顶点i"的最短路径已成功获取。
        boolean[] flag = new boolean[vertexNodes.length];
        // 初始化
        for (int j = 0; j < vertexNodes.length; j++) {
            // 顶点i的最短路径还没获取到。
            flag[j] = false;
            // 顶点i的最短路径为"顶点vs"到"顶点i"的权。
            dist[j] = getWeight(vs, j,vertexNodes);
        }
        // 对"顶点vs"自身进行初始化
        flag[vs] = true;
        dist[vs] = 0;

        // 遍历vertexNodes.length-1次；每次找出一个顶点的最短路径。
        int k = 0;
        for (int i = 1; i < vertexNodes.length; i++) {
            // 寻找当前最小的路径；
            // 即，在未获取最短路径的顶点中，找到离vs最近的顶点(k)。
            int min = INF;
            for (int j = 0; j < vertexNodes.length; j++) {
                if (flag[j]==false && dist[j]<min) {
                    min = dist[j];
                    k = j;
                }
            }
            // 标记"顶点k"为已经获取到最短路径
            flag[k] = true;
            // 修正当前最短路径和前驱顶点
            // 即，当已经"顶点k的最短路径"之后，更新"未获取最短路径的顶点的最短路径和前驱顶点"。
            for (int j = 0; j < vertexNodes.length; j++) {
                int tmp = getWeight(k, j,vertexNodes);
                // 防止溢出
                tmp = (tmp==INF ? INF : (min + tmp));
                if (flag[j]==false && (tmp<dist[j]) )
                {
                    dist[j] = tmp;
                    path[j] = path[k] + "-->" + j;
                }
            }
        }
        int end = 0;
        for (int j = 0; j < vertexNodes.length; j++) {
            if (vertexNodes[j].getStationName().equals(endStation)){
                end = j;
                break;
            }
        }

        String[] strings = path[end].split("-->");
        List<String> intermediateStations = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            int adjVex = Integer.parseInt(strings[i]);
            String intermediateStation = vertexNodes[adjVex].getStationName();
            intermediateStations.add(intermediateStation);
        }
        return intermediateStations;
    }

    /*
     * 获取边<start, end>的权值；若start和end不是连通的，则返回无穷大。
     */

    private int getWeight(int start, int end, VertexNode[] vertexNodes) {
        if (start == end) {
            return 0;
        }
        EdgeNode node = vertexNodes[start].getFirstEdge();
        while (node != null) {
            if (end == node.getAdjvex() && node.getWeight() != null) {
                return Integer.valueOf(node.getWeight());
            }
            node = node.getNext();
        }
        return INF;
    }
}
