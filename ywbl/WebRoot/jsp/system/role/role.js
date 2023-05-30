$(function() {
	$("#checkAll").click(function() {
		if (this.checked) {
			$("input[name='selectFlag']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox
						$(this).attr("checked", true);
					})
		} else {   //反之 取消全选 
			$("input[name='selectFlag']:checkbox").each(function() { //遍历所有的name为selectFlag的 checkbox
						$(this).attr("checked", false);
					})
		}
	})
 });

function chaxun(){
	
	var user_code  = $('#rolecode').val();
	$('#roleform').attr("action","rolemanager!query.action");
	$('#roleform').submit();
}

function del() {
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除角色？",
				function(){
			showResult("yes");// 确认按钮回调方法
		}, function(){
			$.alert("您取消了删除操作！");
		});
	}else {
		$.alert("请选择要删除的条目！");
		return;
	}
}
	

/**
 * 删除确认操作
 * @param btn
 */
function showResult(btn){

	var s='';
	$('input[name="selectFlag"]:checked').each(function(){
	   s+=$(this).val()+',';
	});
	
	$.ajax({
		url:'rolemanager!delete.action?ids='+ s,
		success:function(data){
			if(''==data){
				$.alert("角色下有所属人员，不可删除！");
			}else{
				$.alert("操作成功！");
				location.reload("rolemanager!query.action");
			}
			
		},
		error:function(){
			$.alert("操作失败！");
		}
	});
}



function addPage() {
	
	var url = "./rolemanager!addPage.action";
	$.dialog({
		url:url,
		title: '角色管理-创建新角色', 
		width: 600, 
		height: 275,
		data:{
			a:'1'
		}
		});
	}

function editpage() {
	if($("input[type='checkbox']").is(':checked')){
	var a =  $("input[type='checkbox'][name='selectFlag']:checked").length;
	
		if(a == 1){
			var ids = "";
		     $("input[name='selectFlag']:checked").each(function(){
		          ids += $(this).val();
		      });
			var url = "./rolemanager!editPage.action?id="+ids;
			$.dialog({
				url:url,
				title: '角色管理-修改角色', 
				width: 600, 
				height: 275,
				data:{
					a:'1'
				}
				});
		}else{
			$.alert("请选择一条信息进行编辑");
		}
		
	}else{
		$.alert("请选择要编辑信息");
	}
	
}

function doSub(){
	$(".registerform").submit();
}



function doEdit() {
	$(".registerform").submit();
}


function rolemenu() {

	var f = document.form1;
	
	var role_name = document.getElementById("role_name").value;
	
	if (formValid(role_name, "权限名称不能为空，请输入！")) {
		return;
	}
	Ext.Ajax.request({
		url : "./rolemanager!edit.action",
		form : f,
		async : true,
		success : function(jsonform, action) {
			
			var val = jsonform.responseText;
			if(null==val||''==val){
				showErrorInfo("权限名称已存在，请重新填写！");
			}else{
				parent.document.roleform.submit();
				// parent.location.reload();
				parent.win.close();
			}
		},
		failure : function(response) {
		}
	});

}

function commit() {
	var addIds = "";
	var objs = document.getElementsByTagName("input");
	var role_id = document.getElementById("roleId").value;
	for (var i = 3; i < objs.length; i++) {
		if (objs[i].checked == true)
			addIds = addIds + objs[i].id + "#";
	}
//	Ext.MessageBox.show({
//		msg : '正在保存数据，请稍候...',
//		progressText : '保存中...',
//		width : 300,
//		wait : true,
//		waitConfig : {
//			interval : 200
//		},
//		animEl : 'num'
//	});
	Ext.Ajax.request({
		url : './rolemanager!addRoleMenu.action?ids='+addIds+'&role_id='+role_id,
		async : false,
		success : function(jsonform, action) {
			Ext.MessageBox.hide();
			if (jsonform.responseText == '{success}') {
				Ext.MessageBox.show({
					title : '提示',
					msg : '操作成功！',
					width : 200,
					buttons : {
						yes : '确定'
					},
					fn : showResult1
				});

			} else {
				Ext.MessageBox.show({
					title : '提示',
					msg : '操作失败！',
					width : 200,
					buttons : {
						yes : '确定'
					}
				});
			}
		},
		failure : function(response) {
			Ext.MessageBox.hide();
			Ext.MessageBox.show({
				title : '提示',
				msg : '操作失败！',
				width : 200,
				buttons : {
					yes : '确定'
				}
			});
		}

	});
}



function editRole(role_id) {
	var   user_code = document.getElementById("user_code").value;
 
	$.dialog({
		url:"./rolemanager!getRoleMenuList.action?roleId="+role_id+'&user_code='+user_code,
		title: '编辑菜单', 
		width: 420, 
		height: 400,
		data:{
			a:'1'
		}
		});
}


function checkAll(mainObj) {
	var name = mainObj.name;
	var names = document.getElementsByName(name);
	if (mainObj.checked == true) {
		for (var i = 0; i < names.length; i++) {
			addAllIdByMainObj(names[i], mainObj.checked);
		}
	} else {
		for (var i = 0; i < names.length; i++) {
			addAllIdByMainObj(names[i]);
		}
	}
}


function addSingleIdByChildObj() {
	if (childObj.checked) {
		if (addIds.indexOf(id + "#") < 0) {
			addIds = addIds + id + "#";
		}
	} else {
		if (addIds.indexOf(id + "#") >= 0) {
			addIds.replace(id + "#", "");
		}
	}
}


function addAllIdByMainObj(obj, flag) {
	var id = obj.id;
	if (!obj.checked) {
		if (flag) {
			obj.checked = true;
		}
	} else {
		if (!flag) {
			obj.checked = false;
		}
	}
}

function showResult1() {
	parent.document.listform.submit();
	parent.win.close();
}