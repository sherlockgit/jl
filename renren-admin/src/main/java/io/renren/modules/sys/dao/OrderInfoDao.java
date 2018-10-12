package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.OrderInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 00:57:48
 */
public interface OrderInfoDao extends BaseMapper<OrderInfoEntity> {

    OrderInfoEntity getCount (Map<String,Object> map);
	
}
