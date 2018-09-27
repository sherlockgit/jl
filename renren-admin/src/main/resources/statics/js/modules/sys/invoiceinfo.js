$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/invoiceinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '发票编号', name: 'invoiceNo', index: 'invoice_no', width: 80 }, 			
			{ label: '发票抬头', name: 'invoiceName', index: 'invoice_name', width: 80 }, 			
			{ label: '开票金额', name: 'invoiceAmt', index: 'invoice_amt', width: 80 }, 			
			{ label: '开票装[0-未开票  1-开票中  2-已开票]', name: 'invoiceStauts', index: 'invoice_stauts', width: 80 }, 			
			{ label: '开票类型[0-普票  1- 专票]', name: 'invoiceType', index: 'invoice_type', width: 80 }, 			
			{ label: '发票性质[0-电子发票，1-纸质发票]', name: 'invoiceNature', index: 'invoice_nature', width: 80 }, 			
			{ label: '开票类目[0-服务费， 1-会务费   2-咨询费]', name: 'invoiceCategory', index: 'invoice_category', width: 80 }, 			
			{ label: '稅务登记号', name: 'taxNo', index: 'tax_no', width: 80 }, 			
			{ label: '开户银行', name: 'bankName', index: 'bank_name', width: 80 }, 			
			{ label: '开户账号', name: 'bankCard', index: 'bank_card', width: 80 }, 			
			{ label: '收件人', name: 'cneeName', index: 'cnee_name', width: 80 }, 			
			{ label: '收件电话', name: 'cneePhone', index: 'cnee_phone', width: 80 }, 			
			{ label: '收件地址', name: 'cneeAddr', index: 'cnee_addr', width: 80 }, 			
			{ label: '快递公司', name: 'expressOrg', index: 'express_org', width: 80 }, 			
			{ label: '快递编号', name: 'expressNo', index: 'express_no', width: 80 }, 			
			{ label: '快递签收[0-未签收， 1-已签收]', name: 'expressSign', index: 'express_sign', width: 80 }, 			
			{ label: '开票时间', name: 'expressTime', index: 'express_time', width: 80 }, 			
			{ label: '申请开票时间', name: 'applyTime', index: 'apply_time', width: 80 }, 			
			{ label: '订单编号集合[aa,bbb]', name: 'orderNos', index: 'order_nos', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		invoiceInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.invoiceInfo = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.invoiceInfo.id == null ? "sys/invoiceinfo/save" : "sys/invoiceinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.invoiceInfo),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/invoiceinfo/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "sys/invoiceinfo/info/"+id, function(r){
                vm.invoiceInfo = r.invoiceInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});