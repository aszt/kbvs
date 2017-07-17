<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>我的资源</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${resource(dir: 'css',file: 'bootstrap.min.css')}" type="text/css">
		%{--<link rel="stylesheet" href="${resource(dir: 'css',file: 'main.css')}" type="text/css">--}%
		<link href="${resource(dir: 'css', file: 'mains.css')}" rel="stylesheet">
		<link rel="stylesheet" href="${resource(dir: 'css',file: 'base.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css',file: 'fileinput.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'font-awesome-4.5.0/css',file: 'font-awesome.min.css')}" type="text/css">
		<link rel="shortcut icon" href="${resource(dir: 'images',file: 'favicon.png')}">
		<g:javascript src="jquery-1.11.3.min.js"/>
		<g:javascript src="fileinput.js"/>
		<g:javascript src="fileinput_locale_zh.js"/>
		<g:javascript src="bootstrap.min.js"/>
		<g:javascript src="main.js"/>
		<g:javascript src="bootstrap-treeview.js"/>
		%{--layout使用--}%
		<g:javascript src="layer/layer.js"/>


		<g:layoutHead/>
	</head>
	<body>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	</body>
</html>
