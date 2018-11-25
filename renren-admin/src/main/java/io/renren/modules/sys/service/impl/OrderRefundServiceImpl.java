package io.renren.modules.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.renren.common.utils.HttpRequest;
import io.renren.common.utils.R;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.OrderRefundDao;
import io.renren.modules.sys.entity.OrderRefundEntity;
import io.renren.modules.sys.service.OrderRefundService;


@Service("orderRefundService")
public class OrderRefundServiceImpl extends ServiceImpl<OrderRefundDao, OrderRefundEntity> implements OrderRefundService {

    @Autowired
    OrderRefundDao orderRefundDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) throws ParseException {
        String orderNo = (String)params.get("orderNo");
        String phone = (String)params.get("phone");
        String userName = (String)params.get("userName");
        String applyType = (String)params.get("applyType");
        String refundStatus = (String)params.get("refundStatus");
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
        Page<OrderRefundEntity> page = this.selectPage(
                new Query<OrderRefundEntity>(params).getPage(),
                new EntityWrapper<OrderRefundEntity>()
                        .like(StringUtils.isNotBlank(orderNo),"order_no", orderNo)
                        .like(StringUtils.isNotBlank(phone),"phone", phone)
                        .like(StringUtils.isNotBlank(userName),"user_name", userName)
                        .like(StringUtils.isNotBlank(applyType),"apply_type", applyType)
                        .like(StringUtils.isNotBlank(refundStatus),"refund_status", refundStatus)
                        .gt(StringUtils.isNotBlank(startTime),"apply_time",startTimeDate)
                        .lt(StringUtils.isNotBlank(endTime),"apply_time", endTimeDate)
                        .orderBy("apply_time",false)
        );
        return new PageUtils(page);
    }

    @Override
    public OrderRefundEntity getByOrder(String orderNo) {

        return orderRefundDao.getByOrder(orderNo);
    }

    @Override
    public R returnPay(String orderNo) {
        OrderRefundEntity orderRefundEntity = orderRefundDao.getByOrderFund(orderNo);
        if (orderRefundEntity == null) {
            return R.error("订单状态错误");
        }
        String orderRefundId = orderRefundEntity.getRefundNo();
        BigDecimal bd = new BigDecimal(100);
        Integer orderPrice = (orderRefundEntity.getOrderPrice().multiply(bd).intValue());
        Integer refunPrice = (orderRefundEntity.getRefundPrice().multiply(bd).intValue());
        String sign = DigestUtils.md5Hex(orderRefundId+orderNo+orderPrice+refunPrice+"lkjm35489ksfga6ifg");

        String param = "id="+orderRefundId+"&orderNo="+orderNo+"&orderPrice="+orderPrice+"&refundPrice="+refunPrice+"&sign="+sign;
        System.out.println(param);
        String json = HttpRequest.sendGet("http://m.jingjinh.cn/smxy/payRefund/refund",param);
        System.out.println(json);
        JSONObject jsonObject = JSONObject.parseObject(json);
        if ("0000".equals(jsonObject.get("resultCode"))) {
            OrderRefundEntity orderRefundEntity1 = new OrderRefundEntity();
            orderRefundEntity1.setId(orderRefundEntity.getId());
            orderRefundEntity1.setRefundStatus("1");
            orderRefundDao.updateById(orderRefundEntity1);
            return R.ok("退款成功");
        }
        return R.error(jsonObject.get("message").toString());
    }

    @Override
    public List<OrderRefundEntity> getExcleByOrder(Map<String, Object> params) throws ParseException {
        String orderNo = (String)params.get("orderNo");
        String phone = (String)params.get("phone");
        String userName = (String)params.get("userName");
        String applyType = (String)params.get("applyType");
        String refundStatus = (String)params.get("refundStatus");
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
        List<OrderRefundEntity> list = orderRefundDao.selectList(new EntityWrapper<OrderRefundEntity>()
                .like(StringUtils.isNotBlank(orderNo),"order_no", orderNo)
                .like(StringUtils.isNotBlank(phone),"phone", phone)
                .like(StringUtils.isNotBlank(userName),"user_name", userName)
                .like(StringUtils.isNotBlank(applyType),"apply_type", applyType)
                .like(StringUtils.isNotBlank(refundStatus),"refund_status", refundStatus)
                .gt(StringUtils.isNotBlank(startTime),"apply_time",startTimeDate)
                .lt(StringUtils.isNotBlank(endTime),"apply_time", endTimeDate)
                .orderBy("apply_time",false));
        return list;
    }

}
