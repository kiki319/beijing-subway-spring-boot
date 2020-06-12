package com.chengjiaqi.beijing.subway.model.po;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @author chengjiaqi
 */
@Data
public class EdgeNode {
    private int adjvex;
    private Integer weight;
    private EdgeNode next;
}
