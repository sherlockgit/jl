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

import io.renren.modules.sys.entity.AccountItemEntity;
import io.renren.modules.sys.service.AccountItemService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 会员账户明细表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
@RestController
@RequestMapping("sys/accountitem")
public class AccountItemController {
    @Autowired
    private AccountItemService accountItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:accountitem:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = accountItemService.getAccountItemByUserIdPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 所有用户明细列表
     * @param params
     * @return
     */
    @RequestMapping("/listAll")
    @RequiresPermissions("sys:accountitem:list")
    public R listAll(@RequestParam Map<String, Object> params) {
        return accountItemService.getAccountItemAll(params);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{itemId}")
    @RequiresPermissions("sys:accountitem:info")
    public R info(@PathVariable("itemId") String itemId){
        AccountItemEntity accountItem = accountItemService.selectById(itemId);

        return R.ok().put("accountItem", accountItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:accountitem:save")
    public R save(@RequestBody AccountItemEntity accountItem){
        accountItemService.insert(accountItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:accountitem:update")
    public R update(@RequestBody AccountItemEntity accountItem){
        ValidatorUtils.validateEntity(accountItem);
        accountItemService.updateAllColumnById(accountItem);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:accountitem:delete")
    public R delete(@RequestBody String[] itemIds){
        accountItemService.deleteBatchIds(Arrays.asList(itemIds));

        return R.ok();
    }

}
