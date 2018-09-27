package io.renren.modules.sys.service.impl;

import io.renren.common.utils.R;
import io.renren.common.utils.UUIDUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.DestineDao;
import io.renren.modules.sys.entity.DestineEntity;
import io.renren.modules.sys.service.DestineService;


@Service("destineService")
public class DestineServiceImpl extends ServiceImpl<DestineDao, DestineEntity> implements DestineService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String uname = (String)params.get("uname");
        String phone = (String)params.get("phone");
        String proType = (String)params.get("proType");
        String proName = (String)params.get("proName");
        Page<DestineEntity> page = this.selectPage(
                new Query<DestineEntity>(params).getPage(),
                new EntityWrapper<DestineEntity>()
                        .like(StringUtils.isNotBlank(uname),"UNAME", uname)
                        .like(StringUtils.isNotBlank(phone),"PHONE", phone)
                        .like(StringUtils.isNotBlank(proType),"PRO_TYPE", proType)
                        .like(StringUtils.isNotBlank(proName),"PRO_NAME", proName)
                        .orderBy("CREATE_TIME",false)
        );

        return new PageUtils(page);
    }

    @Override
    public R insertDestince(DestineEntity destineEntity) {
        destineEntity.setDestineId(UUIDUtils.getUUID());
        destineEntity.setCreateTime(new Date());
        insert(destineEntity);
        return R.ok();
    }

}
