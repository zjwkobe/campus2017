package com.qunar.exchangerate;

import com.qunar.fresh.util.ExcelUtil;

public class ExchangeRateExporter {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRateExporter.class);

    private static final String ROOT_URL = "http://www.pbc.gov.cn";
    private static final String LIST_PAGE_URL = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index%s.html";

    private static final String LIST_PAGE_PATTERN = "<td height=\"22\" align=\"left\"><font class=\"newslist_style\" style=\"margin-right:10px;\"><a href=\"([/0-9a-z]+\\.html)\" onclick=\"void\\(0\\)\" target=\"_blank\" title=\".+\">(\\d+)年(\\d+)月(\\d+)日中国外汇交易中心受权公布人民币汇率中间价公告</a></font><span class=\"hui12\">[\\-0-9]+</span></td>";
    private static final String DETAIL_PAGE_PATTERN = "<p>中国人民银行授权中国外汇交易中心公布，(\\d+)年(\\d+)月(\\d+)日银行间外汇市场人民币汇率中间价为：1美元对人民币([\\.0-9]+)元，1欧元对人民币([\\.0-9]+)元，100日元对人民币([\\.0-9]+)元，1港元对人民币([\\.0-9]+)元，1英镑对人民币([\\.0-9]+)元，1澳大利亚元对人民币([\\.0-9]+)元，1新西兰元对人民币([\\.0-9]+)元，1新加坡元对人民币([\\.0-9]+)元，1瑞士法郎对人民币([\\.0-9]+)元，1加拿大元对人民币([\\.0-9]+)元，人民币1元对([\\.0-9]+)林吉特，人民币1元对([\\.0-9]+)俄罗斯卢布。</p>";
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final List<String> EXCEL_TITLE = Lists.newArrayList("日期", "美元汇率", "欧元汇率", "港元汇率");

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public boolean export(String targetFile, final LocalDate beginDate, final LocalDate endDate) {
        Preconditions.checkArgument(StringUtils.isNoneBlank(targetFile), "输出文件路径不能为空");
        Preconditions.checkNotNull(beginDate, "起始时间不能为空");
        Preconditions.checkNotNull(endDate, "结束时间不能为空");
        Preconditions.checkArgument(beginDate.compareTo(endDate) <= 0, "结束时间不能小于开始时间");

        try {
            ExcelUtil.writeToCsv(targetFile, EXCEL_TITLE, new Iterator<List<String>>() {
                private int currentIndex = 1;
                private LocalDate currentDate = endDate;
                private TreeMap<LocalDate, String> dateUrlMap;

                public boolean hasNext() {
                    if (dateUrlMap == null) {
                        try {
                            dateUrlMap = parseListPage(currentIndex);
                        } catch (IOException e) {
                            logger.error("解析第{}页失败", currentIndex, e);
                            return false;
                        }
                    }

                    if (currentDate.isBefore(beginDate)) {
                        // 已经导出所有，结束
                        return false;
                    }

                    if (currentDate.isAfter(dateUrlMap.firstKey())) {
                        // 比网站上最晚的日期还要晚，无法获取到，结束
                        return false;
                    }

                    if (currentDate.isBefore(dateUrlMap.lastKey())) {
                        // 比网页上最早的时间还早，需要翻页
                        currentIndex++;
                        dateUrlMap = null;
                        return hasNext();
                    }

                    return MapUtils.isNotEmpty(dateUrlMap);
                }

                public List<String> next() {
                    try {
                        String url = dateUrlMap.get(currentDate);
                        if (StringUtils.isEmpty(url)) {
                            return Collections.emptyList();
                        }

                        return parseDetailPage(url);
                    } catch (Exception e) {
                        logger.error("解析详情页失败，date:{}, url:{}", currentDate.toString(DATE_PATTERN), StringUtils.defaultString(dateUrlMap.get(currentDate), "无"), e);
                        return Collections.emptyList();
                    } finally {
                        currentDate = currentDate.minusDays(1);
                    }
                }

                public void remove() {
                    throw new UnsupportedOperationException();
                }
            });
        } catch (IOException e) {
            logger.error("文件操作发生异常, targetFile:{}, beginDate:{}, endDate:{}", targetFile, beginDate, endDate, e);
            return false;
        }
        return true;
    }


    private TreeMap<LocalDate, String> parseListPage(Integer pageIndex) throws IOException {
        final TreeMap<LocalDate, String> dateUrlMap = Maps.newTreeMap(new Comparator<LocalDate>() {
            public int compare(LocalDate o1, LocalDate o2) {
                // 按时间逆序排列
                return o2.compareTo(o1);
            }
        });

        Closer closer = Closer.create();
        try {
            URL url = new URL(String.format(LIST_PAGE_URL, pageIndex));
            InputStream inputStream = url.openStream();
            BufferedReader bufferedReader = closer.register(new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8.name())));

            Pattern pattern = Pattern.compile(LIST_PAGE_PATTERN);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line.trim());
                if (matcher.matches()) {
                    LocalDate date = new LocalDate(Ints.tryParse(matcher.group(2)), Ints.tryParse(matcher.group(3)), Ints.tryParse(matcher.group(4)));
                    dateUrlMap.put(date, ROOT_URL + matcher.group(1));
                }
            }
        } finally {
            closer.close();
        }
        return dateUrlMap;
    }


    private List<String> parseDetailPage(String url) throws IOException {
        List<String> detail = Lists.newArrayList();

        Closer closer = Closer.create();
        try {
            URL detailUrl = new URL(url);
            InputStream inputStream = detailUrl.openStream();
            BufferedReader bufferedReader = closer.register(new BufferedReader(new InputStreamReader(inputStream, Charsets.UTF_8.name())));

            Pattern pattern = Pattern.compile(DETAIL_PAGE_PATTERN);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line.trim());
                if (matcher.matches()) {
                    // 日期
                    detail.add(new LocalDate(Ints.tryParse(matcher.group(1)), Ints.tryParse(matcher.group(2)), Ints.tryParse(matcher.group(3))).toString(DATE_PATTERN));

                    // 美元
                    detail.add(new BigDecimal(matcher.group(4)).multiply(ONE_HUNDRED).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

                    // 欧元
                    detail.add(new BigDecimal(matcher.group(5)).multiply(ONE_HUNDRED).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

                    // 港元
                    detail.add(new BigDecimal(matcher.group(7)).multiply(ONE_HUNDRED).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
                }
            }
        } finally {
            closer.close();
        }
        return detail;
    }
}
