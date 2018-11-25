package io.renren.modules.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import io.renren.common.utils.*;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.entity.InvoiceInfoEntity;
import io.renren.modules.sys.vo.ExcelDataVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.OrderRefundEntity;
import io.renren.modules.sys.service.OrderRefundService;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-08 14:48:16
 */
@RestController
@RequestMapping("sys/orderrefund")
public class OrderRefundController {
    @Autowired
    private OrderRefundService orderRefundService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:orderrefund:list")
    public R list(@RequestParam Map<String, Object> params) throws ParseException {
        PageUtils page = orderRefundService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:orderrefund:info")
    public R info(@PathVariable("id") String id){
        OrderRefundEntity orderRefund = orderRefundService.selectById(id);

        return R.ok().put("orderRefund", orderRefund);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:orderrefund:save")
    public R save(@RequestBody OrderRefundEntity orderRefund){
        ValidatorUtils.validateEntity(orderRefund, AddGroup.class);
        orderRefund.setId(UUIDUtils.getUUID());
        orderRefund.setRefundNo(NoUtils.genOrderNo());
        orderRefundService.insert(orderRefund);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:orderrefund:update")
    public R update(@RequestBody OrderRefundEntity orderRefund){
        ValidatorUtils.validateEntity(orderRefund, UpdateGroup.class);
        ValidatorUtils.validateEntity(orderRefund);
        orderRefundService.updateAllColumnById(orderRefund);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:orderrefund:delete")
    public R delete(@RequestBody String[] ids){
        orderRefundService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
    @RequestMapping("/getByOrder/{orderNo}")
    public R getByOrder(@PathVariable("orderNo") String orderNo){



        if ("null".equals(orderNo)){
            return R.error("订单编号不能为空");
        }
        OrderRefundEntity orderRefundEntity = orderRefundService.getByOrder(orderNo);

        if (orderRefundEntity == null) {
            return R.error("没有该编号对应的订单");
        }
        return R.ok().put("data",orderRefundEntity);

    }

    /**
     * 退款
     * @param orderRefund
     * @return
     */
    @RequestMapping("/returnPay")
    @RequiresPermissions("sys:orderrefund:update")
    public R returnPay(@RequestBody OrderRefundEntity orderRefund) {
        if (orderRefund.getOrderNo() == null) {
            return R.error("订单编号不能为空");
        }
        return orderRefundService.returnPay(orderRefund.getOrderNo());
    }

    @RequestMapping("/getExcle")
    public void getExcle(@RequestParam Map<String, Object> params,HttpServletResponse response) throws Exception {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String dataName = simpleDateFormat.format(date);

        ExcelDataVO data = new ExcelDataVO();
        data.setName("退款记录"+dataName);
        List<String> titles = new ArrayList<>();
        titles.add("订单编号");
        titles.add("订单名称");
        titles.add("订单金额");
        titles.add("退款金额");
        titles.add("退款状态");
        titles.add("退款方式");
        titles.add("会员姓名");
        titles.add("手机号码");
        titles.add("申请时间");
        titles.add("退款到账时间");
        data.setTitles(titles);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<OrderRefundEntity> list = orderRefundService.getExcleByOrder(params);
        List<List<Object>> rows = new ArrayList();
        list.forEach(o->{
            List<Object> row = new ArrayList();
            row.add(o.getOrderNo());
            row.add(o.getCourseName());
            row.add(o.getOrderPrice());
            row.add(o.getRefundPrice());
            if ("0".equals(o.getRefundStatus())){
                row.add("待退款");
            }
            if ("1".equals(o.getRefundStatus())){
                row.add("已退款");
            }
            if ("2".equals(o.getRefundStatus())){
                row.add("拒退款");
            }
            if ("0".equals(o.getApplyType())){
                row.add("微信支付");
            }
            if ("1".equals(o.getApplyType())){
                row.add("支付宝");
            }
            if ("2".equals(o.getApplyType())){
                row.add("银联支付");
            }
            if ("3".equals(o.getApplyType())){
                row.add("线下转账");
            }

            row.add(o.getUserName());
            row.add(o.getPhone());
            if (!(o.getApplyTime() == null)) {
                row.add(sdf.format(o.getApplyTime()));
            }else {
                row.add("");
            }
            if (!(o.getRefundTime() == null)) {
                row.add(sdf.format(o.getRefundTime()));
            }else {
                row.add("");
            }
            rows.add(row);

        });
        data.setRows(rows);
        ExportExcelUtils.exportExcel(response,"发票记录"+dataName+".xlsx",data);
    }
}
