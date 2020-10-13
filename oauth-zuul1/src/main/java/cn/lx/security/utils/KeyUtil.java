package cn.lx.security.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * cn.lx.security.util
 *
 * @Author Administrator
 * @date 10:50
 */
public class KeyUtil {

    /**
     * 读取秘钥
     * @param keyName
     * @return
     */
    public static String readKey(String keyName){
        ClassPathResource resource=new ClassPathResource(keyName);
        String key =null;
        try {
            InputStream is = resource.getInputStream();
            key = StreamUtils.copyToString(is, Charset.defaultCharset());
            key = StringUtils.replace(key, "\r", "");
            key = StringUtils.replace(key, "\n", "");
        }catch (Exception e){
            throw new RuntimeException("读取秘钥错误");
        }
        if (key==null){
            throw new RuntimeException("秘钥为空");
        }
        return key;
    }
}
