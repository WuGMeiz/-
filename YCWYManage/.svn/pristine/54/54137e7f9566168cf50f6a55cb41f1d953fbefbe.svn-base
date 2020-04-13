/******************全局变量的声明以及AJAX的设置*****************************************************************************/
	var http_request = false;
	var currentPos = null;
/****以上为AJAX声明方法的全局变量****/	
	var URL2=null; //存返回会成功后再次调用页面的路径
	var OBJ=null; //存返回会成功后再次显示页面的DIV层ID
	var YWURL=null; //存放交易成功后请求的业务路径
/*****************************************************正则表达式****************************************************/	
	var isPrice = /^\d+(.\d{1,2})?$/;//验证最多保留两位小数
	var regmoney=/^\d+(\.\d+)?$/; // 验证货币的正则表达式
	var reghow=/^\d+$/; // 验数量必须为数字的正则表达式
	var regstring = /^[A-Za-z0-9_.@-]+$/; //验证是否包含非法字符的正则表达式

	var regstr= /^[A-Za-z0-9]+$/;         //验证全部是字母和数字的正则表达式  
	var regstrCN= /^[^A-Za-z0-9]+$/;                          //验证全部不是字母和数字的正则表达式  
	var regstrSY= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\-\,\.\<\>\？\，\。]+$/;         //验证全部不是特殊字符的正则表达式
		
	var regphone=/^1[0-9]{10}/;	//手机
	var regtel=/^([0-9]{3,4}-)|([0-9]{3,4})[1-9]{1}[0-9]{6,7}$/;	//固定电话
	var regEmail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;//电子邮箱 
	
	var regjfq=/^[2-9]{1}[0-9]{5}/; // 验证缴费期格式为：201401
/*****************************************************正则表达式****************************************************/	
	
