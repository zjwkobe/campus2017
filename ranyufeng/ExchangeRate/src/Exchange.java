import java.io.File;
import java.io.IOException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class Exchange {
	public void getPageUrl() throws IOException{
		File file = new File(System.currentTimeMillis() + ".xls");
		WritableWorkbook wwb = null;
		WritableSheet ws = null;
		try {
			wwb = Workbook.createWorkbook(file);
			ws = wwb.createSheet("首页", 0);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Document Document = Jsoup.connect("http://www.chinamoney.com.cn/fe-c/historyParity.do").post();
		Element Body = Document.body();
		Element Table = Body.getElementsByTag("table").last();
		Elements Row = Table.getElementsByTag("tr");

		for(int i = 0; i < Row.size(); i++ ){
			Elements Td = Row.get(i).getElementsByTag("td");
			String date = Td.get(0).getElementsByTag("div").get(0).text();
			String meiyuan=Td.get(1).text();
			String ouyuan=Td.get(2).text();
			String gangyuan=Td.get(4).text();
			Label labelt = new Label(0, i, date);
			Label labelm = new Label(1, i, meiyuan);
			Label labelo = new Label(2, i, ouyuan);
			Label labelg = new Label(3, i, gangyuan);
			NumberFormat sevendps = new NumberFormat("#.000000");
			WritableCellFormat wcf = new WritableCellFormat(sevendps);
			labelm.setCellFormat(wcf);
			labelo.setCellFormat(wcf);
			labelg.setCellFormat(wcf);
			try{
			ws.addCell(labelt);
			ws.addCell(labelm);
			ws.addCell(labelo);
			ws.addCell(labelg);
		}  catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		}
		try {
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	}
	public static void main(String args[])throws IOException{
		Exchange exchange = new Exchange();
		exchange.getPageUrl();
	}
}
