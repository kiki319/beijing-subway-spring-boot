package com.chengjiaqi.beijing.subway.dao;

import com.chengjiaqi.beijing.subway.model.vo.SubwayStation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author chengjiaqi
 */
@Repository
public interface SubwayStationMapper {

    List<SubwayStation> selectStationById(Map<String, Object> params);

    List<String> selectLinesByStation(@Param("stationName") String StationName);

    String selectEndStationByLine(@Param("lineNumber") String lineNumber);

    String selectStartStationByLine(@Param("lineNumber") String lineNumber);

    List<String> selectStationsByLine(@Param("lineNumber") String lineNumber);

    void updateStation(Map<String,Object> params);

    void deleteByLineStation(Map<String,Object> params);

    void updateIdMinus(Map<String,Object> params);

    void updateOrderMinus(Map<String,Object> params);

    void updateIdPlus(@Param("id") int id);

    int selectMaxId(@Param("lineNumber") String line);

    int selectMaxOrder(@Param("lineNumber") String line);

    void insertData(Map<String,Object> params);
}
