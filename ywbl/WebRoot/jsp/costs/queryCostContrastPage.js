
var editingId;
var idIndex = 0;
$(function(){
    $('#tg').treegrid({
    	url:'cost!viewCostContrast.action',
       
        rownumbers: true,
        idField: 'id',
        treeField: 'name',
        columns: [[ {
            title: 'id',
            field: 'id',
            hidden: true
        },{
            title: '名称',
            field: 'name',
            width: 200
        },{
            title: 'is_child',
            field: 'is_child',
            hidden: true
        },{
            title: 'plan_id',
            field: 'plan_id',
            hidden: true
        },{
            title: '成本描述',
            field: 'plan_name',
            width: 100
        },{
            title: 'type_id',
            field: 'type_id',
            hidden: true
        },{
            title: '成本类型',
            field: 'type_name',
            width: 60
        },{
            title: '计划成本(元)',
            field: 'value',
            width: 90
        },{
            title: '消耗成本(元)',
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

function getChildData(date){
	location.reload();
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
	var ca_id = $("input[name='ca_id']:checked").val();
	if(null==ca_id){
		$.alert("请选择一条信息进行编辑！");
	}else{
	$.dialog({
		id: 'addChangeApply', //最好传入，否则不能弹出多个dialog
		url:'cost!getCostUpdatePage.action?type=update&ca_id='+ca_id,
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
	var ca_id = $("input[name='ca_id']:checked").val();
	if(null==ca_id){
		$.alert("请选择一条信息进行删除！");
	}else{
	$.dialog({
		id: 'addChangeApply', //最好传入，否则不能弹出多个dialog
		url:'cost!getCostUpdatePage.action?type=del&ca_id='+ca_id,
		title: '删除申请', 
		width: 400, 
		height: 300,
		data:{
		}
	});
	}
}