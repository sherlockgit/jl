package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.ProjectEntity;
import io.renren.modules.sys.service.ProjectService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 项目信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
@RestController
@RequestMapping("sys/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:project:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = projectService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{proId}")
    @RequiresPermissions("sys:project:info")
    public R info(@PathVariable("proId") String proId){
        ProjectEntity project = projectService.selectById(proId);
        Float startTime = project.getBeginTime();
        Float endTime = project.getEndTime();
        Integer StartTimeInt = startTime.intValue();
        Float f = (startTime-StartTimeInt)*100;
        Integer StartTimeMinInt = f.intValue();
        if (StartTimeMinInt == 0) {
            project.setDateMinStart(StartTimeMinInt.toString()+"0");
        }else {
            project.setDateMinStart(StartTimeMinInt.toString());
        }
        project.setDatetimeStart(StartTimeInt.toString());

        Integer EndTimeInt = endTime.intValue();
        Float fe = (endTime-EndTimeInt)*100;
        Integer EndTimeMinInt = fe.intValue();

        if (EndTimeMinInt == 0) {
            project.setDateMinEnd(EndTimeMinInt.toString()+"0");
        }else {
            project.setDateMinEnd(EndTimeMinInt.toString());
        }
        project.setDatetimeEnd(EndTimeInt.toString());
        return R.ok().put("project", project);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:project:save")
    public R save(@RequestBody ProjectEntity project){
        ValidatorUtils.validateEntity(project, AddGroup.class);

        return projectService.insertProject(project);


    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:project:update")
    public R update(@RequestBody ProjectEntity project){
        ValidatorUtils.validateEntity(project,UpdateGroup.class);
        projectService.updateAllColumnById(project);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:project:delete")
    public R delete(@RequestBody String[] proIds){
        projectService.deleteBatchIds(Arrays.asList(proIds));

        return R.ok();
    }

    /**
     * 获取项目名称列表
     * @return
     */
    @RequestMapping("/getProjectNameList")
    public R getProjectNameList(){
        return projectService.getProjectNameList();
    }
}
