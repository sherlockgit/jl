$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/invoiceinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'id',hidden: true, name: 'id', index: 'id', width: 50, key: true },
			{ label: '发票编号', name: 'invoiceNo', index: 'invoice_no', width: 80 }, 			
			{ label: '发票抬头', name: 'invoiceName', index: 'invoice_name', width: 80 }, 			
			{ label: '开票金额', name: 'invoiceAmt', index: 'invoice_amt', width: 80 }, 			
			{ label: '开票状态', name: 'invoiceStauts', width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>开票中</span>';
                } else if (value == '1') {
                    return '<span>已开票</span>';
                }
            }},
			{ label: '开票类型', name: 'invoiceType', width: 80, formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>普票</span>';
                } else if (value == '1') {
                    return '<span>专票</span>';
                }
            }},
			{ label: '发票性质', name: 'invoiceNature', width: 80 , formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>电子发票</span>';
                } else if (value == '1') {
                    return '<span>纸质发票</span>';
                }
            }},
			{ label: '开票类目', name: 'invoiceCategory', width: 80 , formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>服务费</span>';
                } else if (value == '1') {
                    return '<span>会务费</span>';
                }else if (value == '2') {
                    return '<span>咨询费</span>';
                }
            }},
            { label: '快递编号', name: 'expressNo', index: 'express_no', width: 80 },
            { label: '快递签收', name: 'expressSign', width: 80 , formatter: function(value, options, row){
                if (value == '0') {
                    return '<span>未签收</span>';
                } else if (value == '1') {
                    return '<span>已签收</span>';
                }
            }},
            { label: '收件人', name: 'cneeName', index: 'cnee_name', width: 80 },
            { label: '收件电话', name: 'cneePhone', index: 'cnee_phone', width: 80 },
			{ label: '开票时间', name: 'expressTime', index: 'express_time', width: 80 },
			{ label: '申请开票时间', name: 'applyTime', index: 'apply_time', width: 80 },
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
            records: "page.totalCount",
            userdata: "page.count"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
            vm.isOpen = $("#jqGrid").getGridParam('userData')[0]
            vm.unOpen = $("#jqGrid").getGridParam('userData')[1]
        },

    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            invoiceNo: null,
            expressNo: null,
            cneeName: null,
            invoiceStauts: "",
            invoiceType: "",
            invoiceCategory: ""
        },
        showList: true,
        showSaveOrUpdate: false,
        showDetail: false,
		title: null,
		invoiceInfo: {},
        isOpen: null,
        unOpen: null
	},
	methods: {
		query: function () {
			vm.reload();
            vm.isOpen = $("#jqGrid").getGridParam('userData')[0]
            vm.unOpen = $("#jqGrid").getGridParam('userData')[1]
		},
		add: function(){
            vm.showList = false;
            vm.showSaveOrUpdate = true;
            vm.showDetail = false;
			vm.title = "新增";
			vm.invoiceInfo = {invoiceType:0,invoiceNature:0,invoiceCategory:0,invoiceStauts:0,expressSign:0};
		},
		update: function (id) {
			if(id == null){
				return ;
			}
            vm.showList = false;
            vm.showSaveOrUpdate = true;
            vm.showDetail =false;
            vm.title = "修改";
            
            vm.getInfo(id)
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
			var url = vm.invoiceInfo.id == null ? "sys/invoiceinfo/save" : "sys/invoiceinfo/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.invoiceInfo),
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
				    url: baseURL + "sys/invoiceinfo/delete",
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
			$.get(baseURL + "sys/invoiceinfo/info/"+id, function(r){
                vm.invoiceInfo = r.invoiceInfo;
            });
		},
		reload: function (event) {
            vm.showList = true;
            vm.showSaveOrUpdate = false;
            vm.showDetail = false;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{
                    'invoiceNo': vm.q.invoiceNo,
                    'expressNo': vm.q.expressNo,
                    'cneeName': vm.q.cneeName,
                    'invoiceStauts': vm.q.invoiceStauts,
                    'invoiceType': vm.q.invoiceType,
                    'invoiceCategory': vm.q.invoiceCategory
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
            vm.invoiceInfo.invoiceFile = res.msg;//上传成功
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