// JavaScript Document

$(document).ready(function(e) {
	
$(".nav-left ul li").click(function(){
	$(this).addClass("current").siblings().removeClass("current");
    })

	//常用文件页面高度
	$(".left-bar").height(window.innerHeight-$(".navbar").height()-10);
	var $activeheight=$(".navbar").height()+$(".body-right .header").height()+$(".list-col").height()+19;
	$(".activescrollbar").height(window.innerHeight-$activeheight);
	var $activegao=$(".activescrollbar").height();
	var $activeligao=$(".activefilescrollbar").height();
	if($activegao>$activeligao){
		$(".activescrollbar").css("overflow-y","hidden");
	}else{
		$(".activescrollbar").css("overflow-y","scroll");
	}

	//视频页面高度
	$(".left-bar").height(window.innerHeight-$(".navbar").height()-10);
	var $videoheight=$(".navbar").height()+$(".body-right .header").height()+19;
	$(".videoscrollbar").height(window.innerHeight-$videoheight);
	var $videogao=$(".videoscrollbar").height();
	var $videoligao=$(".videofilescrollbar").height();
	if($videogao>$videoligao){
		$(".videoscscrollrollbar").css("overflow-y","hidden");
	}else{
		$(".videoscrollbar").css("overflow-y","scroll");
	}

	//音乐页面高度
	$(".left-bar").height(window.innerHeight-$(".navbar").height()-10);
	var $musicheight=$(".navbar").height()+$(".body-right .header").height()+$(".list-col").height()+19;
	$(".musicscrollbar").height(window.innerHeight-$musicheight);
	var $musicgao=$(".musicscrollbar").height();
	var $musicligao=$(".musicfilescrollbar").height();
	if($musicgao>$musicligao){
		$(".musicscrollbar").css("overflow-y","hidden");
	}else{
		$(".musicscrollbar").css("overflow-y","scroll");
	}

	//文档页面高度
	$(".left-bar").height(window.innerHeight-$(".navbar").height()-10);
	var $textheight=$(".navbar").height()+$(".body-right .header").height()+$(".list-col").height()+19;
	$(".textscrollbar").height(window.innerHeight-$textheight);
	var $textgao=$(".textscrollbar").height();
	var $textligao=$(".textfilescrollbar").height();
	if($textgao>$textligao){
		$(".textscrollbar").css("overflow-y","hidden");
	}else{
		$(".textscrollbar").css("overflow-y","scroll");
	}

	//图片页面高度
	$(".left-bar").height(window.innerHeight-$(".navbar").height()-10);
	var $imgheight=$(".navbar").height()+$(".body-right .header").height()+$(".list-col").height()+19;
	$(".imgscrollbar").height(window.innerHeight-$imgheight);
	var $imggao=$(".imgscrollbar").height();
	var $imgligao=$(".imgscrollbar").height();
	if($imggao>$imgligao){
		$(".imgscrollbar").css("overflow-y","hidden");
	}else{
		$(".imgscrollbar").css("overflow-y","scroll");
	}

	//知识库show页面高度
	$(".left-bar").height(window.innerHeight-$(".navbar").height()-10);
	var $libshowheight=$(".navbar").height()+$(".header_guizi").height()+$(".list-col").height()+53;
	$(".libshowscrollbar").height(window.innerHeight-$libshowheight);
	var $libshowgao=$(".libshowscrollbar").height();
	var $libshowligao=$(".libshowscrollbar").height();
	if($libshowgao>$libshowligao){
		$(".libshowscrollbar").css("overflow-y","hidden");
	}else{
		$(".libshowscrollbar").css("overflow-y","scroll");
	}

	//其它页面高度
	$(".left-bar").height(window.innerHeight-$(".navbar").height()-10);
	var $otherheight=$(".navbar").height()+$(".body-right .header").height()+$(".list-col").height()+19;
	$(".otherscrollbar").height(window.innerHeight-$otherheight);
	var $othergao=$(".otherscrollbar").height();
	var $otherligao=$(".otherfilescrollbar").height();
	if($othergao>$otherligao){
		$(".otherscrollbar").css("overflow-y","hidden");
	}else{
		$(".otherscrollbar").css("overflow-y","scroll");
	}


//移动到页面 点击显示颜se
	$(".classification li").hover(function(){
		$(".classification li").show();
		$(".classification").addClass("shadow");
		$(this).addClass("classificationCurrent").siblings().removeClass("classificationCurrent");
	 },function(){
		$(".classification li:first").show().siblings().hide();
		$(".classification").removeClass("shadow");
		$(this).removeClass("classificationCurrent");
    })
	
	//视图切换
$(".makeup .makeup_r").click(function(){
		$(".makeup .makeup_r").css("background-position","-32px -35px");
		$(".makeup .makeup_l").css("background-position","0 -35px");
		$(".body-nav-r").css("display","block")
		$(".body-nav").css("display","none")
		
	})
    $(".makeup .makeup_l").click(function(){
		$(".makeup .makeup_l").css("background-position","0 0");
		$(".makeup .makeup_r").css("background-position","-33px 0");
		$(".body-nav-r").css("display","none")
		$(".body-nav").css("display","block")
		
		
	})

$(".checkbox").on("click",function(){
  $(this).addClass("on").siblings().removeClass("on");
})	
//user切换
$(".user_myself").click(function(){ $(".option").toggle();});
//check切换
 var old = null; //用来保存原来的对象  
    $(".check-icon-file").click(function(){
	   $(this).css("background-position","-41px -12px");
        this.onclick = function(){  
		
            if(this == old){//如果点击的对象原来是选中的，取消选中  
			 $(this).css("background-position","-9px -12px") 
                old = null;  
            } else{  
                old = this;  
            }  
        } 
    });  
	
	$(".list_header .all_check_icon").click(function(){
	   $(".all_check_icon").css("background-position","-41px -12px");
        this.onclick = function(){  
		
            if(this == old){//如果点击的对象原来是选中的，取消选中  
			 $(".all_check_icon").css("background-position","-9px -12px") 
                old = null;  
            } else{  
                old = this;  
            }  
        } 
    });

	//编辑个人资料
	$(".btn-bj").click(function(){
		$(".grzl").hide();
		$(".xggrzl").css("display","block");
		var $mz=$(".grzl .mz").text();
		$(".xggrzl .usrmz").val($mz);

		var $zhmz=$(".grzl .zhmz").text();
		$(".xggrzl .usrzhm").val($zhmz);

		var $zhmz=$(".grzl .ghnum").text();
		$(".xggrzl .usrgh").val($zhmz);

		var $email=$(".grzl .emailnum").text();
		$(".xggrzl .usremail").val($email);

		var $photo=$(".grzl .photonum").text();
		$(".xggrzl .usrphoto").val($photo);

		var $zhmz=$(".grzl .bgphotonum").text();
		$(".xggrzl .usrbgphoto").val($zhmz);

		var $bm=$(".grzl .bmnum").text();
		$(".xggrzl .usrbm").val($bm);

		var $zuqun=$(".grzl .zuqun").text();
		$(".xggrzl .usrzq").val($zuqun);

	})

	var $showbheight=$(".showfilescrollbar").height();
	var $showsheight=$(".showscrollbar").height();

	if($showbheight>$showsheight){
		$(".showscrollbar").css("overflow-y","scroll");
	}
	if($showbheight<$showsheight){
		$(".showscrollbar").css("overflow-y","hidden");
	}

	var $glbheight=$(".filesc").height();
	var $glsheight=$(".glscrollbar").height();

	if($glbheight>$glsheight){
		$(".glscrollbar").css("overflow-y","hidden");
	}
	if($glbheight<$glsheight){
		$(".glscrollbar").css("overflow-y","scroll");
	}

	var $bh=$(".treeview-content").height();
	var $sh=$(".file-tree-container").height();

	if($bh>$sh){
		$(".file-tree-container").css("overflow-y","scroll");
	}
	if($bh<$sh){
		$(".file-tree-container").css("overflow-y","hidden");
	}



	var $bw=$(".treeview-content").width();
	var $sw=$(".file-tree-container").width();

	if($bw>$sw){
		$(".file-tree-container").css("overflow-y","scroll");
	}
	if($bw<$sw){
		$(".file-tree-container").css("overflow-y","hidden");
	}

	//分享页
	
	$(".share-tab li").click(function(){
		$share_index=$(this).index();
		//alert($share_index);
		$(this).addClass("current").siblings().removeClass("current");
		$(".shengchen_link").eq($share_index).css("display","block").siblings().hide();

	})

//组群页点击事件
	$(".list-user").click(function(){
		$(this).addClass("currentlist").siblings().removeClass("currentlist");
	})



	//$('.right-xinxi').on('click', function(){
	//	layer.open({
	//		title :'加入组群',
	//		type: 1,
	//		area: ['592px', '550px'],
	//		shadeClose: true, //点击遮罩关闭
	//		content:$('#joinzuqun-people')
	//	});
	//});

	//查看组群按钮关闭
	$(".zuqun-people-closeBtn").click(function(){
		layer.closeAll()
	})


//显示隐藏图片查看器
	$('.img').on('click', function(){
		$("#imageScreen").show();

	});
	$(".toolbar .close").on('click', function(){
		$("#imageScreen").hide();

	});


//缩放图片
	$('.img').on('click', function(){
		var imgwidth = $("#imgList .bimg img").width();
		var imgheight = $("#imgList .bimg img").height();
		var windowwidth =window.innerWidth;
		var windowheight =window.innerHeight;

		if(imgwidth > windowwidth){
			$("#imgList .bimg img").css({
				"-moz-transform":"scale(0.4,0.4)",
				"-webkit-transform ": "scale(0.4,0.4)",
				"-o-transform":"scale(0.4,0.4)"

			});
		}
		if(imgheight > windowheight){
			$("#imgList .bimg img").css({
				"-moz-transform":"scale(0.4,0.4)",
				"-webkit-transform ": "scale(0.4,0.4)",
				"-o-transform":"scale(0.4,0.4)"

			});

		}
	});


	$('.addlayer').on('click', function(){
		layer.open({
			title :'新建知识库规则',
			type: 1,
			area: ['430px', '630px'],
			shadeClose: true, //点击遮罩关闭
			content:$('#newkbvs')
		});
	});
	$('.tipbianji').on('click', function(){
		layer.open({
			title :'编辑知识库规则',
			type: 1,
			area: ['430px', '630px'],
			shadeClose: true, //点击遮罩关闭
			content:$('#newkbvs')
		});
	});


});


