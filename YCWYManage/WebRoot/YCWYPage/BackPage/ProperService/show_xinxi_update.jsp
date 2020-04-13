<%@page import="WYBack_Stage.Bean.TBCms_Article"%>
<%@page import="WYBack_Stage.Dao.TB_Village_Dao"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.util.List"/>
<%
   if(session.getAttribute("USER_ID")==null)
   {
   %>
<script language="javascript">
		alert("您的帐号在其他地方登录，或者您长时间没有操作！请重新登录！");
		window.location="login.jsp";
</script>
<%
}
else
{   
%>	
<html>
   <head>

	<!-- 文本编辑器 start -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/ckfinder/ckfinder.js"></script>
	<!-- 文本编辑器 start -->
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/YCWYPage/BackPage/js/index.js"></script>
	<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Managepage/zxschoolSev/css/content.css" id="cssfile"/>
 --%>
  </head>
  <body >          
  
<%
	String cms_category_id=request.getParameter("cms_category_id");
	
	String cms_c_id=request.getParameter("cms_c_id");
   	String cms_a_id=request.getParameter("cms_a_id");
   	TBCms_Article Article=new TB_Village_Dao().getArticle_index(cms_a_id);//(TBCms_Article)list.get(0);
%>
  <form name="uploadxinxi" method="post" action="<%=request.getContextPath()+"/TBCms_Article_update" %>">
<table style="width:100%;border:0px;border-collapse: 0px;border-spacing: 0px;">

	<tr height="45">
			<td align="right" width="20%" style="font-size:14px;">
			信息标题：
			<input type="hidden" name="return_divid" value="Article_edit_show" >
			<input type="hidden" name="return_url" value="<%=request.getContextPath()+"/YCWYPage/BackPage/ProperService/article_edit_show.jsp" %>" >
			<input type="hidden" id="cms_a_id" name="cms_a_id" value="<%=cms_a_id %>" >
			<input type="hidden" name="close_divid" value="div_sj_xxbj_show" >
			<input type="hidden" id="cms_c_id_update" name="cms_c_id_update" value="<%=session.getAttribute("cms_c_id").toString() %>" >
		</td>
		<td align="left" style="padding-left: 3px;" width="80%">
			<input type="text" class="input_txt" id="TITLE" name="TITLE" size="50" maxlength="50" value="<%=Article.getCms_a_title()  %>" />
			<font color="red">*标题最大长度50字符,不可为空。</font>
		</td>
	</tr>		

		<tr height="45">
		<td align="right" valign="top" style="font-size:14px;">
				具体内容：
		</td>
		<td align="left" id="fck">
			<textarea rows="10" cols="97" id="CONTENT" name="CONTENT"><%=Article.getCms_a_body()  %></textarea>
			<script type="text/javascript"> 
			if (typeof CKEDITOR == 'undefined') { 
			document.write('加载CKEditor失败'); 
			} 
			else { 
			var editor = CKEDITOR.replace('CONTENT',{toolbar:'Standard',width:'770',height:'250'}); 
			//CKFinder.SetupCKEditor(editor, '<%=request.getContextPath()%>/ckfinder/'); 
			} 
			</script>	
		</td>
	</tr>
	<tr height="45">
		<td align="right" style="font-size:14px;">
			文章来源：
		</td>
		<td  align="left">
			<input type="text" class="input_txt" id="cms_a_source" name="cms_a_source" maxlength="50" size="30" value="<%=Article.getCms_a_source() %>"><span style="color: red;">*</span>
		</td>
	</tr>	
	
	<tr height="45">
			<td align="center" colspan="2" style="padding-bottom: 20px;">
			<input type="submit" class="submit_input" value="提交" id="xiugai" name="xiugai" value=" 修 改 " onclick="return TBCms_Article_update('cms_c_id_update');"/>
		</td>
	</tr>	
</table>

	</form>
<!-- 显示等待图片的读取层 -->	
	<div id="Loading_div_da" style="width:0;height:0;display: none; z-index:10000001; filter:Alpha(opacity=0);"></div>
	<div id="Loading_div_xiao" style="text-align:center; color:red;width:0px;height:0px;display:none;z-index:1000002;  filter:Alpha(opacity=0);">
	<%-- <img src="<%=request.getContextPath()+"/Managepage/zxschoolSev/images/loading.gif"%>" style="width: 65;height: 65"></img> --%>
	</div> 		
  </body>

</html>
<% 
}
%>  