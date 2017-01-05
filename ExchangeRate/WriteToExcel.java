package exchangeRate;

import java.io.File;
import java.util.List;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class WriteToExcel {

	public void writeToExcel(List<Data> data,File file){		
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet("汇率表",0);
				Label label1 = new Label(0,0,"日期");
				Label label2= new Label(1,0,"美元");
				Label label3 = new Label(2,0,"欧元");
				Label label4= new Label(3,0,"100港元");
				
				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);	
				
				
			for (int i = 0; i < data.size(); i++) {			
					Label date = new Label(0,i+1,data.get(i).getDate());
					Label USD = new Label(1,i+1,data.get(i).getUSD());
					Label EUR = new Label(2,i+1,data.get(i).getEUR());
					Label HKD = new Label(3,i+1,data.get(i).getHKD());
					
					sheet.addCell(date);
					sheet.addCell(USD);
					sheet.addCell(EUR);
					sheet.addCell(HKD);
			}
			
			workbook.write();
			workbook.close();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
