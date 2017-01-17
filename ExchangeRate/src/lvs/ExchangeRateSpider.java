package lvs;

import us.codecraft.webmagic.Spider;

import java.io.File;
import java.util.Scanner;

/**
 * Created by lvs on 2017-1-11.
 */
public class ExchangeRateSpider {

    public static void main(String[] args) {

        System.out.println("请输入爬取结果文件的输出目录：");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        if(!path.endsWith(System.getProperties().getProperty("file.separator"))) {
            path = path + System.getProperties().getProperty("file.separator");
        }
        File file = new File(path);
        if(!file.exists()) {
            System.err.println("输出目录不存在");
            System.exit(1);
        }

        Spider.create(new ExchangeRatePageProcessor()).addUrl("http://www.chinamoney.com.cn/fe-c/historyParity.do")
                .addPipeline(new WriteToExcelPipeline(path)).run();
    }
}
