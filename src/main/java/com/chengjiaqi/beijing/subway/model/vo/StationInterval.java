package com.chengjiaqi.beijing.subway.model.vo;

import lombok.Data;

/**
 * @author chengjiaqi
 */
@Data
public class StationInterval {

    private int id;

    private String lineNumber;

    private String lastStationName;

    private String nextStationName;

    private Integer stationInterval;

}
