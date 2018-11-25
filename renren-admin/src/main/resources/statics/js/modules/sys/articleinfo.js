$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/articleinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id',hidden: true, name: 'id', index: 'id', width: 50, key: true },
			{ label: '文章编号', name: 'articleNo', index: 'article_no', width: 80 },
            { label: '文章名称', name: 'articleName', index: 'article_name', width: 80 },
            { label: '文章标签', name: 'articleTag', width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>私募</span>';
                } else if (value == '1') {
                    return '<span>财经</span>';
                } else {
                    return '<span>保险</span>';
                }
            }},
            { label: '文章状态', name: 'articleStatus',  width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>上线</span>';
                } else if (value == '1') {
                    return '<span>下线</span>';
                } else {
                    return '<span>新建</span>';
                }
            }},
            { label: '文章作者', name: 'articleAuthor', index: 'article_author', width: 80 },
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
            vm.getByType();
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            articleNo: null,
            articleName: null,
            articleAuthor: null,
            articleTag: "",
            articleStatus: ""
        },
        showList: true,
        showSaveOrUpdate: false,
        showDetail: false,
		title: null,
		articleInfo: {},
        list: []
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
            vm.getByType();
			vm.reload();
		},
		add: function(){
            vm.getByType();
            $('#img').removeAttr("src")
            $('#imgd').removeAttr("src")
            editor[1].html('');
            editor[0].html('');
			vm.showList = false;
            vm.showSaveOrUpdate = true;
			vm.title = "新增";
			vm.articleInfo = {articleTag: 0,articleStatus:2 ,articleSort: 1};


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
            vm.title = "修改";

            vm.getInfoUpdate(id)
        },
		saveOrUpdate: function (event) {
			var url = vm.articleInfo.id == null ? "sys/articleinfo/save" : "sys/articleinfo/update";
            vm.articleInfo.articleContent=editor[0].html();
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.articleInfo),
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
        getByType: function () {
            $.get(baseURL + "sys/dict/getByTypeForArticleTag", function(r){
                vm.list = r.data;
            });
        },
		getInfo: function(id){
			$.get(baseURL + "sys/articleinfo/info/"+id, function(r){
                $('#imgd').attr('src', r.articleInfo.articlePic);
                editor[1].html(r.articleInfo.articleContent);
                vm.articleInfo = r.articleInfo;
            });

		},
        getInfoUpdate: function(id){
            $.get(baseURL + "sys/articleinfo/info/"+id, function(r){
                $('#img').attr('src', r.articleInfo.articlePic);
                editor[0].html(r.articleInfo.articleContent);
                vm.articleInfo = r.articleInfo;
            });

        },
		reload: function (event) {
			vm.showList = true;
            vm.showSaveOrUpdate = false;
            vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'articleNo': vm.q.articleNo,
                    'articleName': vm.q.articleName,
                    'articleAuthor': vm.q.articleAuthor,
                    'articleTag': vm.q.articleTag,
                    'articleStatus': vm.q.articleStatus,
                    'startTime': vm.q.startTime,
                    'endTime': vm.q.endTime
                },
                page:page
            }).trigger("reloadGrid");
		},
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
            vm.articleInfo.articlePic = res.msg//上传成功
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