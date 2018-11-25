package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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

    @Autowired
    CourseChapterDao courseChapterDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) throws ParseException {
        String chapterNo = (String)params.get("chapterNo");
        String chapterName = (String)params.get("chapterName");
        String chapterTeacher = (String)params.get("chapterTeacher");
        String chapterType = (String)params.get("chapterType");
        String chapterStatus = (String)params.get("chapterStatus");
        String startTime = (String)params.get("startTime");
        String endTime = (String)params.get("endTime");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        Date startTimeDate = null;
        Date endTimeDate = null;
        if (StringUtils.isNotBlank(startTime)) {
            startTimeDate = sdf.parse(startTime.replace("GMT", "").replaceAll("\\(.*\\)", ""));
        }
        if (StringUtils.isNotBlank(endTime)) {
            endTimeDate = sdf.parse(endTime.replace("GMT", "").replaceAll("\\(.*\\)", ""));
        }
        Page<CourseChapterEntity> page = this.selectPage(
                new Query<CourseChapterEntity>(params).getPage(),
                new EntityWrapper<CourseChapterEntity>()
                        .like(StringUtils.isNotBlank(chapterNo),"chapter_no", chapterNo)
                        .like(StringUtils.isNotBlank(chapterName),"chapter_name", chapterName)
                        .like(StringUtils.isNotBlank(chapterTeacher),"chapter_teacher", chapterTeacher)
                        .like(StringUtils.isNotBlank(chapterType),"chapter_type", chapterType)
                        .like(StringUtils.isNotBlank(chapterStatus),"chapter_Status", chapterStatus)
                        .gt(StringUtils.isNotBlank(startTime),"create_time",startTimeDate)
                        .lt(StringUtils.isNotBlank(endTime),"create_time", endTimeDate)
                        .orderBy("create_time",false)
        );

        return new PageUtils(page);
    }

    @Override
    public void deletrByCourseInfoId(Integer id) {
        courseChapterDao.deletrByCourseInfoId(id);
    }

}
