$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/articleinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '文章编号', name: 'articleNo', index: 'article_no', width: 80 }, 			
			{ label: '文章封面图', name: 'articlePic', index: 'article_pic', width: 80 }, 			
			{ label: '文章名称', name: 'articleName', index: 'article_name', width: 80 }, 			
			{ label: '文章分类', name: 'articleType', index: 'article_type', width: 80 }, 			
			{ label: '文章状态[0-上线, 1-下线， 2-新建]', name: 'articleStatus', index: 'article_status', width: 80 }, 			
			{ label: '文章标签[0-私募 1-财经 2-保险]', name: 'articleTag', index: 'article_tag', width: 80 }, 			
			{ label: '文章作者', name: 'articleAuthor', index: 'article_author', width: 80 }, 			
			{ label: '外部引用地址', name: 'articleHref', index: 'article_href', width: 80 }, 			
			{ label: '文章简介', name: 'articleBrief', index: 'article_brief', width: 80 }, 			
			{ label: '文章内容', name: 'articleContent', index: 'article_content', width: 80 }, 			
			{ label: '文章排序', name: 'articleSort', index: 'article_sort', width: 80 }, 			
			{ label: '阅读数', name: 'articleReads', index: 'article_reads', width: 80 }, 			
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '发布时间', name: 'publishTime', index: 'publish_time', width: 80 }, 			
			{ label: '下线时间', name: 'downTime', index: 'down_time', width: 80 }			
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
		articleInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.articleInfo = {};
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
			var url = vm.articleInfo.id == null ? "sys/articleinfo/save" : "sys/articleinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.articleInfo),
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
				    url: baseURL + "sys/articleinfo/delete",
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
			$.get(baseURL + "sys/articleinfo/info/"+id, function(r){
                vm.articleInfo = r.articleInfo;
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