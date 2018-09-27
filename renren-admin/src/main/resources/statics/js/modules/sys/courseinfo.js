$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/courseinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '课程编号', name: 'courseNo', index: 'course_no', width: 80 }, 			
			{ label: '课程封面图', name: 'coursePic', index: 'course_pic', width: 80 }, 			
			{ label: '课程名称', name: 'courseName', index: 'course_name', width: 80 }, 			
			{ label: '课程老师', name: 'courseTeacher', index: 'course_teacher', width: 80 }, 			
			{ label: '课程总价格', name: 'coursePrice', index: 'course_price', width: 80 }, 			
			{ label: '课程时长(min)', name: 'courseMinute', index: 'course_minute', width: 80 }, 			
			{ label: '课程标签[0-私募,1-财经，2-保险]', name: 'courseTag', index: 'course_tag', width: 80 }, 			
			{ label: '课程分类[0-热门推荐， 1-精品课程， 2-免费专区  3-线下课程]', name: 'courseType', index: 'course_type', width: 80 }, 			
			{ label: '课程状态[0-上线, 1-下线， 2-新建]', name: 'courseStatus', index: 'course_status', width: 80 }, 			
			{ label: '课程排序', name: 'courseSort', index: 'course_sort', width: 80 }, 			
			{ label: '课程文件URL', name: 'courseFile', index: 'course_file', width: 80 }, 			
			{ label: '播放总次数', name: 'coursePalys', index: 'course_palys', width: 80 }, 			
			{ label: '课程简介', name: 'courseBrief', index: 'course_brief', width: 80 }, 			
			{ label: '课程详情', name: 'courseContent', index: 'course_content', width: 80 }, 			
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
		courseInfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.courseInfo = {};
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
			var url = vm.courseInfo.id == null ? "sys/courseinfo/save" : "sys/courseinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.courseInfo),
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
				    url: baseURL + "sys/courseinfo/delete",
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
			$.get(baseURL + "sys/courseinfo/info/"+id, function(r){
                vm.courseInfo = r.courseInfo;
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