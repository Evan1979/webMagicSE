package cn.magnet.task;

import cn.magnet.pojo.MagnetLink;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2020/6/15 13:55
 *
 * 定时任务爬取磁力
 *
 * @author Evan Ma
 * @since
 **/
@Component
@ConfigurationProperties(prefix = "test")
@ComponentScan({"cn.magnet"})
@PropertySource(value = {"classpath:config/crawlerparams.properties"},
        ignoreResourceNotFound = false, encoding = "GBK", name = "crawlerparams.properties")
public class MagnetLinkProcessor implements PageProcessor {
    //
    // @Autowired
    // private MagnetLinkService magnetLinkService;

    Set<String> setLinks = new HashSet<String>();
    private int iexistLink = 1;
    private Spider spider;

    /**
     * 定时爬取的来源网站
     */
    @Value("${crawlerParams.host}")
    private String host;
    /**
     *  爬虫线程数
     */
    @Value("${crawlerParams.threadNums}")
    private int threadNums;
    /**
     * 定时任务 表达式cron = "0 0 22 ? * 2,3,4,5,6"
     */
    @Value("${crawlerParams.cron}")
    private String cronStr;
    /**
     * Site配置
     *   setCharset(String)	设置编码
     */
    @Value("${site.setCharset}")
    private String setCharset;
    /**
     * 设置超时时间  setTimeOut(int)  单位是毫秒
     */
    @Value("${site.setTimeOut}")
    private int setTimeOut;

    /**
     * setRetryTimes(int)	设置重试次数
     */
    @Value("${site.setRetryTimes}")
    private int setRetryTimes;

    /**
     * setCycleRetryTimes(int)	设置循环重试次数
     */
    @Value("${site.setCycleRetryTimes}")
    private String setCycleRetryTimes;

    /**
     * setHttpProxy(HttpHost)	设置Http代理
     */
    @Value("${site.setHttpProxy}")
    private String setHttpProxy;

    @Value("${file.crawlerItemsPath}")
    String crawlerItemsPath;


    @Override
    public void process(Page page) {
        String html = page.getHtml().toString();
        // System.out.println(html);


        //解析页面，获取单条磁力链接信息详情的url地址
        List<Selectable> list = page.getHtml().css("table#archiveResult tbody tr").nodes();

        //判断获取到的集合是否为空
        if (list.size() == 0) {
            // 如果为空，表示这是磁力链接搜索列表页,解析页面，获取单条磁力详情信息，保存数据
        //     int search = page.getHtml().toString().indexOf("减少关键词长度后重新搜索");
        //     if ( search ==-1){
                this.saveMagnetLink(page);
        //     }
        //
        } else {
            //如果不为空，表示这是列表页,解析出详情页的url地址，放到任务队列中
            for (Selectable selectable : list) {
                //获取url地址
                String magnetLinkUrl = selectable.links().toString();
                // System.out.println("magnetLinkUrl==="+magnetLinkUrl);
                //把获取到的url地址放到任务队列中
                page.addTargetRequest(magnetLinkUrl);
            }
        }

    }

