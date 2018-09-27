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

import io.renren.modules.sys.entity.InvoiceInfoEntity;
import io.renren.modules.sys.service.InvoiceInfoService;
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
@RequestMapping("sys/invoiceinfo")
public class InvoiceInfoController {
    @Autowired
    private InvoiceInfoService invoiceInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:invoiceinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = invoiceInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:invoiceinfo:info")
    public R info(@PathVariable("id") Integer id){
        InvoiceInfoEntity invoiceInfo = invoiceInfoService.selectById(id);

        return R.ok().put("invoiceInfo", invoiceInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:invoiceinfo:save")
    public R save(@RequestBody InvoiceInfoEntity invoiceInfo){
        invoiceInfoService.insert(invoiceInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:invoiceinfo:update")
    public R update(@RequestBody InvoiceInfoEntity invoiceInfo){
        ValidatorUtils.validateEntity(invoiceInfo);
        invoiceInfoService.updateAllColumnById(invoiceInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:invoiceinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        invoiceInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
