package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.AccountItemEntity;

import java.util.Map;

/**
 * 会员账户明细表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
public interface AccountItemService extends IService<AccountItemEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils getAccountItemByUserIdPage (Map<String, Object> params);

    /**
     * 获取明细列表
     * @param params
     * @return
     */
    R getAccountItemAll (Map<String, Object> params);

    R getAccountByMonth (Map<String, Object> params);
}

