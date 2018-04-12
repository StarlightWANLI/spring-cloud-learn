package com.lxg.spring.config;


import com.lxg.spring.config.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.savedrequest.NullRequestCache;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许所有用户访问"/"和"/home"
        http.authorizeRequests()
                .antMatchers("/", "/index","/admin/*","/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js").permitAll()       //匹配这些的都不用进行权限校验  ,放开前端的一些请求
                .antMatchers("/admin/**").hasRole("ADMIN")   //如果使用hasRole来用角色限制权限，不用使用前缀ROLE_ ,如hasRole("ROLE_ADMIN")会报错
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
              //  .requestCache().requestCache(new NullRequestCache())    //取消请求缓存requestCache,防止出现莫名其妙的错误
               //  .and()
                .formLogin()             //对登录请求做配置
                .loginPage("/login")   //指定登录页是"/login"
                .defaultSuccessUrl("/loginSuccess")//登录成功后默认跳转到"/loginSuccess"
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()                    //对登出请求做配置
                .logoutUrl("/logout")      // 退出登录默认请求链接   /logout
                .deleteCookies("remember-me")    //退出删除本地token
                .logoutSuccessUrl("/index")//退出登录后的默认url是"/home"
                .permitAll()
                .and()
                .rememberMe()                                      //启用“记住我"功能，有token，不用登录可以直接访问各个链接  ，如果不保存，关闭浏览器，重新访问就又要登录了，前端没有勾选remember me也不会生效
                .tokenValiditySeconds(60 * 60 * 24 * 7)           //有效时间,七天
                .key("SSDKey")                                  //加密的key
                .and()
                .csrf().disable();    //禁用跨域保护;
    }





    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*
          测试过程可以直接在内存中认证
        auth.inMemoryAuthentication()
                .withUser("zhou").password("123").roles("USER")  //赋予权限
                .and()
                .withUser("admin").password("admin").roles("ADMIN","USER");*/

        auth
            .userDetailsService(customUserDetailsService);
           // .passwordEncoder(passwordEncoder());
    }

    /**
     * 设置用户密码的加密方式为MD5加密
     * @return
     */
    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }


}