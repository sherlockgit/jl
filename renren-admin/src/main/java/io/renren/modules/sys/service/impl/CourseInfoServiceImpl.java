package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.CourseInfoDao;
import io.renren.modules.sys.entity.CourseInfoEntity;
import io.renren.modules.sys.service.CourseInfoService;


@Service("courseInfoService")
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoDao, CourseInfoEntity> implements CourseInfoService {

    @Autowired
    private CourseInfoDao dao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) throws ParseException {
        String courseNo = (String)params.get("courseNo");
        String courseName = (String)params.get("courseName");
        String courseTeacher = (String)params.get("courseTeacher");
        String courseType = (String)params.get("courseType");
        String courseStatus = (String)params.get("courseStatus");
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
        Page<CourseInfoEntity> page = this.selectPage(
                new Query<CourseInfoEntity>(params).getPage(),
                new EntityWrapper<CourseInfoEntity>()
                        .like(StringUtils.isNotBlank(courseNo),"course_no", courseNo)
                        .like(StringUtils.isNotBlank(courseName),"course_name", courseName)
                        .like(StringUtils.isNotBlank(courseTeacher),"course_teacher", courseTeacher)
                        .like(StringUtils.isNotBlank(courseType),"course_type", courseType)
                        .like(StringUtils.isNotBlank(courseStatus),"course_status", courseStatus)
                        .gt(StringUtils.isNotBlank(startTime),"create_time",startTimeDate)
                        .lt(StringUtils.isNotBlank(endTime),"create_time", endTimeDate)
                        .orderBy("create_time",false)
        );

        return new PageUtils(page);
    }

    @Override
    public List<CourseInfoEntity> getCourseNameList() {
        return dao.selectAll();
    }

}
