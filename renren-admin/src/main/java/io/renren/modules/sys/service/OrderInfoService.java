package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.OrderInfoEntity;
import io.renren.modules.sys.vo.OrderCountVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 00:57:48
 */
public interface OrderInfoService extends IService<OrderInfoEntity> {

    PageUtils queryPage(Map<String, Object> params) throws ParseException;

    PageUtils getCount (Map<String,Object> map) throws ParseException;

    List<OrderCountVo> getExcle (Map<String,Object> map) throws ParseException;

    List<OrderInfoEntity> getExcleByOrder (Map<String,Object> map) throws ParseException;
}

