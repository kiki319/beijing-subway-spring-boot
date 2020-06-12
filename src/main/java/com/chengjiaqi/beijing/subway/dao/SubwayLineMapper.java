package com.chengjiaqi.beijing.subway.dao;

import com.chengjiaqi.beijing.subway.model.vo.SubwayLine;
import com.chengjiaqi.beijing.subway.model.vo.SubwayStation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author chengjiaqi
 */
@Repository
public interface SubwayLineMapper {

    List<SubwayLine> selectLineById(Map<String, Object> params);

}
