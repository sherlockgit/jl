package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.UserInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 会员信息表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-05 21:47:20
 */
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {

    String getByPhone(String phone);

    String getByWXName(String wxUname);

    UserInfoEntity getOpenId(String wxOpenid);
}
