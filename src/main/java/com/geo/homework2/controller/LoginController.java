package com.geo.homework2.controller;

import com.geo.homework2.pojo.MyUser;
import com.geo.homework2.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class LoginController {

    @Autowired
    private MyUserService myUserService;

    @RequestMapping("/goToIndex")
    public void goToIndex(MyUser myUser,Model model){
        login(myUser,model);
    }

    @RequestMapping("/")
    public String loginpath(Model model) {
        MyUser myUser = new MyUser();
        model.addAttribute("myUser", myUser);
        return "index";
    }

    @PostMapping("/login")
    public String login(MyUser myUser, Model model) {
        MyUser e = myUserService.login(myUser);
        //判断：登录用户是否存在
        if (e != null) {
            findAllUsers(model);
            model.addAttribute("loginUser", e);
            return "showAllUser";
        } else {
            model.addAttribute("msg", "用户名或密码错误！");
            return "error";
        }
    }

    public void findAllUsers(Model model) {
        List<MyUser> userlist = myUserService.findAll();
        model.addAttribute("userlist", userlist);
        model.addAttribute("usercond", new MyUser());
        model.addAttribute("title", "所有的用户");
    }


    @RequestMapping("/update")
    public String updateUser(MyUser myUser, Model model) {
        int res = myUserService.updateUser(myUser);
        if (res > 0) {
            findAllUsers(model);
            return "showAllUser";
        }
        model.addAttribute("msg", "用户信息更新不成功！");
        return "error";
    }

    @RequestMapping("/finduser")
    public String findUserById(int id, Model model) {
        MyUser myUser = myUserService.findUserById(id);
        model.addAttribute("updateuser", myUser);
        model.addAttribute("title", "修改用户信息");
        return "showAuser";
    }

    @RequestMapping("/addpath")
    public String addUserPage(@ModelAttribute("adduser") MyUser myUser, Model model) {
        model.addAttribute("title", "添加用户");
        return "addUser";
    }

    @RequestMapping("/adduser")
    public String addUser(MyUser myUser, Model model) {
        int res = myUserService.saveUser(myUser);
        if (res > 0) {
            findAllUsers(model);
            return "showAllUser";
        }
        model.addAttribute("msg", "添加不成功！");
        return "error";
    }

    @RequestMapping("/delete")
    public String deleteUser(int id, Model model) {
        int res = myUserService.deleteUser(id);
        if (res > 0) {
            findAllUsers(model);
            return "showAllUser";
        }
        model.addAttribute("msg", "删除不成功！");
        return "error";
    }

    @RequestMapping("/query")
    public String queryUsersByCondition(MyUser myUser, Model model) {
        List<MyUser> users = myUserService.findUsersByCondtion(myUser);
        if (users != null) {
            model.addAttribute("userlist", users);
            model.addAttribute("title", "查询结果");
            model.addAttribute("usercond", myUser);
            return "showAllUser";
        } else {
            model.addAttribute("msg", "未查到用户！");
        }
        return "error";
    }

}
