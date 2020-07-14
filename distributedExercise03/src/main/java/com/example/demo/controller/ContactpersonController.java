package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Contactperson;
import com.example.demo.service.IContactpersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Controller
public class ContactpersonController {
    @Resource
    IContactpersonService iContactpersonService;

    @RequestMapping("/query.do")
    public String query(Model model, HttpServletRequest request) {
        QueryWrapper<Contactperson> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Contactperson::getUsername, request.getSession().getAttribute("userName"));

        model.addAttribute("ContactpersonList", iContactpersonService.list(queryWrapper));
        return "contactperson_query";
    }

    @RequestMapping("/conadd.do")
    public String conadd(Contactperson contactperson, Model model, HttpServletRequest request) {
        contactperson.setUsername("" + request.getSession().getAttribute("userName"));
        iContactpersonService.save(contactperson);
        model.addAttribute("text", "增加成功");
        model.addAttribute("url", "3,url=query.do");
        return "error";
    }

    @RequestMapping("/delete.do")
    public String delete(int id, Model mode) {
        iContactpersonService.removeById(id);
        mode.addAttribute("text", "删除成功");
        mode.addAttribute("url", "3,url=query.do");
        return "error";
    }
    @RequestMapping("/edit.do")
    public String edit(Contactperson contactperson,Model mode){
    iContactpersonService.updateById(contactperson);
    mode.addAttribute("text","修改成功");
    mode.addAttribute("url","3,url=query.do");
    return"error";
    }

    @RequestMapping("/edit1.do")
    public String edit1(int id,Model mode){
        QueryWrapper<Contactperson> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Contactperson::getId,id);

        mode.addAttribute("contactperson",iContactpersonService.getOne(queryWrapper));
        return"contactperson_edit";
    }
}
