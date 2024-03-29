package com.mp.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;

@Configuration
public class MyBatisPlusConfig {

    public static ThreadLocal<String> myTableName = new ThreadLocal<>();


    /**
     * MyBatisPlus3.1.1版本以下需要此配置，以上则不需要配置
     * @return
     */
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }

    /**
     * 配置乐观锁
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /*@Bean
    @Profile({"dev","test"}) // 指定生产环境生效
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);  // 是否格式化，默认为false
        performanceInterceptor.setMaxTime(50L);   // 超过5ms 就抛出异常
        return performanceInterceptor;
    }*/

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        /**
         * 配置多租户
         *
         * 查询：会自动加入条件
         * SELECT id, name, age, email, manager_id, create_time, update_time, version
         * FROM user
         * WHERE user.manager_id = 1088248166370832385 AND deleted = 0
         *
         * 插入：自动设置 manager_id
         * INSERT INTO user (id, name, age, email, create_time, manager_id) VALUES (?, ?, ?, ?, ?, 1088248166370832385)
         *
         * 更新删除同样会自动加入条件
         */
        ArrayList<ISqlParser> sqlParsers = new ArrayList<>();
        /*TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
                return new LongValue(1088248166370832385L);
            }

            @Override
            public String getTenantIdColumn() {
                return "manager_id";
            }

            @Override
            public boolean doTableFilter(String tableName) {

                // 过滤不加租户信息的表
                if ("role".equals(tableName)) {
                    return true;
                }

                return false;
            }
        });

        sqlParsers.add(tenantSqlParser);*/

        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
        HashMap<String, ITableNameHandler> tableNameHandlerHashMap = new HashMap<>();
        tableNameHandlerHashMap.put("user", new ITableNameHandler() {
            @Override
            public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
                return myTableName.get();
            }
        });
        dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerHashMap);
        sqlParsers.add(dynamicTableNameParser);
        paginationInterceptor.setSqlParserList(sqlParsers);
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {

                /**
                 * 符合条件则不加入租户信息
                 * SELECT id,name,age,email,manager_id,create_time,update_time,version FROM user WHERE id=? AND deleted=0
                 * sql进行了过滤后，指定表名则不生效
                 */
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                if ("com.mp.dao.UserMapper.selectById".equals(ms.getId())) {
                    return true;
                }

                return false;
            }
        });
        return paginationInterceptor;
    }

}
