function show1(qc_id){
	var url = "quality!showQualityControl.action?qc_id="+qc_id+"";
	var title = "质量管控详情";
	
	$.dialog({
		url:url,
		title: title, 
		data:{
			
		},
		width: 450, 
		height: 400
		});
}
function show2(sc_id){
	var url = "safe!showSafeControl.action?sc_id="+sc_id+"";
	var title = "安全管控详情";
	
	$.dialog({
		url:url,
		title: title, 
		data:{
			
		},
		width: 450, 
		height: 400
		});
}