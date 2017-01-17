package lvs;


/**
 * Created by lvs on 2017-1-11.
 */
public class ExchangeRateBean {

    private String date;
    private String usdRate;
    private String eurRate;
    private String hkdRate;

    public ExchangeRateBean() {
    }

    public ExchangeRateBean(String date, String usdRate, String eurRate, String hkdRate) {
        this.date = date;
        this.usdRate = usdRate;
        this.eurRate = eurRate;
        this.hkdRate = hkdRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(String usdRate) {
        this.usdRate = usdRate;
    }

    public String getEurRate() {
        return eurRate;
    }

    public void setEurRate(String eurRate) {
        this.eurRate = eurRate;
    }

    public String getHkdRate() {
        return hkdRate;
    }

    public void setHkdRate(String hkdRate) {
        this.hkdRate = hkdRate;
    }
}
