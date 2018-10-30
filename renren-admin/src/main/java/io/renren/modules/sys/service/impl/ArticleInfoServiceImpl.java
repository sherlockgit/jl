package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
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
        String articleNo = (String)params.get("articleNo");
        String articleName = (String)params.get("articleName");
        String articleAuthor = (String)params.get("articleAuthor");
        String articleTag = (String)params.get("articleTag");
        String articleStatus = (String)params.get("articleStatus");

        Page<ArticleInfoEntity> page = this.selectPage(
                new Query<ArticleInfoEntity>(params).getPage(),
                new EntityWrapper<ArticleInfoEntity>()
                        .like(StringUtils.isNotBlank(articleNo),"article_no", articleNo)
                        .like(StringUtils.isNotBlank(articleName),"article_name", articleName)
                        .like(StringUtils.isNotBlank(articleAuthor),"article_author", articleAuthor)
                        .like(StringUtils.isNotBlank(articleTag),"article_tag", articleTag)
                        .like(StringUtils.isNotBlank(articleStatus),"article_status", articleStatus)
                .orderBy("create_time",false)
        );

        return new PageUtils(page);
    }

}
