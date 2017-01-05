package exchangeRate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Data {
      private String date;
      private String USD;
      private String EUR;
      private String HKD;
     
      public Data(String date,String USD,String EUR,String HKD){
    	  this.date=date;
    	  this.USD=USD;
    	  this.EUR=EUR;
    	  this.HKD=HKD;
      }

	public String getDate() {
		return date;
	}

	public String getUSD() {
		return USD;
	}
	
	public String getEUR() {
		return EUR;
	}

	public String getHKD() {
		return HKD;
	}



      
      
//      public static void main(String[] args) {
//    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	  Date d = new Date();
//    	  String s = sdf.format(d);
//		 System.out.println(s);
//		 Calendar c = Calendar.getInstance();
//		 c.set(Calendar.DATE,c.get(Calendar.DATE)-30);
//		 String str = sdf.format(c.getTime());
//		 System.out.println(str);
//		 
//	}
}
