/******************全局变量的声明以及AJAX的设置*****************************************************************************/
	var http_request = false;
	var currentPos = null;
	var currentPos1 = null;
	var res_txt = new Array();
	var OBJ=null; //存返回会成功后再次显示页面的DIV层ID
/****以上为AJAX声明方法的全局变量****/	
	var returnURL=null; //存返回成功后再次调用页面的路径
	var returnDIVID=null; //存返回成功后再次显示页面的DIV层ID
	var closeDIVID=null; //存返回成功后需要关闭DIV层的ID
	var Blockid=null;//存简历模块个人信息、求职意向、自我评价的添加、更新成功后需要显示的DIV层的ID
	var Noneid=null;//存简历模块个人信息、求职意向、自我评价的添加、更新成功后需要隐藏的DIV层的ID
	var Addshowid=null;//存简历模块如工作经历需要成功后使添加页面的层为空的层ID
	var BUTTONID=null;
	var URL2=null;
	var Qvalue=null;
/*****************************************************正则表达式****************************************************/	
	var isPrice = /^\d+(.\d{1,2})?$/;//验证最多保留两位小数
	var is_b2 = /^[+,-]\d+(.\d{1,2})?$/;
	var isPrice3 = /^\d+(.\d{1,3})?$/;//验证最多保留两位小数
	var regstrSY= /^[^\~\!@<>\#\$\%\^\&\*\(\)\_\+\|\-\,\.\？\，\。]+$/;         //验证全部不是特殊字符的正则表达式
	var regexZX =/^[0-9]+(.[0-9]{1,5})?$/; //验证整数或者小数
	var reginger =/^-?\d+$/;  //验证正整数 或者 负整数
	
	var regmoney=/^\d+(\.\d+)?$/; // 验证货币的正则表达式
	var reghow=/^\d+$/; // 验数量必须为数字的正则表达式
	var regstring = /^[A-Za-z0-9_.@-]+$/; //严整用户名或密码类提交中是否包含非法字符的正则表达式
	var regstr= /^[A-Za-z0-9]+$/;         //验证全部是字母和数字的正则表达式  
	var regstrCN= /^[^A-Za-z0-9]+$/;                          //验证全部不是字母和数字的正则表达式  
	var regemail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;	//验证邮箱的正则表达式
	var regqq=/^[1-9]{1}[0-9]{4,10}$/;	//验证QQ的正则表达式
	var regstrSYSP= /[\^~!@#\$%&\*\(\)_\+\|\-,\.<>\?？，。'=\/]+$/;         //验证全部不是特殊字符的正则表达式
	var regdate = /^(\d{4})-(\d{2})-(\d{2})$/;  
	var regphone=/^1[0-9]{10}/;	//手机
	var regtel=/^([0-9]{3,4}-)|([0-9]{3,4})[1-9]{1}[0-9]{6,7}$/;	//固定电话
	var regjfq=/^[A-Za-z0-9_@#$|\/-]+$/;///^[2-9]{1}[0-9]{5}/; // 验证缴费期格式为：201401
	var regex=/^[0-9#]*$/;//验证只能包含数字和#
	var regnum =/^[0-9]*$/;
	var regqj=/^[0-9]{1,2}-[0-9]{1,2}#(\d+(\.\d+)?)$/;//验证只能包含数字和#-. 数字-数字#金额
/*****************************************************正则表达式****************************************************/	
	var zje_id = null;
	var yhje_id = null;
	var zfje_id = null;
function send_request(url) {//初始化、指定处理函数、发送请求的函数
	show_Loading_div();//调用打开loading提交等待图片的层
	
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
	http_request.onreadystatechange = processRequest;
	// 确定发送请求的方式和URL以及是否同步执行下段代码
	http_request.open("GET", url, true);
	http_request.send(null);
}

function sendPOST(url,QString) {
	show_Loading_div();//调用打开loading提交等待图片的层
	
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
 http_request.onreadystatechange = processRequest;
 http_request.open("POST", url, true);
 http_request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
 http_request.send(QString);

 }
// 处理返回信息的函数
function processRequest() {
    if (http_request.readyState == 4) { // 判断对象状态
        if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
        	
        	close_loading_div();//调用关闭loading提示图片的层
        	
		    res_txt=http_request.responseText.split('-'); //获得返回的数据进行截取
		   
			var temp=trim(res_txt[0]);  //temp得到去掉空格后的字符串			 
            var PrintDescription=res_txt[1];  //temp得到去掉空格后的字符串
            if(temp=="sessionOvertime")
            {
            	window.location=trim(res_txt[2]);
            }      
            else if(temp=="errors") //返回失败信息调用
            {
                alert(PrintDescription);
            }  
            else if(temp=="error") //返回失败信息调用
            {
                alert(PrintDescription);
                showselect(OBJ,URL2);
            }  
            else if(temp=="ok")	//成功类似的不需刷新页面的调用，如：添加返回主键冲突时也可调用
            {
                alert(PrintDescription);
            }
            else if(temp=="sok")//操作成功，并且传入新的DIV层ID显示的页面调用
            {
                alert(PrintDescription);
                showselect(returnDIVID,returnURL);
            } 
            else if(temp=="sssok")//操作成功，并且传入新的DIV层ID显示的页面调用
            {
                //alert(PrintDescription);
            	currentPos=OBJ;
            	sendPOST(URL2,Qvalue);
            	setTimeout(function (){
            		$("#btnbtn").click();
            	}, 200); 
            } 
            else if(temp=="ssok")//操作成功，并且传入新的DIV层ID显示的页面调用
            {
                alert(PrintDescription);
                showselect(OBJ,URL2);
            }    
            else if(temp=="csok")//操作成功，关闭层。
            {
                alert(PrintDescription);      
                close_tanchu_div(DIV_ID);
            }
            else if(temp=="cssok")//操作成功，并且传入新的DIV层ID显示的页面调用,并且关闭层。
            {
                alert(PrintDescription);                
                showselect(OBJ,URL2);
                close_tanchu_div(DIV_ID);
            }
            else if(temp=="uok")//适用于成功后不弹出提示框，如登录操作成功，论坛发布帖子，直接调用显示成功后的页面。
            {
                showselect(returnDIVID,returnURL);
            }                                     
            else if(temp=="cok")//操作成功，不弹出提示框，直接调用显示成功后的页面,并关闭某个DIV层的方法。
            {
                showselect(returnDIVID,returnURL);
                close_div(closeDIVID);
            }    
            else if(temp=="epok")//设置绑定手机或邮箱,并关闭某个DIV层的方法。
            {
                
                document.getElementById(returnDIVID).innerHTML =PrintDescription;
                close_div(closeDIVID);
            }              
            else if(temp=="lok")//适用于成功后不弹出提示框，如登录操作成功，直接调用显示成功后的页面。
            {
            	window.location=returnURL;
            }             
            else if(temp=="regok")//适用于成功后不弹出提示框，如登录操作成功直接调用显示成功后的页面。
            {
            	window.location=PrintDescription;
            }          
            else if(temp=="zfjs")//缴费项目可以选择时，选择或取消选择时，计算优惠金额和支付金额，并返回到指定位置显示
            {          	
            	document.getElementById(zje_id).value=trim(PrintDescription);
            	document.getElementById(yhje_id).innerHTML = res_txt[2];
            	document.getElementById(zfje_id).value=trim(res_txt[3]);
            }     
            else if(temp=="dcbbok")//操作成功，不显示提示信息直接刷新页面
            {
            	alert(PrintDescription);
            	document.getElementById(BUTTONID).disabled=false;
            }
            else if(temp=="xok")//操作成功，并且传入新的DIV层ID显示的页面调用
            {
            	 alert(PrintDescription);
                 menu_shua(URL2);
            } 
            else
            {
				if(currentPos1!=null){
            		document.getElementById(currentPos1).innerHTML = http_request.responseText;
            		currentPos1=null;
            	}else{
            		document.getElementById(currentPos).innerHTML = http_request.responseText;
            	}
			}
       			
        } else { //页面不正常
        	//  alert("您所请求的页面有异常。"+http_request.status);
        	close_loading_div();//调用关闭loading提示图片的层
        }
    }
}
/******************************************************************************************/
//打开loading提示图片的层
function show_Loading_div(){
 
	 var user1 =document.getElementById("Loading_div_da");
	 var user2 =document.getElementById("Loading_div_xiao");
   var x1 = (window.screen.width-1024)/2;
   var y1 = document.body.scrollTop;//(window.screen.height-768)/2;
   var x2 = window.screen.width/2;
   var y2 = document.body.scrollTop+300;//window.screen.height/2-100;    
   
   user1.style.position = "absolute";
   user1.style.left = x1 + "px";
   user1.style.top = y1 + "px";

   user2.style.position = "absolute";
   user2.style.left = x2 + "px";
   user2.style.top = y2 + "px"; 
    
	 user1.style.display="block";
	 user2.style.display="block";    
   
}
//关闭loading提示图片的层
function close_loading_div()
{
	 var user1 =document.getElementById("Loading_div_da");
	 var user2 =document.getElementById("Loading_div_xiao");
	 user1.style.display="none";
	 user2.style.display="none";
}
/*********************************************************************/
function Rep_str(rstr)
{
  rstr=rstr.replace(/\%/g, "％");
  rstr=rstr.replace(/\&/g, "＆");
  rstr=rstr.replace(/\+/g, "＋");
  rstr=rstr.replace(/\#/g,"＃");
  rstr=rstr.replace(/\\/g,"＼");
  rstr=rstr.replace(/\-/g,'－');
  rstr=rstr.replace(/\~/g,"～");
  rstr=rstr.replace(/\!/g,"！");
  rstr=rstr.replace(/\@/g,"＠");
  rstr=rstr.replace(/\$/g,"＄");
  rstr=rstr.replace(/\^/g,"＾");
  rstr=rstr.replace(/\*/g,"＊");
  rstr=rstr.replace(/\(/g,"（");
  rstr=rstr.replace(/\)/g,"）");
  rstr=rstr.replace(/\_/g,"＿");
  rstr=rstr.replace(/\=/g,"＝");
  rstr=rstr.replace(/\|/g,"｜");
  rstr=rstr.replace(/\`/g,"｀");
  rstr=rstr.replace(/\?/g,"？");
  rstr=rstr.replace(/\,/g,"，");
  rstr=rstr.replace(/\./g,"。");
  rstr=rstr.replace(/\;/g,"；");
  rstr=rstr.replace(/\'/g,"’");
  rstr=rstr.replace(/\</g,"＜");
  rstr=rstr.replace(/\>/g,"＞");  
  return rstr;

}
 
/****去空格的方法****/
function ltrim(s)	//去左空格;
{
    return s.replace( /^\s*/, ""); 
}

function rtrim(s)	//去右空格;
{
    return s.replace( /\s*$/, "");
}

function trim(s)	//去左右空格;
{
    return rtrim(ltrim(s));
}
/*************************************/
/*复选框全选全取消的方法*/
function CheckAll_checkbox(obj,checkname)
{
	if(obj.checked==false)//取消全选
	{
		var options=document.getElementsByName(checkname).length;//这里的checkname是指checkbox组的name名称
		if(options>0)
		{
			for( var i=0;i<options;i++)		      
			{
				document.getElementsByName(checkname)[i].checked=false;
			}
		}
	}
	else				//全部选中
	{
		var options=document.getElementsByName(checkname).length;//这里的checkname是指checkbox组的name名称
		if(options>0)
		{
			for(i=0;i<options;i++)		      
			{
				document.getElementsByName(checkname)[i].checked=true;
			}
		}
	}
}
/*复选框全选全取消的方法(不可用的复选框不可以选择)*/
function CheckAll_checkboxs(obj,checkname)
{
	if(obj.checked==false)//取消全选
	{
		var options=document.getElementsByName(checkname).length;//这里的checkname是指checkbox组的name名称
		if(options>0)
		{
			for(var i=0;i<options;i++)		      
			{
				document.getElementsByName(checkname)[i].checked=false;
			}
		}
	}
	else				//全部选中
	{
		var options=document.getElementsByName(checkname).length;//这里的checkname是指checkbox组的name名称
		if(options>0)
		{
			for(i=0;i<options;i++)		      
			{
				if(document.getElementsByName(checkname)[i].disabled==false)
				{
					document.getElementsByName(checkname)[i].checked=true;
				}
			}
		}
	}
}
/****获得选中的多选框的值 传入NAME 返回集合****/
function getcheckbox(checkid,checkname)
{
 		var j=document.getElementsByName(checkname).length;//document.sglform.fuwuqi.length;

		var k=0;
	
		for( var i=0;i<j;i++)
		{
		
			if(document.getElementsByName(checkname)[i].checked==true)
			{
				checkid[k]=document.getElementsByName(checkname)[i].value;
				k++;
			}
		}

 	return checkid;
} 
 
/**
 * 传入select控件的ID得到选中的select控件的value值
 * @param selectid <select>控件的ID
 * @returns 选中的<select>控件的value值
 */
function get_select_value(selectid)
{
	var select_value;
	var j=document.getElementById(selectid).length;
	
	for( var i=0;i<j;i++)
	{
	  if(document.getElementById(selectid)[i].selected==true)
	  {
		  select_value=document.getElementById(selectid)[i].value;
	  }
	}
	return select_value;
}
/**传入select控件的ID得到选中的select控件的text值*/
function get_select_text(selectid)
{
	var select_value;
	var j=document.getElementById(selectid).length;
	
	for(var i=0;i<j;i++)
	{
	  if(document.getElementById(selectid)[i].selected==true)
	  {
		  select_value=document.getElementById(selectid)[i].text;
	  }
	}
	return select_value;
}
/**
 * 封装刷新参数 适用于没有参数提交页面需要刷新的时候调用
 * @returns {String}
 */
function getRefresh()
{
	return "?Refresh="+new Date().getTime();
}
/**
 * 封装刷新参数 适用于有参数提交页面需要刷新的时候调用
 * @returns {String}
 */
function getRefreshall()
{
	return "&Refresh="+new Date().getTime();
}
/****实现分页的方法****/

function Back(page,pagenum,pos) //上一页
{
	currentPos =pos;
	var QString  = page+"?pagenum="+pagenum+"&type=1"+getRefreshall();
	send_request(QString);
}
function Next(page,pagenum,pos) //下一页
{
	currentPos =pos;
	var QString  = page+"?pagenum="+pagenum+"&type=1"+getRefreshall();
	send_request(QString);
}
function showpages(page,obj,pos)  //具体到某一页,适用于下拉列表控件跳转
{

	if(obj.value!=0)
	{
		currentPos =pos;
		var QString  = page+"?pagenum="+obj.value+"&type=1"+getRefreshall();
		send_request(QString);		
	}
}
function showpagess(page,pagenum,pos)  //具体到某一页，适用于直接输入页码，如首页尾页等
{
	currentPos =pos;
	var QString  = page+"?pagenum="+pagenum+"&type=1"+getRefreshall();
	send_request(QString);		
}
/**************************************************************************************************/

/*验证包含中文、字母、数字、下划线的账号和密码*/
function isNameorPass(str){
    var reg = /^[\u4E00-\uFA29A-Za-z0-9_]+$/;
    if(!reg.test(str)){
     return false;
    }
    return true;
}
/*验证中文*/
function isChn(str){
    var reg = /^[\u4E00-\uFA29]+$/;
    if(!reg.test(str)){
     return false;
    }
    return true;
}
/*验证身份证号*/
function isIdCardNo(num)
 {
     var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
     var error;
     var varArray = new Array();
     var intValue;
     var lngProduct = 0;
     var intCheckDigit;
     var intStrLen = num.length;
     var idNumber = num;
     // initialize
     if ((intStrLen != 15) && (intStrLen != 18)) {
         return false;
     }
     // check and set value
     for(i=0;i<intStrLen;i++) {
         varArray[i] = idNumber.charAt(i);
         if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
             return false;
         } else if (i < 17) {
             varArray[i] = varArray[i]*factorArr[i];
         }
     }
     if (intStrLen == 18) {
         //check date
         // calculate the sum of the products
         for(i=0;i<17;i++) {
             lngProduct = lngProduct + varArray[i];
         }
         // calculate the check digit

         intCheckDigit = 12 - lngProduct % 11;

         switch (intCheckDigit) {
             case 10:
                 intCheckDigit = 'X';
                 break;
             case 11:
                 intCheckDigit = 0;
                 break;
             case 12:
                 intCheckDigit = 1;
                 break;
         }
         // check last digit
         if (varArray[17].toUpperCase() != intCheckDigit) {
             return false;
         }
     }
     return true;
 }
 
 /*验证电话号码*/
 
 String.prototype.Trim = function() {   
var m = this.match(/^\s*(\S+(\s+\S+)*)\s*/);   
return (m == null) ? "" : m[1];   
}   
  
String.prototype.isMobile = function() {     
return (/^1[0-9]{10}/.test(this.Trim()));
}   
  
String.prototype.isTel = function()   
{   
//"兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"   
return (/^0311-[0-9]{8}$/.test(this.Trim()));   
}   
  
function chkFormT(tel) {      
if (tel.Trim().isTel()) {
return true;   
}   
else {   
return false;   
}     
} 

function chkFormM(tel) {      
if (tel.Trim().isMobile()) {
return true;   
}   
else {    
return false;   
}     
} 
/**********************************************兼容IE和火狐浏览器的加入收藏、设为首页方法****************************************************/
/*加入收藏*/
function AddFavorite()
{
	sURL=document.URL;
	sTitle=document.title;
    try
    {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e)
    {
        try
        {
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e)
        {
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}
/*设为首页*/
function SetHome(obj,vrl){
        try{
                obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
        }
        catch(e){
                if(window.netscape) {
                        try {
                                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                        }
                        catch (e) {
                                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
                        }
                        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                        prefs.setCharPref('browser.startup.homepage',vrl);
                 }
        }
}
/********************************************导航公用方法**************************************************/
function set_daohang(daohang_div,daohang_value)
{	
	currentPos=daohang_div;
	document.getElementById(currentPos).innerHTML =daohang_value;
}
/*生成全部的菜单tree，层隐藏，层打开关闭的方法*/
function menu_tree_all(jiajian,jia,jian,ID)
{	
	if(document.getElementById(ID).style.display=="block")
	{
		document.getElementById(ID).style.display = "none";
		document.getElementById(jiajian).src=jia;
	}
	else
	{
		document.getElementById(ID).style.display = "block";
		document.getElementById(jiajian).src=jian;
	}	

}
/********************************************左边列表点击右边显示内容+导航的公用方法**************************************************/
/*缴费项目页面点击左侧列表时右边显示内容的菜单调用方法*/
function listmenu(a_level,daohang_div,daohang_value,content_divid,a_id)
{
	set_daohang(daohang_div,daohang_value);
	
	currentPos=content_divid;
	document.getElementById("a_id").value=a_id;
	document.getElementById("a_level").value=a_level;
	var url=document.getElementById("suburl").value;
	var soname=Rep_str(document.getElementById("soname").value);	
	var shi=document.getElementById("shi").value;

	var QString="soname="+soname+"&shi="+shi+"&a_id="+a_id+"&a_level="+a_level;
	
	sendPOST(url,QString);
}

/********************************************导航公用方法**************************************************/
/***主显示区域查询后显示区域页面的方法***/
function showselect(returnDIVID,url)
{
	//alert(url);
	currentPos=returnDIVID;
	
	send_request(url);
}
 
/***********************************************************************************/
/*弹出层的显示方法*/
function show_tanchu_div(ID,width,height)
{

 	 var userInfo =document.getElementById(ID);
     var x1 = (window.screen.availWidth-width)/2;
	 var y1 = document.body.scrollTop+200;
	 
     userInfo.style.position = "absolute";
     userInfo.style.left = x1 + "px";
     userInfo.style.top = y1 + "px";
     
     userInfo.style.width = width+"px";
     userInfo.style.height = height+"px";
     userInfo.style.display="block";  
}
//这个层的关闭
function close_div(divid)               
{
	document.getElementById(divid).style.display="none";
}
/***********************************************************************/
/*附件下载*/
function downLoad(url,fileName,filepath){
  	
 	//var QString="fileName="+encodeURI(encodeURI(trim(fileName)))+"&filepath="+filepath;

 	var QString="fileName="+fileName+"&filepath="+filepath;
  	url+="?"+QString;
  
  	window.location=url;  	
}

/******************************/
/**查看协议的开关方法*/
function showxieyi(kgdivid,showdivid,url)
{
	var guan="点击关闭协议";
	var kai="点击查看协议";
	var temp=document.getElementById(kgdivid).innerHTML;
	if(temp==kai)
	{
		document.getElementById(kgdivid).innerHTML=guan;
		currentPos=showdivid;
		send_request(url);
	}
	if(temp==guan)
	{
		document.getElementById(kgdivid).innerHTML=kai;
		document.getElementById(showdivid).innerHTML="";
	}	
}
/**登陆时刷新验证码*/
function refresh_yzm(imgid,url)
{
	document.getElementById(imgid).src=url+getRefresh();	//刷新图片必须要传随机数过去，否则不刷新
}

/*根据选择的年或月显示日的方法*/
function set_day()
{
	var Year=document.getElementById("Year").value;
	var Month=document.getElementById("Month").value;

	var day=GetMonthCount(Year,Month)
	
	var select="<select name=\"day\">";
	var option="";
	for(i=1;i<=day;i++)
	{
		option+="<option value=\""+i+"\">"+i+"</option>";
	}
	select=select+option+"</select>日";
	document.getElementById("show_day").innerHTML=select;
}
function IsPinYear(year)            //判断是否闰平年
{
    if(0==year%4&&((year%100!=0)||(year%400==0)))
    {
    	return true;
    }
    else
    {
    	return false;
    }
}
function GetMonthCount(year,month)  //闰年二月为29天
{
    if((month==2))
	{
		if(IsPinYear(year))
		{
			return 29;
		}
		else
		{
			return 28;
		}
	}
	if(month==1 || month==3 || month==5 ||  month==7 || month==8 || month==10 || month==12)
	{
		return 31;
	}
	if(month==4 || month==6 || month==9 || month==11)
	{
		return 30;
	}	
}

/************************************************验证图片类型的方法**************************************************/

//var right_type=new Array(".gif",".jpg",".jpeg",".png",".bmp"); //定义图片类型数组
var right_type=new Array(".jpg",".jpeg",".png"); //定义图片类型数组
/*主调函数（file控件内容变化时触发）*/
function changeSrc(filePicker) 
{
	if(!checkImgType(filePicker.value)) 
	{ 
		alert("图片类型是不正确的"); 
		
		filePicker.outerHTML=filePicker.outerHTML;//清空file控件

		return false;
	}
	else
	{
		return true;
	}
} 
/*检查图片类型*/
function checkImgType(fileURL) 
{ 
	var right_typeLen=right_type.length; //得到图片类型数组长度
	var imgUrl=fileURL.toLowerCase(); //把file控件中选中的文件路径字符串转换为小写。
	var postfixLen=imgUrl.length; 	//得到file控件中选中的文件路径的长度。
	var len4=imgUrl.substring(postfixLen-4,postfixLen); //得到图片后缀为3位的图片名称，例如：".jpg"
	var len5=imgUrl.substring(postfixLen-5,postfixLen); //得到图片后缀为4位的图片名称，例如：".jpeg"
	
	for ( var i=0;i<right_typeLen;i++) 
	{ 
		if((len4==right_type[i])||(len5==right_type[i])) 
		{ 
			return true; 
		} 
	} 
}

/**************************************************************************************************/
/*图片预览方法*/
function ImagePreviewd(idFile,idImg,imgmaxWidth,imgmaxHeight,actionurl)
{
	if(changeSrc(document.getElementById(idFile)))
	{

	var ip = new ImagePreview( $$(idFile), $$(idImg), {maxWidth: imgmaxWidth, maxHeight: imgmaxHeight, action: actionurl });

	ip.img.src = ImagePreview.TRANSPARENT;
	ip.preview();
	
	}
}
/*图片上传方法*/
function QuickUoliad(idFile,actionurl)
{
	//var ip = new QuickUpload( $$(idFile), $$(idImg), {maxWidth: imgmaxWidth, maxHeight: imgmaxHeight, action: actionurl });
	if(changeSrc(document.getElementById(idFile)))
	{
		var file = $$(idFile), fu = new QuickUpload(file, { action: actionurl });
		fu.upload(); 
	}
}
/*************************************论坛发布主帖时添加删除图片的方法*****************************************************/

var file_to=0;
function add_inp()
{
	if(file_to==5)
	{
		alert("一次最多只能添加5个图片！");
	}
	else
	{
		file_to++;
		document.getElementById("img"+file_to).style.display="block";
		document.getElementById("img"+file_to).focus();
	}
}

function del_inp(divid)
{
	if(file_to>0)
	{
		document.getElementById(divid).style.display="none";
		file_to--;
	}

}
/*************************************发布产品时添加删除图片的方法*****************************************************/

var img_file=new Array("img1","img2","img3","img4","img5","img6","img7","img8","img9","img10"); //定义图片类型数组

function add_inpsof()
{
	var a=0;
	for (i=0;i<img_file.length;i++) 
	{ 
		if(document.getElementById(img_file[i]).style.display=="none") 
		{
			document.getElementById(img_file[i]).style.display="block";
			document.getElementById(img_file[i]).focus();
			return; 
		} 
		a++;
	} 
	if(a>=10)
	{
		alert("一次最多只能添加10个图片描述！");
		return; 
	}	
	
}

function del_inpsof(divid,descid,fileid)
{
	document.getElementById(divid).style.display="none";
	document.getElementById(descid).value="";
	document.getElementById(fileid).value="";

}
function del_inpsofon(divid,descid,fileid,imgshow,setoldimg)
{
	document.getElementById(divid).style.display="none";
	document.getElementById(descid).value="";
	document.getElementById(fileid).value="";
	document.getElementById(imgshow).style.display="none";
	//alert(document.getElementById("oldimg").value+":"+document.getElementById("oldimgdesc").value);
	setoldImg(setoldimg);
}
/**
 * 删除旧图片没描述时调用的方法
 * @param setoldimg
 */
function setoldImg(setoldimg) 
{ 
	var oldimg=document.getElementById("oldimg").value;
	oldimg=oldimg.replace(setoldimg,"");

	if(oldimg.substring(0,1)=="|" )
	{
		oldimg=oldimg.substring(1,oldimg.length);
	}
	if(oldimg.substring(oldimg.length-1,oldimg.length)=="|" )
	{
		oldimg=oldimg.substring(0,oldimg.length-1);
	}	
	document.getElementById("oldimg").value=oldimg;
}

/******************************************************************************************/

/*设置文本域的字数限制*/
function setLength(th,maxlength)
{
	if(th.value.length >= maxlength)
	{
		//event.returnValue = false; 
		th.value=th.value.substr(0,maxlength-1);
		
	}
}

/**********************************搜索页面新闻**********************************/
/*显示不同类别的搜索页面*/
function sosshow(divid,url)
{
	currentPos=divid;
	send_request(url);
}
/*搜索页面提交搜索的方法*/
function sos_select(divid,url)
{
	currentPos=divid;
	var sostxt=Rep_str(document.getElementById("sostxt").value);
	var QString="sostxt="+sostxt;

	sendPOST(url,QString);
}


/**************************************************************************/
/*在线播放器*/
function showPlayer(id,url)
{
	var vhtml ="";

	    vhtml = '<object id="wmp"';
	    var userAg = navigator.userAgent;
	    if(-1 != userAg.indexOf("MSIE")){
	        vhtml+=' classid="clsid:6BF52A52-394A-11d3-B153-00C04F79FAA6"';
	    }
	    else if(-1 != userAg.indexOf("Firefox") || -1 != userAg.indexOf("Chrome") || -1 != userAg.indexOf("Opera") || -1 != userAg.indexOf("Safari")){
	        vhtml+=' type="application/x-ms-wmp"';
	    }
	    vhtml+=' width="450" height="300">';
	    vhtml+='<param name="URL" value="'+url+'"/>';
	    vhtml+='<param name="autoStart" value="false" />';
	    vhtml+='<param name="invokeURLs" value="false">';
	    vhtml+='<param name="playCount" value="100">';
	    vhtml+='<param name="Volume" value="100">';
	    vhtml+='<param name="defaultFrame" value="datawindow">';
	
	    vhtml+='</object>';
/*	    
	var type=url.substring(url.lastIndexOf("."), url.length);
	
	if(type==".mp3" || type==".swf" || type==".gif" || type==".avi" || type==".wmv")
	{	    
	}
	else
	{
		vhtml ="该作品的后缀格式不是[.mp3 .swf .gif]其中的一种，不能在线播放！";
	}
*/	
    document.getElementById(id).innerHTML = vhtml;
}
/********************************************************************/

/***********************************************************************************************/
/*输入字符串、截取符号，大小，得到数组（适用于问卷调查的提交验证）*/
function getIndexString(array,str,ch,size)
{
	var strtemp=str;

	for( var j=0;j<size;j++)
	{
		var temp="";
		var end=strtemp.indexOf(ch);
		if(end==-1)
		{
			if(strtemp.length>0)
			{
				temp=strtemp.substring(0, strtemp.length);
				array[j]=temp;
				
			}
		}
		else
		{
			temp=strtemp.substring(0, end);
			array[j]=temp;
			strtemp=strtemp.substring(end+1, strtemp.length);
		}

	}
	
	return array;
}
/******************************************************************************************************88/

/*用户绑定邮箱更新的方法*/
function user_email_update(returndivid,closedivid,url)
{
	returnDIVID=returndivid;
	closeDIVID=closedivid;
	
	var user_email=document.getElementById("user_email").value;
	if(user_email=="")
	{
		alert("邮箱不能为空！");
		document.getElementById("user_email").focus();
		return false;
	}
	else
	{
		if(!regemail.test(user_email))
		{
			alert("邮箱格式不正确，请重新输入!");
			document.getElementById("user_email").focus();
			return false;
		}
	}
	var QString="user_email="+user_email;
	sendPOST(url,QString);	
}
/******************************************************************************************************/
/**首页选择搜索4项内容时执行**/
function sousuo(divid,classname,sstype)               
{
	document.getElementById(divid).className=classname;
	document.getElementById("sstype").value=sstype;
}
/**首页点击搜索按钮进行搜索的方法**/
function sousuosub(path)               
{
	//var sosonr=document.getElementById("sosonr").value;
	var sstype=document.getElementById("sstype").value;
	//alert(sstype);
	var url="";
	if(sstype=="sj")
	{
		url=path+"index_Service_list.jsp";
	}
	else
	{
		url=path+"index_ProductsService.jsp";
	}

	document.sosotj.action=url;
	document.sosotj.submit();
}

function wuye_login(){
	var user_id=document.getElementById("user_id").value;
	var password=document.getElementById("password").value;
	var code=document.getElementById("code").value;
	if(user_id=="") {
		alert("账号不能为空!");
		document.getElementById("user_id").focus();
		return false;
	}
	if(password=="") {
		alert("密码不能为空!");
		document.getElementById("password").focus();
		return false;
	}
	if(code=="") {
		alert("验证码不能为空!");
		document.getElementById("code").focus();
		return false;
	}
}
/*点1级菜单调用的方法*/
function menu1(ID,url,menu,menu_code,menu_level)
{
	currentPos=ID;	
	var Qstring="menu="+menu+"&menu_code1="+menu_code+"&menu_level="+menu_level;   
	
    sendPOST(url,Qstring);	
}
/*点2级菜单调用的方法*/
function menu2(menu_code ,i,count,url)//,menu_code)
{
	currentPos="showmain";
	document.getElementById(menu_code+"li"+i).style.color = "#004274";
	document.getElementById(menu_code+"li"+i).style.fontWeight = "bold";
	for(j=0;j<count;j++)
	{
		if(j!=i)
		{
			document.getElementById(menu_code+"li"+j).style.color = "#000000";
			document.getElementById(menu_code+"li"+j).style.fontWeight = "normal";
		}
	}
	
	send_request(url);
}


 
/******************************************************************************************************/
/****本级页面得到单选钮的值****/
function getradio(radioname) 
{
	ra=document.getElementsByName(radioname);//通过名字得到对象
	var len=ra.length; //得到单选钮的数量	
	var radiovalue="";
	for(i=0;i<len;i++)	//循环判断选种的按钮值
	{
		if(ra[i].checked) 
		{
			radiovalue=ra[i].value;
		}
	}
	return radiovalue;
}
//收费管理--收费项设置
function TB_Item_add(url,url2)
{
	OBJ="showmain";
	URL2=url2;
	var xiaoqu=document.getElementById("xiaoqu").value;
	var sfname=trim(document.getElementById("sfname").value);
	var EhType=trim(document.getElementById("EhType").value);
	var sftype=document.getElementById("sftype").value;
	var price=trim(document.getElementById("price").value);
	var znjday=trim(document.getElementById("znjday").value);
	var zjnbl=trim(document.getElementById("zjnbl").value);
	var remark=trim(document.getElementById("remark").value);
	var Limited=trim(document.getElementById("Limited").value);
	var LimitNumber=trim(document.getElementById("LimitNumber").value);
	var LimitedStr="";
	var gdxz=getradio("gdxz");
	var isyj=getradio("isyj");
	var checkid=new Array();
	getcheckbox(checkid,"ch");
	if(checkid.length>0)
	{
		for( var i=0;i<checkid.length;i++)
		{
			var str=checkid[i]+"#";			
			LimitedStr+=str;
		}
		
	}
	if(Limited!=""){
		if(!reghow.test(Limited)){
		alert("购买金额必须为数字，请重新输入！！");
		document.getElementById("Limited").focus();
		return false;
		}
	}
	if(Limited!=""){
		LimitedStr+=Limited+"#";
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday").focus();
			return false;
		}
	}
	if(zjnbl!=""){
		if(!regexZX.test(zjnbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("zjnbl").focus();
			return false;
		}
	}
	
	if(sfname=="电费"){
		
		if(LimitedStr==""){
			alert("请选中前三个其中任意一个(购买金额的选择框为蓝色才算选中状态哦)或输入第四个购买金额");
			return false;
		}
		if(LimitNumber==""){
			alert("请填写限购次数/月");
			document.getElementById("LimitNumber").focus();
			return false;
		}
		if(LimitNumber!=""){
			if(!reghow.test(LimitNumber)){
				alert("限购次数/月必须为数字，请重新输入！！");
				document.getElementById("LimitNumber").focus();
				return false;
			}
		}
	}
	if(sfname=="水费"){
		
		if(LimitedStr==""){
			alert("请选中前三个其中任意一个(购买金额的选择框为蓝色才算选中状态哦)或输入第四个购买金额");
			return false;
		}
	}
	if(xiaoqu=="")
	{
		alert("请选择小区名称");
		document.getElementById("xiaoqu").focus();
		return false;
	}
	if(sfname=="")
	{
		alert("收费名称不能为空");
		document.getElementById("sfname").focus();
		return false;
	}
	if(regstrSYSP.test(sfname)){
		alert("收费名称不能含有特殊字符！");
	   document.getElementById("sfname").focus();
	   return false;
	}
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price").focus();
		return false;
	}
	if(!isPrice.test(price))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("price").focus();
		return false;		
	}	
	if(EhType==""){
		alert("请选择房屋类型");
		document.getElementById("sftype").focus();
		return false;
	}
	if(sftype=="0")
	{
		alert("请选择收费方式");
		document.getElementById("sftype").focus();
		return false;
	}
	
	var QString="xiaoqu="+xiaoqu+"&sfname="+sfname+"&sftype="+sftype+"&price="+price+
	"&znjday="+znjday+"&zjnbl="+zjnbl+"&remark="+remark+"&EhType="+EhType+
	"&Limited="+LimitedStr+"&LimitNumber="+LimitNumber+"&gdxz="+gdxz+"&isyj="+isyj;
	
	sendPOST(url,QString);
}

function new_show_see_tanchu_div1(ID,width,height,url)
{

 	 var userInfo =document.getElementById(ID);
     var x1 =0; 
     var y1 =70; 
     userInfo.style.position = "absolute";
     userInfo.style.left = x1 + "px";
     userInfo.style.top = y1 + "px";
     $(".gxg_tcdiv").height(($(window).height()-92)+"px");
     userInfo.style.display="block";  
     
     currentPos=ID;
     var Qstring="divid="+ID;
	 sendPOST(url,Qstring);
}

/*关闭弹出的层*/
function close_tanchu_div(ID)
{
	var userInfo =document.getElementById(ID);
	userInfo.style.display="none";
}
/*修改缴费项目*/
function TBPay_Item_update(status,Ct_id,Ei_id,Es_id,i,url,returndivid,url2)
{
	OBJ=returndivid;
	URL2=url2;
	
	var name=trim(Rep_str(document.getElementById("name"+i).value));
	var price1=trim(document.getElementById("price1"+i).value);
	var Limited1=trim(document.getElementById("Limited1"+i).value);
	var LimitNumber1=trim(document.getElementById("LimitNumber1"+i).value);
	var znjday1=trim(document.getElementById("znjday1"+i).value);
	var znjbl1=trim(document.getElementById("znjbl1"+i).value);
	var gdxz1=getradio("gdxz"+i);
	var isyj1=getradio("isyj"+i);
	var shoufeifs=trim(Rep_str(document.getElementById("shoufeifs"+i).value));
	var EhType=trim(document.getElementById("EhType"+i).value);
	if(name=="电费"){
		if(Limited1==""){
			alert("购买金额不能为空");
			document.getElementById("Limited1"+i).focus();
			return false;
		}
		
		if(LimitNumber1==""){
			alert("请填写限购次数/月");
			document.getElementById("LimitNumber1"+i).focus();
			return false;
		}
		if(LimitNumber1!=""){
			if(!reghow.test(LimitNumber1)){
				alert("限购次数/月必须为数字，请重新输入！！");
				document.getElementById("LimitNumber1"+i).focus();
				return false;
			}
			
		}
	} 
	if(name=="水费"){
		if(Limited1==""){
			alert("购买金额不能为空");
			document.getElementById("Limited1"+i).focus();
			return false;
		}
	}
	
	if(!regex.test(Limited1)){
		alert("购买金额只能为数字，如有多个以#分割，例：50#100#150");
		document.getElementById("Limited1"+i).focus();
		return false;
	}
	if(!regnum.test(LimitNumber1)){
		alert("限购次数/月正整数，请重新输入！！");
		document.getElementById("LimitNumber1"+i).focus();
		return false;
	}
	
	if(znjday1!=""){
		if(!reginger.test(znjday1)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday1"+i).focus();
			return false;
		}
	}
	if(znjbl1!=""){
		if(!regexZX.test(znjbl1)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("znjbl1"+i).focus();
			return false;
		}
	}
	
	if(name=="")
	{
		alert("收费名称不能为空");
		document.getElementById("name"+i).focus();
		return false;
	}
	if(regstrSYSP.test(name)){
		alert("收费名称不能含有特殊字符！");
		document.getElementById("name"+i).focus();
		return false;
	}
	if(price1=="")
	{
		alert("单价不能为空");
		document.getElementById("price1"+i).focus();
		return false;
	} 
	if(!isPrice.test(price1))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("price1"+i).focus();
		return false;		
	}
	
	var QString="Es_id="+Es_id+"&Ct_id="+Ct_id+"&Ei_id="+Ei_id+"&name="+name+"&price1="+price1+"&znjday1="+znjday1+"&znjbl1="+znjbl1+"&status="+status
	+"&Limited1="+Limited1+"&LimitNumber1="+LimitNumber1+"&gdxz1="+gdxz1+"&isyj1="+isyj1+"&shoufeifs="+shoufeifs+"&EhType="+EhType;
	
	sendPOST(url,QString);
}

function TBitemjfx_selectD(divid,url)
{
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;//校区
	var QString="Es_id="+Es_id;
	
	sendPOST(url,QString);
}

function TBitemjfx_select(divid,url,Es_id)
{
	currentPos=divid;
	//var Es_id=document.getElementById("Es_id").value;//校区
	var QString="Es_id="+Es_id;
	
	sendPOST(url,QString);
}

function TB_Order(checkname,url,url2)
{
	OBJ="showmain";
	URL2=url2;
	var Es_id=document.getElementById("Es_id").value;//小区
	var Bu_id=document.getElementById("Bu_id").value;//楼宇
	var Un_id = "";
	if(document.getElementById("Un_id")!=null&&Bu_id!=""){
		Un_id=document.getElementById("Un_id").value;
	}
	var EhNumber=trim(document.getElementById("EhNumber").value);//房屋编号
	var cost_startTime=trim(document.getElementById("cost_startTime").value);
	var cost_endTime=document.getElementById("cost_endTime").value;
	var Scost_startTime=trim(document.getElementById("Scost_startTime").value);
	var Scost_endTime=trim(document.getElementById("Scost_endTime").value);
	
	
	if(Es_id=="")
	{
		alert("请选择小区名称");
		document.getElementById("Es_id").focus();
		return false;
	}
	/*if(Bu_id=="")
	{
		alert("请选择楼宇");
		document.getElementById("Bu_id").focus();
		return false;
	} */
	if(regstrSYSP.test(EhNumber)){
		alert("收费名称不能含有特殊字符！");
	   document.getElementById("EhNumber").focus();
	   return false;
	}
	
	if(cost_startTime=="")
	{
		alert("费用起止日期的开始时间不能为空！");
		document.getElementById("cost_startTime").focus();
		return false;
	}
	if(cost_endTime=="")
	{
		alert("费用起止日期的结束时间不能为空！");
		document.getElementById("cost_endTime").focus();
		return false;
	}
	if(!checkDate(cost_startTime, cost_endTime)){
		alert('费用起止日期的起始时间不能大于费用起止日期的结束时间！');
		return false;
	}
	 var obj = document.getElementsByName("is_numberInt");
	 var is_number = "";
    for(var i=0; i<obj.length; i ++){
        if(obj[i].checked){
        	is_number = obj[i].value;
            
        }
    }


	
	
	var sqlstr="";
	var checkid=new Array();
	getcheckbox(checkid,checkname);
	if(checkid.length>0)
	{
		for(i=0;i<checkid.length;i++)
		{
			var str=checkid[i]+";";			
			sqlstr+=str;
		}
	
	    var Qstring="Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id+"&EhNumber="+EhNumber+"&cost_startTime="+cost_startTime+
		"&cost_endTime="+cost_endTime+"&Scost_startTime="+Scost_startTime+"&Scost_endTime="+Scost_endTime+"&sqlstr="+sqlstr+"&is_number="+is_number;
	    sendPOST(url,Qstring);
	}
	else
	{
		alert("请选择您要操作的收费项目！");
	}
}

/*商户对账查询*/
function select_duizhang(divid,url)
{
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	var sfy=document.getElementById("sfy").value;
	if(Es_id==""){
		alert("请选择小区！");
		document.getElementById("Es_id").focus();
		return false;
	}
	if(timesk=="")
	{
		alert("开始时间不能为空！");
		document.getElementById("timesk").focus();
		return false;
	}
	if(timesj=="")
	{
		alert("结束时间不能为空！");
		document.getElementById("timesj").focus();
		return false;
	}
	if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="timesk="+timesk+"&timesj="+timesj+"&Es_id="+Es_id+"&sfy="+sfy;
	sendPOST(url, QString);
}
/*商户对账查询*/
function select_duizhang1(divid,url)
{
	currentPos=divid;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	if(timesk=="")
	{
		alert("开始时间不能为空！");
		document.getElementById("timesk").focus();
		return false;
	}
	if(timesj=="")
	{
		alert("结束时间不能为空！");
		document.getElementById("timesj").focus();
		return false;
	}
	if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="timesk="+timesk+"&timesj="+timesj;
	sendPOST(url, QString);
}
/**
 * 验证日期
 * @param beginDate
 * @param endDate
 * @return
 */
function checkDate(beginDate,endDate){
	var falg = true;
	beginDate = beginDate.replace(/-/g,'');
	endDate   = endDate.replace(/-/g,'');
	if('' != beginDate && '' != endDate){
		if(parseInt(beginDate,10)>parseInt(endDate,10)){
			falg = false;
		}
	}
	return falg;
}

/*流水查询*/
function select_liushui(divid,url)
{
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	if(Es_id==""){
		alert("请选择小区！");
		document.getElementById("Es_id").focus();
		return false;
	}
	if(timesk=="")
	{
		alert("开始时间不能为空！");
		document.getElementById("timesk").focus();
		return false;
	}
	if(timesj=="")
	{
		alert("结束时间不能为空！");
		document.getElementById("timesj").focus();
		return false;
	}
	if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="timesk="+timesk+"&timesj="+timesj+"&Es_id="+Es_id;
	sendPOST(url, QString);
}
/*流水查询*/
function select_liushui1(divid,url)
{
	currentPos=divid;
	
	var TYPE=document.getElementById("TYPE").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	if(timesk=="")
	{
		alert("开始时间不能为空！");
		document.getElementById("timesk").focus();
		return false;
	}
	if(timesj=="")
	{
		alert("结束时间不能为空！");
		document.getElementById("timesj").focus();
		return false;
	}
	if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="timesk="+timesk+"&timesj="+timesj+"&TYPE="+TYPE;
	sendPOST(url, QString);
}
/*缴费用户批量添加验证*/
function TBPay_User_addpl()
{
	var  Bu_id = document.getElementById("Bu_id").value;
	var  Es_id = document.getElementById("Es_id").value;
	var  EhType = document.getElementById("EhType").value;
	var  yonghu_file = document.getElementById("yonghu_file").value;
	
	if(Es_id=="")
	{
	   alert("小区不能为空！");
	   return false;
	} 
	if(Bu_id=="")
	{
	   alert("楼宇不能为空！");
	   return false;
	} 
	if(EhType=="")
	{
	   alert("房屋类型不能为空！");
	   return false;
	} 
	  
	if(yonghu_file=="")
	{
	   alert("请选择要上传的文件！");
	   document.getElementById("yonghu_file").focus();
	   return false;
	} 
	parent.show_Loading_div();
}

function createReport_sev(wulifilepath,filename,url,anid)
{	
	var  Bu_id = document.getElementById("Bu_id").value;
	BUTTONID=anid;
	var QString="wulifilepath="+wulifilepath+"&filename="+filename;
	sendPOST(url,QString);
	  
}

/**
 * 弹框方法，弹出内容太多时，alert 显示不全
 * @param msg
 * @return
 */
function showAlertMsg(msg,returnDiv,returnURL){
	close_loading_div();//调用关闭loading提示图片的层
	document.getElementById('AlertMessage').innerHTML=msg;
	document.getElementById('AlertMessageShow').style.display="block";
	document.getElementById('AlertReturnDiv').value=returnDiv;
	document.getElementById('AlertReturnURL').value=returnURL;
}
function confirmAlertMsg(){
	var returnDiv = document.getElementById('AlertReturnDiv').value;
	var returnURL = document.getElementById('AlertReturnURL').value;
	showselect(returnDiv,returnURL);
}

/***********************************************************************************/
/****************物业管理--start--金鑫********************************************************************/
//联动查询楼宇信息
function select_louyu(th,divid,url,ts_id){
	currentPos=divid;
	var Es_id=th.value;
	var QString="Es_id="+Es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
	getEhType1(Es_id,ts_id);
}
//联动查询楼宇信息
function select_louyuLD2(th,divid,url,ts_id,divid2,url2,divid3,url3){
	
	currentPos=divid;
	var Es_id=th.value;
	var QString="Es_id="+Es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
	
	setTimeout(function (){
		TBitemjfx_select(divid2,url2,Es_id);
	}, 200);
	setTimeout(function (){
		TBsfy(divid3,url3,Es_id,ts_id);
	}, 300);
}
function TBsfy(divid,url,Es_id,ts_id)
{
	currentPos=divid;
	var QString="Es_id="+Es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
}
//联动查询楼宇信息
function select_louyuLD(th,divid,url,ts_id,divid2,url2){
	
	currentPos=divid;
	var Es_id=th.value;
	var QString="Es_id="+Es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
	
	setTimeout(function (){
		TBitemjfx_select(divid2,url2,Es_id);
	}, 300);
}
//联动查询楼宇信息
function select_louyuLD1(th,divid,url,ts_id,divid2,url2){
	
	currentPos=divid;
	var Es_id=th.value;
	var QString="Es_id="+Es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
	
}
//按条件模糊查询订单
function select_orders(divid,url,ts_id){
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var Bu_id=document.getElementById("Bu_id").value;
	var Un_id=document.getElementById("Un_id").value;
	var EhNumber=document.getElementById("EhNumber").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	var payStatus=document.getElementById("payStatus").value;
	
	var itemName=document.getElementById("itemName").value;
	var payType=document.getElementById("payType").value;
	var OwnerName=document.getElementById("OwnerName").value;
	var sfy="";
	if(document.getElementById("sfy")!=null){
		sfy=document.getElementById("sfy").value;
	}
	if(Es_id==""){
		 alert("请选择小区");
	     document.getElementById("Es_id").focus();
	     return false;
	}
	
	/*if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}*/
	if(!CompareDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="ts_id="+ts_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id+"&EhNumber="+EhNumber+"&timesk="+timesk+"&timesj="+timesj+"&payStatus="+payStatus+"&itemName="+itemName+"&payType="+payType+"&OwnerName="+OwnerName+"&sfy="+sfy;
	sendPOST(url,QString);
}

//按条件模糊查询订单
function select_orders1(divid,url,ts_id){
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var Bu_id=document.getElementById("Bu_id").value;
	var Un_id=document.getElementById("Un_id").value;
	var EhNumber=document.getElementById("EhNumber").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	var payStatus="";
	var itemName="";
	var payType="";
	var OwnerName=document.getElementById("OwnerName").value;
	var sfy="";
	if(document.getElementById("sfy")!=null){
		sfy=document.getElementById("sfy").value;
	}
	if(Es_id==""){
		 alert("请选择小区");
	     document.getElementById("Es_id").focus();
	     return false;
	}
	
	/*if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}*/
	if(!CompareDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="ts_id="+ts_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id+"&EhNumber="+EhNumber+"&timesk="+timesk+"&timesj="+timesj+"&payStatus="+payStatus+"&itemName="+itemName+"&payType="+payType+"&OwnerName="+OwnerName+"&sfy="+sfy;
	sendPOST(url,QString);
}

function CompareDate(beginDate,endDate){
	var falg = true;
	beginDate = beginDate.replace(/-/g,"\/");
	endDate   = endDate.replace(/-/g,"\/");
	if('' != beginDate && '' != endDate){
		if(new Date(beginDate)>new Date(endDate)){
			falg = false;
		}
	}
	return falg;
}
//按条件模糊查询订单(人工收费)
function select_orders_rgsf(divid,url,ts_id){
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var Bu_id=document.getElementById("Bu_id").value;
	var EhNumber=document.getElementById("EhNumber").value;
	var OwnerName=document.getElementById("OwnerName").value;
	
	
	var QString="ts_id="+ts_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&EhNumber="+EhNumber+"&OwnerName="+OwnerName;
	sendPOST(url,QString);
}
//按条件模糊查询订单（自助缴费）
function select_orders_zzjf(divid,url,ts_id){
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var Bu_id=document.getElementById("Bu_id").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	var EhNumber=document.getElementById("EhNumber").value;
	var OwnerName=document.getElementById("OwnerName").value;
	var itemName=document.getElementById("itemName").value;
	
	if(Es_id==""){
		 alert("请选择小区");
	     document.getElementById("Es_id").focus();
	     return false;
	}
	if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="ts_id="+ts_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&EhNumber="+EhNumber+"&timesk="+timesk+"&timesj="+timesj+"&itemName="+itemName+"&OwnerName="+OwnerName;
	sendPOST(url,QString);
}
/*弹出层的方法*/
function new_show_see_tanchu_div2(ID,width,height,url,i) {
	var userInfo =document.getElementById(ID);
	var x1 =0; 
	var y1 =70;
	userInfo.style.position = "absolute";
	userInfo.style.left = x1 + "px";
	userInfo.style.top = y1 + "px";
	userInfo.style.width ='100%';
	userInfo.style.height = height+"px";
	userInfo.style.display="block";
	currentPos=ID;
	var Eo_id=document.getElementById("Eo_id"+i+"").value;
	var Bu_id=document.getElementById("Bu_id"+i+"").value;
	var Qstring="divid="+ID+"&Eo_id="+Eo_id+"&Bu_id="+Bu_id;
	sendPOST(url,Qstring);
}
 
//生成报表
function createReportTB_Estate_Order(wulifilepath,filename,url,anid) {
	BUTTONID=anid;
	var QString="wulifilepath="+wulifilepath+"&filename="+filename;
	sendPOST(url, QString);
}
/*复选框全选全取消的方法(不可用的复选框不可以选择)*/
function CheckAllTBOrders(obj,checkname) {
	if(obj.checked==false) {//取消全选
		var options=document.getElementsByName(checkname).length;//这里的checkname是指checkbox组的name名称
		if(options>0) {
			for(var i=0;i<options;i++) {
				document.getElementsByName(checkname)[i].checked=false;
			}
		}
	} else {//全部选中
		var options=document.getElementsByName(checkname).length;//这里的checkname是指checkbox组的name名称
		if(options>0) {
			for(i=0;i<options;i++) {
				if(document.getElementsByName(checkname)[i].disabled==false) {
					document.getElementsByName(checkname)[i].checked=true;
				}
			}
		}
	}
}
//删除订单
function delete_orders(url,url2,ts_id,returndivid,i){
	URL2=url2;
	OBJ=returndivid;
	var Eo_id=document.getElementById("Eo_id"+i+"").value;
	if(confirm("确定要删除吗？")) {
		var QString="ts_id="+ts_id+"&Eo_id="+Eo_id;
		sendPOST(url,QString);
	} else {
		return false;
	}
}
//修改订单
function update_orders(url,url2,ts_id,returndivid,status){
	URL2=url2;
	OBJ=returndivid;
	var Eo_id=document.getElementById("Eo_id").value;
	var total=document.getElementById("total").value;
	var up_Reason= "";
	var b2 = "";
	if(status == "0"){
		 up_Reason=document.getElementById("up_Reason").value;
		 b2=document.getElementById("b2").value;
	}
	
	
	if(total==""){
		alert("缴费金额不能为空");
		return false;	
	}
	/*if(up_Reason==""){
		alert("修改说明不能为空");
		return false;	
	}
	if(b2==""){
		alert("加减金额不能为空");
		return false;	
	}*/
	if(status == "0"){
		if(b2 != "" && b2 != null){
			if(!is_b2.test(b2)){
				alert("加减金额格式不正确，例如：+1.20  -1.20");
				return false;	
			}
			if(up_Reason=="" || up_Reason == null){
				alert("修改说明不能为空");
				return false;
			}
		}
	}
	
	
	
	if(!isPrice.test(total))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("total").focus();
		return false;		
	}	
	
	if(confirm("确定要修改订单信息吗？")) {
		var QString="ts_id="+ts_id+"&Eo_id="+Eo_id+"&total="+total+"&up_Reason="+up_Reason+"&b21="+b2;
		sendPOST(url,QString);
	} else {
		return false;
	}
}
//人工收费
/*function update_orders_rgsf(url,url2,ts_id,returndivid,i){
	URL2=url2;
	OBJ=returndivid;
	var Eo_id=document.getElementById("Eo_id"+i+"").value;
	var total=document.getElementById("total"+i+"").value;
	var total_znj=document.getElementById("total_znj"+i+"").value;
	
	var total_sj = prompt("请输入实缴金额");//手动输入实缴金额
	if(total_sj=="0.00"||total_sj==""||total_sj==null){
		alert("请手动输入实缴金额！");
		return false;
	}else{
		if(!isPrice.test(total_sj))
		{
			alert("单价（元）金额格式错误，最多保留两位小数！");
			return false;		
		}	
		if(parseFloat(total_sj)<parseFloat(total)){
			alert("实缴金额必须大于或等于应缴金额！！！");
			return false;
		}
	}
	var payType="1";
	
	var QString="ts_id="+ts_id+"&Eo_id="+Eo_id+"&total="+total+"&total_znj="+total_znj+"&total_sj="+total_sj+"&payType="+payType;
	sendPOST(url,QString);
	
}*/
//人工收费
function update_orders_rgsf(url,url2,returndivid){
	URL2=url2;
	OBJ=returndivid;
	var ts_id=document.getElementById("ts_id").value;
	var Eo_id=document.getElementById("Eo_id").value;
	var total=document.getElementById("total").value;
	var total_znj=document.getElementById("total_znj").value;
	var total_sj=document.getElementById("total_sj").value;
	var payType=document.getElementById("jfType").value;
	if(total_sj=="0.00"||total_sj==""||total_sj==null){
		alert("请手动输入实缴金额！");
		return false;
	}else{
		if(!isPrice.test(total_sj))
		{
			alert("单价（元）金额格式错误，最多保留两位小数！");
			return false;		
		}	
		if(parseFloat(total_sj)<parseFloat(total)){
			alert("实缴金额必须大于或等于应缴金额！！！");
			return false;
		}
	}
	if(payType==""){
		alert("请选择线下缴费方式!");
		document.getElementById("jfType").focus();
		return false;
	}
	var QString="ts_id="+ts_id+"&Eo_id="+Eo_id+"&total="+total+"&total_znj="+total_znj+"&total_sj="+total_sj+"&payType="+payType;
	sendPOST(url,QString);
}


//人工收费
function update_orders_rgsf2(ID,width,height,url,i,ts_id) {
	 
	var userInfo =document.getElementById(ID);
	var x1 =0; 
	var y1 =70;
	userInfo.style.position = "absolute";
	userInfo.style.left = x1 + "px";
	userInfo.style.top = y1 + "px";
	userInfo.style.width ='100%';
	userInfo.style.height = height+"px";
	userInfo.style.display="block";
	currentPos=ID;
	var Eo_id=document.getElementById("Eo_id"+i+"").value;
	var Bu_id=document.getElementById("Bu_id"+i+"").value;
	var total=document.getElementById("total"+i+"").value;
	var total_znj=document.getElementById("total_znj"+i+"").value;
	var QString="ts_id="+ts_id+"&Eo_id="+Eo_id+"&total="+total+"&total_znj="+total_znj+"&Bu_id="+Bu_id;
	//var Qstring="divid="+ID+"&Eo_id="+Eo_id+"&Bu_id="+Bu_id;
	 
	sendPOST(url,QString);
}
function update_orders_rgsf3(ID,width,height,url,i,ts_id) {
	 
	var userInfo =document.getElementById(ID);
	var x1 =0; 
	var y1 =70;
	userInfo.style.position = "absolute";
	userInfo.style.left = x1 + "px";
	userInfo.style.top = y1 + "px";
	userInfo.style.width ='100%';
	userInfo.style.height = height+"px";
	userInfo.style.display="block";
	currentPos=ID;
	var Eo_id=document.getElementById("Eo_id"+i+"").value;
	var Bu_id=document.getElementById("Bu_id"+i+"").value;
	var total=document.getElementById("total"+i+"").value;
	var total_znj="0.00";
	var QString="ts_id="+ts_id+"&Eo_id="+Eo_id+"&total="+total+"&total_znj="+total_znj+"&Bu_id="+Bu_id;
	//var Qstring="divid="+ID+"&Eo_id="+Eo_id+"&Bu_id="+Bu_id;
	 
	sendPOST(url,QString);
}
//批量选择订单
function pldelTB_Estate_Order(checkname,url,url2,returndivid) {
	URL2=url2;
	OBJ=returndivid;
	var count=0;
	var sqlstr="";
	var checkid=new Array();
	getcheckbox(checkid,checkname);
	if(checkid.length>0) {
		for(var i=0;i<checkid.length;i++) {
			var str=checkid[i];
			if(str.length>0) {
				count++;
				sqlstr+=str+";";
			}
		}
	}
	if(count>0) {
		if(confirm("确定要删除吗？")) {
			var Qstring="sqlstr="+sqlstr;
			sendPOST(url,Qstring);
		} else {
			return false;
		}
	} else {
		alert("请选择您要操作的数据！");
	}
}
//批量现金支付
function pldelTB_Estate_OrderXj(sqlstr,url,url2,returndivid) {
	URL2=url2;
	OBJ=returndivid;
	
	var total_sj = document.getElementById("total_sj").value;
	var payType = document.getElementById("jfType").value;
	var znj_sum = document.getElementById("znj_sum").value;
	if(payType==""){
		alert("请选择线下缴费方式!");
		document.getElementById("jfType").focus();
		return false;
	}
	if(total_sj==""){
		alert("请输入实缴金额!");
		document.getElementById("user_id").focus();
		return false;
	}else{
		var total = document.getElementById("total").value;
//		if(parseFloat(total_sj)==parseFloat(total)){
			if(confirm("确定要进行操作吗？")) {
				var Qstring="sqlstr="+sqlstr+"&total_sj="+total_sj+"&payType="+payType+"&znj_sum="+znj_sum;
				sendPOST(url,Qstring);
			} else {
				return false;
			}
		/*}else{
			alert("实缴金额必须等于应缴金额!");
			document.getElementById("user_id").focus();
			return false;
		}*/
	}
	
}

/****************物业管理-- end --金鑫********************************************************************/
function wuye_login(){
	var user_id=document.getElementById("user_id").value;
	var password=document.getElementById("password").value;
	var code=document.getElementById("code").value;
	if(user_id=="") {
		alert("账号不能为空!");
		document.getElementById("user_id").focus();
		return false;
	}
	if(password=="") {
		alert("密码不能为空!");
		document.getElementById("password").focus();
		return false;
	}
	if(code=="") {
		alert("验证码不能为空!");
		document.getElementById("code").focus();
		return false;
	}
}
/*点1级菜单调用的方法*/
function menu1(ID,url,menu,menu_code,menu_level)
{
	currentPos=ID;	
	var Qstring="menu="+menu+"&menu_code1="+menu_code+"&menu_level="+menu_level;   
	
    sendPOST(url,Qstring);	
}
/*点2级菜单调用的方法*/
function menu2(menu_code ,i,count,url)//,menu_code)
{
	currentPos="showmain";
	document.getElementById(menu_code+"li"+i).style.color = "#004274";
	document.getElementById(menu_code+"li"+i).style.fontWeight = "bold";
	for(j=0;j<count;j++)
	{
		if(j!=i)
		{
			document.getElementById(menu_code+"li"+j).style.color = "#000000";
			document.getElementById(menu_code+"li"+j).style.fontWeight = "normal";
		}
	}
	
	send_request(url);
}
function addorg(divid,url,url2){
	returnDIVID=divid;
	returnURL=url2;
	var org_id= document.getElementById("org_id").value;
	var org_name= trim(document.getElementById("orgName").value);
	var orgRemark= trim(document.getElementById("orgRemark").value);
	if(org_name==""){
		alert("机构名称不能为空！！");
		document.getElementById("orgName").focus();
		return false;
	}else if(!regstrSY.test(org_name)){
		alert("机构名称不能有特殊字符！");
		return false;
	}
	if(orgRemark!=""){
		if(!regstrSY.test(orgRemark)){
			alert("机构描述不能有特殊字符！");
			return false;
	    }
	}
	var QString = "org_id="+org_id+"&org_name="+org_name+"&orgRemark="+orgRemark;
	sendPOST(url, QString);

}
function selectorg(divid,url){
	currentPos=divid;
	var org_id= document.getElementById("org_id").value;
	var org_name= document.getElementById("orgName").value;
	var orgRemark= document.getElementById("orgRemark").value;
	var QString = "org_id="+org_id+"&org_name="+org_name+"&orgRemark="+orgRemark;
	sendPOST(url, QString);
	}

/* 修改机构名称 */
function updateorg(org_id, i, url, returndivid, url2) {
	returnDIVID = returndivid;
	returnURL = url2;
	var org_name = trim(document.getElementById("org_name"+i).value);
	var remark = trim(document.getElementById("remark"+i).value);
	if (org_name == "") {
		alert("机构名称不能为空");
		document.getElementById("org_name"+i).focus();
		return false;
	}else if(!regstrSY.test(org_name)){
		alert("机构名称不能有特殊字符！");
		return false;
	}
	if(remark!=""){
		if(!regstrSY.test(remark)){
			alert("机构描述不能有特殊字符！");
			return false;
	    }
	}
	var QString = "org_id=" + org_id+"&org_name="+org_name+"&remark="+remark;
	sendPOST(url, QString);
}
/* 删除机构名称 */
function delectorg(org_id, i, url, returndivid, url2) {
	returnDIVID = returndivid;
	returnURL = url2;
	var org_name = document.getElementById("org_name"+i).value;
	var QString = "org_id=" + org_id+"&org_name="+org_name;
	if(confirm("确定删除'"+org_name+"'吗？")){
		sendPOST(url, QString);
	}
}
/*弹出层的方法*/
function new_show_see_tanchu_div(ID,width,height,url) {
	//最佳答案 屏幕分辨率的高：window.screen.height 
	//屏幕分辨率的宽：window.screen.width 
	//屏幕可用工作区高度：window.screen.availHeight 
	//屏幕可用工作区宽度：window.screen.availWidth 
	//获得鼠标点击的X坐标 var x1 = event.clientX+10;
	//获得鼠标点击的Y坐标 var y1 = event.clientY+5;
	//获得鼠标点击的绝对X坐标 var x1 = event.clientX + document.body.scrollLeft - document.body.clientLeft;
	//获得鼠标点击的绝对Y坐标 var y1 = event.clientY +document.body.scrollTop - document.body.clientTop - 150 ;
	var userInfo =document.getElementById(ID);
	var x1 =0;//(window.screen.availWidth-width)/2-7;
	var y1 =70;//event.clientY-0;//document.body.scrollTop+150;
	//var userInfo =document.getElementById(ID);
	//var x1 = (window.screen.availWidth-width)/2+25;
	//var y1 = document.body.scrollTop+150;
	userInfo.style.position = "absolute";
	userInfo.style.left = x1 + "px";
	userInfo.style.top = y1 + "px";
	userInfo.style.width ='100%';
	userInfo.style.height = height+"px";
	userInfo.style.display="block";
	currentPos=ID;
	var Qstring="divid="+ID;
	sendPOST(url,Qstring);
}
/*注册用户查询管理页面弹出层的方法*/
function edit_show_see_tanchu_div( ID,width,height,url,parID)
{
 

 	 var userInfo =document.getElementById(ID);
     var x1 =0; 
     var y1 =70; 
     userInfo.style.position = "absolute";
     userInfo.style.left = x1 + "px";
     userInfo.style.top = y1 + "px";
     userInfo.style.display="block";  
     
     currentPos=ID;
     var Qstring="divid="+ID+"&parID="+parID;
	 sendPOST(url,Qstring);
}
 
/*生成全部的菜单tree，层隐藏，层打开关闭的方法*/
function menu_tree_all(jiajian,jia,jian,ID) {
	if(document.getElementById(ID).style.display=="block") {//alert("1"+jia+"|"+ID);
		document.getElementById(ID).style.display = "none";
		document.getElementById(jiajian).className=jia;
	} else {//alert("2"+jian+"|"+ID);
		document.getElementById(ID).style.display = "block";
		document.getElementById(jiajian).className=jian;
	}
}
/*添加角色、编辑角色页面菜单某级别及下级菜单全部选中、取消的方法*/
function role_Check_menu(obj,checkid) {
	if(obj.checked==false) {//取消全选
		var options=document.getElementsByName(checkid).length;//这里的checkid是指表单元素checkbox的ID名称
		if(options>0) {
			for(var i=0;i<options;i++) {
				document.getElementsByName(checkid)[i].disabled= !obj.checked;
				checkNoneChild(checkid);//取消选择全部 二级
				checkddisabled(checkid);//未选中时，下级不可用
			}
		}
	} else {//全选
		var options=document.getElementsByName(checkid).length;//这里的checkid是指表单元素checkbox的ID名称
		if(options>0) {
			for(var i=0;i<options;i++) {
				document.getElementsByName(checkid)[i].disabled= !obj.checked;
				checkAllChild(checkid);//选择全部 二级
				checkdisabled(checkid);//选中时，下级可用
			}
		}
	}
}
/*选中时，可用*/
function checkdisabled(checkid){
	var options=document.getElementsByName(checkid).length;//这里的checkid是指表单元素checkbox的ID名称
	if(options>0) {
		for(var i=0;i<options;i++) {
			document.getElementsByName(checkid)[i].disabled= false;
		}
	}
}
/*未选中时，不可用*/
function checkddisabled(checkid) {
	var options=document.getElementsByName(checkid).length;//这里的checkid是指表单元素checkbox的ID名称
	if(options>0) {
		for(var i=0;i<options;i++) {
			document.getElementsByName(checkid)[i].disabled= true;
		}
	}
}
/*选择全部 二级*/
function checkAllChild(checkid) {
	var options=document.getElementsByName(checkid).length;//这里的checkid是指表单元素checkbox的ID名称
	if(options>0) {
		for(var i=0;i<options;i++) {
			document.getElementsByName(checkid)[i].checked=true;//本级全部选中
			var two=document.getElementsByName(checkid)[i].value;
			checkAllChild3(two);//选中全部3级内容
		}
	}
}
/*取消选择全部 二级*/
function checkNoneChild(checkid) {
	var options=document.getElementsByName(checkid).length;//这里的checkid是指表单元素checkbox的ID名称
	if(options>0) {
		for(var i=0;i<options;i++) {
			document.getElementsByName(checkid)[i].checked=false;//本级全部不选
			var two=document.getElementsByName(checkid)[i].value;
			checkNoneChild3(two);//取消选中全部3级内容
		}
	}
}
/*选择全部 三级*/
function checkAllChild3(checkid) {
	var options=document.getElementsByName(checkid).length;//这里的checkid是指表单元素checkbox的ID名称
	if(options>0){
		for(var j=0;j<options;j++) {
			document.getElementsByName(checkid)[j].disabled=false;
			document.getElementsByName(checkid)[j].checked=true;//本级全部选中
			var Three=document.getElementsByName(checkid)[j].value;
			checkAllChild4(Three);//选中全部3级内容
		}
	}
}
/*取消选择全部 三级*/
function checkNoneChild3(checkid) {
	var options=document.getElementsByName(checkid).length;//这里的checkid是指表单元素checkbox的ID名称
	if(options>0){
		for(var j=0;j<options;j++) {
			document.getElementsByName(checkid)[j].disabled=true;
			document.getElementsByName(checkid)[j].checked=false;//本级全部不选
			var Three=document.getElementsByName(checkid)[j].value;
			checkNoneChild4(Three);//取消选中全部3级内容
		}
	}
}
/*选择全部 四级*/
function checkAllChild4(checkid) {
	var options=document.getElementsByName(checkid).length;//这里的checkid是指表单元素checkbox的ID名称
	if(options>0){
		for(var k=0;k<options;k++) {
			document.getElementsByName(checkid)[k].checked=true;
			document.getElementsByName(checkid)[k].disabled=false;
		}
	}
}
/*取消选择全部 四级*/
function checkNoneChild4(checkid) {
	var options=document.getElementsByName(checkid).length;//这里的checkid是指表单元素checkbox的ID名称
	if(options>0){
		for(var k=0;k<options;k++) {
			document.getElementsByName(checkid)[k].checked=false;
			document.getElementsByName(checkid)[k].disabled=true;
		}
	}
}
/*全选全取消的方法*/
function CheckAll(obj,form) {
	if(obj.checked==false) {//取消全选
		var options=form.elements.length;//这里的checkid是指表单元素checkbox的ID名称
		if(options>0) {
			for(var i=0;i<options;i++) {
				var e = form.elements[i];
				e.checked=false;
				if(e.name != 'chkall' && e.name != 'checkbox1') {
					e.disabled=true;
				}
			}
		}
	} else {//全选
		var options=form.elements.length;//这里的checkid是指表单元素checkbox的ID名称
		if(options>0){
			for(var i=0;i<options;i++) {
				var e = form.elements[i];
				e.checked=true;
				if(e.name != 'chkall' && e.name != 'checkbox1') {
					e.disabled=false;
				}
			}
		}
	}
}
/*商户操作员修改*/
function updateczy(checknamexq,url,url2) 
{ 
	returnDIVID="showmain";
	returnURL=url2;
	var  org_id=document.getElementById("czy_edit_orgid").value;
	var  userid = trim(document.getElementById("czy_edit_id").value);
	var  username = trim(document.getElementById("czy_edit_name").value);
	var  phone = trim(document.getElementById("czy_edit_phone").value);
	var  ramark = trim(document.getElementById("czy_edit_remark").value);
	var  tu_id = trim(document.getElementById("tu_idx").value);
	var  role_code = trim(document.getElementById("role_codex").value);
	if(username=="")
	{
	   alert("用户姓名不能为空");
	   document.getElementById("czy_edit_name").focus();
	   return false;
	}else if(!regstrSY.test(username)){
		alert("姓名不能含有特殊字符！");
		return false;
	}
	if(phone!="") {
		if(!regphone.test(phone)) {
			alert("手机号格式不正确，请重新输入！！");
			document.getElementById("czy_edit_phone").focus();
		return false;
		}
	}
	if(ramark!=""){
		if(!regstrSY.test(ramark)){
			alert("备注不能含有特殊字符！");
			return false;
		}
	}
	var menu_code = "";
	var s1="";
	var checkid1=new Array();
	checkid1 =getcheckbox(checkid1,"checkbox1");
	    
	for(var i=0;i<checkid1.length;i++)
	{
		s1 = s1+checkid1[i]+",";	//循环得到第一级选中的菜单
		
		var checkid2=new Array();
		checkid2 = getcheckbox(checkid2,checkid1[i]);//循环得到第一级选中的下一级菜单组名
		var s2="";
		 
		for(var j =0;j<checkid2.length;j++)
		{
			s2 =s2+checkid2[j]+",";		//循环得到第2级选中的菜单
			
			var checkid3=new Array();
			checkid3 = getcheckbox(checkid3,checkid2[j]);//循环得到第2级选中的下一级菜单组名
			var s3 ="";
			
			for(var k=0;k<checkid3.length;k++)
			{
				s3= s3 + checkid3[k]+",";	//循环得到第3级选中的菜单
				var checkid4=new Array();
				checkid4 = getcheckbox(checkid4,checkid3[k]);//循环得到第3级选中的下一级菜单组名
				var s4 ="";
				for(var l=0;l<checkid4.length;l++)
				{
					s4=s4 + checkid4[l]+",";	//循环得到第4级选中的菜单
				}
				s3=s3+s4;
			}
			s2=s2+s3;	 
		}
		s1=s1+s2;	
	}
 	menu_code=s1.substring(0,s1.length-1);

	if(menu_code==""){
		alert("操作员权限不能为空，请选择操作员权限！");
		return;
	}	
	var stres_id="";
	var m=document.getElementsByName(checknamexq).length;
	for(var n=0;n<m;n++)
	{
	
		if(document.getElementsByName(checknamexq)[n].checked==true)
		{
			stres_id+=document.getElementsByName(checknamexq)[n].value+",";
		}
	}
	if(stres_id==""){
		alert("请选择小区!!");
		return false;
	}
	var Isdrawback=$("input[name='isTkqx']:checked").val(); //是否开通退款权限
	var payWays="";
	var checkidPay=new Array();
	getcheckbox(checkidPay,"xxjf"); //线下缴费方式
	if(checkidPay.length>=1) {
		for(var i=0;i<checkidPay.length;i++) {
			payWays=payWays +checkidPay[i]+",";
		};
	}

	URL2=url2;
	
	var QString="menu_code="+menu_code+"&userid="+userid+"&username="+username+"&phone="+phone+"&ramark="+ramark+"&org_id="+org_id+"&tu_id="+tu_id+"&role_code="+role_code+"&stres_id="+stres_id+"&Isdrawback="+Isdrawback+"&payWays="+payWays;

	sendPOST(url,QString);

}


//添加操作员
function addczy(checknamexq,url,url2) {
	returnDIVID="showmain";
	var Org_id = trim(document.getElementById("Org_id1").value);
	var czyname= trim(document.getElementById("czyname1").value);
	var czyid= trim(document.getElementById("czyid1").value);
	var czyphone= trim(document.getElementById("czyphone1").value);
	var czyremark= trim(document.getElementById("czyremark1").value);
	
	if(Org_id=="") {
		alert("商户名称不能为空！！");
		document.getElementById("Org_id1").focus();
		return false;
	}
	
	if(czyname==""){
		alert("姓名不能为空！！");
		document.getElementById("czyname1").focus();
		return false;
	}else if(!regstrSY.test(czyname)){
		alert("姓名不能含有特殊字符！");
		return false;
	}
	if(czyid=="") {
		alert("操作员账号不能为空！！");
		document.getElementById("czyid1").focus();
		return false;
	}
	if(czyid!=""){
		if(!regstr.test(czyid)){
			alert("操作员账号必须为数字或字母，请重新输入！！");
			document.getElementById("czyid1").focus();
			return false;
		};
	}
	if(czyphone!="") {
		if(!regphone.test(czyphone)) {
			alert("手机号格式不正确，请重新输入！！");
			document.getElementById("czyphone1").focus();
		return false;
		};
	}
	if(czyremark!=""){
		if(!regstrSY.test(czyremark)){
			alert("备注不能含有特殊字符！");
			return false;
		};
	}
	var menu_code = "";
	var s1="";
	var checkid1=new Array();
	checkid1 = getcheckbox(checkid1,"checkbox1");
	for(var i=0;i<checkid1.length;i++) {
		s1 = s1+checkid1[i]+",";//循环得到第一级选中的菜单
		var checkid2=new Array();
		checkid2 = getcheckbox(checkid2,checkid1[i]);//循环得到第一级选中的下一级菜单组名
		var s2="";
		for(var j =0;j<checkid2.length;j++) {
			s2 =s2+checkid2[j]+",";//循环得到第2级选中的菜单
			var checkid3=new Array();
			checkid3 = getcheckbox(checkid3,checkid2[j]);//循环得到第2级选中的下一级菜单组名
			var s3 ="";
			for(var k=0;k<checkid3.length;k++) {
				s3= s3 + checkid3[k]+",";//循环得到第3级选中的菜单
				var checkid4=new Array();
				checkid4 = getcheckbox(checkid4,checkid3[k]);//循环得到第3级选中的下一级菜单组名
				var s4 ="";
				for(var l=0;l<checkid4.length;l++) {
					s4=s4 + checkid4[l]+",";//循环得到第4级选中的菜单
				}
				s3=s3+s4;
			}
			s2=s2+s3;
		}
		s1=s1+s2;
	}
	menu_code=s1.substring(0,s1.length-1);
	if(menu_code==""){
		alert("角色权限不能为空，请选择操作员权限！");
		return false;
	}
	
	

	var stres_id="";
	var m=document.getElementsByName(checknamexq).length;
	for(var n=0;n<m;n++)
	{
	
		if(document.getElementsByName(checknamexq)[n].checked==true)
		{
			stres_id+=document.getElementsByName(checknamexq)[n].value+",";
		};
	}
	if(stres_id==""){
		alert("请选择小区!!");
		return false;
	}
	var Isdrawback=$("input[name='isTkqx1']:checked").val(); //是否开通退款权限
	var payWays="";
	var checkidPay=new Array();
	getcheckbox(checkidPay,"xxjf1"); //线下缴费方式
	if(checkidPay.length<1) {
		alert("请给管理员选择至少一种线下缴费方式！");
		return false;
	}else{
		for(var i=0;i<checkidPay.length;i++) {
			payWays=payWays +checkidPay[i]+",";
		};
	}
	if(payWays==""){
		alert("为管理员选择至少一种线下缴费方式");
		return false;
	}
	returnURL=url2;
	var QString="menu_code="+menu_code+"&Org_id="+Org_id+"&czyname="+czyname+"&czyid="+czyid+"&czyphone="+czyphone+"&czyremark="+czyremark+"&stres_id="+stres_id+"&Isdrawback="+Isdrawback+"&payWays="+payWays;
	sendPOST(url,QString);
}
function selectczy(org_level,divid,url){
	currentPos=divid;
	var Org_id = trim(document.getElementById("Org_id2").value);
	var czyname= trim(document.getElementById("czyname2").value);
	var czyid= trim(document.getElementById("czyid2").value);
	var QString = "Org_id="+Org_id+"&czyname="+czyname+"&czyid="+czyid+"&org_level="+org_level;
	sendPOST(url, QString);
	}
/*重置密码*/
function resetPassword(url,tu_id,password,url2)
{  
	if(confirm("重置密码将把密码重置为111111，确定要重置密码？"))
	{
		//returnURL=url2;
		
		URL2=url2;
		
		var Qstring="tu_id="+tu_id+"&password="+password;

		sendPOST(url,Qstring);
	}
	else
	{
		return false;
	}
}
 
/* 删除操作员 */
function delectableczy(userid, i, returndivid,url, url2) {
	returnDIVID = returndivid;
	returnURL = url2;
	var delectczy_name = document.getElementById("delectczy_name"+i).value;
	var QString = "userid="+userid+"&czyname="+delectczy_name;
	if(confirm("确定删除'"+delectczy_name+"'吗？")){
		sendPOST(url, QString);
	}
}
/*更新密码*/
function updateMima(url,url2)
{
	returnDIVID="showmain";
    returnURL=url2;
    var oldPassword=document.getElementById("oldPassword").value;
    
    if(trim(oldPassword)==""){
       
       alert("请输入旧密码");
       document.getElementById("oldPassword").focus();	
       return false;
    
    }
    else if(!regstring.test(oldPassword)){
    
        alert("旧密码只能是字母或数字");
        document.getElementById("oldPassword").focus();	
        return false;
    }
   
    var newPassword=document.getElementById("newPassword").value;
    if(newPassword.length<6){
    	alert("新密码长度必须大于等于6位且小于12位");
    	document.getElementById("newPassword").focus();
    	 return false;
    }else if(newPassword.length>12){
        
    	alert("新密码长度必须大于等于6位且小于12位");
    	document.getElementById("newPassword").focus();
    	 return false;}
    else if(trim(newPassword)==""){
       
       alert("请输入新密码");
       document.getElementById("newPassword").focus();	
       return false;
   
    }else if(!regstring.test(newPassword)){
    
        alert("新密码只能是字母或数字");
        document.getElementById("newPassword").focus();	
        return false;
    }
   
    var confirmPassword=document.getElementById("comfirmPassword").value;
    
     if(trim(confirmPassword)==""){
       
       alert("请输入确认密码");
       document.getElementById("comfirmPassword").focus();	
       return false;
  
    }
    
    if(trim(newPassword)!=trim(confirmPassword)){
      
       alert("两次输入密码不一致");
       
       return false;
    }
    
    var Qstring="oldPassword="+oldPassword+"&newPassword="+newPassword;
    
    sendPOST(url,Qstring);
}
//查看管理员日志
function admin_log_show(divid,url) {
	currentPos=divid;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	var cznr=document.getElementById("cznr").value;
	var Qstring="timesk="+timesk+"&timesj="+timesj+"&cznr="+cznr;
	sendPOST(url,Qstring);
}
/*缴费用户登陆验证项设置*/
function TBPay_UserdlSet(checkname,url,url2)
{
	returnDIVID="showmain";
	returnURL=url2;
	var Es_id=document.getElementById("Es_id").value;
	var yznr="";
	var j=document.getElementsByName(checkname).length;
	for(var i=0;i<j;i++)
	{
		if(document.getElementsByName(checkname)[i].checked==true)
		{
			yznr+="1";
		}
		else
		{
			yznr+="0";
		}
	}
	var Qstring="yznr="+yznr+"&es_id="+Es_id;

	sendPOST(url,Qstring);
} 
function select_yonghudlszshow1(th,divid,url)
{
	currentPos=divid;
	
	var es_id=th.value;
	var Qstring="es_id="+es_id;

	sendPOST(url,Qstring);
}
/*退出系统*/
function tuichu_xitong(url)
{
	var truthBeTold = window.confirm("您确定要退出系统吗？单击“确定”退出！");
	if (truthBeTold)
	{	
		window.location=url;
	}		
}
function tick()   
{   
    var today;   
    today = new Date();   
    document.getElementById("localtime").innerHTML = showLocale(today);   
    window.setTimeout("tick()", 1000);   
    
}   
function showLocale(objD)   
{   
    var str,colorhead,colorfoot;   
    var yy = objD.getYear();   
        if(yy<1900) yy = yy+1900;   
    var MM = objD.getMonth()+1;   
        if(MM<10) MM = '0' + MM;   
    var dd = objD.getDate();   
        if(dd<10) dd = '0' + dd;   
    var hh = objD.getHours();   
        if(hh<10) hh = '0' + hh;   
    var mm = objD.getMinutes();   
        if(mm<10) mm = '0' + mm;   
    var ss = objD.getSeconds();   
        if(ss<10) ss = '0' + ss;   
    var ww = objD.getDay();   
        if ( ww==0 ) colorhead="<font color=\"#FF0000\">";   
        if ( ww > 0 && ww < 6 ) colorhead="<font color=\"#373737\">";   
        if ( ww==6 ) colorhead="<font color=\"#008000\">";   
        if (ww==0) ww="星期日";   
        if (ww==1) ww="星期一";   
        if (ww==2) ww="星期二";   
        if (ww==3) ww="星期三";   
        if (ww==4) ww="星期四";   
        if (ww==5) ww="星期五";   
        if (ww==6) ww="星期六";   
        colorfoot="</font>"  
            str = colorhead + yy + "年" + MM + "月" + dd + "日" + hh + ":" + mm + ":" + ss + " " + ww + colorfoot;   
        return(str);   
}
/****抄表开始*******/
function showlou(divid,ts_id,url){
	currentPos=divid;
	var xiaoquid = document.getElementById("xiaoquid").value;
	QString="xiaoquid="+xiaoquid+"&ts_id="+ts_id;
	sendPOST(url,QString);
}
/*用户模板下载*/
function metecreateReport_sev(wulifilepath,filename,url,anid){
	BUTTONID=anid;
	var xiaoquid = document.getElementById("xiaoquid").value;
	if(xiaoquid==""){
		alert("请选择小区信息");
		document.getElementById("xiaoquid").focus();
		return false;
	}
	var louid = document.getElementById("louid").value;
	var Mtype = document.getElementById("type").value;
	var unit = document.getElementById("unit").value;
	var QString="wulifilepath="+wulifilepath+"&filename="+filename+"&xiaoquid="+xiaoquid+"&louid="+louid+"&Mtype="+Mtype+"&unit="+unit;
	sendPOST(url,QString);
}
/*用户模板下载*/
function metecreateReport(ts_id,wulifilepath,filename,url,anid){
	BUTTONID=anid;
	var QString="wulifilepath="+wulifilepath+"&filename="+filename+"&ts_id="+ts_id;
	sendPOST(url,QString);
}
/*缴费用户批量添加验证*/
function TB_Mete_pl()
{
	var METE_file = document.getElementById("METE_file").value;
	var xiaoquid = document.getElementById("xiaoquid").value;
	var type = document.getElementById("type").value;
	var unit = document.getElementById("unit").value;
	
	if(METE_file=="") {
		alert("请选择要上传的文件！");
		document.getElementById("METE_file").focus();
		return false;
	} 
	if(xiaoquid==""){
		alert("请选择小区信息！");
		document.getElementById("xiaoquid").focus();
		return false;
	}
	if(type==""){
		alert("请选择费用类型！");
		document.getElementById("type").focus();
		return false;
	}
	if(unit==""){
		alert("请选择计量单位！");
		document.getElementById("unit").focus();
		return false;
	}
	parent.show_Loading_div();
}
/***查询抄表信息****/
function selectMete(divid,url){
	currentPos=divid;
	var ts_id=document.getElementById("ts_id").value;
	var xiaoquid=document.getElementById("xiaoquid").value;
	var louid=document.getElementById("louid").value;
	var Un_id=document.getElementById("Un_id").value;
	var fwid=document.getElementById("fwid").value;
	var type=document.getElementById("type").value;
	var orderStatus=document.getElementById("orderStatus").value;
/*	if(fwid!=""){
		if(!regstr.test(fwid)){
			alert("房屋编号必须为字母或者数字类型!");
			document.getElementById("fwid").focus();
			return false;
		}
	}*/
	var QString = "ts_id="+ts_id+"&xiaoquid="+xiaoquid+"&louid="+louid+"&Un_id="+Un_id+"&fwid="+fwid+"&metetype="+type+"&orderStatus="+orderStatus;
	sendPOST(url,QString);
	
}
/*注册用户查询管理页面弹出层的方法*/
function Mete_tanchu_div(ID,width,height,url,parID,ma_id){
	//最佳答案 屏幕分辨率的高：window.screen.height 
	//屏幕分辨率的宽：window.screen.width 
	//屏幕可用工作区高度：window.screen.availHeight 
	//屏幕可用工作区宽度：window.screen.availWidth 
	//获得鼠标点击的X坐标 var x1 = event.clientX+10;	
	//获得鼠标点击的Y坐标 var y1 = event.clientY+5;  
	//获得鼠标点击的绝对X坐标 var x1 = event.clientX + document.body.scrollLeft - document.body.clientLeft;
	//获得鼠标点击的绝对Y坐标 var y1 = event.clientY +document.body.scrollTop  - document.body.clientTop - 150 ;

	 	 var userInfo =document.getElementById(ID);
		 var x1 =0;//(window.screen.availWidth-width)/2-7;
		 var y1 =0;// event.clientY-0;//document.body.scrollTop+150;
	 	 //var userInfo =document.getElementById(ID);
		 //var x1 = (window.screen.availWidth-width)/2+25;
		 //var y1 = document.body.scrollTop+150;
		 userInfo.style.position = "absolute";
		 userInfo.style.left = x1 + "px";
		 userInfo.style.top = y1 + "px";
		 
		 //userInfo.style.width = width+"px";
		 //userInfo.style.height = height+"px";
		 userInfo.style.display="block";  
		 
		 currentPos=ID;
		 var Qstring="divid="+ID+"&em_id="+parID+"&ma_id="+ma_id;
		 sendPOST(url,Qstring);
}
/*删除订单*/
function Mete_delete(ma_id,em_id,url,divid,url2)
{
	OBJ=divid;
	URL2=url2;
	if(confirm("您正在删除订单，确定要删除吗？"))
	{
		var QString="Em_id="+em_id+"&ma_id="+ma_id;
		
		sendPOST(url,QString);
	}
}
/*批量审核通过或删除的方法*/
function Mete_xinxi_sh(checkname,url,url2,returndivid)
{
	URL2=url2;
 	OBJ=returndivid;
 	
 	var count=0;
	var sqlstr="";
	var checkid=new Array();
	getcheckbox(checkid,checkname);
	if(checkid.length>0)
	{
		for(i=0;i<checkid.length;i++)
		{
			var str=checkid[i];	
			if(str.length>0)
			{
				count++;
				sqlstr+=str+";";
			}
		}
	}
	if(count>0)
	{
		if(confirm("确定要删除吗？"))
		{
			var Qstring="sqlstr="+sqlstr;

			sendPOST(url,Qstring);
		}
		else
		{
			return false;
		}
	}
	else
	{
		alert("请选择您要操作的数据！");
	}
}

/*修改订单*/
function Mete_update(em_id, url, divid, url2,closedivid) {
	OBJ = divid;
	URL2 = url2;
	DIV_ID = closedivid;
	var Maid = trim(document.getElementById("Maid").value);
	if(Maid==""){
		alert("表名称不能为空!");
		document.getElementById("Maid").focus();
		return false;
	}else{
		if(!regstrSY.test(Maid)){
			alert("表名称不能为特殊字符!");
			document.getElementById("Maid").focus();
			return false;
		}
	}
	var LastReadNum = document.getElementById("LastReadNum").value;
	if(LastReadNum==""){
		alert("上次抄表数不能为空!");
		document.getElementById("LastReadNum").focus();
		return false;
	}else{
		if(!regexZX.test(LastReadNum)){
			alert("上次抄表数只能为整数或两位小数!");
			document.getElementById("LastReadNum").focus();
			return false;
		}
	}
	var NowReadNum = document.getElementById("NowReadNum").value;
	if(NowReadNum==""){
		alert("本次抄表数不能为空!");
		document.getElementById("NowReadNum").focus();
		return false;
	}else{
		if(!regexZX.test(NowReadNum)){
			alert("本次抄表数只能为整数或两位小数!");
			document.getElementById("NowReadNum").focus();
			return false;
		}
	}
	if((parseFloat(NowReadNum)-parseFloat(LastReadNum))<0){
		alert("本次抄表数不能小于上次抄表数!");
		return false;
	}
	var UserNumber = document.getElementById("UserNumber").value;
	if(UserNumber==""){
		alert("用量不能为空!");
		document.getElementById("UserNumber").focus();
		return false;
	}else{
		if(!regexZX.test(UserNumber)){
			alert("用量只能为整数或两位小数!");
			document.getElementById("UserNumber").focus();
			return false;
		}
	}
	var ReadDate = document.getElementById("ReadDate").value;
	if(ReadDate==""){
		alert("抄表日期不能为空!");
		document.getElementById("ReadDate").focus();
		return false;
	}else{
		if(!regdate.test(ReadDate)){
			alert("抄表日期格式有误，请按照 年(YYYY)-月(MM)-日(DD)格式添加!");
			document.getElementById("ReadDate").focus();
			return false;
		}
	}
	var MeteType = document.getElementById("MeteType").value;
	var Unit = document.getElementById("Unit").value;
	var Qstring = "em_id=" + em_id+"&Maid="+Maid+"&LastReadNum="+LastReadNum+"&NowReadNum="+NowReadNum+"&UserNumber="+UserNumber+
					"&ReadDate="+ReadDate+"&MeteType="+MeteType+"&Unit="+Unit;
	sendPOST(url, Qstring);
}
function Mete_Order(url,url1,divid){
	returnDIVID=divid;
	returnURL=url1;
	var ts_id=document.getElementById("ts_id").value;
	var xiaoquid=document.getElementById("xiaoquid").value;
	var louid=document.getElementById("louid").value;
	var Un_id=document.getElementById("Un_id").value;
	var fwid=document.getElementById("fwid").value;
	var type=document.getElementById("type").value;
	if(xiaoquid==""){
		alert("请选择小区!");
		document.getElementById("xiaoquid").focus();
		return false;
	}
	if(confirm("您确定一键生成抄表信息收费单吗？"))
	{
		var QString="ts_id="+ts_id+"&Un_id="+Un_id+"&xiaoquid="+xiaoquid+"&louid="+louid+"&fwid="+fwid+"&type="+type;
		sendPOST(url,QString);
	}
	
	
}
/****抄表结束*******/
/*添加小区*/
function TBVillage_add(divid,url,url2)
{
	OBJ="showmain";
	currentPos=divid;
	URL2=url2;
	
	var esName = document.getElementById("EsName").value;	//小区名称
	var EsName = trim(esName);
	var EsHead = document.getElementById("EsHead").value;	//小区负责人
	var EsContact = document.getElementById("EsContact").value;	//小区联系人
	var EsPhone = document.getElementById("EsPhone").value;	//小区联系电话
	var EsAddress = document.getElementById("EsAddress").value;	//小区地址
	var build_Number = document.getElementById("build_Number").value;	//楼宇数量（幢）
	var House_Number = document.getElementById("House_Number").value;	//房屋数量（户）
	var BuildArea = document.getElementById("BuildArea").value;	//建筑面积（m2）
	var FloorArea = document.getElementById("FloorArea").value;	//占地面积（m2）
	var GreenArea = document.getElementById("GreenArea").value;	//绿化面积（m2）
	var VolumeRate = document.getElementById("VolumeRate").value;	//容积率（%）
	var remark = document.getElementById("remark").value;	//备注
	
	var regstrSY1= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\-\,\<\>\？\，\。]+$/;   
	var regmj = /^(\d+\.\d{1,4}|\d+)$/;
	
	if(EsName==""||EsName==null)
	{
		alert("小区名称不能为空！");
		document.getElementById("EsName").focus();
		return false;
	}
	
	if(!regstrSY.test(EsName))
		{
			alert("小区名称格式不正确，请重新输入!");
			document.getElementById("EsName").focus();
			return false;
		}
	if(EsHead!="" && EsHead!=null){
	if(!regstrSY.test(EsHead))
	{
		alert("小区负责人格式不正确，请重新输入!");
		document.getElementById("EsHead").focus();
		return false;
	}
	}
	if(EsContact!="" && EsContact!=null){
	if(!regstrSY.test(EsContact))
	{
		alert("小区联系人格式不正确，请重新输入!");
		document.getElementById("EsContact").focus();
		return false;
	}
	}
	if(EsPhone!="" && EsPhone!=null){
	if(!regphone.test(EsPhone)&&!regtel.test(EsPhone))
	{
		alert("小区联系电话格式不正确，请重新输入!");
		document.getElementById("EsPhone").focus();
		return false;
	}
	}
	if(EsAddress!="" && EsAddress!=null){
	if(!regstrSY.test(EsAddress))
	{
		alert("小区地址格式不正确，请重新输入!");
		document.getElementById("EsAddress").focus();
		return false;
	}
	}
	if(build_Number!="" && build_Number!=null){
	if(!reghow.test(build_Number))
	{
		alert("楼宇数量格式不正确，请重新输入!");
		document.getElementById("build_Number").focus();
		return false;
	}
	}
	if(House_Number!="" && House_Number!=null){
	if(!reghow.test(House_Number))
	{
		alert("房屋数量格式不正确，请重新输入!");
		document.getElementById("House_Number").focus();
		return false;
	}
	}
	if(BuildArea!="" && BuildArea!=null){
	if(!regmj.test(BuildArea))
	{
		alert("建筑面积格式不正确，请重新输入!");
		document.getElementById("BuildArea").focus();
		return false;
	}
	}
	if(FloorArea!="" && FloorArea!=null){
	if(!regmj.test(FloorArea))
	{
		alert("占地面积格式不正确，请重新输入!");
		document.getElementById("FloorArea").focus();
		return false;
	}
	}
	if(GreenArea!="" && GreenArea!=null){
	if(!regmj.test(GreenArea))
	{
		alert("绿化面积格式不正确，请重新输入!");
		document.getElementById("GreenArea").focus();
		return false;
	}
	}
	if(VolumeRate!="" && VolumeRate!=null){
	if(!regmj.test(VolumeRate))
	{
		alert("容积率格式不正确，请重新输入!");
		document.getElementById("VolumeRate").focus();
		return false;
	}
	}
	if(remark!="" && remark!=null){
	if(!regstrSY.test(remark))
	{
		alert("备注格式不正确，请重新输入!");
		document.getElementById("remark").focus();
		return false;
	}
	}
	var Qstring="EsName="+EsName+"&EsHead="+EsHead+"&EsContact="+EsContact+"&EsPhone="+EsPhone+"&EsAddress="+EsAddress+"&build_Number="+build_Number+"&House_Number="+House_Number+"&BuildArea="+BuildArea+"&FloorArea="+FloorArea+"&GreenArea="+GreenArea+"&VolumeRate="+VolumeRate+"&remark="+remark;
	
	sendPOST(url,Qstring);
}
/*****查询小区信息******/
function select_TB_Village(divid,url)
{
	currentPos=divid;
	var esName = document.getElementById("esName").value;	//小区名称
	
	
	var Qstring="EsName="+esName;
	sendPOST(url,Qstring);
}


/*****修改小区信息******/
function TBVillage_update(es_id,i,url,returndivid,url2)
{
	OBJ=returndivid;
	URL2=url2;
	
	var esName = document.getElementById("EsName"+i).value;	//小区名称
	var EsName = trim(esName);
	var EsHead = document.getElementById("EsHead"+i).value;	//小区负责人
	var EsContact = document.getElementById("EsContact"+i).value;	//小区联系人
	var EsPhone = document.getElementById("EsPhone"+i).value;	//小区联系电话
	var EsAddress = document.getElementById("EsAddress"+i).value;	//小区地址
	var build_Number = document.getElementById("build_Number"+i).value;	//楼宇数量（幢）
	var House_Number = document.getElementById("House_Number"+i).value;	//房屋数量（户）
	var BuildArea = document.getElementById("BuildArea"+i).value;	//建筑面积（m2）
	var FloorArea = document.getElementById("FloorArea"+i).value;	//占地面积（m2）
	var GreenArea = document.getElementById("GreenArea"+i).value;	//绿化面积（m2）
	var VolumeRate = document.getElementById("VolumeRate"+i).value;	//容积率（%）
	var remark = document.getElementById("remark"+i).value;	//备注
	
	var regstrSY1= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\-\,\<\>\？\，\。]+$/;   
	var regmj = /^(\d+\.\d{1,4}|\d+)$/;
	
	if(EsName=="" || EsName==null)
	{
		alert("小区名称不能为空！");
		document.getElementById("EsName").focus();
		return false;
	}
	
	if(!regstrSY.test(EsName))
		{
			alert("小区名称格式不正确，请重新输入!");
			document.getElementById("EsName").focus();
			return false;
		}
	if(EsHead!="" && EsHead!=null){
	if(!regstrSY.test(EsHead))
	{
		alert("小区负责人格式不正确，请重新输入!");
		document.getElementById("EsHead").focus();
		return false;
	}
	}
	if(EsContact!="" && EsContact!=null){
	if(!regstrSY.test(EsContact))
	{
		alert("小区联系人格式不正确，请重新输入!");
		document.getElementById("EsContact").focus();
		return false;
	}
	}
	if(EsPhone!="" && EsPhone!=null){
	if(!regphone.test(EsPhone)&&!regtel.test(EsPhone))
	{
		alert("小区联系电话格式不正确，请重新输入!");
		document.getElementById("EsPhone").focus();
		return false;
	}
	}
	if(EsAddress!="" && EsAddress!=null){
	if(!regstrSY.test(EsAddress))
	{
		alert("小区地址格式不正确，请重新输入!");
		document.getElementById("EsAddress").focus();
		return false;
	}
	}
	if(build_Number!="" && build_Number!=null){
	if(!reghow.test(build_Number))
	{
		alert("楼宇数量格式不正确，请重新输入!");
		document.getElementById("build_Number").focus();
		return false;
	}
	}
	if(House_Number!="" && House_Number!=null){
	if(!reghow.test(House_Number))
	{
		alert("房屋数量格式不正确，请重新输入!");
		document.getElementById("House_Number").focus();
		return false;
	}
	}
	if(BuildArea!="" && BuildArea!=null){
	if(!regmj.test(BuildArea))
	{
		alert("建筑面积格式不正确，请重新输入!");
		document.getElementById("BuildArea").focus();
		return false;
	}
	}
	if(FloorArea!="" && FloorArea!=null){
	if(!regmj.test(FloorArea))
	{
		alert("占地面积格式不正确，请重新输入!");
		document.getElementById("FloorArea").focus();
		return false;
	}
	}
	if(GreenArea!="" && GreenArea!=null){
	if(!regmj.test(GreenArea))
	{
		alert("绿化面积格式不正确，请重新输入!");
		document.getElementById("GreenArea").focus();
		return false;
	}
	}
	if(VolumeRate!="" && VolumeRate!=null){
	if(!regmj.test(VolumeRate))
	{
		alert("容积率格式不正确，请重新输入!");
		document.getElementById("VolumeRate").focus();
		return false;
	}
	}
	if(remark!="" && remark!=null){
	if(!regstrSY.test(remark))
	{
		alert("备注格式不正确，请重新输入!");
		document.getElementById("remark").focus();
		return false;
	}
	}
	
	
	var Qstring="Es_id="+es_id+"&EsName="+EsName+"&EsHead="+EsHead+"&EsContact="+EsContact+"&EsPhone="+EsPhone+"&EsAddress="+EsAddress+"&build_Number="+build_Number+"&House_Number="+House_Number+"&BuildArea="+BuildArea+"&FloorArea="+FloorArea+"&GreenArea="+GreenArea+"&VolumeRate="+VolumeRate+"&remark="+remark;
	
	sendPOST(url,Qstring);
}
/*******删除小区信息***********/
function TBVillage_delete(es_id,i,url,returndivid,url2)
{
	if(confirm("您正在进行删除小区的操作，删除后将无法查询该小区下的信息，确定要删除吗？"))
	{

				
		OBJ=returndivid;
		//currentPos=returndivid;
		URL2=url2;
		
		
		var Qstring="Es_id="+es_id;
		
		sendPOST(url,Qstring);
	}
}
/*****查询楼宇信息******/
function select_TB_Build(divid,url)
{
	currentPos=divid;
	var es_id= document.getElementById("Es_id").value;	//小区主键
	var bu_id = document.getElementById("Bu_id").value;	//楼宇主键

	if(es_id==""){
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	
	var Qstring="Es_id="+es_id+"&Bu_id="+bu_id;
	sendPOST(url,Qstring);
}
/*修改楼宇信息*/
function Update_louyu(bu_id,url,url2,divid,closedivid)
{
	
	OBJ=divid;
	URL2=url2;
	DIV_ID=closedivid;
	
    var buName = document.getElementById("BuName").value;
    var BuName = trim(buName);
    var BuTurn = document.getElementById("BuTurn").value;
    var BuType = document.getElementById("BuType").value;
    var BuStru = document.getElementById("BuStru").value;
    var BuNumber = document.getElementById("BuNumber").value;
    var UnitNumber = document.getElementById("UnitNumber").value;
    var HouseNumber = document.getElementById("HouseNumber").value;
    var TotalArea = document.getElementById("TotalArea").value;
    var BuildArea = document.getElementById("BuildArea").value;
    var UseArea = document.getElementById("UseArea").value;
    var FinishDate = document.getElementById("FinishDate").value;
    var BuHead = document.getElementById("BuHead").value;
    var remark = document.getElementById("remark").value;	//备注
    var regstrSY1= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\-\,\<\>\？\，\。]+$/;
    var regstrSY2= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\,\<\>\？\，\。]+$/;
    var regstrSY3= /^[^\~\!@\$\%\^\&\*\(\)\_\+\|\,\<\>\？\，\。]+$/;
    var regmj = /^(\d+\.\d{1,4}|\d+)$/;
    
    if(BuName=="" || BuName==null)
	{
		alert("楼号不能为空！");
		document.getElementById("BuName").focus();
		return false;
	}
	
	if(!regstrSY3.test(BuName))
		{
			alert("楼号格式不正确，请重新输入!");
			document.getElementById("BuName").focus();
			return false;
		}
	if(BuNumber!="" && BuNumber!=null){
		if(!reghow.test(BuNumber))
		{
			alert("楼宇总层数格式不正确，请重新输入!");
			document.getElementById("BuNumber").focus();
			return false;
		}
		}
	if(UnitNumber!="" && UnitNumber!=null){
		if(!reghow.test(UnitNumber))
		{
			alert("单元数格式不正确，请重新输入!");
			document.getElementById("UnitNumber").focus();
			return false;
		}
		}
	if(HouseNumber!="" && HouseNumber!=null){
		if(!reghow.test(HouseNumber))
		{
			alert("房屋数量格式不正确，请重新输入!");
			document.getElementById("HouseNumber").focus();
			return false;
		}
		}
	if(TotalArea!="" && TotalArea!=null){
		if(!regmj.test(TotalArea))
		{
			alert("楼宇总面积格式不正确，请重新输入!");
			document.getElementById("TotalArea").focus();
			return false;
		}
		}
	if(BuildArea!="" && BuildArea!=null){
		if(!regmj.test(BuildArea))
		{
			alert("建筑面积格式不正确，请重新输入!");
			document.getElementById("BuildArea").focus();
			return false;
		}
		}
	if(UseArea!="" && UseArea!=null){
		if(!regmj.test(UseArea))
		{
			alert("使用面积格式不正确，请重新输入!");
			document.getElementById("UseArea").focus();
			return false;
		}
		}
	if(FinishDate!="" && FinishDate!=null){
		if(!regstrSY2.test(FinishDate))
		{
			alert("竣工日期格式不正确，请重新输入!");
			document.getElementById("FinishDate").focus();
			return false;
		}
		}
	if(BuHead!="" && BuHead!=null){
		if(!regstrSY.test(BuHead))
		{
			alert("负责人格式不正确，请重新输入!");
			document.getElementById("BuHead").focus();
			return false;
		}
		}
	if(remark!=""&&remark!=null){
		if(!regstrSY.test(remark))
		{
			alert("备注格式不正确，请重新输入!");
			document.getElementById("remark").focus();
			return false;
		}
	}
	
	var Qstring="Bu_id="+bu_id+"&BuName="+BuName+"&BuTurn="+BuTurn+"&BuType="+BuType+"&BuStru="+BuStru+"&BuNumber="+BuNumber
	+"&UnitNumber="+UnitNumber+"&HouseNumber="+HouseNumber+"&TotalArea="+TotalArea+"&BuildArea="+BuildArea+"&UseArea="+UseArea+"&FinishDate="+FinishDate
	+"&BuHead="+BuHead+"&remark="+remark;
	sendPOST(url,Qstring);
}


/*删除楼宇信息*/
function TBBuild_deletenew(bu_id,url,url2,divid)
{
	if(confirm("您正在执行删除操作，确定要删除吗？"))
	{	
		OBJ=divid;
		URL2=url2;
				
		var Qstring="Bu_id="+bu_id;
		
		sendPOST(url,Qstring);
	}
}
/*删除房屋信息*/
function TBBuild_delete(Eh_id,url,url2,divid)
{
	if(confirm("您正在执行删除操作，确定要删除吗？"))
	{	
		OBJ=divid;
		URL2=url2;
				
		var Qstring="Eh_id="+Eh_id;
		
		sendPOST(url,Qstring);
	}
}
/*添加楼宇信息*/
function TBBuild_add(divid,url,url2)
{
	OBJ="showmain";
	currentPos=divid;
	URL2=url2;
	
	var es_id =  document.getElementById("es_id").value;
	
	
	var Qianzhui = document.getElementById("qianzhui").value; 
	var qianzhui = trim(Qianzhui);
	var QsBuName = document.getElementById("qsBuName").value;
	var qsBuName = trim(QsBuName);
	var ZzBuName = document.getElementById("zzBuName").value;
	var zzBuName = trim(ZzBuName);
	var Houzhui = document.getElementById("houzhui").value; 
	var houzhui = trim(Houzhui);
	var  m =  parseInt(qsBuName);
	var  n =  parseInt(zzBuName);
	
    var BuTurn = document.getElementById("BuTurn").value;
    var BuType = document.getElementById("BuType").value;
    var BuStru = document.getElementById("BuStru").value;
    var BuNumber = document.getElementById("BuNumber").value;
    var UnitNumber = document.getElementById("UnitNumber").value;
    var HouseNumber = document.getElementById("HouseNumber").value;
    var TotalArea = document.getElementById("TotalArea").value;
    var BuildArea = document.getElementById("BuildArea").value;
    var UseArea = document.getElementById("UseArea").value;
    var FinishDate = document.getElementById("FinishDate").value;
    var BuHead = document.getElementById("BuHead").value;  //楼宇负责人
    var remark = document.getElementById("remark").value;	//备注
    var regstrSY1= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\-\,\<\>\？\，\。]+$/;
    var regstrSY2= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\,\<\>\？\，\。]+$/;
	var regstrSY3= /^[^\~\!@\$\%\^\&\*\(\)\_\+\|\-\,\.\<\>\？\，\。]+$/;         //验证全部不是特殊字符的正则表达式
	var regmj = /^(\d+\.\d{1,4}|\d+)$/;
    
    
    if(es_id=="" || es_id==null)
	{
		alert("请先选择小区！");
		document.getElementById("es_id").focus();
		return false;
	}
    
 
    if(qianzhui=="" || qianzhui==null)
	{
		alert("楼号前缀不能为空！");
		document.getElementById("qianzhui").focus();
		return false;
	}
	
	if(!regstrSY.test(qianzhui))
		{
			alert("楼号前缀格式不正确，请勿输入特殊字符!");
			document.getElementById("qianzhui").focus();
			return false;
		}
    
    if(qsBuName=="" || qsBuName==null)
	{
		alert("起始楼号不能为空！");
		document.getElementById("qsBuName").focus();
		return false;
	}
	
	if(!reghow.test(qsBuName))
		{
			alert("起始楼号格式不正确，请输入数字!");
			document.getElementById("qsBuName").focus();
			return false;
		}
	if(qsBuName<=0)
	{
		alert("起始楼号应大于0!");
		document.getElementById("qsBuName").focus();
		return false;
	}

	if(zzBuName!="" && zzBuName!=null){
	if(!reghow.test(zzBuName))
	{
		alert("终止楼号格式不正确，请输入数字!");
		document.getElementById("zzBuName").focus();
		return false;
	}else if(n<=m){
		alert("终止楼号应大于起始楼号!");
		document.getElementById("zzBuName").focus();
		return false;
	}
	}
	
	 if(houzhui=="" || houzhui==null)
		{
			alert("楼号后缀不能为空！");
			document.getElementById("houzhui").focus();
			return false;
		}
		
		if(!regstrSY3.test(houzhui))
			{
				alert("楼号后缀格式不正确，请勿输入特殊字符!");
				document.getElementById("houzhui").focus();
				return false;
			}
	
	
	
	if(BuNumber!="" && BuNumber!=null){
		if(!reghow.test(BuNumber))
		{
			alert("楼宇总层数格式不正确，请重新输入!");
			document.getElementById("BuNumber").focus();
			return false;
		}
		}
	
	if(UnitNumber!="" && UnitNumber!=null){
		if(!reghow.test(UnitNumber))
		{
			alert("单元数格式不正确，请重新输入!");
			document.getElementById("UnitNumber").focus();
			return false;
		}
		}
	if(HouseNumber!="" && HouseNumber!=null){
		if(!reghow.test(HouseNumber))
		{
			alert("房屋数量格式不正确，请重新输入!");
			document.getElementById("HouseNumber").focus();
			return false;
		}
		}
	if(TotalArea!="" && TotalArea!=null){
		if(!regmj.test(TotalArea))
		{
			alert("楼宇总面积格式不正确，请重新输入!");
			document.getElementById("TotalArea").focus();
			return false;
		}
		}
	if(BuildArea!="" && BuildArea!=null){
		if(!regmj.test(BuildArea))
		{
			alert("建筑面积格式不正确，请重新输入!");
			document.getElementById("BuildArea").focus();
			return false;
		}
		}
	if(UseArea!="" && UseArea!=null){
		if(!regmj.test(UseArea))
		{
			alert("使用面积格式不正确，请重新输入!");
			document.getElementById("UseArea").focus();
			return false;
		}
		}
	if(FinishDate!="" && FinishDate!=null){
		if(!regstrSY2.test(FinishDate))
		{
			alert("竣工日期格式不正确，请重新输入!");
			document.getElementById("FinishDate").focus();
			return false;
		}
		}
	/*if(BuHead!="" && BuHead!=null){
		if(!regstrSY.test(BuHead))
		{
			alert("负责人格式不正确，请重新输入!");
			document.getElementById("BuHead").focus();
			return false;
		}
		}*/
	if(remark!="" && remark!=null){
		if(!regstrSY.test(remark))
		{
			alert("备注格式不正确，请重新输入!");
			document.getElementById("remark").focus();
			return false;
		}
		}
	
	var Qstring="es_id="+es_id+"&qsBuName="+qsBuName+"&zzBuName="+zzBuName+"&BuTurn="+BuTurn+"&BuType="+BuType+"&BuStru="+BuStru+"&BuNumber="+BuNumber
	+"&UnitNumber="+UnitNumber+"&HouseNumber="+HouseNumber+"&TotalArea="+TotalArea+"&BuildArea="+BuildArea+"&UseArea="+UseArea+"&FinishDate="+FinishDate
	+"&BuHead="+BuHead+"&remark="+remark+"&qianzhui="+qianzhui+"&houzhui="+houzhui;
	sendPOST(url,Qstring);
}

/*****查询房屋信息******/
function select_TB_House(divid,url)
{
	currentPos=divid;
	var es_id= document.getElementById("Es_id").value;	//小区主键
	var bu_id = document.getElementById("Bu_id").value;	//楼宇主键
	var Un_id = document.getElementById("Un_id").value;	 
	var EhNumber = document.getElementById("EhNumber").value;
	var OwnerName = document.getElementById("OwnerName").value;

	if(es_id==""){
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	
	var Qstring="Es_id="+es_id+"&Bu_id="+bu_id+"&EhNumber="+EhNumber+"&OwnerName="+OwnerName+"&Un_id="+Un_id;
	sendPOST(url,Qstring);
}

/*修改房屋信息*/
function Update_fangwu(eh_id,url,url2,divid,closedivid)
{
	
	OBJ=divid;
	URL2=url2;
	DIV_ID=closedivid;
	
    var EhNumber = document.getElementById("ehNumber").value;
    var ehNumber = trim(EhNumber);
    var EhName = document.getElementById("EhName").value;
    var EhNature = document.getElementById("EhNature").value;
    var EhStru = document.getElementById("EhStru").value;
    var EhType = document.getElementById("EhType").value;
    var EhTurn = document.getElementById("EhTurn").value;
    var EhStatus = document.getElementById("EhStatus").value;
    var PropertyRight = document.getElementById("PropertyRight").value;
    var HousingUse = document.getElementById("HousingUse").value;
    var OftenNumber = document.getElementById("OftenNumber").value;
    var OwnerName1 = document.getElementById("OwnerName1").value;
    var OwnerPhone = document.getElementById("OwnerPhone").value;
    var BuildArea = document.getElementById("BuildArea1").value;	
    var UseArea = document.getElementById("UseArea").value;	
    var HeatingArea = document.getElementById("HeatingArea").value;	
    var CarNum = document.getElementById("CarNum").value;	
    var remark = document.getElementById("remark").value;	
    var handIs = document.getElementById("handIs").value;	
    var storer = document.getElementById("storer").value;
    var regstrSY1= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\-\,\<\>\？\，\。]+$/;
    var regstrSY2= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\,\<\>\？\，\。]+$/;
    var regmj = /^(\d+\.\d{1,4}|\d+)$/;
    
    if(ehNumber=="" && ehNumber==null)
	{
		alert("房屋编号不能为空！");
		document.getElementById("EhNumber").focus();
		return false;
	}
	
	/*if(!reghow.test(ehNumber))
		{
			alert("房屋编号格式不正确，请输入数字!");
			document.getElementById("EhNumber").focus();
			return false;
		}
	*/
	 
	
	    if(EhName!=""){
		if(!regstrSY2.test(EhName))
			{
				alert("房屋名称格式不正确，请重新输入!");
				document.getElementById("EhName").focus();
				return false;
			}
	    }
	    if(OftenNumber!=""){
		if(!regstrSY.test(OftenNumber))
		{
			alert("常住人口数格式不正确，请重新输入!");
			document.getElementById("OftenNumber").focus();
			return false;
		}
	    }
		if(OwnerName1!=""){
		if(!regstrSY.test(OwnerName1))
		{
			alert("业主姓名格式不正确，请重新输入!");
			document.getElementById("OwnerName1").focus();
			return false;
		}
        }         
		
		if(OwnerPhone!="" && OwnerPhone!=null){
			if(!regphone.test(OwnerPhone)&&!regtel.test(OwnerPhone))
			{
				alert("业主电话电话格式不正确，请重新输入!");
				document.getElementById("OwnerPhone").focus();
				return false;
			}
			}
		
	    if(BuildArea=="" || BuildArea==null)
		{
			alert("建筑面积不能为空！");
			document.getElementById("BuildArea1").focus();
			return false;
		}
		
			if(!regmj.test(BuildArea))
			{
				alert("建筑面积格式不正确，请重新输入!");
				document.getElementById("BuildArea1").focus();
				return false;
			}
			
		    if(UseArea=="" || UseArea==null)
			{
				alert("使用面积不能为空！");
				document.getElementById("UseArea").focus();
				return false;
			}
			
			if(!regmj.test(UseArea))
				{
					alert("使用面积格式不正确，请重新输入!");
					document.getElementById("UseArea").focus();
					return false;
				}
				
			 if(HeatingArea=="" || HeatingArea==null)
				{
					alert("供暖面积不能为空！");
					document.getElementById("HeatingArea").focus();
					return false;
				}
					
			if(!regmj.test(HeatingArea))
				{
					alert("供暖面积格式不正确，请重新输入!");
					document.getElementById("HeatingArea").focus();
					return false;
				}
			if(CarNum!=""){
			if(!reghow.test(CarNum))
			{
				alert("车辆数格式不正确，请重新输入!");
				document.getElementById("CarNum").focus();
				return false;
			}
			}
			
			if(storer=="" || storer==null)
			{
				if(storer == "0"){
					alert("楼层数不能为0！");
					document.getElementById("storer").focus();
					return false;
				}
				alert("楼层数不能为空！");
				document.getElementById("storer").focus();
				return false;
			}
						
	
	var Qstring="Eh_id="+eh_id+"&EhNumber="+ehNumber+"&EhName="+EhName+"&EhNature="+EhNature+"&EhStru="+EhStru+"&EhType="+EhType
	+"&EhTurn="+EhTurn+"&EhStatus="+EhStatus+"&PropertyRight="+PropertyRight+"&HousingUse="+HousingUse+"&OftenNumber="+OftenNumber+"&OwnerName="+OwnerName1
	+"&OwnerPhone="+OwnerPhone+"&BuildArea="+BuildArea+"&UseArea="+UseArea+"&HeatingArea="+HeatingArea+"&CarNum="+CarNum+"&remark="+remark+"&handIs="+handIs+
	"&storer="+storer;
	sendPOST(url,Qstring);
	
}

/*删除楼宇信息*/
function TBHouse_delete(eh_id,url,url2,divid)
{
	if(confirm("您正在执行删除操作，确定要删除吗？"))
	{	
		OBJ=divid;
		URL2=url2;
				
		var Qstring="Eh_id="+eh_id;
		
		sendPOST(url,Qstring);
	}
}
/*添加房屋信息*/
function TBHouse_add(divid,url,url2)
{
	OBJ="showmain";
	currentPos=divid;
	URL2=url2;
	var es_id = document.getElementById("es_id").value;
	var bu_id = document.getElementById("bu_id").value;
	var Un_id1 = document.getElementById("Un_id1").value;
	var EhNumber = document.getElementById("ehNumber").value;
	var ehNumber = trim(EhNumber);
	var EhName = document.getElementById("EhName").value;
	var EhNature = document.getElementById("EhNature").value;
	var EhStru = document.getElementById("EhStru").value;
	var EhType = document.getElementById("EhType").value;
	var EhTurn = document.getElementById("EhTurn").value;
	var EhStatus = document.getElementById("EhStatus").value;
	var PropertyRight = document.getElementById("PropertyRight").value;
	var HousingUse = document.getElementById("HousingUse").value;
	var oftenNumber = (document.getElementById("OftenNumber").value);
	var OftenNumber = trim(oftenNumber);
	var ownerName = document.getElementById("ownerName").value;
	var OwnerPhone = document.getElementById("OwnerPhone").value;
	var BuildArea = document.getElementById("BuildArea").value;	
	var UseArea = document.getElementById("UseArea").value;	
	var HeatingArea = document.getElementById("HeatingArea").value;	
	var CarNum = document.getElementById("CarNum").value;	
	var remark = document.getElementById("remark").value;	
	var storer = document.getElementById("storer").value;
	var handIs = document.getElementById("handIs").value;
	
	var regstrSY1= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\-\,\<\>\？\，\。]+$/;
	var regstrSY2= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\,\<\>\？\，\。]+$/;
	var regmj = /^(\d+\.\d{1,4}|\d+)$/;
	
	 if(es_id=="" || es_id==null)
		{
			alert("请选择小区！");
			document.getElementById("es_id").focus();
			return false;
		}
	    
	 if(bu_id=="" || bu_id==null)
		{
			alert("请选择楼号！");
			document.getElementById("bu_id").focus();
			return false;
		}
	    if(ehNumber=="" || ehNumber==null)
		{
			alert("房屋编号不能为空！");
			document.getElementById("EhNumber").focus();
			return false;
		}
	    
	    if(EhType == "" || EhType == null){
	    	alert("房屋类型不能为空！");
			document.getElementById("EhType").focus();
			return false;
	    }
		
		/*if(!reghow.test(ehNumber))
			{
				alert("房屋编号格式不正确，请输入数字!");
				document.getElementById("EhNumber").focus();
				return false;
			}
		*/
		
		    if(EhName!="" && EhName!=null){
			if(!regstrSY2.test(EhName))
				{
					alert("房屋名称格式不正确，请重新输入!");
					document.getElementById("EhName").focus();
					return false;
				}
		    }
			
			if(OftenNumber!="" && OftenNumber!=null){
			if(!reghow.test(OftenNumber))
			{
				alert("常住人口数格式不正确，请重新输入!");
				document.getElementById("OftenNumber").focus();
				return false;
			}
			}
			if(ownerName!=""){
			if(!regstrSY.test(ownerName))
			{
				alert("业主姓名格式不正确，请重新输入!");
				document.getElementById("ownerName").focus();
				return false;
			}
	        }         
			
			if(OwnerPhone!="" && OwnerPhone!=null){
				if(!regphone.test(OwnerPhone)&&!regtel.test(OwnerPhone))
				{
					alert("业主电话电话格式不正确，请重新输入!");
					document.getElementById("OwnerPhone").focus();
					return false;
				}
				}
			if(storer=="" || storer==null)
			{
				alert("楼层数不能为空！");
				document.getElementById("storer").focus();
				return false;
			}
			
		    if(BuildArea=="" || BuildArea==null)
			{
				alert("建筑面积不能为空！");
				document.getElementById("BuildArea").focus();
				return false;
			}
			
				if(!regmj.test(BuildArea))
				{
					alert("建筑面积格式不正确，请重新输入!");
					document.getElementById("BuildArea").focus();
					return false;
				}
				
			    if(UseArea=="" || UseArea==null)
				{
					alert("使用面积不能为空！");
					document.getElementById("UseArea").focus();
					return false;
				}
				
				if(!regmj.test(UseArea))
					{
						alert("使用面积格式不正确，请重新输入!");
						document.getElementById("UseArea").focus();
						return false;
					}
					
				 if(HeatingArea=="" || HeatingArea==null)
					{
						alert("供暖面积不能为空！");
						document.getElementById("HeatingArea").focus();
						return false;
					}
						
				if(!regmj.test(HeatingArea))
					{
						alert("供暖面积格式不正确，请重新输入!");
						document.getElementById("HeatingArea").focus();
						return false;
					}
				if(CarNum!=""){
				if(!reghow.test(CarNum))
				{
					alert("车位数格式不正确，请重新输入!");
					document.getElementById("CarNum").focus();
					return false;
				}
				if(remark=="" || remark==null)
				{
					alert("车位编号不能为空！");
					document.getElementById("remark").focus();
					return false;
				}
				}
				
				
				
				/*if(remark!=""){
				if(!regstrSY.test(remark))
				{
					alert("备注格式不正确，请重新输入!");
					document.getElementById("remark").focus();
					return false;
				}
				}	
				*/
				var Qstring="Un_id1="+Un_id1+"&Es_id="+es_id+"&Bu_id="+bu_id+"&EhNumber="+ehNumber+"&EhName="+EhName+"&EhNature="+EhNature+"&EhStru="+EhStru+"&EhType="+EhType
				+"&EhTurn="+EhTurn+"&EhStatus="+EhStatus+"&PropertyRight="+PropertyRight+"&HousingUse="+HousingUse+"&OftenNumber="+OftenNumber+"&ownerName="+ownerName
				+"&OwnerPhone="+OwnerPhone+"&BuildArea="+BuildArea+"&UseArea="+UseArea+"&HeatingArea="+HeatingArea+"&CarNum="+CarNum+"&remark="+remark+"&handIs="+handIs+"&storer="+storer;
				sendPOST(url,Qstring);
		}
/*功能操作后，需要刷新右边整体页面时调用的菜单方法*/
function menu_shua(url)
{	
	currentPos="showmain";	
	
	send_request(url);
}

function add_order_rgsf(url,url2,returndivid)
{	
	URL2=url2;
	OBJ=returndivid;
	
	var Es_id= document.getElementById("Es_id").value;	//小区主键
	var Bu_id = document.getElementById("Bu_id").value;	//楼宇主键
	var EhNumber = document.getElementById("EhNumber").value;
	var payItem = document.getElementById("payItem").value;
	var payType=document.getElementById("payType").value;
	if(Es_id==""){
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	/*if(Bu_id==""){
		alert("请选择楼宇!");
		document.getElementById("Bu_id").focus();
		return false;
	}*/
	var Un_id =""; 
	if(document.getElementById("Un_id")!=null){
		Un_id=document.getElementById("Un_id").value; //单元主键
	}
	if(EhNumber==""){
		alert("请输入房屋编号!");
		document.getElementById("EhNumber").focus();
		return false;
	}
	if(payType==""){
		alert("请选择缴费方式!");
		document.getElementById("payType").focus();
		return false;
	}
	if(payItem==""){
		alert("请选择收费项!");
		document.getElementById("payItem").focus();
		return false;
	}
	var shuju = document.getElementById("shuju").value;//限购设置
	var total =0;
	var totalqt="";
	if(shuju==""){
		total = document.getElementById("total").value;
	}else{
		var limited = document.getElementById("limited").value;
		if(limited==""){
			total = document.getElementById("total").value;
		}else{
			total = getradio("total");
			totalqt = document.getElementById("totalqt").value;
		}
		
	}
	if(totalqt==""){
		if(total=="" ||total=="0"||total=="0.0"||total=="0.00"){
			alert("缴费金额不能为空!");
			return false;
		}
		if(!isPrice.test(total))
		{
			alert("缴费金额格式错误，最多保留两位小数！");
			return false;		
		}	
	}
	
	if(totalqt!=""){
		if(!isPrice.test(totalqt))
		{
			alert("其他金额格式错误");
			return false;		
		}else{
			total=totalqt;
		}	
	}
	if(document.getElementById("HouseStandard")!=null){
		var fwmj=document.getElementById("fwmj").value; 
		var HouseStandard=document.getElementById("HouseStandard").value; //房屋面积标准
		var BelowStandard=document.getElementById("BelowStandard").value; //低于标准
		var AboveStandard=document.getElementById("AboveStandard").value; //高于标准
		if(HouseStandard!="0"){  //设置房屋标准时进行判断
			if(parseInt(fwmj)<=parseInt(HouseStandard)){  //房屋面积小于等于标准
				if(parseInt(total)>parseInt(BelowStandard)){  //如果金额大于最高限制
					alert("抱歉，金额不能超过低于标准的最高限额!");
					return false;
				}
			}else{  //房屋面积大于标准
				if(parseInt(total)>parseInt(AboveStandard)){  //如果金额大于最高限制
					alert("抱歉，金额不能超过高于标准的最高限额!");
					return false;
				}
			}
		}
	}
	var lyid=document.getElementById("lyid").value; //楼宇信息
	if(lyid==""){
		alert("输入的房屋编号信息有误！");
		return false;	
	}
	var isYjLimited = document.getElementById("isYjLimited").value;//是否开启预缴
	if(isYjLimited=="0"||isYjLimited=="null"){
		alert("抱歉，该小区暂未开放预缴收费!");
		return false;
	}else{
		var QString = "Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id+"&EhNumber="+EhNumber+"&payItem="+payItem+"&total="+total+"&payType="+payType+"&lyid="+lyid;
		sendPOST(url,QString);
	}
	
}
/*用户重置密码*/
function resetUserPassword(url,eh_id,password,url2)
{  
	if(confirm("重置密码将把密码重置为111111，确定要重置密码？"))
	{
		//returnURL=url2;
		
		URL2=url2;
		
		var Qstring="eh_id="+eh_id+"&password="+password;

		sendPOST(url,Qstring);
	}
	else
	{
		return false;
	}
}
/**************物业后台 交易管理--报表管理  金鑫 stsrt*******************/
function select_baobiao(divid,url,ts_id){
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var Bu_id=document.getElementById("Bu_id").value;
	var itemName=document.getElementById("itemName").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	var payType=document.getElementById("payType").value;
	if(Es_id==""){
		 alert("请选择小区");
	     document.getElementById("Es_id").focus();
	     return false;
	}
	if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="ts_id="+ts_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&itemName="+itemName+"&timesk="+timesk+"&timesj="+timesj+"&payType="+payType;
	sendPOST(url,QString);
}
//按条件模糊查询订单(固额查询)
function select_gue_orders(divid,url,ts_id){
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var Bu_id=document.getElementById("Bu_id").value;
	var Un_id=document.getElementById("Un_id").value;
	var EhNumber=document.getElementById("EhNumber").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	var payStatus=document.getElementById("payStatus").value;
	var payType=document.getElementById("payType").value;
	var OwnerName=document.getElementById("OwnerName").value;
	var itemName=document.getElementById("itemName").value;
	var sfy="";
	if(document.getElementById("sfy")!=null){
		 sfy=document.getElementById("sfy").value;
	}
	if(Es_id==""){
		 alert("请选择小区");
	     document.getElementById("Es_id").focus();
	     return false;
	}
	
	if(!CompareDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="ts_id="+ts_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id+"&EhNumber="+EhNumber+"&timesk="+timesk+"&timesj="+timesj+"&payStatus="+payStatus+"&payType="+payType+"&OwnerName="+OwnerName+"&itemName="+itemName+"&sfy="+sfy;
	sendPOST(url,QString);
}
//按条件模糊查询订单(固额查询)
function select_gue_orders2(divid,url,ts_id){
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var Bu_id=document.getElementById("Bu_id").value;
	var Un_id=document.getElementById("Un_id").value;
	var EhNumber=document.getElementById("EhNumber").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	var payType=document.getElementById("payType").value;
	var OwnerName=document.getElementById("OwnerName").value;
	var payItem=document.getElementById("payItem").value;
	var payStatus=document.getElementById("payStatus").value;
	var sfy="";
	if(document.getElementById("sfy")!=null){
		sfy=document.getElementById("sfy").value;
	}
	if(Es_id==""){
		 alert("请选择小区");
	     document.getElementById("Es_id").focus();
	     return false;
	}
	if(!CompareDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="ts_id="+ts_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id+"&EhNumber="+EhNumber+"&payType="+payType+"&OwnerName="+OwnerName+"&payItem="+payItem+"&payStatus="+payStatus+"&sfy="+sfy+"&timesk="+timesk+"&timesj="+timesj;
	//alert(QString);
	sendPOST(url,QString);
}
/**************物业后台 交易管理--报表管理  金鑫 end  *******************/
/*********************物业后台固额订单批量导入 start****************************/
/*固额订单批量导入验证*/
function TB_gu_pl()
{
	var Gue_file = document.getElementById("Gue_file").value;
	var xiaoquid = document.getElementById("xiaoquid").value;
	//var louid = document.getElementById("louid").value;
	var cost_startTime=trim(document.getElementById("cost_startTime").value);
	var cost_endTime=document.getElementById("cost_endTime").value;
	
	if(Gue_file=="") {
		alert("请选择要上传的文件！");
		document.getElementById("Gue_file").focus();
		return false;
	} 
	if(xiaoquid==""){
		alert("请选择小区信息！");
		document.getElementById("xiaoquid").focus();
		return false;
	}
	if(cost_startTime=="")
	{
		alert("费用起止日期的开始时间不能为空！");
		document.getElementById("cost_startTime").focus();
		return false;
	}
	if(cost_endTime=="")
	{
		alert("费用起止日期的结束时间不能为空！");
		document.getElementById("cost_endTime").focus();
		return false;
	}
	if(!checkDate(cost_startTime, cost_endTime)){
		alert('费用起止日期的起始时间不能大于费用起止日期的结束时间！');
		return false;
	}

	parent.show_Loading_div();
}

/*固额订单模板下载*/
function gue_createReport_sev(wulifilepath,filename,url,anid){
	BUTTONID=anid;
	var xiaoquid = document.getElementById("xiaoquid").value;
	var QString="wulifilepath="+wulifilepath+"&filename="+filename+"&xiaoquid="+xiaoquid;
	sendPOST(url,QString);
}

/*********************物业后台固额订单批量导入 end****************************/
//人工收费——固额
function update_orders_rgsf_gue(url,url2,ts_id,returndivid,i){
	URL2=url2;
	OBJ=returndivid;
	var Eo_id=document.getElementById("Eo_id"+i+"").value;
	var total=document.getElementById("total"+i+"").value;
	var total_znj="0";
	var payType="1";
	var total_sj = total;
//	var total_sj = prompt("请输入实缴金额");//手动输入实缴金额
//	if(total_sj=="0.00"){
//		alert("请手动输入实缴金额！");
//		return false;
//	}
//	if(parseFloat(total_sj)<parseFloat(total)){
//		alert("实缴金额必须大于或等于应缴金额！！！");
//		return false;
//	}
	
	if(confirm("确定已现金支付吗？")) {
		var QString="ts_id="+ts_id+"&Eo_id="+Eo_id+"&total="+total+"&total_znj="+total_znj+"&total_sj="+total_sj+"&payType="+payType;
		sendPOST(url,QString);
	} else {
		return false;
	}
}
//查询是否是电费
function select_jine(th,divid,url,ts_id){
	currentPos=divid;
	var Ei_id=th.value;
	var QString="Ei_id="+Ei_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
}


//添加房屋联动查询楼宇信息
function select_louyu1(th,divid,url,ts_id){
	currentPos=divid;
	var es_id=th.value;
	var QString="Es_id="+es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
	getEhType(es_id,ts_id);
}

//分操作员不能新建小区
function  cant_xinjian(){
	window.alert("二级操作员不能新建小区！");
	return;
}
//分操作员不能新建机构
function addorg1(){
	window.alert("二级操作员不能新建机构！");
	return;
}
//分操作员不能新建操作员
function addczy1(){
	window.alert("二级操作员不能新建操作员！");
	return;
}
/**************物业后台优化  *******************/
//查询楼宇
function select_ly(th,divid,url,dividdy,ts_id){
	currentPos=divid;
	var Es_id=th.value;
	var QString="Es_id="+Es_id+"&dividdy="+dividdy+"&ts_id="+ts_id;
	sendPOST(url,QString);
	document.getElementById("Bu_id").value = "";
	document.getElementById("Un_id").value = "";
	document.getElementById("Bu_id").disabled = true;
	document.getElementById("Un_id").disabled = true;
}
//查询单元
function select_danyuan(th,divid,url,ts_id,Es_id){
	currentPos=divid;
	var Bu_id=th.value;
	var QString="Bu_id="+Bu_id+"&Es_id="+Es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
	document.getElementById("Un_id").value = "";
	document.getElementById("Un_id").disabled = true;
	//select_EhNature('dy2',url1,ts_id,Es_id); 
	
}
//查询单元所有信息
function select_Unit(divid,url)
{
	currentPos=divid;
	var es_id= document.getElementById("Es_id").value;	//小区主键
	var bu_id = document.getElementById("Bu_id").value;	//楼宇主键
	var Un_id = document.getElementById("Un_id").value;	//单元主键
	if(es_id==""){
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	if(bu_id==""){
		alert("请选择楼宇!");
		document.getElementById("Bu_id").focus();
		return false;
	}
	var Qstring="Es_id="+es_id+"&Bu_id="+bu_id+"&Un_id="+Un_id;
	sendPOST(url,Qstring);
}
//删除单元号
function TB_Unit_delete(Un_id,i,url,returndivid,url2)
{
	if(confirm("您正在进行删除单元的操作，删除后将无法查询该单元下的信息，确定要删除吗？"))
	{
		OBJ=returndivid;
		//currentPos=returndivid;
		URL2=url2;
		var Qstring="Un_id="+Un_id;
		
		sendPOST(url,Qstring);
	}
}
//修改单元信息
function TB_Unit_update(Un_id,i,url,returndivid,url2)
{
	OBJ=returndivid;
	URL2=url2;
	
	var UnName = trim(document.getElementById("UnName"+i).value);	//小区名称
	var Remark = document.getElementById("Remark"+i).value;	//备注
	var regstrSY= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\-\,\<\>\？\，\。]+$/;   
	
	if(UnName=="" || UnName==null)
	{
		alert("单元号不能为空！");
		document.getElementById("UnName").focus();
		return false;
	}
	/*if(!regstrSY.test(UnName))
		{
			alert("单元号格式不正确，请重新输入!");
			document.getElementById("UnName").focus();
			return false;
		}
*/
	if(Remark!="" && Remark!=null){
	if(!regstrSY.test(Remark))
	{
		alert("备注格式不正确，请重新输入!");
		document.getElementById("Remark").focus();
		return false;
	 }
	}
	var Qstring="Un_id="+Un_id+"&UnName="+UnName+"&Remark="+Remark;
	sendPOST(url,Qstring);
}
/*添加单元信息*/
function TBUnit_add(divid,url,url2)
{
	OBJ="showmain";
	currentPos=divid;
	URL2=url2;
	var es_id =  document.getElementById("es_id3").value;
	var bu_id=   document.getElementById("Bu_id3").value;
	var Qianzhui = document.getElementById("qianzhui").value; 
	var qianzhui = trim(Qianzhui);
	var QsBuName = document.getElementById("qsBuName").value;
	var qsBuName = trim(QsBuName);
	var ZzBuName = document.getElementById("zzBuName").value;
	var zzBuName = trim(ZzBuName);
	var Houzhui = document.getElementById("houzhui").value; 
	var houzhui = trim(Houzhui);
	var  m =  parseInt(qsBuName);
	var  n =  parseInt(zzBuName);
    var remark = document.getElementById("remark").value;	//备注
    var regstrSY= /^[^\~\!@\#\$\%\^\&\*\(\)\_\+\|\-\,\<\>\？\，\。]+$/;   
	var regstrSY3= /^[^\~\!@\$\%\^\&\*\(\)\_\+\|\-\,\.\<\>\？\，\。]+$/;//验证全部不是特殊字符的正则表达式
    if(es_id=="" || es_id==null)
	{
		alert("请先选择小区！");
		document.getElementById("es_id3").focus();
		return false;
	}
    if(bu_id=="" || bu_id==null)
	{
		alert("请先选择楼宇！");
		document.getElementById("Bu_id3").focus();
		return false;
	}
    if(qianzhui=="" || qianzhui==null)
	{
		alert("单元号前缀不能为空！");
		document.getElementById("qianzhui").focus();
		return false;
	}
	if(!regstrSY.test(qianzhui))
		{
			alert("单元号前缀格式不正确，请勿输入特殊字符!");
			document.getElementById("qianzhui").focus();
			return false;
		}
    
    if(qsBuName=="" || qsBuName==null)
	{
		alert("起始单元号不能为空！");
		document.getElementById("qsBuName").focus();
		return false;
	}
	
	if(!reghow.test(qsBuName))
		{
			alert("起始单元号格式不正确，请输入数字!");
			document.getElementById("qsBuName").focus();
			return false;
		}
	if(qsBuName<=0)
	{
		alert("起始单元号应大于0!");
		document.getElementById("qsBuName").focus();
		return false;
	}

	if(zzBuName!="" && zzBuName!=null){
	if(!reghow.test(zzBuName))
	{
		alert("终止单元号格式不正确，请输入数字!");
		document.getElementById("zzBuName").focus();
		return false;
	}else if(n<=m){
		alert("终止单元号应大于起始单元号!");
		document.getElementById("zzBuName").focus();
		return false;
	}
	}
	 if(houzhui=="" || houzhui==null)
		{
			alert("单元号后缀不能为空！");
			document.getElementById("houzhui").focus();
			return false;
		}
		
		if(!regstrSY3.test(houzhui))
			{
				alert("单元号后缀格式不正确，请勿输入特殊字符!");
				document.getElementById("houzhui").focus();
				return false;
			}
	if(remark!="" && remark!=null){
		if(!regstrSY.test(remark))
		{
			alert("备注格式不正确，请重新输入!");
			document.getElementById("remark").focus();
			return false;
		}
		}
	
	var Qstring="es_id="+es_id+"&bu_id="+bu_id+"&qsBuName="+qsBuName+"&zzBuName="+zzBuName+"&remark="+remark+"&qianzhui="+qianzhui+"&houzhui="+houzhui;
	sendPOST(url,Qstring);
}
//添加单元联动查询楼宇信息
function select_louyu3(th,divid,url,ts_id){
	currentPos=divid;
	var es_id=th.value;
	var QString="Es_id="+es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
}
/*修改电梯缴费项目*/
function TBDinaItem_update(status,Ct_id,Ei_id,sfname,i,url,returndivid,url2,qjsize)
{
	OBJ=returndivid;
	URL2=url2;
	
	var name=sfname;
	var price=trim(document.getElementById("price"+i).value);
	var remark="";//trim(document.getElementById("remark"+i).value);
	var shoufeifs=trim(Rep_str(document.getElementById("shoufeifs"+i).value));
	var EhType=trim(document.getElementById("EhType"+i).value);
	var znjbl=trim(document.getElementById("znjbl"+i).value);
	var znjday=trim(document.getElementById("znjday"+i).value);
	var es_id=trim(document.getElementById("es_id"+i).value);
	
	if(EhType==""){
		alert("请选择房屋类型");
		document.getElementById("EhType"+i).focus();
		return false;
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday"+i).focus();
			return false;
		}
	}
	if(znjbl!=""){
		if(!regexZX.test(znjbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("znjbl"+i).focus();
			return false;
		}
	}
	if((znjday=="" && znjbl!="") || (znjday!="" && znjbl=="")){
		alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
		return false;	
   }
	
	if(price!=""){
		if(!isPrice.test(price))
		{
			alert("单价（元）金额格式错误，最多保留两位小数！");
			document.getElementById("price"+i).focus();
			return false;		
		}
	}
	var Se_id1="",Se_id2="",Se_id3="",Se_id4="",Se_id5="",Se_id6="";
	if(qjsize=="6"){
		Se_id1=document.getElementById("Se_id1"+i).value;
		Se_id2=document.getElementById("Se_id2"+i).value;
		Se_id3=document.getElementById("Se_id3"+i).value;
		Se_id4=document.getElementById("Se_id4"+i).value;
		Se_id5=document.getElementById("Se_id5"+i).value;
		Se_id6=document.getElementById("Se_id6"+i).value;
	}
	if(qjsize=="5"){
		Se_id1=document.getElementById("Se_id1"+i).value;
		Se_id2=document.getElementById("Se_id2"+i).value;
		Se_id3=document.getElementById("Se_id3"+i).value;
		Se_id4=document.getElementById("Se_id4"+i).value;
		Se_id5=document.getElementById("Se_id5"+i).value;
	}
	if(qjsize=="4"){
		Se_id1=document.getElementById("Se_id1"+i).value;
		Se_id2=document.getElementById("Se_id2"+i).value;
		Se_id3=document.getElementById("Se_id3"+i).value;
		Se_id4=document.getElementById("Se_id4"+i).value;
	}
	if(qjsize=="3"){
		Se_id1=document.getElementById("Se_id1"+i).value;
		Se_id2=document.getElementById("Se_id2"+i).value;
		Se_id3=document.getElementById("Se_id3"+i).value;
	}
	if(qjsize=="2"){
		Se_id1=document.getElementById("Se_id1"+i).value;
		Se_id2=document.getElementById("Se_id2"+i).value;
	}
	if(qjsize=="1"){
		Se_id1=document.getElementById("Se_id1"+i).value;
	}
	
	var qj1=trim(document.getElementById("qj1"+i).value);
	var qj2=trim(document.getElementById("qj2"+i).value);
	var qj3=trim(document.getElementById("qj3"+i).value);
	var qj4=trim(document.getElementById("qj4"+i).value);
	var qj5=trim(document.getElementById("qj5"+i).value);
	var qj6=trim(document.getElementById("qj6"+i).value);
	if((price=="" || price=="0.00")  && qj1=="")
	  {
	    alert("统一单价与区间1只能有一个为空");
	    document.getElementById("price"+i).focus();
	    return false;
	  }
	if(qj1!=""){
		if(!regqj.test(qj1))
		{
			alert("请按照格式“楼层-楼层#单价”格式输入！");
			document.getElementById("qj1"+i).focus();
			return false;		
		}
	}
	if(qj2!=""){
		if(!regqj.test(qj2))
		{
			alert("请按照格式“楼层-楼层#单价”格式输入！");
			document.getElementById("qj2"+i).focus();
			return false;		
		}
	}
	if(qj3!=""){
		if(!regqj.test(qj3))
		{
			alert("请按照格式“楼层-楼层#单价”格式输入！");
			document.getElementById("qj3"+i).focus();
			return false;		
		}
	}
	if(qj4!=""){
		if(!regqj.test(qj4))
		{
			alert("请按照格式“楼层-楼层#单价”格式输入！");
			document.getElementById("qj4"+i).focus();
			return false;		
		}
	}
	if(qj5!=""){
		if(!regqj.test(qj5))
		{
			alert("请按照格式“楼层-楼层#单价”格式输入！");
			document.getElementById("qj5"+i).focus();
			return false;		
		}
	}
	if(qj6!=""){
		if(!regqj.test(qj6))
		{
			alert("请按照格式“楼层-楼层#单价”格式输入！");
			document.getElementById("qj6"+i).focus();
			return false;		
		}
	}
	if(qj1==""){
		if(qj2!=""){
			alert("请按区间序号填写，不能跳写！！");
			document.getElementById("qj1"+i).focus();
			return false;	
		}
	}
	if(qj2==""){
		if(qj3!=""){
			alert("请按区间序号填写，不能跳写！！");
			document.getElementById("qj2"+i).focus();
			return false;	
		}
	}
	if(qj3==""){
		if(qj4!=""){
			alert("请按区间序号填写，不能跳写！！");
			document.getElementById("qj3"+i).focus();
			return false;	
		}
	}
	if(qj4==""){
		if(qj5!=""){
			alert("请按区间序号填写，不能跳写！！");
			document.getElementById("qj4"+i).focus();
			return false;	
		}
	}
	if(qj5==""){
		if(qj6!=""){
			alert("请按区间序号填写，不能跳写！！");
			document.getElementById("qj5"+i).focus();
			return false;	
		}
	}
	if(qj1!="" && qj2!=""){
	 if(isRangeIn(qj1.split("#")[0].split("-")[0],qj1.split("#")[0].split("-")[1],qj2.split("#")[0].split("-")[0]) || isRangeIn(qj1.split("#")[0].split("-")[0],qj1.split("#")[0].split("-")[1],qj2.split("#")[0].split("-")[1])){
		 alert("楼号区间不能交叉！");
		 document.getElementById("qj2"+i).focus();
		 return false;		 
	  }	
	}
	if(qj1!="" && qj2!="" && qj3!=""){
		 if(isRangeIn(qj1.split("#")[0].split("-")[0],qj1.split("#")[0].split("-")[1],qj3.split("#")[0].split("-")[0]) || isRangeIn(qj1.split("#")[0].split("-")[0],qj1.split("#")[0].split("-")[1],qj3.split("#")[0].split("-")[1]) || isRangeIn(qj2.split("#")[0].split("-")[0],qj2.split("#")[0].split("-")[1],qj3.split("#")[0].split("-")[0]) || isRangeIn(qj2.split("#")[0].split("-")[0],qj2.split("#")[0].split("-")[1],qj3.split("#")[0].split("-")[1]) ){
			 alert("楼号区间不能交叉！");
			 document.getElementById("qj3"+i).focus();
			 return false;		 
		  }	
		}
	if(qj1!="" && qj2!="" && qj3!="" && qj4!=""){
		 if(isRangeIn(qj1.split("#")[0].split("-")[0],qj1.split("#")[0].split("-")[1],qj4.split("#")[0].split("-")[0]) || isRangeIn(qj1.split("#")[0].split("-")[0],qj1.split("#")[0].split("-")[1],qj4.split("#")[0].split("-")[1]) || isRangeIn(qj2.split("#")[0].split("-")[0],qj2.split("#")[0].split("-")[1],qj4.split("#")[0].split("-")[0]) || isRangeIn(qj2.split("#")[0].split("-")[0],qj2.split("#")[0].split("-")[1],qj4.split("#")[0].split("-")[1]) || isRangeIn(qj3.split("#")[0].split("-")[0],qj3.split("#")[0].split("-")[1],qj4.split("#")[0].split("-")[0]) || isRangeIn(qj3.split("#")[0].split("-")[0],qj3.split("#")[0].split("-")[1],qj4.split("#")[0].split("-")[1])){
			 alert("楼号区间不能交叉！");
			 document.getElementById("qj4"+i).focus();
			 return false;		 
		  }	
		}
	if(qj1!="" && qj2!="" && qj3!="" && qj4!="" && qj5!=""){
		 if(isRangeIn(qj1.split("#")[0].split("-")[0],qj1.split("#")[0].split("-")[1],qj5.split("#")[0].split("-")[0]) || isRangeIn(qj1.split("#")[0].split("-")[0],qj1.split("#")[0].split("-")[1],qj5.split("#")[0].split("-")[1]) || isRangeIn(qj2.split("#")[0].split("-")[0],qj2.split("#")[0].split("-")[1],qj5.split("#")[0].split("-")[0]) || isRangeIn(qj2.split("#")[0].split("-")[0],qj2.split("#")[0].split("-")[1],qj5.split("#")[0].split("-")[1]) || isRangeIn(qj3.split("#")[0].split("-")[0],qj3.split("#")[0].split("-")[1],qj5.split("#")[0].split("-")[0]) || isRangeIn(qj3.split("#")[0].split("-")[0],qj3.split("#")[0].split("-")[1],qj5.split("#")[0].split("-")[1])  || isRangeIn(qj4.split("#")[0].split("-")[0],qj4.split("#")[0].split("-")[1],qj5.split("#")[0].split("-")[0]) || isRangeIn(qj4.split("#")[0].split("-")[0],qj4.split("#")[0].split("-")[1],qj5.split("#")[0].split("-")[1]) ){
			 alert("楼号区间不能交叉！");
			 document.getElementById("qj5"+i).focus();
			 return false;		 
		  }	
		}
	if(qj1!="" && qj2!="" && qj3!="" && qj4!="" && qj5!="" && qj6!=""){
		 if(isRangeIn(qj1.split("#")[0].split("-")[0],qj1.split("#")[0].split("-")[1],qj6.split("#")[0].split("-")[0]) || isRangeIn(qj1.split("#")[0].split("-")[0],qj1.split("#")[0].split("-")[1],qj6.split("#")[0].split("-")[1]) || isRangeIn(qj2.split("#")[0].split("-")[0],qj2.split("#")[0].split("-")[1],qj6.split("#")[0].split("-")[0]) || isRangeIn(qj2.split("#")[0].split("-")[0],qj2.split("#")[0].split("-")[1],qj6.split("#")[0].split("-")[1]) || isRangeIn(qj3.split("#")[0].split("-")[0],qj3.split("#")[0].split("-")[1],qj6.split("#")[0].split("-")[0]) || isRangeIn(qj3.split("#")[0].split("-")[0],qj3.split("#")[0].split("-")[1],qj6.split("#")[0].split("-")[1])  || isRangeIn(qj4.split("#")[0].split("-")[0],qj4.split("#")[0].split("-")[1],qj6.split("#")[0].split("-")[0]) || isRangeIn(qj4.split("#")[0].split("-")[0],qj4.split("#")[0].split("-")[1],qj6.split("#")[0].split("-")[1]) || isRangeIn(qj5.split("#")[0].split("-")[0],qj5.split("#")[0].split("-")[1],qj6.split("#")[0].split("-")[0]) || isRangeIn(qj5.split("#")[0].split("-")[0],qj5.split("#")[0].split("-")[1],qj6.split("#")[0].split("-")[1]) ){
			 alert("楼号区间不能交叉！");
			 document.getElementById("qj6"+i).focus();
			 return false;		 
		  }	
		}
	var QString="Ct_id="+Ct_id+"&Ei_id="+Ei_id+"&name="+name+"&price="+price+"&status="+status
	+"&shoufeifs="+shoufeifs+"&remark="+remark+"&EhType="+EhType+"&znjday="+znjday+"&znjbl="+znjbl+
	"&Se_id1="+Se_id1+"&Se_id2="+Se_id2+"&Se_id3="+Se_id3+"&Se_id4="+Se_id4+"&Se_id5="+Se_id5+"&Se_id6="+Se_id6+
	"&qj1="+qj1+"&qj2="+qj2+"&qj3="+qj3+"&qj4="+qj4+"&qj5="+qj5+"&qj6="+qj6+"&qjsize="+qjsize+"&es_id="+es_id;
	
	sendPOST(url,QString);
}
//电梯费收取设置
function TB_Item_DianTiadd(url,url2)
{
	OBJ="showmain";
	URL2=url2;
	var xiaoqu=document.getElementById("xiaoqu").value;
	var sfname=trim(document.getElementById("sfname").value);
	var sftype=document.getElementById("sftype").value;
	var price=trim(document.getElementById("price").value);
	var remark="";//trim(document.getElementById("remark").value);
	var EhType=trim(document.getElementById("EhType").value);
	var znjday=trim(document.getElementById("znjday").value);
	var zjnbl=trim(document.getElementById("zjnbl").value);
	if(xiaoqu=="")
	{
		alert("请选择小区名称");
		document.getElementById("xiaoqu").focus();
		return false;
	}
	if(sfname=="")
	{
		alert("收费名称不能为空");
		document.getElementById("sfname").focus();
		return false;
	}
	if(regstrSYSP.test(sfname)){
		alert("收费名称不能含有特殊字符！");
	   document.getElementById("sfname").focus();
	   return false;
	}
	if(EhType==""){
		alert("请选择房屋类型");
		document.getElementById("EhType").focus();
		return false;
	}
	if(sftype=="0")
	{
		alert("请选择收费方式");
		document.getElementById("sftype").focus();
		return false;
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday").focus();
			return false;
		}
	}
	if(zjnbl!=""){
		if(!regexZX.test(zjnbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("zjnbl").focus();
			return false;
		}
	}
	if((znjday=="" && zjnbl!="") || (znjday!="" && zjnbl=="")){
			alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
			return false;	
	}

	var se_qz1=trim(document.getElementById("se_qz1").value); //区间1
	var se_hz1=trim(document.getElementById("se_hz1").value);
	var Seprice1=trim(document.getElementById("Seprice1").value); //价格1
	var se_qz2=trim(document.getElementById("se_qz2").value); //区间2
	var se_hz2=trim(document.getElementById("se_hz2").value);
	var Seprice2=trim(document.getElementById("Seprice2").value); //价格2
	var se_qz3=trim(document.getElementById("se_qz3").value); //区间3
	var se_hz3=trim(document.getElementById("se_hz3").value);
	var Seprice3=trim(document.getElementById("Seprice3").value); //价格3
	var se_qz4=trim(document.getElementById("se_qz4").value); //区间4
	var se_hz4=trim(document.getElementById("se_hz4").value);
	var Seprice4=trim(document.getElementById("Seprice4").value); //价格4
	var se_qz5=trim(document.getElementById("se_qz5").value); //区间5
	var se_hz5=trim(document.getElementById("se_hz5").value);
	var Seprice5=trim(document.getElementById("Seprice5").value); //价格5
	var se_qz6=trim(document.getElementById("se_qz6").value); //区间6
	var se_hz6=trim(document.getElementById("se_hz6").value);
	var Seprice6=trim(document.getElementById("Seprice6").value); //价格6
	if(Seprice1=="" && (price=="" || price=="0.00")){
	    alert("统一单价与区间1单价只能有一个为空！");
	    document.getElementById("price").focus();
	    return false;  
	  }
	if(price!=""){
		if(!isPrice.test(price))
		{
			alert("统一单价金额格式错误，最多保留两位小数！");
			document.getElementById("price").focus();
			return false;		
		}
	}
	if(se_qz1=="" || se_hz1=="" || Seprice1==""){
		if(se_qz2!="" || Seprice2!="" || se_qz2!=""){
			alert("请按区间序号填写，不能跳写！！");
			document.getElementById("se_qz1").focus();
			return false;	
		}
	}
	if(se_qz2=="" || se_hz2=="" || Seprice2==""){
		if(se_qz3!="" || Seprice3!="" || se_qz3!=""){
			alert("请按区间序号填写，不能跳写！！");
			document.getElementById("se_qz2").focus();
			return false;	
		}
	}
	if(se_qz3=="" || se_hz3=="" || Seprice3==""){
		if(se_qz4!="" || Seprice4!="" || se_qz4!=""){
			alert("请按区间序号填写，不能跳写！！");
			document.getElementById("se_qz3").focus();
			return false;	
		}
	}
	if(se_qz4=="" || se_hz4=="" || Seprice4==""){
		if(se_qz5!="" || Seprice5!="" || se_qz5!=""){
			alert("请按区间序号填写，不能跳写！！");
			document.getElementById("se_qz4").focus();
			return false;	
		}
	}
	if(se_qz5=="" || se_hz5=="" || Seprice5==""){
		if(se_qz6!="" || Seprice6!="" || se_qz6!=""){
			alert("请按区间序号填写，不能跳写！！");
			document.getElementById("se_qz5").focus();
			return false;	
		}
	}
	if((se_qz1=="" && Seprice1!="") || (se_qz1!="" && Seprice1=="")|| (se_hz1=="" && Seprice1!="") || (se_hz1!="" && Seprice1=="") || (se_qz1=="" && se_hz1!="") || (se_qz1!="" && se_hz1=="")){
		alert("楼层区间1与区间1单价要么都填要么都不填！！");
		document.getElementById("se_qz1").focus();
		return false;	
    }
	if((se_qz2=="" && Seprice2!="") || (se_qz2!="" && Seprice2=="")|| (se_hz2=="" && Seprice2!="") || (se_hz2!="" && Seprice2=="") || (se_qz2=="" && se_hz2!="") || (se_qz2!="" && se_hz2=="")){
		alert("楼层区间2与区间2单价要么都填要么都不填！！");
		document.getElementById("se_qz2").focus();
		return false;	
    }
	if((se_qz3=="" && Seprice3!="") || (se_qz3!="" && Seprice3=="")|| (se_hz3=="" && Seprice3!="") || (se_hz3!="" && Seprice3=="") || (se_qz3=="" && se_hz3!="") || (se_qz3!="" && se_hz3=="")){
		alert("楼层区间3与区间3单价要么都填要么都不填！！");
		document.getElementById("se_qz3").focus();
		return false;	
    }
	if((se_qz4=="" && Seprice4!="") || (se_qz4!="" && Seprice4=="")|| (se_hz4=="" && Seprice4!="") || (se_hz4!="" && Seprice4=="") || (se_qz4=="" && se_hz4!="") || (se_qz4!="" && se_hz4=="")){
		alert("楼层区间4与区间4单价要么都填要么都不填！！");
		document.getElementById("se_qz4").focus();
		return false;	
    }
	if((se_qz5=="" && Seprice5!="") || (se_qz5!="" && Seprice5=="")|| (se_hz5=="" && Seprice5!="") || (se_hz5!="" && Seprice5=="") || (se_qz5=="" && se_hz5!="") || (se_qz5!="" && se_hz5=="")){
		alert("楼层区间5与区间5单价要么都填要么都不填！！");
		document.getElementById("se_qz5").focus();
		return false;	
    }
	if((se_qz6=="" && Seprice6!="") || (se_qz6!="" && Seprice6=="")|| (se_hz6=="" && Seprice6!="") || (se_hz6!="" && Seprice6=="") || (se_qz6=="" && se_hz6!="") || (se_qz6!="" && se_hz6=="")){
		alert("楼层区间6与区间6单价要么都填要么都不填！！");
		document.getElementById("se_qz6").focus();
		return false;	
    }
	if(se_qz1!=""){
		if(!reghow.test(se_qz1))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_qz1").focus();
			return false;		
		}
	}
	if(se_hz1!=""){
		if(!reghow.test(se_hz1))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_hz1").focus();
			return false;		
		}
	}
	if(Seprice1!=""){
		if(!isPrice.test(Seprice1))
		{
			alert("区间1单价金额格式错误，最多保留两位小数！");
			document.getElementById("Seprice1").focus();
			return false;		
		}
	}
	if(se_qz2!=""){
		if(!reghow.test(se_qz2))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_qz2").focus();
			return false;		
		}
	}
	if(se_hz2!=""){
		if(!reghow.test(se_hz2))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_hz2").focus();
			return false;		
		}
	}
	if(Seprice2!=""){
		if(!isPrice.test(Seprice2))
		{
			alert("区间2单价金额格式错误，最多保留两位小数！");
			document.getElementById("Seprice2").focus();
			return false;		
		}
	}
	if(se_qz3!=""){
		if(!reghow.test(se_qz3))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_qz3").focus();
			return false;		
		}
	}
	if(se_hz3!=""){
		if(!reghow.test(se_hz3))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_hz3").focus();
			return false;		
		}
	}
	if(Seprice3!=""){
		if(!isPrice.test(Seprice3))
		{
			alert("区间3单价金额格式错误，最多保留两位小数！");
			document.getElementById("Seprice3").focus();
			return false;		
		}
	}
	if(se_qz4!=""){
		if(!reghow.test(se_qz4))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_qz4").focus();
			return false;		
		}
	}
	if(se_hz4!=""){
		if(!reghow.test(se_hz4))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_hz4").focus();
			return false;		
		}
	}
	if(Seprice4!=""){
		if(!isPrice.test(Seprice4))
		{
			alert("区间4单价金额格式错误，最多保留两位小数！");
			document.getElementById("Seprice4").focus();
			return false;		
		}
	}
	if(se_qz5!=""){
		if(!reghow.test(se_qz5))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_qz5").focus();
			return false;		
		}
	}
	if(se_hz5!=""){
		if(!reghow.test(se_hz5))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_hz5").focus();
			return false;		
		}
	}
	if(Seprice5!=""){
		if(!isPrice.test(Seprice5))
		{
			alert("区间5单价金额格式错误，最多保留两位小数！");
			document.getElementById("Seprice5").focus();
			return false;		
		}
	}
	if(se_qz6!=""){
		if(!reghow.test(se_qz6))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_qz6").focus();
			return false;		
		}
	}
	if(se_hz6!=""){
		if(!reghow.test(se_hz6))
		{
			alert("楼层区间设置必须为数字");
			document.getElementById("se_hz6").focus();
			return false;		
		}
	}
	if(Seprice6!=""){
		if(!isPrice.test(Seprice6))
		{
			alert("区间6单价金额格式错误，最多保留两位小数！");
			document.getElementById("Seprice6").focus();
			return false;		
		}
	}
	if(se_qz1!="" && se_hz1!="" && se_qz2!="" && se_hz2!="" ){
	 if(isRangeIn(se_qz1,se_hz1,se_qz2) || isRangeIn(se_qz1,se_hz1,se_hz2)){
		 alert("楼号区间不能交叉！");
		 document.getElementById("se_qz2").focus();
		 return false;		 
	  }
	}
	if(se_qz1!="" && se_hz1!="" && se_qz2!="" && se_hz2!="" && se_qz3!="" && se_hz3!="" ){
		 if(isRangeIn(se_qz1,se_hz1,se_qz3) || isRangeIn(se_qz1,se_hz1,se_hz3) ||isRangeIn(se_qz2,se_hz2,se_qz3) || isRangeIn(se_qz2,se_hz2,se_hz3)){
			 alert("楼号区间不能交叉！");
			 document.getElementById("se_qz3").focus();
			 return false;		 
		  }
		}
	if(se_qz1!="" && se_hz1!="" && se_qz2!="" && se_hz2!="" && se_qz3!="" && se_hz3!="" && se_qz4!="" && se_hz4!="" ){
		 if(isRangeIn(se_qz1,se_hz1,se_qz4) || isRangeIn(se_qz1,se_hz1,se_hz4) ||isRangeIn(se_qz2,se_hz2,se_qz4) || isRangeIn(se_qz2,se_hz2,se_hz4) ||isRangeIn(se_qz3,se_hz3,se_qz4) || isRangeIn(se_qz3,se_hz3,se_hz4)){
			 alert("楼号区间不能交叉！");
			 document.getElementById("se_qz4").focus();
			 return false;		 
		  }
		}
	if(se_qz1!="" && se_hz1!="" && se_qz2!="" && se_hz2!="" && se_qz3!="" && se_hz3!="" && se_qz4!="" && se_hz4!="" && se_qz5!="" && se_hz5!=""  ){
		 if(isRangeIn(se_qz1,se_hz1,se_qz5) || isRangeIn(se_qz1,se_hz1,se_hz5) ||isRangeIn(se_qz2,se_hz2,se_qz5) || isRangeIn(se_qz2,se_hz2,se_hz5) ||isRangeIn(se_qz3,se_hz3,se_qz5) || isRangeIn(se_qz3,se_hz3,se_hz5) ||isRangeIn(se_qz4,se_hz4,se_qz5) || isRangeIn(se_qz4,se_hz4,se_hz5)){
			 alert("楼号区间不能交叉！");
			 document.getElementById("se_qz5").focus();
			 return false;		 
		  }
		}
	if(se_qz1!="" && se_hz1!="" && se_qz2!="" && se_hz2!="" && se_qz3!="" && se_hz3!="" && se_qz4!="" && se_hz4!="" && se_qz5!="" && se_hz5!="" && se_qz6!="" && se_hz6!="" ){
		if(isRangeIn(se_qz1,se_hz1,se_qz6) || isRangeIn(se_qz1,se_hz1,se_hz6) ||isRangeIn(se_qz2,se_hz2,se_qz6) || isRangeIn(se_qz2,se_hz2,se_hz6) ||isRangeIn(se_qz3,se_hz3,se_qz6) || isRangeIn(se_qz3,se_hz3,se_hz6) ||isRangeIn(se_qz4,se_hz4,se_qz6) || isRangeIn(se_qz4,se_hz4,se_hz6) ||isRangeIn(se_qz5,se_hz5,se_qz6) || isRangeIn(se_qz5,se_hz5,se_hz6)){
			 alert("楼号区间不能交叉！");
			 document.getElementById("se_qz6").focus();
			 return false;		 
		  }
	 }
	var QString="xiaoqu="+xiaoqu+"&sfname="+sfname+"&sftype="+sftype+"&price="+price+"&remark="+remark+"&EhType="+EhType+
	"&se_qz1="+se_qz1+"&se_hz1="+se_hz1+"&Seprice1="+Seprice1+
	"&se_qz2="+se_qz2+"&se_hz2="+se_hz2+"&Seprice2="+Seprice2+
	"&se_qz3="+se_qz3+"&se_hz3="+se_hz3+"&Seprice3="+Seprice3+
	"&se_qz4="+se_qz1+"&se_hz4="+se_hz4+"&Seprice4="+Seprice4+
	"&se_qz5="+se_qz5+"&se_hz5="+se_hz5+"&Seprice5="+Seprice5+
	"&se_qz6="+se_qz6+"&se_hz6="+se_hz6+"&Seprice6="+Seprice6+"&znjday="+znjday+"&zjnbl="+zjnbl;
	sendPOST(url,QString);
}
//判断数字是否在某个范围内
function isRangeIn(f1,f2,target)
{
    var falg = false ;
    f1 = parseInt(f1);
    f2 = parseInt(f2); 
    target=parseInt(target); 
    if(target >=f1 && target <=f2){
        falg = true;
    }
      return falg;     
   }
/*修改车位费缴费项目*/
function CarItem_update(status,Ct_id,Ei_id,sfname,i,url,returndivid,url2)
{
	OBJ=returndivid;
	URL2=url2;
	
	var name=sfname;
	var price=trim(document.getElementById("price"+i).value);
	var remark=trim(document.getElementById("remark"+i).value);
	var shoufeifs=trim(Rep_str(document.getElementById("shoufeifs"+i).value));
	var EhType=trim(document.getElementById("EhType"+i).value);
	var znjbl=trim(document.getElementById("znjbl"+i).value);
	var znjday=trim(document.getElementById("znjday"+i).value);
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price"+i).focus();
		return false;
	} 
	if(!isPrice.test(price))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("price"+i).focus();
		return false;		
	}
	if(EhType==""){
		alert("请选择房屋类型");
		document.getElementById("EhType"+i).focus();
		return false;
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday"+i).focus();
			return false;
		}
	}
	if(znjbl!=""){
		if(!regexZX.test(znjbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("znjbl"+i).focus();
			return false;
		}
	}
	if((znjday=="" && znjbl!="") || (znjday!="" && znjbl=="")){
		alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
		return false;	
   }
	var QString="Ct_id="+Ct_id+"&Ei_id="+Ei_id+"&name="+name+"&price="+price+"&status="+status
	+"&shoufeifs="+shoufeifs+"&remark="+remark+"&EhType="+EhType+"&znjday="+znjday+"&znjbl="+znjbl;
	
	sendPOST(url,QString);
}
//车位费收取
function TB_Item_Caradd(url,url2)
{
	OBJ="showmain";
	URL2=url2;
	var xiaoqu=document.getElementById("xiaoqu").value;
	var sfname=trim(document.getElementById("sfname").value);
	var sftype=document.getElementById("sftype").value;
	var price=trim(document.getElementById("price").value);
	var remark=trim(document.getElementById("remark").value);
	var EhType=trim(document.getElementById("EhType").value);
	var znjday=trim(document.getElementById("znjday").value);
	var zjnbl=trim(document.getElementById("zjnbl").value);
	if(xiaoqu=="")
	{
		alert("请选择小区名称");
		document.getElementById("xiaoqu").focus();
		return false;
	}
	if(sfname=="")
	{
		alert("收费名称不能为空");
		document.getElementById("sfname").focus();
		return false;
	}
	if(regstrSYSP.test(sfname)){
		alert("收费名称不能含有特殊字符！");
	   document.getElementById("sfname").focus();
	   return false;
	}
	if(EhType==""){
		alert("请选择房屋类型");
		document.getElementById("EhType").focus();
		return false;
	}
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price").focus();
		return false;
	}
	if(!isPrice.test(price))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("price").focus();
		return false;		
	}	
	if(sftype=="0")
	{
		alert("请选择收费方式");
		document.getElementById("sftype").focus();
		return false;
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday").focus();
			return false;
		}
	}
	if(zjnbl!=""){
		if(!regexZX.test(zjnbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("zjnbl").focus();
			return false;
		}
	}
	if((znjday=="" && zjnbl!="") || (znjday!="" && zjnbl=="")){
			alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
			return false;	
	}
	var QString="xiaoqu="+xiaoqu+"&sfname="+sfname+"&sftype="+sftype+"&price="+price+
	"&remark="+remark+"&EhType="+EhType+"&znjday="+znjday+"&zjnbl="+zjnbl;
	
	sendPOST(url,QString);
}
/*修改供暖缴费项目*/
function TBGongReItem_update(status,Ct_id,Ei_id,sfname,i,url,returndivid,url2)
{
	OBJ=returndivid;
	URL2=url2;
	
	var name=sfname;
	var price=trim(document.getElementById("price"+i).value);
	var guRatio=trim(document.getElementById("guRatio"+i).value);
	var remark=trim(document.getElementById("remark"+i).value);
	var shoufeifs=trim(Rep_str(document.getElementById("shoufeifs"+i).value));
	var EhType=trim(document.getElementById("EhType"+i).value);
	var znjbl=trim(document.getElementById("znjbl"+i).value);
	var znjday=trim(document.getElementById("znjday"+i).value);
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price"+i).focus();
		return false;
	} 
	if(!isPrice.test(price))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("price"+i).focus();
		return false;		
	}
	if(EhType==""){
		alert("房屋类型不能为空");
		document.getElementById("EhType"+i).focus();
		return false;
	}
	if(guRatio!=""){
		if(!regexZX.test(guRatio)){
			alert("故障比例须为整数或小数，请重新输入！！");
			document.getElementById("guRatio"+i).focus();
			return false;
		}
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday"+i).focus();
			return false;
		}
	}
	if(znjbl!=""){
		if(!regexZX.test(znjbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("znjbl"+i).focus();
			return false;
		}
	}
	if((znjday=="" && znjbl!="") || (znjday!="" && znjbl=="")){
		alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
		return false;	
   }
	var QString="Ct_id="+Ct_id+"&Ei_id="+Ei_id+"&name="+name+"&price="+price+"&guRatio="+guRatio+"&status="+status
	+"&shoufeifs="+shoufeifs+"&remark="+remark+"&EhType="+EhType+"&znjday="+znjday+"&znjbl="+znjbl;
	
	sendPOST(url,QString);
}
//供暖费收取设置
function TB_GongReItemadd(url,url2){
	OBJ="showmain";
	URL2=url2;
	var xiaoqu=document.getElementById("xiaoqu").value;
	var sfname=trim(document.getElementById("sfname").value);
	var sftype=document.getElementById("sftype").value;
	var price=trim(document.getElementById("price").value);
	var remark=trim(document.getElementById("remark").value);
	var guRatio=trim(document.getElementById("guRatio").value);
	var EhType=trim(document.getElementById("EhType").value);
	var znjday=trim(document.getElementById("znjday").value);
	var zjnbl=trim(document.getElementById("zjnbl").value);
	if(xiaoqu=="")
	{
		alert("请选择小区名称");
		document.getElementById("xiaoqu").focus();
		return false;
	}
	if(sfname=="")
	{
		alert("收费名称不能为空");
		document.getElementById("sfname").focus();
		return false;
	}
	if(regstrSYSP.test(sfname)){
		alert("收费名称不能含有特殊字符！");
	   document.getElementById("sfname").focus();
	   return false;
	}
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price").focus();
		return false;
	}
	if(!isPrice.test(price))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("price").focus();
		return false;		
	}	
	if(sftype=="0")
	{
		alert("请选择收费方式");
		document.getElementById("sftype").focus();
		return false;
	}
	if(EhType==""){
		alert("请选择房屋类型");
		document.getElementById("EhType").focus();
		return false;
	}
	if(guRatio!=""){
		if(!regexZX.test(guRatio)){
			alert("故障收取比例须为整数或小数，请重新输入！！");
			document.getElementById("guRatio").focus();
			return false;
		}
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday").focus();
			return false;
		}
	}
	if(zjnbl!=""){
		if(!regexZX.test(zjnbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("zjnbl").focus();
			return false;
		}
	}
	if((znjday=="" && zjnbl!="") || (znjday!="" && zjnbl=="")){
			alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
			return false;	
	}
	var QString="xiaoqu="+xiaoqu+"&sfname="+sfname+"&sftype="+sftype+"&price="+price+
	"&remark="+remark+"&guRatio="+guRatio+"&EhType="+EhType+"&znjday="+znjday+"&zjnbl="+zjnbl;
	
	sendPOST(url,QString);	
}
/*修改物业缴费项目*/
function TBWuyeItem_update(status,Ct_id,Ei_id,sfname,i,url,returndivid,url2)
{
	OBJ=returndivid;
	URL2=url2;
	
	var name=sfname;
	var price=trim(document.getElementById("price"+i).value);
	var znjbl=trim(document.getElementById("znjbl"+i).value);
	var znjday=trim(document.getElementById("znjday"+i).value);
	var remark=trim(document.getElementById("remark"+i).value);
	var shoufeifs=trim(Rep_str(document.getElementById("shoufeifs"+i).value));
	var EhType=trim(document.getElementById("EhType"+i).value);
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday"+i).focus();
			return false;
		}
	}
	if(znjbl!=""){
		if(!regexZX.test(znjbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("znjbl"+i).focus();
			return false;
		}
	}
	if((znjday=="" && znjbl!="") || (znjday!="" && znjbl=="")){
		alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
		return false;	
   }
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price"+i).focus();
		return false;
	} 
	if(!isPrice.test(price))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("price"+i).focus();
		return false;		
	}
	if(EhType==""){
		alert("房屋类型不能为空");
		document.getElementById("EhType"+i).focus();
		return false;
	}
	var QString="Ct_id="+Ct_id+"&Ei_id="+Ei_id+"&name="+name+"&price="+price+"&znjday="+znjday+"&znjbl="+znjbl+"&status="+status
	+"&shoufeifs="+shoufeifs+"&remark="+remark+"&EhType="+EhType;
	
	sendPOST(url,QString);
}
//物业费收取设置
function TB_WuYeItemadd(url,url2)
{
	OBJ="showmain";
	URL2=url2;
	var xiaoqu=document.getElementById("xiaoqu").value;
	var sfname=trim(document.getElementById("sfname").value);
	var sftype=document.getElementById("sftype").value;
	var price=trim(document.getElementById("price").value);
	var remark=trim(document.getElementById("remark").value);
	var znjday=trim(document.getElementById("znjday").value);
	var zjnbl=trim(document.getElementById("zjnbl").value);
    var EhType=trim(document.getElementById("EhType").value); //房屋类型
	if(xiaoqu=="")
	{
		alert("请选择小区名称");
		document.getElementById("xiaoqu").focus();
		return false;
	}
	if(sfname=="")
	{
		alert("收费名称不能为空");
		document.getElementById("sfname").focus();
		return false;
	}
	if(regstrSYSP.test(sfname)){
		alert("收费名称不能含有特殊字符！");
	   document.getElementById("sfname").focus();
	   return false;
	}
	if(EhType==""){
		alert("请选择房屋类型");
		document.getElementById("EhType").focus();
		return false;
	}
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price").focus();
		return false;
	}
	if(!isPrice.test(price))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("price").focus();
		return false;		
	}	
	if(sftype=="0")
	{
		alert("请选择收费方式");
		document.getElementById("sftype").focus();
		return false;
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday").focus();
			return false;
		}
	}
	if(zjnbl!=""){
		if(!regexZX.test(zjnbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("zjnbl").focus();
			return false;
		}
	}
	if((znjday=="" && zjnbl!="") || (znjday!="" && zjnbl=="")){
			alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
			return false;	
	
	}
	
	var QString="xiaoqu="+xiaoqu+"&sfname="+sfname+"&sftype="+sftype+"&price="+price+
	"&remark="+remark+"&znjday="+znjday+"&zjnbl="+zjnbl+"&EhType="+EhType;
	sendPOST(url,QString);
}
/*修改水费缴费项目*/
function TBWaterItem_update(status,Ct_id,Ei_id,sfname,i,url,returndivid,url2)
{
	OBJ=returndivid;
	URL2=url2;
	
	var name=sfname;
	var price=trim(document.getElementById("price"+i).value);
	var Limited=trim(document.getElementById("Limited"+i).value);
	var LimitNumber=trim(document.getElementById("LimitNumber"+i).value);
	var remark=trim(document.getElementById("remark"+i).value);
	var gdxz=getradio("gdxz"+i);
	var isyj=getradio("isyj"+i);
	var shoufeifs=trim(Rep_str(document.getElementById("shoufeifs"+i).value));
	var EhType=trim(document.getElementById("EhType"+i).value);
	var HouseStandard=trim(document.getElementById("HouseStandard"+i).value);
	var BelowStandard=trim(document.getElementById("BelowStandard"+i).value);
	var AboveStandard=trim(document.getElementById("AboveStandard"+i).value);
	var znjbl="";//trim(document.getElementById("znjbl"+i).value);
	var znjday="";//trim(document.getElementById("znjday"+i).value);
	if(Limited==""){
		alert("购买金额不能为空");
		document.getElementById("Limited"+i).focus();
		return false;
	}
	
	if(!regex.test(Limited)){
		alert("购买金额只能为数字，如有多个以#分割，例：50#100#150");
		document.getElementById("Limited"+i).focus();
		return false;
	}
	if(LimitNumber!=""){
		if(!reghow.test(LimitNumber)){
			alert("限购次数/月必须为数字，请重新输入！！");
			document.getElementById("LimitNumber"+i).focus();
			return false;
		}
	}
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price"+i).focus();
		return false;
	} 
	if(!isPrice.test(price))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("price"+i).focus();
		return false;		
	}
	if(HouseStandard!=""){
		if(!reghow.test(HouseStandard)){
			alert("房屋面积标准必须为数字，请重新输入！！");
			document.getElementById("HouseStandard"+i).focus();
			return false;
		}
		if(BelowStandard=="" || AboveStandard==""){
			alert("请输入标准限额");
			document.getElementById("BelowStandard"+i).focus();
			return false;
		}
		if(!reghow.test(BelowStandard)){
			alert("限额必须为整数，请重新输入！！");
			document.getElementById("BelowStandard"+i).focus();
			return false;
		}
		if(!reghow.test(AboveStandard)){
			alert("限额必须为整数，请重新输入！！");
			document.getElementById("AboveStandard"+i).focus();
			return false;
		}
		if(parseInt(BelowStandard)>parseInt(AboveStandard)){
			alert("低于标准的最高限额不能大于高于标准的最高限额，请重新输入！！");
			document.getElementById("BelowStandard"+i).focus();
			return false;
		}
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday"+i).focus();
			return false;
		}
	}
	if(znjbl!=""){
		if(!regexZX.test(znjbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("znjbl"+i).focus();
			return false;
		}
	}
	if((znjday=="" && znjbl!="") || (znjday!="" && znjbl=="")){
		alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
		return false;	
   }
	var QString="Ct_id="+Ct_id+"&Ei_id="+Ei_id+"&name="+name+"&price="+price+"&status="+status+"&EhType="+EhType+"&znjday="+znjday+"&znjbl="+znjbl
+"&Limited="+Limited+"&gdxz="+gdxz+"&isyj="+isyj+"&shoufeifs="+shoufeifs+"&remark="+remark+"&LimitNumber="+LimitNumber+"&HouseStandard="+HouseStandard+"&BelowStandard="+BelowStandard+"&AboveStandard="+AboveStandard;
	sendPOST(url,QString);
  }
//水费收取
function TB_WaterItemadd(url,url2){
	OBJ="showmain";
	URL2=url2;
	var xiaoqu=document.getElementById("xiaoqu").value;
	var sfname=trim(document.getElementById("sfname").value);
	var sftype=document.getElementById("sftype").value;
	var price=trim(document.getElementById("price").value);
	var remark=trim(document.getElementById("remark").value);
	var Limited=trim(document.getElementById("Limited").value);
	var LimitNumber=trim(document.getElementById("LimitNumber").value);
	var EhType=trim(document.getElementById("EhType").value);
	var HouseStandard=trim(document.getElementById("HouseStandard").value);
	var BelowStandard=trim(document.getElementById("BelowStandard").value);
	var AboveStandard=trim(document.getElementById("AboveStandard").value);
	var znjday="";//trim(document.getElementById("znjday").value);
	var zjnbl="";//trim(document.getElementById("zjnbl").value);
	var LimitedStr="";
	var gdxz=getradio("gdxz");
	var isyj=getradio("isyj");
	var checkid=new Array();
	getcheckbox(checkid,"ch");
	if(checkid.length>0)
	{
		for( var i=0;i<checkid.length;i++)
		{
			var str=checkid[i]+"#";			
			LimitedStr+=str;
		}
		
	}
	
	if(Limited!=""){
		LimitedStr+=Limited+"#";
	}
	if(xiaoqu=="")
	{
		alert("请选择小区名称");
		document.getElementById("xiaoqu").focus();
		return false;
	}
	if(sfname=="")
	{
		alert("收费名称不能为空");
		document.getElementById("sfname").focus();
		return false;
	}
	if(regstrSYSP.test(sfname)){
		alert("收费名称不能含有特殊字符！");
	   document.getElementById("sfname").focus();
	   return false;
	}
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price").focus();
		return false;
	}
	if(!isPrice.test(price))
	{
		alert("单价（元）金额格式错误，最多保留两位小数！");
		document.getElementById("price").focus();
		return false;		
	}
	if(EhType==""){
		alert("请选择房屋类型");
		document.getElementById("EhType").focus();
		return false;
	}
	if(sftype=="0")
	{
		alert("请选择收费方式");
		document.getElementById("sftype").focus();
		return false;
	}
	if(LimitedStr==""){
		alert("请选中前三个其中任意一个(购买金额的选择框为蓝色才算选中状态哦)或输入第四个购买金额");
		return false;
	}
	if(LimitNumber!=""){
		if(!reghow.test(LimitNumber)){
			alert("限购次数/月必须为数字，请重新输入！！");
			document.getElementById("LimitNumber").focus();
			return false;
		}
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday").focus();
			return false;
		}
	}
	if(zjnbl!=""){
		if(!regexZX.test(zjnbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("zjnbl").focus();
			return false;
		}
	}
	if((znjday=="" && zjnbl!="") || (znjday!="" && zjnbl=="")){
			alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
			return false;	
	
	}
	if(HouseStandard!=""){
		if(!reghow.test(HouseStandard)){
			alert("房屋面积标准必须为数字，请重新输入！！");
			document.getElementById("HouseStandard").focus();
			return false;
		}
		if(BelowStandard=="" || AboveStandard==""){
			alert("请输入标准限额");
			document.getElementById("BelowStandard").focus();
			return false;
		}
		if(!reghow.test(BelowStandard)){
			alert("限额必须为整数，请重新输入！！");
			document.getElementById("BelowStandard").focus();
			return false;
		}
		if(!reghow.test(AboveStandard)){
			alert("限额必须为整数，请重新输入！！");
			document.getElementById("AboveStandard").focus();
			return false;
		}
		if(parseInt(BelowStandard)>parseInt(AboveStandard)){
			alert("低于标准的最高限额不能大于高于标准的最高限额，请重新输入！！");
			document.getElementById("BelowStandard").focus();
			return false;
		}
	}
	var QString="xiaoqu="+xiaoqu+"&sfname="+sfname+"&sftype="+sftype+"&price="+price+"&EhType="+EhType+"&znjday="+znjday+"&zjnbl="+zjnbl+
"&remark="+remark+"&Limited="+LimitedStr+"&gdxz="+gdxz+"&isyj="+isyj+"&LimitNumber="+LimitNumber+"&HouseStandard="+HouseStandard+"&BelowStandard="+BelowStandard+"&AboveStandard="+AboveStandard;
	sendPOST(url,QString);
}
/*修改电费缴费项目*/
function TBEleItem_update(status,Ct_id,Ei_id,sfname,i,url,returndivid,url2)
{
	OBJ=returndivid;
	URL2=url2;
	
	var name=sfname;
	var price=trim(document.getElementById("price"+i).value);
	var Limited=trim(document.getElementById("Limited"+i).value);
	var LimitNumber=trim(document.getElementById("LimitNumber"+i).value);
	var remark=trim(document.getElementById("remark"+i).value);
	var gdxz=getradio("gdxz"+i);
	var isyj=getradio("isyj"+i);
	var shoufeifs=trim(Rep_str(document.getElementById("shoufeifs"+i).value));
	var EhType=trim(document.getElementById("EhType"+i).value);
	var HouseStandard=trim(document.getElementById("HouseStandard"+i).value);
	var BelowStandard=trim(document.getElementById("BelowStandard"+i).value);
	var AboveStandard=trim(document.getElementById("AboveStandard"+i).value);
	var znjbl="";//trim(document.getElementById("znjbl"+i).value);
	var znjday="";//trim(document.getElementById("znjday"+i).value);
	if(Limited==""){
		alert("购买金额不能为空");
		document.getElementById("Limited"+i).focus();
		return false;
	}
	
	if(LimitNumber==""){
		alert("请填写限购次数/月");
		document.getElementById("LimitNumber"+i).focus();
		return false;
	}
	if(LimitNumber!=""){
		if(!reghow.test(LimitNumber)){
			alert("限购次数/月必须为数字，请重新输入！！");
			document.getElementById("LimitNumber"+i).focus();
			return false;
		}
	}
	if(!regex.test(Limited)){
		alert("购买金额只能为数字，如有多个以#分割，例：50#100#150");
		document.getElementById("Limited"+i).focus();
		return false;
	}
	if(!regnum.test(LimitNumber)){
		alert("限购次数/月正整数，请重新输入！！");
		document.getElementById("LimitNumber"+i).focus();
		return false;
	}
	if(EhType==""){
		alert("请选择房屋类型");
		document.getElementById("EhType"+i).focus();
		return false;
	}
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price"+i).focus();
		return false;
	} 
	if(!isPrice3.test(price))
	{
		alert("单价（元）金额格式错误，最多保留三位小数！");
		document.getElementById("price"+i).focus();
		return false;		
	}
	if(HouseStandard!=""){
		if(!reghow.test(HouseStandard)){
			alert("房屋面积标准必须为数字，请重新输入！！");
			document.getElementById("HouseStandard"+i).focus();
			return false;
		}
		if(BelowStandard=="" || AboveStandard==""){
			alert("请输入标准限额！！");
			document.getElementById("BelowStandard"+i).focus();
			return false;
		}
		if(!reghow.test(BelowStandard)){
			alert("限额必须为整数，请重新输入！！");
			document.getElementById("BelowStandard"+i).focus();
			return false;
		}
		if(!reghow.test(AboveStandard)){
			alert("限额必须为整数，请重新输入！！");
			document.getElementById("AboveStandard"+i).focus();
			return false;
		}
		if(parseInt(BelowStandard)>parseInt(AboveStandard)){
			alert("低于标准的最高限额不能大于高于标准的最高限额，请重新输入！！");
			document.getElementById("BelowStandard"+i).focus();
			return false;
		}
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday"+i).focus();
			return false;
		}
	}
	if(znjbl!=""){
		if(!regexZX.test(znjbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("znjbl"+i).focus();
			return false;
		}
	}
	if((znjday=="" && znjbl!="") || (znjday!="" && znjbl=="")){
		alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
		return false;	
   }
	var QString="Ct_id="+Ct_id+"&Ei_id="+Ei_id+"&name="+name+"&price="+price+"&status="+status+"&EhType="+EhType+"&znjday="+znjday+"&znjbl="+znjbl+
"&Limited="+Limited+"&LimitNumber="+LimitNumber+"&gdxz="+gdxz+"&isyj="+isyj+"&shoufeifs="+shoufeifs+"&remark="+remark+"&HouseStandard="+HouseStandard+"&BelowStandard="+BelowStandard+"&AboveStandard="+AboveStandard;
	sendPOST(url,QString);
  }
//电费收取
function TB_ElectricItemadd(url,url2){
	OBJ="showmain";
	URL2=url2;
	var xiaoqu=document.getElementById("xiaoqu").value;
	var sfname=trim(document.getElementById("sfname").value);
	var sftype=document.getElementById("sftype").value;
	var price=trim(document.getElementById("price").value);
	var remark=trim(document.getElementById("remark").value);
	var Limited=trim(document.getElementById("Limited").value);
	var LimitNumber=trim(document.getElementById("LimitNumber").value);
	var EhType=trim(document.getElementById("EhType").value);
	var HouseStandard=trim(document.getElementById("HouseStandard").value);
	var BelowStandard=trim(document.getElementById("BelowStandard").value);
	var AboveStandard=trim(document.getElementById("AboveStandard").value);
	var znjday="";//trim(document.getElementById("znjday").value);
	var zjnbl="";//trim(document.getElementById("zjnbl").value);
	var LimitedStr="";
	var gdxz=getradio("gdxz");
	var isyj=getradio("isyj");
	var checkid=new Array();
	getcheckbox(checkid,"ch");
	if(checkid.length>0)
	{
		for( var i=0;i<checkid.length;i++)
		{
			var str=checkid[i]+"#";			
			LimitedStr+=str;
		}
		
	}
	
	if(Limited!=""){
		LimitedStr+=Limited+"#";
	}
	if(xiaoqu=="")
	{
		alert("请选择小区名称");
		document.getElementById("xiaoqu").focus();
		return false;
	}
	if(sfname=="")
	{
		alert("收费名称不能为空");
		document.getElementById("sfname").focus();
		return false;
	}
	if(regstrSYSP.test(sfname)){
		alert("收费名称不能含有特殊字符！");
	   document.getElementById("sfname").focus();
	   return false;
	}
	if(price=="")
	{
		alert("单价不能为空");
		document.getElementById("price").focus();
		return false;
	}
	if(!isPrice3.test(price))
	{
		alert("单价（元）金额格式错误，最多保留三位小数！");
		document.getElementById("price").focus();
		return false;		
	}
	if(EhType==""){
		alert("请选择房屋类型");
		document.getElementById("EhType").focus();
		return false;
	}
	if(sftype=="0")
	{
		alert("请选择收费方式");
		document.getElementById("sftype").focus();
		return false;
	}
	if(LimitedStr==""){
		alert("请选中前三个其中任意一个(购买金额的选择框为蓝色才算选中状态哦)或输入第四个购买金额");
		return false;
	}
	if(LimitNumber==""){
		alert("请填写限购次数/月");
		document.getElementById("LimitNumber").focus();
		return false;
	}
	if(LimitNumber!=""){
		if(!reghow.test(LimitNumber)){
			alert("限购次数/月必须为数字，请重新输入！！");
			document.getElementById("LimitNumber").focus();
			return false;
		}
	}
	if(HouseStandard!=""){
		if(!reghow.test(HouseStandard)){
			alert("房屋面积标准必须为数字，请重新输入！！");
			document.getElementById("HouseStandard").focus();
			return false;
		}
		if(BelowStandard=="" || AboveStandard==""){
			alert("请输入标准限额！！");
			document.getElementById("BelowStandard").focus();
			return false;
		}
		if(!reghow.test(BelowStandard)){
			alert("限额必须为整数，请重新输入！！");
			document.getElementById("BelowStandard").focus();
			return false;
		}
		if(!reghow.test(AboveStandard)){
			alert("限额必须为整数，请重新输入！！");
			document.getElementById("AboveStandard").focus();
			return false;
		}
		if(parseInt(BelowStandard)>parseInt(AboveStandard)){
			alert("低于标准的最高限额不能大于高于标准的最高限额，请重新输入！！");
			document.getElementById("BelowStandard").focus();
			return false;
		}
	}
	if(znjday!=""){
		if(!reginger.test(znjday)){
			alert("滞纳金天数须为正负数或0，请重新输入！！");
			document.getElementById("znjday").focus();
			return false;
		}
	}
	if(zjnbl!=""){
		if(!regexZX.test(zjnbl)){
			alert("滞纳金比例须为整数或小数，请重新输入！！");
			document.getElementById("zjnbl").focus();
			return false;
		}
	}
	if((znjday=="" && zjnbl!="") || (znjday!="" && zjnbl=="")){
			alert("滞纳金天数与滞纳金比例二者要么都填要么都不填！！");
			return false;	
	
	}
var QString="xiaoqu="+xiaoqu+"&sfname="+sfname+"&sftype="+sftype+"&price="+price+"&EhType="+EhType+"&znjday="+znjday+"&zjnbl="+zjnbl+
"&remark="+remark+"&Limited="+LimitedStr+"&gdxz="+gdxz+"&isyj="+isyj+"&LimitNumber="+LimitNumber+"&HouseStandard="+HouseStandard+"&BelowStandard="+BelowStandard+"&AboveStandard="+AboveStandard;

	sendPOST(url,QString);
}


//联动查询楼宇信息
function select_unite(th,divid,url,ts_id){
	currentPos=divid;
	var ly_id=th.value;
	var sign = document.getElementById("sign").value;
	var QString="ly_id="+ly_id+"&ts_id="+ts_id+"&sign="+sign;
	sendPOST(url,QString);
}

//添加维修人员
function addRepPeo(divid,url,url2){
	returnDIVID=divid;
	returnURL=url2;
	var Es_id= document.getElementById("Es_id").value;
	var ReName= trim(document.getElementById("ReName").value);
	var phone= trim(document.getElementById("phone").value);
	if(Es_id=="" ||Es_id==null ){
		alert("请选择小区！");
		document.getElementById("Es_id").focus();
		return false;
	}
	if(ReName==""){
		alert("维修人员姓名不能为空！");
		document.getElementById("ReName").focus();
		return false;
	}else if(!regstrSY.test(ReName)){
		alert("姓名不能有特殊字符！");
		return false;
	}
	if(phone==""){
		alert("联系方式不能为空！");
		document.getElementById("phone").focus();
		return false;
	}else if(!regphone.test(phone)){
		alert("联系方式格式不正确为空！");
		document.getElementById("phone").focus();
		return false;
	}
	var QString = "&Es_id="+Es_id+"&ReName="+ReName+"&phone="+phone;
	sendPOST(url, QString);
}

/* 修改维修人员信息 */
function updateRepPeo(Re_id, i, url, returndivid, url2) {
	returnDIVID = returndivid;
	returnURL = url2;
	var ReName = trim(document.getElementById("ReName"+i).value);
	var phone = trim(document.getElementById("phone"+i).value);
	var remark = trim(document.getElementById("remark"+i).value);
	if(ReName==""){
		alert("维修人员姓名不能为空！");
		document.getElementById("ReName").focus();
		return false;
	}else if(!regstrSY.test(ReName)){
		alert("姓名不能有特殊字符！");
		return false;
	}
	if(phone==""){
		alert("联系方式不能为空！");
		document.getElementById("phone").focus();
		return false;
	}else if(!regphone.test(phone)){
		alert("联系方式格式不正确为空！");
		document.getElementById("phone").focus();
		return false;
	}
	if(remark!=""){
		if(!regstrSY.test(remark)){
			alert("备注信息不能有特殊字符！");
			return false;
	    }
	}
	var QString = "Re_id=" + Re_id+"&ReName="+ReName+"&remark="+remark+"&phone="+phone;
	sendPOST(url, QString);
}

/* 删除维修人员信息 */
function delectRepPeo(Re_id, i, url, returndivid, url2) {
	returnDIVID = returndivid;
	returnURL = url2;
	var ReName = document.getElementById("ReName"+i).value;
	var QString = "Re_id=" +Re_id+"&ReName="+ReName;
	if(confirm("确定删除'"+ReName+"'吗？")){
		sendPOST(url, QString);
	}
}

/* 受理报修信息 */
function RepInfo(Inf_id, i, url, returndivid, url2) {
	returnDIVID = returndivid;
	returnURL = url2;
	var content = document.getElementById("content"+i).value;
	var QString = "Inf_id=" +Inf_id+"&content="+content;
	sendPOST(url, QString);
}
//报修信息
function selRepairPeo(divid,url)
{
	currentPos=divid;
	var es_id= document.getElementById("Es_id").value;	//小区主键
	if(es_id==""){
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	var Qstring="Es_id="+es_id;
	sendPOST(url,Qstring);
}
//报修信息
function selRepairInf(divid,url)
{
	currentPos=divid;
	var es_id= document.getElementById("Es_id").value;	//小区主键
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
    var Type=document.getElementById("Type").value;
	if(es_id==""){
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var Qstring="Es_id="+es_id+"&timesk="+timesk+"&timesj="+timesj+"&Type="+Type;
	sendPOST(url,Qstring);
}
//投诉信息
function selTou(divid,url)
{
	currentPos=divid;
	var es_id= document.getElementById("Es_id").value;	//小区主键
    var tousuType=document.getElementById("tousuType").value;
    var timesk=document.getElementById("timesk").value;
    var timesj=document.getElementById("timesj").value;
	if(es_id==""){
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var Qstring="Es_id="+es_id+"&tousuType="+tousuType+"&timesk="+timesk+"&timesj="+timesj;
	sendPOST(url,Qstring);
}
//问卷设置查询
function selMytp(divid,url){
	currentPos=divid;
	var es_id= document.getElementById("Es_id").value;	//小区主键
	var Type=document.getElementById("Type").value; //区分满意度和投票
	var title=document.getElementById("title").value; 
    var timesk=document.getElementById("timesk").value;
    var status=document.getElementById("status").value; 
	if(es_id==""){ 
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	var Qstring="Es_id="+es_id+"&Type="+Type+"&timesk="+timesk+"&status="+status+"&title="+title;
	sendPOST(url,Qstring);
}
//人工现金退款
function update_orders_rgtk(url,url2,ts_id,returndivid,i){
	URL2=url2;
	OBJ=returndivid;
	var Eo_id=document.getElementById("Eo_id"+i+"").value;
	
	var total_sj = document.getElementById("total_sj"+i) .value;
	var pay_status = document.getElementById("pay_status"+i) .value;
	if(total_sj<="0.00"||total_sj==""||total_sj==null){
		alert("请核实实缴金额是否有误！");
		return false;
	}else{
		if(pay_status!="1"){
			alert("请检查订单状态是否已支付！");
			return false;
		}else{
			if(confirm("确定要退款"+total_sj+"吗？")) {
				
				var QString="ts_id="+ts_id+"&Eo_id="+Eo_id+"&total_sj="+total_sj;
				sendPOST(url,QString);
			} else {
				return false;
			}
		}
	}
}


//人工收费
function update_orders_rgtk3(ID,width,height,url,i,ts_id){
	var userInfo =document.getElementById(ID);
	var x1 =0; 
	var y1 =70;
	userInfo.style.position = "absolute";
	userInfo.style.left = x1 + "px";
	userInfo.style.top = y1 + "px";
	userInfo.style.width ='100%';
	userInfo.style.height = height+"px";
	userInfo.style.display="block";
	currentPos=ID;
	var Eo_id=document.getElementById("Eo_id"+i+"").value;
	var Bu_id=document.getElementById("Bu_id"+i+"").value;
	var total=document.getElementById("total"+i+"").value;
	var total_znj=document.getElementById("total_znj"+i+"").value;
	var pay_status = document.getElementById("pay_status"+i) .value;
	var total_sj = document.getElementById("total_sj"+i).value;
	
	if(total_sj<="0.00"||total_sj==""||total_sj==null){
		alert("请核实实缴金额是否有误！");
		return false;
	}else{
		if(pay_status!="1"){
			alert("请检查订单状态是否已支付！");
			return false;
		}else{
			var QString="ts_id="+ts_id+"&Eo_id="+Eo_id+"&total="+total+"&total_znj="+total_znj+"&Bu_id="+Bu_id+"&pay_status="+pay_status+"&total="+total+"&total_sj="+total_sj;
			sendPOST(url,QString);
		}
	}
	
}


//人工现金退款
function update_orders_rgtk2(url,url2,returndivid){
	URL2=url2;
	OBJ=returndivid;
	var Eo_id=document.getElementById("Eo_id").value;
	var ts_id=document.getElementById("ts_id").value;
	var total_sj = document.getElementById("total_sj").value;
	var pay_status = document.getElementById("pay_status").value;
	if(total_sj<="0.00"||total_sj==""||total_sj==null){
		alert("请核实实缴金额是否有误！");
		return false;
	}else{
		if(pay_status!="1"){
			alert("请检查订单状态是否已支付！");
			return false;
		}else{
			var QString="ts_id="+ts_id+"&Eo_id="+Eo_id+"&total_sj="+total_sj;
			sendPOST(url,QString);
		}
	}
}






//(打印查询)
function select_print_orders(divid,url,ts_id){
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var Bu_id=document.getElementById("Bu_id").value;
	var Un_id=document.getElementById("Un_id").value;
	var EhNumber=document.getElementById("EhNumber").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	var payStatus=document.getElementById("payStatus").value;
	var payType=document.getElementById("payType").value;
	var OwnerName=document.getElementById("OwnerName").value;
	
	if(Es_id==""){
		 alert("请选择小区");
	     document.getElementById("Es_id").focus();
	     return false;
	}
	if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="ts_id="+ts_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id+"&EhNumber="+EhNumber+"&timesk="+timesk+"&timesj="+timesj+"&payStatus="+payStatus+"&payType="+payType+"&OwnerName="+OwnerName;
	sendPOST(url,QString);
}
//修改满意度投票设置
function ExamineUpdate(ex_id,url,url2,divid,closedivid)
{
	
	OBJ=divid;
	URL2=url2;
	DIV_ID=closedivid;
	var Es_id=document.getElementById("es_id2").value;
	var title=trim(document.getElementById("title2").value);
	var description=document.getElementById("description2").value;
	var Type=document.getElementById("Type2").value; //区分满意度和投票
	var status=document.getElementById("status2").value;
	var counts=document.getElementById("counts2").value;
	var start_time=document.getElementById("start_time2").value;
	var end_time=document.getElementById("end_time2").value;
	var remark=document.getElementById("remark2").value;
	if(Es_id==""){
		 alert("请选择小区");
	     document.getElementById("es_id2").focus();
	     return false;
	}
	if(title==""){  
		 alert("请输入标题");
	     document.getElementById("title2").focus();
	     return false;
	}
	if(title!=""){
		if(!regstrSY.test(title))
		{
			alert("标题格式不正确，请重新输入!");
			document.getElementById("title2").focus();
			return false;
		}
	}	
	if(Type==""){
		 alert("请选择类别");
	     document.getElementById("Type2").focus();
	     return false;
	}
	if(status==""){
		 alert("请选择状态");
	     document.getElementById("status2").focus();
	     return false;
	}
	if(counts!=""){
		if(!reghow.test(counts))
		{
			alert("参与次数只能为数字!");
			document.getElementById("counts2").focus();
			return false;
		}
	}
	if(start_time=="" || start_time==null){
		alert("请填写开始时间！");
		document.getElementById("start_time2").focus();
		return false;
	}
	if(end_time=="" || end_time==null){
		alert("请填写结束时间！");
		document.getElementById("end_time2").focus();
		return false;
	}
	if(start_time!=="" && end_time!==""){
		if(start_time>end_time){
			alert("开始时间不能大于结束时间！");
			document.getElementById("start_time2").focus();
			return false;
		}
  } 
	var Qstring="Es_id="+Es_id+"&title="+title+"&description="+description+"&Type="+Type+"&status="+status+"&counts="+counts+"&start_time="+start_time+"&end_time="+end_time+"&remark="+remark+"&ex_id="+ex_id;
	sendPOST(url,Qstring);
	
}
//添加满意度投票设置
function ExamineAdd(divid,url,url2)
{
	OBJ="showmain";
	currentPos=divid;
	URL2=url2;
	var Es_id=document.getElementById("es_id").value;
	var title=trim(document.getElementById("title1").value);
	var description=document.getElementById("description").value;
	var Type=document.getElementById("Type1").value; //区分满意度和投票
	var status=document.getElementById("status1").value;
	var counts=document.getElementById("counts").value;
	var start_time=document.getElementById("start_time").value;
	var end_time=document.getElementById("end_time").value;
	var remark=document.getElementById("remark").value;
	if(Es_id==""){
		 alert("请选择小区");
	     document.getElementById("es_id").focus();
	     return false;
	}
	if(title==""){  
		 alert("请输入标题");
	     document.getElementById("title1").focus();
	     return false;
	}
	if(title!=""){
		if(!regstrSY.test(title))
		{
			alert("标题格式不正确，请重新输入!");
			document.getElementById("title1").focus();
			return false;
		}
	}	
	if(description!=""){
		if(!regstrSY.test(description))
		{
			alert("说明格式不正确，请重新输入!");
			document.getElementById("description").focus();
			return false;
		}
	}
	if(Type==""){
		 alert("请选择类别");
	     document.getElementById("Type1").focus();
	     return false;
	}
	if(status==""){
		 alert("请选择状态");
	     document.getElementById("status1").focus();
	     return false;
	}
	if(counts!=""){
		if(!reghow.test(counts))
		{
			alert("参与次数只能为数字!");
			document.getElementById("counts").focus();
			return false;
		}
	}
	if(start_time=="" || start_time==null){
		alert("请填写开始时间！");
		document.getElementById("start_time").focus();
		return false;
	}
	if(end_time=="" || end_time==null){
		alert("请填写结束时间！");
		document.getElementById("end_time").focus();
		return false;
	}
	if(start_time!=="" && end_time!==""){
		if(start_time>end_time){
			alert("开始时间不能大于结束时间！");
			document.getElementById("start_time").focus();
			return false;
		}
   } 
	var QString="Es_id="+Es_id+"&title="+title+"&description="+description+"&Type="+Type+"&status="+status+"&counts="+counts+"&start_time="+start_time+"&end_time="+end_time+"&remark="+remark;
	sendPOST(url,QString);
}

/*****添加题目**********/
//第二个参数是servlet地址  第三个参数是返回jsp地址
function TBExtopic_add(divid,url,url2)
{
	OBJ="showmain";
	currentPos=divid;
	URL2=url2;
	
	var xq = document.getElementById("Es_ida");//小区
	var type = document.getElementById("wenjuanTypea");//问卷类型
	var wenjuana = document.getElementById("wenjuana");//问卷类型
	
	var wenjuanId = document.getElementById("wenjuana").value;	//问卷id
	var TopicName = document.getElementById("TopicName").value;	//题目名称
	var TopicIfTw = document.getElementById("TopicIfTw").value;	//题目是否图文
	
	var TopicType = document.getElementById("TopicType").value;	//题目类别
	var TopicSort = document.getElementById("TopicSort").value;	//题目排序
	
	
	if(xq.selectedIndex == null || xq.selectedIndex==""){
		alert("请选择小区");
		return false;
	}
	if(type.selectedIndex == null || type.selectedIndex==""){
		alert("请选择问卷类型");
		return false;
	}
	if(wenjuana.selectedIndex == null || wenjuana.selectedIndex==""){
		alert("请选择问卷");
		return false;
	}
	
	var regstrSY1= /^[0-9]*$/;
	if(TopicIfTw!=0){
		if(TopicName==""||TopicName==null)
		{
			alert("题目名称不能为空！");
			document.getElementById("TopicName").focus();
			return false;
		}
	}
	if(!regstrSY1.test(TopicSort))
	{
		alert("排序值应为正整数!");
		document.getElementById("TopicSort").focus();
		return false;
	}
	
	
	//var Qstring="EsName="+EsName+"&EsHead="+EsHead+"&EsContact="+EsContact+"&EsPhone="+EsPhone+"&EsAddress="+EsAddress+"&build_Number="+build_Number+"&House_Number="+House_Number+"&BuildArea="+BuildArea+"&FloorArea="+FloorArea+"&GreenArea="+GreenArea+"&VolumeRate="+VolumeRate+"&remark="+remark;
	
	var Qstring = "TopicSort="+TopicSort+"&TopicName="+TopicName+"&TopicIfTw="+TopicIfTw+"&TopicType="+TopicType+"&wenjuanId="+wenjuanId;
	//提交图片
	//判断是不是图文
	if(TopicIfTw!=2){
		var TopicImg= document.getElementById('TopicImg').files[0];//获取图片流
		
		if(TopicImg==null||TopicImg==""){
			alert("请选择要上传的图片");
			return false;
		}
		var ImgName = document.getElementById('TopicImg').files[0].name;//获取图片名
		
		//判断文件类型
		var index= ImgName.lastIndexOf("."); //（考虑严谨用lastIndexOf(".")得到）得到"."在第几位
		var img_id=ImgName.substring(index); //截断"."之前的，得到后缀
		if(img_id!=".bmp"&&img_id!=".png"&&img_id!=".gif"&&img_id!=".jpg"&&img_id!=".jpeg"){  //根据后缀，判断是否符合图片格式
		    alert("请选择图片格式文件"); 
		    document.getElementById('TopicImg').value="";  // 不符合，就清除，重新选择
		    return false;
		}
		
		url = url.split("=")[0]+"=4&";
		sendPOSTIMG(url,Qstring,TopicImg);
		return;
	}
	//提交数据
	sendPOST(url,Qstring);
}

/*****查询问题列表******/
function select_TB_Extopic(divid,url)
{
	currentPos=divid;
	var xq = document.getElementById("Es_id");
	var type = document.getElementById("wenjuanType");
	
	var wenjuan = document.getElementById("wenjuan");
	var wenjuanId = document.getElementById("wenjuan").value;	//问卷id
	if(xq.selectedIndex == null || xq.selectedIndex == ""){
		alert("请选择小区");
		return;
	}
	if(type.selectedIndex == null || type.selectedIndex == ""){
		alert("请选择问卷类型");
		return;
	}
	if(wenjuan.selectedIndex == null || wenjuan.selectedIndex == ""){
		alert("请选择问卷");
		return;
	}
	
	var Qstring="wenjuanId="+wenjuanId;
	sendPOST(url,Qstring);
}

/*******删除问题项***********/
function delete_TB_Extopic(to_id,i,url,returndivid,url2)
{
	if(confirm("您正在进行删除题目的操作，确定要删除吗？"))
	{

				
		OBJ=returndivid;
		//currentPos=returndivid;
		URL2=url2;
		
		
		var Qstring="to_id="+to_id;
		
		sendPOST(url,Qstring);
	}
}

/*****修改题目信息******/
function update_TB_Extopic(to_id,i,url,returndivid,url2)
{
	OBJ=returndivid;
	URL2=url2;
	
	var xiugaiIfwg = document.getElementById("xiugaiIfwg"+i).value;	//是否是图文
	var xiugaiTopicName = document.getElementById("xiugaiTopicName"+i).value;	//题目名称
	var xiugaiType = document.getElementById("xiugaiType"+i).value;	//类型
	var xiugaisort = document.getElementById("xiugaisort"+i).value;	//排序
	
	var regstrSY1= /^[0-9]*$/;
	
	
	if(xiugaiIfwg != 0){
		if(xiugaiTopicName==""||xiugaiTopicName==null)
		{
			alert("题目名称不能为空！");
			document.getElementById("xiugaiTopicName").focus();
			return false;
		}
	}
	if(xiugaisort!="" && xiugaisort!=null){
		if(!regstrSY1.test(xiugaisort))
		{
			alert("排序必须为正整数!");
			document.getElementById("EsHead").focus();
			return false;
		}
	}
	var Qstring="xiugaiIfwg="+xiugaiIfwg+"&xiugaiTopicName="+xiugaiTopicName+"&xiugaiType="+xiugaiType+"&xiugaisort="+xiugaisort+"&to_id="+to_id;
	//判断是不是纯文字信息
	if(xiugaiIfwg != 2 && document.getElementById('xiugaiImg'+i).files[0]){
		var xiugaiImg= document.getElementById('xiugaiImg'+i).files[0];//获取图片流
		
		var ImgName = document.getElementById('xiugaiImg'+i).files[0].name;//获取图片名
		
		if(xiugaiImg != "" || xiugaiImg!= null){
			//判断文件类型
			var index= ImgName.lastIndexOf("."); //（考虑严谨用lastIndexOf(".")得到）得到"."在第几位
			var img_id=ImgName.substring(index); //截断"."之前的，得到后缀
			if(img_id!=".bmp"&&img_id!=".png"&&img_id!=".gif"&&img_id!=".jpg"&&img_id!=".jpeg"){  //根据后缀，判断是否符合图片格式
			    alert("请选择图片格式文件"); 
			    document.getElementById('xiugaiImg').value="";  // 不符合，就清除，重新选择
			    return false;
			}
			url = url.split("=")[0]+"=5&";
			sendPOSTIMG(url,Qstring,xiugaiImg);
			return;
		}
	}
	
	sendPOST(url,Qstring);
}

/********发送图片请求请求*********************/
function sendPOSTIMG(url,QString,ImgObj) {
	show_Loading_div();//调用打开loading提交等待图片的层
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
	
	var formData = new FormData();
	var http_request = new XMLHttpRequest();
	formData.append('images', ImgObj);
	http_request.open("POST",url+QString);
	
	http_request.withCredentials = true;
  
	http_request.send(formData);
	http_request.onload = function(){
		if (http_request.readyState == 4) { // 判断对象状态
	        if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
	        	close_loading_div();//调用关闭loading提示图片的层
			    res_txt=http_request.responseText.split('-'); //获得返回的数据进行截取
				var temp=trim(res_txt[0]);  //temp得到去掉空格后的字符串			 
	            var PrintDescription=res_txt[1];  //temp得到去掉空格后的字符串
	            
	            if(temp=="sessionOvertime")
	            {
	            	window.location=trim(res_txt[2]);
	            }      
	            else if(temp=="errors") //返回失败信息调用
	            {
	                alert(PrintDescription);
	            }     
	            else if(temp=="ok")	//成功类似的不需刷新页面的调用，如：添加返回主键冲突时也可调用
	            {
	                alert(PrintDescription);
	            }
	            else if(temp=="sok")//操作成功，并且传入新的DIV层ID显示的页面调用
	            {
	                alert(PrintDescription);
	                showselect(returnDIVID,returnURL);
	            } 
	            else if(temp=="ssok")//操作成功，并且传入新的DIV层ID显示的页面调用
	            {
	                alert(PrintDescription);
	                showselect(OBJ,URL2);
	            }    
	            else if(temp=="csok")//操作成功，关闭层。
	            {
	                alert(PrintDescription);      
	                close_tanchu_div(DIV_ID);
	            }
	            else if(temp=="cssok")//操作成功，并且传入新的DIV层ID显示的页面调用,并且关闭层。
	            {
	                alert(PrintDescription);                
	                showselect(OBJ,URL2);
	                close_tanchu_div(DIV_ID);
	            }
	            else if(temp=="uok")//适用于成功后不弹出提示框，如登录操作成功，论坛发布帖子，直接调用显示成功后的页面。
	            {
	                showselect(returnDIVID,returnURL);
	            }                                     
	            else if(temp=="cok")//操作成功，不弹出提示框，直接调用显示成功后的页面,并关闭某个DIV层的方法。
	            {
	                showselect(returnDIVID,returnURL);
	                close_div(closeDIVID);
	            }    
	            else if(temp=="epok")//设置绑定手机或邮箱,并关闭某个DIV层的方法。
	            {
	                
	                document.getElementById(returnDIVID).innerHTML =PrintDescription;
	                close_div(closeDIVID);
	            }              
	            else if(temp=="lok")//适用于成功后不弹出提示框，如登录操作成功，直接调用显示成功后的页面。
	            {
	            	window.location=returnURL;
	            }             
	            else if(temp=="regok")//适用于成功后不弹出提示框，如登录操作成功直接调用显示成功后的页面。
	            {
	            	window.location=PrintDescription;
	            }          
	            else if(temp=="zfjs")//缴费项目可以选择时，选择或取消选择时，计算优惠金额和支付金额，并返回到指定位置显示
	            {          	
	            	document.getElementById(zje_id).value=trim(PrintDescription);
	            	document.getElementById(yhje_id).innerHTML = res_txt[2];
	            	document.getElementById(zfje_id).value=trim(res_txt[3]);
	            }     
	            else if(temp=="dcbbok")//操作成功，不显示提示信息直接刷新页面
	            {
	            	alert(PrintDescription);
	            	document.getElementById(BUTTONID).disabled=false;
	            }
	            else if(temp=="xok")//操作成功，并且传入新的DIV层ID显示的页面调用
	            {
	            	 alert(PrintDescription);
	                 menu_shua(URL2);
	            } 
	            else
	            {
					if(currentPos1!=null){
	            		document.getElementById(currentPos1).innerHTML = http_request.responseText;
	            		currentPos1=null;
	            	}else{
	            		document.getElementById(currentPos).innerHTML = http_request.responseText;
	            	}
				}
	       			
	        } else { //页面不正常
	        	//  alert("您所请求的页面有异常。"+http_request.status);
	        	close_loading_div();//调用关闭loading提示图片的层
	        }
		}
	};

}

/*****题目update下拉框******/
function wjtmchange(xiugaiTopicName1,xiugaiImg1,obj,i){
	
	var xiugaiTopicName = document.getElementById(xiugaiTopicName1+i);
	var xiugaiImg = document.getElementById(xiugaiImg1+i);
	var index = obj.selectedIndex;
	if(obj.options[index].value == 0){
		//只图片
		//xiugaiTopicName.style.display = "none";
		//xiugaiImg.style.display = "block";
	}else if(obj.options[index].value == 1){
		//图文
		xiugaiTopicName.style.display = "block";
		xiugaiImg.style.display = "block";
	}else{
		//文字
		xiugaiTopicName.style.display = "block";
		xiugaiImg.style.display = "none";
	}
}
/**********题目添加下拉框************/
function wjtmchange2(TopicName1,nameTd1,Img1,imgTd1,obj){
	
	var xiugaiTopicName = document.getElementById(TopicName1);
	var xiugaiImg = document.getElementById(Img1);
	var nameTd = document.getElementById(nameTd1);
	var imgTd = document.getElementById(imgTd1);
	var index = obj.selectedIndex;
	//myselect.options[index].value
	if(obj.options[index].value == 0){
		//只图片
		//xiugaiTopicName.style.display = "none";
		//nameTd.style.display="none";
		
		//xiugaiImg.style.display = "table-cell";
		//imgTd.style.display = "table-cell";
	}else if(obj.options[index].value == 1){
		//图文
		xiugaiTopicName.style.display = "table-cell";
		nameTd.style.display="table-cell";
		
		xiugaiImg.style.display = "table-cell";
		imgTd.style.display = "table-cell";
	}else{
		//文字
		xiugaiTopicName.style.display = "table-cell";
		nameTd.style.display="table-cell";
		
		xiugaiImg.style.display = "none";
		imgTd.style.display = "none";
	}
}
/*********获取问卷**************/
function getWenJuan(xqId,typeId,wenjuanId,url){
	var xq = document.getElementById(xqId);
	var type = document.getElementById(typeId);
	var wenjuan = document.getElementById(wenjuanId);
	
	
	
	//如果小区和类型  有一个不选择的话就不执行
	if(xq.selectedIndex == 0 || type.selectedIndex == 0){
		return;
	}
	
	//清除问卷下所有内容
	$(wenjuan).empty();
	//获取小区id
	xqIndexId = xq.options[xq.selectedIndex].value;
	typeIndexId = type.options[type.selectedIndex].value;
	//获取类型id
	var QString = "?xqId="+xqIndexId+"&typeId="+typeIndexId;
	$.ajax({
	    type: "GET",
	    dataType: "json",
	    url: url+QString,
		success: function(data){
			//成功封装到问卷下
			$(wenjuan).append("<option value=''>--请选择问卷表--</option>");
			for(var dataIndex in data){
				$(wenjuan).append("<option value='"+data[dataIndex].value+"'>"+data[dataIndex].name+"</option>");
			}
		},
		error: function(msg){
			alert("获取问卷失败，请稍后重试或联系管理员");
		}
	});
}


/******宋波*********/
//选项管理
function selExoption(divid,url)
{
  currentPos=divid;
  var to_id= document.getElementById("To_id").value;
  if(to_id==""){
    alert("请选择题目!");
    document.getElementById("to_id").focus();
    return false;
  }
  var Qstring="To_id="+to_id;
  sendPOST(url,Qstring);
}

//联动查询问卷
function select_Examine(th,divid,url,ts_id){
	currentPos=divid;
	var xq_id=th.value;
	//var sign = document.getElementById("sign").value;
	//var QString="xq_id="+xq_id+"&ts_id="+ts_id+"&sign="+sign;
	var QString="xq_id="+xq_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
}
//联动查询题目
function select_Extopic(th,divid,url,ts_id){
	currentPos=divid;
	var wj_id=th.value;
	//var sign = document.getElementById("sign").value;
	//var QString="xq_id="+xq_id+"&ts_id="+ts_id+"&sign="+sign;
	var QString="wj_id="+wj_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
}

//添加选项
function insert_Exoption(divid,url)
{
	var userInfo =document.getElementById(divid);
    var x1 =0; 
    var y1 =70; 
    userInfo.style.position = "absolute";
    userInfo.style.left = x1 + "px";
    userInfo.style.top = y1 + "px";
    userInfo.style.display="block";  
    
    currentPos=divid;
    var Qstring="";
  sendPOST(url,Qstring);
}
//修改选项
function Update_Exoption2(ID,url,op_id){
  
   var userInfo =document.getElementById(ID);
     var x1 =0; 
     var y1 =70; 
     userInfo.style.position = "absolute";
     userInfo.style.left = x1 + "px";
     userInfo.style.top = y1 + "px";
     userInfo.style.display="block";  
     
     currentPos=ID;
  
  var Qstring="op_id="+op_id;
  sendPOST(url,Qstring);
}

//修改选项
function Update_Exoption(){
   var if_tw=document.getElementById("if_tw").value;
   var optionName=document.getElementById("optionName").value;
   var InfImage=document.getElementById("InfImage").value;
   if(if_tw==""){
     alert("请选择选项类型");
     return false; 
   }
   if(if_tw == "2" || if_tw == "1"){
	   if(optionName==""){
		     alert("请输入选项名称");
		     return false; 
		   }  
   }
   if(if_tw == "0" || if_tw == "1"){
	  if(InfImage == ""){
		  alert("请上传图片");
		    return false;  
	  } 
   }
  
}
/*上传图片显示缩略图*/
function previewImg(file) {
    var reader = new FileReader();
    reader.readAsDataURL(file.files[0]);
    var image = $("<img>");
    reader.onload=function(e){
        image.attr("src",e.target.result);
        $(file).next(".imgScale").append(image.clone());
    }
}
/* 删除选项 */
function deleteExoption(to_id, op_id, OptionNam, url, url2, divid) {
  
  if(confirm("确定删除'"+OptionNam+"'这个选项吗？"))
  {  
    OBJ=divid;
    URL2=url2;
    //session.setAttribute("To_id",to_id);
    //var Qstring="Bu_id="+bu_id;
    var QString = "op_id=" +op_id+"&To_id="+to_id;
    sendPOST(url,QString);
  }
}
 function divDisplay(val){
	 if(val == 0 || val == 1){
		 $("#localImag").show();
	 }else{
		 $("#localImag").hide();
	 }
 }



/*********张丽君*************/
//问卷记录信息
function selWJ(divid,url)
{
	//alert(url);
	currentPos=divid;
	var es_id= document.getElementById("Es_id").value;	//小区主键
    var type=document.getElementById("type").value;
    var ex_id=document.getElementById("ex_id").value;
   /* var timesk=document.getElementById("timesk").value;
    var timesj=document.getElementById("timesj").value;*/
	if(es_id==""){
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	if(type==""){
		alert("请选择类型!");
		document.getElementById("Es_id").focus();
		return false;
	}
	if(ex_id==""){
		alert("请选择标题名称!");
		document.getElementById("Es_id").focus();
		return false;
	}
	
	var Qstring="Es_id="+es_id+"&type1="+type+"&ex_id="+ex_id;
	sendPOST(url,Qstring);
}


/*弹出层的方法*/
function new_show_see_tanchu_div123(ID,width,height,url,ex_id,Eh_id,selectTime) {
	
	//最佳答案 屏幕分辨率的高：window.screen.height 
	//屏幕分辨率的宽：window.screen.width 
	//屏幕可用工作区高度：window.screen.availHeight 
	//屏幕可用工作区宽度：window.screen.availWidth 
	//获得鼠标点击的X坐标 var x1 = event.clientX+10;
	//获得鼠标点击的Y坐标 var y1 = event.clientY+5;
	//获得鼠标点击的绝对X坐标 var x1 = event.clientX + document.body.scrollLeft - document.body.clientLeft;
	//获得鼠标点击的绝对Y坐标 var y1 = event.clientY +document.body.scrollTop - document.body.clientTop - 150 ;
	var userInfo =document.getElementById(ID);
	var x1 =0;//(window.screen.availWidth-width)/2-7;
	var y1 =70;//event.clientY-0;//document.body.scrollTop+150;
	//var userInfo =document.getElementById(ID);
	//var x1 = (window.screen.availWidth-width)/2+25;
	//var y1 = document.body.scrollTop+150;
	userInfo.style.position = "absolute";
	userInfo.style.left = x1 + "px";
	userInfo.style.top = y1 + "px";
	userInfo.style.width ='100%';
	userInfo.style.height = height+"px";
	userInfo.style.display="block";
	currentPos=ID;
	selectTime=selectTime.replace(" ","_");
//alert(selectTime);
	var Qstring="divid="+ID+"&ex_id="+ex_id+"&eh_id="+Eh_id+"&selectTime="+selectTime;
	//alert(Qstring);
	sendPOST(url,Qstring);
}

function selectWJ(url){
	
	 $("#ex_id").empty();
     $("#ex_id").append("<option value=''>请选择问卷标题</option>");
	//var $ex_id=$("#ex_id");
	var Es_id=$("#Es_id").val();
	var type=$("#type").val();

	if(Es_id==""){
		alert("请选择小区");
		return false;
	}
	if(type==""){
		alert("请选择类型");
		return false;
	}
	//alert(type);
	//console.log(Es_id);
    //if(id){
       // console.log("有id的获取执行了");
        $.ajax({
            type:"post",
            url:url+"?Es_id="+Es_id+"&type="+type,
            async:true,
            success:function(data){
                for(a in data){
                	$("#ex_id").append("<option value='"+data[a].ex_id+"'>"+data[a].title+"</option>");
                }
            }
        });
     //}
}

function checkWJ(){
	var Es_id=$("#Es_id").val();
	var type=$("#type").val();
	if(Es_id==""||type==""){
		alert("请重新选择小区和类型");
		return false;
	}
}

function getWJ(url){
	  //var $ex_id=$("#ex_id");
	  var Es_id=$("#Es_id").val();
	  var type=$("#type").val();

	  if(type!=""){
	     $("#ex_id").empty();
	       $("#ex_id").append("<option value=''>请选择问卷标题</option>");
	  
	       // console.log("有id的获取执行了");
	        $.ajax({
	            type:"post",
	            url:url+"?Es_id="+Es_id+"&type="+type,
	            async:true,
	            success:function(data){
	                for(a in data){
	                  $("#ex_id").append("<option value='"+data[a].ex_id+"'>"+data[a].title+"</option>");
	                }
	            }
	        });
	  }
}

//人工收费模块输入房屋编号，反显用户姓名
function select_EhName(th,divid,url,ts_id,divid2,url2){
	currentPos=divid;
	var ly_id=th.value;
	var Es_id= document.getElementById("Es_id").value;	//小区主键
	var Bu_id = document.getElementById("Bu_id").value;	//楼宇主键
	var Un_id = "";
	if(document.getElementById("Un_id")!=null&&document.getElementById("Un_id")!=""){
		Un_id = document.getElementById("Un_id").value; //单元主键
	}
	
	if(ly_id=="")
	{
	   alert("请输入房屋编号！");
	   return false;
	} 
	if(Es_id=="")
	{
	   alert("请选择小区！");
	   return false;
	} 
	/*if(Bu_id=="")
	{
	   alert("请选择楼宇！");
	   return false;
	} */
	
	var QString="ly_id="+ly_id+"&ts_id="+ts_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id;
	sendPOST(url,QString);
	setTimeout(function (){
	 sel_itemType(divid2,url2,ly_id,Es_id,Bu_id,Un_id);
	}, 300);
}

//批量人工收费
function update_orders_plsf(ID,width,height,url,checkname,ts_id) {
	var count=0;
	var sqlstr="";
	var znj_sum = "";
	var checkid=new Array();
	var total_znj = new Array();
	
	var j=document.getElementsByName(checkname).length;//document.sglform.fuwuqi.length;
	var k=0;
	var Eh_id = "";
	var EHID = "";
	for( var i=0;i<j;i++){
		if(document.getElementsByName(checkname)[i].checked==true)
		{
			checkid[k]=document.getElementsByName(checkname)[i].value;
			total_znj[k]=document.getElementsByName("total_znj")[i].value;
			if(Eh_id==""){
				Eh_id = document.getElementsByName("Eh_id")[i].value;
			}else{
				if(Eh_id==document.getElementsByName("Eh_id")[i].value){
					Eh_id = document.getElementsByName("Eh_id")[i].value;
				}else{
					EHID = document.getElementsByName("Eh_id")[i].value;
				}
			}
			k++;
		}
	}
	

	
	if(k>0){
		if(EHID==""){
			if(checkid.length>0) {
				for(var i=0;i<checkid.length;i++) {
					var str=checkid[i];
					var msg = total_znj[i];
					
					if(str.length>0) {
						count++;
						sqlstr+=str+";";
					}
					if(msg.length>0) {
						count++;
						znj_sum+=msg+";";
					}
				}
				//console.log(sqlstr);
				//------ 获取选中得滞纳金
				
				/*for(var i = 0 ; i<checkid.lenth ; i++){
					var total_znj=document.getElementById("total_znj"+i+"").value;
					if(total_znj != ""){
						znj_sum+=total_znj+";";
					}
					
				}*/
				
				//console.log(znj_sum);

				//------
			}
			var userInfo =document.getElementById(ID);
			var x1 =0; 
			var y1 =70;
			userInfo.style.position = "absolute";
			userInfo.style.left = x1 + "px";
			userInfo.style.top = y1 + "px";
			userInfo.style.width ='100%';
			userInfo.style.height = height+"px";
			userInfo.style.display="block";
			currentPos=ID;
			var QString="ts_id="+ts_id+"&sqlstr="+sqlstr+"&znj_sum="+znj_sum;
			sendPOST(url,QString);
		}else{
			alert("您只能选择同一个人的订单");
			return false;
		}
		
	
	}else{
		alert("请选择您要缴费的数据");
		return false;
	}
}

function add_orderBS(url3,url,url2,returndivid)
{	
	URL2=url2;
	OBJ=returndivid;
	var bankid= document.getElementById("bankid").value;
	var ts_id= document.getElementById("ts_id").value;
	var Es_id= document.getElementById("Es_id").value;	//小区主键
	var Bu_id = document.getElementById("Bu_id").value;	//楼宇主键
	var EhNumber = document.getElementById("EhNumber").value;
	var payItem = document.getElementById("payItem").value;
	if(Es_id==""){
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	if(Bu_id==""){
		alert("请选择楼宇!");
		document.getElementById("Bu_id").focus();
		return false;
	}
	var Un_id="" ; //单元主键
	if(document.getElementById("Un_id")!=null){
		Un_id = document.getElementById("Un_id").value; //单元主键
	}
	if(EhNumber==""){
		alert("请输入房屋编号!");
		document.getElementById("EhNumber").focus();
		return false;
	}
	if(payItem==""){
		alert("请选择收费项!");
		document.getElementById("payItem").focus();
		return false;
	}
	var shuju = document.getElementById("shuju").value;//限购设置
	var total =0;
	if(shuju==""){
		total = document.getElementById("total").value;
	}else{
		var limited = document.getElementById("limited").value;
		if(limited==""){
			total = document.getElementById("total").value;
		}else{
			total = getradio("total");
		}
		
	}
	if(total=="" ||total=="0"||total=="0.0"||total=="0.00"){
		alert("缴费金额不能为空!");
		return false;
	}
	if(!isPrice.test(total))
	{
		alert("缴费金额格式错误，最多保留两位小数！");
		return false;		
	}	
	if(document.getElementById("HouseStandard")!=null){
		var fwmj=document.getElementById("fwmj").value; 
		var HouseStandard=document.getElementById("HouseStandard").value; //房屋面积标准
		var BelowStandard=document.getElementById("BelowStandard").value; //低于标准
		var AboveStandard=document.getElementById("AboveStandard").value; //高于标准
		if(HouseStandard!="0"){  //设置房屋标准时进行判断
			if(parseInt(fwmj)<=parseInt(HouseStandard)){  //房屋面积小于等于标准
				if(parseInt(total)>parseInt(BelowStandard)){  //如果金额大于最高限制
					alert("抱歉，金额不能超过低于标准的最高限额!");
					return false;
				}
			}else{  //房屋面积大于标准
				if(parseInt(total)>parseInt(AboveStandard)){  //如果金额大于最高限制
					alert("抱歉，金额不能超过高于标准的最高限额!");
					return false;
				}
			}
		};
	}
	var isYjLimited = document.getElementById("isYjLimited").value;//是否开启预缴
	if(isYjLimited=="0"||isYjLimited=="null"){
		alert("抱歉，该小区暂未开放预缴收费!");
		return false;
	}else{
		var QString = "Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id+"&EhNumber="+EhNumber+"&bankid="+bankid+"&ts_id="+ts_id+"&payItem="+payItem+"&total="+total;
		Qvalue=QString;
		sendPOST(url,QString);
	}
	
}
//全部删除的订单（未支付）
function TBOrder_qbsc(orderType,qxid,lyid,dyid,jfxid,url,url2,divid)
{
	
	if(qxid==""){
		alert("请选择小区");
		return false;
	}else{
		if(confirm("您正在执行删除操作，确定要删除全部订单吗？"))
		{	
			OBJ=divid;
			URL2=url2;
					
			var QString="qxid="+qxid+"&lyid="+lyid+"&dyid="+dyid+"&orderType="+orderType+"&jfxid="+jfxid;
			
			sendPOST(url,QString);
		}
	}
	
}
//人工收费模块根据房屋编号查询对应类型的预交费项
function sel_itemType(divid,url,fw_id,Es_id,Bu_id,Un_id){
	currentPos=divid;
	/*var fw_id=th.value;
	var Es_id= document.getElementById("Es_id").value;	//小区主键
	var Bu_id = document.getElementById("Bu_id").value;	//楼宇主键
	var Un_id = document.getElementById("Un_id").value; //单元主键
	*/
	if(fw_id=="")
	{
	   alert("请输入房屋编号！");
	   return false;
	} 
	if(Es_id=="")
	{
	   alert("请选择小区！");
	   return false;
	} 
	/*if(Bu_id=="")
	{
	   alert("请选择楼宇！");
	   return false;
	} */
	
	var QString="fw_id="+fw_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id;
	sendPOST(url,QString);
	
}
//联动查询小区负责人信息
function select_operator(th,divid,url,ts_id){
	
	currentPos=divid;
	var Es_id=th.value;
	var QString="Es_id="+Es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
}
//删除问卷投票标题
function TBExamine_delete(Ex_id,url,url2,divid)
{
	if(confirm("您正在执行删除操作，确定要删除吗？"))
	{	
		OBJ=divid;
		URL2=url2;
				
		var Qstring="Ex_id="+Ex_id;
		
		sendPOST(url,Qstring);
	}
}
//修改人工收费扫码盒子支付
function add_orderBS1(url3,url,url2,returndivid)
{	
	URL2=url2;
	OBJ=returndivid;
	//var bankid= document.getElementById("bankid").value;
	var ts_id= document.getElementById("ts_id").value;
	var Es_id= document.getElementById("Es_id").value;	//小区主键
	var Bu_id = document.getElementById("Bu_id").value;	//楼宇主键
	var EhNumber = document.getElementById("EhNumber").value;
	var payItem = document.getElementById("payItem").value;
	if(Es_id==""){
		alert("请选择小区!");
		document.getElementById("Es_id").focus();
		return false;
	}
	/*if(Bu_id==""){
		alert("请选择楼宇!");
		document.getElementById("Bu_id").focus();
		return false;
	}*/
	var Un_id="" ; //单元主键
	if(document.getElementById("Un_id")!=null){
		Un_id = document.getElementById("Un_id").value; //单元主键
	}
	if(EhNumber==""){
		alert("请输入房屋编号!");
		document.getElementById("EhNumber").focus();
		return false;
	}
	if(payItem==""){
		alert("请选择收费项!");
		document.getElementById("payItem").focus();
		return false;
	}
	var shuju = document.getElementById("shuju").value;//限购设置
	var total =0;
	var totalqt="";
	  if(shuju==""){
	    total = document.getElementById("total").value;
	  }else{
	    var limited = document.getElementById("limited").value;
	    if(limited==""){
	      total = document.getElementById("total").value;
	    }else{
	      total = getradio("total");
	      totalqt = document.getElementById("totalqt").value;
	    }
	    
	  }
	  if(totalqt==""){
	    if(total=="" ||total=="0"||total=="0.0"||total=="0.00"){
	      alert("缴费金额不能为空!");
	      return false;
	    }
	    if(!isPrice.test(total))
	    {
	      alert("缴费金额格式错误，最多保留两位小数！");
	      return false;    
	    }  
	  }
	  
	  if(totalqt!=""){
	    if(!isPrice.test(totalqt))
	    {
	      alert("其他金额格式错误");
	      return false;    
	    }else{
	      total=totalqt;
	    }  
	  }	
	if(document.getElementById("HouseStandard")!=null){
		var fwmj=document.getElementById("fwmj").value; 
		var HouseStandard=document.getElementById("HouseStandard").value; //房屋面积标准
		var BelowStandard=document.getElementById("BelowStandard").value; //低于标准
		var AboveStandard=document.getElementById("AboveStandard").value; //高于标准
		if(HouseStandard!="0"){  //设置房屋标准时进行判断
			if(parseInt(fwmj)<=parseInt(HouseStandard)){  //房屋面积小于等于标准
				if(parseInt(total)>parseInt(BelowStandard)){  //如果金额大于最高限制
					alert("抱歉，金额不能超过低于标准的最高限额!");
					return false;
				}
			}else{  //房屋面积大于标准
				if(parseInt(total)>parseInt(AboveStandard)){  //如果金额大于最高限制
					alert("抱歉，金额不能超过高于标准的最高限额!");
					return false;
				}
			}
		};
	}
	var lyid=document.getElementById("lyid").value; //查询楼宇信息
	if(lyid==""){
		alert("输入的房屋编号信息有误！");
		return false;	
	}
	var isYjLimited = document.getElementById("isYjLimited").value;//是否开启预缴
	if(isYjLimited=="0"||isYjLimited=="null"){
		alert("抱歉，该小区暂未开放预缴收费!");
		return false;
	}else{
		var QString = "Es_id="+Es_id+"&Bu_id="+Bu_id+"&Un_id="+Un_id+"&EhNumber="+EhNumber+"&ts_id="+ts_id+"&payItem="+payItem+"&total="+total+"&lyid="+lyid;
		//Qvalue=QString;
		//sendPOST(url,QString);
		$.ajax({
		    type: "post",
		    dataType: "json",
		    url: url,
		    data : QString,
			success: function(data){
				if (data.bj == "no") {//添加订单失败
					alert(data.msg);
				}else{
					currentPos=OBJ;
					Qvalue=QString+"&bankid="+data.bankid;
	            	sendPOST(URL2,Qvalue);
	            	setTimeout(function (){
	            		$("#btnbtn").click();
	            	}, 200); 	
				}
			}
		});
	}
	
}
/* 频道文章内容编辑页面，用于查询不同频道下的文章内容或修改调用 */
function TBCms_Article_update_show(width, height, divid, cms_a_id, cms_c_id,
		url) {
	currentPos = divid;
	// var QString=url+"?cms_a_id="+cms_a_id+"&cms_c_id="+cms_c_id;
	// send_request(QString);
	var QString = "cms_a_id=" + cms_a_id + "&cms_c_id=" + cms_c_id;
	sendPOST(url, QString);

	// show_tanchu_div(divid,width,height);
	// 最佳答案 屏幕分辨率的高：window.screen.height
	// 屏幕分辨率的宽：window.screen.width
	// 屏幕可用工作区高度：window.screen.availHeight
	// 屏幕可用工作区宽度：window.screen.availWidth
	// 获得鼠标点击的X坐标 var x1 = event.clientX+10;
	// 获得鼠标点击的Y坐标 var y1 = event.clientY+5;
	// 获得鼠标点击的绝对X坐标 var x1 = event.clientX + document.body.scrollLeft -
	// document.body.clientLeft;
	// 获得鼠标点击的绝对Y坐标 var y1 = event.clientY +document.body.scrollTop -
	// document.body.clientTop - 150 ;

	var userInfo = document.getElementById(divid);
	var x1 = 0;// (window.screen.availWidth-width)/2+25;
	var y1 = 90;// document.body.scrollTop+150;

	userInfo.style.position = "absolute";
	userInfo.style.left = x1 + "px";
	userInfo.style.top = y1 + "px";

	// userInfo.style.width = width+"px";
	// userInfo.style.height = height+"px";
	userInfo.style.display = "block";
}
/* 频道文章内容删除 */
function TBCms_Article_delete(cms_a_id, url, url2, returndivid) {
	if(confirm("确定要删除吗？")){
		URL2 = url2;
		OBJ = returndivid;
		var QString = "cms_a_id=" + cms_a_id;
		sendPOST(url, QString);
	}
}
/*频道文章内容发布*/
function TBCms_Article_add(selectName)
{
	var cms_c_id=document.getElementById("cms_c_id_add").value;
	var TITLE = Rep_str(trim(document.getElementById("TITLE").value));
	
	if(cms_c_id=="0")
	{
		alert("暂无频道！请先添加频道后再发布内容！");
		return false;
	}		
	if(TITLE=="")
	{
		alert("标题不能为空！");
		return false;
	}	
	var cms_a_source = Rep_str(trim(document.getElementById("cms_a_source").value));
	if(cms_a_source=="")
	{
		alert("来源不能为空！");
		return false;
	}		

}
/*频道文章内容修改*/
function TBCms_Article_update(selectName)
{

	var cms_c_id=document.getElementById("cms_c_id_update").value;
	var TITLE = Rep_str(trim(document.getElementById("TITLE").value));
	if(cms_c_id=="0")
	{
		alert("暂无频道！请先添加频道后再发布内容！");
		return false;
	}		
	if(TITLE=="")
	{
		alert("标题不能为空！");
		return false;
	}
	var cms_a_source = Rep_str(trim(document.getElementById("cms_a_source").value));
	if(cms_a_source=="")
	{
		alert("来源不能为空！");
		return false;
	}		
	document.uploadxinxi.submit();
}
//联动查询收费员
function select_sfy(th,divid,url,ts_id){
	currentPos=divid;
	var Es_id=th.value;
	var QString="Es_id="+Es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
}
//下年度预收统计
function select_naindu(divid,url,ts_id){
	currentPos=divid;
	var Es_id=document.getElementById("Es_id").value;
	var Bu_id=document.getElementById("Bu_id").value;
	var itemName=document.getElementById("itemName").value;
	var timesk=document.getElementById("timesk").value;
	var timesj=document.getElementById("timesj").value;
	if(Es_id==""){
		 alert("请选择小区");
	     document.getElementById("Es_id").focus();
	     return false;
	}
	if(!checkDate(timesk, timesj)){
		alert('起始时间不能大于结束时间！');
		return false;
	}
	var QString="ts_id="+ts_id+"&Es_id="+Es_id+"&Bu_id="+Bu_id+"&itemName="+itemName+"&timesk="+timesk+"&timesj="+timesj;
	sendPOST(url,QString);
}
function deletePrint(bankid,es_id){

	$.ajax({
		type : 'POST',
		url : "/WYManage/DeletePrintServlet",
		data : {"bankid" : bankid,"es_id" : es_id},
		dataType:"text",
		success : function(data) {
         alert(data);
         displayFlash('select_orders');
    	},
    	error : function() {
			alert("服务请求异常！");
		}
	});

}

function displayFlash(name){
	document.getElementById(name).contentWindow.location.reload(true);
}
//更新作废状态
function deletePrint1(url,url2,bankid,es_id,returndivid){
	URL2=url2;
	OBJ=returndivid;
	if(confirm("确定要作废吗？")) {
		var QString="bankid="+bankid+"&es_id="+es_id;
		sendPOST(url,QString);
	} else {
		return false;
	}
}
/************运城行添加*****************/
//联动查询小区房屋类型
function selHouType(th,divid,url){
	
	currentPos=divid;
	var Es_id=th.value;
	var QString="Es_id="+Es_id;
	sendPOST(url,QString);
}

//添加房屋类型
function addhostType(divid,url,url2){
	returnDIVID=divid;
	returnURL=url2;
	var Es_id= document.getElementById("Es_id").value;
	var ReName= trim(document.getElementById("ReName").value);
	
	if(Es_id=="" ||Es_id==null ){
		alert("请选择小区！");
		document.getElementById("Es_id").focus();
		return false;
	}
	if(ReName==""){
		alert("房屋类型不能为空！");
		document.getElementById("ReName").focus();
		return false;
	}else if(!regstrSY.test(ReName)){
		alert("房屋类型不能有特殊字符！");
		return false;
	}
	
	var QString = "&Es_id="+Es_id+"&ReName="+ReName;
	sendPOST(url, QString);
}

//添加房屋联动查询房屋类型信息
function select_EhNature(divid,url,ts_id,Es_id){
	currentPos=divid;
	var QString="Es_id="+Es_id+"&ts_id="+ts_id;
	sendPOST(url,QString);
}


/* 修改房屋类型信息 */
function updatehostType(Re_id, i, url, returndivid, url2) {
	returnDIVID = returndivid;
	returnURL = url2;
	var ReName = trim(document.getElementById("ReName"+i).value);
	var remark = trim(document.getElementById("remark"+i).value);
	if(ReName==""){
		alert("房屋类型不能为空！");
		document.getElementById("ReName").focus();
		return false;
	}else if(!regstrSY.test(ReName)){
		alert("房屋类型不能有特殊字符！");
		return false;
	}
	
	if(remark!=""){
		if(!regstrSY.test(remark)){
			alert("备注信息不能有特殊字符！");
			return false;
	    }
	}
	var QString = "Re_id=" + Re_id+"&ReName="+ReName+"&remark="+remark;
	sendPOST(url, QString);
}


function getEhType(es_id,ts_id){
	$.ajax({
		type : 'POST',
		url : "/YCWYManage/HouseTypeSeclect",
		data : {"Es_id" : es_id,"ts_id" : ts_id},
		dataType:"text",
		success : function(data) {
        $("#EhType").html(data);
    	},
    	error : function() {
			alert("房屋类型获取失败！");
		}
	});
}


function getEhType1(es_id,ts_id){
	$.ajax({
		type : 'POST',
		url : "/YCWYManage/HouseTypeSeclect",
		data : {"Es_id" : es_id,"ts_id" : ts_id},
		dataType:"text",
		success : function(data) {
        $("#EhType").html(data);
    	},
    	error : function() {
			alert("房屋类型获取失败！");
		}
	});
}


