package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Schedule;
import com.example.demo.service.IScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;

@Controller
public class ScheduleController {

    @Resource
    IScheduleService iScheduleService;

    @RequestMapping("/scherduleQuery.do")
    public String scherduleQuery(Model model){
        model.addAttribute("scheduleList", iScheduleService.list());
    return "schedule";
    }

    @RequestMapping("/scherduleAdd.do")
    public String scherduleAdd(Schedule schedule, Model model){
        iScheduleService.save(schedule);
        model.addAttribute("text", "增加成功");
        model.addAttribute("url", "3,url=scherduleQuery.do");
        return "error";
    }
    @RequestMapping("/scheduleDelete.do")
    public String scheduleDelete(int id,Model model){
        iScheduleService.removeById(id);
        model.addAttribute("text", "删除成功");
        model.addAttribute("url", "3,url=scherduleQuery.do");
        return "error";
    }
    @RequestMapping("/scherduleEdit.do")
    public String edit(Schedule schedule, Model mode){
        iScheduleService.updateById(schedule);
        mode.addAttribute("text","修改成功");
        mode.addAttribute("url","3,url=scherduleQuery.do");
        return"error";
    }

    @RequestMapping("/scheduleEdit1.do")
    public String scherduleEdit1(int id,Model mode){
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Schedule::getId,id);

        mode.addAttribute("scherdule",iScheduleService.getOne(queryWrapper));
        return"scherdule_edit";
    }

}
