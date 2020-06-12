package com.chengjiaqi.beijing.subway.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author chengjiaqi
 */
@Data
public class GetStartEndStationDTO implements Serializable {
    private String startStation;
    private String endStation;
}
