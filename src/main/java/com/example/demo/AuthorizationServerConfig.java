package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
@Order(1)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private UserDetailService service;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(service)
                .accessTokenConverter(jwtAccessTokenConverter);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.inMemory()
                    .withClient("test1")
                    .secret(new BCryptPasswordEncoder().encode("test1111"))
                    .accessTokenValiditySeconds(3600)
                    .refreshTokenValiditySeconds(86400)
                    .scopes("all","a","b","c")
                    .authorizedGrantTypes("password","refresh_token")
                .and()
                    .withClient("test2")
                    .secret(new BCryptPasswordEncoder().encode("test2222"))
                    .accessTokenValiditySeconds(7200);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Override
//    public void configure(HttpSecurity auth) throws Exception {
//        auth.csrf().disable();
//    }
}
