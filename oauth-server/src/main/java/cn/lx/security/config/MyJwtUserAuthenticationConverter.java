package cn.lx.security.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * cn.lx.security.config
 *
 * @Author Administrator
 * @date 17:06
 */
@Component
public class MyJwtUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    /**
     * 向令牌添加自定义信息
     * @param authentication
     * @return
     */
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<String, Object>();
        response.put(USERNAME, authentication.getName());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        //自己添加的额外数据
        response.put("sex","nan");
        return response;
    }
}
