$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/orderinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单编号[自动生成]', name: 'orderNo', index: 'order_no', width: 80 }, 			
			{ label: '订单名称[课程/问答/直播]', name: 'courseName', index: 'course_name', width: 80 }, 			
			{ label: '课程价格', name: 'coursePrice', index: 'course_price', width: 80 }, 			
			{ label: '订单类型[0-课程， 2-问答  3-直播]', name: 'orderType', index: 'order_type', width: 80 }, 			
			{ label: '订单金额', name: 'orderPrice', index: 'order_price', width: 80 }, 			
			{ label: '订单抵扣', name: 'orderCoupon', index: 'order_coupon', width: 80 }, 			
			{ label: '订单状态[0-待付款， 1-已付款， 2-待退款， 3-已退款，4-拒退款]', name: 'orderStatus', index: 'order_status', width: 80 }, 			
			{ label: '付款方式[0-微信， 1-支付宝,  2-银联]', name: 'payType', index: 'pay_type', width: 80 }, 			
			{ label: '机构名称', name: 'orgName', index: 'org_name', width: 80 }, 			
			{ label: '会员姓名', name: 'userName', index: 'user_name', width: 80 }, 			
			{ label: '会员电话', name: 'userPhone', index: 'user_phone', width: 80 }, 			
			{ label: '下单时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '付款时间', name: 'payTime', index: 'pay_time', width: 80 }			
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
		orderInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.orderInfo = {};
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
			var url = vm.orderInfo.id == null ? "sys/orderinfo/save" : "sys/orderinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.orderInfo),
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
				    url: baseURL + "sys/orderinfo/delete",
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
			$.get(baseURL + "sys/orderinfo/info/"+id, function(r){
                vm.orderInfo = r.orderInfo;
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