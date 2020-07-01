package cn.magnet.task;

/**
 * 2020/6/16 15:47
 *
 * @author Evan Ma
 * @since
 **/
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TxtParseUtils {

    private static final Integer ONE = 1;
    private List<String> list = new LinkedList<String>();
    public String[] getCrawlerItems(String host,String crawlerItems){


        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            //"C:\\Files\\crawlerItems.txt"
                            new FileInputStream(new File(crawlerItems)),"UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                list.add(host+lineTxt);
                //数据以逗号分隔
                // System.out.println(lineTxt);
                // String[] names = lineTxt.split(",");
                // for (String name : names) {
                //     if (map.keySet().contains(name)) {
                //         map.put(name, (map.get(name) + ONE));
                //     } else {
                //         map.put(name, ONE);
                //     }
                // }
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }

        String[] items = new String[list.size()];
        int i = 0;
        for (String item : list) {
            items[i] = item;
            i++;
        }

        return items;
    }


    // public static void main(String[] args) {
    //     Map<String, Integer> map = new HashMap<String, Integer>();
    //
    //     /* 读取数据 */
    //     try {
    //         BufferedReader br = new BufferedReader(
    //                 new InputStreamReader(
    //                         new FileInputStream(new File("F:\\Desktop\\crawlerItems.txt")),"UTF-8"));
    //         String lineTxt = null;
    //         while ((lineTxt = br.readLine()) != null) {
    //             //数据以逗号分隔
    //             System.out.println(lineTxt);
    //             // String[] names = lineTxt.split(",");
    //             // for (String name : names) {
    //             //     if (map.keySet().contains(name)) {
    //             //         map.put(name, (map.get(name) + ONE));
    //             //     } else {
    //             //         map.put(name, ONE);
    //             //     }
    //             // }
    //         }
    //         br.close();
    //     } catch (Exception e) {
    //         System.err.println("read errors :" + e);
    //     }
    //
    //     // /* 输出数据 */
    //     // try {
    //     //     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("E:/value_map.txt")),
    //     //             "UTF-8"));
    //     //
    //     //     for (String name : map.keySet()) {
    //     //         bw.write(name + " " + map.get(name));
    //     //         bw.newLine();
    //     //     }
    //     //     bw.close();
    //     // } catch (Exception e) {
    //     //     System.err.println("write errors :" + e);
    //     // }
    // }


}
// @ComponentScan({"cn.magnet"})
// @PropertySource(value = {"classpath:config/crawlerparams.properties"},
//         ignoreResourceNotFound = false, encoding = "GBK", name = "crawlerparams.properties")
// class GetFilePath{
//     @Value("${file.crawlerItemsPath}")
//     String path = "";
//
//     public String getPath() {
//         System.out.println(path);
//         return path;
//     }
// }