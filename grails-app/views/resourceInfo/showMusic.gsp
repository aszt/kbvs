<%--
  Created by IntelliJ IDEA.
  User: c-kx
  Date: 2016/6/9
  Time: 22:44
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:javascript src="APlayer.min.js"/>
    <link rel="stylesheet" href="${resource(dir: 'css',file: 'APlayer.min.css')}">
    <title>音乐播放</title>
    <style>
    body{font-family:'Helvetica Neue',Helvetica,Arial,sans-serif;}.container{max-width:32rem;margin-left:auto;margin-right:auto;}h1{font-size:54px;color:#333;margin:30px 0 10px;}h2{font-size:22px;color:#555;}h3{font-size:24px;color:#555;}hr{display:block;width:7rem;height:1px;margin:2.5rem 0;background-color:#eee;border:0;}a{color:#08c;text-decoration:none;}p{font-size:18px;}
    </style>
</head>

<body>
    <div id="player1" class="aplayer"></div>
<script>
    var ap1 = new APlayer({
        element: document.getElementById('player1'),
        narrow: false,
        autoplay: true,
        showlrc: false,
        music: {
            title: '${resourceInfo.name}',
            author: '${resourceInfo.artist}',
            url: '../${resourceInfo.path}',
            pic: '${resource(dir: 'images',file: 'music_94.025465230167px_1191632_easyicon.net.png')}'
        }
    });
    ap1.init();

</script>
</body>
</html>