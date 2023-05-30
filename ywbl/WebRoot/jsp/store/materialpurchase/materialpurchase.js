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

/**
 * 获取一条记录的数据
 */
function getData(mt_id,mt_name,mt_unit){
	document.getElementById("mt_id").value=mt_id;
	document.getElementById("mt_name").value=mt_name;
	document.getElementById("ma_unit").value=mt_unit;
}
/**
 * 关闭弹出窗口
 */
function closeDialog() {
	$.closeDialog();
}


function chaxun() {
	var user_code = $('#paramcode').val();
	$('#userform').attr("action", "purchase!queryMaterialPurchaseList.action");
	$('#userform').submit();
}

function chaxun1() {
	var user_code = $('#paramcode').val();
	$('#userform').attr("action", "purchase!queryMaterialPurchaseInList.action");
	$('#userform').submit();
}

function chaxunOut() {
	var user_code = $('#paramcode').val();
	$('#userform').attr("action", "storetype!queryMaterialOutList.action");
	$('#userform').submit();
}
function chazhao() {
	var user_code = $('#paramcode').val();
	$('#userform').attr("action", "storetype!addMaterialApplyPage.action");
	$('#userform').submit();
}

//var win;
/**
 * 跳转至添加页面
 */
function addPage() {
	var url = "purchase!addPage.action";
	$.dialog({
		id:"addId",
		url:url,
		title: '添加材料采购', 
		width: 800, 
		height: 600
		});
}

/**
 * 选择材料页面
 */
function selectMaterial() {
	$.dialog({
	    id:'selectmaterial',
		url:'purchase!selectMaterial.action',
		title: '选择材料', 
		width: 600, 
		height: 500
	});
}


//function totalprice(i) {
//	onblur="totalprice('+i+');"
//	var mpinfo_num="mpinfo_num"+i;
//	var mpinfo_price="mpinfo_price"+i;
//	var totalprice="totalprice"+i;
//	var num=document.getElementById(mpinfo_num).value;
//	var price=document.getElementById(mpinfo_price).value;
//	document.getElementById(totalprice).value=price*num;
//}

/**
 * 跳转至编辑页面
 */
function editpage() {
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		var ids = "";
		if(a<2){
			
		$("input[name='selectFlag']:checked").each(function() {
			ids += $(this).val();
		});
		
		var url = "purchase!editMaterialPurchasePage.action?mp_id=" + ids;
		$.dialog({
			id: "editId",
			url:url,
			title: '修改材料采购', 
			width: 800, 
			height: 600
			});
	}else
		$.alert("请选择一条信息进行编辑！");
		} else {
		$.alert("请选择一条信息进行编辑！");
	}

}

function queryMaterialPurchase(mp_id) {
	var url = "purchase!queryMaterialPurchasePage.action?mp_id="+mp_id;
	$.dialog({
		url:url,
		title: '查看材料采购详情', 
		width: 800, 
		height: 400
		});
}

/**
 * 跳转至添加材料出库页面
 */
function addMaterialOutPage() {
	if ($("input[type='checkbox']").is(':checked')) {
		var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
		var ids = "";
		if(a<2){
			
		$("input[name='selectFlag']:checked").each(function() {
			ids += $(this).val();
		});
		var url = "storetype!addMaterialOutPage.action?ma_id=" + ids;
		$.dialog({
			url:url,
			title: '材料出库', 
			width: 500, 
			height: 400
			});
	}else
		$.alert("请选择一条信息进行编辑！");
		} else {
		$.alert("请选择一条信息进行编辑！");
	}
}

function update(flag) {
	$.confirm("是否确认提交，提交后信息将无法修改！",
			function(){
		showResult1("yes",flag);// 确认按钮回调方法
	}, function(){
		
	});
}

/**
 * 用户确认操作
 * @param btn
 */
function showResult1(btn,flag){
	if("yes" == btn){
		document.getElementById("mp_flag").value=flag;
		doSub();
	}
}
/**
 * 添加页调用提交方法
 */
function doSub(flag) {
	var flag=flag;
	if(flag==36){
		document.getElementById("mp_flag").value=flag;
	}
	var tablebody = $("#fileUploadTable tr").length;
	if($("#tab1").find("tr").length<=2){//没有添加材料
		$.alert("请选择需采购材料！");
		return;
	}
	$.ajax({
		url:'project!queryProjectStatus.action',
		dataType:'json',
		success:function(data){
			if('6'==data.state){
				$.alert('已竣工的工程不能提出材料采购！');
			}else{
				if(tablebody != 0){
					$(".registerform").submit();
				}else{
					$.alert("请上传附件");
					return false;
				}
			}
		},
		error:function(){
			$.alert('操作失败！');
		}
	});
}

