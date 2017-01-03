package com.shj.ExchangeRate1;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {
	/**
	 * @功能：手工构建一个简单格式的Excel
	 */

	public static void exportToExcel(List<Item> list) throws Exception {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("人民币汇率");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("日期");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("美元:人民币");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("欧元:人民币");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("港币:人民币");
		cell.setCellStyle(style);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Item stu = (Item) list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(stu.getDate());
			row.createCell((short) 1).setCellValue(stu.getUSD());
			row.createCell((short) 2).setCellValue(stu.getEUR());
			row.createCell((short) 3).setCellValue(stu.getHKD());
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("./ExchangeRate"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
