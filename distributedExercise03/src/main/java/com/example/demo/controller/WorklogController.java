package com.example.demo.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Schedule;
import com.example.demo.entity.Worklog;
import com.example.demo.service.IWorklogService;
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
public class WorklogController {
    @Resource
    IWorklogService iWorklogService;

    @RequestMapping("/worklogQuery.do")
    public String worklogQuery(Model model){
        model.addAttribute("worklogList", iWorklogService.list());
        return "worklog";
    }

    @RequestMapping("/worklogAdd.do")
    public String worklogAdd(Worklog worklog,Model mode){
        iWorklogService.save(worklog);
        mode.addAttribute("text","增加成功");
        mode.addAttribute("url","3,url=worklogQuery.do");
        return "error";
    }
    @RequestMapping("/worklogDelete.do")
    public String worklogDelete(int id,Model model){
        iWorklogService.removeById(id);
        model.addAttribute("text", "删除成功");
        model.addAttribute("url", "3,url=worklogQuery.do");
        return "error";
    }
    @RequestMapping("/worklogEdit.do")
    public String edit(Worklog worklog, Model mode){
        iWorklogService.updateById(worklog);
        mode.addAttribute("text","修改成功");
        mode.addAttribute("url","3,url=worklogQuery.do");
        return"error";
    }

    @RequestMapping("/worklogEdit1.do")
    public String worklogEdit1(int id,Model mode){
        QueryWrapper<Worklog> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Worklog::getId,id);

        mode.addAttribute("worklog",iWorklogService.getOne(queryWrapper));
        return"worklog_edit";
    }
}
