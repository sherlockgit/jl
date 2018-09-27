$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/accountitem/listAll',
        datatype: "json",
        colModel: [
            { label: '用户姓名', name: 'userName', index: 'USER_NAME', width: 80 },
            { label: '手机号码', name: 'phone', index: 'PHONE', width: 80 },
			{ label: '收入', name: 'amtIn', index: 'AMT_IN', width: 80 },
			{ label: '支出', name: 'amtOut', index: 'AMT_OUT', width: 80 },
			{ label: '余额', name: 'balance', index: 'BALANCE', width: 80 },
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
            userName: null,
            phone: null,
            tranStatus: ""
        },
		showList: true,
		title: null,
		accountItem: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.accountItem = {};
		},
		update: function (event) {
			var itemId = getSelectedRow();
			if(itemId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(itemId)
		},
		saveOrUpdate: function (event) {
			var url = vm.accountItem.itemId == null ? "sys/accountitem/save" : "sys/accountitem/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.accountItem),
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
			var itemIds = getSelectedRows();
			if(itemIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/accountitem/delete",
                    contentType: "application/json",
				    data: JSON.stringify(itemIds),
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
		getInfo: function(itemId){
			$.get(baseURL + "sys/accountitem/info/"+itemId, function(r){
                vm.accountItem = r.accountItem;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'phone': vm.q.phone,
                    'userName': vm.q.userName,
                    'tranStatus': vm.q.tranStatus
                },
                page:page
            }).trigger("reloadGrid");
		}
	}
});

