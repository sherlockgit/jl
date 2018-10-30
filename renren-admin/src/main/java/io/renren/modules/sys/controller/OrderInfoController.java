package io.renren.modules.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import io.renren.common.utils.ExportExcelUtils;
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

import io.renren.modules.sys.entity.OrderInfoEntity;
import io.renren.modules.sys.service.OrderInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 00:57:48
 */
@RestController
@RequestMapping("sys/orderinfo")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:orderinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/getExcle")
    public void getExcle(@RequestParam Map<String, Object> params,HttpServletResponse response) throws Exception {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String dataName = simpleDateFormat.format(date);

        ExcelDataVO data = new ExcelDataVO();
        data.setName("订单"+dataName);
        List<String> titles = new ArrayList<>();
        titles.add("课程名称");
        titles.add("课程价格");
        titles.add("实际收款");
        titles.add("抵扣费用");
        titles.add("微信收款（元）");
        titles.add("支付宝收款（元）");
        titles.add("付款时间");
        data.setTitles(titles);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<OrderCountVo> list = orderInfoService.getExcle(params);
        List<List<Object>> rows = new ArrayList();
        list.forEach(o->{
            List<Object> row = new ArrayList();
            row.add(o.getCourseName());
            row.add(o.getCoursePrice());
            row.add(o.getOrderPrice());
            row.add(o.getOrderCoupon());
            row.add(o.getWechatPrice());
            row.add(o.getZhifuPrice());
            if (!(o.getPayTime() == null)) {
                row.add(sdf.format(o.getPayTime()));
            }
            rows.add(row);

        });
        data.setRows(rows);
        ExportExcelUtils.exportExcel(response,"发货订单"+dataName+".xlsx",data);
    }

    @RequestMapping("/getCount")
    public R getCount(@RequestParam Map<String, Object> params) throws ParseException {
        PageUtils page = orderInfoService.getCount(params);

        return R.ok().put("page", page);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:orderinfo:info")
    public R info(@PathVariable("id") Integer id){
        OrderInfoEntity orderInfo = orderInfoService.selectById(id);

        return R.ok().put("orderInfo", orderInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:orderinfo:save")
    public R save(@RequestBody OrderInfoEntity orderInfo){
        ValidatorUtils.validateEntity(orderInfo, AddGroup.class);

        orderInfoService.insert(orderInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:orderinfo:update")
    public R update(@RequestBody OrderInfoEntity orderInfo){
        ValidatorUtils.validateEntity(orderInfo, UpdateGroup.class);
        ValidatorUtils.validateEntity(orderInfo);
        orderInfoService.updateAllColumnById(orderInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:orderinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        orderInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
