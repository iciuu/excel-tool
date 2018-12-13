package com.ng.excel.exceltool.service;

import com.ng.excel.exceltool.dto.CellExcel;
import com.ng.excel.exceltool.model.Comments;
import com.ng.excel.exceltool.model.Fans;
import com.ng.excel.exceltool.model.User;
import com.ng.excel.exceltool.model.Weibo;
import com.ng.excel.exceltool.repository.CommentsRepository;
import com.ng.excel.exceltool.repository.FansRepository;
import com.ng.excel.exceltool.repository.UserRepository;
import com.ng.excel.exceltool.repository.WeiboRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PoiService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeiboRepository weiboRepository;

    @Autowired
    private FansRepository fansRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    public static final String MARKET_DOWNLOAD_TMP_PATH = "/exceltemplate/biaoshudating.xlsx";

    private DateTimeFormatter dataFormater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.addHeader("Content-Disposition", "attachment");
        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");

        List<Weibo> weiboList = IteratorUtils.toList(weiboRepository.findAll().iterator());
        if(CollectionUtils.isEmpty(weiboList)){
            return;
        }

        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row rowTitle = sheet.createRow(0);
        rowTitle.createCell(0).setCellValue("微博内容");
        rowTitle.createCell(1).setCellValue("评论");
        rowTitle.createCell(2).setCellValue("点赞");

        int rowNum = 1;
        for (int i = 0; i < weiboList.size(); i++) {
            List<Comments> commentsList = commentsRepository.findByWeiboId(weiboList.get(i).getWeiboId());
            List<Fans> fansList = fansRepository.findByWeiboId(weiboList.get(i).getWeiboId());
            int rowNumTmp = 0;
            if(commentsList.size() > fansList.size()){
                rowNumTmp = commentsList.size();
            } else {
                rowNumTmp = fansList.size();
            }
            boolean first = true;
            for (int j = 0; j < rowNumTmp; j++) {
                Row rowTmp = sheet.createRow(rowNum++);
                if(first){
                    rowTmp.createCell(0).setCellValue(weiboList.get(i).getWeiboText());
                    first = false;
                }
                if(commentsList.size() >= j+1 && commentsList.get(j) != null){
                    rowTmp.createCell(1).setCellValue(commentsList.get(j).getCommentText());
                }
                if(fansList.size() >= j+1 && fansList.get(j) != null){
                    rowTmp.createCell(2).setCellValue(fansList.get(j).getUserId());
                }
            }
        }
        workbook.setActiveSheet(0);
        workbook.write(response.getOutputStream());
    }

    /*
     * 有模版写excel
     * */
    public void writeDataWithTemplate(
            String templateFilePath,
            List<CellExcel> dataList,
            List<Integer> sheetNoList,
            OutputStream out
    ) throws Exception {
        // 获取excel模版
        Workbook workbook = getTmpWorkbook(templateFilePath);
        if (dataList != null && dataList.size() > 0 && sheetNoList != null && sheetNoList.size() > 0) {
            for(int sheetNo:sheetNoList ){
                Sheet sheetTmp = workbook.getSheetAt(sheetNo);
                for(CellExcel dataCell: dataList){
                    if (dataCell.getSheetNo() == sheetNo) {
                        setCell(dataCell, sheetTmp);
                    }
                }
                workbook.setActiveSheet(sheetNo);
            }
        }
        workbook.write(out);
        workbook.close();
    }

    /*
     * 写入excel cell
     * */
    private void setCell(CellExcel dataCell, Sheet sheet) {
        // 是否需要合并单元格
        if (dataCell.getRowSize() > -1 || dataCell.getColumnSize() > -1) {
            CellRangeAddress rangeAddress = new CellRangeAddress(
                    dataCell.getRow(),
            dataCell.getRowSize() > -1 ? dataCell.getRow() + dataCell.getRowSize() -1 : dataCell.getRow(),
                    dataCell.getColumn(),
            dataCell.getColumnSize() > -1 ? dataCell.getColumn() + dataCell.getColumnSize() -1 : dataCell.getColumn());

            sheet.addMergedRegion(rangeAddress);
        }

        // 得到写入行
        Row rowIn = sheet.getRow(dataCell.getRow());
        if (null == rowIn) {
            rowIn = sheet.createRow(dataCell.getRow());
        }

        // 得到写入单元格
        Cell cellIn = rowIn.getCell(dataCell.getColumn());
        if (null == cellIn) {
            cellIn = rowIn.createCell(dataCell.getColumn());
        }

        // 写入数据
        Object data = dataCell.getData();
        if (data instanceof String) {
            cellIn.setCellValue(data.toString());
        } else if (data instanceof LocalDateTime) {
            cellIn.setCellValue(((LocalDateTime) data).format(dataFormater));
        } else if (data instanceof Double) {
            cellIn.setCellValue((Double) data);
        } else if(data != null){
            cellIn.setCellValue(data.toString());
        }
    }


    /*
     * 获取模版输入
     * */
    private Workbook getTmpWorkbook(String templateFilePath) throws Exception{
        // 本地读取
        InputStream tmpInputStream = this.getClass().getResourceAsStream(templateFilePath);
        // 发布读取
        //val tmpInputStream = this.javaClass.classLoader.getResourceAsStream(templateFilePath)
        return WorkbookFactory.create(tmpInputStream);
    }
}
