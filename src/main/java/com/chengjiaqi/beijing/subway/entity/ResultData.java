package com.chengjiaqi.beijing.subway.entity;

import com.chengjiaqi.beijing.subway.model.dto.GetAllSubwayInfoDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author chengjiaqi
 */

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class ResultData implements Serializable {

    private int status;

    private String msg;

    private GetAllSubwayInfoDTO data;

}
