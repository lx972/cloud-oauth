package cn.lx.security.config;

import cn.lx.security.util.KeyUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * cn.lx.security.config
 *
 * @Author Administrator
 * @date 17:56
 */
@Configuration
//记住这个注解，继承的类从注解源码注释找
@EnableResourceServer
public class OauthResourceConfig  extends ResourceServerConfigurerAdapter {


    /**
     * 告诉服务器令牌的类型是jwt
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 告诉服务器令牌如何验证
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        String publicKey = KeyUtil.readKey("publicKey.txt");
        //设置rsa公钥
        jwtAccessTokenConverter.setVerifierKey(publicKey);
        return jwtAccessTokenConverter;
    }

    /**
     * 设置资源标识，并设置令牌验证策略
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                //很重要，唯一标识这个资源服务器
                .resourceId("product_api")
                .tokenStore(tokenStore());
    }

    /**
     * 和security中的同名方法差不多
     * 这里面可以配置客户端的访问权限
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //前后端分离，禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //认证服务的请求全部放行
                .and().authorizeRequests().antMatchers("/server/**").permitAll()
                .and().authorizeRequests()
                //客户端权限为read才能使用get方法，读取资源
                .antMatchers(HttpMethod.GET).access("#oauth2.hasScope('read')")
                //客户端权限为write能使用post方法，修改资源
                .antMatchers(HttpMethod.POST).access("#oauth2.hasScope('write')");
    }
}


