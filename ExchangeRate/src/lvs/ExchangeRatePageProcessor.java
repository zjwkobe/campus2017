package lvs;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lvs on 2017-1-11.
 */
public class ExchangeRatePageProcessor implements PageProcessor {

    private Site site = Site.me();
    @Override
    public void process(Page page) {
        List<ExchangeRateBean> list = new ArrayList<>();
        Selectable  row = page.getHtml().xpath("//table//div//table//tr");
        for (int i = 1; i < row.nodes().size(); i++) {
            String date = row.nodes().get(i).xpath("//td//div/text()").get();
            List<String> rate = row.nodes().get(i).xpath("//td/text()").all();
            list.add(new ExchangeRateBean(date, rate.get(1), rate.get(2), rate.get(4)));
        }
        page.putField("ExchangeRate",list);
    }

    @Override
    public Site getSite() {
        return site;
    }
}