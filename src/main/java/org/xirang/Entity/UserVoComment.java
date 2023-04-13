package org.xirang.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVoComment {
   private String username;
   private long uId ;
   private String qq;
   private String userDate;
   private Long cId;
   private String content;
    private String   date;
    private Long   userId;
    private String    artId;
}
