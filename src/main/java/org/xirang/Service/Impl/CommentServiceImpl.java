package org.xirang.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xirang.Entity.Comment;
import org.xirang.Mapper.CommentMapper;
import org.xirang.Service.CommentService;

import java.util.List;

@Component
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    public boolean saveComment(Comment comment) {
        boolean save = save(comment);
        return save;
    }

    public int deleteComment(int id) {
        int i = commentMapper.deleteById(id);
        return i;
    }
}

