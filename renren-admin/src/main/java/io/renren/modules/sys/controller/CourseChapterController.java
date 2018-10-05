package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.common.utils.NoUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.CourseInfoEntity;
import io.renren.modules.sys.service.CourseInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.CourseChapterEntity;
import io.renren.modules.sys.service.CourseChapterService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-27 11:35:10
 */
@RestController
@RequestMapping("sys/coursechapter")
public class CourseChapterController {
    @Autowired
    private CourseChapterService courseChapterService;

    @Autowired
    private CourseInfoService courseInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:coursechapter:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseChapterService.queryPage(params);
        page.getList().forEach(o->{
            CourseChapterEntity courseChapterEntity = (CourseChapterEntity)o;
            courseChapterEntity.setCourseName(courseInfoService.selectById(courseChapterEntity.getCourseId()).getCourseName());
        });
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:coursechapter:info")
    public R info(@PathVariable("id") Integer id){
        CourseChapterEntity courseChapter = courseChapterService.selectById(id);

        return R.ok().put("courseChapter", courseChapter);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:coursechapter:save")
    public R save(@RequestBody CourseChapterEntity courseChapter){
        courseChapter.setCreateTime(new Date());
        courseChapter.setChapterNo(NoUtils.genOrderNo());
        courseChapterService.insert(courseChapter);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:coursechapter:update")
    public R update(@RequestBody CourseChapterEntity courseChapter){
        ValidatorUtils.validateEntity(courseChapter);
        courseChapterService.updateAllColumnById(courseChapter);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:coursechapter:delete")
    public R delete(@RequestBody Integer[] ids){
        courseChapterService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
