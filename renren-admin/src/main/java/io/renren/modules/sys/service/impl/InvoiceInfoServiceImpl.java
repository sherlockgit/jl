package io.renren.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
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
        );

        return new PageUtils(page);
    }

}
