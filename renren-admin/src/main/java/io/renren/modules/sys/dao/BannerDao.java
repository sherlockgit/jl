package io.renren.modules.sys.dao;

import io.renren.modules.sys.entity.BannerEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 轮播图表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-04 21:54:43
 */
public interface BannerDao extends BaseMapper<BannerEntity> {

    Integer selectBySort(Integer sort);

    List<BannerEntity> selectBannerList();
}
