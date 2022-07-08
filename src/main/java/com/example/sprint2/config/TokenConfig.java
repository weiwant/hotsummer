package com.example.sprint2.config;

import com.example.sprint2.models.abc.Token;
import com.example.sprint2.models.abc.sub.RsaToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZWForum
 * @description Token配置
 * @date 2022/7/8
 */
@Configuration
public class TokenConfig {
    @Bean
    public Token tokenType() {
        return RsaToken.getFactory();
    }
}
