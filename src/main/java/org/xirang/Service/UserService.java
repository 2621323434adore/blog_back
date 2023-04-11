package org.xirang.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.xirang.Entity.XiRangUser;
import org.xirang.Mapper.UserMapper;

@Service
public interface UserService extends IService<XiRangUser> {
}
