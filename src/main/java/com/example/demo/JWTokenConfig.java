package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkTokenStore;

@Configuration
public class JWTokenConfig {

//    @Bean
//    public TokenStore jwtTokenStore(){
//        return new JwkTokenStore();
//    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter  converter = new JwtAccessTokenConverter();
        converter.setSigningKey("this is a test key");
        return converter;
    }
}
