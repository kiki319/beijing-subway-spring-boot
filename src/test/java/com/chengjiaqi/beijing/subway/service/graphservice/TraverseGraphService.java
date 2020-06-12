package com.chengjiaqi.beijing.subway.service.graphservice;

import com.chengjiaqi.beijing.subway.model.dto.QueryResultDTO;
import com.chengjiaqi.beijing.subway.model.dto.StationDataDTO;
import com.chengjiaqi.beijing.subway.model.po.EdgeData;
import com.chengjiaqi.beijing.subway.model.po.SubwayGraph;
import com.chengjiaqi.beijing.subway.model.vo.StationInterval;
import com.chengjiaqi.beijing.subway.service.dbservice.StationDataService;
import com.chengjiaqi.beijing.subway.service.dbservice.StationRelativeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengjiaqi
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TraverseGraphService {
    @Autowired
    private StationDataService stationDataService;

    @Autowired
    private CreateGraphicsService createGraphicsService;

    @Autowired
    private ShortestPathAlgorithmService shortestPathAlgorithmService;

    @Autowired
    private InitializationInfo initializationInfo;
    @Autowired
    private StationRelativeService stationRelativeService;

    @Test
    public void deleteStationRelativeData() {
        String line = "s1线";
        String station = "呵呵呵";
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

    public void getShortestPath() {
        String startStation = "北工大西门";
        String endStation = "北京站";
        EdgeData[] edgeData = initializationInfo.initEdgeData();
        List<String> stations = initializationInfo.initVertexData();
        SubwayGraph subwayGraph = createGraphicsService.listUDG(stations,edgeData);
        List<String> resultStation = shortestPathAlgorithmService.dijkstra(startStation,endStation,subwayGraph);
        List<StationDataDTO> stationDataDTOS = getAllChangeLine(resultStation);
        List<QueryResultDTO> resultDTOList = getChangeLine(stationDataDTOS);
        resultDTOList.forEach(str -> {
            log.info("得到的所有换乘路线的数据");
            System.out.println(str.getName());
            System.out.println(str.getChange());
        });
    }

    /**
     * 获取线路上每一个站点所在的路线
     * @param resultStation
     * @return
     */
    public List<StationDataDTO> getAllChangeLine(List<String> resultStation) {
        String station;
        List<StationDataDTO> stationDataDTOS = new ArrayList<>();
        for (int i = 0; i < resultStation.size(); i++) {
            station = resultStation.get(i);
            List<String> changes = stationDataService.selectLinesByStation(station);
            StationDataDTO stationDataDTO = new StationDataDTO();
            stationDataDTO.setName(station);
            stationDataDTO.setChanges(changes);
            stationDataDTOS.add(stationDataDTO);
        }
        return stationDataDTOS;
    }

    /**
     * 获取换乘站点的换乘路线
     * @param stationDataDTOList
     * @return
     */
    public List<QueryResultDTO> getChangeLine(List<StationDataDTO> stationDataDTOList) {
        List<QueryResultDTO> resultDTOList = new ArrayList<>();
        for (int i = 0; i < stationDataDTOList.size(); i++) {
            QueryResultDTO queryResultDTO = new QueryResultDTO();
            if (i == stationDataDTOList.size()-1){
                queryResultDTO.setName(stationDataDTOList.get(i).getName());
                queryResultDTO.setChange(null);
                resultDTOList.add(queryResultDTO);
                continue;
            }
            for (int j = 0; j < stationDataDTOList.get(i).getChanges().size(); j++) {
                String line = stationDataDTOList.get(i).getChanges().get(j);
                List<String> lines = stationDataDTOList.get(i+1).getChanges();
                for (int k = 0; k < lines.size(); k++) {
                    if (lines.get(k).equals(line)){
                        queryResultDTO.setName(stationDataDTOList.get(i).getName());
                        queryResultDTO.setChange(line);
                        resultDTOList.add(queryResultDTO);
                    }
                }
            }
        }
        for (int i = resultDTOList.size()-1; i > 0; i--) {
            String line = resultDTOList.get(i).getChange();
            if (line != null){
                if (resultDTOList.get(i-1).getChange().equals(line)){
                    resultDTOList.get(i).setChange(null);
                }
            }
        }
        resultDTOList.get(0).setChange(null);
        return resultDTOList;
    }
}
