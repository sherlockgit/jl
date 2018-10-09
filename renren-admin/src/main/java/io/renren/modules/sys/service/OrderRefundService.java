package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.OrderRefundEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-08 14:48:16
 */
public interface OrderRefundService extends IService<OrderRefundEntity> {

    PageUtils queryPage(Map<String, Object> params);

    OrderRefundEntity getByOrder(String orderNo);
}

