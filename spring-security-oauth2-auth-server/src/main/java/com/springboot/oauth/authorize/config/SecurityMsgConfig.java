package com.springboot.oauth.authorize.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.List;

/**
 * @Description:
 * @Author: 万里
 * @Date: 2018/4/18 12:09
 */
@Configuration
public class SecurityMsgConfig extends ReloadableResourceBundleMessageSource {

    @Value(value = "{classpath:org/springframework/security/messages}")
    List<String> basenames;



}
