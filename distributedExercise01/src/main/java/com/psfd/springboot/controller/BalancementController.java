package com.psfd.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.psfd.springboot.entity.*;
import com.psfd.springboot.service.*;
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

public class BalancementController {
    @Autowired
    private BalancementService balancementService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private CheckinorderService checkinorderService;

    @Autowired
    private CheckinitemService checkinitemService;
    @Autowired
    private RoomService roomService;

//    private Integer bmId; 结账信息编号 private Integer bmCheckinorderId; 入住登记订单编号
//    private Integer bmGuestId; 客人编号 private String bmType; 结账类型
//    private Double bmTotalrate; 应收金额 private Double bmPaidmoney; 已付押金
//    private Double bmReceivmoney;续收金额  private Date bmCreatetime;创建时间
//    private String bmPaymentmodel;支付类型    private String bmRemark;结账说明
//    private String bmOperator;操作员
    //查询所有返回列表页面
    @GetMapping("/balancementEmps")
    public String list(Model model) {
        List<Balancement> list = balancementService.list();
        if (list.size() > 0) {
            model.addAttribute("emps", list);
        }
        //thymeleaf默认就会拼串
        //classpath:/templates/**.html
        return "balancementEmp/balancementList";
    }

    //来到添加页面
    @GetMapping("/balancementEmp/{cimId}")
    public String toAddPage(Model model,@PathVariable("cimId")Integer cimId,Map<String, Object>map) {
        System.out.println("要结账的订单编号:"+cimId);

        Checkinorder checkinorder = checkinorderService.getById(cimId);

        System.out.println("要结账的订单详细信息:"+checkinorder);
        //更新入住订单表
        checkinorder.setCioPrctoutdatetime(new Date());
        checkinorder.setCioState("已结算");

        boolean update = checkinorderService.updateById(checkinorder);
        System.out.println("入住订单表更新是否成功:"+update);


        Balancement balancement = new Balancement();
        balancement.setBmCheckinorderId(checkinorder.getCioId());

        //更新入住登记表
        map.put("checkinorder_cio_id", checkinorder.getCioId());
        List<Checkinitem> checkinitems = checkinitemService.listByMap(map);
        System.out.println("要更新的入住登记表"+checkinitems.get(0));

        checkinitems.get(0).setCimOutdatetime(new Date());
        checkinitems.get(0).setCimState("已结算");

        boolean save1 = checkinitemService.updateById(checkinitems.get(0));
        System.out.println("是否成功:"+save1);

        //获取客人的id
        map.remove("checkinorder_cio_id");
        map.put("gt_name", checkinorder.getCioGuestname());
        List<Guest> guests = guestService.listByMap(map);

        System.out.println("客人的详细信息"+guests.get(0));
        balancement.setBmGuestId(guests.get(0).getGtId());

        double money = checkinitems.get(0).getCimPrctprice() * checkinitems.get(0).getCimDiscount();
        System.out.println("客房一天价格:" + money);

        // 获取相差的天数
        //获取入住时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkinorder.getCioIndatetime());
        long timeInMillis1 = calendar.getTimeInMillis();

        //获取当前离开时间
        calendar.setTime(new Date());
        long timeInMillis2 = calendar.getTimeInMillis();
        long betweenDays = (timeInMillis2 - timeInMillis1) / (1000L * 3600L * 24L);
        betweenDays++;
        System.out.println("天数:"+betweenDays);

        //应收费用
        balancement.setBmTotalrate(money*betweenDays);
        System.out.println("应收:"+balancement.getBmTotalrate());

        //已付押金
        balancement.setBmPaidmoney(checkinorder.getCioPaidmoney());
        //续收金额
        balancement.setBmReceivmoney(balancement.getBmTotalrate()-balancement.getBmPaidmoney());
        model.addAttribute("emp",balancement);
        return "balancementEmp/balancementAdd";
    }

    //添加
    //springMVC自动将请求参数和入参对象的属性进行一一绑定,
    //要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/balancementEmp")
    public String addEmp(Balancement balancement) {
            balancement.setBmCreatetime(new Date());
        System.out.println("添加:"+balancement);
        boolean save = balancementService.save(balancement);
        System.out.println(save);

        //redirect:表示重定向到一个地址 /代表当前项目路径
        //forward:表示转发到一个地址
        return "redirect:/balancementEmps";
    }

    //删除
    @DeleteMapping("/balancementEmp/{bmId}") //需要开启spring.mvc.hiddenmethod.filter.enabled=true 不然页面405
    public String deleteEmployee(@PathVariable("bmId") Integer bmId) {
        System.out.println("要删除的入住登记信息编号:" + bmId);
        balancementService.removeById(bmId);
        return "redirect:/balancementEmps";
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