    private void saveMagnetLink(Page page) {
        // System.out.println("进入到详情页面解析.......");
        //保存磁力链接
        // 创建磁力链接页面对象
        MagnetLink magnetLink = new MagnetLink();

        //解析页面
        Html html = page.getHtml();
        // System.out.println("saveMagnetLink\n\n" + html);
        String title = "";
        if (Jsoup.parse(html.css("h2").toString()).text() != null){
            title = Jsoup.parse(html.css("h2").toString()).text();
        }
        // Details for torrent: 肖申克的救赎(国英双音轨版).The.Shawshank.Redemption.1994.BD-1080p.X264.AAC.2AUDIO.CHS.ENG-99Mp4.mp4
        String magnetLinkKeyWords[] = title.split("torrent: ");

        String strMagnetLink = Jsoup.parse(html.css("textarea.magnet-link").toString()).text();

        // System.out.println(strMagnetLink);

        List<Selectable> nodes = html.css("table.detailSummary tr").nodes();
        String contentSize = Jsoup.parse(nodes.get(3).css("td").toString()).text();
        // String Number of Files = nodes.get(3).toString();
        // String Created On = nodes.get(3).toString();
        // String Download = nodes.get(3).toString();

        // System.out.println("123");

        //获取数据封装到对象中
        magnetLink.setMagnetLinkFileSize(contentSize);
        magnetLink.setCrawledDate(new Date());
        magnetLink.setFromWhichSe("torKitty");
        magnetLink.setMagnetLink(strMagnetLink);
        magnetLink.setMagnetDownloadTimes(0);
        magnetLink.setMagnetLinkSize(0f);
        magnetLink.setMagnetLinkKey(magnetLinkKeyWords[1]);
        //这里判断去重  ---然后决定是否要存入数据库
        // 敏感词过滤


        // 判断数据是否存在
        boolean existLink = setLinks.contains(strMagnetLink);

        if (!existLink){
            page.putField("magnetLink",magnetLink);
            setLinks.add(strMagnetLink);
        }else {
            iexistLink++;
            System.out.println(iexistLink+":"+strMagnetLink);
        }
    }

    /**
     * setTimeOut          超时时间
     * setRetrySleepTime   重试时间
     * setRetryTimes      重试次数
     */
    private Site site = Site.me()
            .setCharset(setCharset)
            .setTimeOut(setTimeOut)
            .setRetrySleepTime(3000)
            .setRetryTimes(setRetryTimes);
            // .setCharset("utf8")
            // .setTimeOut(10*1000)
            // .setRetrySleepTime(3000)
            // .setRetryTimes(1);
    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    private SpringDataPipeline springDataPipeline;



    /**
     * initialDelay  任务启动后隔多久执行方法
     * fixedDelay  隔多久执行一次
     */

    // private String url = "https://www.clttu.xyz/search/%E6%91%A9%E5%A4%A9_ctime_1.html";
    // private String url = "https://www.clttu.xyz/search/%E8%82%96%E7%94%B3%E5%85%8B%E7%9A%84%E6%95%91%E8%B5%8E_ctime_1.html";
    // private String url = "https://www.clttu.xyz/search/[%E5%89%A7%E6%83%85]%E8%82%96%E7%94%B3%E5%85%8B%E7%9A%84%E6%95%91%E8%B5%8E_ctime_1.html";

    // @Scheduled(initialDelay = 1000,fixedDelay = 10*1000)
    // @Scheduled(cron = "0 0 22 ? * 2,3,4,5,6")  //每周一到周五晚上8点执行
    @Scheduled(cron = "${crawlerParams.cron}")  //每周一到周五晚上8点执行
    public void process(){
        //
        // System.out.println("crawlerParams.cron==" + cronStr);
        // System.out.println("site.setCharset==" + setCharset);
        // System.out.println("site.setTimeOut==" + setTimeOut);
        // System.out.println("site.setHttpProxy==" + setHttpProxy);

        TxtParseUtils tpu = new TxtParseUtils();
        String host = "http://www.torkitty.com/search/";
        String[] urls = tpu.getCrawlerItems(host,crawlerItemsPath);

        // String urls[] = {"http://www.torkitty.com/search/这个杀手不太冷/","http://www.torkitty.com/search/肖申克的救赎/"};
        spider = Spider.create(new MagnetLinkProcessor())
                // .addUrl("https://btsow.fun/search/%E8%82%96%E7%94%B3%E5%85%8B%E7%9A%84%E6%95%91%E8%B5%8E")
                .addUrl(urls)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(1000)))
                // .setScheduler(new SpikeFileCacheQueueScheduler("f:\\Desktop\\cache"))
                .addPipeline(springDataPipeline)
                .thread(threadNums);
        runSpider();
    }

    public void runSpider(){
        spider.run();
    }
    public void stopSpider(){
        spider.run();
    }

}
