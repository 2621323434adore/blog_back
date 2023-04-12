package org.xirang.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

@TableName("label")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Label {
    @TableId(type = ASSIGN_ID)
    private int id;
    private String name;
    private String Type;
}
