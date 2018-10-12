package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.InvoiceInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 10:07:01
 */
public interface InvoiceInfoDao extends BaseMapper<InvoiceInfoEntity> {

    Integer getCount(Map<String,Object> map);
	
}
