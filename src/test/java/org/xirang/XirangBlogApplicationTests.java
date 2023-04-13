package org.xirang;

import cn.hutool.core.util.PageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xirang.Entity.Article;
import org.xirang.Entity.UserVoComment;
import org.xirang.Service.Impl.ArticleServiceImpl;
import org.xirang.Service.Impl.UserVoCommentServiceImpl;
import org.xirang.Utils.PasswordUtil;
import java.util.List;

@SpringBootTest
class XirangBlogApplicationTests {
@Autowired
    ArticleServiceImpl articleService;
@Autowired
UserVoCommentServiceImpl commentService;
    @Test
    void login() {
        System.out.println(PasswordUtil.encode("123456"));
        System.out.println(PasswordUtil.verify("123456","e10adc3949ba59abbe56e057f20f883e"));
    }
    @Test
    void token() {
        System.out.println();
    }
    @Test
    void getArticleList() {
        int articlesNumber = articleService.getArticlesNumber();
        int totalPage = PageUtil.totalPage(articlesNumber, 10);
        System.out.println("articlesNumber: "+articlesNumber+"totalPage :"+totalPage);
        int[] startEnd1 = PageUtil.transToStartEnd(4, 10);
       List<Article> articles =  articleService.getArticleList(startEnd1[1]);
        System.out.println(articles);
    }
    @Test
    public void getArticleComment(){
        List<UserVoComment> comment = commentService.getArticleComments(30L);
        System.out.println(comment);
    }}
