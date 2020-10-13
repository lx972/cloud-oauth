package cn.lx.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * cn.lx.security.filter
 *
 * @Author Administrator
 * @date 15:29
 */
@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中明文信息
        String jsonToken = request.getHeader("jsonToken");
        if (jsonToken==null|| StringUtils.isEmpty(jsonToken)){
            //没有信息
            throw new RuntimeException("用户未登录，请先登录");
        }
        String userInfo = new String(Base64.getDecoder().decode(jsonToken), "UTF-8");
        //得到用户名和用户权限
        JSONObject jsonObject = JSON.parseObject(userInfo);
        String user_name = jsonObject.getString("user_name");
        JSONArray authorities = jsonObject.getJSONArray("authorities");
        //使用工具类转化权限
        String[] array = authorities.toArray(new String[0]);
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(array);
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                user_name, null,authorityList);
        //已通过认证的对象放入容器中
        SecurityContextHolder.getContext().setAuthentication(authRequest);
        filterChain.doFilter(request,response);
    }
}
