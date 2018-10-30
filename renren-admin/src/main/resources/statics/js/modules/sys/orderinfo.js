$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/orderinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id',hidden: true, name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单编号', name: 'orderNo', index: 'order_no', width: 80 },
			{ label: '课程名称', name: 'courseName', index: 'course_name', width: 80 }, 			
			{ label: '订购内容类型', name: 'contentType', width: 80, formatter: function(value, options, row){
                if (value == '1') {
                    return '<span>全课程</span>';
                } else if (value == '2') {
                    return '<span>课程章节</span>';
                }else if (value == '3') {
                    return '<span>知识问答</span>';
                } else {
                    return '<span>直播课程</span>';
                }
            }},
			{ label: '课程价格', name: 'coursePrice', index: 'course_price', width: 80 }, 			
			{ label: '抵扣费用', name: 'orderCoupon', index: 'order_coupon', width: 80 },
			{ label: '实际收款', name: 'orderPrice', index: 'order_price', width: 80 }, 			
			{ label: '付款状态', name: 'payStatus',  width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>待付款</span>';
                } else if (value == '1') {
                    return '<span>已付款</span>';
                }
            }},
			{ label: '付款方式', name: 'payType', width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>微信支付</span>';
                } else if (value == '1') {
                    return '<span>支付宝</span>';
                }else if (value == '2') {
                    return '<span>银联支付</span>';
                } else {
                    return '<span>线下转账</span>';
                }
            }},
			{ label: '发票状态', name: 'invoiceStatus', width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>未开票</span>';
                } else if (value == '1') {
                    return '<span>需开票</span>';
                }else if (value == '2') {
                    return '<span>已开票</span>';
                }
            }},
			{ label: '会员姓名', name: 'userName', index: 'user_name', width: 80 },
			{ label: '会员电话', name: 'userPhone', index: 'user_phone', width: 80 }, 			
			{ label: '下单时间', name: 'createTime', index: 'create_time', width: 80 },
			{ label: '付款时间', name: 'payTime', index: 'pay_time', width: 80 }	,
            {
                label: '操作', name: '', index: 'operate', width: 80, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    var detail="<a style='color: #4169E1;text-decoration:underline;' onclick='vm.detail(\""+ rowObject.id + "\")'' href=\"#\" >详情</a>|";
                    var update="<a style='color: #4169E1;text-decoration:underline;' onclick='vm.update(\""+ rowObject.id + "\")'' href=\"#\" >修改</a>"
                    return detail+update;
                },
            },
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
        q:{
            orderNo: null,
            userPhone: null,
            contentType: "",
            payType: "",
            payStatus: ""
        },
        showList: true,
        showSaveOrUpdate: false,
        showDetail: false,
		title: null,
		orderInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
            vm.showList = false;
            vm.showSaveOrUpdate = true;
            vm.showDetail = false;
			vm.title = "新增";
			vm.orderInfo = {};
		},
		update: function (id) {
			if(id == null){
				return ;
			}
            vm.showList = false;
            vm.showSaveOrUpdate = true;
            vm.showDetail =false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
        detail: function (id) {
            if(id == null){
                return ;
            }
            vm.showList = false;
            vm.showDetail = true;
            vm.showSaveOrUpdate= false;
            vm.title = "详情";

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
            vm.showSaveOrUpdate = false;
            vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'orderNo': vm.q.orderNo,
                    'userPhone': vm.q.userPhone,
                    'contentType': vm.q.contentType,
                    'payType': vm.q.payType,
                    'payStatus': vm.q.payStatus
                },
                page:page
            }).trigger("reloadGrid");
		}
	}
});