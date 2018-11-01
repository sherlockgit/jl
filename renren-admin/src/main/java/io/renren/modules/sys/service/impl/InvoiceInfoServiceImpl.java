package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.InvoiceInfoDao;
import io.renren.modules.sys.entity.InvoiceInfoEntity;
import io.renren.modules.sys.service.InvoiceInfoService;


@Service("invoiceInfoService")
public class InvoiceInfoServiceImpl extends ServiceImpl<InvoiceInfoDao, InvoiceInfoEntity> implements InvoiceInfoService {

    @Autowired
    InvoiceInfoDao invoiceInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String invoiceNo = (String)params.get("invoiceNo");
        String expressNo = (String)params.get("expressNo");
        String cneeName = (String)params.get("cneeName");
        String invoiceStauts = (String)params.get("invoiceStauts");
        String invoiceType = (String)params.get("invoiceType");
        String invoiceCategory = (String)params.get("invoiceCategory");
        Page<InvoiceInfoEntity> page = this.selectPage(
                new Query<InvoiceInfoEntity>(params).getPage(),
                new EntityWrapper<InvoiceInfoEntity>()
                        .like(StringUtils.isNotBlank(invoiceNo),"invoice_no", invoiceNo)
                        .like(StringUtils.isNotBlank(expressNo),"express_no", expressNo)
                        .like(StringUtils.isNotBlank(cneeName),"cnee_name", cneeName)
                        .like(StringUtils.isNotBlank(invoiceStauts),"invoice_stauts", invoiceStauts)
                        .like(StringUtils.isNotBlank(invoiceType),"invoice_type", invoiceType)
                        .like(StringUtils.isNotBlank(invoiceCategory),"invoice_category", invoiceCategory)
                        .orderBy("apply_time",false)
        );

        Integer isOpen = 0;
        Integer unOpen = 0;
        if (invoiceType == null || "".equals(invoiceType)) {
            /*已开票*/
            params.put("invoiceType",1);
            isOpen = invoiceInfoDao.getCount(params);
            params.put("invoiceType",0);
            unOpen = invoiceInfoDao.getCount(params);
        }else {
            if (invoiceType.equals("0")){
                unOpen = invoiceInfoDao.getCount(params);
            }else {
                isOpen = invoiceInfoDao.getCount(params);
            }
        }
        List<Integer> list = new ArrayList<>();
        if (isOpen == null) {
            isOpen = 0;
        }
        if (unOpen == null) {
            unOpen = 0;
        }
        list.add(unOpen);
        list.add(isOpen);
        return new PageUtils(page,list);
    }

}
