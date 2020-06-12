package com.chengjiaqi.beijing.subway.controller;

import com.chengjiaqi.beijing.subway.entity.ResultData;
import com.chengjiaqi.beijing.subway.entity.ResultQuery;
import com.chengjiaqi.beijing.subway.entity.ResultQueryStation;
import com.chengjiaqi.beijing.subway.model.dto.GetAllSubwayInfoDTO;
import com.chengjiaqi.beijing.subway.model.dto.GetStartEndStationDTO;
import com.chengjiaqi.beijing.subway.model.dto.QueryResultDTO;
import com.chengjiaqi.beijing.subway.model.dto.QueryStationDTO;
import com.chengjiaqi.beijing.subway.service.frontservice.GetAllLineStation;
import com.chengjiaqi.beijing.subway.service.frontservice.QueryLine;
import com.chengjiaqi.beijing.subway.service.frontservice.QueryStation;
import com.chengjiaqi.beijing.subway.service.frontservice.QueryTransfer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Set;

/**
 * @author chengjiaqi
 */
@RestController
@RequestMapping(value = "/subway")
@Slf4j
public class FrontController {

    @Autowired
    private QueryTransfer queryTransfer;
    @Autowired
    private GetAllLineStation getAllLineStation;
    @Autowired
    private QueryStation queryStation;
    @Autowired
    private QueryLine queryLine;


    /**
     * 解决跨域
     * @param request
     * @param response
     */
    public void crossOrigin(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许指定域访问跨域资源
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        // 允许浏览器发送的请求消息头
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        // 允许浏览器在预检请求成功之后发送的实际请求方法名
        response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
    }

    /**
     * 换乘查询
     * @param start
     * @param end
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/data/transfer")
    @CrossOrigin
    public ResultQuery getTransferInfo(@RequestParam("startStation") String start,
                                       @RequestParam("endStation") String end,
                                       HttpServletRequest request, HttpServletResponse response) {
        //得到的结果是[str1,str2]
        log.info("得到的起始站点为{}",start);
        log.info("得到的终点为{}",end);
        GetStartEndStationDTO getStartEndStationDTO = new GetStartEndStationDTO();
        getStartEndStationDTO.setStartStation(start);
        getStartEndStationDTO.setEndStation(end);
        List<QueryResultDTO> result = queryTransfer.getShortestPath(getStartEndStationDTO);
        log.info("是否发出transfer请求{}",result);
        result.forEach(str -> {
            System.out.println(str.getName());
            System.out.println(str.getChange());
        });
        crossOrigin(request,response);
        if (result != null){
            return new ResultQuery(200,"获取地铁站点信息成功",result);
        }else {
            return new ResultQuery(404,"获取地铁站点信息失败", null);
        }
    }

    /**
     * 站点查询
     * @param station
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/data/station")
    @CrossOrigin
    public ResultQueryStation getStationInfo(@RequestParam("station") String station,
                                             HttpServletRequest request, HttpServletResponse response) {
        List<QueryStationDTO> result = queryStation.queryStation(station);
        crossOrigin(request,response);
        if (result != null){
            return new ResultQueryStation(200,"获取地铁站点信息成功",result);
        }else {
            return new ResultQueryStation(404,"获取地铁站点信息失败", null);
        }
    }

    /**
     * 线路查询
     * @param line
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = "/data/line")
    @CrossOrigin
    public ResultQuery getLinesInfo(@RequestParam("line") String line,
                                    HttpServletRequest request, HttpServletResponse response) {
        List<QueryResultDTO> result = queryLine.queryLine(line);
        crossOrigin(request,response);
        if (result != null){
            return new ResultQuery(200,"获取地铁线路信息成功",result);
        }else {
            return new ResultQuery(404,"获取地铁线路信息失败", null);
        }
    }

    /**
     * 实现模糊搜索线路和站点
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/data",method = RequestMethod.GET)
    public ResultData getInfo(HttpServletRequest request, HttpServletResponse response){
        List<String> lines = getAllLineStation.getAllLines();
        Set<String> sites = getAllLineStation.getAllStations();
        GetAllSubwayInfoDTO getAllSubwayInfoDTO = new GetAllSubwayInfoDTO();
        getAllSubwayInfoDTO.setLines(lines);
        getAllSubwayInfoDTO.setSites(sites);
        crossOrigin(request,response);
        if (lines != null && sites != null){
            return new ResultData(200,"获取地铁站点信息成功",getAllSubwayInfoDTO);
        }else {
            return new ResultData(404,"获取地铁站点信息失败",null);
        }
    }

}
