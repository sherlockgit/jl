package io.renren.modules.sys.service.impl;

import io.renren.common.utils.R;
import io.renren.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.BannerDao;
import io.renren.modules.sys.entity.BannerEntity;
import io.renren.modules.sys.service.BannerService;


@Service("bannerService")
public class BannerServiceImpl extends ServiceImpl<BannerDao, BannerEntity> implements BannerService {

    @Autowired
    BannerDao bannerDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BannerEntity> page = this.selectPage(
                new Query<BannerEntity>(params).getPage(),
                new EntityWrapper<BannerEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 新增轮播图
     * @param userInfoEntity
     * @return
     */
    @Override
    public R insertBanner(BannerEntity userInfoEntity) {

        if (bannerDao.selectBySort(userInfoEntity.getSort()) != null) {
            return R.error("该排序已存在");
        }

        userInfoEntity.setBannerId(UUIDUtils.getUUID());
        userInfoEntity.setCreateTime(new Date());
        insert(userInfoEntity);
        return R.ok();
    }

    /**
     * 更新轮轮播图
     * @param userInfoEntity
     * @return
     */
    @Override
    public R updateBannner(BannerEntity userInfoEntity) {

        Integer sort = selectById(userInfoEntity.getBannerId()).getSort();

        if (userInfoEntity.getSort() != sort) {
            if (bannerDao.selectBySort(userInfoEntity.getSort()) != null) {
                return R.error("该排序已存在");
            }
        }

        updateById(userInfoEntity);
        return R.ok();
    }

    /**
     * banner列表
     * @return
     */
    @Override
    public R selectBannerList() {
        return R.ok().put("data",bannerDao.selectBannerList());
    }

}
