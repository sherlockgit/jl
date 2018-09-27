package io.renren.modules.sys.api;

import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.sys.entity.ProjectEntity;
import io.renren.modules.sys.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 小宇宙
 * @Date: 2018/6/11
 */
@RequestMapping("/apiProject")
@RestController
public class ApiProjectController {

    @Autowired
    ProjectService projectService;

    /**
     * 项目列表
     * @param proType
     * @param page
     * @return
     */
    @GetMapping("/getProjectByType")
    public R getProjectByType (@RequestParam(value = "proType",required = false,defaultValue = "")String proType,
                               @RequestParam(value = "page",required = false,defaultValue = "1")Integer page) {

        Map<String,Object> map = new HashMap<>();
        map.put("proType",proType);
        map.put("page",page);

        return projectService.getProjectByType(map);
    }

    /**
     * 项目详情
     * @param proId
     * @return
     */
    @GetMapping("/getProjectDetail")
    public R getProjectDetail (@RequestParam(value = "proId",defaultValue = "")String proId) {
        Assert.isBlank(proId,"项目ID不能为空");
        return projectService.getProjectByDetail(proId);
    }

    /**
     * 获取项目名称列表
     * @return
     */
    @RequestMapping("/getProjectByNameList")
    public R getProjectByNameList(){
        return projectService.getProjectByNameList();
    }

    /**
     * 获取用户预约信息
     * @param userId
     * @return
     */
    @GetMapping("/getDestineByUser")
    public R getDestineByUser(@RequestParam(value = "userId",defaultValue = "")String userId,
                              @RequestParam(value = "destineStatus",defaultValue = "")String destineStatus){
        Assert.isBlank(userId,"用户ID不能为空");
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("destineStatus",destineStatus);
        return projectService.getDestineByUser(map);
    }
}
