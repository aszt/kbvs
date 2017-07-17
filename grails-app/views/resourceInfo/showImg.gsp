<%--
  Created by IntelliJ IDEA.
  User: c-kx
  Date: 2016/6/7
  Time: 11:23
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <style>
    *{margin:0;padding:0;list-style:none;border:0;}
    body{font-size:12px;font-family:Verdana, Geneva, sans-serif;}
    a{color:#fff;text-decoration:none;}
    a:hover{text-decoration:underline;}

    /* carousel */
    .carousel{margin:20px auto;position:relative;height:340px;width:880px;overflow:hidden;}
    .carousel h2 a{color:#fff;}
    .carousel .backgrounds{height:340px;}
    .carousel .backgrounds .item{width:880px;height:340px;float:left;position:relative;z-index:1;}

    .carousel .panel{background:#000;color:#fff;position:absolute;right:0;top:0;height:340px;width:275px;z-index:10;filter:alpha(opacity=80);-moz-opacity:0.8;-khtml-opacity:0.8;opacity:0.8;}
    .carousel .panel .paging{position:absolute;bottom:25px;left:25px;width:225px;text-align:center;}
    .carousel .panel .paging a{color:#4c4c4c;font-size:1.1em;}
    .carousel .panel .pause{position:absolute;right:20px;top:25px;display:block;width:18px;height:18px;background:url(../images/carousel_pause_bg.gif) no-repeat 0 0;text-indent:-6000px;}
    .carousel .panel .play{position:absolute;right:20px;top:25px;display:block;width:18px;height:18px;background:url(../images/carousel_play_bg.gif) no-repeat 0 0;text-indent:-6000px;}
    .carousel .panel .paging .next{position:absolute;right:0;bottom:0;display:block;width:18px;height:18px;background:url(../images/carousel_next_bg.gif) no-repeat 0 0;text-indent:-6000px;}
    .carousel .panel .paging .previous{position:absolute;left:0;bottom:0;display:block;width:18px;height:18px;background:url(../images/carousel_previous_bg.gif) no-repeat 0 0;text-indent:-6000px;}
    .carousel .panel .paging #numbers a{padding:0 5px 0 5px;}
    .carousel .panel .paging #numbers a.selected{color:#fff;}
    .carousel .panel .details_wrapper{position:absolute;top:20px;left:25px;width:225px;overflow:hidden;height:200px;}
    .carousel .panel .details_wrapper .details{height:200px;}
    .carousel .panel .details_wrapper .details .detail{width:225px;height:200px;float:left;}
    .carousel .panel .details_wrapper .details h2{font-size:1.9em;line-height:1.2em;margin:0 0 5px 0;}
    .carousel .panel .details_wrapper .details a.more{color:#fff;font-size:1.1em;}
    </style>
    <g:javascript src="jquery-1.11.3.min.js"/>
    <g:javascript src="easing.js"/>
    <g:javascript src="dualSlider.js"/>
    <script type="text/javascript">
        $(document).ready(function(){

            $(".carousel").dualSlider({
                auto:true,
                autoDelay: 6000,
                easingCarousel: "swing",
                easingDetails: "easeOutBack",
                durationCarousel: 1000,
                durationDetails: 500
            });

        });
    </script>

</head>
<body>

<div class="carousel">
    <div class="panel">
        <div class="details_wrapper">
            <div class="details">
                <div class="detail">
                    <h2 class="Lexia-Bold"><a href="#">${resourceInfo.name}</a></h2>
                    <a href="#" class="more"></a>
                </div>

            </div>
        </div>
    </div><!-- /panel -->

    <div class="backgrounds">
        <div class="item item_1"><img src="${resource(dir: '',file: resourceInfo.path)}"/></div>
    </div>

</div>


</body>
</html>