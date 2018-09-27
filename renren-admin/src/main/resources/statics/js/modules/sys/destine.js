$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/destine/list',
        datatype: "json",
        colModel: [			
			{ hidden: true,label: 'destineId', name: 'destineId', index: 'DESTINE_ID', width: 50, key: true },
			{ label: '会员姓名', name: 'uname', index: 'UNAME', width: 80 },
			{ label: '手机号码', name: 'phone', index: 'PHONE', width: 80 }, 			
			{ label: '项目归类', name: 'proType', width: 80,formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>足疗</span>';
                } else if (value == '1') {
                    return '<span>保健</span>';
                }else if (value == '2') {
                    return '<span>纤体</span>';
                } else {
                    return '<span>养生</span>';
                }
            }},
			{ label: '项目名称', name: 'proName', index: 'PRO_NAME', width: 80 }, 			
			{ label: '项目金额', name: 'proAmt', index: 'PRO_AMT', width: 80 },
            { label: '预约服务时间', name: 'destineTime',  width: 80,formatter:function(value,options,row){
                return new Date(value).Format('yyyy-MM-dd HH:mm');
            }},
			{ label: '预约状态', name: 'distineStatus',  width: 80 ,formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>待确认</span>';
                } else if (value == '1') {
                    return '<span>待服务</span>';
                }else if (value == '2') {
                    return '<span>已取消</span>';
                } else {
                    return '<span>已完成</span>';
                }
            }},
			{ label: '创建时间', name: 'createTime', index: 'CREATE_TIME', width: 80 },
            {
                label: '操作', name: '', index: 'operate', width: 50, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    var detail="<a  onclick='vm.detail(\""+ rowObject.destineId + "\")'' href=\"#\" >详情</a>";
                    return detail;
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

var list = new Array();

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            uuame: null,
            phone: null,
            proType: "",
            proName: null
        },
		showList: true,
		showEdit:false,
		showDetail:false,
		title: null,
		destine: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
            vm.showEdit=true;
			vm.showDetail=false;
			vm.title = "新增";
			vm.destine = {};
            $.get(baseURL + "sys/project/getProjectNameList", function(r){
            	if(list.length==0){
                    list = r.data
                    console.log(r.data)
                    for(var i = 0;i<list.length;i++){
                        $('#projectName').append('<option value='+list[i]+' >'+list[i]+'</option>')
                    }
				}
            });
		},
		update: function (event) {
            var destineId = getSelectedRow();
			if(destineId == null){
				return ;
			}
			vm.showList = false;
            vm.showEdit=true;
                vm.showDetail=false;
            vm.title = "修改";
            $.get(baseURL + "sys/project/getProjectNameList", function(r){
                if(list.length==0){
                    list = r.data
                    console.log(r.data)
                    for(var i = 0;i<list.length;i++){
                        $('#projectName').append('<option value='+list[i]+' >'+list[i]+'</option>')
                    }
                }
            });
            vm.getInfo(destineId)
		},
        detail: function (destineId) {
            if(destineId == null){
                return ;
            }
            vm.showList = false;
            vm.showEdit=false;
                vm.showDetail=true;
                vm.title = "修改";
            $.get(baseURL + "sys/project/getProjectNameList", function(r){
                if(list.length==0){
                    list = r.data
                    console.log(r.data)
                    for(var i = 0;i<list.length;i++){
                        $('#projectName').append('<option value='+list[i]+' >'+list[i]+'</option>')
                    }
                }
            });
            vm.getInfo(destineId)
        },
		saveOrUpdate: function (event) {
			var url = vm.destine.destineId == null ? "sys/destine/save" : "sys/destine/update";

            vm.destine.destineTime=$("#datetimepicker").data("datetimepicker").getDate();
            $.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.destine),
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
			var destineIds = getSelectedRows();
			if(destineIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/destine/delete",
                    contentType: "application/json",
				    data: JSON.stringify(destineIds),
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
		getInfo: function(destineId){
			$.get(baseURL + "sys/destine/info/"+destineId, function(r){

                vm.destine = r.destine;
                vm.destine.destineTime=new Date().Format("yyyy-MM-dd HH:mm");
            });
		},
		reload: function (event) {
			vm.showList = true;
            vm.showEdit=false;
                vm.showDetail=false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'uname': vm.q.uname,
                    'phone': vm.q.phone,
                    'proName': vm.q.proName,
                    'proType': vm.q.proType
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