package com.chengjiaqi.beijing.subway.service.dbservice;

import com.chengjiaqi.beijing.subway.dao.SubwayLineMapper;
import com.chengjiaqi.beijing.subway.model.vo.SubwayLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengjiaqi
 */
@Service
public class LineDataService {

    @Autowired
    private SubwayLineMapper subwayLineMapper;

    /**
     * 从数据库中取所有线路信息
     * @return
     */
    public List<SubwayLine> exactSearchLineById() {
        Map<String, Object> params = new HashMap<>();
        List<SubwayLine> subwayLines = subwayLineMapper.selectLineById(params);
        return subwayLines;
    }
}
