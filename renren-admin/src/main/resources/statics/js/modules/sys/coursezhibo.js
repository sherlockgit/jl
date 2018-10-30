$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/coursezhibo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id',hidden: true, name: 'id', index: 'id', width: 50, key: true },
			{ label: '课程编号', name: 'courseNo', index: 'course_no', width: 80 }, 			
			{ label: '课程名称', name: 'courseName', index: 'course_name', width: 80 },
            { label: '开播时间', name: 'publishTime',  width: 80 ,formatter:function(value,options,row){
                return new Date(value).Format('yyyy-MM-dd HH:mm');
            }},
            { label: '直播状态', name: 'courseStatus', width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>新建</span>';
                } else if (value == '1') {
                    return '<span>预告</span>';
                }else if (value == '2') {
                    return '<span>正在直播</span>';
                } else {
                    return '<span>直播结束</span>';
                }
            }},
            { label: '课程总价格', name: 'coursePrice', index: 'course_price', width: 80 },
            { label: '直播时长(min)', name: 'courseMinute', index: 'course_minute', width: 80 },
            { label: '课程老师', name: 'courseTeacher', index: 'course_teacher', width: 80 },
            { label: '手机号码', name: 'coursePhone', index: 'course_phone', width: 80 },
            { label: '创建时间', name: 'createTime', index: 'create_time', width: 80 },
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
            courseNo: null,
            courseName: null,
            courseTeacher: null,
            publishTime: null
        },
        showList: true,
        showSaveOrUpdate: false,
        showDetail: false,
		title: null,
		courseZhibo: {}
	},
	methods: {
		query: function () {
            if ($("#dateBegin").val().length <= 0) {
                vm.q.publishTime = '';
            }else {
                vm.q.publishTime=$("#dateBegin").data("datetimepicker").getDate();
            }
			vm.reload();
		},
		add: function(){
            $('#img').removeAttr("src")
            vm.showList = false;
            vm.showSaveOrUpdate = true;
            vm.showDetail = false;
			vm.title = "新增";
			vm.courseZhibo = {courseStatus:0};
            layui.use('layedit', function(){
                var layedit = layui.layedit
                    ,$ = layui.jquery;


                layedit.set({
                    uploadImage: {
                        url: baseURL + "common/uploadEdit/" //接口url
                        ,type: 'post' //默认post
                    }
                });


                //构建一个默认的编辑器
                var index = layedit.build('LAY_demo1',{
                    height: 520 ,//设置编辑器高度
                });

                var active = {
                    content: function () {
                        vm.courseZhibo.publishTime=$("#datetimepicker").data("datetimepicker").getDate();
                        vm.courseZhibo.courseContent = layedit.getContent(index)
                        var url = vm.courseZhibo.id == null ? "sys/coursezhibo/save" : "sys/coursezhibo/update";
                        console.log(url)
                        $.ajax({
                            type: "POST",
                            url: baseURL + url,
                            contentType: "application/json",
                            data: JSON.stringify(vm.courseZhibo),
                            success: function(r){
                                if(r.code === 0){
                                    alert('操作成功', function(index){
                                        location.reload()
                                    });
                                }else{
                                    alert(r.msg);
                                }
                            }
                        });

                    }
                }
                $('.site-demo-layedit').on('click', function(){
                    var type = $(this).data('type');
                    active[type] ? active[type].call(this) : '';
                });


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
            
            vm.getInfoUpdate(id)
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
			var url = vm.courseZhibo.id == null ? "sys/coursezhibo/save" : "sys/coursezhibo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.courseZhibo),
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
				    url: baseURL + "sys/coursezhibo/delete",
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
			$.get(baseURL + "sys/coursezhibo/info/"+id, function(r){
                $('#imgd').attr('src', r.courseZhibo.coursePic);
                layui.use('layedit', function(){
                    var layedit = layui.layedit
                        ,$ = layui.jquery;


                    layedit.set({
                        uploadImage: {
                            url: baseURL + "common/uploadEdit/" //接口url
                            ,type: 'post' //默认post
                        }
                    });

                    //构建一个默认的编辑器
                    var index = layedit.build('LAY_demo1d',{
                        height: 520 ,//设置编辑器高度
                    });
                    layedit.setContent(index,r.courseZhibo.courseContent,false)
                });
                vm.courseZhibo = r.courseZhibo;
                $("#datetimepickerd").val(new Date(vm.courseZhibo.publishTime).Format('yyyy-MM-dd HH:mm'))

            });
		},
        getInfoUpdate: function(id){
            $.get(baseURL + "sys/coursezhibo/info/"+id, function(r){
                vm.courseZhibo = r.courseZhibo;
                $("#datetimepicker").val(new Date(vm.courseZhibo.publishTime).Format('yyyy-MM-dd HH:mm'))
                $('#img').attr('src', r.courseZhibo.coursePic);
                layui.use('layedit', function(){
                    var layedit = layui.layedit
                        ,$ = layui.jquery;


                    layedit.set({
                        uploadImage: {
                            url: baseURL + "common/uploadEdit/" //接口url
                            ,type: 'post' //默认post
                        }
                    });

                    //构建一个默认的编辑器
                    var index = layedit.build('LAY_demo1',{
                        height: 520 ,//设置编辑器高度
                    });
                    layedit.setContent(index,r.courseZhibo.courseContent,false)
                    var active = {
                        content: function () {
                            vm.courseZhibo.publishTime=$("#datetimepicker").data("datetimepicker").getDate();
                            vm.courseZhibo.courseContent = layedit.getContent(index)
                            var url = vm.courseZhibo.id == null ? "sys/coursezhibo/save" : "sys/coursezhibo/update";
                            console.log(url)
                            $.ajax({
                                type: "POST",
                                url: baseURL + url,
                                contentType: "application/json",
                                data: JSON.stringify(vm.courseZhibo),
                                success: function(r){
                                    if(r.code === 0){
                                        alert('操作成功', function(index){
                                            location.reload()
                                        });
                                    }else{
                                        alert(r.msg);
                                    }
                                }
                            });

                        }
                    }
                    $('.site-demo-layedit').on('click', function(){
                        var type = $(this).data('type');
                        active[type] ? active[type].call(this) : '';
                    });

                });

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
                    'publishTime': vm.q.publishTime
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
            vm.courseZhibo.coursePic = res.msg//上传成功
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

$('#datetimepicker').datetimepicker({
    format: 'yyyy-mm-dd hh:ii'
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

$('#dateBegin').datetimepicker({
    format: 'yyyy-mm-dd hh:ii'
});