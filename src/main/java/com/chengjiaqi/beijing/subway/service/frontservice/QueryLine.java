package com.chengjiaqi.beijing.subway.service.frontservice;

import com.chengjiaqi.beijing.subway.model.dto.QueryResultDTO;
import com.chengjiaqi.beijing.subway.service.dbservice.StationDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengjiaqi
 */
@Service
@Slf4j
public class QueryLine {

    @Autowired
    private StationDataService stationDataService;

    public List<QueryResultDTO> queryLine(String line) {
        List<String> stations = stationDataService.selectStationsByLine(line);
        List<QueryResultDTO> resultDTOList = new ArrayList<>();
        String name;
        List<String> changes;
        String change;
        for (int i = 0; i < stations.size(); i++) {
            name = stations.get(i);
            changes = stationDataService.selectLinesByStation(name);
            for (int j = 0; j < changes.size(); j++) {
                if (changes.get(j).equals(line)){
                    changes.remove(j);
                }
            }
            change = listToString(changes);
            QueryResultDTO queryResultDTO = new QueryResultDTO();
            queryResultDTO.setName(name);
            queryResultDTO.setChange(change);
            resultDTOList.add(queryResultDTO);
        }
        return resultDTOList;
    }

    private String listToString(List<String> cashIdsList){
        StringBuilder sb = new StringBuilder();
        if (cashIdsList != null && cashIdsList.size() > 0) {
            for (int i = 0; i < cashIdsList.size(); i++) {
                if (i < cashIdsList.size() - 1) {
                    sb.append(cashIdsList.get(i) + "  ");
                } else {
                    sb.append(cashIdsList.get(i));
                }
            }
        }
        return sb.toString();
    }
}

