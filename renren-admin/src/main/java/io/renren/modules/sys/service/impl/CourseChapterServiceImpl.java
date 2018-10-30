package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
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
        String chapterNo = (String)params.get("chapterNo");
        String chapterName = (String)params.get("chapterName");
        String chapterTeacher = (String)params.get("chapterTeacher");
        String chapterType = (String)params.get("chapterType");
        String chapterStatus = (String)params.get("chapterStatus");
        Page<CourseChapterEntity> page = this.selectPage(
                new Query<CourseChapterEntity>(params).getPage(),
                new EntityWrapper<CourseChapterEntity>()
                        .like(StringUtils.isNotBlank(chapterNo),"chapter_no", chapterNo)
                        .like(StringUtils.isNotBlank(chapterName),"chapter_name", chapterName)
                        .like(StringUtils.isNotBlank(chapterTeacher),"chapter_teacher", chapterTeacher)
                        .like(StringUtils.isNotBlank(chapterType),"chapter_type", chapterType)
                        .like(StringUtils.isNotBlank(chapterStatus),"chapter_Status", chapterStatus)
                        .orderBy("create_time",false)
        );

        return new PageUtils(page);
    }

}
