$(function(){
	$(".aside").height(($(window).height()-2)+"px");
	$(".section").height(($(window).height()-2)+"px").width(($(window).width()-245)+"px");
});

//导航
$(document).ready(function(){
	$(".list_clasify .list_clasifyUl li a").click(function(){							//从第二个li开始，点击执行;
		$(this).siblings(".list_clasifyNav").slideToggle(200);									//子级下拉/隐藏;
		$(this).parent("li").siblings().find(".list_clasifyNav").fadeOut(200);					//同级其他元素的子级隐藏;
	});
});
//admin
$(function(){
	$(".jbxxMineTitle").click(function(){
		$(this).prev(".jbxxMineNav").toggle();
	});
});
