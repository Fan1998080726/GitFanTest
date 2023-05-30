/**
 * 编辑角色权限JS
 * 

 */


// 页面初始化，默认选中已存在的菜单
//var role_id = document.getElementById("roleId").value;
//var checkObjs = document.getElementsByName("check"+role_id);
//for ( var j = 0; j < checkObjs.length; j++) {
//	checkObjs[j].checked = true;
//}

// 保存被选中的ID
var checkIds = "";
/**
 * 编辑提交
 */
function commit() {
	
	var form=document.getElementById("listform");
	form.action="rolemanager!addRoleMenu.action";
	$(".registerform").submit();
//	var dataPara = getFormJson(form);
//	$.ajax({
//		type:'post',//可选get
//		url:'./rolemanager!addRoleMenu.action',//这里是接收数据的PHP程序
//		dataType:'text',//服务器返回的数据类型 可选XML ,Json jsonp script html text等
//		data: dataPara,
//		success:function(data){
//				$.closeDialog();
//		},
//		error:function(){
//			$.alert('保存失败！');
//		}
//	});
}

//将form中的值转换为键值对。
function getFormJson(frm) {
    var o = {};
    var a = $(frm).serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return o;
}

/**
 * 刷新并关闭窗口
 * 
 * @param btn
 */
function showResult(btn) {
	parent.window.location = parent.window.location.href; // 刷新父页面
	close();
}
/**
 * 关闭窗口
 */
function close() {
	parent.win.close();
}

/**
 * 点击字时
 */
function checkWord(id, is_child) {
	var obj = document.getElementById(id);
	if (obj.checked == true) {
		obj.checked = false;
		// checkIds = checkIds.replace(id+"#", "");
	} else {
		obj.checked = true;
		// checkIds = checkIds + id + "#";
	}
	// 同时选择子菜单
	if ("0" == is_child)
		checkChildAll(id, obj.checked);
}

/**
 * 点击复选框时
 */
function checkInput(id, is_child) {
	var obj = document.getElementById(id);
	// 同时选择子菜单
	if ("0" == is_child)
		checkChildAll(id + "", obj.checked);
}

/**
 * 根据父菜单的选中与否来操作其所有的子菜单
 * 
 * @param id
 * @param flag
 */
function checkChildAll(id, flag) {
	var len = id.length;
	// 获取所有INPUT标签
	var objs = document.getElementsByTagName("input");
	for ( var i = 0; i < objs.length; i++) {
		if (objs[i].id.length >= len)
			if (objs[i].id.substring(0, len) == id) {
				// 根据子菜单的ID是否包含父菜单ID
				objs[i].checked = flag;
				// if(!flag){
				// checkIds = checkIds.replace(objs[i].id+"#", "");
				// }else{
				// checkIds = checkIds + objs[i].id + "#";
				// }
			}
	}
}

function checkAllOrNot(obj) {
	var checked = obj.checked;
	var objs = document.getElementsByTagName("input");
	
	for (var i = 3; i < objs.length; i++) {
		if (checked) {
			if (objs[i].checked == false)
				objs[i].checked = true;
		} else {
			if (objs[i].checked == true)
				objs[i].checked = false;
		}
	}
}

$(function(){
	//$(".registerform").Validform();  //就这一行代码！;
		
	$(".registerform").Validform({
		showAllError:true,
		ajaxPost:true,
		tiptype:function(msg,o,cssctl){
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip=o.obj.siblings(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}
		},
		callback:function(data){
			$.closeDialog();
		}
	});
})


function sel(checkbox,id){
	if(checkbox.checked){
		checkbox.value="1";
		$("#role_id_"+id).attr("checked",true);
		$("#role_id_"+id).attr("value",1);
	}else{
		checkbox.value="0";
	}
}

function selmain(checkbox,id){
	if(checkbox.checked){
		checkbox.value="1";
	}else{
		checkbox.value="0";
		$("#rm_add_"+id).attr("checked",false);
		$("#rm_add_"+id).attr("value",0);
		
		$("#rm_update_"+id).attr("checked",false);
		$("#rm_update_"+id).attr("value",0);
		
		$("#rm_del_"+id).attr("checked",false);
		$("#rm_del_"+id).attr("value",0);

	}
}