package com.psfd.springboot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.psfd.springboot.entity.Operator;
import com.psfd.springboot.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Controller
public class OperatorController {
    @Autowired
    private OperatorService operatorService;

    //查询所有员工返回列表页面
    @GetMapping("/operatorEmps")
    public String list(Model model){
        List<Operator> operatorList = operatorService.list();
        //将数据放在请求域中
        if (operatorList.size()>0) {
            model.addAttribute("emps",operatorList);
        }
        //thymeleaf默认就会拼串
        //classpath:/templates/**.html
        return "operatorEmp/operatorList";
    }
    /**
     * 分页查询全部数据
     * @Author Sans
     * @CreateTime 2019/6/8 16:37
     * @Return IPage<UserInfoEntity> 分页数据
     */
    @RequestMapping("/getInfoListPage")
    public IPage<Operator> getInfoListPage(){
        //需要在Config配置类中配置分页插件
        IPage<Operator> page = new Page<Operator>();
        page.setCurrent(2); //当前页
        page.setSize(5);    //每页条数
        page = operatorService.page(page);
        return page;
    }
    //来到员工添加页面
    @GetMapping("/operatorEmp")
    public String toAddPage(){
        //来到添加页面
        return "operatorEmp/operatorAdd";
    }
    //员工添加
    //springMVC自动将请求参数和入参对象的属性进行一一绑定,
    //要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/operatorEmp")
    public String addEmp(Operator operator){
        System.out.println("提交的操作员信息"+operator);
        operator.setOpCreatetime(new Date());
        boolean save = operatorService.save(operator);
        System.out.println("是否成功:"+save);
        //redirect:表示重定向到一个地址 /代表当前项目路径
        //forward:表示转发到一个地址

        return "redirect:/operatorEmps";
    }
    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/operatorEmp/{opUsername}")
    public String toEditPage(@PathVariable("opUsername") String opUsername, Model model){
        Operator operator = operatorService.getById(opUsername);
        model.addAttribute("emp",operator);
        //回到修改页面
        return "operatorEmp/operatorUpdate";
        //回到修改页面(add是一个修改添加二合一的页面)
        //return "emp/add";
    }

    //员工修改
    @PutMapping("/operatorEmp")
    public String updateEmployee(Operator operator){
        System.out.println("修改的员工数据："+operator);
        operator.setOpCreatetime(new Date());
        operatorService.updateById(operator);
        return "redirect:/operatorEmps";
    }

    //员工删除
    @DeleteMapping("/operatorEmp/{opUsername}") //需要开启spring.mvc.hiddenmethod.filter.enabled=true 不然页面405
    //@PostMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("opUsername") String opUsername){
        System.out.println("要删除的员工用户名:"+opUsername);
        operatorService.removeById(opUsername);
        return "redirect:/operatorEmps";
    }
}
