package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import io.renren.common.utils.R;
import io.renren.common.utils.UUIDUtils;
import io.renren.modules.sys.dao.AccountItemDao;
import io.renren.modules.sys.entity.AccountItemEntity;
import io.renren.modules.sys.service.AccountItemService;
import io.renren.modules.sys.vo.AccountVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.AccountDao;
import io.renren.modules.sys.entity.AccountEntity;
import io.renren.modules.sys.service.AccountService;


@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountDao, AccountEntity> implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    AccountItemService accountItemService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String userName = (String)params.get("userName");
        String phone = (String)params.get("phone");
        String balance = (String)params.get("balance");
        String updateTime = (String)params.get("updateTime");

        Page<AccountEntity> page = this.selectPage(
                new Query<AccountEntity>(params).getPage(),
                new EntityWrapper<AccountEntity>()
                        .gt("BALANCE",balance)
                        .gt("UPDATE_TIME",updateTime)

        );

        return new PageUtils(page);
    }

    /**
     * 获取账户列表
     * @param params
     * @return
     */
    @Override
    public R getAccountInfoList(Map<String, Object> params) {

        Integer limit = Integer.parseInt((String)params.get("limit"));
        Integer page = Integer.parseInt((String)params.get("page"));
        Integer pagesSelect = (page-1)*limit;
        params.put("limit",limit);
        params.put("page",pagesSelect);
        PageUtils pageUtils = new PageUtils(accountDao.getAccountInfoList(params),accountDao.selectCount(params),limit,page);
        return R.ok().put("page",pageUtils);
    }

    /**
     * 充值
     * @param accountVO
     * @return
     */
    @Override
    public R addAccount(AccountVO accountVO) {

        /*更新账户*/
        String accountId = accountVO.getAccountId();
        AccountEntity account = selectById(accountId);
        account.setTotalIn(account.getTotalIn().add(accountVO.getTotalInNow()));
        account.setBalance(accountVO.getTotalInNow().add(account.getBalance()));
        account.setUpdateTime(new Date());

        /*添加账户详情*/
        AccountItemEntity accountItemEntity = new AccountItemEntity();
        accountItemEntity.setItemId(UUIDUtils.getUUID());
        accountItemEntity.setUserId(account.getUserId());
        accountItemEntity.setAmtIn(accountVO.getTotalInNow());
        accountItemEntity.setBalance(account.getBalance());
        accountItemEntity.setTranStatus("1");
        accountItemEntity.setCreateTime(new Date());
        accountItemEntity.setMemo(accountVO.getMemo());

        updateById(account);
        accountItemService.insert(accountItemEntity);
        return R.ok();
    }

    /**
     * 获取账户详情
     * @param accountId
     * @return
     */
    @Override
    public R getAccountDetail(String accountId) {

        return R.ok().put("account",accountDao.getAccountDetail(accountId));
    }

}
