package com.chengjiaqi.beijing.subway.service.frontservice;

import com.chengjiaqi.beijing.subway.model.vo.SubwayLine;
import com.chengjiaqi.beijing.subway.model.vo.SubwayStation;
import com.chengjiaqi.beijing.subway.service.dbservice.LineDataService;
import com.chengjiaqi.beijing.subway.service.dbservice.StationDataService;
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
public class GetAllLineStation {

    @Autowired
    private LineDataService exactSearchLineById;

    @Autowired
    private StationDataService stationDataService;

    /**
     * 获取所有的线路
     * @return
     */
    public List<String> getAllLines() {
        List<SubwayLine> subwayLines = exactSearchLineById.exactSearchLineById();
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < subwayLines.size(); i++) {
            lines.add(subwayLines.get(i).getLineNumber());
        }
        return lines;
    }

    /**
     * 获取所有的站点
     * @return
     */
    public Set<String> getAllStations() {
        List<SubwayStation> subwayLines = stationDataService.exactSearchStationById();
        Set<String> sites = new HashSet<>();
        for (int i = 0; i < subwayLines.size(); i++) {
            sites.add(subwayLines.get(i).getStationName());
        }
        return sites;
    }

}
