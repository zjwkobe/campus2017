import java.io.IOException;

/**
 * Created by yeluo on 1/17/17.
 */
public class Main {
    private static final String path = "/home/yeluo/Qunar/campus2017/ExchangeRate/ExchangeRate.xls";
    public static void main(String[] args) throws Exception {
        ExchangeRate er = new ExchangeRate();
        // 依次抓取美元欧元港币数据
        try {
            er.crawlRateData("usd");
            er.crawlRateData("eur");
            er.crawlRateData("hkd");
        } catch (Exception e) {
            System.out.print("抓取数据失败！");
            e.printStackTrace();
        }

        // 写入xls
        try {
            er.outputAsXls(path);
        } catch (IOException e) {
            System.out.println("写入XLS失败！");
            e.printStackTrace();
        }

    }
}
