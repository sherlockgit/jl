package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.ProjectEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.omg.CORBA.OBJ_ADAPTER;

import java.util.List;
import java.util.Map;

/**
 * 项目信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
public interface ProjectDao extends BaseMapper<ProjectEntity> {

    List<ProjectEntity> getProjectByType(Map<String, Object> map);

    ProjectEntity getProjectByDetail(String proId);

    List<String> getProjectNameList ();

    List<ProjectEntity> getProjectByNameList ();

    List<ProjectEntity> getDestineByUser(Map<String,Object> map);
}
