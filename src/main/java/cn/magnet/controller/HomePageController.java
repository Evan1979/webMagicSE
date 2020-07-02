package cn.magnet.controller;

import cn.magnet.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 2020/6/28 10:27
 * 主页导向
 * @author Evan Ma
 * @since
 **/
@RestController
public class HomePageController {

    /**
     * 登录的时候获取用户信息
     * @return
     */
    @RequestMapping("/getLoginInfo")
    public User requestMapping(HttpSession session) {
        User userMapper = null;
        // System.out.println(session.getAttribute("userSession"));
        if (session.getAttribute("userSession") != null){
            userMapper = (User) session.getAttribute("userSession");
        }else{
            userMapper = new User();
            userMapper.setuName("please Login");
            userMapper.setuRank(-1);
        }
        return userMapper;
    }
}
