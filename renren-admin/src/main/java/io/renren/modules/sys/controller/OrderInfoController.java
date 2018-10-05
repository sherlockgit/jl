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

import io.renren.modules.sys.entity.OrderInfoEntity;
import io.renren.modules.sys.service.OrderInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 00:57:48
 */
@RestController
@RequestMapping("sys/orderinfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:orderinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:orderinfo:info")
    public R info(@PathVariable("id") Integer id){
        OrderInfoEntity orderInfo = orderInfoService.selectById(id);

        return R.ok().put("orderInfo", orderInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:orderinfo:save")
    public R save(@RequestBody OrderInfoEntity orderInfo){
        orderInfoService.insert(orderInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:orderinfo:update")
    public R update(@RequestBody OrderInfoEntity orderInfo){
        ValidatorUtils.validateEntity(orderInfo);
        orderInfoService.updateAllColumnById(orderInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:orderinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        orderInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
