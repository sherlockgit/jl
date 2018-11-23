package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.InvoiceInfoEntity;
import io.renren.modules.sys.vo.OrderCountVo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 10:07:01
 */
public interface InvoiceInfoService extends IService<InvoiceInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<InvoiceInfoEntity> getExcle (Map<String,Object> map) throws ParseException;
}

