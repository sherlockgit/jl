package io.renren.modules.sys.api;

import io.renren.common.utils.R;
import io.renren.modules.sys.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 小宇宙
 * @Date: 2018/6/11
 */
@RequestMapping("/apiBanner")
@RestController
public class ApiBannerController {

    @Autowired
    BannerService bannerService;

    @GetMapping("getBannerList")
    public R getBannerList () {
        return bannerService.selectBannerList();
    }
}
