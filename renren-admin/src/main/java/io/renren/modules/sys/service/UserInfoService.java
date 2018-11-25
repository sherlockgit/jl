package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.UserInfoEntity;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 会员信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-05 21:47:20
 */
public interface UserInfoService extends IService<UserInfoEntity> {

    PageUtils queryPage(Map<String, Object> params) throws ParseException;

    R insertUserInfo(UserInfoEntity userInfoEntity);

    R updateUserInfo(UserInfoEntity userInfoEntity);

    R appLogin(UserInfoEntity userInfoEntity);

    List<UserInfoEntity> getExcel(Map<String, Object> params) throws ParseException;
}

