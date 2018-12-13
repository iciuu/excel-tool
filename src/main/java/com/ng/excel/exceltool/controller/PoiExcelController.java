package com.ng.excel.exceltool.controller;

import com.ng.excel.exceltool.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/poi")
public class PoiExcelController {

    @Autowired
    public PoiService poiService;

    @RequestMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        try {
            poiService.export(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
