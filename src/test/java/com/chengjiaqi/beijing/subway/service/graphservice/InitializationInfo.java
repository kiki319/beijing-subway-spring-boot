package com.chengjiaqi.beijing.subway.service.graphservice;

import com.chengjiaqi.beijing.subway.model.po.EdgeData;
import com.chengjiaqi.beijing.subway.model.vo.StationInterval;
import com.chengjiaqi.beijing.subway.model.vo.SubwayStation;
import com.chengjiaqi.beijing.subway.service.dbservice.StationDataService;
import com.chengjiaqi.beijing.subway.service.dbservice.StationRelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chengjiaqi
 */
@Service
public class InitializationInfo {

    @Autowired
    private StationRelativeService stationRelativeService;

    @Autowired
    private StationDataService stationDataService;

    /**
     * 初始化边对象
     * @return
     */
    public EdgeData[] initEdgeData() {
        List<StationInterval> stationIntervals = stationRelativeService.exactSearchIntervalById();
        EdgeData[] edgeData = new EdgeData[stationIntervals.size()];
        EdgeData edgeData1;
        for (int i = 0; i < stationIntervals.size(); i++) {
            edgeData1 = new EdgeData();
            edgeData1.setStart(stationIntervals.get(i).getLastStationName());
            edgeData1.setEnd(stationIntervals.get(i).getNextStationName());
            edgeData1.setWeight(stationIntervals.get(i).getStationInterval());
            edgeData[i] = edgeData1;
        }
        return edgeData;
    }

    /**
     * 初始化顶点对象
     * @param
     * @return
     */
    public List<String> initVertexData(){
        List<SubwayStation> subwayStations = stationDataService.exactSearchStationById();
        Set<String> stations = new HashSet<>();
        for (int i = 0; i < subwayStations.size(); i++) {
            stations.add(subwayStations.get(i).getStationName());
        }
        List<String> stations1 = new ArrayList<>(stations);
        return stations1;
    }
}
