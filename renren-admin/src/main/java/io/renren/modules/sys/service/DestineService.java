package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.DestineEntity;

import java.util.Map;

/**
 * 项目预约表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
public interface DestineService extends IService<DestineEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R insertDestince(DestineEntity destineEntity);
}

