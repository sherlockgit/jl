$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/courseinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id',hidden: true, name: 'id', index: 'id', width: 50, key: true },
			{ label: '课程编号', name: 'courseNo', index: 'course_no', width: 80 }, 			
			{ label: '课程名称', name: 'courseName', index: 'course_name', width: 80 },
            { label: '课程分类', name: 'courseType',  width: 80, formatter: function(value, options, row){
                if (value == '1') {
                    return '<span>精品课程</span>';
                } else if (value == '2') {
                    return '<span>免费专区</span>';
                }else if (value == '0') {
                    return '<span>直播课程</span>';
                } else {
                    return '<span>线下课程</span>';
                }
            }},
            // { label: '课程标签', name: 'courseTag', width: 80, formatter: function(value, options, row){
            //     if (value == '0') {
            //         return '<span>私募基金</span>';
            //     } else if (value == '1') {
            //         return '<span>财经</span>';
            //     } else {
            //         return '<span>法务</span>';
            //     }
            // }},
            { label: '课程总价格', name: 'coursePrice', index: 'course_price', width: 80 },
            { label: '课程状态', name: 'courseStatus', width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>新建</span>';
                } else if (value == '1') {
                    return '<span>上线</span>';
                } else {
                    return '<span>下线</span>';
                }
            }},
            { label: '播放总次数', name: 'coursePalys', index: 'course_palys', width: 80 },
            { label: '课程老师', name: 'courseTeacher', index: 'course_teacher', width: 80 },
            { label: '创建时间', name: 'createTime', index: 'create_time', width: 80 },
            { label: '发布时间', name: 'publishTime', index: 'publish_time', width: 80 },
            {
                label: '操作', name: '', index: 'operate', width: 80, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    var detail="<a style='color: #4169E1;text-decoration:underline;' onclick='vm.detail(\""+ rowObject.id + "\")'' href=\"#\" >详情</a>";
                    var update="<a style='color: #4169E1;text-decoration:underline;' onclick='vm.update(\""+ rowObject.id + "\")'' href=\"#\" >修改</a>"
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
            courseNo: null,
            courseName: null,
            courseTeacher: null,
            courseType: "",
            courseStatus: ""
        },
		showList: true,
        showSaveOrUpdate: false,
        showDetail: false,
		title: null,
		courseInfo: {},
		list:[]
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
		    vm.getByType();
            $('#img').removeAttr("src")
            editor[1].html('');
            editor[0].html('');
            vm.showList = false;
            vm.showSaveOrUpdate = true;
			vm.title = "新增";
			vm.courseInfo = {courseType: 2,courseTag: 0,courseStatus:0,courseIschapter:0,courseIshot:0,course_kind:0 };

		},
        detail: function (id) {
            vm.getByType();
            if(id == null){
                return ;
            }

            vm.showList = false;
            vm.showDetail = true,
                vm.title = "详情";

            vm.getInfo(id)


        },
		update: function (id) {
            vm.getByType();
			if(id == null){
				return ;
			}
            vm.showList = false;
            vm.showSaveOrUpdate = true;
            vm.showDetail =false;
            vm.title = "修改";
            
            vm.getInfoUpdate(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.courseInfo.id == null ? "sys/courseinfo/save" : "sys/courseinfo/update";
            vm.courseInfo.courseContent = editor[0].html();
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
                $('#imgd').attr('src', r.courseInfo.coursePic);
                editor[1].html(r.courseInfo.courseContent);
                })
    },
        getInfoUpdate: function(id){
            $.get(baseURL + "sys/courseinfo/info/"+id, function(r) {
                    vm.courseInfo = r.courseInfo;
                    $('#img').attr('src', r.courseInfo.coursePic);
                    editor[0].html(r.courseInfo.courseContent);

            })
        },
        getByType: function () {
            $.get(baseURL + "sys/dict/getByTypeForCourseTag", function(r){
                vm.list = r.data;
            });
        },
		reload: function (event) {
            vm.showList = true;
            vm.showSaveOrUpdate = false;
            vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'courseNo': vm.q.courseNo,
                    'courseName': vm.q.courseName,
                    'courseTeacher': vm.q.courseTeacher,
                    'courseType': vm.q.courseType,
                    'courseStatus': vm.q.courseStatus
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
            vm.courseInfo.coursePic = res.msg//上传成功
            layer.closeAll('loading');
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });
});


var editor = new Array();
KindEditor.ready(function(K) {
    editor[0] = K.create('#editor_id',{
        uploadJson : baseURL + "common/uploadImg/",
        filePostName: 'file',
        items: [
            'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
            'flash', 'table', 'hr', 'emoticons', 'pagebreak',
            'anchor', 'link', 'unlink', '|', 'about'
        ],
        height: '700px'
    });
    editor[1] = K.create('#editor_idd',{
        uploadJson : baseURL + "common/uploadImg/",
        filePostName: 'file',
        items: [
            'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
            'flash', 'table', 'hr', 'emoticons', 'pagebreak',
            'anchor', 'link', 'unlink', '|', 'about'
        ],
        height: '700px'
    });
});

layui.use('upload', function(){
    var $ = layui.jquery
        ,upload = layui.upload;

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#fileFile'
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
            vm.courseInfo.courseFile = res.msg;//上传成功
            var value = document.getElementById("text");
            value.value = res.msg
            layer.closeAll('loading');
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoTextFile');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
            layer.closeAll('loading');
        }
    });
});
