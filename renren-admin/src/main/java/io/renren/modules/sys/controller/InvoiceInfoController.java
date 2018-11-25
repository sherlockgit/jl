package io.renren.modules.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import io.renren.common.utils.ExportExcelUtils;
import io.renren.common.utils.NoUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.vo.ExcelDataVO;
import io.renren.modules.sys.vo.OrderCountVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.InvoiceInfoEntity;
import io.renren.modules.sys.service.InvoiceInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-05 10:07:01
 */
@RestController
@RequestMapping("sys/invoiceinfo")
public class InvoiceInfoController {
    @Autowired
    private InvoiceInfoService invoiceInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:invoiceinfo:list")
    public R list(@RequestParam Map<String, Object> params) throws ParseException {
        PageUtils page = invoiceInfoService.queryPage(params);

        return R.ok().put("page", page);
    }



    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:invoiceinfo:info")
    public R info(@PathVariable("id") Integer id){
        InvoiceInfoEntity invoiceInfo = invoiceInfoService.selectById(id);

        return R.ok().put("invoiceInfo", invoiceInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:invoiceinfo:save")
    public R save(@RequestBody InvoiceInfoEntity invoiceInfo){
        invoiceInfo.setInvoiceNo(NoUtils.genOrderNo());
        invoiceInfo.setApplyTime(new Date());
        invoiceInfoService.insert(invoiceInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:invoiceinfo:update")
    public R update(@RequestBody InvoiceInfoEntity invoiceInfo){
        ValidatorUtils.validateEntity(invoiceInfo);
        invoiceInfoService.updateAllColumnById(invoiceInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:invoiceinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        invoiceInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
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
        data.setName("发票记录"+dataName);
        List<String> titles = new ArrayList<>();
        titles.add("订单编号");
        titles.add("订单名称");
        titles.add("订单金额");
        titles.add("是否需要发票");
        titles.add("发票类型");
        titles.add("发票抬头");
        titles.add("发票名目");
        titles.add("税号");
        titles.add("开户行");
        titles.add("开户银行账号");
        titles.add("企业注册地址");
        titles.add("邮寄地址");
        data.setTitles(titles);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<InvoiceInfoEntity> list = invoiceInfoService.getExcle(params);
        List<List<Object>> rows = new ArrayList();
        list.forEach(o->{
            List<Object> row = new ArrayList();
            row.add(o.getOrderNos());
            row.add("");
            row.add(o.getInvoiceAmt());
            row.add("");
            if ("0".equals(o.getInvoiceType())) {
                row.add("普票");
            }
            if ("1".equals(o.getInvoiceType())) {
                row.add("专票");
            }
            row.add(o.getInvoiceName());
            if ("0".equals(o.getInvoiceCategory())) {
                row.add("培训费，");
            }
            if ("1".equals(o.getInvoiceCategory())) {
                row.add("会务费");
            }
            if ("2".equals(o.getInvoiceCategory())) {
                row.add("咨询费");
            }
            row.add(o.getTaxNo());
            row.add(o.getBankName());
            row.add(o.getBankCard());
            row.add("");
            row.add(o.getCneeAddr());
            rows.add(row);

        });
        data.setRows(rows);
        ExportExcelUtils.exportExcel(response,"发票记录"+dataName+".xlsx",data);
    }
}
