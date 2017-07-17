<%--
  Created by IntelliJ IDEA.
  User: tong
  Date: 2016/5/24
  Time: 11:21
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
    <script type="text/javascript">
        var folderIds = null;//点击到的文件夹==移动（数据交互处理）
        var id = null;//点击文件夹id
        $(document).ready(function(){

//当多层文件被点击
            $('body').on('click','.treeview-node-handler',function(){
                //alert("sdasd");
                id = $(this).attr("dir");

                //点击文件夹id
//                $(this).addclass(".ydbg");

                var a = $(this).attr("id");//记录文件夹等级(一级距离30px)
                var s = $(this).attr("onstalled");//文件夹状态
                if(s=="关"){
                    $(this).attr("onstalled","开");
                    $.ajax({
                        url:'<g:createLink controller="folderInfo" action="findFolder" />',
                        type:'post',
                        dataType:'json',
                        data:{folderId:id},
                        success:function(msg){
                            //处理字符串转整数
                            var c = parseInt(a);
                            var d = c+20;
                            var folderList = msg.folderInfoList
                            for(var i=0;i<folderList.length;i++){
                                var folder = folderList[i]
                                var ids = folder.ownerId
                                //删除二次添加数据
                                $("#"+folder.id).remove()
                                //子文件夹id
//                                alert("子文件夹id："+folder.id)
//                                alert("父文件夹id："+ids)
                                var ss='<div id='+folder.id +'>'+
                                        '<ul class="treeview treeview-content" _pl="30"><li>' +
                                        '<div class="treeview-node treenode-empty" style="padding-left:'+d+'px" _pl="30px">' +
                                        '<span class="treeview-node-handler" dir='+folder.id+' id='+d+' onstalled='+"关" +'>' +
                                        '<em class="b-in-blk plus icon-operate "></em>' +
                                        '<dfn class="b-in-blk treeview-ic"></dfn>' +
                                        '<span class="treeview-txt" id="e">'+folder.name+'</span></span></div>'+
                                        '<ul class="treeview treeview-content treeview-collapse" _pl="45px"></ul></li></ul></div>'
                                $("#"+ids).append(ss)

                            }

                        }
                    });
                }
                else if(s=="停"){
                    //点击全部文件，不做任何处理
                }
                else{
                    $(this).attr("onstalled","关");
                    $.ajax({
                        url:'<g:createLink controller="folderInfo" action="findFolder" />',
                        type:'post',
                        dataType:'json',
                        data:{folderId:id},
                        success:function(msg){
                            var folderList = msg.folderInfoList
                            for(var i=0;i<folderList.length;i++){
                                var folder = folderList[i]
//                                var ids = folder.ownerId
                                //删除二次添加数据
                                $("#"+folder.id).remove()
                            }
                        }
                    });
                }
            });

            //要移动文件夹的ownerId改成要移动到的文件夹id
            $("#vs").click(function(){

                if(folderIds==null){
                    folderIds = id;
                }
                var FolderIds = $("#fd").attr("dir")//勾选的文件夹id
//                alert("勾选文件夹的id:"+FolderIds)
//                alert("点击文件夹的id:"+folderIds)//移动到此文件夹的id
                $.ajax({
                    url:'<g:createLink controller="folderInfo" action="move" />',
                    type:'post',
                    dataType:'json',
                    data:{FolderIds:FolderIds,folderIds:folderIds},
                    success:function(msg){
                            if(msg.state="200"){
                                var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
//                                跳转到首页
                                parent.location = "<g:createLink controller="resourceInfo" action="findResourcesByFolder" />?id=" +folderIds;
                            }
                        }
                    });

            });

            //关闭iframe层
            var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
            $('#cance').on('click', function(){
                parent.layer.close(index); //执行关闭
            });

        });

    </script>
</head>

<body>

<div id="fileTreeDialog">
    <div class="dialog-body">
        <div class="file-tree-container">
            <ul class="treeview treeview-content _disk_id_1">
                <li>
                    <div class="treeview-node treeview-node-on treeview-root _minus" style="padding-left:0px" _pl="0px">
                        <span class="treeview-node-handler" dir="${FolderInfos.id}" onstalled="停">
                            <em class="b-in-blk plus icon-operate minus"></em>
                            <dfn class="b-in-blk treeview-ic"></dfn>
                            <span class="treeview-txt" node-path="/">全部文件</span>
                        </span>
                    </div>

                        <ul class="treeview treeview-root-content treeview-content" _pl="15">
                        <g:each in="${FolderInfoList}" status="i" var="folderInfoInstance">
                            <li id="${folderInfoInstance.id}">
                                <div class="treeview-node treenode-empty" style="padding-left:15px" _pl="15px">
                                    <span class="treeview-node-handler" dir="${folderInfoInstance.id}" id="30" onstalled="关">
                                        <em class="b-in-blk plus icon-operate "></em>
                                        <dfn class="b-in-blk treeview-ic"></dfn>
                                        <span class="treeview-txt">${fieldValue(bean: folderInfoInstance, field: "name")}</span>
                                    </span>
                                </div>
                               %{--二级--}%

                            </li>
                        </g:each>
                        </ul>

                </li>
            </ul>
        </div>
        %{--接收控制层传入的文件夹id--}%
        <input type="hidden" id="fd" dir="${FolderId}">
        <span class="btn btn-default btn-sm" style="float: right; margin-right: 10px;" id="cance">取消</span>
        <span class="btn btn-info btn-sm" id="vs" style="float: right; margin-right: 10px;">确认</span>

    </div>
</div>
</body>
</html>