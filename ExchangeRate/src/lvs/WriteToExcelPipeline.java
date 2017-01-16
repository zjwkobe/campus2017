package lvs;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by lvs on 2017-1-11.
 */
public class WriteToExcelPipeline implements Pipeline{
    public WriteToExcelPipeline() {
    }

    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        System.out.println("日期      美元/人民币  欧元/人民币  港元/人民币");
        List<ExchangeRate> list = resultItems.get("ExchangeRate");
        for (ExchangeRate exchangeRate : list) {
            System.out.println(exchangeRate.getDate() + "\t" + exchangeRate.getUsdRate() + "\t" + exchangeRate.getEurRate() + "\t" + exchangeRate.getHkdRate());
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
            FileOutputStream fos = new FileOutputStream("D:\\ExchangeRate.xls");
            hwb.write(fos);
            fos.close();
            System.out.println("---已将爬取结果输出到D:\\ExchangeRate.xls---");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
