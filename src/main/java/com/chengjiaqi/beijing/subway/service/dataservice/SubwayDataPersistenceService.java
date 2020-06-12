package com.chengjiaqi.beijing.subway.service.dataservice;

import com.chengjiaqi.beijing.subway.dao.PersistenceDataMapper;
import com.chengjiaqi.beijing.subway.model.vo.PersistenceData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author chengjiaqi
 */
@Service
@Slf4j
public class SubwayDataPersistenceService {
    private static final Logger logger = LoggerFactory.getLogger(SubwayDataPersistenceService.class);

    @Value("${subway.url}")
    private String url;
    @Value("${subway.lineUrl}")
    private String lineUrl;
    @Value("${subway.stationUrl}")
    private String stationUrl;

    @Autowired
    private SubwayBaseDataService SubwayBaseDataService;
    @Autowired
    private PersistenceDataMapper persistenceDataMapper;

    public void batchInsertLineInfo() throws IOException {
        Integer isDel = 0;
        String persistenceDataInfo;
        List<List<String>> subwayLineInfos;
        persistenceDataInfo = SubwayBaseDataService.getSubwayInfo(lineUrl);
        subwayLineInfos = SubwayBaseDataService.chooseSubwayLineInfo(persistenceDataInfo);
        PersistenceData persistenceData;
        List<PersistenceData> persistenceDataList = new ArrayList<>();
        for (int i = 0; i < subwayLineInfos.size(); i++) {
            persistenceData = new PersistenceData();
            try {
                persistenceData.setSubwayStationName(subwayLineInfos.get(i).get(2));
                persistenceData.setStationL(subwayLineInfos.get(i).get(3));
                persistenceData.setStationOrder(subwayLineInfos.get(i).get(0));
                persistenceData.setSubwayLineNumber(subwayLineInfos.get(i).get(1));
                persistenceData.setSid(subwayLineInfos.get(i).get(4));
                persistenceData.setIsDel(isDel);
                persistenceDataList.add(persistenceData);
                logger.info("线路信息设置完毕");
                logger.info("线路[{}]信息写入数据库完毕", persistenceData);
            } catch (Exception e) {
                logger.error("持久化存储线路[{}]信息失败", persistenceData, e);
            }
        }
        int i = persistenceDataMapper.batchInsertSubwayLine(persistenceDataList);
        log.info("添加记录条数是:{}", i);
    }
}
