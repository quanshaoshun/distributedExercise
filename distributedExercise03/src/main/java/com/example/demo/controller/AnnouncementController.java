package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Announcement;
import com.example.demo.entity.Contactperson;
import com.example.demo.service.IAnnouncementService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class AnnouncementController {
    @Resource
    IAnnouncementService iAnnouncementService;

    @RequestMapping("/annQuery.do")
    public String swsQuery(Model model){
        model.addAttribute("annList", iAnnouncementService.list());
        return "annQuery";
    }
    @RequestMapping("/annAdd.do")
    public String annAdd(Announcement ann, Model model){
        iAnnouncementService.save(ann);
        model.addAttribute("text","增加成功");
        model.addAttribute("url","3,url=annQuery.do");
        return "error";
    }

    @RequestMapping("/annDelete.do")
    public String swsDelete(int id, Model model){
        iAnnouncementService.removeById(id);
        model.addAttribute("text","删除成功");
        model.addAttribute("url","3,url=annQuery.do");
        return "error";
    }

    @RequestMapping("/annEdit.do")
    public String edit(Announcement ann, Model mode){
        iAnnouncementService.updateById(ann);
        mode.addAttribute("text","修改成功");
        mode.addAttribute("url","3,url=annQuery.do");
        return"error";
    }

    @RequestMapping("/annEdit1.do")
    public String edit1(int id,Model mode){
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Announcement::getId,id);

        mode.addAttribute("announcement",iAnnouncementService.getOne(queryWrapper));
        return"announcement_edit";
    }
}
