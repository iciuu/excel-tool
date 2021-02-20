package com.ng.excel.exceltool.controller;

import com.ng.excel.exceltool.service.EasyExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
@RequestMapping("/easyExcel")
public class EasyExcelController {

    @Autowired
    private EasyExcelService easyExcelService;

    @RequestMapping("/export")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        try {
            easyExcelService.export(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/exportUrl")
    public InputStream exportUrl(HttpServletRequest request, HttpServletResponse response) {
        InputStream filePath = null;
        try {
            filePath = easyExcelService.exportUrl(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /*
    * react request style
    *
    * async function downloadExcel() {
        const res = await request('/easyExcel/webStreamDownload', {
        params: { ...queryParam },
        method: 'post',
        responseType: 'blob',
        });
      const blob = new Blob([res], { type: 'octet/stream' });
      const a = document.createElement('a');
      a.href = URL.createObjectURL(blob);
      a.download = 'ss.xlsx';
      a.style.display = 'none';
      document.body.appendChild(a);
      a.click();
      a.remove();
      }
    *
    * */
    @PostMapping("/webStreamDownload")
    public void webStreamDownload(HttpServletRequest request, HttpServletResponse response) {
        easyExcelService.outputStreamDownload(response);
    }
}
