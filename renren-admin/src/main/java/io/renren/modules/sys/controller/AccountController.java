package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.vo.AccountVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.AccountEntity;
import io.renren.modules.sys.service.AccountService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 会员账户表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
@RestController
@RequestMapping("sys/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:account:list")
    public R list(@RequestParam Map<String, Object> params){
        return accountService.getAccountInfoList(params);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{accountId}")
    @RequiresPermissions("sys:account:info")
    public R info(@PathVariable("accountId") String accountId){

        return accountService.getAccountDetail(accountId);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:account:save")
    public R save(@RequestBody AccountEntity account){
        accountService.insert(account);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:account:update")
    public R update(@RequestBody AccountVO accountVO){
        ValidatorUtils.validateEntity(accountVO, UpdateGroup.class);

        return accountService.addAccount(accountVO);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:account:delete")
    public R delete(@RequestBody String[] accountIds){
        accountService.deleteBatchIds(Arrays.asList(accountIds));

        return R.ok();
    }

}
