package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.UserCneeDao;
import io.renren.modules.sys.entity.UserCneeEntity;
import io.renren.modules.sys.service.UserCneeService;


@Service("userCneeService")
public class UserCneeServiceImpl extends ServiceImpl<UserCneeDao, UserCneeEntity> implements UserCneeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserCneeEntity> page = this.selectPage(
                new Query<UserCneeEntity>(params).getPage(),
                new EntityWrapper<UserCneeEntity>()
        );

        return new PageUtils(page);
    }

}
