package com.chengjiaqi.beijing.subway.service.adminservice;

import com.chengjiaqi.beijing.subway.dao.SubwayStationMapper;
import com.chengjiaqi.beijing.subway.service.dbservice.StationDataService;
import com.chengjiaqi.beijing.subway.service.dbservice.StationRelativeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chengjiaqi
 */
@Service
@Slf4j
public class IncreaseStation {
    @Autowired
    private SubwayStationMapper subwayStationMapper;
    @Autowired
    private StationDataService stationDataService;
    @Autowired
    private StationRelativeService stationRelativeService;

    /**
     * 向站点表中添加一条数据
     * @param line
     * @param station
     */
    public void insertStationData(String line,String station) {
        int id = subwayStationMapper.selectMaxId(line);
        int order = subwayStationMapper.selectMaxOrder(line);
        subwayStationMapper.updateIdPlus(id);
        stationDataService.insertData(id+1,order+1,line,station);
    }

    /**
     * 向站点关系表中添加一条数据
     * @param line
     * @param station
     * @param time
     */
    public void insertStationRelativeData(String line,String station,Integer time) {
        String lastStationName = stationDataService.selectEndStationByLine(line);
        log.info("该线路上最后一个站点名为{}",lastStationName);
        log.info("该线路上新增的站点为{}",station);
        log.info("两个站点之间的间隔时间为{}",time);
        stationRelativeService.insertData(line,lastStationName,station,time);
    }
}
