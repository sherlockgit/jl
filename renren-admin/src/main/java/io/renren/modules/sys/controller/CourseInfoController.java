package io.renren.modules.sys.controller;

import java.text.ParseException;
import java.util.*;

import io.renren.common.utils.NoUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.entity.CourseChapterEntity;
import io.renren.modules.sys.service.CourseChapterService;
import io.renren.modules.sys.vo.CourseInfoEntityVo;
import io.renren.modules.sys.vo.CourseNameList;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.CourseInfoEntity;
import io.renren.modules.sys.service.CourseInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-04 11:10:26
 */
@RestController
@RequestMapping("sys/courseinfo")
public class CourseInfoController {
    @Autowired
    private CourseInfoService courseInfoService;

    @Autowired
    private CourseChapterService courseChapterService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:courseinfo:list")
    public R list(@RequestParam Map<String, Object> params) throws ParseException {
        PageUtils page = courseInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有列表
     */
    @RequestMapping("/getCourseNameList")
    public R getCourseNameList(){
        List<CourseInfoEntity> list = courseInfoService.getCourseNameList();

        List<CourseNameList> lists = new ArrayList<>();
        list.forEach(o->{
                CourseNameList courseNameList = new CourseNameList();
                courseNameList.setId(o.getId().toString());
                courseNameList.setCourseName(o.getCourseName());
                lists.add(courseNameList);
        });
        return R.ok().put("list", lists);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:courseinfo:info")
    public R info(@PathVariable("id") Integer id){
        CourseInfoEntity courseInfo = courseInfoService.selectById(id);

        return R.ok().put("courseInfo", courseInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:courseinfo:save")
    public R save(@RequestBody CourseInfoEntity courseInfo){
        ValidatorUtils.validateEntity(courseInfo, AddGroup.class);

        courseInfo.setCourseNo(NoUtils.genOrderNo());
        courseInfo.setCreateTime(new Date());
        if ("1".equals(courseInfo.getCourseStatus())) {
            courseInfo.setPublishTime(new Date());
        }
        courseInfoService.insert(courseInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:courseinfo:update")
    public R update(@RequestBody CourseInfoEntity courseInfo){
        ValidatorUtils.validateEntity(courseInfo, UpdateGroup.class);
        ValidatorUtils.validateEntity(courseInfo);
        CourseInfoEntity courseInfoEntity = courseInfoService.selectById(courseInfo.getId());
        if (!"1".equals(courseInfoEntity.getCourseStatus())) {
            if ("1".equals(courseInfo.getCourseStatus())) {
                courseInfo.setPublishTime(new Date());
            }
        }
        courseInfoService.updateAllColumnById(courseInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:courseinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        courseInfoService.deleteBatchIds(Arrays.asList(ids));
        for (int i = 0;i<ids.length;i++) {
            courseChapterService.deletrByCourseInfoId(ids[i]);
        }
        return R.ok();
    }

}
