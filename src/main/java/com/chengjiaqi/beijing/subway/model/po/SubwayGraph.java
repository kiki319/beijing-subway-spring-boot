package com.chengjiaqi.beijing.subway.model.po;

import lombok.Data;

/**
 * @author chengjiaqi
 */
@Data
public class SubwayGraph {
    private static final int MAX_SIZE = 500;
    private VertexNode[] vertexNodes = new VertexNode[MAX_SIZE];
    private int vertexNum;
    private int edgeNum;

}
