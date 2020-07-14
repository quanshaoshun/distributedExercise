package com.psfd.springboot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.psfd.springboot.entity.Roomcatalog;
import com.psfd.springboot.service.RoomcatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
public class RoomcatalogController {
    @Autowired
    private RoomcatalogService  roomcatalogService;

    //查询所有返回列表页面
    @GetMapping("/roomcatalogEmps")
    public String list(Model model){
        List<Roomcatalog> list = roomcatalogService.list();
        if (list.size()>0) {
            model.addAttribute("emps",list);
        }
        //thymeleaf默认就会拼串
        //classpath:/templates/**.html
        return "roomcatalogEmp/roomcatalogList";
    }
    /**
     * 分页查询全部数据
     * @Author Sans
     * @CreateTime 2019/6/8 16:37
     * @Return IPage<UserInfoEntity> 分页数据
     */
//    @RequestMapping("/getInfoListPage")
//    public IPage<Roomcatalog> getInfoListPage(){
//        //需要在Config配置类中配置分页插件
//        IPage<Roomcatalog> page = new Page<Roomcatalog>();
//        page.setCurrent(2); //当前页
//        page.setSize(5);    //每页条数
//        page = roomcatalogService.page(page);
//        return page;
//    }
    //来到添加页面
    @GetMapping("/roomcatalogEmp")
    public String toAddPage(){
        //来到添加页面
        return "roomcatalogEmp/roomcatalogAdd";
    }
    //添加
    //springMVC自动将请求参数和入参对象的属性进行一一绑定,
    //要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/roomcatalogEmp")
    public String addEmp(Roomcatalog roomcatalog,Model model){
        System.out.println("提交的客房类型信息"+roomcatalog);

        boolean save = roomcatalogService.save(roomcatalog);
        System.out.println("是否成功:"+save);
        //返回到查看员工信息页面
        List<Roomcatalog> list = roomcatalogService.list();
        //将数据放在请求域中
        model.addAttribute("emps",list);
        //redirect:表示重定向到一个地址 /代表当前项目路径
        //forward:表示转发到一个地址

        return "redirect:/roomcatalogEmps";
    }
    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/roomcatalogEmp/{rcId}")
    public String toEditPage(@PathVariable("rcId") Integer rcId, Model model){
        Roomcatalog roomcatalog = roomcatalogService.getById(rcId);
        model.addAttribute("emp",roomcatalog);
        //回到修改页面
        return "roomcatalogEmp/roomcatalogUpdate";

    }

    //修改
    @PutMapping("/roomcatalogEmp")
    public String updateEmployee(Roomcatalog roomcatalog){
        System.out.println("修改的客房类型数据："+roomcatalog);

        roomcatalogService.updateById(roomcatalog);
        return "redirect:/roomcatalogEmps";
    }

    //删除
    @DeleteMapping("/roomcatalogEmp/{rcId}") //需要开启spring.mvc.hiddenmethod.filter.enabled=true 不然页面405
    public String deleteEmployee(@PathVariable("rcId") Integer rcId){
        System.out.println("要删除的客房类型编号:"+rcId);
        roomcatalogService.removeById(rcId);
        return "redirect:/roomcatalogEmps";
    }
}
