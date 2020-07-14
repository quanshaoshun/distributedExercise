package com.psfd.springboot.controller;

import com.psfd.springboot.entity.Operator;
import com.psfd.springboot.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author quanshaoshun
 * @date 2020/7/9 - 20:10
 */
@Controller
public class LoginController {
    @Autowired
    private OperatorService operatorService;

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        System.out.println("用户名：" + username + "\t" + "密码：" + password);
        map.put("op_username", username);
        map.put("op_password", password);
        List<Operator> operatorList = operatorService.listByMap(map);
        System.out.println("数据数：" + operatorList.size());
        if (operatorList.size() > 0) {
            session.setAttribute("loginUser", username);
            session.setAttribute("opPrivilege", operatorList.get(0).getOpPrivilege());
            return "redirect:/roomEmps";
        }else {
            map.put("msg","用户名或者密码错误");
            return "login.html";
        }
    }
    //    @Autowired
//    private UserInfoService userInfoService;
//
//    /**
//     * MP扩展演示
//     * @Author Sans
//     * @CreateTime 2019/6/8 16:37
//     * @Return Map<String,Object> 返回数据
//     */
//    @RequestMapping("/getInfoListPlus")
//    public Map<String,Object> getInfoListPage(){
//        //初始化返回类
//        Map<String,Object> result = new HashMap<>();
//        //查询年龄等于18岁的学生
//        //等价SQL: SELECT id,name,age,skill,evaluate,fraction FROM user_info WHERE age = 18
//        QueryWrapper<UserInfoEntity> queryWrapper1 = new QueryWrapper<>();
//        queryWrapper1.lambda().eq(UserInfoEntity::getAge,18);
//        List<UserInfoEntity> userInfoEntityList1 = userInfoService.list(queryWrapper1);
//        result.put("studentAge18",userInfoEntityList1);
//        //查询年龄大于5岁的学生且小于等于18岁的学生
//        //等价SQL: SELECT id,name,age,skill,evaluate,fraction FROM user_info WHERE age > 5 AND age <= 18
//        QueryWrapper<UserInfoEntity> queryWrapper2 = new QueryWrapper<>();
//        queryWrapper2.lambda().gt(UserInfoEntity::getAge,5);
//        queryWrapper2.lambda().le(UserInfoEntity::getAge,18);
//        List<UserInfoEntity> userInfoEntityList2 = userInfoService.list(queryWrapper2);
//        result.put("studentAge5",userInfoEntityList2);
//        //模糊查询技能字段带有"画"的数据,并按照年龄降序
//        //等价SQL: SELECT id,name,age,skill,evaluate,fraction FROM user_info WHERE skill LIKE '%画%' ORDER BY age DESC
//        QueryWrapper<UserInfoEntity> queryWrapper3 = new QueryWrapper<>();
//        queryWrapper3.lambda().like(UserInfoEntity::getSkill,"画");
//        queryWrapper3.lambda().orderByDesc(UserInfoEntity::getAge);
//        List<UserInfoEntity> userInfoEntityList3 = userInfoService.list(queryWrapper3);
//        result.put("studentAgeSkill",userInfoEntityList3);
//        //模糊查询名字带有"小"或者年龄大于18的学生
//        //等价SQL: SELECT id,name,age,skill,evaluate,fraction FROM user_info WHERE name LIKE '%小%' OR age > 18
//        QueryWrapper<UserInfoEntity> queryWrapper4 = new QueryWrapper<>();
//        queryWrapper4.lambda().like(UserInfoEntity::getName,"小");
//        queryWrapper4.lambda().or().gt(UserInfoEntity::getAge,18);
//        List<UserInfoEntity> userInfoEntityList4 = userInfoService.list(queryWrapper4);
//        result.put("studentOr",userInfoEntityList4);
//        //查询评价不为null的学生,并且分页
//        //等价SQL: SELECT id,name,age,skill,evaluate,fraction FROM user_info WHERE evaluate IS NOT NULL LIMIT 0,5
//        IPage<UserInfoEntity> page = new Page<>();
//        page.setCurrent(1);
//        page.setSize(5);
//        QueryWrapper<UserInfoEntity> queryWrapper5 = new QueryWrapper<>();
//        queryWrapper5.lambda().isNotNull(UserInfoEntity::getEvaluate);
//        page = userInfoService.page(page,queryWrapper5);
//        result.put("studentPage",page);
//        return result;
//    }
//}
//
//六.自定义SQL
//        引入Mybatis-Plus不会对项目现有的 Mybatis 构架产生任何影响，而且Mybatis-Plus支持所有 Mybatis 原生的特性,这也是我喜欢使用它的原因之一,由于某些业务复杂,我们可能要自己去写一些比较复杂的SQL语句,我们举一个简单的例子来演示自定义SQL.
//
//        示例:查询大于设置分数的学生(分数为动态输入,且有分页)
//
//        编写Mapper.xml文件
//
//<mapper namespace="com.mp.demo.dao.UserInfoDao">
//<!-- Sans 2019/6/9 14:35 -->
//<select id="selectUserInfoByGtFraction" resultType="com.mp.demo.entity.UserInfoEntity" parameterType="long">
//        SELECT * FROM user_info WHERE fraction > #{fraction}
//</select>
//</mapper>
//
//        在DAO中加入方法
//
///**
// * 查询大于该分数的学生
// * @Author Sans
// * @CreateTime 2019/6/9 14:28
// * @Param  page  分页参数
// * @Param  fraction  分数
// * @Return IPage<UserInfoEntity> 分页数据
// */
//        IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page, Long fraction);
//
//        在service加入方法
//
///**
// * 查询大于该分数的学生
// * @Author Sans
// * @CreateTime 2019/6/9 14:27
// * @Param  page  分页参数
// * @Param  fraction  分数
// * @Return IPage<UserInfoEntity> 分页数据
// */
//        IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page,Long fraction);
//
//        在serviceImpl加入方法
//
///**
// * 查询大于该分数的学生
// * @Author Sans
// * @CreateTime 2019/6/9 14:27
// * @Param  page  分页参数
// * @Param  fraction  分数
// * @Return IPage<UserInfoEntity> 分页数据
// */
//@Override
//public IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page, Long fraction) {
//        return this.baseMapper.selectUserInfoByGtFraction(page,fraction);
//        }
//
//        在Controller中测试
//
///**
// * MP自定义SQL
// * @Author Sans
// * @CreateTime 2019/6/9 14:37
// * @Return IPage<UserInfoEntity> 分页数据
// */
//@RequestMapping("/getInfoListSQL")
//public IPage<UserInfoEntity> getInfoListSQL(){
//        //查询大于60分以上的学生,并且分页
//        IPage<UserInfoEntity> page=new Page<>();
//        page.setCurrent(1);
//        page.setSize(5);
//        page=userInfoService.selectUserInfoByGtFraction(page,60L);
//        return page;
//        }
}
