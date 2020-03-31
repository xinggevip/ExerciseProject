package com.mp.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
     * 创建日期为2019年2月14日并且直属上级为名字为王姓
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

}