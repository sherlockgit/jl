package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CourseInfoEntity> page = this.selectPage(
                new Query<CourseInfoEntity>(params).getPage(),
                new EntityWrapper<CourseInfoEntity>()
        );

        return new PageUtils(page);
    }

}