function send_request(url) {//初始化、指定处理函数、发送请求的函数

	http_request = false;
	//开始初始化XMLHttpRequest对象
	if(window.XMLHttpRequest) { //Mozilla 浏览器
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {//设置MiME类别
			http_request.overrideMimeType('text/xml');
		}
	}
	else if (window.ActiveXObject) { // IE浏览器
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	if (!http_request) { // 异常，创建对象实例失败
		window.alert("不能创建XMLHttpRequest对象实例.");
		return false;
	}
	http_request.onreadystatechange = processRequestSwept;
	// 确定发送请求的方式和URL以及是否同步执行下段代码
	http_request.open("GET", url, true);
	http_request.send(null);
}
function sendPOSTSwept(url,QString) {

	http_request = false;
	//开始初始化XMLHttpRequest对象
	if(window.XMLHttpRequest) { //Mozilla 浏览器
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {//设置MiME类别
			http_request.overrideMimeType('text/xml');
		}
	}
	else if (window.ActiveXObject) { // IE浏览器
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	if (!http_request) { // 异常，创建对象实例失败
		window.alert("不能创建XMLHttpRequest对象实例.");
		return false; 
	}
 http_request.onreadystatechange = processRequestSwept;
 http_request.open("POST", url, true);
 http_request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 http_request.send(QString);

 }
/***主显示区域查询后显示区域页面的方法***/
function showselect(returnDIVID,url)
{
	//alert(url);
	currentPos=returnDIVID;
	
	send_request(url);
}
// 处理返回信息的函数
function processRequestSwept() {
    if (http_request.readyState == 4) { // 判断对象状态
        if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
            
        	var res_str=http_request.responseText.split('-'); //获得返回的数据进行截取
			var tempcode=trim(res_str[0]);  //temp得到去掉空格后的字符串
            var tempmsg=res_str[1];  //temp得到去掉空格后的字符串
            
            if(tempcode=="Y")
            {
            	allTime=10;
            	$(".orderState").text("交易成功！");
                $(".coverContent .tip").text(tempmsg);
                $(".cover .close").removeAttr("disabled");   //扫码成功，关闭取消支付功能
                showselect(OBJ,URL2);
            }
            else if(tempcode=="N")
            {
            	allTime=30;
            	$(".orderState").text("交易失败！");
            	$(".coverContent .tip").text(tempmsg);
            	$(".cover .close").removeAttr("disabled");   //扫码成功，关闭取消支付功能
            	//clearInterval(timer1);//清除计时器
            	//该处做更新本地订单的业务处理
            }
            else
            {
            	document.getElementById(currentPos).innerHTML = http_request.responseText;
				$(".cover").click(); //执行一次点击事件，保证输入框聚焦
            }
		    
          /*var res_txt=http_request.responseText; //获得返回的数据信息
		    var JSONOBJ = null;//生命JSON对象，用于存储支付返回信息
		    if(isJSON(res_txt))	//如果返回的数据串格式为JSON格式时执行
		    {
		    	JSONOBJ = JSON.parse(res_txt);//将返回的json数据串转换成JSON对象
	            if(JSONOBJ.RESULT=="Y") //扣款成功返回成功消息后，处理业务
	            {	            	
	                //该处做更新本地订单的业务处理
	                var BANKID = JSONOBJ.BANKID;
	                var ORDERID = JSONOBJ.ORDERID;
	                var PAYMENT = JSONOBJ.PAYMENT;
	                var RESULT = JSONOBJ.RESULT;
	                var SIGN = JSONOBJ.SIGN;
	        	    var QString = "BANKID="+BANKID+"&ORDERID="+ORDERID+"&PAYMENT="+PAYMENT+"&RESULT="+RESULT+"&SIGN="+SIGN;
	        	    sendPOST(YWURL, QString);
	            }       
	            if(JSONOBJ.RESULT=="N")//调用接口支付业务失败返回
	            {
	            	allTime=30;
	            	var msgstr = "错误码："+JSONOBJ.RETURN_CODE+" 错误信息："+JSONOBJ.RETURN_MSG;
	            	$(".orderState").text("交易失败！");
	            	$(".coverContent .tip").text(msgstr);
	            	$(".cover .close").removeAttr("disabled");   //扫码成功，关闭取消支付功能
	            	//clearInterval(timer1);//清除计时器
	            	//该处做更新本地订单的业务处理
	            }
		    }
            else 
            {
            	var res_str=http_request.responseText.split('-'); //获得返回的数据进行截取
    			var tempcode=trim(res_str[0]);  //temp得到去掉空格后的字符串
                var tempmsg=res_str[1];  //temp得到去掉空格后的字符串
                
                if(tempcode=="ok")
                {
                	allTime=10;
                	$(".orderState").text("交易成功！");
	                $(".coverContent .tip").text(tempmsg);
	                $(".cover .close").removeAttr("disabled");   //扫码成功，关闭取消支付功能
                }
                else
                {
                	document.getElementById(currentPos).innerHTML = http_request.responseText;
    				$(".cover").click(); //执行一次点击事件，保证输入框聚焦
                }
        	}*/
        	     			
        } else { //页面不正常
            alert("您所请求的页面有异常。"+http_request.status);
        }
    }
}
/******************************************************************************************/
/****去空格的方法****/
function ltrim(s)	//去左空格;
{
    return s.replace( /^\s*/, ""); 
}

function rtrim(s)	//去右空格;
{
    return s.replace( /\s*$/, "");
}

function lrtrim(s)	//去左右空格;
{
    return s.replace(/^\s+|\s+$/g,"");
}
function trim(s)	//去全部空格;
{
	return s.replace(/\s+/g,""); 
}
function isJSON(str) {
    if (typeof str == 'string') {
        try {
            var obj=JSON.parse(str);
            if(typeof obj == 'object' && obj ){
                return true;
            }else{
                return false;
            }

        } catch(e) {
            return false;
        }
    }
}
/*************************************/
/*设置全局变量存储计时器*/
var timer1;
var allTime = 60;//默认倒计时120秒
/*弹出遮盖层，业务页面主调函数*/
function showThis(type,div,url,tsid,total)
{
    var total_sj=document.getElementById("total_sj").value;
    var Eo_id=document.getElementById("Eo_id").value;
   
    
	$('.cover').fadeIn(50);  
    pvtScroll();  
    currentPos = div;
	    
    var QString = "ORDERID="+Eo_id+"&PAYMENT="+total_sj+"&TSID="+tsid+"&type="+type;  
    sendPOSTSwept(url, QString);
		 
    
}


//等待扫码结果方法
function waitForRst(obj,ORDERID,PAYMENT,url,tsid,returndivid,url2){
	//YWURL= ywurl;	//将交易成功的业务处理地址赋值给变量
	
	OBJ=returndivid;
	URL2=url2;
    var QRCODE = $(obj).val();   //存储此时的值
    if(QRCODE!="")
    {
    	countdown(); //开始支付倒计时    
	    $(".coverContent .tip").text("扫码成功，等待银行返回扣款结果中...");
	    $(".cover .close").attr("disabled","disabled");     //扫码成功，关闭取消支付功能
	    var QString = "ORDERID="+ORDERID+"&PAYMENT="+PAYMENT+"&QRCODE="+QRCODE+"&TSID="+tsid;
	    sendPOSTSwept(url, QString);
    }
}
 

/**
 * 监听到回车按下时触发文本框的失去焦点事件，目的是为了触发在IE浏览器上的文本框的onchange事件
 */
//$(document).keydown(function(event){
//    if(event.keyCode === 13){
//        $("#QRCODE").blur();
//    }
//});

/**
 * 绑定弹出框聚焦方法
 */
$(document).ready(function(){
	$(".cover").click(
			function(){
				$("#QRCODE").focus();
				}//保持输入框聚焦
			); //绑定弹出层的点击事件，用来保证点击层的任何位置都能保证输入框的聚焦事件
});

/**
 * 弹出层后阻止底层滚动
 */
function pvtScroll(){
    var body = $("body");
    var state = body.css("position");
    if( state != "fixed"){
        var scrTop = $(window).scrollTop();     //获取当前页面卷起高度
        body.css({"position":"fixed","left":"0","right":"0","margin":"auto","top":-scrTop +"px"});  //对body设置定位属性，阻止滚动
    }
}
/**
 * 关闭弹出层后还原页面
 */
function reduction(){
    var body = $("body");
    var scrTop = parseInt(body.css("top")); //获取之前页面卷起高度（负值）并转换为整型
    body.css({"position":"","left":"","right":"","margin":"","top":""});   //移除body的定位属性
    $(window).scrollTop(-scrTop);           //还原之前页面卷起高度
}
/**
 * 关闭弹出层
 */
function realClose(){
    $(".cover").fadeOut(20);
    clearInterval(timer1);
    reduction();
   
}
/**
 * 计时器计时
 */
function countdown(){
	if(allTime<60)
	{
		allTime=60;
	}
    timer1 = setInterval(function(){
        $(".cover .timerTip").html("<span class='red'>" + (allTime--)+"</span>秒后关闭本页");
        if(allTime < 0){
            realClose(".cover");//执行关闭弹窗的方法
        }
    },1000);
}
 