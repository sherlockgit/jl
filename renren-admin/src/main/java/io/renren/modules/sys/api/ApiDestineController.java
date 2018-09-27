package io.renren.modules.sys.api;

import io.renren.common.utils.R;
import io.renren.common.validator.Assert;
import io.renren.modules.sys.entity.DestineEntity;
import io.renren.modules.sys.entity.ProjectEntity;
import io.renren.modules.sys.service.DestineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: 小宇宙
 * date: 2018/6/17
 */
@RequestMapping("/apiDestine")
@RestController
public class ApiDestineController {

    @Autowired
    DestineService destineService;

    /**
     * 新增预约
     * @param destineEntity
     * @return
     */
    @PostMapping("/insertProjece")
    public R insertProjece(@RequestBody DestineEntity destineEntity){
        Assert.isBlank(destineEntity.getProId(),"项目id不能为空");
        Assert.isBlank(destineEntity.getTechninain(),"预约技师不能为空");
        Assert.isBlank(destineEntity.getUserId(),"用户id不能为空");
        Assert.isBlank(destineEntity.getPhone(),"手机号码不能为空");
        Assert.isNull(destineEntity.getDestineTime(),"预约时间不能为空");
        Assert.isBlank(destineEntity.getUname(),"预约姓名不能为空");
        Assert.isBlank(destineEntity.getProType(),"项目类型不能为空");
        Assert.isBlank(destineEntity.getProName(),"项目名称不能为空");
        Assert.isNull(destineEntity.getProAmt(),"项目金额不能为空");
        destineEntity.setDistineStatus("0");
        return destineService.insertDestince(destineEntity);

    }
}
