package cn.lx.security.filter;

import cn.lx.security.utils.JwtTokenUtil;
import cn.lx.security.utils.ResponseUtil;
import cn.lx.security.utils.Result;
import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * cn.lx.security.filter
 *
 * @Author Administrator
 * @date 10:41
 */
@Component
public class JwtDecodeFilter extends ZuulFilter {
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

        //获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpServletResponse response = currentContext.getResponse();

        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/server")){
            //认证服务不做任何操作，直接转发
            return null;
        }
        //获取令牌
        String authorization = request.getHeader("Authorization");
        if (authorization==null|| StringUtils.isEmpty(authorization)||!authorization.startsWith("Bearer ")){
            //不需要路由，默认是true
            currentContext.setSendZuulResponse(false);
            try {
                ResponseUtil.responseJson(new Result("403","用户未登录，请先登录！"),response);
            } catch (IOException e) {
               throw new RuntimeException("请求异常");
            }
            //直接返回
            return null;
        }

        //解析令牌
        Map<String, Object> map = JwtTokenUtil.decodeJwtToken(authorization.substring(7));
        //必须要有用户名
        if (map==null||map.get("user_name")==null){
            //不需要路由，默认是true
            currentContext.setSendZuulResponse(false);
            try {
                ResponseUtil.responseJson(new Result("403","用户未登录，请先登录！"),response);
            } catch (IOException e) {
                throw new RuntimeException("请求异常");
            }
            //直接返回
            return null;
        }

        //将用户名和权限添加到请求头中
        Map<String,Object> jsonToken=new HashMap<>();
        jsonToken.put("user_name",map.get("user_name"));
        jsonToken.put("authorities",map.get("authorities"));
        //base64加密后转发
        currentContext.addZuulRequestHeader("jsonToken", Base64.getEncoder()
                .encodeToString(JSON.toJSONString(jsonToken).getBytes()));
        return null;
    }
}
