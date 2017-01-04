package com.shj.ExchangeRate1;

/**
 * Created by shenhaojie on 2017/1/4.
 */
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuyin on 16-11-16.
 */
public class WriteToExcel {
    public static void writeToExcel(List<Item> list,String path){
        WritableWorkbook writable = null;
        File file = new File(path);

        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            writable = Workbook.createWorkbook(file);
            WritableSheet sheet = writable.createSheet("汇率",0);
            Label label1 = new Label(0,0,"日期");
            Label label2 = new Label(1,0,"美元:人民币");
            Label label3 = new Label(2,0,"欧元:人民币");
            Label label4 = new Label(3,0,"港币:人民币");
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            int j=1;

            for(Item item:list){
                Label date =  new Label(0,j,item.getDate());
                Label usd =  new Label(1,j,item.getUSD());
                Label eur =  new Label(2,j,item.getEUR());
                Label hkd =  new Label(3,j,item.getHKD());

                sheet.addCell(date);
                sheet.addCell(usd);
                sheet.addCell(eur);
                sheet.addCell(hkd);

                j++;
            }

            writable.write();
            writable.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}