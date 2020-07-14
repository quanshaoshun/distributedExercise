package com.psfd.springboot.controller;


import com.psfd.springboot.entity.Guest;
import com.psfd.springboot.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Controller
public class GuestController {
//    private Integer gtId;   客人编号   private String gtName;客人姓名
//    private String gtYpe;   客人类型   private String gtCadcatalog;客人证件类别
//    private String gtCardId; 客人证件号码      private String gtCountry;国籍
//private String gtAddress; 地址     private String gtZip;邮编
//    private String gtCompany;  公司    private String gtTelphone;固定电话
//    private String gtMobile;   手机号码    private String gtGender;性别
//    private String gtEmail; email地址       private Date gtCreatetime;资料创建日期
    @Autowired
    private GuestService guestService;


    //查询所有员工返回列表页面
    @GetMapping("/guestEmps")
    public String list(Model model){
        List<Guest> list = guestService.list();
        if (list.size()>0) {
            model.addAttribute("emps",list);
        }
        //thymeleaf默认就会拼串
        //classpath:/templates/**.html
        return "guestEmp/guestList";
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
    //来到登记客户页面 @PathVariable这个注解是配合URL上携带的数据的
    @GetMapping("/guest/{rmId}")
    public String toAddPage(@PathVariable("rmId") Integer rmId,Model model){
        System.out.println("要登记客房的编号:"+rmId);
        model.addAttribute("rmId",rmId);
        //来到登记客户页面
        return "guestEmp/add";
    }
    //判断该客户是否已有资料
    @PostMapping("/guest")
    public String add(String gtName,Integer rmId,Model model,Map<String, Object> map){
        System.out.println("提交的客户姓名:"+gtName);
        if ("".equals(gtName)){
            gtName=null;
        }
        map.put("gt_name", gtName);
        List<Guest> guests = guestService.listByMap(map);
        if (guests.size()>0) {
            Guest guest =guests.get(0);
            model.addAttribute("rmId", rmId);
            System.out.println("客户的详细资料"+guest);
            model.addAttribute("emp", guest);
            //来到订单登记页面
            return "checkinorderEmp/checkinorderAdd";
        }else {
            //来到客户资料添加页面
            //将数据放在请求域中
            model.addAttribute("rmId", rmId);
            model.addAttribute("emp", gtName);
            //redirect:表示重定向到一个地址 /代表当前项目路径
            //forward:表示转发到一个地址
            return "guestEmp/guestAdd";
        }
    }
    //添加客户
    //springMVC自动将请求参数和入参对象的属性进行一一绑定,
    //要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/guestEmp")
    public String addEmp(Guest guest,Integer rmId,Model model){
        System.out.println("提交的客人信息:"+guest);
        guest.setGtCreatetime(new Date());
        boolean save = guestService.save(guest);
        System.out.println("是否成功:"+save);
        System.out.println("房间ID:"+rmId);
        if (rmId>0){
            model.addAttribute("rmId",rmId);
            model.addAttribute("emp",guest);
            return "checkinorderEmp/checkinorderAdd";
        }
        //返回到查看员工信息页面
        List<Guest> list = guestService.list();
        //将数据放在请求域中
        model.addAttribute("emps",list);
        //redirect:表示重定向到一个地址 /代表当前项目路径
        //forward:表示转发到一个地址
        return "redirect:/guestEmps";
    }
    //来到修改页面，查出当前数据，在页面回显
    @GetMapping("/guestEmp/{gtId}")
    public String toEditPage(@PathVariable("gtId") Integer gtId, Model model){
        Guest guest = guestService.getById(gtId);
        model.addAttribute("emp",guest);
        //回到修改页面
        return "guestEmp/guestUpdate";

    }

    //修改
    @PutMapping("/guestEmp")
    public String updateEmployee(Guest guest){
        System.out.println("修改的客房数据："+guest);
        guest.setGtCreatetime(new Date());
        guestService.updateById(guest);
        return "redirect:/guestEmps";
    }

    //删除
    @DeleteMapping("/guestEmp/{gtId}") //需要开启spring.mvc.hiddenmethod.filter.enabled=true 不然页面405
    public String deleteEmployee(@PathVariable("gtId") Integer gtId){
        System.out.println("要删除的客房编号:"+gtId);
        guestService.removeById(gtId);
        return "redirect:/guestEmps";
    }
}
