package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.UserCneeEntity;

import java.util.Map;

/**
 * 会员收货人表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-08 17:26:29
 */
public interface UserCneeService extends IService<UserCneeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

