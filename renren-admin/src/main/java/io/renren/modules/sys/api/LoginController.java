package io.renren.modules.sys.api;

import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.sys.entity.UserInfoEntity;
import io.renren.modules.sys.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: 小宇宙
 * @Date: 2018/6/11
 */
@RequestMapping("/user")
@RestController
public class LoginController {

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/login")
    public R Login (@RequestBody Map<String,Object> map) {

        String wxUname = (String) map.get("wxUname");
        String wxOpenid = (String) map.get("wxOpenid");
        String wxHeadpic = (String) map.get("wxHeadpic");

        Assert.isNull(wxUname,"微信昵称不能为空");
        Assert.isNull(wxOpenid,"微信OPENID不能为空");
        Assert.isNull(wxHeadpic,"微信头像不能为空");
        System.out.println("微信名："+ map.get("wxUname").toString());
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setWxUname(wxUname);
        userInfoEntity.setWxOpenid(wxOpenid);
        userInfoEntity.setWxHeadpic(wxHeadpic);

        return userInfoService.appLogin(userInfoEntity);
    }

}
