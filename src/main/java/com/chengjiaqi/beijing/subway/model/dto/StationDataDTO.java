package com.chengjiaqi.beijing.subway.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author chengjiaqi
 */
@Data
public class StationDataDTO {
    private String name;
    private List<String> changes;
}
