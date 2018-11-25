package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
    public PageUtils queryPage(Map<String, Object> params) throws ParseException {
        String articleNo = (String)params.get("articleNo");
        String articleName = (String)params.get("articleName");
        String articleAuthor = (String)params.get("articleAuthor");
        String articleTag = (String)params.get("articleTag");
        String articleStatus = (String)params.get("articleStatus");
        String startTime = (String)params.get("startTime");
        String endTime = (String)params.get("endTime");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);
        Date startTimeDate = null;
        Date endTimeDate = null;
        if (StringUtils.isNotBlank(startTime)) {
            startTimeDate = sdf.parse(startTime.replace("GMT", "").replaceAll("\\(.*\\)", ""));
        }
        if (StringUtils.isNotBlank(endTime)) {
            endTimeDate = sdf.parse(endTime.replace("GMT", "").replaceAll("\\(.*\\)", ""));
        }
        Page<ArticleInfoEntity> page = this.selectPage(
                new Query<ArticleInfoEntity>(params).getPage(),
                new EntityWrapper<ArticleInfoEntity>()
                        .like(StringUtils.isNotBlank(articleNo),"article_no", articleNo)
                        .like(StringUtils.isNotBlank(articleName),"article_name", articleName)
                        .like(StringUtils.isNotBlank(articleAuthor),"article_author", articleAuthor)
                        .like(StringUtils.isNotBlank(articleTag),"article_tag", articleTag)
                        .like(StringUtils.isNotBlank(articleStatus),"article_status", articleStatus)
                        .gt(StringUtils.isNotBlank(startTime),"create_time",startTimeDate)
                        .lt(StringUtils.isNotBlank(endTime),"create_time", endTimeDate)
                .orderBy("create_time",false)
        );

        return new PageUtils(page);
    }

}
