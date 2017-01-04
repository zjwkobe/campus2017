package com.shj.ExchangeRate1;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HttpGetUtils {
	public static void main(String[] args) {

	}

	public static String getHTML(String url) {
		String result = "";
		try {
			// 获取httpclient实例
			CloseableHttpClient httpclient = HttpClients.createDefault();
			// 获取方法实例。POST
			HttpPost httpPost = new HttpPost(url);
			// 建立一个NameValuePair数组，用于存储欲传送的参数
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// 添加参数
			// 时间格式化
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			String endDate = formatter.format(new Date());
			
			Calendar now = Calendar.getInstance();
			now.setTime(new Date());
			now.set(Calendar.DATE, now.get(Calendar.DATE) - 30);
			String startDate = formatter.format(now.getTime());
			
			params.add(new BasicNameValuePair("startDate", startDate));
			params.add(new BasicNameValuePair("endDate", endDate));
			// 设置编码
			httpPost.setEntity((HttpEntity) new UrlEncodedFormEntity(params, HTTP.UTF_8));
			// 执行方法得到响应
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				// 如果正确执行而且返回值正确，即可解析
				if (response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();
					// 从输入流中解析结果
					result = readResponse(entity, "utf-8");
				}
			} finally {
				httpclient.close();
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String readResponse(HttpEntity resEntity, String charset) {
		StringBuffer res = new StringBuffer();
		BufferedReader reader = null;
		try {
			if (resEntity == null) {
				return null;
			}

			reader = new BufferedReader(new InputStreamReader(resEntity.getContent(), charset));
			String line = null;

			while ((line = reader.readLine()) != null) {
				res.append(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}

}