package com.qdc.demoeurekaauthserver.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;


//实现OAuth2.0认证服务的配置
@Component
@Configuration
public class Oauth2AuthorizationServerConfigureation extends AuthorizationServerConfigurerAdapter {
    //用于进行身份验证的接口
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DruidDataSource druidDataSource;

    //AuthorizationServerSecurityConfigurer配置令牌安全节点的安全约束
    //只有设置了ROLE_TRUSTED_CLIENT权限的客户端才可以通过认证
    //ROLE_TRUSTED_CLIENT可信任用户
    @Override
    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) throws Exception {
        //这对于access token认证很重要
        securityConfigurer.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }
    //配置数据源
    @Override
    public void configure(ClientDetailsServiceConfigurer clientConfigurer) throws Exception {
        //数据库管理客户端应用，从dataSource配置的数据源中读取客户端数据
        //客户端数据保存在数据库中
        clientConfigurer.withClientDetails(new JdbcClientDetailsService(druidDataSource));
    }
    //配置认证服务器的非安全属性
    //生成令牌
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) throws Exception {
        //用户信息查询服务
        endpointsConfigurer.userDetailsService(userDetailsService);
        //数据库管理
        TokenStore tokenStore=new JdbcTokenStore(druidDataSource);
        DefaultTokenServices tokenServices=new DefaultTokenServices();

        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(new JdbcClientDetailsService(druidDataSource));
        tokenServices.setAccessTokenValiditySeconds(38000);//设置验证时间
        tokenServices.setRefreshTokenValiditySeconds(180);//设置刷新时间
        endpointsConfigurer.tokenServices(tokenServices);
        //数据库管理授权码
        endpointsConfigurer.authorizationCodeServices(new JdbcAuthorizationCodeServices(druidDataSource));
        //数据库管理授权信息
        ApprovalStore approvalStore=new JdbcApprovalStore(druidDataSource);
        endpointsConfigurer.approvalStore(approvalStore);
    }
}
