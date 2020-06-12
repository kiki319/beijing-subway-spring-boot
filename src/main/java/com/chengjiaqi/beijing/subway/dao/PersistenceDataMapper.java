package com.chengjiaqi.beijing.subway.dao;

import com.chengjiaqi.beijing.subway.model.vo.PersistenceData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chengjiaqi
 */
public interface PersistenceDataMapper {

    int batchInsertSubwayLine(@Param("subwayLines") List<PersistenceData> persistenceDataList);
}
