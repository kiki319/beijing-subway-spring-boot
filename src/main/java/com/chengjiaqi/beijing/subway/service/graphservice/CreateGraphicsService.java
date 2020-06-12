package com.chengjiaqi.beijing.subway.service.graphservice;

import com.chengjiaqi.beijing.subway.model.po.EdgeData;
import com.chengjiaqi.beijing.subway.model.po.EdgeNode;
import com.chengjiaqi.beijing.subway.model.po.SubwayGraph;
import com.chengjiaqi.beijing.subway.model.po.VertexNode;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chengjiaqi
 */
@Service
public class CreateGraphicsService {

    public SubwayGraph listUDG(List<String> stations, EdgeData[] edgeData) {
        SubwayGraph subwayGraph = new SubwayGraph();
        // 初始化"顶点数"和"边数"
        int vertexNum = stations.size();
        int edgeNum = edgeData.length;
        subwayGraph.setVertexNum(vertexNum);
        subwayGraph.setEdgeNum(edgeNum);

        // 初始化"顶点"
        VertexNode[] vertexNodes = new VertexNode[vertexNum];
        for (int i = 0; i < vertexNodes.length; i++) {
            vertexNodes[i] = new VertexNode();
            vertexNodes[i].setStationName(stations.get(i));
            vertexNodes[i].setFirstEdge(null);
        }

        // 初始化"边"
        for (int j = 0; j < edgeNum; j++) {
            // 读取边的起始顶点和结束顶点
            String c1 = edgeData[j].getStart();
            String c2 = edgeData[j].getEnd();
            Integer weight = edgeData[j].getWeight();
            // 读取边的起始顶点和结束顶点在数组中的位置
            int p1 = getPosition(c1,vertexNodes);
            int p2 = getPosition(c2,vertexNodes);
            // 初始化node1
            EdgeNode node1 = new EdgeNode();
            node1.setAdjvex(p2);
            node1.setWeight(weight);
            // 将node1链接到"p1所在链表的末尾"
            if(vertexNodes[p1].getFirstEdge() == null) {
                vertexNodes[p1].setFirstEdge(node1);
            } else {
                linkLast(vertexNodes[p1].getFirstEdge(), node1);
            }
            // 初始化node2
            EdgeNode node2 = new EdgeNode();
            node2.setAdjvex(p1);
            node2.setWeight(weight);
            // 将node2链接到"p2所在链表的末尾"
            if(vertexNodes[p2].getFirstEdge() == null) {
                vertexNodes[p2].setFirstEdge(node2);
            } else {
                linkLast(vertexNodes[p2].getFirstEdge(), node2);
            }
        }
        subwayGraph.setVertexNodes(vertexNodes);
        return subwayGraph;
    }
    /**
     * 返回ch位置
     */
    private int getPosition(String ch,VertexNode[] vertexNodes) {
        for(int i=0; i<vertexNodes.length; i++) {
            if(vertexNodes[i].getStationName().equals(ch)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 将node节点链接到list的最后
     */
    public void linkLast(EdgeNode list, EdgeNode node) {
        EdgeNode p = list;

        while(p.getNext()!=null) {
            p = p.getNext();
        }
        p.setNext(node);
    }
}
