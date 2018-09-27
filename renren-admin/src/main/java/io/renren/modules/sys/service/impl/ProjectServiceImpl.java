package io.renren.modules.sys.service.impl;

import io.renren.common.utils.R;
import io.renren.common.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ProjectDao;
import io.renren.modules.sys.entity.ProjectEntity;
import io.renren.modules.sys.service.ProjectService;


@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectEntity> implements ProjectService {

    @Autowired
    ProjectDao projectDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String proType = (String)params.get("proType");
        String proName = (String)params.get("proName");
        String proStatus = (String)params.get("proStatus");
        String proLong = (String)params.get("proLong");

        Page<ProjectEntity> page = this.selectPage(
                new Query<ProjectEntity>(params).getPage(),
                new EntityWrapper<ProjectEntity>()
                        .like(StringUtils.isNotBlank(proType),"PRO_TYPE", proType)
                        .like(StringUtils.isNotBlank(proName),"PRO_NAME", proName)
                        .like(StringUtils.isNotBlank(proStatus),"PRO_STATUS", proStatus)
                        .like(StringUtils.isNotBlank(proLong),"PRO_LONG", proLong)
                        .orderBy("CREATE_TIME",false)
        );

        return new PageUtils(page);
    }

    @Override
    public R getProjectByType(Map<String, Object> params) {

        Integer page = (int)params.get("page");
        Integer pagesSelect = (page-1)*10;
        params.put("page",pagesSelect);
        List<ProjectEntity> list = projectDao.getProjectByType(params);
        list = reloadTime(list);
        PageUtils pageUtils = new PageUtils(list,0,0,page);
        return R.ok().put("data", pageUtils);
    }

    /**
     * 新增项目
     * @param projectEntity
     * @return
     */
    @Override
    public R insertProject(ProjectEntity projectEntity) {
        String startTime = projectEntity.getDatetimeStart()+"."+projectEntity.getDateMinStart();
        String endTime = projectEntity.getDatetimeEnd()+"."+projectEntity.getDateMinEnd();
        projectEntity.setBeginTime(Float.valueOf(startTime));
        projectEntity.setEndTime(Float.valueOf(endTime));
        projectEntity.setProId(UUIDUtils.getUUID());
        projectEntity.setCreateTime(new Date());
        insert(projectEntity);
        return R.ok();
    }

    /**
     * 查询项目详情
     * @param proId
     * @return
     */
    @Override
    public R getProjectByDetail(String proId) {
        return R.ok().put("data",projectDao.getProjectByDetail(proId));
    }

    /**
     * 获取项目名称列表
     * @return
     */
    @Override
    public R getProjectNameList() {
        return R.ok().put("data",projectDao.getProjectNameList());

    }

    public R getProjectByNameList(){
        List<ProjectEntity> list = projectDao.getProjectByNameList();
        list = reloadTime(list);
        return R.ok().put("data",list);
    }

    @Override
    public R getDestineByUser(Map<String,Object> map) {
        return R.ok().put("data",projectDao.getDestineByUser(map));
    }

    private List<ProjectEntity> reloadTime(List<ProjectEntity> list){
        list.forEach(project->{
            Float startTime = project.getBeginTime();
            Float endTime = project.getEndTime();
            Integer StartTimeInt = startTime.intValue();
            Float f = (startTime-StartTimeInt)*100;
            Integer StartTimeMinInt = f.intValue();
            if (StartTimeMinInt == 0) {
                project.setDatetimeStart(StartTimeInt.toString()+":"+StartTimeMinInt.toString()+0);
            }else {
                project.setDatetimeStart(StartTimeInt.toString()+":"+StartTimeMinInt.toString());
            }


            Integer EndTimeInt = endTime.intValue();
            Float fe = (endTime-EndTimeInt)*100;
            Integer EndTimeMinInt = fe.intValue();
            if (EndTimeMinInt == 0) {
                project.setDatetimeEnd(EndTimeInt.toString()+":"+EndTimeMinInt.toString()+0);
            }else {
                project.setDatetimeEnd(EndTimeInt.toString()+":"+EndTimeMinInt.toString());
            }
            project.setBeginTime(null);
            project.setEndTime(null);
        });
        return list;
    }

}
