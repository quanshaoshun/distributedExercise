package com.psfd.springboot.controller;


import com.psfd.springboot.entity.Checkinitem;
import com.psfd.springboot.entity.Checkinorder;
import com.psfd.springboot.entity.Room;
import com.psfd.springboot.service.CheckinitemService;
import com.psfd.springboot.service.CheckinorderService;
import com.psfd.springboot.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
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
public class CheckinitemController {

    @Autowired
    private CheckinorderService checkinorderService;

    @Autowired
    private CheckinitemService checkinitemService;

    @Autowired
    private RoomService roomService;
    //private Integer cimId;入住登记信息编号 private Integer checkinorderCioId;入住登记订单
    //private Integer roomRmId;  房间编号  private Double cimPrctprice;实际价格
    // private Double cimDiscount; 折扣   private Date cimIndatetime;入住时间
    // private Date cimOutdatetime; 离开时间   private String cimState;登记状态
    //查询所有返回列表页面
    @GetMapping("/checkinitemEmps")
    public String list(Model model) {
        List<Checkinitem> list = checkinitemService.list();
        if (list.size() > 0) {
            model.addAttribute("emps", list);
        }
        //thymeleaf默认就会拼串
        //classpath:/templates/**.html
        return "checkinitemEmp/checkinitemList";
    }

    //来到添加页面
    @GetMapping("/checkinitemEmp")
    public String toAddPage(Model model) {
        //来到添加页面
        List<Checkinorder> list = checkinorderService.list();
        if (list.size() > 0) {
            model.addAttribute("emps", list);
        }
        return "checkinitemEmp/checkinitemAdd";
    }

    //添加
    //springMVC自动将请求参数和入参对象的属性进行一一绑定,
    //要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/checkinitemEmp")
    public String addEmp(Checkinitem checkinitem) {
        System.out.println("提交的入住登记订单信息:" + checkinitem);
        Checkinorder checkinorder = checkinorderService.getById(checkinitem.getCheckinorderCioId());
        Room room = roomService.getById(checkinorder.getCioRmId());
        checkinitem.setRoomRmId(room.getRmId());
        checkinitem.setCimPrctprice(room.getRmPrctprice());
        checkinitem.setCimDiscount(room.getRmPrctdiscount());
        checkinitem.setCimIndatetime(checkinorder.getCioIndatetime());
        checkinitem.setCimOutdatetime(checkinorder.getCioPrctoutdatetime());
        checkinitem.setCimState(checkinorder.getCioState());
        boolean save = checkinitemService.save(checkinitem);
        System.out.println("是否成功:"+save);
        //redirect:表示重定向到一个地址 /代表当前项目路径
        //forward:表示转发到一个地址
        return "redirect:/checkinitemEmps";
    }

//    //来到修改页面，查出当前数据，在页面回显
//    @GetMapping("/checkinitemEmp/{cimId}")
//    public String toEditPage(@PathVariable("cimId") Integer cimId, Model model) {
//        List<Checkinorder> list = checkinorderService.list();
//        if (list.size() > 0) {
//            model.addAttribute("emps", list);
//        }
//        Checkinitem checkinitem = checkinitemService.getById(cimId);
//        model.addAttribute("emp", checkinitem);
//        //回到修改页面
//        return "checkinitemEmp/checkinitemUpdate";
//
//    }
//
//    //修改
//    @PutMapping("/checkinitemEmp")
//    public String updateEmployee(Checkinitem checkinitem) {
//        System.out.println("修改的入住登记信息数据：" + checkinitem);
//        checkinitemService.updateById(checkinitem);
//        return "redirect:/checkinitemEmps";
//    }


    //删除
    @DeleteMapping("/checkinitemEmp/{cimId}") //需要开启spring.mvc.hiddenmethod.filter.enabled=true 不然页面405
    public String deleteEmployee(@PathVariable("cimId") Integer cimId) {
        System.out.println("要删除的入住登记信息编号:" + cimId);
        checkinitemService.removeById(cimId);
        return "redirect:/checkinitemEmps";
    }
    /**
     * 分页查询全部数据
     *
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
}
