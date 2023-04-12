package org.xirang.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.xirang.Entity.Label;

import java.util.List;

@Mapper
public interface LabelMapper extends BaseMapper<Label> {
@Select("select * from label where type like ${name}")
    public List<Label> searchByNameLabel(String name);
}

