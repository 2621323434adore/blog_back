package org.xirang.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xirang.Entity.XiRangUser;
@Mapper
public interface UserMapper extends BaseMapper<XiRangUser> {
    @Select("select * from xirang_user where username = #{username}")
    Boolean findUserByUsername(String username);

    @Select("select * from xirang_user where email = #{email}")
    Boolean findUserByEmail(String email);

    @Select("select * from xirang_user where qq = #{qq}")
    Boolean findUserByQq(String qq);
}

