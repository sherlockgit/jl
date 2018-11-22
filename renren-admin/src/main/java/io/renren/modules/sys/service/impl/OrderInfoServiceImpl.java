package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.vo.OrderCountVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.OrderInfoDao;
import io.renren.modules.sys.entity.OrderInfoEntity;
import io.renren.modules.sys.service.OrderInfoService;


@Service("orderInfoService")
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfoEntity> implements OrderInfoService {

    @Autowired
    OrderInfoDao orderInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) throws ParseException {

        String orderNo = (String)params.get("orderNo");
        String userPhone = (String)params.get("userPhone");
        String contentType = (String)params.get("contentType");
        String payType = (String)params.get("payType");
        String payStatus = (String)params.get("payStatus");
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

        Page<OrderInfoEntity> page = this.selectPage(
                new Query<OrderInfoEntity>(params).getPage(),
                new EntityWrapper<OrderInfoEntity>()
                        .like(StringUtils.isNotBlank(orderNo),"order_no", orderNo)
                        .like(StringUtils.isNotBlank(userPhone),"user_phone", userPhone)
                        .like(StringUtils.isNotBlank(contentType),"content_type", contentType)
                        .like(StringUtils.isNotBlank(payType),"pay_type", payType)
                        .like(StringUtils.isNotBlank(payStatus),"pay_status", payStatus)
                        .gt(StringUtils.isNotBlank(startTime),"create_time",startTimeDate)
                        .lt(StringUtils.isNotBlank(endTime),"create_time", endTimeDate)
                        .orderBy("create_time",false)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils getCount(Map<String, Object> params) throws ParseException {

        String orderNo = (String)params.get("orderNo");
        String courseName = (String)params.get("courseName");
        String payType = (String)params.get("payType");
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
        Page<OrderInfoEntity> page = this.selectPage(
                new Query<OrderInfoEntity>(params).getPage(),
                new EntityWrapper<OrderInfoEntity>()
                        .like(StringUtils.isNotBlank(orderNo),"order_no", orderNo)
                        .like(StringUtils.isNotBlank(courseName),"course_name", courseName)
                        .like(StringUtils.isNotBlank(payType),"pay_type", payType)
                        .gt(StringUtils.isNotBlank(startTime),"pay_time",startTimeDate)
                        .lt(StringUtils.isNotBlank(endTime),"pay_time", endTimeDate)
        );

        List<OrderInfoEntity> list =  page.getRecords();
        List<OrderCountVo> list1 = new ArrayList<>();
        list.forEach(o->{
            OrderCountVo orderCountVo = new OrderCountVo();
            orderCountVo.setCourseName(o.getCourseName());
            orderCountVo.setCoursePrice(o.getCoursePrice());
            orderCountVo.setOrderCoupon(o.getOrderCoupon());
            orderCountVo.setOrderPrice(o.getOrderPrice());
            if ("0".equals(o.getPayType())) {
                orderCountVo.setWechatPrice(o.getOrderPrice());
            }else if ("1".equals(o.getPayType())) {
                orderCountVo.setZhifuPrice(o.getOrderPrice());
            }else if ("3".equals(o.getPayType())) {
                orderCountVo.setDuigongPrice(o.getOrderPrice());
            }
            orderCountVo.setPayTime(o.getPayTime());
            list1.add(orderCountVo);
        });

        /*统计*/
        OrderInfoEntity orderInfoEntity = orderInfoDao.getCount(params);
        OrderCountVo orderCountVo = new OrderCountVo();
        BigDecimal wechatPrice = new BigDecimal(0);
        BigDecimal zhifuPrice = new BigDecimal(0);
        BigDecimal duigongtPrice = new BigDecimal(0);
        if (!(orderInfoEntity == null)) {
            if (StringUtils.isBlank(payType)) {
            /*查询微信*/
                params.put("payType",0);
                OrderInfoEntity wechatPriceVo = orderInfoDao.getCount(params);
                if (wechatPriceVo != null) {
                    wechatPrice = wechatPriceVo.getOrderPrice();
                }
            /*查询支付宝*/
                params.put("payType",1);
                OrderInfoEntity zhifuPriceVo = orderInfoDao.getCount(params);
                if (zhifuPriceVo != null) {
                    zhifuPrice = zhifuPriceVo.getOrderPrice();
                }
            /*查询对公账户*/
                params.put("payType",3);
                OrderInfoEntity duigongtPriceVo = orderInfoDao.getCount(params);
                if (duigongtPriceVo != null) {
                    duigongtPrice = duigongtPriceVo.getOrderPrice();
                }
            }else if ("0".equals(payType)) {
                wechatPrice = orderInfoEntity.getOrderPrice();
            }else if ("1".equals(payType)) {
                zhifuPrice = orderInfoEntity.getOrderPrice();
            }else if ("3".equals(payType)) {
                duigongtPrice = orderInfoEntity.getOrderPrice();
            }
            orderCountVo.setCoursePrice(orderInfoEntity.getCoursePrice());
            orderCountVo.setOrderCoupon(orderInfoEntity.getOrderCoupon());
            orderCountVo.setOrderPrice(orderInfoEntity.getOrderPrice());
        }else {
            orderCountVo.setCoursePrice(new BigDecimal(0));
            orderCountVo.setOrderCoupon(new BigDecimal(0));
            orderCountVo.setOrderPrice(new BigDecimal(0));
        }


        orderCountVo.setCourseName("合计");

        orderCountVo.setZhifuPrice(zhifuPrice);
        orderCountVo.setWechatPrice(wechatPrice);
        orderCountVo.setDuigongPrice(duigongtPrice);
        list1.add(orderCountVo);

        return new PageUtils(list1,page.getTotal(),page.getSize(),page.getPages());
    }


    @Override
    public List<OrderCountVo> getExcle(Map<String, Object> params) throws ParseException {

        String orderNo = (String)params.get("orderNo");
        String courseName = (String)params.get("courseName");
        String payType = (String)params.get("payType");
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
        List<OrderInfoEntity> list = orderInfoDao.selectList(new EntityWrapper<OrderInfoEntity>()
                .like(StringUtils.isNotBlank(orderNo),"order_no", orderNo)
                .like(StringUtils.isNotBlank(courseName),"course_name", courseName)
                .like(StringUtils.isNotBlank(payType),"pay_type", payType)
                .gt(StringUtils.isNotBlank(startTime),"pay_time",startTimeDate)
                .lt(StringUtils.isNotBlank(endTime),"pay_time", endTimeDate));


        List<OrderCountVo> list1 = new ArrayList<>();
        list.forEach(o->{
            OrderCountVo orderCountVo = new OrderCountVo();
            orderCountVo.setCourseName(o.getCourseName());
            orderCountVo.setCoursePrice(o.getCoursePrice());
            orderCountVo.setOrderCoupon(o.getOrderCoupon());
            orderCountVo.setOrderPrice(o.getOrderPrice());
            if ("0".equals(o.getPayType())) {
                orderCountVo.setWechatPrice(o.getOrderPrice());
            }else if ("1".equals(o.getPayType())) {
                orderCountVo.setZhifuPrice(o.getOrderPrice());
            }else if ("3".equals(o.getPayType())) {
                orderCountVo.setDuigongPrice(o.getOrderPrice());
            }
            orderCountVo.setPayTime(o.getPayTime());
            list1.add(orderCountVo);
        });

        /*统计*/
        OrderInfoEntity orderInfoEntity = orderInfoDao.getCount(params);
        OrderCountVo orderCountVo = new OrderCountVo();

        BigDecimal wechatPrice = new BigDecimal(0);
        BigDecimal zhifuPrice = new BigDecimal(0);
        BigDecimal duigongtPrice = new BigDecimal(0);
        if (!(orderInfoEntity == null)) {
            if (StringUtils.isBlank(payType)) {
            /*查询微信*/
                params.put("payType",0);
                OrderInfoEntity wechatPriceVo = orderInfoDao.getCount(params);
                if (wechatPriceVo != null) {
                    wechatPrice = wechatPriceVo.getOrderPrice();
                }
            /*查询支付宝*/
                params.put("payType",1);
                OrderInfoEntity zhifuPriceVo = orderInfoDao.getCount(params);
                if (zhifuPriceVo != null) {
                    zhifuPrice = zhifuPriceVo.getOrderPrice();
                }
            /*查询对公账户*/
                params.put("payType",3);
                OrderInfoEntity duigongtPriceVo = orderInfoDao.getCount(params);
                if (duigongtPriceVo != null) {
                    duigongtPrice = duigongtPriceVo.getOrderPrice();
                }
            }else if ("0".equals(payType)) {
                wechatPrice = orderInfoEntity.getOrderPrice();
            }else if ("1".equals(payType)) {
                zhifuPrice = orderInfoEntity.getOrderPrice();
            }else if ("3".equals(payType)) {
                duigongtPrice = orderInfoEntity.getOrderPrice();
            }
            orderCountVo.setCoursePrice(orderInfoEntity.getCoursePrice());
            orderCountVo.setOrderCoupon(orderInfoEntity.getOrderCoupon());
            orderCountVo.setOrderPrice(orderInfoEntity.getOrderPrice());
        }else {
            orderCountVo.setCoursePrice(new BigDecimal(0));
            orderCountVo.setOrderCoupon(new BigDecimal(0));
            orderCountVo.setOrderPrice(new BigDecimal(0));
        }


        orderCountVo.setCourseName("合计");

        orderCountVo.setZhifuPrice(zhifuPrice);
        orderCountVo.setWechatPrice(wechatPrice);
        orderCountVo.setDuigongPrice(duigongtPrice);
        list1.add(orderCountVo);

        return list1;
    }

    @Override
    public List<OrderInfoEntity> getExcleByOrder(Map<String, Object> params) throws ParseException {
        String orderNo = (String)params.get("orderNo");
        String userPhone = (String)params.get("userPhone");
        String contentType = (String)params.get("contentType");
        String payType = (String)params.get("payType");
        String payStatus = (String)params.get("payStatus");
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
        List<OrderInfoEntity> list = orderInfoDao.selectList(new EntityWrapper<OrderInfoEntity>()
                .like(StringUtils.isNotBlank(orderNo),"order_no", orderNo)
                .like(StringUtils.isNotBlank(userPhone),"user_phone", userPhone)
                .like(StringUtils.isNotBlank(contentType),"content_type", contentType)
                .like(StringUtils.isNotBlank(payType),"pay_type", payType)
                .like(StringUtils.isNotBlank(payStatus),"pay_status", payStatus)
                .gt(StringUtils.isNotBlank(startTime),"create_time",startTimeDate)
                .lt(StringUtils.isNotBlank(endTime),"create_time", endTimeDate)
                .orderBy("create_time",false)
        );
        return list;
    }

}
