package org.xirang.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xirang.Entity.Comment;
import org.xirang.Entity.Label;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}