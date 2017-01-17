package lvs;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by lvs on 2017-1-11.
 */
public class WriteToExcelPipeline implements Pipeline{

    private String path = null;

    public WriteToExcelPipeline() {
    }

    public WriteToExcelPipeline(String path) {
        this.path = path;
    }

    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        System.out.println("日期      美元/人民币  欧元/人民币  港元/人民币");
        List<ExchangeRateBean> list = resultItems.get("ExchangeRate");
        for (ExchangeRateBean rate : list) {
            System.out.println(rate.getDate() + "\t" + rate.getUsdRate() + "\t" + rate.getEurRate() + "\t" + rate.getHkdRate());
        }

        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet hSheet = hwb.createSheet("人民币汇率中间价");
        HSSFRow title = hSheet.createRow(0);

        title.createCell(0).setCellValue("日期");
        title.createCell(1).setCellValue("美元/人民币");
        title.createCell(2).setCellValue("欧元/人民币");
        title.createCell(3).setCellValue("港元/人民币");

        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = hSheet.createRow(i+1);
            row.createCell(0).setCellValue(list.get(i).getDate());
            row.createCell(1).setCellValue(list.get(i).getUsdRate());
            row.createCell(2).setCellValue(list.get(i).getEurRate());
            row.createCell(3).setCellValue(list.get(i).getHkdRate());
        }

        hSheet.setColumnWidth(0, 12*256);
        hSheet.setColumnWidth(1, 12*256);
        hSheet.setColumnWidth(2, 12*256);
        hSheet.setColumnWidth(3, 12*256);

        try {
            FileOutputStream fos = new FileOutputStream(path + "ExchangeRate.xls");
            hwb.write(fos);
            fos.close();
            System.out.println("---已将爬取结果输出到" + path + "ExchangeRate.xls---");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
