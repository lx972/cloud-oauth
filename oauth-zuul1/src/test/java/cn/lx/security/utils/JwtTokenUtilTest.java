package cn.lx.security.utils;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * cn.lx.security.utils
 *
 * @Author Administrator
 * @date 15:15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtTokenUtilTest {

    @Test
    public void decodeJwtToken() {

        //获取公钥
        String rsaPublicKey = KeyUtil.readKey("publicKey.txt");
        //验签
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicHJvZHVjdF9hcGkiXSwidXNlcl9uYW1lIjoid3giLCJzY29wZSI6WyJyZWFkIl0sImV4cCI6MTYwMjQyOTA3MCwiYXV0aG9yaXRpZXMiOlsiUFJPRFVDVF9MSVNUIl0sImp0aSI6ImUyNzQwNGUxLWM2NjYtNGYyZC04NmU5LTA5NWIyNWY2ZGM1MCIsImNsaWVudF9pZCI6ImhlaW1hX29uZSJ9.L11vVVM_pFdNZ20GB00Opa4G6umS3ACiC8E6p8pQkWNkZicfbKE4IcP8tBnrc-gqEKwAC1e9oW_pWo8BGtEHJ_-603Czol5m4GWAguAL-0hmWbDIoxBrPpEb100Fgut7hxZTmsT0f5tBjeo7QbFSrqV5a2AurBS4nMmPJdko14616dVLhbGWrzlUgnrC9niyOQscUmZ5g4Z2oTQI8emkPEkP1zTlxdmleYOkpVsJhbTH6w6udNUaNT6FqL5UGTmqRp7ZJ7AzM9kxKg34kJMLW6oKZEHmcEnUdMQumaIPJ4pSqSmBO5lzOUjBZaT-aqGpN1hGVrYjqYM0qZUMoKB87w";
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(rsaPublicKey));
        String claimsStr = jwt.getClaims();
        Map<String, Object> claims = JSON.parseObject(claimsStr, Map.class);
        //令牌是否过期
        if (claims.containsKey("exp") && claims.get("exp") instanceof Integer) {
            Integer intValue = (Integer) claims.get("exp");
            claims.put("exp", new Long(intValue));
            Date expiration = new Date(new Long(intValue));
            if (!expiration.before(new Date())) {
                throw new RuntimeException("令牌已经过期了");
            }
        }
        //this.getJwtClaimsSetVerifier().verify(claims);
        System.out.println(claims);
    }
}
