package com.mp.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.mp.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void select() {
        List<User> users = userMapper.selectList(null);
        Assertions.assertEquals(5,users.size());
        users.forEach(System.out::println);
    }

    @Test
    void insertTest() {
        /**
         * MybatisPlus 默认的插入规则是 如果对象属性为null，则不插入
         */
        User user = new User();
        user.setName("赵六六");
        user.setAge(20);
        user.setManagerId(1088248166370832385L);
        user.setCreateTime(LocalDateTime.now());
        user.setEmail("zs@qq.com");
        // user.setRemark("备注，不会被插入到数据库");
        int rows = userMapper.insert(user);
        log.info("user = {}",user);
        Assertions.assertEquals(1,rows);
        log.info("rows = {}",rows);
    }

    /**
     * 普通查询
     */

    // 根据id查询
    @Test
    void selectById() {
        User user = userMapper.selectById(1094590409767661570L);
        log.info("user = {}",user);
    }

    // 根据多个id查询 list
    @Test
    void selectIds() {
        List<Long> list = Arrays.asList(1087982257332887553L,
                1088248166370832385L,
                1088250446457389058L);

        List<User> users = userMapper.selectBatchIds(list);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    // 根据条件查询 map
    @Test
    void selectByMap() {
        HashMap<String, Object> columnMap = new HashMap<>();
        //columnMap.put("name","王天风");
        columnMap.put("age",20);  // 注意这里的属性是数据库中的属性名，不是实体中的属性名
        List<User> users = userMapper.selectByMap(columnMap);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * 1.名字中包含雨且年龄小于40的
     * name like '%雨%' and age < 40
     */
    @Test
    void selectByWrapper1() {
        // QueryWrapper<User> query = Wrappers.query();  // 多种创建QueryWrapper的方式
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","雨").lt("age",40);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * 2.名字中包含雨且年龄大于等于20且小于等于40且email不能为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }
    /**
     * 3.名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     * name like '王%' or age >= 25 order by age desc,id asc
     */
    @Test
    void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.likeRight("name", "王").or().ge("age", 25)
                .orderByDesc("age").orderByAsc("id");

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }
    /**
     * 4.创建日期为2019年2月14日并且直属上级为名字为王姓
     * date_format(create_time,'%Y-%m-%d')='2019-02-14' and manager_id in (select id from user where name like '王%')
     */
    @Test
    void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-02-14")
                .inSql("manager_id","select id from user where name like '王%'");

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * 5.名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * 6.名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     * name like '王%' or (age<40 and age>20 and email is not null)
     */
    @Test
    void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * 7.（年龄小于40或邮箱不为空）并且名字为王姓
     * (age<40 or email is not null) and name like '王%'
     */
    @Test
    void selectByWrapper7() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name","王");

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * 8.年龄为30、31、34、35
     * age in (30、31、34、35)
     */
    @Test
    void selectByWrapper8() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35));

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * 9.只返回满足条件的其中一条语句即可
     * limit 1
     */
    @Test
    void selectByWrapper9() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * 10.名字中包含雨且年龄小于40的
     * 指定返回字段
     * SELECT id,name FROM user WHERE name LIKE ? AND age < ?
     */
    @Test
    void selectByWrapper10() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name").like("name","雨").lt("age",40);


        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * 11.名字中包含雨且年龄小于40的
     * 指定非返回字段
     * SELECT id,name,age,email FROM user WHERE name LIKE ? AND age < ?
     */
    @Test
    void selectByWrapper11() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name").like("name","雨").lt("age",40)
                .select(User.class,info->!info.getColumn().equals("create_time")&&
                        !info.getColumn().equals("manager_id"));

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * condition作用
     */
    @Test
    void testCondition() {
        String name = "王";
        String email = "";
        condition(name,email);
    }

    private void condition(String name,String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 旧写法 繁琐
//        if (StringUtils.isNotEmpty(name)) {
//            queryWrapper.like("name", name);
//        }
//
//        if (StringUtils.isNotEmpty(email)) {
//            queryWrapper.like("email", email);
//        }
        // 新写法 简洁
        queryWrapper.like(StringUtils.isNotEmpty(name), "name", name)
                .like(StringUtils.isNotEmpty(email), "email", email);

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }

    }

    /**
     * 实体作为条件构造器方法的参数
     */
    @Test
    void selectByWrapperEntity() {
        /**
         * SELECT id,name,age,email,manager_id,create_time FROM user WHERE name=? AND age=?
         * 默认是等于号，可以在实体类加注解 改变符号
         * @TableField(condition = SqlCondition.LIKE)
         * @TableField(condition = "%s&lt;#{%s}")
         * SELECT id,name,age,email,manager_id,create_time FROM user WHERE name LIKE CONCAT('%',?,'%') AND age<?
         *
         */
        User whereUser = new User();
        whereUser.setName("王雨");
        whereUser.setAge(20);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(whereUser);

        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * AllEq用法
     */
    @Test
    void selectByWrapperAllEq() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "王雨");
        params.put("age", null);

        /**
         * queryWrapper.allEq(params);
         * 第二个参数不填，默认为true，如果属性值为null 则拼接 is null
         * SELECT id,name,age,email,manager_id,create_time FROM user WHERE name = ? AND age IS NULL
         * 填false，如果为null，则不拼接
         * SELECT id,name,age,email,manager_id,create_time FROM user WHERE name = ?
         */
        // queryWrapper.allEq(params,false);

        /**
         * 过滤属性key，即不拼接条件
         * SELECT id,name,age,email,manager_id,create_time FROM user WHERE age IS NULL
         */
        queryWrapper.allEq((k, v) -> !k.equals("name"), params);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            log.info("user = {}",user);
        }
    }

    /**
     * 返回结果封装成map，
     * 优点：只返回select指定的字段
     */
    @Test
    void selectByWrapperMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","name").like("name","雨").lt("age",40);


        List<Map<String, Object>> users = userMapper.selectMaps(queryWrapper);
        users.forEach(System.out::print);
    }

    /**
     * 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。
     * 并且只取年龄总和小于500的组。
     * select avg(age) avg_age,min(age) min_age,max(age) max_age
     * from user
     * group by manager_id
     * having sum(age) <500
     */
    @Test
    void selectByWrapperMaps2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) avg_age","min(age) min_age","max(age) max_age")
                .groupBy("manager_id").having("sum(age)<{0}",500);

        List<Map<String, Object>> users = userMapper.selectMaps(queryWrapper);
        users.forEach(System.out::println);
        /**
         * {max_age=40, avg_age=40.0000, min_age=40}
         * {max_age=25, avg_age=25.0000, min_age=25}
         * {max_age=32, avg_age=23.3333, min_age=19}
         */
    }

    /**
     * 只返回每个结果的第一个属性的值
     * 40.0000
     * 25.0000
     * 23.3333
     */
    @Test
    void selectByWrapperObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) avg_age","min(age) min_age","max(age) max_age")
                .groupBy("manager_id").having("sum(age)<{0}",500);

        List<Object> users = userMapper.selectObjs(queryWrapper);
        users.forEach(System.out::println);
    }

    // 查询符合条件的记录数
    @Test
    void selectByWrapperCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","雨").lt("age",40);

        Integer rows = userMapper.selectCount(queryWrapper);
        log.info("rows = {}",rows);
    }

    /**
     * 只能返回一条结果，找到两个及以上结果会报错
     */
    @Test
    void selectByWrapperOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","王雨").lt("age",40);

        User user = userMapper.selectOne(queryWrapper);
        log.info("user = {}",user);
    }

    /**
     * LambdaQueryWrapper相比QueryWrapper
     * 优点：防误写，属性写错有提示
     */
    @Test
    void selectLambda() {
        // 方式一创建
        //LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        // 方式二创建
        //LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 方式三创建
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();

        lambdaQuery.like(User::getName,"雨").lt(User::getAge,40);
        List<User> users = userMapper.selectList(lambdaQuery);
        users.forEach(System.out::println);

    }

    /**
     * 5.名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    void selectLambda2() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
        lambdaQuery.likeRight(User::getName, "雨").and(lqw -> lqw.lt(User::getAge, 40).or()
                .isNotNull(User::getEmail));
        List<User> users = userMapper.selectList(lambdaQuery);
        users.forEach(System.out::println);
    }

    /**
     * SELECT id,name,age,email,manager_id,create_time FROM user WHERE name LIKE ? AND age >= ?
     */
    @Test
    void  selectLambda3() {
        List<User> users = new LambdaQueryChainWrapper<User>(userMapper)
                .like(User::getName, "雨")
                .ge(User::getAge, 20)
                .list();
        users.forEach(System.out::println);

    }





}