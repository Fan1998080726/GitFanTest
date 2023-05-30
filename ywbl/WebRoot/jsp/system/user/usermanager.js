$(function() {
	
	$("#checkAll").click(function() {
		if (this.checked) {
			$("input[name='selectFlag']:checkbox").each(function() { // 遍历所有的name为selectFlag的
				// checkbox
				$(this).attr("checked", true);
			})
		} else { // 反之 取消全选
			$("input[name='selectFlag']:checkbox").each(function() { // 遍历所有的name为selectFlag的
				// checkbox
				$(this).attr("checked", false);
			})
		}
	})
});

function chaxun() {
	var user_code = $('#usercode').val();
	$('#userform').attr("action", "usermanager!query.action");
	$('#userform').submit();
}

/**
 * 关闭弹出窗口
 */
function closeDialog() {
	$.closeDialog();
}

/**
 * 用户删除
 */
function del() {
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除用户？",
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
 * 用户删除确认操作
 * @param btn
 */
function showResult(btn){
	if("yes" == btn){
		document.listform.action = "usermanager!delete.action";
		document.listform.submit();
		$.alert("删除成功！");
	}
}
//var win;
/**
 * 创建新用户弹出窗口
 */
function addPage() {
	var url = "usermanager!addPage.action";
	$.dialog({
		url:url,
		title: '用户管理-创建新用户', 
		width: 900, 
		height: 400
		});

}
/**
 * 编辑用户信息并提交
 */
function editpage() {
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		
		if (a == 1) {
			
			var ids = "";
			$("input[name='selectFlag']:checked").each(function() {
				ids += $(this).val();
			});
			var url = "usermanager!editPage.action?id=" + ids;
			$.dialog({
				url:url,
				title: '用户管理-编辑用户', 
				width: 900, 
				height: 400
			});
		} else {
			$.alert("请选择一条信息进行编辑！");
		}

	} else {
		$.alert("请选择要编辑信息");
	}

}


function doSub1(state) {
	var password = document.getElementById("passWord").value;
	var newpassword = document.getElementById("newpassWord").value;
	if(password=="a"){
		$.alert("密码过于简单,请重新修改!");
		return;
		
	}
	
	if(password==newpassword){
		$(".registerform").attr("action", "usermanager!resetPass.action?passWord="+password+"&state="+state);
		$(".registerform").submit();
	}else{
		$.alert("二次密码输入不一致,请重新输入！");
		return;
	}
	
}

function doSub2() {
	var login_password = document.getElementById("login_password").value;
	$(".registerform").attr("action", "usermanager!edit.action?login_password="+login_password);
	$(".registerform").submit();
}

function changeDept(val){
	$.ajax({
		type: "get",
		dataType:"json",
		url: "rolemanager!getRoleList.action?dept_id="+val,
		success: function(data, textStatus){
			var sel=document.getElementById("role_id");
			sel.length = 1;
			for(var i=0;i<data.length;i++){
				sel.options.add(new Option(data[i].role_name,data[i].role_id));
			}
		},
		error: function(){
		}
	});
}