/**
 * simhasher
 * 2020/5/13 14:31
 *
 * @author Evan Ma
 * @since
 **/
package cn.magnet.task;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

@Component
public class ProxyTest implements PageProcessor {

    // @Scheduled(fixedDelay = 1000)
    public void Process(){
        //create Downloader
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //set Proxy Info
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("123.207.43.128",1080)));

        Spider.create(new ProxyTest())
                .addUrl("http://ip.chinaz.com")
                .setDownloader(httpClientDownloader)
                .run();
    }

    @Override
    public void process(Page page) {
        String ip = page.getHtml().css("div#rightinfo dd.fz24").toString();
        System.out.println("ip: " + ip);
    }

    private Site site = Site.me();
    @Override
    public Site getSite() {
        return site;
    }
}
