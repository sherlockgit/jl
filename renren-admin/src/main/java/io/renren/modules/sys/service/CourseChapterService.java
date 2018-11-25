package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.CourseChapterEntity;

import java.text.ParseException;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-04 14:56:19
 */
public interface CourseChapterService extends IService<CourseChapterEntity> {

    PageUtils queryPage(Map<String, Object> params) throws ParseException;

    void deletrByCourseInfoId(Integer id);
}

