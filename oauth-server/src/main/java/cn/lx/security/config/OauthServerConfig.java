package cn.lx.security.config;

import cn.lx.security.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.*;

import javax.sql.DataSource;
import java.security.KeyPair;

/**
 * cn.lx.security.config
 *
 * @Author Administrator
 * @date 15:41
 */
@Configuration
@EnableAuthorizationServer
public class OauthServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 读取application.yml中配置的证书文件，需要设置位置，密码，别名等
     * @return
     */
    @Bean
    public KeyProperties keyProperties() {
        KeyProperties keyProperties = new KeyProperties();
        return keyProperties;
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 用来查找用户的，而非客户端
     */
    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * 连接数据库获取客户端信息
     *
     * @return
     */
    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * token保存策略
     *
     * @return 返回一个用于生成和检索令牌的对象
     */
    @Bean
    public TokenStore tokenStore() {
        //return new InMemoryTokenStore();
        //return new JdbcTokenStore(dataSource);
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 设置令牌转换器
     * 令牌生成方式
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //这种方式传入私钥一直报错，不知道啥原因，所以现在改为使用秘钥库文件获取私钥
        //String privateKey = KeyUtil.readKey("privateKey.txt");
        KeyPair keyPair = new KeyStoreKeyFactory(
                        //将秘钥库文件转化为resource
                        keyProperties().getKeyStore().getLocation()
                        //秘钥库文件的访问密码
                        , keyProperties().getKeyStore().getPassword().toCharArray())
                //获取秘钥对
                .getKeyPair(keyProperties().getKeyStore().getAlias());
        //设置秘钥对
        converter.setKeyPair(keyPair);
        return converter;
    }

    /**
     * 授权信息保存策略
     *
     * @return
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
        //return new InMemoryApprovalStore();
    }


    /**
     * 授权码保存策略
     *
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
        //return new InMemoryAuthorizationCodeServices();
    }

    /**
     * 自定义一个令牌服务
     *
     * @return
     */
    /*@Bean
    public DefaultTokenServices myTokenServices(){
        //创建一个令牌服务
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        //设置令牌存储策略
        tokenServices.setTokenStore(tokenStore());
        //设置支持刷新令牌
        tokenServices.setSupportRefreshToken(true);
        //设置客户端信息来源
        tokenServices.setClientDetailsService(clientDetailsService());
        //设置令牌过期时间2小时
        tokenServices.setAccessTokenValiditySeconds(7200);
        //刷新令牌默认有效期3天
        tokenServices.setRefreshTokenValiditySeconds(259200);
        return tokenServices;
    }
*/
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //端点/oauth/token_key：提供公有秘钥的端点，如果使用jwt令牌的话
                .tokenKeyAccess("permitAll()")
                //端点/oauth/check_token: 用于资源服务令牌解析
                .checkTokenAccess("permitAll()")
                //允许通过表单认证（申请令牌）
                .allowFormAuthenticationForClients();
    }

    /**
     * 连接数据库，查询客户端信息
     * 配置客户端详细信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //内存中设置一个客户端
//        clients
//                .inMemory()
//                .withClient("heima_one")
//                .secret(bCryptPasswordEncoder.encode("123456"))
//                .scopes("read")
//                .authorizedGrantTypes("password")
//                .redirectUris("http://www.baidu.com");
        //连接数据库获取客户端信息
        //clients.jdbc(dataSource).passwordEncoder(bCryptPasswordEncoder);

        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //设置认证管理器，通过它验证用户名和密码是否正确
                .authenticationManager(authenticationManager)
                //设置授权信息存储策略，可以选择内存或者数据库
                .approvalStore(approvalStore())
                //设置令牌存储策略，可以选择普通令牌或者jwt令牌
                .tokenStore(tokenStore())
                //设置令牌转换器，指定生成jwt格式令牌，令牌存储策略为jwt型必须设置该配置才能生效
                .accessTokenConverter(jwtAccessTokenConverter())
                //设置授权码认证专用对象，可以选择将授权码保存在内存或者内存
                .authorizationCodeServices(authorizationCodeServices());
        //设置令牌服务，这里设置令牌的过期时间等等
        //.tokenServices(myTokenServices());
        //.userDetailsService(userDetailsService);
    }
}
