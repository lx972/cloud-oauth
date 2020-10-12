package cn.lx.security.config;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * cn.lx.security.config
 *
 * @Author Administrator
 * @date 18:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OauthJwtTest {


    @Autowired
    private KeyProperties keyProperties;


    /**
     * 创建一个具有管理员权限的令牌
     * @return
     */
    @Test
    public void createJwt(){
        KeyPair keyPair = new KeyStoreKeyFactory(
                //将秘钥库文件转化为resource
                keyProperties.getKeyStore().getLocation()
                //秘钥库文件的访问密码
                , keyProperties.getKeyStore().getPassword().toCharArray())
                //获取秘钥对
                .getKeyPair(keyProperties.getKeyStore().getAlias());
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String,Object> content=new HashMap<>(16);
        //该令牌有管理员权限
        content.put("authorities",new String[]{"PRODUCT_LIST"});
        content.put("aud",new String[]{"product_api"});
        content.put("scope",new String[]{"read"});
        content.put("client_id","heima_one");
        content.put("user_name","lx");
        //设置超时时间为当前时间的100之后,需要改为秒
        content.put("exp",System.currentTimeMillis()/1000+100);
        String token = JwtHelper.encode(JSON.toJSONString(content),  new RsaSigner(privateKey)).getEncoded();
        System.out.println(token);
    }


}
