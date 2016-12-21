package com.yuanhaolv.service;

import com.yuanhaolv.api.ICrawData;
import com.yuanhaolv.api.IDataToExcel;
import com.yuanhaolv.model.Area;
import com.yuanhaolv.model.Exchange;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 元浩 on 2016/12/21.
 */
@Service("dataToExcel")
public class DataToExcelImpl implements IDataToExcel {
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    @Autowired
    ICrawData crawData;

    public void generateExcel() throws IOException {
        List<Exchange> listEurope = crawData.crawData(Area.EUROPE);
        List<Exchange> listUSA = crawData.crawData(Area.USA);
        List<Exchange> listHK = crawData.crawData(Area.HK);

        ByteArrayOutputStream baos = getOutputStreamFromData(listEurope, listUSA, listHK);
        byte[] xls = baos.toByteArray();
        Date date = new Date();
        String tableName = new SimpleDateFormat("yyyy-MM-dd").format(date);//表名
        File file = new File(tableName + ".xls");
        OutputStream os = null;
        try{
            os = new FileOutputStream(file);
            try{
                os.write(xls);
            }catch (IOException e){

            }finally {
                os.close();
            }
        }catch (IOException e){

        }finally {
            try{
                if (os != null ){
                    os.close();
                }
                if (baos != null ){
                    baos.close();
                }
            }catch (IOException e){

            }
        }
    }
    /**
     *
     *   Description: 从原始数据生成数据流
     *   @authour 元浩
     *   @date 2016/12/21
     *
     *   @param europe 欧元数据
     *   @param usa 美元数据
     *   @param hk 港币数据
     *   @return ByteArrayOutputStream 输出数据流
     *   @throws
     *   @since
     **/
    private ByteArrayOutputStream getOutputStreamFromData(List<Exchange> europe,
                                                          List<Exchange> usa, List<Exchange> hk) throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

//
        HSSFWorkbook table = new HSSFWorkbook();
        //创建三个工作sheet
        HSSFSheet sheetEurope = table.createSheet("Europe");
        sheetEurope.setColumnWidth(0, 20* 256);
        sheetEurope.setColumnWidth(1, 20* 256);
        sheetEurope.setColumnWidth(2, 20* 256);

        HSSFSheet sheetUSA = table.createSheet("USA");
        sheetUSA.setColumnWidth(0, 20* 256);
        sheetUSA.setColumnWidth(1, 20* 256);
        sheetUSA.setColumnWidth(2, 20* 256);

        HSSFSheet sheetHK = table.createSheet("HK");
        sheetHK.setColumnWidth(0, 20* 256);
        sheetHK.setColumnWidth(1, 20* 256);
        sheetHK.setColumnWidth(2, 20* 256);

        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        //欧元表格第一行
        //sheetEurope.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFRow europeRow0 = sheetEurope.createRow(0);
        HSSFCell europeRow0_1 = europeRow0.createCell(0);
        HSSFCell europeRow0_2 = europeRow0.createCell(1);
        HSSFCell europeRow0_3 = europeRow0.createCell(2);
        europeRow0_1.setCellValue("Area/Country");
        europeRow0_2.setCellValue("Date");
        europeRow0_3.setCellValue("Exchange");

        for (int i = 0; i < europe.size(); i++){
            Exchange exchange = europe.get(i);
            //sheetEurope.addMergedRegion(new CellRangeAddress(i+1, i+1, 0, 2));
            HSSFRow row = sheetEurope.createRow(i+1);
            HSSFCell row_1 = row.createCell(0);
            HSSFCell row_2 = row.createCell(1);
            HSSFCell row_3 = row.createCell(2);
            row_1.setCellValue(exchange.getArea().toString());
            row_2.setCellValue(format.format(exchange.getDate()));
            row_3.setCellValue(exchange.getExchange());
        }

        //美元表格第一行
        //sheetUSA.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFRow usaRow0 = sheetUSA.createRow(0);
        HSSFCell usaRow0_1 = usaRow0.createCell(0);
        HSSFCell usaRow0_2 = usaRow0.createCell(1);
        HSSFCell usaRow0_3 = usaRow0.createCell(2);
        usaRow0_1.setCellValue("Area/Country");
        usaRow0_2.setCellValue("Date");
        usaRow0_3.setCellValue("Exchange");

        for (int i = 0; i < usa.size(); i++){
            Exchange exchange = usa.get(i);
//            sheetUSA.addMergedRegion(new CellRangeAddress(i+1, i+1, 0, 2));
            HSSFRow row = sheetUSA.createRow(i+1);
            HSSFCell row_1 = row.createCell(0);
            HSSFCell row_2 = row.createCell(1);
            HSSFCell row_3 = row.createCell(2);
            row_1.setCellValue(exchange.getArea().toString());
            row_2.setCellValue(format.format(exchange.getDate()));
            row_3.setCellValue(exchange.getExchange());
        }

        //港币第一行
//        sheetHK.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        HSSFRow hkRow0 = sheetHK.createRow(0);
        HSSFCell hkRow0_1 = hkRow0.createCell(0);
        HSSFCell hkRow0_2 = hkRow0.createCell(1);
        HSSFCell hkRow0_3 = hkRow0.createCell(2);
        hkRow0_1.setCellValue("Area/Country");
        hkRow0_2.setCellValue("Date");
        hkRow0_3.setCellValue("Exchange");

        for (int i = 0; i < hk.size(); i++){
            Exchange exchange = hk.get(i);
//            sheetHK.addMergedRegion(new CellRangeAddress(i+1, i+1, 0, 2));
            HSSFRow row = sheetHK.createRow(i+1);
            HSSFCell row_1 = row.createCell(0);
            HSSFCell row_2 = row.createCell(1);
            HSSFCell row_3 = row.createCell(2);
            row_1.setCellValue(exchange.getArea().toString());
            row_2.setCellValue(format.format(exchange.getDate()));
            row_3.setCellValue(exchange.getExchange());
        }

        table.write(baos);

        return baos;
    }
}
