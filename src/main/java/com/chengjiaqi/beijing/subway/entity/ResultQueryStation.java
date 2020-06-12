package com.chengjiaqi.beijing.subway.entity;

import com.chengjiaqi.beijing.subway.model.dto.QueryStationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author chengjiaqi
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ResultQueryStation {
    private int status;

    private String msg;

    private List<QueryStationDTO> data;
}
