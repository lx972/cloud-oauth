package cn.lx.security.util;

import com.alibaba.fastjson.JSON;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;

import java.util.Date;
import java.util.Map;

/**
 * cn.lx.security.util
 *
 * @Author Administrator
 * @date 11:13
 */
public class JwtTokenUtil {


    /**
     * 解析令牌
     * @param token
     * @return
     */
    public static Map<String, Object> decodeJwtToken(String token) {
        try {
            //获取公钥
            String rsaPublicKey = KeyUtil.readKey("publicKey.txt");
            //验签
            Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(rsaPublicKey));
            String claimsStr = jwt.getClaims();
            Map<String, Object> claims = JSON.parseObject(claimsStr,Map.class);
            //令牌是否过期
            if (claims.containsKey("exp") && claims.get("exp") instanceof Integer) {
                Integer intValue = (Integer) claims.get("exp");
                claims.put("exp", new Long(intValue));
                Date expiration=new Date(new Long(intValue));
                if(!expiration.before(new Date())){
                    throw new RuntimeException("令牌已经过期了");
                }
            }
            //this.getJwtClaimsSetVerifier().verify(claims);
            return claims;
        }
        catch (Exception e) {
            throw new InvalidTokenException("Cannot convert access token to JSON", e);
        }
    }
}
