$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/orderrefund/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单编号', name: 'orderNo', index: 'order_no', width: 80 }, 			
			{ label: '订单金额', name: 'orderPrice', index: 'order_price', width: 80 }, 			
			{ label: '实际退款金额', name: 'refundPrice', index: 'refund_price', width: 80 }, 			
			{ label: '退款状态[0-待退款 1-已退款 2-拒退款]', name: 'refundStatus', index: 'refund_status', width: 80 }, 			
			{ label: '退款说明-必填', name: 'refundMemo', index: 'refund_memo', width: 80 }, 			
			{ label: '退款流水号', name: 'refundNo', index: 'refund_no', width: 80 }, 			
			{ label: '退款操作人', name: 'refundOper', index: 'refund_oper', width: 80 }, 			
			{ label: '', name: 'refundTime', index: 'refund_time', width: 80 }, 			
			{ label: '', name: 'applyTime', index: 'apply_time', width: 80 }			
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
		orderRefund: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.orderRefund = {};
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
			var url = vm.orderRefund.id == null ? "sys/orderrefund/save" : "sys/orderrefund/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orderRefund),
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
				    url: baseURL + "sys/orderrefund/delete",
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
			$.get(baseURL + "sys/orderrefund/info/"+id, function(r){
                vm.orderRefund = r.orderRefund;
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