package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
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

        String orderNo = (String)params.get("orderNo");
        String userPhone = (String)params.get("userPhone");
        String orderSource = (String)params.get("orderSource");
        String payType = (String)params.get("payType");
        String payStatus = (String)params.get("payStatus");

        Page<OrderInfoEntity> page = this.selectPage(
                new Query<OrderInfoEntity>(params).getPage(),
                new EntityWrapper<OrderInfoEntity>()
                        .like(StringUtils.isNotBlank(orderNo),"order_no", orderNo)
                        .like(StringUtils.isNotBlank(userPhone),"user_phone", userPhone)
                        .like(StringUtils.isNotBlank(orderSource),"order_source", orderSource)
                        .like(StringUtils.isNotBlank(payType),"pay_type", payType)
                        .like(StringUtils.isNotBlank(payStatus),"pay_status", payStatus)
        );

        return new PageUtils(page);
    }

}
