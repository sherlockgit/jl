$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/coursechapter/list',
        datatype: "json",
        colModel: [			
			{ label: 'id',hidden: true, name: 'id', index: 'id', width: 50, key: true },
            { label: '章节编号', name: 'chapterNo', index: 'chapter_no', width: 80 },
            { label: '课程名称', name: 'courseName', width: 80 },
            { label: '章节名称', name: 'chapterName', index: 'chapter_name', width: 80 },
            { label: '章节价格', name: 'chapterPrice', index: 'chapter_price', width: 80 },
            { label: '章节状态', name: 'chapterStatus',  width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>新建</span>';
                } else if (value == '1') {
                    return '<span>上线</span>';
                } else {
                    return '<span>下线</span>';
                }
            }},
            { label: '章节排序', name: 'chapterSort', index: 'chapter_sort', width: 80 },
            { label: '章节播放次数', name: 'chapterPlays', index: 'chapter_plays', width: 80 },
            { label: '章节老师', name: 'chapterTeacher', index: 'chapter_teacher', width: 80 },
            { label: '创建时间', name: 'createTime', index: 'create_time', width: 80 },
            { label: '上线时间', name: 'publishTime', index: 'publish_time', width: 80 },
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

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            chapterNo: null,
            chapterName: null,
            chapterTeacher: null,
            chapterType: "",
            chapterStatus: ""
        },
        showList: true,
        showSaveOrUpdate: false,
        showDetail: false,
		title: null,
		courseChapter: {},
        list:[]
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
            $('#img').removeAttr("src")
            $('#imgd').removeAttr("src")

            vm.showList = false;
            vm.showSaveOrUpdate = true;
            vm.showDetail = false;
			vm.title = "新增";
			vm.courseChapter = {chapterSort:1,chapterStatus:0,chapterType:0,chapterIstry:0};
            $.get(baseURL + "sys/courseinfo/getCourseNameList", function(r){
                if(vm.list.length==0){
                    vm.list = r.list
                    console.log(vm.list)
                }
            });
		},
		update: function (id) {
			if(id == null){
				return ;
			}
            vm.showList = false;
            vm.showSaveOrUpdate = true;
            vm.showDetail =false;
            vm.title = "修改";

            $.get(baseURL + "sys/courseinfo/getCourseNameList", function(r){
                if(vm.list.length==0){
                    vm.list = r.list
                }
            });
            vm.getInfo(id)

		},
        detail: function (id) {
            if(id == null){
                return ;
            }
            $.get(baseURL + "sys/courseinfo/getCourseNameList", function(r){
                if(vm.list.length==0){
                    vm.list = r.list
                }
            });
            vm.showList = false;
            vm.showDetail = true;
            vm.showSaveOrUpdate= false;
                vm.title = "详情";

            vm.getInfo(id)


        },
		saveOrUpdate: function (event) {

			var url = vm.courseChapter.id == null ? "sys/coursechapter/save" : "sys/coursechapter/update";
                vm.courseChapter.courseId = vm.courseChapter.courseIdName
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.courseChapter),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
                            location.reload();
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
                $('#img').attr('src', r.courseChapter.chapterPic);
                $('#imgd').attr('src', r.courseChapter.chapterPic);
                vm.courseChapter = r.courseChapter;
                vm.courseChapter.courseIdName=  r.courseChapter.courseId
            });
		},
		reload: function (event) {
            vm.showList = true;
            vm.showSaveOrUpdate = false;
            vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'chapterNo': vm.q.chapterNo,
                    'chapterName': vm.q.chapterName,
                    'chapterTeacher': vm.q.chapterTeacher,
                    'chapterType': vm.q.chapterType,
                    'chapterStatus': vm.q.chapterStatus,
                    'startTime': vm.q.startTime,
                    'endTime': vm.q.endTime
                },
                page:page
            }).trigger("reloadGrid");
		}
	}
});


layui.use('upload', function(){
    var $ = layui.jquery
        ,upload = layui.upload;

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#file'
        ,url: baseURL + "common/UploadFile/"
        ,accept: 'file'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            layer.load(2);
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            vm.courseChapter.chapterFile = res.msg;//上传成功
            var value = document.getElementById("text");
            value.value = res.msg
            layer.closeAll('loading');
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
            layer.closeAll('loading');
        }
    });
});

layui.use('upload', function(){
    var $ = layui.jquery
        ,upload = layui.upload;

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#filePic'
        ,url: baseURL + "common/upload/"
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            layer.load(2);
            obj.preview(function(index, file, result){
                $('#img').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            vm.courseChapter.chapterPic = res.msg//上传成功
            layer.closeAll('loading');
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoTextPic');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });
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