var contractCopies = new Object();
contractCopies['叁']="壹";
contractCopies['伍']="贰";
contractCopies['柒']="叁";
contractCopies['玖']="肆";
contractCopies['拾壹']="伍";
contractCopies['拾叁']="陆";
contractCopies['拾伍']="柒";

function goB0() {

	 $(window).scrollTop(0);
	document.getElementById("load0").style.display = "block";
	document.getElementById("load1").style.display = "none";
	document.getElementById("load2").style.display = "none";
	document.getElementById("load3").style.display = "none";
}
function goA1() {
	 $(window).scrollTop(0);
//	document.getElementById("load1").click();
//	document.getElementById('moreMerchant').scrollIntoView();
	document.getElementById("load0").style.display = "none";
	document.getElementById("load1").style.display = "block";
	document.getElementById("load2").style.display = "none";
	document.getElementById("load3").style.display = "none";
}
function goA2() {
	
	 $(window).scrollTop(0);

	document.getElementById("load0").style.display = "none";
	document.getElementById("load1").style.display = "none";
	document.getElementById("load2").style.display = "block";
	document.getElementById("load3").style.display = "none";
}
function goA3() {

	 $(window).scrollTop(0);
	document.getElementById("load0").style.display = "none";
	document.getElementById("load1").style.display = "none";
	document.getElementById("load2").style.display = "none";
	document.getElementById("load3").style.display = "block";
}
function selectCopies(obj){
	var kkk = obj.value;
	document.getElementById("contractCopy").value=contractCopies[kkk];
}
