package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Sms;
import com.example.demo.entity.User;
import com.example.demo.service.ISmsService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Controller
public class SmsController {
    @Resource
    ISmsService iSmsService;

    @RequestMapping("/swsQuery.do")
    public String swsQuery(Model model){
        model.addAttribute("swsList", iSmsService.list());
        return "sws_query";
    }
    @RequestMapping("/swsEdit.do")
    public String swsEdit(int id,Model model){
        QueryWrapper<Sms> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Sms::getId,id);
        Sms one = iSmsService.getOne(queryWrapper);
        one.setIsRead(one.getIsRead()+1);
        iSmsService.updateById(one);
        model.addAttribute("url","1;url=swsQuery.do");
        return "error";
    }

    @RequestMapping("/swsAdd.do")
    public String swsAdd(Sms sms, Model model){
        iSmsService.save(sms);
        model.addAttribute("text","增加成功");
        model.addAttribute("url","3,url=swsQuery.do");
        return "error";
    }

    @RequestMapping("/swsDelete.do")
    public String swsDelete(int id, Model model){
        iSmsService.removeById(id);
        model.addAttribute("text","删除成功");
        model.addAttribute("url","3,url=swsQuery.do");
        return "error";
    }
}
