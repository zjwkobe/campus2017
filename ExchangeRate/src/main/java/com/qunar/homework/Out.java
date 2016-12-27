package com.qunar.homework;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by joe on 2016/11/13.
 */
public class Out {
//    public static void main(String[] args) throws IOException {
//        ArrayList<RMBRate> rates = new ArrayList<RMBRate>();
//        RMBRate rate = new RMBRate();
//        rate.setDate(new Date());
//        HashMap<String, Double> map = new HashMap<String, Double>();
//        map.put("美元", 100.0);
//        map.put("欧元", 30.0);
//        map.put("港元", 60.3);
//        rate.setRate(map);
//
//        rates.add(rate);
//        new Out().printToExcel(rates, new File("d://1.xls"));
//    }

    public void printToExcel(List<RMBRate> list, File file) throws IOException {

        int nowRow = 0;
        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet();
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        //设置第一行标题
        Row row = sheet.createRow(nowRow);
        Font font = wb.createFont();
        font.setFontHeight((short) 500);
        Cell cell = row.createCell(0);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFont(font);
        //左右居中
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cell.setCellValue("近一月人民币汇率");
        cell.setCellStyle(cellStyle);
        nowRow++;
        //------------------------------第一行标题编辑结束
        row = sheet.createRow(nowRow);
        cell = row.createCell(1);
        cell.setCellValue("人民币");
        cell = row.createCell(2);
        cell.setCellValue("港元");
        cell = row.createCell(3);
        cell.setCellValue("美元");
        cell = row.createCell(4);
        cell.setCellValue("欧元");
        cell = row.createCell(5);
        cell.setCellValue("日期");
        nowRow++;
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(nowRow);

            cell = row.createCell(0);
            cell.setCellValue("金额");
            cell = row.createCell(1);
            cell.setCellValue(100);
            cell = row.createCell(2);
            cell.setCellValue(list.get(i).getRate().get("港元"));
            cell = row.createCell(3);
            cell.setCellValue(list.get(i).getRate().get("美元"));
            cell = row.createCell(4);
            cell.setCellValue(list.get(i).getRate().get("欧元"));
            cell = row.createCell(5);
            cell.setCellValue(new SimpleDateFormat("yyyy年mm月dd日").format(list.get(i).getDate()));
            nowRow++;
        }

        FileOutputStream outputStream = new FileOutputStream(file);
        wb.write(outputStream);
        wb.close();
        outputStream.close();
    }
}
