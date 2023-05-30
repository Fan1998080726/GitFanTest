<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
    <%@page import="com.sdkj.util.context.Pagination"%>
        <html xmlns="http://www.w3.org/1999/xhtml">

        <head>
            <%@ include file="/jsp/common/common.jsp" %>
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                <title>报建登记管理</title>
                <link rel="stylesheet" type="text/css" href="css/table.css" media="screen" />
                <script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
                <script type="text/javascript" src="js/demo.tables.js"></script>
                <script type="text/javascript" src="<%=path %>/jsp/system/tBProjectInfo/queryTBProjectInfo.js"></script>
                <!--表单验证-->
                <link href="css/form/demo.css" type="text/css" rel="stylesheet" />
                <link rel="stylesheet" href="css/form/style.css" type="text/css" media="all" />
                <script type="text/javascript" src="js/form/Validform_v5.3.2_min.js"></script>
                <script type="text/javascript" src="js/lhgcalendar/lhgcalendar.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
                <link rel="stylesheet" type="text/css" href="css/table.css" media="screen" />
                <script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
                <script type="text/javascript" src="js/demo.tables.js"></script>
                <!--表单验证-->

                <script type="text/javascript" src="js/jspdf.debug.js"></script>
                <script type="text/javascript" src="js/jspdf.min.js"></script>
                <script type="text/javascript" src="js/html2canvas.js"></script>
                <script type="text/javascript" src="js/canvg2.js"></script>

                <script type="text/javascript" SRC="js/Cookie.js"></script>
                <script>
                    function locking() {
                        document.all.ly.style.display = "block";
                        document.all.ly.style.width = document.body.clientWidth;
                        document.all.ly.style.height = document.body.clientHeight;
                        document.all.Layer2.style.display = 'block';
                    }

                    function Lock_CheckForm(theForm) {
                        document.all.ly.style.display = 'none';
                        document.all.Layer2.style.display = 'none';
                        return false;
                    }

                    function getChildData() {
                        $('#listform').submit();
                    }

                    function choose(url, PrjNum, buildCorpName, buildCorpId, buildCorpCode, PrjName, PrjTypeNum, countyNum, date_, userName, shouji, buildCorpAddress) {
                        document.getElementById("PrjName").value = PrjName;
                        $('#tBProjectInfoform').attr("action", url + "?PrjNum=" + PrjNum + "&buildCorpName=" + buildCorpName +
                            "&buildCorpId=" + buildCorpId + "&buildCorpCode=" + buildCorpCode + "&PrjTypeNum=" + PrjTypeNum + "&countyNum=" + countyNum +
                            "&date_=" + date_);

                        var param = {};
                        param["userName"] = userName;
                        param["shouji"] = shouji;
                        param["buildCorpAddress"] = buildCorpAddress;
                        $.post("tbBuilderLicenceManageCopy!setBuildInfoTosession.action", param, function(data) {});

                        $('#tBProjectInfoform').submit();
                    }

                    function choose1(prjnum, PrjName, PrjTypeNum) {
                        document.getElementById("PrjName").value = PrjName;
                        $('#tBProjectInfoform').attr("action", "tbBuilderLicenceManageCopy!choose.action?prjnum=" + prjnum + "&PrjTypeNum=" + PrjTypeNum);
                        $('#tBProjectInfoform').submit();
                    }

                    function queryTBProjectInfoAllStep(url, PrjNum) {
                        $('#tBProjectInfoform').attr("action", "tBProjectInfo!queryTBProjectInfoAllStep.action?PrjNum=" + PrjNum);
                        $('#tBProjectInfoform').submit();
                    }

                    function queryViewmessage(id) {
                        $('#tBProjectInfoform').attr("action", "tBProjectInfo!getTBProjectInfoById.action?id=" + id);
                        $('#tBProjectInfoform').submit();
                    }

                    function aa() {

                        var targetDom = $("#pdfContainer");
                        //把需要导出的pdf内容clone一份，这样对它进行转换、微调等操作时才不会影响原来界面
                        var copyDom = targetDom.clone();
                        //新的div宽高跟原来一样，高度设置成自适应，这样才能完整显示节点中的所有内容（比如说表格滚动条中的内容）
                        copyDom.width(targetDom.width() + "px");
                        copyDom.height(targetDom.height() + "px");

                        $('body').append(copyDom); //ps:这里一定要先把copyDom append到body下，然后再进行后续的glyphicons2canvas处理，不然会导致图标为空

                        svg2canvas(copyDom);
                        html2canvas(copyDom, {
                            onrendered: function(canvas) {
                                var imgData = canvas.toDataURL('image/jpeg');
                                var img = new Image();
                                img.src = imgData;
                                //根据图片的尺寸设置pdf的规格，要在图片加载成功时执行，之所以要*0.225是因为比例问题
                                img.onload = function() {
                                    //此处需要注意，pdf横置和竖置两个属性，需要根据宽高的比例来调整，不然会出现显示不完全的问题
                                    if (this.width > this.height) {
                                        var doc = new jsPDF('l', 'mm', [this.width * 0.225, this.height * 0.225]);
                                    } else {
                                        var doc = new jsPDF('p', 'mm', [this.width * 0.225, this.height * 0.225]);
                                    }
                                    doc.addImage(imgData, 'jpeg', 0, 0, this.width * 0.225, this.height * 0.225);
                                    //根据下载保存成不同的文件名
                                    doc.save('pdf_' + new Date().getTime() + '.pdf');
                                };
                                //删除复制出来的div
                                copyDom.remove();
                            },
                            background: "#fff",
                            //这里给生成的图片默认背景，不然的话，如果你的html根节点没设置背景的话，会用黑色填充。
                            allowTaint: true //避免一些不识别的图片干扰，默认为false，遇到不识别的图片干扰则会停止处理html2canvas
                        });

                        function svg2canvas(targetElem) {
                            var svgElem = targetElem.find('svg');
                            svgElem.each(function(index, node) {
                                var parentNode = node.parentNode;
                                //由于现在的IE不支持直接对svg标签node取内容，所以需要在当前标签外面套一层div，通过外层div的innerHTML属性来获取
                                var tempNode = document.createElement('div');
                                tempNode.appendChild(node);
                                var svg = tempNode.innerHTML;
                                var canvas = document.createElement('canvas');
                                //转换
                                canvg(canvas, svg);
                                parentNode.appendChild(canvas);
                            });
                        }

                        function glyphicons2canvas(targetElem, fontClassName, fontFamilyName) {
                            var iconElems = targetElem.find('.' + fontClassName);
                            iconElems.each(function(index, inconNode) {
                                var fontSize = $(inconNode).css("font-size");
                                var iconColor = $(inconNode).css("color");
                                var styleContent = $(inconNode).attr('style');
                                //去掉"px"
                                fontSize = fontSize.replace("px", "");
                                var charCode = getCharCodeByGlyphiconsName(iconName);
                                var myCanvas = document.createElement('canvas');
                                //把canva宽高各增加2是为了显示图标完整
                                myCanvas.width = parseInt(fontSize) + 2;
                                myCanvas.height = parseInt(fontSize) + 2;
                                myCanvas.style = styleContent;
                                var ctx = myCanvas.getContext('2d');
                                //设置绘图内容的颜色
                                ctx.fillStyle = iconColor;
                                //设置绘图的字体大小以及font-family的名字
                                ctx.font = fontSize + 'px ' + fontFamilyName;
                                ctx.fillText(String.fromCharCode(charCode), 1, parseInt(fontSize) + 1);
                                $(inconNode).replaceWith(myCanvas);
                            });
                        }
                        //根据glyphicons/glyphicon图标的类名获取到对应的char code
                        function getCharCodeByGlyphiconsName(iconName) {
                            switch (iconName) {
                                case ("glyphicons-resize-full"):
                                    return "0xE216";
                                case ("glyphicons-chevron-left"):
                                    return "0xE225";
                                default:
                                    return "";
                            }
                        }
                    }


                    function editpage_cjp(PrjNum) {
                        if ($("input[type='checkbox']").is(':checked')) {
                            var a = $("input[type='checkbox'][name='selectFlag']:checked").length;
                            if (a == 1) {
                                var ids = "";
                                $("input[name='selectFlag']:checked").each(function() {
                                    ids = $(this).val();
                                });
                                $.ajax({
                                    url: 'tBProjectInfo!auditDate.action?ids=' + ids,
                                    success: function(data) {
                                        if (data == 'ok') {
                                            alert("超过规定时限不可修改数据！");
                                            return;
                                        }
                                        $('#tBProjectInfoform').attr("action", "tBProjectInfo!updateTBProjectInfoPage.action?tBProjectInfoVo.id=" + ids + "&PrjNum=" + PrjNum);
                                        $('#tBProjectInfoform').submit();
                                    }
                                });
                            } else {
                                $.alert("请选择一条信息进行编辑");
                            }
                        } else {
                            $.alert("请选择要编辑信息");
                        }
                    }
                </script>

        </head>

        <body id="pdfContainer">
            <!-- <input type="button" onclick="aa();" value="下载"/>
 <div  class="container"> -->
            <div class="main">
                <c:if test="${op==''||op=='zhzj'}">
                    <div class="breadcrumb">当前位置：报建登记管理</div>
                </c:if>
                <c:if test="${op=='tbBuilderLicenceManageCopy!queryTbBuilderLicenceManageCopy.action'}">
                    <div class="breadcrumb">当前位置：报建登记信息管理</div>
                </c:if>
                <c:if test="${op=='qualitySupervision!queryQualitySupervision.action'}">
                    <div class="breadcrumb">当前位置：单位工程质量监督管理</div>
                </c:if>
                <c:if test="${op=='safetyConstructionManage!querySafetyConstructionManage.action'}">
                    <div class="breadcrumb">当前位置：安全施工措施审查管理</div>
                </c:if>
                <c:if test="${op=='noUnpaidWages!queryNoUnpaidWages.action'}">
                    <div class="breadcrumb">当前位置：无拖欠证明管理</div>
                </c:if>
                <c:if test="${op=='tBTenderInfoKc!queryTBTenderInfoKc.action'}">
                    <div class="breadcrumb">当前位置：勘察中标备案信息管理</div>
                </c:if>
                <c:if test="${op=='tBTenderInfoSj!queryTBTenderInfoSj.action'}">
                    <div class="breadcrumb">当前位置：设计中标备案信息管理</div>
                </c:if>
                <c:if test="${op=='tBTenderInfoSg!queryTBTenderInfoSg.action'}">
                    <div class="breadcrumb">当前位置：施工中标备案信息管理</div>
                </c:if>
                <c:if test="${op=='tBTenderInfoJl!queryTBTenderInfoJl.action'}">
                    <div class="breadcrumb">当前位置：监理中标备案信息管理</div>
                </c:if>
                <c:if test="${op=='tBTenderInfoHw!queryTBTenderInfoHw.action'}">
                    <div class="breadcrumb">当前位置：货物中标备案信息管理</div>
                </c:if>
                <c:if test="${op=='tBTenderInfoZx!queryTBTenderInfoZx.action'}">
                    <div class="breadcrumb">当前位置：咨询中标备案信息管理</div>
                </c:if>
                <c:if test="${op=='tBTenderInfoLw!queryTBTenderInfoLw.action'}">
                    <div class="breadcrumb">当前位置：工程分包中标备案信息管理</div>
                </c:if>
                <c:if test="${op=='tBContractRecordManage!queryTBContractRecordManage.action'}">
                    <div class="breadcrumb">当前位置：设计合同备案管理</div>
                </c:if>
                <c:if test="${op=='tBProjectCensorInfo!queryTBProjectCensorInfo.action'}">
                    <div class="breadcrumb">当前位置：施工图审查备案管理</div>
                </c:if>
                <c:if test="${op=='tBContractRecordManageKC!queryTBContractRecordManageKC.action'}">
                    <div class="breadcrumb">当前位置：勘察合同备案管理</div>
                </c:if>
                <c:if test="${op=='feeBasedContent!queryFeeBasedContent.action'}">
                    <div class="breadcrumb">当前位置：收费信息管理</div>
                </c:if>
                <c:if test="${op=='tBContractRecordManageZb!queryTBContractRecordManageZb.action'}">
                    <div class="breadcrumb">当前位置：施工总包合同管理</div>
                </c:if>
                <c:if test="${op=='tBContractRecordManagefb!queryTBContractRecordManagefb.action'}">
                    <div class="breadcrumb">当前位置：专业分包合同管理</div>
                </c:if>
                <c:if test="${op=='tBContractRecordManagelw!queryTBContractRecordManagelw.action'}">
                    <div class="breadcrumb">当前位置：劳务分包合同管理</div>
                </c:if>
                <c:if test="${op=='tBContractRecordManagejl!queryTBContractRecordManagejl.action'}">
                    <div class="breadcrumb">当前位置：监理合同备案管理</div>
                </c:if>
                <c:if test="${op=='supervisionGroup!querySupervisionGroup.action'}">
                    <div class="breadcrumb">当前位置：大队工程检查情况日报管理</div>
                </c:if>
                <c:if test="${op=='tBProjectFinishManage!queryTBProjectFinishManage.action'}">
                    <div class="breadcrumb">当前位置：竣工验收备案信息管理</div>
                </c:if>
                <c:if test="${op=='tBProjectDesignEconUserInfo!queryTBProjectDesignEconUserInfo.action'}">
                    <div class="breadcrumb">当前位置：勘察设计审图人员明细管理</div>
                </c:if>
                <c:if test="${op=='tBProjectBuilderUserInfo!queryTBProjectBuilderUserInfo.action'}">
                    <div class="breadcrumb">当前位置：施工安全从业人员明细管理</div>
                </c:if>
                <c:if test="${op=='qualityCheckRecord!queryQualityCheckRecord.action'}">
                    <div class="breadcrumb">当前位置：现场监管管理</div>
                </c:if>
                <c:if test="${op=='supervisionInfo!querySupervisionInfo.action'}">
                    <div class="breadcrumb">当前位置：散办现场监管管理</div>
                </c:if>
                <c:if test="${op=='punishInfo!queryPunishInfo.action'}">
                    <div class="breadcrumb">当前位置：企业不良行为管理</div>
                </c:if>
                <c:if test="${op=='shutdownRepriseManage!queryShutdownRepriseManage.action'}">
                    <div class="breadcrumb">当前位置：停复工管理</div>
                </c:if>
                <c:if test="${op=='jointNotification!queryJointNotification.action'}">
                    <div class="breadcrumb">当前位置：现场通报管理</div>
                </c:if>
                <c:if test="${op=='punishInfo!queryPunishInfoGood.action'}">
                    <div class="breadcrumb">当前位置：企业良好行为管理</div>
                </c:if>
                <c:if test="${op=='punishInfo!queryPunishInfoPeopleBad.action'}">
                    <div class="breadcrumb">当前位置：注册人员不良行为管理</div>
                </c:if>
                <c:if test="${op=='punishInfo!queryPunishInfoPeopleGood.action'}">
                    <div class="breadcrumb">当前位置：注册人员良好行为管理</div>
                </c:if>
                <div class="main3">

                    <div class="toolbar">
                        <div class="action">

                            <c:if test="${dept_name!='企业用户组' }">
                                <input name="btn_search" type="button" onclick="javascript:chaxun('${ PrjNum }')" value="" style="background:url(img/search.png) no-repeat;" />
                                <c:if test="${username=='曹继平'}">
                                    <input name="btn_edit" type="button" onclick="editpage_cjp('${ PrjNum }')" value="" style="background:url(img/t-edit.png) no-repeat;" />
                                </c:if>
                                <c:if test="${username=='曹继平' or dept_name=='建管处'}">
                                    <%--          <input name="t-down" type="button" onclick="javascript:exportExcel('${ PrjNum }')" value="" style="background:url(img/t-down.png) no-repeat;"/> --%>
                                </c:if>
                            </c:if>
                            <c:if test="${dept_name=='企业用户组' }">
                                <input name="btn_search" type="button" onclick="javascript:chaxun('${ PrjNum }')" value="" style="background:url(img/search.png) no-repeat;" />
                                <input name="btn_add" type="button" onclick="updateTBProjectInfoPage('${ PrjNum }')" value="" style="background:url(img/t-add.png) no-repeat;" />
                                <input name="btn_edit" type="button" onclick="editpage('${ PrjNum }')" value="" style="background:url(img/t-edit.png) no-repeat;" />
                                <input name="btn_delete" type="button" onclick="del('${ PrjNum }')" value="" style="background:url(img/t-delete.png) no-repeat;" />
                            </c:if>
                        </div>
                        <form method="post" id="tBProjectInfoform">
                            <input id="PrjName" type="hidden" name="PrjName" value="" />
                            <input id="opform" type="hidden" name="opform" value="" />
                            <input type="hidden" name="op" value="${ op }" />
                            <ul class="clearfix">

                                <!-- begin -->

                                <c:if test="${username!='曹继平'}">
                                  
                                        <span>项目编号:</span>
                                        <input name="queryprjNum" id="queryprjNum" value="${ queryprjNum }" type="text" class="span3" placeholder="请输入文字..." style="width:100px;" />
                                  
                                </c:if>

                             
                                    <span>项目名称：</span>
                                    <input name="prjName" id="prjName" value="${ prjName }" type="text" class="span3" placeholder="请输入文字..." style="width:100px;" />
                              

                                <c:if test="${dept_name!='企业用户组' }">
                                    <c:if test="${username=='曹继平'}">
                                      
                                            <span>编号:</span>
                                            <input name="num" id="num" value="${ num }" type="text" class="span3" placeholder="请输入文字..." style="width:100px;" />
                                       
                                    </c:if>

                                    
                                        <span>建设单位：</span>
                                        <input name="buildCorpName" id="buildCorpName" value="${ buildCorpName }" type="text" class="span3" placeholder="请输入文字..." style="width:100px;" />

                                  
                                        <span>分类： </span>
                                        <select name="queryprjTypeNum" id="PrjTypeNum">
         	<option value="" <c:if test="${queryprjTypeNum=='' }">selected</c:if>>选择分类</option>
    		<option value="01" <c:if test="${queryprjTypeNum=='01' }">selected</c:if>>房屋建筑</option>
    		<option value="02" <c:if test="${queryprjTypeNum=='02' }">selected</c:if>>市政工程</option>
    		<option value="99" <c:if test="${queryprjTypeNum=='99' }">selected</c:if>>其他</option>
    </select>
                                    <!--监理 -->

                                    <c:if test="${username!='曹继平'}">
                                      

                                            <span>年度： </span>
                                            <select name="queryyear">
         	<option value="all" <c:if test="${queryyear=='all' }">selected</c:if>>全部</option>
         	<option value="2019" <c:if test="${queryyear=='2019' }">selected</c:if>>2019</option>
         	<option value="2018" <c:if test="${queryyear=='2018' }">selected</c:if>>2018</option>
    		<option value="2017" <c:if test="${queryyear=='2017' }">selected</c:if>>2017</option>
    		<option value="2016" <c:if test="${queryyear=='2016' }">selected</c:if>>2016</option>
    		<option value="2015" <c:if test="${queryyear=='2015' }">selected</c:if>>2015</option>
    		<option value="2014" <c:if test="${queryyear=='2014' }">selected</c:if>>2014</option>
    		<option value="2013" <c:if test="${queryyear=='2013' }">selected</c:if>>2013</option>
    		<option value="2012" <c:if test="${queryyear=='2012' }">selected</c:if>>2012</option>
    		<option value="2011" <c:if test="${queryyear=='2011' }">selected</c:if>>2011</option>
    </select> 
                                        <!--       <span>状态：</span><select name="status" > -->
                                        <%--          	<option value="" <c:if test="${status=='' }">全部</c:if>>全部</option> --%>
                                            <%--     		<option value="待办" <c:if test="${status=='待办' }">selected</c:if>>待办</option> --%>
                                                <%--     		<option value="已办" <c:if test="${status=='已办' }">selected</c:if>>已办</option> --%>
                                                    <!--     </select> -->
                                    </c:if>

                                </c:if>


                                <!-- end -->



                                <c:if test="${username=='曹继平'}">
                                   
                                        <span>报建日期： </span>

                                        <input name="date_1" id="date_1" value="${ date_1 }" type="text" class="span3" placeholder="请输入文字..." onclick="$.calendar();" readonly="readonly" style="width:70px;" /> 到：
                                        <input name="date_2" id="date_2" value="${ date_2 }" type="text" class="span3" placeholder="请输入文字..." onclick="$.calendar();" readonly="readonly" style="width:70px;" />
                                    
                                </c:if>

                            </ul>
                        </form>
                        <div class="clear"></div>
                    </div>


                    <!--table-->
                    <form aciont="tBProjectInfo!queryTBProjectInfo.action" method="post" name="listform" id="listform">
                        <div class="da-panel-content table-responsive">
                            <table class="layui-table" lay-even lay-skin="row" lay-size="sm">
                                <thead>
                                    <tr>
                                        <%--  <c:if test="${op!=''}"> --%>
                                            <c:if test="${dept_name=='企业用户组' }">
                                                <th style="width:30px; background:#e3f3f9; border-left:1px solid #91abb9; border-right:1px solid #91abb9;">
                                                    <input type="checkbox" value="option1" id="checkAll">
                                                </th>
                                            </c:if>
                                            <c:if test="${username=='曹继平'}">
                                                <th style="width:30px; background:#e3f3f9; border-left:1px solid #91abb9; border-right:1px solid #91abb9;">
                                                    <input type="checkbox" value="option1" id="checkAll">
                                                </th>
                                            </c:if>
                                            <%--     </c:if> --%>
                                                <th><img src="img/th-img.png" /> 序号</th>
                                                <th><img src="img/th-img.png" /> 项目编号</th>
                                                <c:if test="${username=='曹继平'}">
                                                    <th><img src="img/th-img.png" /> 编号</th>
                                                </c:if>
                                                <th><img src="img/th-img.png" /> 报建日期</th>
                                                <th><img src="img/th-img.png" /> 项目名称</th>
                                                <th><img src="img/th-img.png" /> 项目分类</th>
                                                <th><img src="img/th-img.png" /> 建设单位</th>
                                                <th><img src="img/th-img.png" /> 建设规模</th>
                                                <th><img src="img/th-img.png" />总投资额（万元）</th>
                                                <!--                         <th><img src="img/th-img.png"/> 办理状态</th> -->
                                                <!--   <th><img src="img/th-img.png"/>填报单位是否已承诺<br>对所录入信息真实准确性负责</th> -->
                                                <%--  20201019 <c:if test="${dept_name=='建管处' || username=='单海波'}">
                      <th ><img src="img/th-img.png"/>录入人</th> 
                     <th ><img src="img/th-img.png"/>录入时间</th>
                     <th ><img src="img/th-img.png"/>最后修改时间</th>
                </c:if> --%>

                                                    <th style="width:7%"><img src="img/th-img.png" /> 操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${list}" var="m" varStatus="stauts">
                                        <tr <c:if test="${stauts.count%2==1 }">class="odd"</c:if> ondblclick="queryViewmessage('${ m.id}')">
                                            <%-- <c:if test="${op!=''}"> --%>
                                                <c:if test="${dept_name=='企业用户组' }">
                                                    <td style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;">
                                                        <c:if test="${m.state=='办理中'||m.state=='退回'}">
                                                            <input type="checkbox" name="selectFlag" value="${m.id }" id="checkbox_id" />
                                                        </c:if>
                                                    </td>

                                                </c:if>
                                                <c:if test="${username=='曹继平'}">
                                                    <td style="background:#e3f3f9; border-left:1px solid #91abb9;  border-right:1px solid #91abb9;">
                                                        <input type="checkbox" name="selectFlag" value="${m.id }" id="checkbox_id" />
                                                    </td>
                                                </c:if>
                                                <td>${ stauts.count }</td>
                                                <td>
                                                    <div>${ m.prjNum }</div>
                                                </td>
                                                <c:if test="${username=='曹继平'}">
                                                    <td>${ m.num }</td>
                                                </c:if>
                                                <td>${ m.date_ }</td>
                                                <td>
                                                    <div align="left">${ m.prjName }</div>
                                                </td>
                                                <td>
                                                    <c:if test="${m.prjTypeNum=='01'}">
                                                        房屋建筑
                                                    </c:if>
                                                    <c:if test="${m.prjTypeNum=='02'}">
                                                        市政建筑
                                                    </c:if>
                                                    <c:if test="${m.prjTypeNum=='99'}">
                                                        其它
                                                    </c:if>
                                                </td>
                                                <td>
                                                    <div align="left">${ m.buildCorpName }</div>
                                                </td>
                                                <td>
                                                    <div align="left">${ m.allArea }${m.mjdw }</div>
                                                </td>
                                                <td>
                                                    <div align="left">${ m.allInvest }</div>
                                                </td>
                                                <%-- 	                 <td>${ m.state }</td> --%>
                                                    <!-- <td>是</td> -->
                                                    <%--   <c:if test="${dept_name=='建管处' || username=='单海波' }">
	                   <td ><div style='width: 50px;display:block;word-break: break-all;word-wrap: break-word;'>${ m.lrr }</div></td> 
	                   <td>${ m.sysTime}</td>
	                   <td>${ m.lastupdatetime }</td>
	                 </c:if>     --%>
                                                        <%--      <td>${ m.state }</td> --%>

                                                            <td>
                                                                <%--  20201014 <c:if test="${op!=''}"> 
	                      <c:if test="${m.state=='已办结'}">
	                   <a href="#" onclick="choose('${op}','${m.prjNum }','${m.buildCorpName }','${m.buildCorpId }','${m.buildCorpCode }','${m.prjName}','${m.prjTypeNum}','${m.countyNum }','${m.date_ }','${m.userName}','${m.shouji }','${m.buildCorpAddress}');"><img src="img/sgxk.png"></img></a> 
	                   <a href="#" onclick="choose1('${m.prjNum}','${m.prjName}','${m.prjTypeNum}');"><img src="img/xz.png"></img></a>
	                  
	                   
	                   
	                  </c:if>
	                   
	                    </c:if>
	                    
	                     --%>
                                                                    <c:if test="${m.state=='已办结'}">
                                                                        <c:if test="${username=='曹继平'}">
                                                                            <a href="#" onclick="print_copy('${ m.id}')"><img src="img/t-print.png"></img>
                                                                            </a>
                                                                        </c:if>
                                                                    </c:if>
                                                                    <a href="#" onclick="queryViewmessage('${ m.id}')">
                                                                        <c:if test="${dept_name=='企业用户组' }">
                                                                            <img src="img/xiangqingOfsgxk.png"></img>
                                                                        </c:if>
                                                                        <c:if test="${dept_name!='企业用户组' }">
                                                                            <img src="img/xiangqing.png"></img>
                                                                        </c:if>
                                                                    </a>
                                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${username=='曹继平'}">
                                        <c:if test="${queryprjTypeNum=='01' || queryprjTypeNum=='02'  || queryprjTypeNum=='03'}">
                                            <tr>
                                                <td></td>
                                                <td>合计</td>
                                                <td></td>

                                                <td></td>

                                                <td></td>
                                                <td></td>
                                                <td>
                                                </td>
                                                <td>
                                                    <div align="left">${ infoTj.allArea }
                                                        <c:if test="${queryprjTypeNum=='01'}">平方米</c:if>
                                                        <c:if test="${queryprjTypeNum=='02'}">延长米</c:if>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div align="left">${infoTj.allInvest }万元</div>
                                                </td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                </tbody>
                            </table>
                    </form>
                    <!-------------显示页数开始------------>
                    <DIV ID=TableTail>
                        <DIV ID=PageSelectorBar>
                            <jsp:include page="/jsp/page/page.jsp" />
                        </DIV>
                    </DIV>
                    <!-------------显示页数结束------------>
                    </div>
                </div>
                <!-- </div> -->
        </body>

        </html>