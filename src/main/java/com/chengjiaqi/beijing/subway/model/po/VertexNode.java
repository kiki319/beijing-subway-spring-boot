package com.chengjiaqi.beijing.subway.model.po;

import lombok.Data;
import lombok.ToString;

import javax.xml.soap.SAAJResult;

/**
 * @author chengjiaqi
 */
@Data
@ToString
public class VertexNode {
    private String stationName;
    private EdgeNode firstEdge;
}
