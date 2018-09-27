package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.AccountEntity;
import io.renren.modules.sys.vo.AccountVO;

import java.util.Map;

/**
 * 会员账户表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
public interface AccountService extends IService<AccountEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取账户列表
     * @param params
     * @return
     */
    R getAccountInfoList (Map<String, Object> params);

    /**
     * 账户充值
     * @param accountVO
     * @return
     */
    R addAccount (AccountVO accountVO);

    /**
     * 获取账户详情
     * @param accountId
     * @return
     */
    R getAccountDetail (String accountId);
}

