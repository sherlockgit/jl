$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/coursechapter/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '课程PID', name: 'courseId', index: 'course_id', width: 80 }, 			
			{ label: '课程编号', name: 'courseNo', index: 'course_no', width: 80 }, 			
			{ label: '章节编号', name: 'chapterNo', index: 'chapter_no', width: 80 }, 			
			{ label: '章节名称', name: 'chapterName', index: 'chapter_name', width: 80 }, 			
			{ label: '章节价格', name: 'chapterPrice', index: 'chapter_price', width: 80 }, 			
			{ label: '课程类型[0-直播， 1-录播， 2-问答]', name: 'chapterType', index: 'chapter_type', width: 80 }, 			
			{ label: '章节排序', name: 'chapterSort', index: 'chapter_sort', width: 80 }, 			
			{ label: '章节状态[0-上线, 1-下线， 2-新建]', name: 'chapterStatus', index: 'chapter_status', width: 80 }, 			
			{ label: '章节老师', name: 'chapterTeacher', index: 'chapter_teacher', width: 80 }, 			
			{ label: '章节文件[视频/音频文件]', name: 'chapterFile', index: 'chapter_file', width: 80 }, 			
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
		courseChapter: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.courseChapter = {};
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
			var url = vm.courseChapter.id == null ? "sys/coursechapter/save" : "sys/coursechapter/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.courseChapter),
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
				    url: baseURL + "sys/coursechapter/delete",
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
			$.get(baseURL + "sys/coursechapter/info/"+id, function(r){
                vm.courseChapter = r.courseChapter;
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