function ajaxFileUpload(){
    	var FileId = 'uploadfile';
    	$.ajaxFileUpload
        (
            {
                url:'purchase!ajaxFileUpload.action',
                secureuri:false,
                fileElementId:FileId,
                dataType: 'json',
                success: function (data, status)
                {   
                	if(data.status=='y'){
						$('#mpfile_url').val(data.mpfile_url);
						$('#mpfile_name').val(data.mpfile_name);
						$(".registerform").submit();
                	}else{
                		$.alert(data.info);
                	}
                },
                error: function (data, status, e)
                {
                	$.alert("文件上传失败!");
                }
            }
        )
	}

/**
 * 弹出多文件上传窗口，返回添加后的json realName 带时间戳的文件名称（真实名称） 展示的名称应为 realName.substring(14)
 */
function addFileUpload(){
	$.dialog({
		id:'uploadFilesId',
		url:'filesUpload!uploadFile.action',
		title: '上传文件', 
		width: 500, 
		height: 400,
		data:{
			filePath:'d:/materialFile/'
		}
		});
}

function getChildData(data){
	var temp=data.split(",");
	var flag=temp[4];
	// 如果flag==1 则是文件上传的回调。
	if("2"!=flag){
		var json = $.parseJSON(data);
		$(json.files).each(function(){
			$('#fileUploadTable').append("<tr><td>"+this.name+"<input type='hidden' value='"+this.name+"' name='fileName'/>"+
					"<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><td><input type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()'/></td></tr>");
		})
	}else {
		 // 获取选择材料的数据
		
		//添加一行
		var i =tab1.rows.length-1;
		var n =tab1.rows.length-2;
		var Nam="'div1'";
		var Cod="fuJ"+i;
		//var totalprice=temp[3]*temp[]
		var newTr = tab1.insertRow();
		//添加列
		var newTd0 = newTr.insertCell(0);
		var newTd1 = newTr.insertCell(1);
		var newTd2 = newTr.insertCell(2);
		var newTd3 = newTr.insertCell(3);
		var newTd4 = newTr.insertCell(4);
		//设置列内容和属性
		newTd0.innerHTML = '<input type="hidden" name="purchaseInfos['+n+'].mt_id" value='+temp[0]+' id="mt_id'+i+'" /><input type="text" value='+temp[1]+'  readonly="readonly" style="width:80px;"/>';
		newTd1.innerHTML = '<input type="text" name="purchaseInfos['+n+'].mpinfo_standard" value="" datatype="*1-20" nullmsg="规格不可为空！" errormsg="规格不可为空,最多20字符！" style="width:80px;"/>';
		newTd2.innerHTML = '<input type="text" id="mpinfo_num" name="purchaseInfos['+n+'].mpinfo_num" value="" style="width:80px;" class="inputxt" datatype="/^\\d{1,8}(?:\\.\\d{0,2})?$/" nullmsg="数量不可为空，可有八位整数两位小数！" errormsg="数量不可为空，可有八位整数两位小数！"/>';
		newTd3.innerHTML = '<input type="text" id="mpinfo_price" name="purchaseInfos['+n+'].mpinfo_price" style="width:80px;" class="inputxt" />';
		//newTd4.innerHTML = '<a href="javascript:$(this).remove" style=\'color:red\'>aaa</a>';
		newTd4.innerHTML = '<input type="button" value="移除" onclick="javascript:$(this).parent().parent().remove()"/>'
	}
	 
}
/**
 * 添加/编辑页面提交
 */
/*function doSub() {
	$(".registerform").submit();
}
*/
/**
 * 删除
 */
function del(){
	if($("input[type='checkbox']").is(':checked')){
		$.confirm("是否要删除采购单？",
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

function showResult(btn){
	if("yes" == btn){
		document.listform.action = "purchase!delete.action";
		document.listform.submit();
		$.alert("删除成功！");
	}
}
/**
 * 入库操作
 */
function inputMaterial(mp_id){
	$.ajax({
		url:'purchase!inputMaterial.action?mp_id='+mp_id,
		dataType:'json',
		success:function(data){
			if('y'==data.status){
				$.alert('操作成功！');
				location.reload();
			}else{
				ajaxFileUpload();
			}
		},
		error:function(){
			$.alert('操作失败！');
		}
	});
}
