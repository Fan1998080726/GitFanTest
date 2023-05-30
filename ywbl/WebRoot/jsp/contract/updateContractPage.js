
function update(project_flag){
	var startDate = document.getElementById("c_start_date").value;
	var endDate = document.getElementById("c_end_date").value;
 
	if(startDate!='' && endDate!='' && (getDate(startDate)-getDate(endDate)>0)){
	    $.alert("结束时间不能小于开始时间");
	    return false;
	}
	
	if(13 == project_flag){
		$.confirm("是否确认提交，提交后信息将无法修改！",
				function(){
			showResult("yes",project_flag);// 确认按钮回调方法
		}, function(){
			
		});
	}else{
		document.getElementById("form").action="contract!updateContract.action?contract.c_flag="+project_flag;
		$(".registerform").submit();
	}
	

}

/**
 * 用户确认操作
 * @param btn
 */
function showResult(btn,project_flag){
	if("yes" == btn){
		document.getElementById("form").action="contract!updateContract.action?contract.c_flag="+project_flag;
		$(".registerform").submit();
	}
}


function getDate(date){
 var dates = date.split("-");
 var dateReturn = '';
 
 for(var i=0; i<dates.length; i++){
  dateReturn+=dates[i];
 }
 return dateReturn;
}

function delFile(pf_id,btn){
	var findex = getElementOrder(pf_id);//此处减1是因sourceTable中有一行是隐藏的
	document.getElementById("fileUploadTable").deleteRow(findex);
	document.getElementById("delFileId").value+=pf_id+",";
}

//  查询出将要删除的行所在的位置index
function getElementOrder(field){
    var i = 0;
    var order = 0;
    var elements = document.getElementsByName(field.name);
    for(i=0;i<elements.length;i++){
        order++;
        if(elements[i]==field){
            break;
        }
    }
    return order;
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
			if('n'==data.status){
				$.alert("工程名称已存在，请重新输入！");
				return;
			}
			parent.location.replace("contract!goMainPage.action?pro_id="+data.pro_id);
		}
	});
})

$.fn.runCode = function () {
	var getText = function(elems) {
		var ret = "", elem;

		for ( var i = 0; elems[i]; i++ ) {
			elem = elems[i];
			if ( elem.nodeType === 3 || elem.nodeType === 4 ) {
				ret += elem.nodeValue;
			} else if ( elem.nodeType !== 8 ) {
				ret += getText( elem.childNodes );
			};
		};

		return ret;
	};
	
	var code = getText(this);
	new Function(code).call(window);
	
	return this;
};

$(function(){
	// 按钮触发代码运行
	$(document).bind('click', function(event){
		var target = event.target,
			$target = $(target);

		if ($target.hasClass('runcode')) {
			$('#' + target.name).runCode();
		};
	});
});

/**
* 增加多个文件上传功能 fileSum 记录数量
*/
var fileSum=1;
function addFileUpload(){
	if(fileSum == 5){
		 $.alert("最多只能同时上传5个文件！");
		return;
	}
	fileSum = fileSum+1;
	$('#fileUploadTable').append('<tr><td width="100px"><label>文件名称：</label></td><td><input id="myFile_'+fileSum+'" type="file" name="myFile_'+fileSum+'"/></td></tr>');
}

function ajaxFileUpload(id){
	var result=0;
	for(var i=1;i<=fileSum;i++){
    	var FileId = 'myFile_'+i;
    	
    	$.ajaxFileUpload
        (
            {
                url:'project!ajaxFileUpload.action?id='+id,
                secureuri:false,
                fileElementId:FileId,
                dataType: 'text',
                success: function (data, status)
                {        
                	if(data == 'success'){

                	}else{
                		result=1;
                	}
                },
                error: function (data, status, e)
                {
                	return 1;
                }
            }
        )
        
    }
    return result;
}

function addFileUpload(){
	$.dialog({
		id:'uploadFilesId',
		url:'filesUpload!uploadFile.action',
		title: '上传文件', 
		width: 500, 
		height: 400,
		data:{
			filePath:'d:/contractFile/'
		}
		});
}
function getChildData(data){
	var json = $.parseJSON(data);
	$(json.files).each(function(){
		$('#fileUploadTable').append("<tr><td>"+this.name+"<input type='hidden' value='"+this.name+"' name='fileName'/>"+
				"<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><td><input type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()'/></td></tr>");
	})
}