package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.OrderRefundDao;
import io.renren.modules.sys.entity.OrderRefundEntity;
import io.renren.modules.sys.service.OrderRefundService;


@Service("orderRefundService")
public class OrderRefundServiceImpl extends ServiceImpl<OrderRefundDao, OrderRefundEntity> implements OrderRefundService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderRefundEntity> page = this.selectPage(
                new Query<OrderRefundEntity>(params).getPage(),
                new EntityWrapper<OrderRefundEntity>()
        );

        return new PageUtils(page);
    }

}
