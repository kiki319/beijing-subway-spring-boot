package com.chengjiaqi.beijing.subway.dao;

import com.chengjiaqi.beijing.subway.model.vo.StationInterval;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chengjiaqi
 */

public interface StationRelativeMapper {

    List<StationInterval> selectIntervalById(Map<String, Object> params);

    void editLastStation(Map<String,Object> params);

    void editNextStation(Map<String,Object> params);

    List<StationInterval> selectAllByLine(@Param("lineNumber") String line);

    void deleteByLineStation(Map<String,Object> params);

    void insertData(Map<String,Object> params);

}
