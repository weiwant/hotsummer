package com.example.sprint2.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author ZWForum
 * @description mybatis-plus代码生成器配置
 * @date 2022/6/23
 */
public class GeneratorConfig {
    public static void main(String[] args) {
        new GeneratorConfig().generator("", "", "", Arrays.asList("", "..."));
    }

    /**
     * @param url
     * @param username
     * @param password
     * @param tableName
     * @return
     * @author ZWForum
     * @description 输入url，用户名，密码，表名生成实体类
     * @date 2022/6/29
     */
    private void generator(String url, String username, String password, List<String> tableName) {
        String path = System.getProperty("user.dir");
        String packageName = (this.getClass().getCanonicalName()).replace(".config.GeneratorConfig", "");
        String codePath = "/src/main/java";
        String resourcePath = "/src/main/resources";
        FastAutoGenerator.create(url, username, password)
                //全局配置
                .globalConfig(builder -> {
                    builder.author(System.getProperty("user.name"))//作者
                            .disableOpenDir()//关闭输出目录
                            .outputDir(path + codePath);//输出目录
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent(packageName)//父包名
                            .entity("mybatis.entity")//Entity包名
                            .mapper("mybatis.mapper")//Mapper包名
                            .xml("mapper")//MapperXml包名
                            .service("mybatis.service")//Service包名
                            .serviceImpl("mybatis.service.impl")//ServiceImpl包名
                            .controller("")//Controller包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + resourcePath + "/mapper"));//MapperXml生成路径
                })
                //配置策略
                .strategyConfig(builder -> {
                    builder.addInclude(tableName)//需要映射生成的表名
                            //Entity配置
                            .entityBuilder()
                            .naming(NamingStrategy.underline_to_camel)//Entity类命名配置
                            .enableLombok()//开启Lombok
                            .enableTableFieldAnnotation()//生成注解
                            .fileOverride()//覆盖生成文件
                            //Controller配置
                            .controllerBuilder()
                            .formatFileName("")//Controller命名配置
                            .fileOverride()//覆盖生成文件
                            //Service配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")//Service命名配置
                            .formatServiceImplFileName("%sServiceImpl")//ServiceImpl命名配置
                            .fileOverride()//覆盖生成文件
                            //Mapper配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class)//设置父类
                            .enableMapperAnnotation()//开启@Mapper
                            .fileOverride()//覆盖生成文件
                            .formatMapperFileName("%sMapper")//格式Mapper化文件名
                            .formatXmlFileName("%sMapper");//格式化Xml文件名
                })
                .templateEngine(new FreemarkerTemplateEngine())//模板引擎
                .execute();
    }
}
