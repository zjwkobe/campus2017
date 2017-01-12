package com.excel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by smile on 2017/1/9.
 */
public class OutputExcel {
    public void exportTable(List<List<String>> allrata) throws Exception {

        File path = new File("");

        File file = new File(path.getAbsolutePath()+"/excel.xls");
        file.createNewFile();
        OutputStream os = new FileOutputStream(file);
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        WritableSheet sheet = workbook.createSheet("rate",0);
//        sheet.setRowView();
        Label label1 = new Label(0,0,"日期/汇率");

        sheet.addCell(label1);
        label1 = new Label(1,0,"美元");

        sheet.addCell(label1);
        label1 = new Label(2,0,"欧元");
        sheet.addCell(label1);
        label1 = new Label(3,0,"港币");
        sheet.addCell(label1);
        List<String> celllist;
        for(int i=0;i<allrata.size()&&i<30;i++){
            celllist = allrata.get(i);
            for(int j=0;j<celllist.size();j++){
                label1 = new Label(j,i+1,celllist.get(j));
                sheet.addCell(label1);
            }
        }
        workbook.write();
        workbook.close();
        os.close();
    }

}
