package com.security.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 *
 *
 * @Description:认证服务器配置
 * @Author: 万里
 * @Date: 2018/4/18 15:56
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    /**
     * 注入authenticationManager，使用WebSecurityConfigurerAdapter中配置的 authenticationManager来支持密码
     * 认证
     * 来支持 password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserService myUserService;


    @Override
    public void configure( ClientDetailsServiceConfigurer clients ) throws Exception {
        //这里主要配置的是客户端的信息，而不是认证用户的信息
        //添加客户端信息
        clients.inMemory()                  // 使用in-memory存储客户端信息
                .withClient("client")       // client_id
                .secret("{noop}secret")                   // client_secret
                .authorizedGrantTypes("authorization_code","password","client_credentials","refresh_token","implicit")     // 该client允许的授权类型
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")          //认证通过，授予的权限
                .scopes("all")      // 允许的授权范围
                .accessTokenValiditySeconds(120).//Access token is only valid for 2 minutes.
                refreshTokenValiditySeconds(600);//Refresh token is only valid for 10 minutes.
           ;
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.userDetailsService(myUserService);
       // endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory));       //配置使用redis存储token

    }


    /**
     * 使用授权服务的 /oauth/check_token 端点你需要将这个端点暴露出去，以便资源服务可以进行访问
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //默认是denyAll()  拒绝所有
        oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
                .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }


}
