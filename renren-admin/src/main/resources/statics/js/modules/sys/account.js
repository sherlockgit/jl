$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/account/list',
        datatype: "json",
        colModel: [			
			{ label: 'accountId',hidden: true, name: 'accountId', index: 'ACCOUNT_ID', width: 50, key: true },
            { hidden: true, name: 'userId', index: "USER_ID", width: 0 },
			{ label: '用户姓名', name: 'userName', index: 'USER_NAME', width: 80 ,formatter: function(value, options, row){
                return value == null ?
                    '<span>--</span>' :
                    value;
            }},
            { label: '手机号码', name: 'phone', index: 'PHONE', width: 80 ,formatter: function(value, options, row){
                return value == null ?
                    '<span>--</span>' :
                    value;
            }},
            { label: '累计充值（元）', name: 'totalIn', index: 'TOTAL_IN', width: 80 },
			{ label: '累计消费（元）', name: 'totalOut', index: 'TOTAL_OUT', width: 80 },
			{ label: '账户余额（元）', name: 'balance', index: 'BALANCE', width: 80 },
			{ label: '更新时间', name: 'updateTime', index: 'UPDATE_TIME', width: 80 },
            {
                label: '操作', name: '', index: 'operate', width: 80, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    var detail="<a  onclick='vm.countList(\""+ rowObject.userId + "\")'' href=\"#\" >明细</a>";
                    return detail;
                },
            }
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
    $("#tableShow").hide();
});

$(function () {
    $("#jqGridCount").jqGrid({
        url: baseURL + 'sys/accountitem/list',
        datatype: "json",
        colModel: [
            { label: '收入（元）', name: 'amtIn', index: 'AMT_IN', width: 80 },
            { label: '支出（元）', name: 'amtOut', index: 'AMT_OUT', width: 80 },
            { label: '余额（元）', name: 'balance', index: 'BALANCE', width: 80 },
            { label: '交易状态', name: 'tranStatus',  width: 80,formatter: function(value, options, row){
                return value == '0' ?
                    '<span>失败</span>' :
                    '<span>成功</span>';
            } },
            { label: '交易时间', name: 'createTime', index: 'CREATE_TIME', width: 80 },
            { label: '说明', name: 'memo', index: 'MEMO', width: 80 }
        ],
        viewrecords: true,
        height: 385,
        width: 1440,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:false,
        multiselect: true,
        pager: "#jqGridCountPager",
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
            $("#jqGridCount").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
    $("#tableShow").hide();
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            userName: null,
            phone: null,
            balance: null,
			userId: null,
            tranStatus: null
        },
		showList: true,
        showCountList: false,
		showDetail: false,
        tableShow: true,
		title: null,
		account: {}
	},
	methods: {
        countList: function (userId) {

            if(userId == null){
                return ;
            }
            vm.showList = false;
			vm.showCountList = true;
            vm.showDetail = false;
            vm.tableShow = true;
            vm.q.userId = userId;
            var page = $("#jqGridCount").jqGrid('getGridParam','page');
            var postData = {
                'userId': userId
			}
            $("#jqGridCount").jqGrid('setGridParam',{
                postData:postData
            }).trigger("reloadGrid");
        },
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
            vm.showCountList = false;
            vm.showDetail = true;
            vm.tableShow = false;
			vm.title = "新增";
			vm.account = {};
		},
		update: function (event) {
			var accountId = getSelectedRow();
			if(accountId == null){
				return ;
			}
            vm.showList = false;
            vm.showCountList = false;
			vm.showDetail = true;
            vm.title = "修改";
            
            vm.getInfo(accountId)
		},
		saveOrUpdate: function (event) {
			var url = vm.account.accountId == null ? "sys/account/save" : "sys/account/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.account),
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
			var accountIds = getSelectedRows();
			if(accountIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/account/delete",
                    contentType: "application/json",
				    data: JSON.stringify(accountIds),
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
		getInfo: function(accountId){
			$.get(baseURL + "sys/account/info/"+accountId, function(r){
                vm.account = r.account;
            });
		},
		reload: function (event) {
			vm.showList = true;
            vm.showCountList = false;
            vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'userName': vm.q.userName,
                    'phone': vm.q.phone,
                    'balance': vm.q.balance
                },
                page:page
            }).trigger("reloadGrid");
		},
		queryCount: function () {
			vm.reloadCount();
        },
        reloadCount: function (event) {
            vm.showList = false;
            vm.showCountList = true;
            vm.showDetail = false;
            vm.tableShow = false;
            var page = $("#jqGridCount").jqGrid('getGridParam','page');
            $("#jqGridCount").jqGrid('setGridParam',{
                postData:{
                    'userId': vm.q.userId,
                    'tranStatus': vm.q.tranStatus
                },
                page:page
            }).trigger("reloadGrid");
        },
        flush: function (event) {
            location.reload();
        }
	}
});