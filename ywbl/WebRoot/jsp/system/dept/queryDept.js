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
	
	$('#deptform').attr("action","dept!queryDept.action");
	$('#deptform').submit();
}

function del() {
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除部门？",
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

//	if("yes" == btn){
//		document.listform.action = "dept!delDept.action";
//		document.listform.submit();
//	}
		    
	$.ajax({
		url:'dept!delDept.action?ids='+ s,
		success:function(data){
			if(''==data){
				$.alert("部门下有所属权限，不可删除！");
			}else{
				$.alert("操作成功！");
				location.reload("dept!queryDept.action");
			}
			
		},
		error:function(){
			$.alert("编辑失败！");
		}
	});
}



function addPage() {
	
	$('#deptform').attr("action","dept!updateDeptPage.action");
	$('#deptform').submit();
/*	var url = "./dept!updateDeptPage.action";
	$.dialog({
		url:url,
		title: '添加部门', 
		width: 600, 
		height: 275,
		data:{
			a:'1'
		}
		});*/
	}

function editpage() {
	if($("input[type='checkbox']").is(':checked')){
	var a =  $("input[type='checkbox'][name='selectFlag']:checked").length;
	
		if(a == 1){
			var ids = "";
		     $("input[name='selectFlag']:checked").each(function(){
		          ids = $(this).val();
		      });
	$('#deptform').attr("action","dept!updateDeptPage.action?deptVo.dept_id="+ids);
	$('#deptform').submit();
/*			var url = "./dept!updateDeptPage.action?deptVo.dept_id="+ids;
			$.dialog({
				url:url,
				title: '部门修改', 
				width: 600, 
				height: 275,
				data:{
					a:'1'
				}
				});*/
		}else{
			$.alert("请选择一条信息进行编辑");
		}
		
	}else{
		$.alert("请选择要编辑信息");
	}
	
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

//审批人选择
function editAppUser(dept_id,app_user){
	$.dialog({
		url:'dept!queryDeptUser.action?app_user='+app_user+'&dept_id='+dept_id,
		title: '编辑审批员', 
		width: 800, 
		height: 600});
	
}

//审批人选择后刷新父页面
function getChildData(){
	window.location.reload();
}