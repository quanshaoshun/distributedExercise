package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.demo.entity.User;
import com.example.demo.service.IUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Resource
    IUserService iUserService;

    @RequestMapping("/login.do")
    public String login(User user, Model mode, HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, user.getUsername());
        queryWrapper.lambda().eq(User::getPassword, user.getPassword());
        User one = iUserService.getOne(queryWrapper);
        if (one == null) {
            mode.addAttribute("text", "用户名或密码错误");
            mode.addAttribute("url", "3,url=login.html");
            return "error";
        }

        HttpSession session = request.getSession();
        session.setAttribute("userName", one.getUsername());
        return "home";
    }

    @RequestMapping("login1.do")
    public String login1() {
        return "login1";
    }

    @RequestMapping("add.do")
    public String add(User user, Model mode) {
        if (user.getPassword() == null || user.getUsername() == null) {
            mode.addAttribute("text", "用户名密码不能为空");
            mode.addAttribute("url", "3;url=login.html");
            return "error";
        }
        boolean save = iUserService.save(user);
        if (save == true) {
            mode.addAttribute("text", "增加成功");
            mode.addAttribute("url", "3;url=login.html");
            return "error";
        }
        mode.addAttribute("text", "增加失败");
        mode.addAttribute("url", "3;url=login.html");

        return "error";
    }

}
