package org.xirang.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
   private Long id;
   private String content;
   private String date;
   private Long artId;
   private Long userId;
}
