<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
	<title>建筑市场监管信息平台</title> <%@ include file="/jsp/common/common.jsp"%>
	<!--表单验证-->
<link href="css/form/demo.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="css/form/style.css" type="text/css"
	media="all" />
<script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jsp/store/storetype/materialapply.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		var demo = $(".registerform").Validform({
			ajaxPost : true, //异步

			tiptype : function(msg, o, cssctl) {
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if (!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip = o.obj.siblings(".Validform_checktip");
					cssctl(objtip, o.type);
					objtip.text(msg);
				}
			},
			callback : function(data) {
				if (data.status == "y") {
					$.alert('保存成功！');
					$.closeDialog();
				} else {

					$.alert('保存失败！');
				}
			}
		});
		demo.addRule([ {
			ele : "select",
			datatype : "*"
		} ]);
	})
	
	function accMul(arg1,arg2){
	  var m=0,s1=arg1.toString(),s2=arg2.toString();
	  try{m+=s1.split(".")[1].length}catch(e){}
	  try{m+=s2.split(".")[1].length}catch(e){}
	  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
	}
	
	function calculate(){
		var num=$("#ma_num").val();
		var price=$("#ma_price").val();
		var a = accMul(num,price);
		$("#ma_cost").val(a.toFixed(2));
	}
</script>
<body>
	<div class="main">


		<div class="main5">
			<!--toolbar-->
			  <!--toolbar-->
		   <div class="toolbar">
		    <form method="post" id="userform">
		      <ul class="clearfix">
		        <li>
		            <span>快速查找</span> <input name="param_code" id="paramcode" value="${ param_code }" type="text" class="span3" placeholder="请输入类型...">
		        </li>
		        <li>
		          <!-- a href="javascript:chaxun()" ><img src="<%=path %>/img/search.png"></a-->
		          
		          <input type="button" onclick="chazhao();"
					style="background:url(img/search.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" />
		          <input type="button" onclick="doSub();"
					style="background:url(img/t-save.png) no-repeat; width:64px; height:25px; border:none; margin-bottom:10px;" />
		          
		        </li>
		      </ul>
		    </form>
		    <div class="clear"></div>
		  </div>
			
			<!--form-->
			<form id="form" class="registerform" action="storetype!addMaterialApply.action">
				<input type="hidden" name="materialApply.ma_flag" value="0">
				<!--table-->
				<table
					class="table2 table2-striped table2-bordered table2-condensed table2-down">
					<tr>
						<th style="width:25%;"><span class="need">*</span> 材料名:</th>
						<td style="width:25%;">
							<input type="hidden" id="mt_id" name="materialApply.mt_id" />
							<input type="text" id="mt_name" readonly="readonly"	class="inputxt"
									datatype="*1-20" errormsg="材料名不可为空！" />
							<div class="Validform_checktip">材料名不可为空！</div>
						</td>
						
						<th style="width:25%;"><span class="need">*</span> 数量:</th>
						<td style="width:25%;">
							<input type="text" id="ma_num" 
									name="materialApply.ma_num" class="inputxt" onblur="calculate();"
									datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="数量不可为空，可有八位整数两位小数！" />
							<div class="Validform_checktip">数量不可为空，可有八位整数两位小数！</div>
						</td>
					</tr>
					<tr>
						<th><span class="need">*</span> 单位:</th>
						<td>
							<input type="text" id="ma_unit" readonly="readonly"
									name="materialApply.ma_unit" class="inputxt"
									datatype="*1-20" errormsg="单位不可为空！" />
							<div class="Validform_checktip">单位不可为空！</div>
						</td>
						
						<th><span class="need">*</span> 日期:</th>
						<td><input type="text" readonly="readonly" onclick="$.calendar();"
							id="ma_date" name="materialApply.ma_apply_date" datatype="*1-20" class="inputxt" nullmsg="请选择日期！"  errormsg="请选择日期！" />
							<div class="Validform_checktip">日期不可为空！</div>
						</td>
					</tr>
					<tr>
						<th><span class="need">*</span> 单价（元）:</th>
						<td>
							<input type="text" id="ma_price" 
									name="materialApply.ma_price" onblur="calculate();" class="inputxt"
									datatype="/^\d{1,8}(?:\.\d{0,2})?$/" errormsg="单价不可为空,可有两位小数八位整数！" />
							<div class="Validform_checktip">单价为材料价格，不可为空，可有两位小数八位整数！</div>
						</td>
						
						<th> 总价（元）:</th>
						<td><input type="text" readonly="readonly" id="ma_cost"  class="inputxt" />
						</td>
					</tr>
				</table>
				
			</form>

	<!--table-->
        <div class="da-panel-content1">
        <form aciont="" method="post" id="listform" name="listform">
          <table class="table table-striped table-bordered table-condensed">
            <thead>
              <tr>
                <th style="width:30px; background:#e3f3f9; border-right:1px solid #91abb9;"></th>
                <th>材料名</th>
                <th>描述</th>
                <th>库存量</th>
                <th>单位</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach items="${list}" var="type" varStatus="stauts">
	              <tr onclick="getData(${type.mt_id },'${ type.mt_name }','${ type.mt_unit }');" <c:if test="${stauts.count%2==1 }">class="odd"</c:if>>
	             	<td style="background:#e3f3f9; border-right:1px solid #91abb9;"><input type="radio" value="${type.mt_id }" name="selectFlag"></td>
	                <td>${ type.mt_name }</td>
	                <td>${ type.mt_remark }</td>
	                <td>${ type.ml_num }</td>
	                <td>${ type.mt_unit }</td>
	              </tr>
			</c:forEach>
            </tbody>
          </table>
          </form>
          
          <!-------------显示页数开始------------>
			<DIV ID=TableTail>
				<DIV ID=PageSelectorBar>
					<jsp:include page="/jsp/page/page.jsp" />
				</DIV>
			</DIV> 
		  <!-------------显示页数结束--------->
        </div>

  </div>
  </div>
</body>
</html>