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

import io.renren.modules.sys.entity.BannerEntity;
import io.renren.modules.sys.service.BannerService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 轮播图表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
@RestController
@RequestMapping("sys/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:banner:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bannerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{bannerId}")
    @RequiresPermissions("sys:banner:info")
    public R info(@PathVariable("bannerId") String bannerId){
        BannerEntity banner = bannerService.selectById(bannerId);

        return R.ok().put("banner", banner);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:banner:save")
    public R save(@RequestBody BannerEntity banner){
        ValidatorUtils.validateEntity(banner, AddGroup.class);
        return bannerService.insertBanner(banner);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:banner:update")
    public R update(@RequestBody BannerEntity banner){
        ValidatorUtils.validateEntity(banner, UpdateGroup.class);
        return bannerService.updateBannner(banner);//全部更新
        
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:banner:delete")
    public R delete(@RequestBody String[] bannerIds){
        bannerService.deleteBatchIds(Arrays.asList(bannerIds));

        return R.ok();
    }

}
