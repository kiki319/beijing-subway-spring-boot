package com.chengjiaqi.beijing.subway.service.frontservice;

import com.chengjiaqi.beijing.subway.model.dto.GetStartEndStationDTO;
import com.chengjiaqi.beijing.subway.model.dto.QueryResultDTO;
import com.chengjiaqi.beijing.subway.model.dto.StationDataDTO;
import com.chengjiaqi.beijing.subway.model.po.EdgeData;
import com.chengjiaqi.beijing.subway.model.po.SubwayGraph;
import com.chengjiaqi.beijing.subway.service.dbservice.StationDataService;
import com.chengjiaqi.beijing.subway.service.graphservice.CreateGraphicsService;
import com.chengjiaqi.beijing.subway.service.graphservice.InitializationInfo;
import com.chengjiaqi.beijing.subway.service.graphservice.ShortestPathAlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengjiaqi
 */
@Service
public class QueryTransfer {
    @Autowired
    private StationDataService stationDataService;

    @Autowired
    private CreateGraphicsService createGraphicsService;

    @Autowired
    private ShortestPathAlgorithmService shortestPathAlgorithmService;

    @Autowired
    private InitializationInfo initializationInfo;

    public List<QueryResultDTO> getShortestPath(GetStartEndStationDTO getStartEndStationDTO) {
        String startStation = getStartEndStationDTO.getStartStation();
        String endStation = getStartEndStationDTO.getEndStation();
        EdgeData[] edgeData = initializationInfo.initEdgeData();
        List<String> stations = initializationInfo.initVertexData();
        SubwayGraph subwayGraph = createGraphicsService.listUDG(stations,edgeData);
        List<String> resultStation = shortestPathAlgorithmService.dijkstra(startStation,endStation,subwayGraph);
        List<StationDataDTO> stationDataDTOS = getAllChangeLine(resultStation);
        List<QueryResultDTO> resultDTOList = getChangeLine(stationDataDTOS);
        return resultDTOList;
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
