package com.example.sprint2.config;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.text.CaseUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZWForum
 * @description 项目初始化配置包和Tests
 * @date 2022/6/29
 */
public class InitialConfig {
    public static void main(String[] args) throws IOException {
        new InitialConfig().initialTemplate();
    }

    private void initialTemplate() throws IOException {
        String path = System.getProperty("user.dir");//项目路径
        String javaPath = path + "\\src\\main\\java";//java根目录
        String resourcesPath = path + "\\src\\main\\resources";//资源根目录
        String testJavaPath = path + "\\src\\test\\java";//测试根目录
        Integer last = path.lastIndexOf("\\");
        String project = null;//项目名称
        if (last != path.length() - 1 && last != -1) {
            project = path.substring(last + 1);
        }
        String packageName = getClass().getPackage().getName().replace(".config", "");//包名
        //要创建的包
        List<String> packageToCreate = Arrays.asList(
                "config",
                "controller",
                "dao",
                "models.abc",
                "models.abc.sub",
                "models.enumerate",
                "models.enumerate.impl",
                "models.pojo",
                "models.vo",
                "mybatis.entity",
                "mybatis.mapper",
                "mybatis.service",
                "mybatis.service.impl",
                "service",
                "service.impl",
                "utils",
                "utils.annotations"
        );
        //要创建的资源目录
        List<String> resourceDirToCreate = Arrays.asList(
                "mapper",
                "sql",
                "static",
                "templates"
        );
        //要创建的资源文件
        List<String> resourceFileToCreate = Arrays.asList(
                "application.properties"
        );
        //创建包
        for (String singlePackage : packageToCreate) {
            File file = new File(javaPath + "\\" + packageName.replace(".", "\\") + "\\" + singlePackage.replace(".", "\\"));
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        //创建资源目录
        for (String singleResource : resourceDirToCreate) {
            File file = new File(resourcesPath + "\\" + singleResource.replace(".", "\\"));
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        //创建资源文件
        for (String singleResource : resourceFileToCreate) {
            File file = new File(resourcesPath + "\\" + singleResource);
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        //创建测试根目录
        File file = new File(testJavaPath + "\\" + packageName.replace(".", "\\"));
        if (!file.exists()) {
            file.mkdirs();
        }
        //创建测试源文件
        MethodSpec method = MethodSpec.methodBuilder("contextLoads")
                .addAnnotation(Test.class)
                .returns(void.class)
                .build();
        TypeSpec typeSpec = TypeSpec.classBuilder(CaseUtils.toCamelCase(project, true).replace("-", "") + "ApplicationTests")
                .addMethod(method)
                .addAnnotation(SpringBootTest.class)
                .build();
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec)
                .build();
        javaFile.writeTo(new File(testJavaPath));
    }
}
