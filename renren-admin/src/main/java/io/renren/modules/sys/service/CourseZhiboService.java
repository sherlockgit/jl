package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.CourseZhiboEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 22:02:06
 */
public interface CourseZhiboService extends IService<CourseZhiboEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

