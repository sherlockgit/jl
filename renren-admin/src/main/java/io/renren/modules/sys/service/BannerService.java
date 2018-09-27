package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.entity.BannerEntity;

import java.util.Map;

/**
 * 轮播图表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
public interface BannerService extends IService<BannerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R insertBanner(BannerEntity userInfoEntity);

    R updateBannner(BannerEntity userInfoEntity);

    R selectBannerList();
}

