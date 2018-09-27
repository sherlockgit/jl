package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.CourseChapterDao;
import io.renren.modules.sys.entity.CourseChapterEntity;
import io.renren.modules.sys.service.CourseChapterService;


@Service("courseChapterService")
public class CourseChapterServiceImpl extends ServiceImpl<CourseChapterDao, CourseChapterEntity> implements CourseChapterService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CourseChapterEntity> page = this.selectPage(
                new Query<CourseChapterEntity>(params).getPage(),
                new EntityWrapper<CourseChapterEntity>()
        );

        return new PageUtils(page);
    }

}
