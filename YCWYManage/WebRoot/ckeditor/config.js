/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.filebrowserBrowseUrl =  '/ckfinder/ckfinder.html';//上传文件时浏览服务文件夹   如果需要在本机测试时，需要添加项目名称，下边几个一样道理，记得清楚浏览器缓存
    config.filebrowserImageBrowseUrl =  '/ckfinder/ckfinder.html?type=Images';//上传图片时浏览服务文件夹 
    config.filebrowserFlashBrowseUrl =  '/ckfinder/ckfinder.html?type=Flash';//上传Flash时浏览服务文件夹   
    config.filebrowserUploadUrl =  '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files'; //上传文件按钮(标签)   
    config.filebrowserImageUploadUrl =  '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images'; //上传图片按钮(标签)   
    config.filebrowserFlashUploadUrl =  '/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'; //上传Flash按钮(标签)  
    config.filebrowserWindowWidth = '1000';  //图片弹出窗口的宽
    config.filebrowserWindowHeight = '700';	//图片弹出窗口的高
    config.language =  "zh-cn";
    config.toolbarCanCollapse = false;// 工具栏是否可以被收缩   
    config.resize_enabled = false;// 取消 “拖拽以改变尺寸”功能 plugins/resize/plugin.js       
    config.image_previewText=' '; //预览区域显示内容
    config.width = 700; //宽度
    config.height = 300; //高度
    config.skin = 'kama';//默认皮肤
    //config.skin = 'v2';
    //config.skin = 'office2003';   
    //config.toolbar = 'Basic';
    config.toolbar = 'Full';
    config.toolbar_Full =
    [
        ['Source','-','Save','NewPage','Preview','-','Templates'],
        ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
        ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
        ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
        '/',
        ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
        ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
        ['Link','Unlink','Anchor'],
        ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
        '/',
        ['Styles','Format','Font','FontSize'],
        ['TextColor','BGColor'],
        ['Maximize', 'ShowBlocks','-','About']
    ];

	config.toolbar_Standard=
	[
		['Source','Preview','Templates'],
		['Paste','PasteText','PasteFromWord','Scayt'],
		['Undo','Redo','Find','Replace'],
		['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
		['Link','Unlink','Anchor'],
		['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
		'/',
		['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
		['Styles','Format','Font','FontSize'],
		['TextColor','BGColor'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','Maximize']
		
	];
    config.toolbar_Basic =
    [
        ['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink','-','About']
    ];  

};
