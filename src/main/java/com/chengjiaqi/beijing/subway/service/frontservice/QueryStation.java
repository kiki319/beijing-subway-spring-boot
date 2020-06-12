package com.chengjiaqi.beijing.subway.service.frontservice;

import com.chengjiaqi.beijing.subway.model.dto.QueryStationDTO;
import com.chengjiaqi.beijing.subway.service.dbservice.StationDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengjiaqi
 */
@Service
@Slf4j
public class QueryStation {

    @Autowired
    private StationDataService stationDataService;

    public List<QueryStationDTO> queryStation(String station) {
        List<String> lines = stationDataService.selectLinesByStation(station);
        List<QueryStationDTO> queryStationDTOS = new ArrayList<>();
        String startStation;
        String endStation;
        String line;
        for (int i = 0; i < lines.size(); i++) {
            line = lines.get(i);
            startStation = stationDataService.selectStartStationByLine(line);
            endStation = stationDataService.selectEndStationByLine(line);
            QueryStationDTO queryStationDTO1 = new QueryStationDTO();
            queryStationDTO1.setLine(line);
            queryStationDTO1.setStation(startStation);
            queryStationDTOS.add(queryStationDTO1);
            QueryStationDTO queryStationDTO2 = new QueryStationDTO();
            queryStationDTO2.setLine(line);
            queryStationDTO2.setStation(endStation);
            queryStationDTOS.add(queryStationDTO2);
        }
        return queryStationDTOS;
    }
}
