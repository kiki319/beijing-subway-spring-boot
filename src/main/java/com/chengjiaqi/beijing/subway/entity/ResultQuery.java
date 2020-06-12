package com.chengjiaqi.beijing.subway.entity;

import com.chengjiaqi.beijing.subway.model.dto.QueryResultDTO;
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
public class ResultQuery implements Serializable {

    private int status;

    private String msg;

    private List<QueryResultDTO> data;

}
