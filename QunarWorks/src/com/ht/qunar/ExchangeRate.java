package com.ht.qunar;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableHeader;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ExchangeRate {
	static List<ERBean> erBeans = new ArrayList<ERBean>();
	static class ERBean{
		String date;
		double dollar;
		double euro;
		double HKDollar;
		public ERBean(){
			
		}
		public ERBean(String date,double dollar,double euro,double HKDollar){
			this.date = date;
			this.dollar = dollar;
			this.euro = euro;
			this.HKDollar = HKDollar;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public double getDollar() {
			return dollar;
		}
		public void setDollar(double dollar) {
			this.dollar = dollar;
		}
		public double getEuro() {
			return euro;
		}
		public void setEuro(double euro) {
			this.euro = euro;
		}
		public double getHKDollar() {
			return HKDollar;
		}
		public void setHKDollar(double hKDollar) {
			HKDollar = hKDollar;
		}
		
	}
	public static void main(String[] args) throws ParserException {
		// TODO Auto-generated method stub
		String filePath = downloadFileFromUrl("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action?projectBean.startDate='2016-12-18'&projectBean.endDate='2017-01-18'&queryYN='true'");
		// downloadFileFromUrl("http://www.safe.gov.cn/wps/portal/sy/tjsj_hlzjj_inquire");
		String sourceContent = getFileContent(filePath);
		parseHtml(sourceContent);
		createExcel(erBeans,"C:\\Users\\HuangTing\\Desktop\\"+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(System.currentTimeMillis())));
		
	}

	private static void createExcel(List<ERBean> erBeans, String destFilePath) {
		WritableWorkbook book = null;
		WritableSheet sheet = null;
		try {
			book = Workbook.createWorkbook(new File(destFilePath));
			sheet = book.createSheet("汇率中间价", 0);
			int row = 0;	
			int col = 0;
			sheet.addCell(new Label(col++,row,"日期"));
			sheet.addCell(new Label(col++,row,"美元"));
			sheet.addCell(new Label(col++,row,"欧元"));
			sheet.addCell(new Label(col++,row,"港元"));
			row = 1;
			for(ERBean er:erBeans){			
				if(er == null){
					continue;
				}
				col = 0;
			    sheet.addCell(new Label(col++,row,er.getDate()));
			    sheet.addCell(new Label(col++,row,String.valueOf(er.getDollar())));
			    sheet.addCell(new Label(col++,row,String.valueOf(er.getEuro())));
			    sheet.addCell(new Label(col++,row,String.valueOf(er.getHKDollar())));
			    row++;			
			}
			book.write();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(book != null){
				try {
					book.close();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			    
	}

	private static void parseHtml(String sourceContent) throws ParserException {
		Parser parser = null;

		parser = Parser.createParser(sourceContent, "UTF-8");
		NodeFilter filter = new TagNameFilter("table");
		NodeList tableList = parser.extractAllNodesThatMatch(filter);
		TableTag tableTag = (TableTag) tableList.elementAt(4);
		TableRow[] tableRows = tableTag.getRows();
		TableHeader[] headers = tableRows[0].getHeaders();
		// 输出标题
		// headers[1].getStringText().trim().indexOf("&nbsp;");
		System.out.print(headers[0].getStringText().trim() + "\t\t\t");
		System.out.print(headers[1]
				.getStringText()
				.trim()
				.substring(0,
						headers[1].getStringText().trim().indexOf("&nbsp;"))
				+ "\t\t");
		System.out.print(headers[2]
				.getStringText()
				.trim()
				.substring(0,
						headers[2].getStringText().trim().indexOf("&nbsp;"))
				+ "\t\t");
		System.out.print(headers[4]
				.getStringText()
				.trim()
				.substring(0,
						headers[4].getStringText().trim().indexOf("&nbsp;"))
				+ "\t\t");
		System.out.println();
		for (int r = 1; r < tableRows.length; r++) {
			TableRow tableRow = tableRows[r];
			ERBean er = new ERBean();
			erBeans.add(er);
			TableColumn[] tableColumns = tableRow.getColumns();
			for (int c = 0; c < tableColumns.length; c++) {
				if (c == 0 || c == 1 || c == 2 || c == 4) {//选择指定几种汇率（美元等）
					switch (c) {
					case 0:
						er.setDate(tableColumns[c]
								.toPlainTextString()
								.trim()
								.substring(
										0,
										tableColumns[c].toPlainTextString()
												.trim().indexOf("&nbsp;")));
						break;
					case 1:
						er.setDollar(Double.parseDouble(tableColumns[c]
								.toPlainTextString()
								.trim()
								.substring(
										0,
										tableColumns[c].toPlainTextString()
												.trim().indexOf("&nbsp;"))));
						break;
					case 2:
						er.setEuro(Double.parseDouble(tableColumns[c]
								.toPlainTextString()
								.trim()
								.substring(
										0,
										tableColumns[c].toPlainTextString()
												.trim().indexOf("&nbsp;"))));
						break;
					case 4:
						er.setHKDollar(Double.parseDouble(tableColumns[c]
								.toPlainTextString()
								.trim()
								.substring(
										0,
										tableColumns[c].toPlainTextString()
												.trim().indexOf("&nbsp;"))));
						break;

					}
					//输出汇率
					System.out.print(tableColumns[c]
							.toPlainTextString()
							.trim()
							.substring(
									0,
									tableColumns[c].toPlainTextString().trim()
											.indexOf("&nbsp;"))
							+ "\t\t");
				}

			}
			System.out.println();
		}
	}
	 
	private static String getFileContent(String filePath) {//获取文件内容（字符串形式）
		File file = new File(filePath);
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		String line = null;
		String content = "";
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis,"UTF-8");//处理中文乱码问题
			br = new BufferedReader(isr);
			while((line=br.readLine()) != null){
				content += line + "\n";
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return content;
		}
		
	}

	public static String getFileNameByUrl(String url, String contentType) {//定义文件名称
		url = url.substring(7);
		if (contentType.indexOf("html") != -1) {//html文件类型
			url = url.replaceAll("[\\?/:*|<>\"]", "_") + ".html";
			return url;
		} else// 其他文件类型
		{
			return url.replaceAll("[\\?/:*|<>\"]", "_") + "."
					+ contentType.substring(contentType.lastIndexOf("/") + 1);
		}

	}

	private static void saveToLocal(byte[] data, String filePath) {//保存网页源代码到本地文件
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					new File(filePath)));
			for (int i = 0; i < data.length; i++){
				out.write(data[i]);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void saveToLocal(InputStream data, String filePath){
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filePath)));

			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = data.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String downloadFileFromUrl(String url) {
		// TODO Auto-generated method stub
		HttpClient httpClient = new HttpClient();
		String filePath = null;
		GetMethod getMethod = null;
		try {
			httpClient.getHttpConnectionManager().getParams()
					.setConnectionTimeout(5000);// 设置链接超时为5s
			getMethod = new GetMethod(url);
			// getMethod.get
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "+ getMethod.getStatusLine());
				filePath = null;
			}
			// byte[] responseBody = getMethod.getResponseBody();
			InputStream responseBody = getMethod.getResponseBodyAsStream();
			filePath = "C:\\Users\\HuangTing\\Desktop\\"+ getFileNameByUrl(url,getMethod.getResponseHeader("Content-Type").getValue());
			saveToLocal(responseBody, filePath);

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return filePath;

	}

}
