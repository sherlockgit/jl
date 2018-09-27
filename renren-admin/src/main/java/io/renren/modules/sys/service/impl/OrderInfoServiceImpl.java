package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.OrderInfoDao;
import io.renren.modules.sys.entity.OrderInfoEntity;
import io.renren.modules.sys.service.OrderInfoService;


@Service("orderInfoService")
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfoEntity> implements OrderInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderInfoEntity> page = this.selectPage(
                new Query<OrderInfoEntity>(params).getPage(),
                new EntityWrapper<OrderInfoEntity>()
        );

        return new PageUtils(page);
    }

}
