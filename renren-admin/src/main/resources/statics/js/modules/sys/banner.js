$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/banner/list',
        datatype: "json",
        colModel: [			
			{ label: 'bannerId', hidden: true, name: 'bannerId', index: 'BANNER_ID', width: 50, key: true },
			{ label: '标题', name: 'title', index: 'TITLE', width: 80 },
            { label: '图片地址', name: 'picture', index: 'PICTURE', width: 80 },
            { label: '跳转地址', name: 'href', index: 'HREF', width: 80 },
            { label: '归属', name: 'come', width: 80, formatter: function(value, options, row){
                if (value == '1') {
                    return '<span>全部</span>';
                } else if (value == '2') {
                    return '<span>首页</span>';
                } else {
                    return '<span>发现</span>';
                }
            }},
			{ label: '排序', name: 'sort', index: 'SORT', width: 80 }, 			
			{ label: '轮播状态', name: 'statu', width: 80, formatter: function(value, options, row){
				if (value == '0') {
                	return '<span>新建</span>';
				} else if (value == '1') {
                    return '<span>已上线</span>';
				} else {
                    return '<span>已下线</span>';
				}
            }},
			{ label: '创建时间', name: 'createTime', index: 'CREATE_TIME', width: 80 },
            {
                label: '操作', name: '', index: 'operate', width: 80, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    var detail="<a style='color: #4169E1;text-decoration:underline;' onclick='vm.detail(\""+ rowObject.bannerId + "\")'' href=\"#\" >详情</a>";
                    var update="<a style='color: #4169E1;text-decoration:underline;' onclick='vm.update(\""+ rowObject.bannerId + "\")'' href=\"#\" >修改</a>"
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
		showList: true,
        showSaveOrUpdate: false,
        showDetail: false,
		title: null,
		banner: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
            $('#img').removeAttr("src")

			vm.showList = false;
            vm.showSaveOrUpdate = true;
			vm.title = "新增";
			vm.banner = {statu: 0,sort: 1};
		},
		update: function (bannerId) {
			if(bannerId == null){
				return ;
			}

			vm.showList = false;
            vm.showSaveOrUpdate = true;
            vm.title = "修改";
            
            vm.getInfo(bannerId)


        },
        detail: function (bannerId) {
            if(bannerId == null){
                return ;
            }

            vm.showList = false;
            vm.showDetail = true,
			vm.title = "详情";

            vm.getInfo(bannerId)


        },
		saveOrUpdate: function (event) {
			var url = vm.banner.bannerId == null ? "sys/banner/save" : "sys/banner/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.banner),
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
			var bannerIds = getSelectedRows();
			if(bannerIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/banner/delete",
                    contentType: "application/json",
				    data: JSON.stringify(bannerIds),
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
		getInfo: function(bannerId){
			$.get(baseURL + "sys/banner/info/"+bannerId, function(r){
                $('#img').attr('src', r.banner.picture);
                $('#imgd').attr('src', r.banner.picture);
                vm.banner = r.banner;
            });
		},
		reload: function (event) {
			vm.showList = true;
            vm.showSaveOrUpdate = false;
            vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
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
            vm.banner.picture = res.msg//上传成功
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


