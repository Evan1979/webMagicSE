package cn.magnet.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 2020/6/22 10:33
 *
 * @author Evan Ma
 * @since
 *
 *  @ConfigurationProperties(prefix = "文件名")
 *  prefix属性  该注解有一个prefix属性，通过指定的前缀，绑定配置文件中的配置，该注解可以放在类上，也可以放在方法上
 *  crawlerparams.properties
 * 可以将外部配置文件（比如applicaition.properties）加载进来，填充对象的对应字段的数据，然后供其他Bean使用。
 *
 * @PropertySource与@Value
 * 这两个注解配合使用获取相应的配置文件中的键值
 **/

@Component
@ConfigurationProperties(prefix = "crawlerparams")
@PropertySource(value = {"classpath:config/crawlerparams.properties"},
        ignoreResourceNotFound = false, encoding = "GBK", name = "crawlerparams.properties")
public class CrawlerParameters {
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
    private String cron;
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getThreadNums() {
        return threadNums;
    }

    public void setThreadNums(int threadNums) {
        this.threadNums = threadNums;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getSetCharset() {
        return setCharset;
    }

    public void setSetCharset(String setCharset) {
        this.setCharset = setCharset;
    }

    public int getSetTimeOut() {
        return setTimeOut;
    }

    public void setSetTimeOut(int setTimeOut) {
        this.setTimeOut = setTimeOut;
    }

    public int getSetRetryTimes() {
        return setRetryTimes;
    }

    public void setSetRetryTimes(int setRetryTimes) {
        this.setRetryTimes = setRetryTimes;
    }

    public String getSetCycleRetryTimes() {
        return setCycleRetryTimes;
    }

    public void setSetCycleRetryTimes(String setCycleRetryTimes) {
        this.setCycleRetryTimes = setCycleRetryTimes;
    }

    public String getSetHttpProxy() {
        return setHttpProxy;
    }

    public void setSetHttpProxy(String setHttpProxy) {
        this.setHttpProxy = setHttpProxy;
    }
}