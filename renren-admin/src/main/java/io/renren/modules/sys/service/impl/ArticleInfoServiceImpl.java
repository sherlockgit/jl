package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ArticleInfoDao;
import io.renren.modules.sys.entity.ArticleInfoEntity;
import io.renren.modules.sys.service.ArticleInfoService;


@Service("articleInfoService")
public class ArticleInfoServiceImpl extends ServiceImpl<ArticleInfoDao, ArticleInfoEntity> implements ArticleInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ArticleInfoEntity> page = this.selectPage(
                new Query<ArticleInfoEntity>(params).getPage(),
                new EntityWrapper<ArticleInfoEntity>()
        );

        return new PageUtils(page);
    }

}
