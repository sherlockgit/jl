$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/orderrefund/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', hidden: true,name: 'id', index: 'id', width: 50, key: true },
			{ label: '订单编号', name: 'orderNo', index: 'order_no', width: 80 },
            { label: '订单名称', name: 'courseName', index: 'course_name', width: 80 },
            { label: '订单金额', name: 'orderPrice', index: 'order_price', width: 80 },
            { label: '实际退款', name: 'refundPrice', index: 'refund_price', width: 80 },
            { label: '退款状态', name: 'refundStatus', width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>待退款</span>';
                } else if (value == '1') {
                    return '<span>已退款</span>';
                }else if (value == '2') {
                    return '<span>拒退款</span>';
                }
            }},
            { label: '付款方式', name: 'applyType',  width: 80 , formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>微信支付</span>';
                } else if (value == '1') {
                    return '<span>支付宝</span>';
                }else if (value == '2') {
                    return '<span>银联支付</span>';
                }else if (value == '3') {
                    return '<span>线下转账</span>';
                }
            }},
            { label: '机构名称', name: 'organization', index: 'organization', width: 80 },
            { label: '用户姓名', name: 'userName', index: 'user_name', width: 80 },
            { label: '手机号码', name: 'phone', index: 'phone', width: 80 },
            { label: '退款申请时间', name: 'applyTime', width: 80 ,formatter:function(value,options,row){
                return new Date(value).Format('yyyy-MM-dd HH:mm');
            }},
            { label: '退款处理时间', name: 'operTime',  width: 80 ,formatter:function(value,options,row){
                return new Date(value).Format('yyyy-MM-dd HH:mm');
            }},
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

Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            orderNo: null,
            phone: null,
            userName: null,
            applyType: "",
            refundStatus: ""
        },
        showList: true,
        showSaveOrUpdate: false,
        showDetail: false,
		title: null,
		orderRefund: {}
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
			vm.orderRefund = {orderNo:null,applyType:0,refundStatus:0};
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
            vm.title = "修改";

            vm.getInfo(id)
        },
		saveOrUpdate: function (event) {
			var url = vm.orderRefund.id == null ? "sys/orderrefund/save" : "sys/orderrefund/update";
            vm.orderRefund.applyTime=$("#datetimepicker").data("datetimepicker").getDate();
            vm.orderRefund.operTime=$("#datetimepickerd").data("datetimepicker").getDate();
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
                $("#datetimepicker").val(new Date(vm.orderRefund.applyTime).Format('yyyy-MM-dd HH:mm'))
                $("#datetimepickerd").val(new Date(vm.orderRefund.operTime).Format('yyyy-MM-dd HH:mm'))
                $("#datetimepicker1").val(new Date(vm.orderRefund.applyTime).Format('yyyy-MM-dd HH:mm'))
                $("#datetimepickerd1").val(new Date(vm.orderRefund.operTime).Format('yyyy-MM-dd HH:mm'))

            });
		},
        getOrder: function(){
            vm.getByOrderNo()
        },
        getByOrderNo: function(event){
            orderNo = vm.orderRefund.orderNo;
            $.get(baseURL + "sys/orderrefund/getByOrder/"+orderNo, function(r){
                if(r.code == 0){
                    vm.orderRefund = r.data;
                    vm.orderRefund.applyType = 0
                    vm.orderRefund.refundStatus = 0
                }else{
                    alert(r.msg);
                }

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
                    'phone': vm.q.phone,
                    'userName': vm.q.userName,
                    'applyType': vm.q.applyType,
                    'refundStatus': vm.q.refundStatus
                },
                page:page
            }).trigger("reloadGrid");
		}
	}
});

$('#datetimepicker').datetimepicker({
    format: 'yyyy-mm-dd hh:ii'
});


$('#datetimepickerd').datetimepicker({
    format: 'yyyy-mm-dd hh:ii'
});