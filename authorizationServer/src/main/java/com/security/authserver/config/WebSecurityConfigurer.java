package com.security.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description:
 * @Author: 万里
 * @Date: 2018/4/19 9:42
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class  WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserService);

        /*auth
                .inMemoryAuthentication()
                .withUser("wanli").password("{noop}123456").roles("USER");*/
    }



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 1\这里记得设置requestMatchers,不拦截需要token验证的url
     * 不然会优先被这个filter拦截,走用户端的认证而不是token认证
     * 2\这里记得对oauth的url进行保护,正常是需要登录态才可以
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .requestMatchers().antMatchers("/oauth/**","/login/**","/logout/**")          //这里表示不被spring security拦截认证的url
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .formLogin().permitAll();////新增login form支持用户登录及授权
    }


}
