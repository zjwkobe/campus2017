package exchangeRate;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class ExchangeRate {
     public static List<Data> crawler() throws IOException {
    	 String startDate;
    	 String endDate;
    	 SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
    	 Calendar c = Calendar.getInstance();
    	 endDate=sdf.format(new Date()).toString();
    	 c.set(Calendar.DATE,c.get(Calendar.DATE)-30);
    	 startDate=sdf.format(c.getTime());
    	Document doc =  Jsoup.connect("http://www.chinamoney.com.cn/fe-c/historyParity.do")
    			            .data("startDate", startDate)
    			            .data("endDate", endDate)
    			            .data("Submit", "true")   			            
    			            .post();   			               	                            
    	Element table= doc.select("table").get(2);
   
    	List<Element> tr = table.select("tr");
    	
    	List<Data> list = new ArrayList<Data>();
    	 	
	    for (int j = 1; j < tr.size(); j++) {
	    	String Date = null;
	    	String USD = null;
	    	String EUR = null;
	    	String HKD = null;
	    	List<Element> td = tr.get(j).select("td");
			for (int i = 0; i < td.size(); i++) {
				if(i==0){
					Date=td.get(0).text();
				}
				else if(i==1){
					USD=td.get(1).text();
				}
				else if(i==2){
					EUR=td.get(2).text();
				}
				else if(i==4){
					HKD=td.get(3).text();
					list.add(new Data(Date,USD,EUR,HKD));
				}else{
					continue;
				}
			}
	    	
		}
    	return list;
    	 
     }
     
     
     public static void main(String[] args) throws IOException {
    	 File file = new File("E:/Git/campus2017/ExchangeRate.xls");
    	 if(file.exists()){
    		 file.delete();
    		 file.createNewFile();
    	 }
    	 WriteToExcel wte = new  WriteToExcel();
		try {
			List<Data> list=ExchangeRate.crawler();
			wte.writeToExcel(list,file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
