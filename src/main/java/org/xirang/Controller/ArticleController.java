package org.xirang.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.PageUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xirang.Entity.Article;
import org.xirang.Service.Impl.ArticleServiceImpl;
import org.xirang.Utils.ResultCode;
import org.xirang.Utils.ResultObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    ArticleServiceImpl articleService;


    /**
     * 分页查询文章列表
     */
    @CrossOrigin
    @RequestMapping(value = "/getArticleList", method = RequestMethod.GET)
    @ResponseBody
    public ResultObject getArticleList(@RequestParam int page) {
        ResultObject result = new ResultObject();
        List<Article> list = articleService.getArticleList(page);
        int articlesCount = articleService.getArticlesNumber();
        result.setResultCode(ResultCode.SUCCESS).setMsg("请求成功！").setData(list).setData("total",articlesCount);
        return result;
    }

    /**
     * 加载单个文章详细内容
     */
    @CrossOrigin
    @RequestMapping(value = "/readArticle",method = RequestMethod.GET)
    public ResultObject readArticle(@RequestParam Long id) {
        ResultObject object = new ResultObject();
        Article article = articleService.getArticleById(id);
        if (article != null) {
            object.setData(article).setResultCode(ResultCode.SUCCESS).setMsg( "请求成功");
        } else {
            object.setResultCode(ResultCode.NotFoundException).setMsg("未找到文章！");
        }
        return object;
    }


    /**
     * 发布新文章
     */
    @RequestMapping("/sendArticle")
    public ResultObject sendArticle(@RequestParam("title") String title, @RequestParam("articleCover") String articleCover, @RequestParam("label") String label, @RequestParam("type") String type, @RequestParam("sketch") String sketch, @RequestParam("artId") long artId, @RequestParam("artName") String artName, @RequestParam("text") String text, @RequestParam("token") String token, @RequestParam("id") Long id) {

        ResultObject resultObject = new ResultObject();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String articleDate = format.format(date);  //获取当前时间作为文章发布日期
        if (StpUtil.getLoginIdByToken(token) == id) {  //再次验证用户是否登录
            Article article = new Article(0L, title, articleCover, label, type, sketch, artId, artName, text, articleDate, 0);
            //根据用户上传的数据包装文章实体
            boolean save = articleService.SaveArticle(article);
            //将文章进行保存
            if (save) {
                //验证文章是否发布
                resultObject.setResultCode(ResultCode.SUCCESS).setData("Y").setMsg("文章发布成功！正在等待审核...");
            } else {
                resultObject.setResultCode(ResultCode.FAILED).setData("N").setMsg("文章发布失败！请稍后重新尝试...");
            }
        }
        return resultObject;
    }

    /** \
     * 更新文章数据
     */
    @RequestMapping("/updateArticle")
    public ResultObject updateArticle(@RequestParam("articleId") Long articleId, @RequestParam("title") String title, @RequestParam("articleCover") String articleCover, @RequestParam("label") String label, @RequestParam("type") String type, @RequestParam("sketch") String sketch, @RequestParam("artId") long artId, @RequestParam("artName") String artName, @RequestParam("text") String text, @RequestParam("hot") int hot, @RequestParam("token") String token, @RequestParam("userId") Long id) {
        ResultObject object = new ResultObject();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String articleDate = format.format(date);
        if (artId == id) {
            Article article = new Article(articleId, title, articleCover, label, type, sketch, artId, artName, text, articleDate, hot);
            boolean update = articleService.updateById(article);
            if (update) {
                object.setResultCode(ResultCode.SUCCESS).setData("Y").setMsg("文章更新成功！正在等待审核...");
            } else {
                object.setResultCode(ResultCode.FAILED).setData("N").setMsg("文章更新失败！请稍后重新尝试...");
            }
        } else {
            object.setResultCode(ResultCode.WARNING).setMsg("更改失败，请重新登录后尝试");
        }
        return object;
    }

    /**
     * 根据文章id删除文章信息
     */
    @RequestMapping("/deleteArticle")
    public ResultObject deleteArticle(@RequestParam Long id) {
        ResultObject object = new ResultObject();
        int i = articleService.deleteArticle(id);
        if (i == 0) {
            object.setResultCode(ResultCode.FAILED).setData("N").setMsg("文章删除失败....");
        } else {
            object.setResultCode(ResultCode.SUCCESS).setData("Y").setMsg("文章删除成功....");
        }
        return object;
    }
}
