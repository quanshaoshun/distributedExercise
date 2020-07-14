package com.psfd.springboot.controller;


import com.psfd.springboot.entity.Room;
import com.psfd.springboot.entity.Roomcatalog;
import com.psfd.springboot.service.RoomService;
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
public class RoomController {
    @Autowired
    private RoomService roomService;


    //查询所有客房信息返回列表页面
    @GetMapping("/roomEmps")
    public String list(Model model){
        List<Room> list = roomService.list();
        System.out.println("一共有"+list.size()+"数据");
        if (list.size()>0) {
            model.addAttribute("emps",list);
        }
        //thymeleaf默认就会拼串
        //classpath:/templates/**.html
        return "roomEmp/roomList";
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
    @GetMapping("/roomEmp")
    public String toAddPage(){
        //来到添加页面
        return "roomEmp/roomAdd";
    }
    //添加
    //springMVC自动将请求参数和入参对象的属性进行一一绑定,
    //要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/roomEmp")
    public String addEmp(Room room,Model model){
        System.out.println("提交的客房信息:"+room);

        boolean save = roomService.save(room);
        System.out.println("是否成功:"+save);
        //返回到查看员工信息页面
        List<Room> list = roomService.list();
        //将数据放在请求域中
        model.addAttribute("emps",list);
        //redirect:表示重定向到一个地址 /代表当前项目路径
        //forward:表示转发到一个地址

        return "redirect:/roomEmps";
    }
    //来到修改页面，查出当前数据，在页面回显
    @GetMapping("/roomEmp/{rmId}")
    public String toEditPage(@PathVariable("rmId") Integer rmId, Model model){
        Room room = roomService.getById(rmId);
        model.addAttribute("emp",room);
        //回到修改页面
        return "roomEmp/roomUpdate";

    }

    //修改
    @PutMapping("/roomEmp")
    public String updateEmployee(Room room){
        System.out.println("修改的客房数据："+room);
        roomService.updateById(room);
        return "redirect:/roomEmps";
    }

    //删除
    @DeleteMapping("/roomEmp/{rmId}") //需要开启spring.mvc.hiddenmethod.filter.enabled=true 不然页面405
    public String deleteEmployee(@PathVariable("rmId") Integer rcId){
        System.out.println("要删除的客房编号:"+rcId);
        roomService.removeById(rcId);
        return "redirect:/roomEmps";
    }
}
