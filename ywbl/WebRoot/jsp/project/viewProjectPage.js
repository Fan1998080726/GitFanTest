
function gotoUpdate(){
	window.location.href="project!updateProjectPage.action?type=update";
}

function fileDownload(fileUrl,fileName){
	window.location.href="project!fileDownload.action?fileUrl="+fileUrl+"&fileName="+fileName;
}

function changeFlag(){
	if(""==$("#type").text()){
		$.alert("工程类型不可为空！");
		return;
	}
	if(""==$("#manager").text()){
		$.alert("注册建造师不可为空！");
		return;
	}
	$.ajax({
		url:'project!queryContractStatus.action',
		dataType:'json',
		success:function(data){
			if(data.status == '18'){
				$.confirm("是否确认提交，提交后计划进度和计划成本信息将无法修改！",
				function(){
					showResult("yes");// 确认按钮回调方法
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
}


function showResult(btn){
	if("yes" == btn){
		window.location.href="project!changeProjectFlag.action";
	}
}

$(function(){
	//$(".registerform").Validform();  //就这一行代码！;
		
	$(".registerform").Validform({
		tiptype:function(msg,o,cssctl){
			//msg：提示信息;
			//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
			//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
			if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
				var objtip=o.obj.siblings(".Validform_checktip");
				cssctl(objtip,o.type);
				objtip.text(msg);
			}
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
