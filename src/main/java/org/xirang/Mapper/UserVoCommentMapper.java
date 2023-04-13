package org.xirang.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xirang.Entity.UserVoComment;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserVoCommentMapper {
    @Select("select * FROM " +
            "(SELECT u.username,u.id as u_id,u.qq,u.user_date,c.id as c_id,c.content,c.date,c.user_id,c.art_id " +
            "FROM `comment` as c" +
            " INNER JOIN xirang_user as u " +
            " ON c.user_id = u.id) as comment " +
            "WHERE comment.art_id = #{articleId}")
    public List<UserVoComment> getArticleComments(Long articleId);
}
