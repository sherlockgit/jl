package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.AccountItemEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.vo.AccountItemVO;

import java.util.List;
import java.util.Map;

/**
 * 会员账户明细表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
public interface AccountItemDao extends BaseMapper<AccountItemEntity> {
    List<AccountItemEntity> getAccountItemByUserId (String userId);

    List<AccountItemVO> getAccountItemAll (Map<String, Object> params);

    Integer selectCountItemAll(Map<String, Object> params);

    List<AccountItemEntity> getAccountByMonth(Map<String, Object> params);
}
