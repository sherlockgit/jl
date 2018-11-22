package io.renren.modules.sys.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import io.renren.common.utils.ExportExcelUtils;
import io.renren.common.utils.UUIDUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.vo.ExcelDataVO;
import io.renren.modules.sys.vo.OrderCountVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.UserInfoEntity;
import io.renren.modules.sys.service.UserInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 会员信息表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-06-05 21:47:20
 */
@RestController
@RequestMapping("sys/userinfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:userinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userInfoService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("sys:userinfo:info")
    public R info(@PathVariable("userId") String userId){
        UserInfoEntity userInfo = userInfoService.selectById(userId);

        return R.ok().put("userInfo", userInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:userinfo:save")
    public R save(@RequestBody UserInfoEntity userInfo){
        ValidatorUtils.validateEntity(userInfo, AddGroup.class);


        return userInfoService.insertUserInfo(userInfo);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:userinfo:update")
    public R update(@RequestBody UserInfoEntity userInfo){
        ValidatorUtils.validateEntity(userInfo, UpdateGroup.class);

        return userInfoService.updateUserInfo(userInfo);
    }

    @RequestMapping("/getExcle")
    public void getExcle(@RequestParam Map<String, Object> params,HttpServletResponse response) throws Exception {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String dataName = simpleDateFormat.format(date);

        ExcelDataVO data = new ExcelDataVO();
        data.setName("会员列表"+dataName);
        List<String> titles = new ArrayList<>();
        titles.add("会员编号");
        titles.add("会员姓名");
        titles.add("会员类型");
        titles.add("手机号码");
        titles.add("机构名称");
        titles.add("注册时间");
        titles.add("到期时间");
        data.setTitles(titles);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<UserInfoEntity> list = userInfoService.getExcel(params);
        List<List<Object>> rows = new ArrayList();
        list.forEach(o->{
            List<Object> row = new ArrayList();
            row.add(o.getUserNo());
            row.add(o.getUserName());
            if ("1".equals(o.getUserType())) {
                row.add("金牌会员");
            }
            if ("2".equals(o.getUserType())) {
                row.add("银牌会员");
            }
            if ("3".equals(o.getUserType())) {
                row.add("铜牌会员");
            }
            if ("4".equals(o.getUserType())) {
                row.add("普通会员");
            }

            row.add(o.getPhone());
            row.add(o.getOrganization());
            if (!(o.getRegistTime() == null)) {
                row.add(sdf.format(o.getRegistTime()));
            }
            if (!(o.getEndTime() == null)) {
                row.add(sdf.format(o.getEndTime()));
            }

            rows.add(row);

        });
        data.setRows(rows);
        ExportExcelUtils.exportExcel(response,"会员列表"+dataName+".xlsx",data);
    }

}
