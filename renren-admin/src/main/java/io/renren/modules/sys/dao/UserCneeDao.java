package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.UserCneeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.vo.UserCneeVo;

import java.util.List;
import java.util.Map;

/**
 * 会员收货人表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-08 17:26:29
 */
public interface UserCneeDao extends BaseMapper<UserCneeEntity> {

    List<UserCneeVo> selectAll(Map<String, Object> map);

}
