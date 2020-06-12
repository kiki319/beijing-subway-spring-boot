package com.chengjiaqi.beijing.subway.model.dto;

import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author chengjiaqi
 */
@Data
public class GetAllSubwayInfoDTO implements Serializable{
    private List<String> lines;
    private Set<String> sites;
}
