package org.xirang.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xirang.Entity.XiRangUser;
@Mapper
public interface UserMapper extends BaseMapper<XiRangUser> {
@Select("select * from User where username = #{username}")
    XiRangUser findUserByUsername(String username);
}
