String.prototype.Trim = function(){ return Trim(this);}
String.prototype.LTrim = function(){return LTrim(this);}
String.prototype.RTrim = function(){return RTrim(this);}
//此处为独立函数
function LTrim(str)
{
    var i;
    for(i=0;i<str.length;i++)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(i,str.length);
    return str;
}
function RTrim(str)
{
    var i;
    for(i=str.length-1;i>=0;i--)
    {
        if(str.charAt(i)!=" "&&str.charAt(i)!=" ")break;
    }
    str=str.substring(0,i+1);
    return str;
}
function Trim(str)
{
    return LTrim(RTrim(str));
}
// 请求提交
function doPostBack(pagerName, arg) {
	try{
		var offsetInput = document.getElementsByName(pagerName + '.offset')[0];
		
		if (isNaN(arg)) {
			// 每页记录数改变
			if (arg.toLowerCase() == 'maxpageitems') {
				PT_CheckSubmit(pagerName,1);
			} 
			// 页面跳转
			else if (arg.toLowerCase() == 'pagenumber') {
				PT_CheckSubmit(pagerName,2);
			}
		}
		// 如arg为数字表示跳转至某页
		else {
			if (arg > 0)
				arg--; // 索引从0开始，此处减1
			offsetInput.value = arg;
			PT_CheckSubmit(pagerName,1);
		}
	} catch(e){
		alert('分页出现异常:'+e.message);
	}
}

// 提交验证
function PT_CheckSubmit(pagerName, type){
	
	var submit=document.getElementsByName(pagerName + '.submit')[0];
	
	// 直接提交
	if(type==1){
		var clickObj=submit.onclick;
		submit.onclick=null;	// 去除onclick事件，以免进行"转到"操作
		submit.click();
		submit.onclick=clickObj;// 恢复onclick事件
	}
	// 验证跳转输入信息
	else if(type==2){
		var offsetInput = document.getElementsByName(pagerName + '.offset')[0];
		var pageInput = document.getElementsByName(pagerName + '.pageNumber')[0];
		var pageSizeInput = 15;
		var oldVal=pageInput.getAttribute("number");
		var skipNumber=pageInput.value;
		//var pages=pageInput.getAttribute("pages");
		var totalCount = document.getElementById("totalCount").value/1;
		var pages = Math.ceil(totalCount / 15) ;
		while(skipNumber.indexOf(' ')>0) skipNumber=skipNumber.replace(' ','');
		if(skipNumber.Trim()==''){
			pageInput.value=oldVal;
			pageInput.focus(); 
			return false;
		}
		// 非数字则恢复原值
		if(isNaN(skipNumber)) {
			pageInput.value=oldVal;
			pageInput.focus();
			return false;
		}
		if(parseInt(skipNumber)<=0||parseInt(skipNumber)>pages){
			$.alert('索引超出范围！');
			pageInput.focus();
			return false;
		}
		// 计算记录索引
		offsetInput.value = (skipNumber-1) * pageSizeInput;
	}
}
