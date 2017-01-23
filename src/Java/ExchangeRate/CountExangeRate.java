package Java.ExchangeRate;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by hk__lrzy on 2017/1/14.
 *
 * Aim:分析从今天开始过去30天时间里，中国人民
 * 银行公布的人民币汇率中间价，得到人民币对美元、欧元、港币的汇率，形
 * 成excel文件输出。汇率数据找相关的数据源，自己爬数据分析
 */
public class CountExangeRate {
    private static WritableWorkbook workbook = null;
    private static WritableSheet writableSheet = null;

    /**
     * 执行统计任务
     * @param pageParam 提供要爬取页面的参数: Url,User-Agent,Cookie,data域,timeout...
     * @param targetParam 提供要求爬取的字段:
     */
    public static void execute(Map pageParam,Map targetParam){
        try {
            Document document = Jsoup.connect(pageParam.get("url").toString())
                    .data((HashMap)pageParam.get("data"))
                    .userAgent(pageParam.get("userAgent").toString())
                    .cookies((HashMap)pageParam.get("cookies"))
                    .timeout((Integer)pageParam.get("timeout"))
                    .post();
            //
            Element table = document.getElementById("InfoTable");
            Element tbody = table.child(0);
            Element first = tbody.children().first();
            // System.out.println(e);
            List<Integer> list = new LinkedList();
            Map<String,String> map = (HashMap)targetParam.get("result");
            System.out.println(map);
            int id = 0;
            for (Element index : first.children()){
                for (String key : map.keySet()){
                    if (index.text().contains(map.get(key))){
                        list.add(id);
                    }
                }
                id++;
            }
            List<String> data = new ArrayList<String>();
            int count = 0;
            for (Element element : tbody.children() ){
                for (Integer i : list){
                    data.add(element.child(i).text());
                }
                render2Excel(data,count);
                count++;
                data.clear();
            }

            workbook.write();
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*protected static boolean render(){
        return render2Excel();
    }*/

    protected static void render2Excel(List<String> data,int row){
        createExcel("");
        try {
            for (int i = 0; i < data.size();i++){
                System.out.println(i + "---" + row + "----" + data.get(i));
                Label label = new Label(i,row,data.get(i));
                writableSheet.addCell(label);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*dcl实现*/
    protected static void createExcel(String path){
        path = "E:/demo.xls";
        if(workbook == null){
            synchronized (CountExangeRate.class){
                if (workbook == null){
                    try {
                        workbook = Workbook.createWorkbook(new File(path));
                        if (writableSheet == null){
                            writableSheet = workbook.createSheet("default",0);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }




}
