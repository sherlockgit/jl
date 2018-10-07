package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.CourseZhiboDao;
import io.renren.modules.sys.entity.CourseZhiboEntity;
import io.renren.modules.sys.service.CourseZhiboService;


@Service("courseZhiboService")
public class CourseZhiboServiceImpl extends ServiceImpl<CourseZhiboDao, CourseZhiboEntity> implements CourseZhiboService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String courseNo = (String)params.get("courseNo");
        String courseName = (String)params.get("courseName");
        String courseTeacher = (String)params.get("courseTeacher");
        String publishTime = (String)params.get("publishTime");
        Page<CourseZhiboEntity> page = this.selectPage(
                new Query<CourseZhiboEntity>(params).getPage(),
                new EntityWrapper<CourseZhiboEntity>()
                        .like(StringUtils.isNotBlank(courseNo),"course_no", courseNo)
                        .like(StringUtils.isNotBlank(courseName),"course_name", courseName)
                        .like(StringUtils.isNotBlank(courseTeacher),"course_teacher", courseTeacher)
                        .like(StringUtils.isNotBlank(publishTime),"publish_time", publishTime)
        );

        return new PageUtils(page);
    }

}
