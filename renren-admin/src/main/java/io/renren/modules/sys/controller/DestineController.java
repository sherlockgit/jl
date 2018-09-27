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

import io.renren.modules.sys.entity.DestineEntity;
import io.renren.modules.sys.service.DestineService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 项目预约表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
@RestController
@RequestMapping("sys/destine")
public class DestineController {
    @Autowired
    private DestineService destineService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:destine:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = destineService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{destineId}")
    @RequiresPermissions("sys:destine:info")
    public R info(@PathVariable("destineId") String destineId){
        DestineEntity destine = destineService.selectById(destineId);

        return R.ok().put("destine", destine);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:destine:save")
    public R save(@RequestBody DestineEntity destine){
        ValidatorUtils.validateEntity(destine, AddGroup.class);
        return destineService.insertDestince(destine);

    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:destine:update")
    public R update(@RequestBody DestineEntity destine){
        ValidatorUtils.validateEntity(destine, UpdateGroup.class);
        destineService.updateAllColumnById(destine);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:destine:delete")
    public R delete(@RequestBody String[] destineIds){
        destineService.deleteBatchIds(Arrays.asList(destineIds));

        return R.ok();
    }

}
