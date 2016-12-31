
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.FileOutputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
* 使用URLConnection下载文件或图片并保存到本地。
*
*/

public class Parser {
	private final String commonPath=".table-responsive";
	private final String date="td";//日期
	private final String money="td:eq(2)";//1CNY=？
	public List<Item> list;
	public ArrayList<List<Item>> arr;
	public boolean parse(String url) throws Exception{
		Document doc;
		try {
			doc = Jsoup.parse(new URL(url),40000);//如果设置成1ms则Timout
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return true;
		} 
		Element commonElement=doc.select(commonPath).get(0);
		Elements tableElement=commonElement.select("tr");
		//System.out.println(tableElement.size());
		//build a new obj
		for(Element element:tableElement){
			Item item=new Item();
			String dateStr=element.select(date).get(0).text().trim();
			String moneyStr=element.select(money).get(0).text().trim();
			moneyStr=moneyStr.substring(0, moneyStr.length()-4);
			//System.out.println(dateStr);
			//System.out.println(moneyStr);
			item.setDate(dateStr);
			item.setMoney(moneyStr);
			//System.out.println(item.getDate());
			//System.out.println(item.getMoney());
			list.add(item);
		}
//		System.out.println(list.size());
//		for(Item item1:list){
//			System.out.println(item1.getDate());
//			System.out.println(item1.getMoney());
//		}
		return false;
	}
	//test program
	public static void main(String[] args) throws Exception {
		Parser parser=new Parser();
		parser.arr=new ArrayList<List<Item>>();
		String[] currency={"USD","EUR","HKD"};
		for(int i=0;i<currency.length;i++){
			parser.list=new LinkedList<Item>();
			String url="http://cn.exchange-rates.org/history/"+currency[i]+"/CNY/T";
			parser.parse(url);
			//System.out.println(parser.list.size());
			if(parser.list.size()>30) {
				parser.list=parser.list.subList(0, 30);//选取最近30天
			}
			else{
				System.out.println("网络环境不好");
			}
			parser.arr.add(parser.list);
		}
		//test extracted items
		System.out.println("items:");

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("汇率表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("日期");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("美元");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("欧元");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("港币");
		cell.setCellStyle(style);


		for (int i = 0; i < parser.list.size(); i++)
		{
			row = sheet.createRow((int) i + 1);

			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(parser.arr.get(0).get(i).getDate());
			row.createCell((short) 1).setCellValue(parser.arr.get(0).get(i).getMoney());
			row.createCell((short) 2).setCellValue(parser.arr.get(1).get(i).getMoney());
			row.createCell((short) 3).setCellValue(parser.arr.get(2).get(i).getMoney());
		}
		// 第六步，将文件存到指定位置
		try
		{
			FileOutputStream fout = new FileOutputStream("/Users/SDD/WorkSpace_java/ExchangeRate/file/Rates.xls");
			wb.write(fout);
			fout.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

