$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/orderinfo/getCount',
        datatype: "json",
        colModel: [			
			{ label: '课程名称', name: 'courseName', index: 'course_name', width: 80 },
			{ label: '课程价格', name: 'coursePrice', index: 'course_price', width: 80 },
            { label: '实际收款', name: 'orderPrice', index: 'order_price', width: 80 },
            { label: '抵扣费用', name: 'orderCoupon', index: 'order_coupon', width: 80 },
            { label: '微信收款（元）', name: 'wechatPrice', width: 80 ,formatter: function(value, options, row){
                return value == null ?
                    '<span>--</span>' :
                    value;
            }},
            { label: '支付宝收款（元）', name: 'zhifuPrice',  width: 80 ,formatter: function(value, options, row){
                return value == null ?
                    '<span>--</span>' :
                    value;
            }},
            { label: '对公账户收款（元）', name: 'duigongPrice',  width: 80 ,formatter: function(value, options, row){
                return value == null ?
                    '<span>--</span>' :
                    value;
            }},
			{ label: '付款时间', name: 'payTime', index: 'pay_time', width: 80 }	,
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
            orderNo: "",
            courseName: "",
            payType: "",
            startTime: "",
            endTime: "",
        },
        showList: true,
        showSaveOrUpdate: false,
        showDetail: false,
		title: null,
		orderInfo: {}
	},
	methods: {
		query: function () {

            if ($("#dateBegin").val().length <= 0) {
                vm.q.startTime = '';
            }else {
                vm.q.startTime=$("#dateBegin").data("datetimepicker").getDate();
            }
            if ($("#dateEnd").val().length <= 0) {
                vm.q.endTime='';
            }else {
                vm.q.endTime=$("#dateEnd").data("datetimepicker").getDate();
            }
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
        excle: function (event) {
            layer.confirm('确定要导出数据？', function(index){
                window.location =baseURL + "sys/orderinfo/getExcle?orderNo="+vm.q.orderNo+"&courseName="+vm.q.courseName+"&payType="+vm.q.payType+"&startTime="+vm.q.startTime+"&endTime="+vm.q.endTime;
                layer.close(index);
                vm.reload();
            });
            var url =  "sys/orderinfo/getExcle"
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.q),
                success: function(r){
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
			$.get(baseURL + "sys/orderinfo/getCount/"+id, function(r){
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
                    'courseName': vm.q.courseName,
                    'payType': vm.q.payType,
                    'startTime': vm.q.startTime,
                    'endTime': vm.q.endTime
                },
                page:page
            }).trigger("reloadGrid");
		}
	}
});

function DatePicker(beginSelector,endSelector){
    $(beginSelector).datetimepicker(
        {
            language: 'zh-CN', // 语言选择中文
            autoclose: true,
            startView: 'month', // 进来是月
            minView: 'hour',// 可以看到小时
            minuteStep:1, //分钟间隔为1分
            format: 'yyyy-mm-dd hh:ii:ss',// 年月日时分秒
            clearBtn:false,
            todayBtn:true,
            endDate:new Date() }).on('changeDate', function(ev){
                if(ev.date){
                    $(endSelector).datetimepicker('setStartDate', new Date(ev.date.valueOf()))
                }else{
                    $(endSelector).datetimepicker('setStartDate',null);
                }
            })
    $(endSelector).datetimepicker(
        {
            language: "zh-CN",
            autoclose: true,
            minView: "hour",
            minuteStep:1,
            startView:'month',
            format: "yyyy-mm-dd hh:ii:ss",
            clearBtn:true,
            todayBtn:false,
            endDate:new Date() }).on('changeDate', function(ev){
                if(ev.date){
                    $(beginSelector).datetimepicker('setEndDate', new Date(ev.date.valueOf()))
                }else{
                    $(beginSelector).datetimepicker('setEndDate',new Date());
                }
            })
}
DatePicker("#dateBegin","#dateEnd");
