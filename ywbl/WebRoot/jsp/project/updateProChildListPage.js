//添加右击菜单内容
function onRowContextMenu(e, rowIndex, rowData){
    e.preventDefault();
    $(this).treegrid('select', rowIndex.pc_id);
    $('#mm').menu('show', {
        left:e.pageX,
        top:e.pageY
    });       
}

function gotoUpdate(){
	window.location.href="project!getProChildListPage.action";
}

function getChildData(data){
	window.location.reload();
	$.alert("操作成功！");
}

$(function(){
    $('#tg').treegrid({
        url: 'project!getProjectChildListJson.action',
        title: '子工程列表',
        rownumbers: true,
        idField: 'pc_id',
        treeField: 'pc_name',
        fitColumns: true,
        singleSelect:false,//需设置  
        onContextMenu: onRowContextMenu, //右键菜单
        columns: [[
        	{
        	title:'是否有子节点',
        	field:'pc_is_child',
        	hidden:true
        	
       	},{
            title: '序号',
            field: 'pc_id',
			align: 'center',
            checkbox: true
        },{
        	title: '名称',
            field: 'pc_name',
            width: 150
        },{
            title: '描述',
            field: 'pc_describe',
            width: 280,
        },{
            title: '操作',
            field: '操作',
            width: 150,
            formatter:function(value, row, index){
            	if(row.pc_id==0){
            		return '&nbsp;&nbsp;&nbsp;&nbsp; <a style="color:#FB6401; text-decoration:underline;" href="javascript:paste('+row.pc_id+',\''+row.pc_name+'\');">粘贴</a>';
            	}else{
            		return '<a style="color:#0F8B0F; text-decoration:underline;" href="javascript:cur('+row.pc_id+',\''+row.pc_name+'\');"><font>剪切</font></a> <a style="color:#FB6401; text-decoration:underline;" href="javascript:paste('+row.pc_id+',\''+row.pc_name+'\');">粘贴</a>';
            	}
            	
			}
        },{
            title: '父节点',
            field: 'pc_pid',
            width: 0,
            hidden:true
        }
        
        ]],

        toolbar: [{
            id: 'idAdd',
            text: '增加',
            iconCls: 'icon-add',
            plain: 'true',
            //按钮事件
            handler: function(){
            	add();
            }
        }, '-', {
            id: 'idDelete',
            text: '删除',
            //disabled: true,
            iconCls: 'icon-remove',
            //按钮事件
            handler: function(){
            	del();
            }
        }, '-', {
            id: 'idEdit',
            text: '修改',
            iconCls: 'icon-edit',
            //disabled: true,
            handler: function(){
            	edit();
            }
        }, '-', {
            id: 'idExcel',
            text: '导入excel',
            //disabled: true,
            handler: function(){
            	uploadExcel();
            }
        }],
    
    
    });
});

//弹出上传excel页  20200305
function uploadExcel(){
	
	$.dialog({
		id:"uploadExcel",
		url:'excelUpload!getExcelUploadPage.action',
		title: 'excel导入', 
		width: 500, 
		height: 400
	});
}

//添加
function add(){
	var row = $('#tg').treegrid('getSelections');
	if(0==row.length){
		$.alert("请选择要添加子项的工程！");
		return;
	}
	if(row.length>1){
		$.alert("只能对一个工程进行操作！");
		return;
	}
	/**
	 * 判断此工程下是否录入过 计划成本、进度
	 * 有，则不能添加 
	 */
	$.ajax({
		type: 'POST',
		async: false,
		url:'project!queryChildStatus.action?pc_pid='+row[0].pc_id,
		dataType:'json',
		success : function(data) {
			if("1"==data.flag){
				$.dialog({
					url:'project!updateProjectChildPage.action?pc_pid='+row[0].pc_id,
					title: '子工程管理', 
					width: 500, 
					height: 400
				});
			}else if("2"==data.flag){
				$.alert('此子工程已经录入计划成本或进度，不能添加子工程！');
			}else if("3"==data.flag){
				$.alert('操作失败！');
			}
		},
		error:function(){
			$.alert('操作失败！');
		}
	});
}

