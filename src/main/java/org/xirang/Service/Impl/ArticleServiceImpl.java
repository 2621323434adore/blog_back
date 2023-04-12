package org.xirang.Service.Impl;

import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xirang.Entity.Article;
import org.xirang.Mapper.ArticleMapper;
import org.xirang.Service.ArticleService;

import java.util.List;

@Component
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public List<Article> getArticleList(int page) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        int[] pages = PageUtil.transToStartEnd(page, 10);
        wrapper.last("limit " + pages[0] + ", " + 10);
        return articleMapper.selectList(wrapper);
    }

    public int getArticlesNumber() {
        return articleMapper.count();
    }

    public Article getArticleById(long id) {
        return articleMapper.selectById(id);
    }

    public boolean SaveArticle(Article article) {
        boolean save = save(article);
        return save;
    }
    public boolean UpdateArticle(Article article){
        boolean updateById = updateById(article);
        return updateById;
    }

    public int deleteArticle(Long id){
        int i = articleMapper.deleteById(id);
        return i;
    }
}

