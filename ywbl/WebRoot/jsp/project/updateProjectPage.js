

function update(project_flag){
	var startDate = document.getElementById("pro_start_date").value;
	var endDate = document.getElementById("pro_end_date").value;
 
	if(startDate!='' && endDate!='' && (getDate(startDate)-getDate(endDate)>0)){
	    $.alert("结束时间不能小于开始时间");
	    return false;
	}
	//获取select内的值，并赋值给selectUser
	var obj = document.getElementById('select2');
	var options = obj.options;
	var selectUser;
	
	if(options.length>0&&options.length<11){
	for(var i=0,len=options.length;i<len;i++){
	    var opt = options[i];
	    if(selectUser==null){
	    	selectUser =","+opt.value;
	    }else{
	    	selectUser =selectUser+","+opt.value;
	    }
	}
	$("#selectUser").attr("value",selectUser+",");
	}else{
		$.alert("技术员不能为0且不能超过10个，请编辑后重新提交！");
		return false;
	}
	if(8 == project_flag){
		$.ajax({
			url:'project!queryContractStatus.action',
			dataType:'json',
			success:function(data){
				if(data.status == '18'){
					$.confirm("是否确认提交，提交后计划进度和计划成本信息将无法修改！",
					function(){
						showResult("yes",project_flag);// 确认按钮回调方法
				}, function(){
					
				});
				}else{
					$.alert("本项目的合同没有审批通过，该项目不能提交！");
				}
			},
			error:function(){
				$.alert("提交失败！");
			}
		});
		
	}else{
		document.getElementById("form").action="project!updateProject.action?project.pro_flag="+project_flag;
		$(".registerform").submit();
	}
}


/**
 * 用户确认操作
 * @param btn
 */
function showResult(btn,project_flag){
	if("yes" == btn){
		document.getElementById("form").action="project!updateProject.action?project.pro_flag="+project_flag;
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
//注册建造师选择
function selectManaPage(){

	$.dialog({
		url:'project!selectManaPage.action',
		title: '选择注册建造师', 
		width: 800, 
		height: 600});
	
}
//选择注册建造师后
function getChildData(data){
	var temp2=data.split(";");
	if(temp2.length>1){
		var temp=temp2[1].split(",");
		if(undefined!=temp[0]&&undefined!=temp[1]){
			document.getElementById("pro_manager").value=temp[0];
			document.getElementById("pro_manager_name").value=temp[1];
		}
		
	}else{
		var json = $.parseJSON(data);
		$(json.files).each(function(){
			$('#fileUploadTable').append("<tr><td>"+this.name+"<input type='hidden' value='"+this.name+"' name='fileName'/>"+
					"<input type='hidden' name='filePath' value='"+this.realPath+"'/></td><td><input type='button' value='删除' onclick='javascript:$(this).parent().parent().remove()'/></td></tr>");
		})
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
			if('n'==data.status){
				$.alert("工程名称已存在，请重新输入！");
				return;
			}
			location.replace("project!getProjectPage.action");
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
$(function(){
	
	//移到右边
	$('#add').click(function(){
		//获取选中的选项，删除并追加给对方
		$('#select1 option:selected').appendTo('#select2');	
	});
	
	//移到左边
	$('#remove').click(function(){
		$('#select2 option:selected').appendTo('#select1');
	});
	
	//全部移到右边
	$('#add_all').click(function(){
		//获取全部的选项,删除并追加给对方
		$('#select1 option').appendTo('#select2');
	});
	
	//全部移到左边
	$('#remove_all').click(function(){
		$('#select2 option').appendTo('#select1');
	});
	
	//双击选项
	$('#select1').dblclick(function(){ //绑定双击事件
		//获取全部的选项,删除并追加给对方
		$("option:selected",this).appendTo('#select2'); //追加给对方
	});
	
	//双击选项
	$('#select2').dblclick(function(){
		$("option:selected",this).appendTo('#select1');
	});
	
});
/**
 * 弹出多文件上传窗口，返回添加后的json realName 带时间戳的文件名称（真实名称） 展示的名称应为 realName.substring(14)
 */
function addFileUpload(){
	$.dialog({
		id:'uploadFilesId',
		url:'filesUpload!uploadFile.action',
		title: '上传文件', 
		width: 350, 
		height: 300,
		data:{
			filePath:'d:/projectFile/'
		}
		});
}


var fileSum=1;
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
