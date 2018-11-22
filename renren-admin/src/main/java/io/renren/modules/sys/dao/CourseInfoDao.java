package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.CourseInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-04 11:10:26
 */
public interface CourseInfoDao extends BaseMapper<CourseInfoEntity> {

    List<CourseInfoEntity> selectAll();

	
}
