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

import io.renren.modules.sys.entity.OrderRefundEntity;
import io.renren.modules.sys.service.OrderRefundService;
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
@RequestMapping("sys/orderrefund")
public class OrderRefundController {
    @Autowired
    private OrderRefundService orderRefundService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:orderrefund:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderRefundService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:orderrefund:info")
    public R info(@PathVariable("id") String id){
        OrderRefundEntity orderRefund = orderRefundService.selectById(id);

        return R.ok().put("orderRefund", orderRefund);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:orderrefund:save")
    public R save(@RequestBody OrderRefundEntity orderRefund){
        orderRefundService.insert(orderRefund);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:orderrefund:update")
    public R update(@RequestBody OrderRefundEntity orderRefund){
        ValidatorUtils.validateEntity(orderRefund);
        orderRefundService.updateAllColumnById(orderRefund);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:orderrefund:delete")
    public R delete(@RequestBody String[] ids){
        orderRefundService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
