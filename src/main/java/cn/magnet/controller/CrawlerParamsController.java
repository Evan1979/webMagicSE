package cn.magnet.controller;

/**
 * 2020/6/22 1:13
 *
 * @author Evan Ma
 * @since
 **/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
@EnableConfigurationProperties
public class CrawlerParamsController {
    @Autowired
    CrawlerParameters crawlerParameters;

    @RequestMapping(value = "getCrawlerParams",method = RequestMethod.POST)
    public CrawlerParameters getCrawlerParams() throws IOException, URISyntaxException {

        Properties properties = readCrawlerParameter();
        for (String key : properties.stringPropertyNames()) {
            //crawlerParams.cron:0 0 22 ? * 2,3,4,5,6
            // site.setHttpProxy:222.95.21.71:11862
            // crawlerParams.threadNums:10
            // site.setCycleRetryTimes:3
            // site.setCharset:utf-8
            // crawlerParams.host:http://www.torkitty.com/search/
            // site.setTimeOut:3000
            // site.setRetryTimes:3
            if (key == "crawlerParams.cron"){
                crawlerParameters.setCron(properties.getProperty(key));
            }else if (key == "site.setHttpProxy"){
                crawlerParameters.setSetHttpProxy(properties.getProperty(key));
            }else if (key == "crawlerParams.threadNums"){
                crawlerParameters.setThreadNums(Integer.parseInt(properties.getProperty(key)));
            }else if (key == "crawlerParams.host"){
                crawlerParameters.setHost(properties.getProperty(key));
            }else if (key == "site.setCycleRetryTimes"){
                crawlerParameters.setSetCycleRetryTimes(properties.getProperty(key));
            }else if (key == "site.setCharset"){
                crawlerParameters.setSetCharset(properties.getProperty(key));
            }else if (key == "site.setTimeOut"){
                crawlerParameters.setSetTimeOut(Integer.parseInt(properties.getProperty(key)));
            }else if (key == "site.setRetryTimes"){
                crawlerParameters.setSetRetryTimes(Integer.parseInt(properties.getProperty(key)));
            }

            // System.out.println(key + ":" +properties.getProperty(key));
        }

        return crawlerParameters;
    }

    @RequestMapping(value = "changeCrawlerParams",method = RequestMethod.POST)
    public String changeCrawlerParams(String host, String threadNums, String cron,
                                      String setCharset, String setTimeOut,
                                      String setCycleRetryTimes, String setHttpProxy){
        String result = "false";
        /**
         * 传递键值对的Map，更新properties文件
         * @param fileName 文件名(放在resource源包目录下)，需要后缀
         * @param keyValueMap 键值对Map
         */
        String fileName = "crawlerparams.properties";
        String filePath = "";
        //获取文件路径

        try {
            filePath = this.getClass().getClassLoader().getResource("config/"+fileName).toURI().getPath();
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println("propertiesPath:" + filePath);
        Properties props = new Properties();
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            // 从输入流中读取属性列表（键和元素对）
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            props.load(br);
            br.close();

            // 写入属性文件
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            // 清空旧的文件
            // props.clear();

            for (String key : props.stringPropertyNames()) {

                switch (key){
                    case "crawlerParams.cron":
                        props.setProperty(key, cron);
                        break;
                    case "site.setHttpProxy":
                        props.setProperty(key, setHttpProxy);
                        break;
                    case "crawlerParams.threadNums":
                        props.setProperty(key, threadNums);
                        break;
                    case "crawlerParams.host":
                        props.setProperty(key, host);
                        break;
                    case "site.setCycleRetryTimes":
                        props.setProperty(key, setCycleRetryTimes);
                        break;
                    case "site.setCharset":
                        props.setProperty(key, setCharset);
                        break;
                    case "site.setTimeOut":
                        props.setProperty(key, setTimeOut);
                        break;
                    // case "site.setRetryTimes":
                    //     props.setProperty(key, setRetryTimes);
                    //     break;

                    default:break;
                }

                // if (key == "crawlerParams.cron"){
                //     props.setProperty(key, "update");
                //
                // }else if (key == "site.setHttpProxy"){
                //     props.setProperty(key, "update");
                //
                // }else if (key == "crawlerParams.threadNums"){
                //     props.setProperty(key, "update");
                //
                // }else if (key == "crawlerParams.host"){
                //     props.setProperty(key, "update");
                //
                // }else if (key == "site.setCycleRetryTimes"){
                //     props.setProperty(key, "update");
                //
                // }else if (key == "site.setCharset"){
                //     props.setProperty(key, "update");
                //
                // }else if (key == "site.setTimeOut"){
                //     props.setProperty(key, "update");
                // }else if (key == "site.setRetryTimes"){
                //     props.setProperty(key, "update");
                // }

                // System.out.println("key=="+props.getProperty(key));
            }

            props.store(bw, "改变数据");
            bw.close();
            result = "true";
        } catch (IOException e) {
            result = "false";
            e.printStackTrace();
            System.err.println("Visit " + filePath + " for updating " + "" + " value error");
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    /**
     * 获取配置Properties对象
     * @return
     * @throws IOException
     */
    public Properties readCrawlerParameter() throws IOException, URISyntaxException {
        /**
         * 传递键值对的Map，更新properties文件
         * @param fileName 文件名(放在resource源包目录下)，需要后缀
         * @param keyValueMap 键值对Map
         */
        String fileName = "crawlerparams.properties";
        Map<String, String> keyValueMap = new HashMap<>();
        //获取文件路径
        String filePath = this.getClass().getClassLoader().getResource("config/"+fileName).toURI().getPath();
        System.out.println("propertiesPath:" + filePath);
        Properties props = new Properties();
        BufferedReader br = null;
        // 从输入流中读取属性列表（键和元素对）
        br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        props.load(br);
        br.close();
        return props;
    }

}
