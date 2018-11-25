$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/usercnee/list',
        datatype: "json",
        colModel: [			
			{ label: 'id',hidden: true, name: 'id', index: 'id', width: 50, key: true },
			{ label: '会员编号', name: 'userNo', index: 'USER_NO', width: 80 },
			{ label: '收件人', name: 'cneeName', index: 'cnee_name', width: 80 },
			{ label: '手机号码', name: 'cneePhone', index: 'cnee_phone', width: 80 },
			{ label: '收件地址', name: 'cneeAddr', index: 'cnee_addr', width: 80 },
			{ label: '是否默认', name: 'isDefaute',  width: 80 , formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>否</span>';
                } else if (value == '1') {
                    return '<span>是</span>';
                }
            }},
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '修改时间', name: 'updateTime', index: 'update_time', width: 80 },
            {
                label: '操作', name: '', index: 'operate', width: 80, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    var update="<a style='color: #4169E1;text-decoration:underline;' onclick='vm.update(\""+ rowObject.id + "\")'' href=\"#\" >修改</a>"
                    return update;
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
            userNo: null,
            cneeName: null,
            phone: null,
            cneeAddr: null
        },
        showList: true,
        showSaveOrUpdate: false,
        showDetail: false,
		title: null,
		userCnee: {}
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
			vm.userCnee = {};
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
			var url = vm.userCnee.id == null ? "sys/usercnee/save" : "sys/usercnee/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.userCnee),
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
				    url: baseURL + "sys/usercnee/delete",
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
			$.get(baseURL + "sys/usercnee/info/"+id, function(r){
                vm.userCnee = r.userCnee;
            });
		},
		reload: function (event) {
            vm.showList = true;
            vm.showSaveOrUpdate = false;
            vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'userNo': vm.q.userNo,
                    'cneeName': vm.q.cneeName,
                    'phone': vm.q.phone,
                    'cneeAddr': vm.q.cneeAddr,
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