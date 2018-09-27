package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.AccountEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.vo.AccountVO;

import java.util.List;
import java.util.Map;

/**
 * 会员账户表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
public interface AccountDao extends BaseMapper<AccountEntity> {

    List<AccountVO> getAccountInfoList(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);

    AccountVO getAccountDetail(String accountId);

    AccountVO getBalanceByUserId(String userId);
}
