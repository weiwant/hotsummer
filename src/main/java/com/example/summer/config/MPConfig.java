package com.example.summer.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hy
 * @Date 2022/6/27
 * @Description 分页查询配置
 */

@Configuration
public class MPConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        //MP拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件PaginationInnerInterceptor
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        //设置数据库类型
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        //溢出总页数后是否进行处理,默认不处理，这里设置为处理（虽然我也不知道怎么处理）
        paginationInnerInterceptor.setOverflow(true);
        //添加
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}
