package cn.lx.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * cn.lx.security.filter
 *
 * @Author Administrator
 * @date 16:09
 */
@Component
public class SendJsonTokenFilter extends ZuulFilter {
    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
     * important for a filter. filterOrders do not need to be sequential.
     *
     * @return the int order of a filter
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * a "true" return from this method means that the run() method should be invoked
     *
     * @return true if the run() method should be invoked. false will not invoke the run() method
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
     *
     * @return Some arbitrary artifact may be returned. Current implementation ignores it.
     * @throws ZuulException if an error occurs during execution.
     */
    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof OAuth2Authentication)){
            //说明请求不是oauth请求，没有携带令牌
            //直接放行
            return null;
        }
        //强转为真实类型
        OAuth2Authentication oAuth2Authentication= (OAuth2Authentication) authentication;
        //取出用户名和用户权限
        UsernamePasswordAuthenticationToken userAuthentication = (UsernamePasswordAuthenticationToken)
                oAuth2Authentication.getUserAuthentication();
        Collection<? extends GrantedAuthority> authorities = userAuthentication.getAuthorities();
        String principal = (String) userAuthentication.getPrincipal();
        //获取权限
        Set<String> authoritySet = AuthorityUtils.authorityListToSet(authorities);
        Map<String,Object> jsonToken=new HashMap<>();
        jsonToken.put("user_name",principal);
        jsonToken.put("authorities",authoritySet);
        currentContext.addZuulRequestHeader("jsonToken", Base64.getEncoder()
                .encodeToString(JSON.toJSONString(jsonToken).getBytes()));
        return null;
    }
}
