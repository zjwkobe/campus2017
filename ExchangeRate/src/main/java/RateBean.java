/**
 * Created by yeluo on 1/17/17.
 */
public class RateBean {
    String date;
    String rate;
    String exchange;

    public RateBean(String date, String rate, String exchange) {
        this.date = date;
        this.rate = rate;
        this.exchange = exchange;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
}