//删除
function del(){
	var row = $('#tg').treegrid('getSelections');
	if(0==row.length){
		$.alert("请选择要删除的工程！");
		return;
	}
	var pc_id="";
	for(var i=0;i<row.length;i++){
		pc_id+=row[i].pc_id+",";
		if(0 != row[i].pc_is_child||0 == row[i].pc_id){
    		$.alert("您选择的子工程下尚有子工程，无法删除！");
    		return;
    	}
	}
	
	$.confirm("此操作会一起删除所属计划成本、计划进度，是否继续？",function(){
		$.ajax({
			type: "get",
			url: "project!delProjectChild.action?pc_id="+pc_id,
			dataType:'json',
			success: function(data, textStatus){
				if("1"==data.flag){
					window.location.reload();
				}else if("2"==data.flag){
					$.alert("子工程下录入了实际进度或实际成本，不能删除");
				}else if("3"==data.flag){
					$.alert("操作失败！");
				}
			},
			error: function(){
				$.alert("操作失败！");
			}
		});
	}, function(){
	});
}

function edit(){
	var row = $('#tg').treegrid('getSelections');
	if(0==row.length){
		$.alert("请选择要修改的工程！");
		return;
	}
	if(row.length>1){
		$.alert("只能对一个工程进行操作！");
		return;
	}
	$.dialog({
		url:'project!updateProjectChildPage.action?pc_id='+row[0].pc_id,
		title: '子工程管理', 
		width: 500, 
		height: 400
	});
}

//剪切功能
function cur(val,name){
	$.alert("剪切成功！");
	$('#cut_id').val(val);
	$('#cut_name').val(name);
}
//粘贴功能
function paste(val,name){
	if(''==$('#cut_id').val()){
		$.alert("剪切板为空,请选择要粘贴的子工程！");
		return;
	}
	if(val==$('#cut_id').val()){
		$.alert("不可将工程自身剪切粘贴！");
		return;
	}
	if(0==val){
		$.confirm("是否要将'"+$('#cut_name').val()+"'移入跟节点下？",function(){
			showResult($('#cut_id').val(),val);// 确认按钮回调方法
		}, function(){
			$.alert("您取消了操作！");
		});
		return;
	}
	if(!getParent($('#cut_id').val(),val)){
		$.alert("不可将工程剪切到其子工程下！");
		return;
	}
	$.confirm("是否要将'"+$('#cut_name').val()+"'移入'"+name+"'下？",function(){
		showResult($('#cut_id').val(),val);// 确认按钮回调方法
	}, function(){
		$.alert("您取消了操作！");
	});
}
//判断剪切工程是否是粘贴工程的父节点
function getParent(from_id,to_id){
	if(0==$('#tg').treegrid('getParent',to_id).pc_id){//粘贴节点父节点为跟节点，可以操作
		return true;
	}else if(from_id==$('#tg').treegrid('getParent',to_id).pc_id){//粘贴节点为剪切节点子节点，不可操作
		return false;
	}else{//开始递归
		return getParent(from_id,$('#tg').treegrid('getParent',to_id).pc_id);
	}
}

function showResult(from_id,to_id){
	$.ajax({
		type: 'POST',
		async: false,
		url:'project!cutPaste.action?from_id='+from_id+'&to_id='+to_id,
		dataType:'json',
		success : function(data) {
			if("1"==data.flag){
				location.reload();
			}else if("2"==data.flag){
				$.alert('粘贴工程已录入计划成本或进度，不能添加子工程！');
			}else if("3"==data.flag){
				$.alert('操作失败！');
			}
		},
		error:function(){
			$.alert('操作失败！');
		}
	});
}