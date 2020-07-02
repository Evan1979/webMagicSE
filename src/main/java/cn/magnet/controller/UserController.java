package cn.magnet.controller;

import cn.magnet.pojo.User;
import cn.magnet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
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
    public String getChangeResult(Long id, String name, String password,String email, String phone){
        String result = "true";

        User u = new User();
        u.setuId(id);
        u.setuName(name);
        u.setuPassword(password);
        u.setuEmail(email);
        u.setuPhone(phone);

        int changeRows = this.userService.save(u);
        if (changeRows == 0){
            result = "false";
        }
        return result;

    }


    @RequestMapping(value = "verifyUser",method = RequestMethod.POST)
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

    @RequestMapping(value = "verifyRegisterEmail",method = RequestMethod.POST)
    public String verifyRegisterEmail(String email){
        String result = "false";
        User u = new User();
        u.setuEmail(email);
        User checkedUser = this.userService.findUserByEmail(u);
        if (checkedUser != null){
            result = "true";
        }
        return result;
    }

    @RequestMapping(value = "registerUser",method = RequestMethod.POST)
    public String registerUser(String name, String password,String phone, String email, HttpSession session){

        String result = "true";
        User u = new User();
        // u.setuId(0L);
        u.setuName(name);
        u.setuPassword(password);
        u.setuEmail(email);
        u.setuPhone(phone);
        u.setuRank(0);
        u.setuBirthday(new Date());
        u.setuCreateDate(new Date());
        int changeRows = this.userService.addUser(u);

        if (changeRows == 0){
            result = "false";
        }else {
            session.setAttribute("userSession",u);
        }
        return result;

    }

}
