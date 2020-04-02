package com.mp.demo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author xinggevip
 * @date 2020/04/02 16:41
 */
public class Generator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") ;
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("xinggevip");
        gc.setOpen(false);
        gc.setFileOverride(true);//是否覆盖文件
        gc.setServiceName("%sService");//去掉I前缀
        gc.setBaseResultMap(true); // xml resultmap
        gc.setBaseColumnList(true); // xml columlist
        gc.setSwagger2(true);  //实体属性 Swagger2 注解
        gc.setIdType(IdType.ID_WORKER);//主键生成策略
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/mp?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        // 数据类型转换
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if ( fieldType.toLowerCase().contains( "datetime" ) ) {
                    return DbColumnType.DATE;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }

        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //String packgeName = scanner("包名");
        /*pc.setParent("com.mp.demo");
        pc.setController("controller."+packgeName);
        pc.setEntity("domain." + packgeName);
        pc.setService("service." + packgeName);
        pc.setServiceImpl("service." + packgeName +".impl");
        pc.setMapper("dao." + packgeName);*/

        // pc.setModuleName("");//模块名称
        pc.setParent("com.mp.demo");
        pc.setController("controller");
        pc.setEntity("domain");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                /*return projectPath + "/src/main/resources/mapper/" + packgeName
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;*/
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//lombok
        strategy.setRestControllerStyle(true);//Controller层rest风格
        //strategy.setControllerMappingHyphenStyle(true);//localhost:8080/hello_id_2
        //strategy.setLogicDeleteFieldName("deleted");//逻辑删除字段

        // 自动填充配置
        //TableFill create_time = new TableFill("create_time", FieldFill.INSERT);
        //TableFill update_time = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        //strategy.setTableFillList(Arrays.asList(create_time, update_time));

        // 乐观锁
        //strategy.setVersionFieldName("version");

        // 公共父类
        // 写于父类中的公共字段
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix("t_");  // 表前缀
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
