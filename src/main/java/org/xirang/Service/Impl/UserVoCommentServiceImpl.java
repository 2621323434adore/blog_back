package org.xirang.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xirang.Entity.UserVoComment;
import org.xirang.Mapper.UserVoCommentMapper;
import org.xirang.Service.UserVoCommentService;

import java.util.HashMap;
import java.util.List;

@Component
public class UserVoCommentServiceImpl implements UserVoCommentService {
@Autowired
    UserVoCommentMapper userVoCommentMapper;
    @Override
    public List<UserVoComment> getArticleComments(Long articleId) {
        List<UserVoComment> userComments = userVoCommentMapper.getArticleComments(articleId);
        return userComments;
    }
}
