package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.UserCneeEntity;
import io.renren.modules.sys.service.UserCneeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 会员收货人表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-08 17:26:29
 */
@RestController
@RequestMapping("sys/usercnee")
public class UserCneeController {
    @Autowired
    private UserCneeService userCneeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:usercnee:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userCneeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:usercnee:info")
    public R info(@PathVariable("id") Long id){
        UserCneeEntity userCnee = userCneeService.selectById(id);

        return R.ok().put("userCnee", userCnee);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:usercnee:save")
    public R save(@RequestBody UserCneeEntity userCnee){
        userCneeService.insert(userCnee);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:usercnee:update")
    public R update(@RequestBody UserCneeEntity userCnee){
        ValidatorUtils.validateEntity(userCnee);
        userCneeService.updateAllColumnById(userCnee);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:usercnee:delete")
    public R delete(@RequestBody Long[] ids){
        userCneeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
