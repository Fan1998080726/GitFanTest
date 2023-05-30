
var editingId;
	$(function(){
	    $('#tg').treegrid({
	    	url:'progress!viewProgressContrast.action?projectId=1',
	        idField: 'id',
	        treeField: 'name',
	        columns: [[ {
	            title: 'id',
	            field: 'id',
	            hidden: true
	        },{
	            title: 'plan_id',
	            field: 'plan_id',
	            hidden: true
	        },{
	            title: '名称',
	            align: 'left',
	            field: 'name',
	            width: 200
	        },{
	            title: '开始时间',
	            field: 'begin',
	            width: 90
	        },{
	            title: '结束时间',
	            field: 'end',
	            width: 90
	        },{
	            title: '计划工程量',
	            field: 'value',
	            width: 80
	        },{
	            title: '已完成工程量',
	            field: 'actual_value',
	            width: 90
	        },{
	            title: '状态',
	            field: 'is_end',
	            width: 50,
	            formatter:function(value, row, index){
	            	if('0'==row.is_end){//完结
	            		return "完结";
	            	}
	            	if('1'==row.is_end){//在建
	            		return "在建";
	            	}
	            	
				}
	        }]]
	    });
	});

	function addProgressActual(plan_id){
	}

function chaxun() {
	$.dialog({
		url:'jsp/costs/viewCostContrastPage.jsp',
		title: '工程成本对比详情', 
		width: 1000, 
		height: 400
		});
}

/**
 *  提交修改申请
 */
function update(){
	var pa_id = $("input[name='pa_id']:checked").val();
	if(null==pa_id){
		$.alert("请选择一条信息进行编辑！");
	}else{
	$.dialog({
		id: 'addChangeApply', //最好传入，否则不能弹出多个dialog
		url:'progress!getProgressUpdatePage.action?type=update&pa_id='+pa_id,
		title: '修改申请', 
		width: 400, 
		height: 300,
		data:{
		}
	});
	}
}

/**
 *  提交删除申请
 */
function del(){
	var pa_id = $("input[name='pa_id']:checked").val();
	if(null==pa_id){
		$.alert("请选择一条信息进行删除！");
	}else{
	$.dialog({
		id: 'addChangeApply', //最好传入，否则不能弹出多个dialog
		url:'progress!getProgressUpdatePage.action?type=del&pa_id='+pa_id,
		title: '删除申请', 
		width: 400, 
		height: 300,
		data:{
		}
	});
	}
}

function getChildData(date){
	location.reload();
}