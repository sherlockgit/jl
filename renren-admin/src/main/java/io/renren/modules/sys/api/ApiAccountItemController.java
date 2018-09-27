package io.renren.modules.sys.api;

import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.sys.service.AccountItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * author: 小宇宙
 * date: 2018/6/17
 */
@RequestMapping("/apiAccount")
@RestController
public class ApiAccountItemController {

    @Autowired
    AccountItemService accountItemService;

    @GetMapping("/getAccountItem")
    public R getAccountItem(@RequestParam(value = "userId",defaultValue = "")String userId,
                            @RequestParam(value = "timeStart" ,required = false)Long timeStart,
                            @RequestParam(value = "timeEnd",required = false)Long timeEnd){
        Assert.isBlank(userId,"用户ID不能为空");
        Assert.isNull(timeStart,"当月开始时间戳不能为空");
        Assert.isNull(timeEnd,"当月结束时间戳不能为空");
        Date timeS = new Date(timeStart);
        Date timee = new Date(timeEnd);

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("timeStart",timeS);
        map.put("timeEnd",timee);
        return accountItemService.getAccountByMonth(map);
    }
}
