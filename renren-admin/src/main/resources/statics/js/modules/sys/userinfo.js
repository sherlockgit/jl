$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/userinfo/list',
        datatype: "json",
        colModel: [
            { hidden: true, name: 'userId', index: "USER_ID", width: 0, key: true },
            { label: '会员编号', name: 'userNo', index: 'USER_NO', width: 80 },
			{ label: '会员姓名', name: 'userName', index: 'USER_NAME', width: 80 ,formatter: function(value, options, row){
                return value == null ?
                    '<span>--</span>' :
                    value;
            }},
            { label: '机构名称', name: 'organization', index: 'organization', width: 80 },
			{ label: '手机号码', name: 'phone', index: 'PHONE', width: 80 ,formatter: function(value, options, row){
                return value == null ?
                    '<span>--</span>' :
                    value;
            }},
            { label: '会员类型', name: 'userType',width: 80, formatter: function(value, options, row){
                if (value == '1') {
                    return '<span>金牌会员</span>';
                } else if (value == '2') {
                    return '<span>银牌会员</span>';
                }else if (value == '3') {
                    return '<span>铜牌会员</span>';
                } else {
                    return '<span>普通会员</span>';
                }
            }},
			{ label: '微信号', name: 'wxUname', index: 'WX_UNAME', width: 80 },
            { label: 'OPENID', name: 'wxOpenid', index: 'WX_OPENID', width: 80 },
			{ label: '注册时间', name: 'registTime', index: 'REGIST_TIME', width: 80 },
            { label: '开通时间', name: 'startTime', index: 'START_TIME', width: 80 },
            { label: '到期时间', name: 'endTime', index: 'END_TIME', width: 80 },
            {
                label: '操作', name: '', index: 'operate', width: 80, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    var detail="<a style='color: #4169E1;text-decoration:underline;' onclick='vm.detail(\""+ rowObject.userId + "\")'' href=\"#\" >详情</a>";
                    var update="<a style='color: #4169E1;text-decoration:underline;' onclick='vm.update(\""+ rowObject.userId + "\")'' href=\"#\" >修改</a>"
                    return detail+'|'+update;
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
            userName: "",
            phone: "",
            wxUname: "",
            userType: ""
        },
		showList: true,
		showSaveOrUpdate: false,
		showDetail: false,
		title: null,
		userSex: 0,
		userInfo: {}
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
            vm.showSaveOrUpdate = true,
			vm.title = "新增";
			vm.userInfo = {userType:4};
		},
		update: function (userId) {

			if(userId == null){
				return ;
			}
			vm.showList = false;
            vm.showSaveOrUpdate = true,
            vm.title = "修改";
            
            vm.getInfo(userId)
		},
		detail: function (userId) {
            if(userId == null){
                return ;
            }
            vm.showList = false;
            vm.showDetail = true,
            vm.title = "详情";

            vm.getInfo(userId)
        },
        excle: function (event) {
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
            layer.confirm('确定要导出数据？', function(index){
                window.location =baseURL + "sys/userinfo/getExcle?userName="+vm.q.userName+"&phone="+vm.q.phone+"&wxUname="+vm.q.wxUname+"&userType="+vm.q.userType+"&startTime="+vm.q.startTime+"&endTime="+vm.q.endTime;
                layer.close(index);
                vm.reload();
            });
            var url =  "sys/userinfo/getExcle"
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.q),
                success: function(r){
                }
            });
        },
		saveOrUpdate: function (event) {
			var url = vm.userInfo.userId == null ? "sys/userinfo/save" : "sys/userinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.userInfo),
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
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/userinfo/delete",
                    contentType: "application/json",
				    data: JSON.stringify(userIds),
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
		getInfo: function(userId){
			$.get(baseURL + "sys/userinfo/info/"+userId, function(r){
                vm.userInfo = r.userInfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
            vm.showSaveOrUpdate = false;
            vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                	'userName': vm.q.userName,
                    'phone': vm.q.phone,
                    'wxUname': vm.q.wxUname,
                    'userType': vm.q.userType,
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