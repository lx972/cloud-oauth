package cn.lx.security.dao;

import cn.lx.security.domain.SysUser;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * cn.lx.security.dao
 *
 * @Author Administrator
 * @date 12:16
 */
public interface UserMapper extends Mapper<SysUser> {

    @Select("select * from sys_user where username=#{username}")
    @Results({@Result(id = true, property = "id", column = "id"),
            @Result(property = "authorities", column = "id", javaType = List.class,
                    many = @Many(select = "cn.lx.security.dao.PermissionMapper.findByUid"))})
    public SysUser findByUsername(String username);

}
