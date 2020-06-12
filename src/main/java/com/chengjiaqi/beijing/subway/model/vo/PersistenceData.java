package com.chengjiaqi.beijing.subway.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chengjiaqi
 */
@Setter
@Getter
@ToString
public class PersistenceData {
    private Long id;

    private String sid;

    private String stationOrder;

    private String subwayLineNumber;

    private String subwayStationName;

    private String stationL;

    private String nextStationInterval;

    private String transferLine;

    private Integer isDel;
}
