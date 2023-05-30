$(function(){
		//父页面接值 计划进度id 隐藏域赋值
		var data = $.getParentData();
		//$('#cp_id').val(data.cp_id);
		$("input[name='costPlan.cp_id']").each(function (){
			$(this).attr('value',data.cp_id);
			});
		//$('#pc_id').val(data.pc_id);
		$("input[name='costPlan.pc_id']").each(function (){
			$(this).attr('value',data.pc_id);
			});
		//$('#cp_name').val(data.cp_name);
		$("input[name='costPlan.cp_name']").each(function (){
			$(this).attr('value',data.cp_name);
			});
		//$('#cp_type').val(data.cp_type);
		$("input[name='costPlan.cp_type']").each(function (){
			$(this).attr('value',data.cp_type);
			});
		//$('#cp_value').val(data.cp_value);
		$("input[name='costPlan.cp_value']").each(function (){
			$(this).attr('value',data.cp_value);
			});
		$("input[name='costPlan.cp_num']").each(function (){
			$(this).attr('value',data.cp_num);
			});
	});

function test(){
    document.getElementById('1').style.display='none';
    document.getElementById('2').style.display='none';
    document.getElementById('3').style.display='none';
    document.getElementById('35').style.display='none';
    var value=document.getElementById('cp_type').value;
    
    $("#aa").attr("onclick","save('"+value+"')");
    document.getElementById(value).style.display='block';
    
    var demo = $(".registerform"+value).Validform({
		ajaxPost:true,	 //异步
	
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
		callback : function(data) {
			if (data.status == "y") {
				//$.alert('保存成功！');
				$.closeDialog('','id1');
			} else {

				$.alert('保存失败！');
			}
		}
	});
	
	//通过demo.addRule 添加校验规则 根据空间类型
	demo.addRule([{
		ele:"select",
		datatype:"*"
	}]);
   }
   
   function selectMaterial() {
	   $.dialog({
		    id:'selectmaterial',
			url:'cost!selectMaterial.action',
			title: '计划成本-选择材料', 
			width: 600, 
			height: 500
		});
   }
   
   function getChildData(data){
	   
		var temp=data.split(",");
		$("input[name='costPlan.cp_name']").each(function (){
			$(this).attr('value',temp[1]);
			});
		$("input[name='costPlan.mt_num']").each(function (){
			$(this).attr('value',temp[2]);
			});
	}
   function checkForm() {
	 	//父页面接值 计划进度id 隐藏域赋值
		var data = $.getParentData();
	 	var value = data.type_id;
	 	var type_id = value-1;
	 	if(value==35){
	 		var type_id = 3;
	 	}
	  if(data.type_id!=null){
		  	document.getElementById('1').style.display='none';
		    document.getElementById('2').style.display='none';
		    document.getElementById('3').style.display='none';
		    document.getElementById('35').style.display='none';
		    //var value=data.type_id;
		    var objSelect = document.getElementById('cp_type');
		    $("#aa").attr("onclick","save('"+value+"')");
		    objSelect.options[type_id].selected = true;
		    document.getElementById(value).style.display='block';
	  }else{
		  test(); 	
	  }
   }
   
	//保存进度
 	function save(form_flag){
		var flag = form_flag;
 		$(".registerform"+flag).submit();
 	}
	//关闭页面
	function closeDialog(){
		$.closeDialog();
	}