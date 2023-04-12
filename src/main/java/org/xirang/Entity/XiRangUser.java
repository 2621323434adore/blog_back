package org.xirang.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName( "xirang_user")
public class XiRangUser implements Serializable {
    /*
AUTO，配合数据库设置自增主键，可以实现主键的自动增长，类型为nmber；
INPUT，由用户输入；
NONE，不设置，等同于INPUT；
ASSIGN_ID，只有当用户未输入时，采用雪花算法生成一个适用于分布式环境的全局唯一主键，类型可以是String和number；
ASSIGN_UUID，只有当用户未输入时，生成一个String类型的主键，但不保证全局唯一；
    * */
    @TableId(type = ASSIGN_ID)
    private Long id;
   // @TableField("username")
    //TableField 指定字段名
    private String username;
    private String name;
    private String password;
    private char gender;
    @TableField("birth_date")
    private String birthDate;
    @TableField("user_date")
    private String userDate;
    private String label;
    private String email;
    private String qq;
    private String mobile;
    private String authority;
}
