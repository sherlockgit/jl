package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.CourseInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-04 11:10:26
 */
public interface CourseInfoService extends IService<CourseInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

