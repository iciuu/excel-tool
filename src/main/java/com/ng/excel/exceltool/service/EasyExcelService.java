package com.ng.excel.exceltool.service;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.ng.excel.exceltool.dto.WeiboExcel;
import com.ng.excel.exceltool.repository.WeiboRepository;
import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EasyExcelService {

    @Autowired
    private WeiboRepository weiboRepository;

    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<WeiboExcel> list = new ArrayList<>();
        WeiboExcel sina = new WeiboExcel();
        sina.setUserId(1);
        sina.setWeiboId(1);
        sina.setWeiboText("@you");
        sina.setCreateDate(new Date());
        list.add(sina);
        writeExcel(response,list,"ColorfulWorld",new WeiboExcel());
    }
    private void writeExcel(HttpServletResponse response, List<? extends BaseRowModel> list,
                            String fileName, BaseRowModel object) throws IOException {
        /*fileName =  new String(fileName.getBytes(), "ISO-8859-1");
        //创建本地文件
        String relativelyPath = System.getProperty("user.dir");
        String filePath = relativelyPath + fileName;
        File dbfFile = new File(filePath);
        if (!dbfFile.exists() || dbfFile.isDirectory()) {
            dbfFile.createNewFile();
        }*/
        response.addHeader("Content-Disposition", "attachment");
        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        OutputStream out = response.getOutputStream();
        try{
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLS);
            Sheet sheet = new Sheet(1, 0, object.getClass());
            writer.write(list, sheet);
            writer.finish();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                out.close();
                /*if(dbfFile.exists()){
                    dbfFile.delete()
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public InputStream exportUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin","*");
        List<WeiboExcel> list = new ArrayList<>();
        WeiboExcel sina = new WeiboExcel();
        sina.setUserId(1);
        sina.setWeiboId(1);
        sina.setWeiboText("@you");
        sina.setCreateDate(new Date());
        list.add(sina);
        return writeExcel(list, new Sheet(1,0,WeiboExcel.class),response);
    }

    private InputStream writeExcel(List<? extends BaseRowModel> dataList,Sheet sheet,HttpServletResponse response) throws Exception{
        String relativelyPath = System.getProperty("user.dir");
        String fileName = relativelyPath + "/temp" + System.currentTimeMillis() + ".xls";
        OutputStream out = new FileOutputStream(fileName);
        InputStream result = null;
        try{
            ExcelWriter writer = new ExcelWriter(out,ExcelTypeEnum.XLS);
            writer.write(dataList, sheet);
            writer.finish();
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            result = new FileInputStream(fileName);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            out.flush();
            return result;
        }
    }


    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        //创建本地文件
        String filePath = fileName + ".xls";
        File dbfFile = new File(filePath);
        try {
            if (!dbfFile.exists() || dbfFile.isDirectory()) {
                dbfFile.createNewFile();
            }
            fileName = new String(filePath.getBytes(), "ISO-8859-1");
            response.addHeader("Content-Disposition", "filename=" + fileName);
            return response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response  HttpServletResponse
     * @param list      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param object    映射实体类，Excel 模型
     */
    /*@Throws(IOException::class)
    fun writeExcel(
            response: HttpServletResponse,
            list: List<BaseRowModel>,
            fileName: String,
            clazz: Class<out BaseRowModel>
    ) {
        *//*var fileName =  String(fileName.toByteArray(), Charset.forName("ISO-8859-1"))
        //创建本地文件
        val relativelyPath = System.getProperty("user.dir")
        val filePath = "$relativelyPath/$fileName.xlsx"
        val dbfFile = File(filePath)
        if (!dbfFile.exists() || dbfFile.isDirectory()) {
            dbfFile.createNewFile()
        }*//*
        response.addHeader("Content-Disposition", "attachment")
        response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8")
        val out = response.outputStream
        try {
            val writer = ExcelWriter(out, ExcelTypeEnum.XLS)
            val sheet = Sheet(1, 0, clazz)
            writer.write(list, sheet)
            writer.finish()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                out.close()
                *//*if(dbfFile.exists()){
                    dbfFile.delete()
                }*//*
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }*/
}
