package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.OrderRefundEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-08 14:48:16
 */
public interface OrderRefundDao extends BaseMapper<OrderRefundEntity> {
    OrderRefundEntity getByOrder(@Param("orderNo") String orderNo);

    OrderRefundEntity getByOrderFund(@Param("orderNo") String orderNo);
	
}
