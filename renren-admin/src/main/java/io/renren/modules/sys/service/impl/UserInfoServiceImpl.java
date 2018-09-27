package io.renren.modules.sys.service.impl;

import io.renren.common.utils.*;
import io.renren.modules.sys.dao.AccountDao;
import io.renren.modules.sys.entity.AccountEntity;
import io.renren.modules.sys.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.renren.modules.sys.dao.UserInfoDao;
import io.renren.modules.sys.entity.UserInfoEntity;
import io.renren.modules.sys.service.UserInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountDao accountDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String userName = (String)params.get("userName");
        String phone = (String)params.get("phone");
        String wxUname = (String)params.get("wxUname");
        String userType = (String)params.get("userType");

        Page<UserInfoEntity> page = this.selectPage(
                new Query<UserInfoEntity>(params).getPage(),
                new EntityWrapper<UserInfoEntity>()
                        .like(StringUtils.isNotBlank(userName),"USER_NAME", userName)
                        .like(StringUtils.isNotBlank(phone),"PHONE", phone)
                        .like(StringUtils.isNotBlank(wxUname),"WX_UNAME", wxUname)
                        .like(StringUtils.isNotBlank(userType),"USER_TYPE", userType)
                        .orderBy("REGIST_TIME",false)
        );

        return new PageUtils(page);
    }

    /**
     * 新增用户
     * @param userInfoEntity
     * @return
     */
    @Override
    public R insertUserInfo(UserInfoEntity userInfoEntity) {

        if ( userInfoDao.getByPhone(userInfoEntity.getPhone()) != null) {
            return R.error("该手机号码已经存在");
        }

        if ( userInfoDao.getByWXName(userInfoEntity.getWxUname()) != null) {
            return R.error("该微信号已经存在");
        }

        /*构建用户信息*/
        String userId = UUIDUtils.getUUID();
        userInfoEntity.setUserId(userId);
        userInfoEntity.setRegistTime(new Date());
        userInfoEntity.setUserNo(NoUtils.genOrderNo());


        insert(userInfoEntity);
        return R.ok();
    }

    /**
     * 更新会员
     * @param userInfoEntity
     * @return
     */
    @Override
    public R updateUserInfo(UserInfoEntity userInfoEntity) {

        UserInfoEntity userInfo = selectById(userInfoEntity.getUserId());

        if (!(userInfoEntity.getPhone().equals(userInfo.getPhone()))) {
            if ( userInfoDao.getByPhone(userInfoEntity.getPhone()) != null) {
                return R.error("该手机号码已经存在");
            }
        }

        if (!(userInfoEntity.getWxUname().equals(userInfo.getWxUname()))) {
            if ( userInfoDao.getByWXName(userInfoEntity.getWxUname()) != null) {
                return R.error("该微信号已经存在");
            }
        }

        updateById(userInfoEntity);
        return R.ok();
    }

    /**
     * 登陆
     * @param
     * @return
     */
    @Override
    public R appLogin(UserInfoEntity userInfoEntity) {

        UserInfoEntity wxOpenid = userInfoDao.getOpenId(userInfoEntity.getWxOpenid());
        if (wxOpenid == null) {
            String userId = UUIDUtils.getUUID();
            userInfoEntity.setUserId(userId);
            userInfoEntity.setUserNo(NoUtils.genOrderNo());
            userInfoEntity.setUserType("2");
            userInfoEntity.setRegistTime(new Date());

            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setAccountId(UUIDUtils.getUUID());
            accountEntity.setUserId(userId);
            accountEntity.setUpdateTime(new Date());

            accountService.insert(accountEntity);
            insert(userInfoEntity);
            userInfoEntity.setRegistTime(null);
            userInfoEntity.setBalance(new BigDecimal(0));
            return R.ok().put("data",userInfoEntity);
        }
        wxOpenid.setBalance(accountDao.getBalanceByUserId(wxOpenid.getUserId()).getBalance());
        return R.ok().put("data",wxOpenid);
    }
}
