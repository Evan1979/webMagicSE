package cn.magnet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 2020/6/15 13:02
 * spring boot 引导类
 *
 * @author Evan Ma
 * @since
 **/
@SpringBootApplication
@EnableScheduling   //开启定时任务
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
