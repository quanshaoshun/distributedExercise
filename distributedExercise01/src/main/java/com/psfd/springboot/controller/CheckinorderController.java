package com.psfd.springboot.controller;


import com.psfd.springboot.entity.Checkinitem;
import com.psfd.springboot.entity.Checkinorder;
import com.psfd.springboot.entity.Guest;
import com.psfd.springboot.entity.Room;
import com.psfd.springboot.service.CheckinitemService;
import com.psfd.springboot.service.CheckinorderService;
import com.psfd.springboot.service.GuestService;
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
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Controller

public class CheckinorderController {
    //    private Integer cioId;  入住登记编号  private List<Checkinitem> checkinitems; //入住登记信息列表
//    private String cioGuestname; 客人名称   private Integer cioMannumber; 人数
//    private String cioGuestcatalog;客人类别  private String cioGuesttype; 客人类型
//    private String cioGroupname;  团队名称    private String cioGuestcardcatalog; 客人证件类别
//    private String cioGuestcardid; 客人证件号码  private String cioCause;  事由
//    private String cioState;  登记状态    private Date cioIndatetime;  客人入住时间
//    private Date cioPreoutdatetime; 预计离开时间   private Date cioPrctoutdatetime; 实际离开时间
//    private String cioPaymentmodel;  支付类型   private Double cioPaidmoney;  已付押金
//    private String cioIsreservid;  有无预定     private String cioOperator;  操作员
//    private String cioGuestgender;  客人性别    private Double cioTotalrate; 总费用
//    private Double cioBedrate;  加床费       private String cioOrderid; 登记登记单号
    @Autowired
    private CheckinorderService checkinorderService;

    @Autowired
    private RoomService roomService;

    //查询所有返回列表页面
    @GetMapping("/checkinorderEmps")
    public String list(Model model) {
        List<Checkinorder> list = checkinorderService.list();
        if (list.size() > 0) {
            model.addAttribute("emps", list);
        }
        //thymeleaf默认就会拼串
        //classpath:/templates/**.html
        return "checkinorderEmp/checkinorderList";
    }

    //来到添加页面
    @GetMapping("/checkinorderEmp")
    public String toAddPage() {
        //来到添加页面
        return "checkinorderEmp/checkinorderAdd";
    }

    //添加
    //springMVC自动将请求参数和入参对象的属性进行一一绑定,
    //要求了请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/checkinorderEmp")
    public String addEmp(Checkinorder checkinorder) {
        System.out.println("提交的入住登记订单信息:" + checkinorder);
        checkinorder.setCioIndatetime(new Date());
        getMoney(checkinorder);

        System.out.println("需要支付金额为:"+checkinorder.getCioTotalrate());

        boolean save = checkinorderService.save(checkinorder);
        System.out.println("是否成功:" + save);
        Checkinitem checkinitem = new Checkinitem();
        //redirect:表示重定向到一个地址 /代表当前项目路径
        //forward:表示转发到一个地址
        return "redirect:/checkinorderEmps";
    }

    //来到修改页面，查出当前数据，在页面回显
    @GetMapping("/checkinorderEmp/{gtId}")
    public String toEditPage(@PathVariable("gtId") Integer gtId, Model model) {
        Checkinorder checkinorder = checkinorderService.getById(gtId);
        model.addAttribute("emp", checkinorder);
        //回到修改页面
        return "checkinorderEmp/checkinorderUpdate";

    }

    //修改
    @PutMapping("/checkinorderEmp")
    public String updateEmployee(Checkinorder checkinorder) {
        System.out.println("修改的客房数据：" + checkinorder);

        getMoney(checkinorder);

        System.out.println("需要支付金额为:"+checkinorder.getCioTotalrate());
        checkinorderService.updateById(checkinorder);
        return "redirect:/checkinorderEmps";
    }

    private void getMoney(Checkinorder checkinorder) {
        Room room = roomService.getById(checkinorder.getCioRmId());

        double money = room.getRmPrctdiscount() * room.getRmPrctdiscount();
        System.out.println("客房一天价格:" + money);

        // 获取相差的天数
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkinorder.getCioIndatetime());
        long timeInMillis1 = calendar.getTimeInMillis();
        calendar.setTime(checkinorder.getCioPreoutdatetime());
        long timeInMillis2 = calendar.getTimeInMillis();
        long betweenDays = (timeInMillis2 - timeInMillis1) / (1000L * 3600L * 24L);
        System.out.println("天数:"+betweenDays);
        //  总费用
        //获取总金额
        checkinorder.setCioTotalrate(money * betweenDays + checkinorder.getCioBedrate());
    }

    //删除
    @DeleteMapping("/checkinorderEmp/{cioId}") //需要开启spring.mvc.hiddenmethod.filter.enabled=true 不然页面405
    public String deleteEmployee(@PathVariable("cioId") Integer cioId) {
        System.out.println("要删除的编号:" + cioId);
        checkinorderService.removeById(cioId);
        return "redirect:/checkinorderEmps";
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
