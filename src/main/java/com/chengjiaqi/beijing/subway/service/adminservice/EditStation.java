package com.chengjiaqi.beijing.subway.service.adminservice;

import com.chengjiaqi.beijing.subway.dao.StationRelativeMapper;
import com.chengjiaqi.beijing.subway.dao.SubwayStationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengjiaqi
 */
@Service
public class EditStation {

    @Autowired
    private SubwayStationMapper subwayStationMapper;
    @Autowired
    private StationRelativeMapper stationRelativeMapper;

    public void editData(String station,String newStation) {
        Map<String,Object> updateMap = new HashMap<>();
        updateMap.put("stationName",station);
        updateMap.put("stationNewName",newStation);
        subwayStationMapper.updateStation(updateMap);
        stationRelativeMapper.editLastStation(updateMap);
        stationRelativeMapper.editNextStation(updateMap);
    }
}
