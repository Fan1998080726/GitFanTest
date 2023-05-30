function maxDays(mm, yyyy){
var mDay;
	if((mm == 3) || (mm == 5) || (mm == 8) || (mm == 10)){
		mDay = 30;
  	}
  	else{
  		mDay = 31
  		if(mm == 1){
   			if (yyyy/4 - parseInt(yyyy/4) != 0){
   				mDay = 28
   			}
		   	else{
   				mDay = 29
  			}
		}
  }
return mDay;
}
function changeBg(id){
	if (eval(id).style.backgroundColor != "red"){
		eval(id).style.backgroundColor = "red"
	}
	else{
		eval(id).style.backgroundColor = "#87CEE4"
	}
}
function writeCalendar(){
var now = new Date
var dd = now.getDate()
var mm = now.getMonth()
var dow = now.getDay()
var yyyy = now.getFullYear()
var arrM = new Array("1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月")
var arrY = new Array()
	for (ii=0;ii<=4;ii++){
		arrY[ii] = yyyy - 2 + ii
	}
var arrD = new Array("日","一","二","三","四","五","六")

var text = ""
text = "<form name=calForm style='width:30%; float:left;'>"
text += "<table border=0 style='width:100%;'>"
text += "<tr><td>"
text += "<table width=100%><tr style='background:url(img/table-th.png); height:30px;'>"
text += "<td align=right>"
text += "<select name=selMonth onChange='changeCal()' style='width:80px; font-size:14px;'>"
	for (ii=0;ii<=11;ii++){
		if (ii==mm){
			text += "<option value= " + ii + " Selected>" + arrM[ii] + "</option>"
		}
		else{
			text += "<option value= " + ii + ">" + arrM[ii] + "</option>"
		}
	}
text += "</select>"
text += "</td>"
text += "<td align=left>"
text += "<select name=selYear onChange='changeCal()' style='width:80px; font-size:14px;'>"
	for (ii=0;ii<=4;ii++){
		if (ii==2){
			text += "<option value= " + arrY[ii] + " Selected>" + arrY[ii] + "</option>"
		}
		else{
			text += "<option value= " + arrY[ii] + ">" + arrY[ii] + "</option>"
		}
	}
text += "</select>"
text += "</td>"
text += "</tr></table>"
text += "</td></tr>"
text += "<tr><td style='height:122px; border:1px solid #A9B7C0; padding:1px;'>"
text += "<table border=0 style='width:100%; background:#87CEE4;'>"
text += "<tr style='background:#095787; height:22px; line-height:22px;'>"
	for (ii=0;ii<=6;ii++){
		text += "<td align=center><span class=label>" + arrD[ii] + "</span></td>"
	}
text += "</tr>"
aa = 0
	for (kk=0;kk<=5;kk++){
		text += "<tr>"
		for (ii=0;ii<=6;ii++){
			text += "<td align=center style='padding-top:1px;'><span id=sp" + aa + " onClick='changeBg(this.id)'>1</span></td>"
			aa += 1
		}
		text += "</tr>"
	}
text += "</table>"
text += "</td></tr>"
text += "</table>"
text += "</form>"
document.write(text)
changeCal()
}
function changeCal(){
var now = new Date
var dd = now.getDate()
var mm = now.getMonth()
var dow = now.getDay()
var yyyy = now.getFullYear()
var currM = parseInt(document.calForm.selMonth.value)
var prevM
	if (currM!=0){
		prevM = currM - 1
	}
	else{
		prevM = 11
	}
var currY = parseInt(document.calForm.selYear.value)
var mmyyyy = new Date()
mmyyyy.setFullYear(currY)
mmyyyy.setMonth(currM)
mmyyyy.setDate(1)
var day1 = mmyyyy.getDay()
	if (day1 == 0){
		day1 = 7
	}
var arrN = new Array(41)
var aa
	for (ii=0;ii<day1;ii++){
		arrN[ii] = maxDays((prevM),currY) - day1 + ii + 1
	}
	aa = 1
	for (ii=day1;ii<=day1+maxDays(currM,currY)-1;ii++){
		arrN[ii] = aa
		aa += 1
	}
	aa = 1
	for (ii=day1+maxDays(currM,currY);ii<=41;ii++){
		arrN[ii] = aa
		aa += 1
	}
	for (ii=0;ii<=41;ii++){
		eval("sp"+ii).style.backgroundColor = "#87CEE4"
	}
var dCount = 0
	for (ii=0;ii<=41;ii++){
		if (((ii<7)&&(arrN[ii]>20))||((ii>27)&&(arrN[ii]<20))){
			eval("sp"+ii).innerHTML = arrN[ii]
			eval("sp"+ii).className = "c3"
		}
		else{
			eval("sp"+ii).innerHTML = arrN[ii]
			if ((dCount==0)||(dCount==6)){
				eval("sp"+ii).className = "c2"
			}
			else{
				eval("sp"+ii).className = "c1"
			}
			if ((arrN[ii]==dd)&&(mm==currM)&&(yyyy==currY)){
				eval("sp"+ii).style.backgroundColor="#FF9900"
			}
		}
	dCount += 1
		if (dCount>6){
			dCount=0
		}
	}
}