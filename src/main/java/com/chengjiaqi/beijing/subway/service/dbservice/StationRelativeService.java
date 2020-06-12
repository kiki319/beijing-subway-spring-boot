package com.chengjiaqi.beijing.subway.service.dbservice;

import com.chengjiaqi.beijing.subway.dao.StationRelativeMapper;
import com.chengjiaqi.beijing.subway.model.vo.StationInterval;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengjiaqi
 */
@Service
@Slf4j
public class StationRelativeService {

    @Autowired
    private StationRelativeMapper stationRelativeMapper;

    /**
     * 从数据库中取所有的站点之间的时间间隔信息
     * @return
     */
    public List<StationInterval> exactSearchIntervalById() {
        Map<String, Object> params = new HashMap<>();
        return stationRelativeMapper.selectIntervalById(params);
    }

    /**
     * 取得该路线上的所有信息
     * @param line
     * @return
     */
    public List<StationInterval> selectAllByLine(String line) {
        return stationRelativeMapper.selectAllByLine(line);
    }

    /**
     * 删除该线路上该站点和下一站点的信息
     * @param line
     * @param station
     */
    public void deleteByLineStation(String line,String station) {
        Map<String,Object> deleteMap = new HashMap<>();
        deleteMap.put("lineNumber",line);
        deleteMap.put("lastStationName",station);
        deleteMap.put("nextStationName",station);
        stationRelativeMapper.deleteByLineStation(deleteMap);
    }

    /**
     * 添加一条数据
     * @param line
     * @param lastStationName
     * @param nextStationName
     * @param time
     */
    public void insertData(String line,String lastStationName,String nextStationName,Integer time){
        log.info("传过来的time为{}",time);
        Map<String,Object> insertMap = new HashMap<>();
        insertMap.put("lineNumber",line);
        insertMap.put("lastStationName",lastStationName);
        insertMap.put("nextStationName",nextStationName);
        insertMap.put("stationInterval",time);
        stationRelativeMapper.insertData(insertMap);
    }
}
