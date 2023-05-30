<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0092)http://bigapp.scedu.net/yqtb/mobile/?openid=_Z5um5kjcSk2Z3_GmpSUWK43CirFybhq4lMHXVgTyXU3pzj9 -->
<html lang="zh-CN" class="md device-pixel-ratio-1 device-desktop device-windows"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=edge"><meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no,maximum-scale=1,minimum-scale=1"><title>哈尔滨建筑业</title><link href="./EnterpriseService_files/framework7.d8abd4da.css" rel="stylesheet"><link href="./EnterpriseService_files/mobile_portal.f9e3945d.css" rel="stylesheet"></head><body>
    
    <meta http-equiv="Content-Security-Policy" content="default-src * &#39;self&#39; &#39;unsafe-inline&#39; &#39;unsafe-eval&#39; data: gap: content:">
<!--    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui, viewport-fit=cover">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="default">
    <meta name="theme-color" content="#2196f3">
    <meta name="format-detection" content="telephone=no">
    <meta name="msapplication-tap-highlight" content="no">

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <title>一地疫码现场管理系统</title>
<%@ include file="/common/common.jsp"%>
  <link rel="stylesheet" href="./CorpSystem_files/framework7.bundle4.4.min.css">
<!--    <link href="https://cdn.bootcss.com/framework7/5.5.0/css/framework7.bundle.min.css" rel="stylesheet">-->
    <link rel="stylesheet" href="./CorpSystem_files/icons.css">
    <link rel="stylesheet" href="./CorpSystem_files/app.css">
<script type="text/javascript">
function serch(){
	var prjName = document.getElementById("prjName").value;
	if(prjName.length>=4){
		$('#form1').submit();
	}else{
		alert("最少输入4个文字");
	}
// 	$('#form1').submit();
}


function qysub(id,type){
 
	location.replace("WxServlet?opflag=queryWxSendById&id="+id+"&type="+type);

}

</script>
<style type="text/css">
	ul{
		padding-top: 5% !important;
		font-size: 16px;
		background: #ddd;
	}
	.sli1{
		font-weight:bold;
		color: #333;
	}
	.sli2{
		padding-top: 5px !important;
	}
	.bg{
		background: #ddd;
	}
	hr{
	height:2px;
	}
</style>
</head>

<body style="visibility: visible;">
<div data-v-74dc8f02="" id="framework7-root" class="framework7-root">
  <div data-v-74dc8f02="" class="view view-main">
    <div data-v-3607b4f8="" class="page page-current">
      <div class="page-content"> 
          <div data-v-3607b4f8="" class="public-service">
            <div data-v-3607b4f8="" class="public-service-info">
              <div data-v-3607b4f8="" class="public-service-title">发送记录</div>
            </div>
            
                      <c:forEach items="${list }" var="m">
		 
<!-- 				<div style="height:1px;width:100%;border-top:1px solid #ccc;margin-top:15px;"></div> -->
            <div data-v-3607b4f8="" class="apps">
              <div data-v-26eaba11="" data-v-3607b4f8="" class="app">
                <div data-v-26eaba11="" class="app-ico"><img data-v-26eaba11="" src="./EnterpriseService_files/RYY.png" alt="" class="app-ico-img">
                    <!---->
                </div>
                <div data-v-26eaba11="" class="app-info">
                  <a href="#"  onclick="qysub('${m.id}','${m.type }');">  <div data-v-26eaba11="" class="app-name">${m.title}</div></a>
                  <div data-v-26eaba11="" class="app-desc">  </div>
                  <div data-v-3607b4f8="" class="public-service-desc">
                <p data-v-3607b4f8="">发送日期${m.id}${m.type}：${m.sendtime }</p>
              </div>
                  <div data-v-26eaba11="" class="app-state"></div>
                </div>
              </div>
              
            </div>
            
			</c:forEach>
            
            
            
            
            
            
          </div>
      
       
     
       
        <div data-v-7ea78fec="" data-v-3607b4f8="" class="footer-info">
          <div data-v-7ea78fec="">哈尔滨建筑业微信公众号</div>
          <div data-v-7ea78fec="">哈尔滨市住房和城乡建设局</div>
        </div>
      </div>
    </div>
  </div>
  <div class="framework7-modals"></div>
</div>
	 
</body></html>