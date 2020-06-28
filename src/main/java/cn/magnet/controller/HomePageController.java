package cn.magnet.controller;

import cn.magnet.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 2020/6/28 10:27
 * 主页导向
 * @author Evan Ma
 * @since
 **/
@Controller
public class HomePageController {



    /**
     * 登录的时候进到主页面
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView requestMapping(HttpSession session) {
        ModelAndView mv = new ModelAndView("index");
        System.out.println(session.getAttribute("userSession"));
        User userMapper = (User)session.getAttribute("userSession");
        if(userMapper!=null) {
            mv.addObject("user", userMapper);
        }else {
             userMapper.setuName("please Login");
             userMapper.setuRank(-1);
        }
        //跳转到哪个页面
        mv.setViewName("search");
        return mv;
    }
}
