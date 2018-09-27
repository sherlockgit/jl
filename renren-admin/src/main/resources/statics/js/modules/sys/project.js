$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/project/list',
        datatype: "json",
        colModel: [			
			{ hidden: true,label: 'proId', name: 'proId', index: 'PRO_ID', width: 50, key: true },
            { label: '项目类型', name: 'proType',  width: 80 ,formatter: function(value, options, row){
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
            { label: '服务时长/分钟', name: 'proLong', index: 'PRO_LONG', width: 80 },
            { label: '项目状态', name: 'proStatus', width: 80,formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>新建</span>';
                } else if (value == '1') {
                    return '<span>已上线</span>';
                }else if (value == '2') {
                    return '<span>已下线</span>';
                }
            } },
			{ label: '创建时间', name: 'createTime', index: 'CREATE_TIME', width: 80 },
            {
                label: '操作', name: '', index: 'operate', width: 50, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    var detail="<a  onclick='vm.detail(\""+ rowObject.proId + "\")'' href=\"#\" >详情</a>";
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

var picture = new Map();
var indexPicture;
var cyclePic = "";
var strs = new Array();
var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            proName: null,
            proLong: null,
            proStatus: "",
            proType: ""
        },
		showList: true,
        showEdit: false,
        showDetail: false,
		title: null,
		project: {proStatus: 0}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
            vm.showEdit = true;
            vm.showDetail =false;
			vm.title = "新增";
			vm.project = {};

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
                        vm.project.coverPic = res.msg//上传成功
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



                //多图片上传
                upload.render({
                    elem: '#test2'
                    ,url: baseURL + "common/upload/"
                    ,method: 'POST'
                    ,accept: 'file'
                    ,before: function(obj){
                        //预读本地文件示例，不支持ie8
                        layer.load(2);
                        var files = obj.pushFile();
                        obj.preview(function(index, file, result){
                            $('#demo2').append('<div class="remove_'+index+'"><img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img" width="100px" height="100px"></div>')
                            picture.set(index,result);
                            indexPicture=index;
                            $('.remove_'+index).bind('click',function(){
                                delete files[index]
                                $(this).remove();
                                picture.delete(index)
                            })
                        });
                    }
                    ,done: function(res){
                        //上传完毕
                        //如果上传失败
                        if(res.code > 0){
                            return layer.msg('上传失败');
                        }
                        picture.set(indexPicture,res.msg);
                        console.log(picture);
                        layer.closeAll('loading');
                    }
                });
            });

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
                        vm.project.proContent = layedit.getContent(index)
                        var url = vm.project.proId == null ? "sys/project/save" : "sys/project/update";

                        picture.forEach(function (value, key, map) {
                            cyclePic=cyclePic+value+",";
                        });
                        cyclePic = cyclePic.substring(0,cyclePic.length-1);
                        vm.project.cyclePic=cyclePic;
                        console.log(vm.project.cyclePic)
                        $.ajax({
                            type: "POST",
                            url: baseURL + url,
                            contentType: "application/json",
                            data: JSON.stringify(vm.project),
                            success: function(r){
                                if(r.code === 0){
                                    alert('操作成功', function(index){
                                        location.reload()
                                    });
                                }else{
                                    vm.project.cyclePic="";
                                    cyclePic="";
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
        detail: function (proId) {
            if(proId == null){
                return ;
            }
            vm.showList = false;
            vm.showEdit = false;
            vm.showDetail =true;
            vm.title = "详情";

            vm.getInfoDetail(proId)
        },
		update: function (event) {
			var proId = getSelectedRow();
			if(proId == null){
				return ;
			}
			vm.showList = false;
            vm.showEdit = true;
            vm.showDetail =false;
            vm.title = "修改";
            
            vm.getInfo(proId)
		},
		saveOrUpdate: function (event) {
			var url = vm.project.proId == null ? "sys/project/save" : "sys/project/update";
            picture.forEach(function (value, key, map) {
                cyclePic=cyclePic+value+",";
                console.log(cyclePic)
            });
            vm.project.cyclePic=cyclePic;
            console.log(vm.project.cyclePic);

            console.log(vm.project.proCentent)
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.project),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
                        vm.project.cyclePic="";
                        cyclePic="";
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var proIds = getSelectedRows();
			if(proIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/project/delete",
                    contentType: "application/json",
				    data: JSON.stringify(proIds),
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
		getInfo: function(proId){

			$.get(baseURL + "sys/project/info/"+proId, function(r){
                vm.project = r.project;
                $('#img').attr('src', r.project.coverPic);
                if(strs.length==0){
                    var str = r.project.cyclePic
                    strs = str.split(",");
                    for(var i = 0;i<strs.length;i++){
                        $('#demo2').append('<div class="remove_'+i+'"><img src="'+ strs[i] +'" alt="'+ file.name +'" class="layui-upload-img" width="100px" height="100px"></div>')
                        picture.set("remove_"+i,strs[i]);
                        $('.remove_'+i).bind('click',function(){
                            picture.delete($(this).attr("class"));
                            $(this).remove();
                        })

                    }
                }

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
                            vm.project.coverPic = res.msg//上传成功
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



                    //多图片上传
                    upload.render({
                        elem: '#test2'
                        ,url: baseURL + "common/upload/"
                        ,method: 'POST'
                        ,accept: 'file'
                        ,before: function(obj){
                            //预读本地文件示例，不支持ie8
                            layer.load(2);
                            var files = obj.pushFile();
                            obj.preview(function(index, file, result){
                                $('#demo2').append('<div class="remove_'+index+'"><img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img" width="100px" height="100px"></div>')
                                picture.set(index,result);
                                console.log(file)
                                indexPicture=index;
                                $('.remove_'+index).bind('click',function(){
                                    delete files[index]
                                    $(this).remove();
                                    picture.delete(index)
                                })
                            });
                        }
                        ,done: function(res){
                            //上传完毕
                            //如果上传失败
                            if(res.code > 0){
                                return layer.msg('上传失败');
                            }
                            picture.set(indexPicture,res.msg);
                            console.log(picture);
                            layer.closeAll('loading');
                        }
                    });
                });

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
                        layedit.setContent(index,r.project.proContent,false)

                        var active = {
                            content: function(){
                                var url = vm.project.proId == null ? "sys/project/save" : "sys/project/update";
                                picture.forEach(function (value, key, map) {
                                    cyclePic=cyclePic+value+",";
                                });
                                cyclePic=cyclePic.substring(0,cyclePic.length-1);
                                vm.project.cyclePic=cyclePic;
                                vm.project.proContent=layedit.getContent(index)
                                vm.project.proContent=htmlencode(vm.project.proContent)
                                console.log(vm.project.cyclePic)
                                $.ajax({
                                    type: "POST",
                                    url: baseURL + url,
                                    contentType: "application/json",
                                    data: JSON.stringify(vm.project),
                                    success: function(r){

                                        if(r.code === 0){
                                            alert('操作成功', function(index){
                                                location.reload()
                                            });
                                        }else{
                                            vm.project.cyclePic="";
                                            cyclePic="";
                                            alert(r.msg);
                                        }
                                    }
                                });

                            }
                        };

                        $('.site-demo-layedit').on('click', function(){
                            var type = $(this).data('type');
                            active[type] ? active[type].call(this) : '';
                        });

                    });

            });
		},
        getInfoDetail: function(proId){
            $.get(baseURL + "sys/project/info/"+proId, function(r){
                $('#imgd').attr('src', r.project.coverPic);
                if(strs.length==0){
                    var str = r.project.cyclePic
                    strs = str.split(",");
                    for(var i = 0;i<strs.length;i++){
                        $('#demo2d').append('<div class="remove_'+i+'"><img src="'+ strs[i] +'" alt="'+ file.name +'" class="layui-upload-img" width="100px" height="100px"></div>')
                    }
                }


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
                    layedit.setContent(index,r.project.proContent,false)

                });
                vm.project = r.project;
            });
        },
		reload: function (event) {
			vm.showList = true;
            vm.showEdit = false;
            vm.showDetail =false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'proName': vm.q.proName,
                    'proLong': vm.q.proLong,
                    'proStatus': vm.q.proStatus,
                    'proType': vm.q.proType
                },
                page:page
            }).trigger("reloadGrid");
		},
		flush: function (event) {
            location.reload();
        }
	}
});


function htmlencode(str) {
    str = str.replace(/"/g,"'");
    return str;
}
