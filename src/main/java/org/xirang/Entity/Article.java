package org.xirang.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("articles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
@TableId
private long id;
private String title;
@TableField("article_cover")
private String articleCover;
private String label;
private String type;
private String sketch;
@TableField("art_id")
private long artId;
@TableField("art_name")
private String artName;
private String text;
private String date;
private long hot;
}
