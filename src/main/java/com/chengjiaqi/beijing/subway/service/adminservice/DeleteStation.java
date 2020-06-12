package com.chengjiaqi.beijing.subway.service.adminservice;

import com.chengjiaqi.beijing.subway.model.vo.StationInterval;
import com.chengjiaqi.beijing.subway.service.dbservice.StationDataService;
import com.chengjiaqi.beijing.subway.service.dbservice.StationRelativeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author chengjiaqi
 */
@Service
@Slf4j
public class DeleteStation {

    @Autowired
    private StationRelativeService stationRelativeService;
    @Autowired
    private StationDataService stationDataService;

    /**
     * 删除站点关系表中的站点信息
     * @param line
     * @param station
     */
    public void deleteStationRelativeData(String line,String station) {
        String start = stationDataService.selectStartStationByLine(line);
        String end = stationDataService.selectEndStationByLine(line);
        if (station.equals(start) || station.equals(end)) {
            stationRelativeService.deleteByLineStation(line, station);
        } else {
            int time1 = 0;
            int time2 = 0;
            String lastStationName = null;
            String nextStationName = null;
            List<StationInterval> list = stationRelativeService.selectAllByLine(line);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getLastStationName().equals(station)) {
                    nextStationName = list.get(i).getNextStationName();
                    time1 = list.get(i).getStationInterval();
                }
                if (list.get(i).getNextStationName().equals(station)) {
                    lastStationName = list.get(i).getLastStationName();
                    time2 = list.get(i).getStationInterval();
                }
            }
            stationRelativeService.deleteByLineStation(line, station);
            stationRelativeService.insertData(line,lastStationName,nextStationName,time1+time2);
        }
    }

    /**
     * 删除站点表中的站点信息
     * @param line
     * @param station
     */
    public void deleteStationData(String line,String station) {
        stationDataService.updateIdMinus(line,station);
        stationDataService.updateOrderMinus(line,station);
        stationDataService.deleteByLineStation(line,station);
    }
}
