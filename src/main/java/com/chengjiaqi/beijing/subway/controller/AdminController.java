package com.chengjiaqi.beijing.subway.controller;

import com.chengjiaqi.beijing.subway.service.adminservice.DeleteStation;
import com.chengjiaqi.beijing.subway.service.adminservice.EditStation;
import com.chengjiaqi.beijing.subway.service.adminservice.IncreaseStation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chengjiaqi
 */
@RestController
@RequestMapping(value = "/subway")
@Slf4j
public class AdminController {
    @Autowired
    private EditStation editStation;
    @Autowired
    private DeleteStation deleteStation;
    @Autowired
    private IncreaseStation increaseStation;
    @Autowired
    private FrontController frontController;

    @GetMapping(value = "/admin/increase")
    @CrossOrigin
    public void getIncreaseInfo(@RequestParam("line") String line, @RequestParam("station") String station,
                                @RequestParam("time") Integer time, HttpServletRequest request,
                                HttpServletResponse response){

        log.info("收到的线路为{} 收到的新站点为{} 收到的时间为{}",line,station,time);
        increaseStation.insertStationRelativeData(line,station,time);
        increaseStation.insertStationData(line,station);
        frontController.crossOrigin(request,response);
    }

    /**
     * 删除站点
     * @param line
     * @param station
     * @param request
     * @param response
     */
    @GetMapping(value = "/admin/delete")
    @CrossOrigin
    public void getDeleteInfo(@RequestParam("line") String line, @RequestParam("station") String station,
                              HttpServletRequest request, HttpServletResponse response) {
        deleteStation.deleteStationRelativeData(line,station);
        deleteStation.deleteStationData(line,station);
        frontController.crossOrigin(request,response);
    }

    /**
     * 修改站点
     * @param station
     * @param newStation
     * @param request
     * @param response
     */
    @GetMapping(value = "/admin/edit")
    @CrossOrigin
    public void getEditInfo(@RequestParam("station") String station,@RequestParam("newStation") String newStation,
                            HttpServletRequest request,HttpServletResponse response){

        editStation.editData(station, newStation);
        frontController.crossOrigin(request,response);
    }

}
