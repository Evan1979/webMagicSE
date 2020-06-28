package cn.magnet.controller;

import cn.magnet.pojo.MagnetLink;
import cn.magnet.pojo.MagnetLinkField;
import cn.magnet.pojo.User;
import cn.magnet.service.MagnetLinkRepositoryService;
import cn.magnet.service.MagnetLinkService;
import cn.magnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 2020/6/19 15:20
 *
 * @author Evan Ma
 * @since
 **/
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "getUsers",method = RequestMethod.POST)
    public Page<User> getUsers(String state){

        Page<User> userResult = null;
        if ("*".equals(state)){
            userResult  =  this.userService.findUserByPage(1,100);
        }
        return userResult;
    }

    @RequestMapping(value = "delUserInfo",method = RequestMethod.POST)
    public String getDeleteResult(String state,Long id){
        String result = "true";
        User u = new User();
        u.setuId(id);
        this.userService.delete(u);

        List<User> user = this.userService.findUser(u);
        if (user.size() != 0) {
            result = "false";
        }
        return result;
    }

    @RequestMapping(value = "changeUserInfo",method = RequestMethod.POST)
    public String getChangeResult(Long id, String name, String email, String phone){
        String result = "true";

        User u = new User();
        u.setuId(id);
        u.setuName(name);
        u.setuEmail(email);
        u.setuPhone(phone);

        int changeRows = this.userService.save(u);
        if (changeRows == 0){
            result = "false";
        }
        return result;

    }


    @RequestMapping(value = "verifyUser",method = RequestMethod.POST)
    // public String verifyUser(Long id, String name, String email, String phone){
    public String verifyLoginUser(String name, String password, HttpSession session){

        String result = "true";

        User u = new User();
        u.setuName(name);
        u.setuPassword(password);
        User checkedUser = this.userService.findUserByNameAndPassword(u);

         // System.out.println(checkedUser);

         if (checkedUser == null){
             result = "false";
         }else {
             session.setAttribute("userSession",checkedUser);
         }

        return result;

    }

}
