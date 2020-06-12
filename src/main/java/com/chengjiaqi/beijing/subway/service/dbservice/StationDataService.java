package com.chengjiaqi.beijing.subway.service.dbservice;

import com.chengjiaqi.beijing.subway.dao.SubwayStationMapper;
import com.chengjiaqi.beijing.subway.model.vo.SubwayStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengjiaqi
 */
@Service
public class StationDataService {

    @Autowired
    private SubwayStationMapper subwayStationMapper;

    /**
     * 从数据库中取所有的站点信息
     * @return
     */
    public List<SubwayStation> exactSearchStationById() {
        Map<String, Object> params = new HashMap<>();
        return subwayStationMapper.selectStationById(params);
    }

    /**
     * 从数据库中取出某条路线的所有站点
     * @param line
     * @return
     */
    public List<String> selectStationsByLine(String line) {
        return subwayStationMapper.selectStationsByLine(line);
    }


    /**
     * 从数据库中取出每个站点对应的路线
     * @param Station
     * @return
     */
    public List<String> selectLinesByStation(String Station) {
        return subwayStationMapper.selectLinesByStation(Station);
    }

    /**
     * 从数据库中取出线路中的起始站
     * @param line
     * @return
     */
    public String selectStartStationByLine(String line) {
        return subwayStationMapper.selectStartStationByLine(line);
    }

    /**
     * 从数据库中取出线路中的终点站
     * @param line
     * @return
     */
    public String selectEndStationByLine(String line) {
        return subwayStationMapper.selectEndStationByLine(line);
    }

    /**
     * 删除某一线路上某个站点的信息
     * @param line
     * @param stationName
     */
    public void deleteByLineStation(String line,String stationName) {
        Map<String,Object> deleteMap = new HashMap<>();
        deleteMap.put("lineNumber",line);
        deleteMap.put("stationName",stationName);
        subwayStationMapper.deleteByLineStation(deleteMap);
    }

    /**
     * 更新某一线路上某一站点之后的id信息
     * @param line
     * @param stationName
     */
    public void updateIdMinus(String line,String stationName) {
        Map<String,Object> deleteMap = new HashMap<>();
        deleteMap.put("lineNumber",line);
        deleteMap.put("stationName",stationName);
        subwayStationMapper.updateIdMinus(deleteMap);
    }

    /**
     * 更新某一线路上某一站点之后的station_order信息
     * @param line
     * @param stationName
     */
    public void updateOrderMinus(String line,String stationName) {
        Map<String,Object> deleteMap = new HashMap<>();
        deleteMap.put("lineNumber",line);
        deleteMap.put("stationName",stationName);
        subwayStationMapper.updateOrderMinus(deleteMap);
    }

    /**
     * 添加一条数据
     * @param id
     * @param order
     * @param line
     * @param station
     */
    public void insertData(int id,int order,String line,String station) {
        Map<String,Object> insertMap = new HashMap<>();
        insertMap.put("id",id);
        insertMap.put("lineNumber",line);
        insertMap.put("stationOrder",order);
        insertMap.put("stationName",station);
        subwayStationMapper.insertData(insertMap);
    }
}
