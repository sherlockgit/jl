package io.renren.modules.sys.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.common.utils.NoUtils;
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

import io.renren.modules.sys.entity.CourseZhiboEntity;
import io.renren.modules.sys.service.CourseZhiboService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 22:02:06
 */
@RestController
@RequestMapping("sys/coursezhibo")
public class CourseZhiboController {
    @Autowired
    private CourseZhiboService courseZhiboService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:coursezhibo:list")
    public R list(@RequestParam Map<String, Object> params) throws ParseException {
        PageUtils page = courseZhiboService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:coursezhibo:info")
    public R info(@PathVariable("id") Integer id){
        CourseZhiboEntity courseZhibo = courseZhiboService.selectById(id);

        return R.ok().put("courseZhibo", courseZhibo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:coursezhibo:save")
    public R save(@RequestBody CourseZhiboEntity courseZhibo){
        ValidatorUtils.validateEntity(courseZhibo, AddGroup.class);

        courseZhibo.setCourseNo(NoUtils.genOrderNo());
        courseZhibo.setCreateTime(new Date());
        courseZhiboService.insert(courseZhibo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:coursezhibo:update")
    public R update(@RequestBody CourseZhiboEntity courseZhibo){
        ValidatorUtils.validateEntity(courseZhibo, UpdateGroup.class);
        ValidatorUtils.validateEntity(courseZhibo);
        courseZhiboService.updateAllColumnById(courseZhibo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:coursezhibo:delete")
    public R delete(@RequestBody Integer[] ids){
        courseZhiboService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
