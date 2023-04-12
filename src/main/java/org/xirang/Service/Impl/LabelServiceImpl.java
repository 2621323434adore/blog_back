package org.xirang.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xirang.Entity.Label;
import org.xirang.Mapper.LabelMapper;
import org.xirang.Service.LabelService;

import java.util.List;

@Component
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {
    @Autowired
    LabelMapper labelMapper;

    public List<Label> getArticleById() {
        return list();
    }

    public List<Label> searchLabel(String label) {
        return labelMapper.searchByNameLabel("\""+"%"+""+label.toUpperCase()+""+"%"+"\"");
    }

    public boolean saveLabel(Label label) {
        boolean save = save(label);
        return save;
    }

    public boolean UpdateArticle(Label label) {
        boolean updateById = updateById(label);
        return updateById;
    }

    public int deleteArticle(int id) {
        int i = labelMapper.deleteById(id);
        return i;
    }
}

