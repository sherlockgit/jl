package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.common.utils.NoUtils;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.ArticleInfoEntity;
import io.renren.modules.sys.service.ArticleInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-27 11:35:10
 */
@RestController
@RequestMapping("sys/articleinfo")
public class ArticleInfoController {
    @Autowired
    private ArticleInfoService articleInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:articleinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:articleinfo:info")
    public R info(@PathVariable("id") Integer id){
        ArticleInfoEntity articleInfo = articleInfoService.selectById(id);
        articleInfo.setArticleNo(NoUtils.genOrderNo());
        articleInfo.setCreateTime(new Date());
        return R.ok().put("articleInfo", articleInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:articleinfo:save")
    public R save(@RequestBody ArticleInfoEntity articleInfo){
        articleInfoService.insert(articleInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:articleinfo:update")
    public R update(@RequestBody ArticleInfoEntity articleInfo){
        ValidatorUtils.validateEntity(articleInfo);
        articleInfoService.updateAllColumnById(articleInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:articleinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        articleInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
