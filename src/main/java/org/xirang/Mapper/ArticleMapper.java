package org.xirang.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xirang.Entity.Article;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
@Select("select count(id) as number FROM articles")
public int count();
}

