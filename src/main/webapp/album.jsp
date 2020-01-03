<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<style>
    #a2{
        margin-top: -30px;
        margin-bottom: 10px;
    }
    .modal-body {
        position: relative;
        padding: 15px 162px;
    }
    .button {
        display: inline-block;
        white-space: nowrap;
        background-color: #ccc;
        background-image: linear-gradient(top, #eee, #ccc);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorStr='#eeeeee', EndColorStr='#cccccc');
        border: 1px solid #777;
        padding: 0 1.5em;
        margin: 0.5em;
        font: bold 1em/2em Arial, Helvetica;
        text-decoration: none;
        color: #333;
        text-shadow: 0 1px 0 rgba(255,255,255,.8);
        border-radius: .2em;
        box-shadow: 0 0 1px 1px rgba(255,255,255,.8) inset, 0 1px 0 rgba(0,0,0,.3);
    }

    .button:hover {
        background-color: #ddd;
        background-image: linear-gradient(top, #fafafa, #ddd);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorStr='#fafafa', EndColorStr='#dddddd');
    }

    .button:active {
        box-shadow: 0 0 4px 2px rgba(0,0,0,.3) inset;
        position: relative;
        top: 1px;
    }

    .button:focus {
        outline: 0;
        background: #fafafa;
    }

    .button:before {
        background: #ccc;
        background: rgba(0,0,0,.1);
        float: left;
        width: -1em;
        text-align: center;
        font-size: 1.5em;
        margin: 0 1em 0 -1em;
        padding: 0 .2em;
        box-shadow: 1px 0 0 rgba(0,0,0,.5), 2px 0 0 rgba(255,255,255,.5);
        border-radius: .15em 0 0 .15em;
        pointer-events: none;
    }

    /* Hexadecimal entities for the icons */

    .add:before {
        content: "\271A";
    }

    .edit:before {
        content: "\270E";
    }

    .delete:before {
        content: "\2718";
    }

    .save:before {
        content: "\2714";
    }

    .email:before {
        content: "\2709";
    }

    .like:before {
        content: "\2764";
    }

    .next:before {
        content: "\279C";
    }

    .star:before {
        content: "\2605";
    }

    .spark:before {
        content: "\2737";
    }

    .play:before {
        content: "\25B6";
    }
</style>
<script>
    $(function () {
        $("#table1").jqGrid({
            url:"${path}/Album/QueryAll",
            styleUI:"Bootstrap",
            editurl:"${path}/Album/Edit",
            height:"400px",
            datatype:"json",
            caption:"专辑与章节信息",
            rowNum:2,
            pager:"#page1",
            rowList:[2,5],
            viewrecords:true,
            autowith:true,
            multiselect:true,
            width:1200,
            colNames:[
                "ID","标题","分数","作者","播音员","章节数","专辑简介","状态","发行时间","插图"
            ],
            colModel:[
                {name:"id"},
                {name:"title",editable:true,editrules:{required:true}},
                {name:"score",editable:true,editrules:{required:true,number:true,minValue:1,maxValue:10},edittype:"number"},
                {name:"author",editable:true,editrules:{required:true}},
                {name:"broadcaster",editable:true,editrules:{required:true}},
                {name:"count"},
                {name:"brief",editable:true,editrules:{required:true}},
                {name:"status",editable:true,editrules:{required:true},edittype:"select", editoptions:{
                        value:"激活:激活;冻结:冻结"
                    }
                },
                {name:"create_date",editable:true,edittype:"date",required:true},
                {name:"img",editable:true,edittype:"file",formatter:function (cellvalue, options, rowObject) {
                        return "<img style='width: 100%;height: 100px' src='${path}/img/"+cellvalue+"'/>"
                    }}
            ],
            subGrid:true,
            subGridRowExpanded:function (subGridId,albumId) {
                //添加表格的方法
                addSubGrid(subGridId,albumId);
            }
        }).jqGrid("navGrid","#page1",{
            search:false,addtext:"添加",edittext:"修改",deltext:"删除"
        },
            {
                afterSubmit:function (response) {
                    var id = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        url:"${path}/Album/upload",
                        fileElementId:"img",
                        data:{albumId:id},
                        type:"post",
                        success:function () {
                            $("#table1").jqGrid().trigger("reloadGrid");
                            $("#danger").show();
                            setTimeout(function () {
                                $("#danger").hide();
                            },2000);

                        }
                    })
                    return response;
                }
            },
            {
                afterSubmit:function (response) {
                    var id = response.responseJSON.albumId;
                    $.ajaxFileUpload({
                        url:"${path}/Album/upload",
                        fileElementId:"img",
                        data:{albumId:id},
                        type:"post",
                        success:function () {
                            $("#table1").jqGrid().trigger("reloadGrid");
                            $("#danger").show();
                            setTimeout(function () {
                                $("#danger").hide();
                            },2000);

                        }
                    })
                    return response;
                }
            },
            {}
        )
    })
    $(function () {
        var audio = new Audio();
        audio.id="aduio"
        document.body.appendChild(audio)
    })
    function addSubGrid(subGridId,albumId) {
        //动态表格 table
        var tableid = subGridId+ "table";
        var idforalbum = subGridId+ "div";
        //动态添加子表格
        $("#"+subGridId).html("<table id='"+tableid+"'></table>"+"<div id='"+idforalbum+"' style='height:50px;width: 60px '></div>")
        $("#"+tableid).jqGrid({
            url:"${path}/Chapter/QueryAll?album_id="+albumId,
            styleUI:"Bootstrap",
            editurl:"${path}/Chapter/Edit?album_id="+albumId,
            height:"100px",
            datatype:"json",
            caption:"章节信息",
            rowNum:2,
            pager:"#"+idforalbum,
            rowList:[2,5],
            toolbar:[true,"top"],
            viewrecords:true,
            autowith:true,
            multiselect:true,
            width:1000,
            colNames:[
                "ID","标题","大小","时长","上传时间","音频","状态"
            ],
            colModel:[
                {name:"id"},
                {name:"title",editable:true,editrules:{required:true}},
                {name:"size"},
                {name:"duration"},
                {name:"create_date",editable:true,edittype:"date",required:true},
                {name:"src",editable:true,edittype:"file"},
                {name:"status" ,editable:true,required:true,edittype:"select", editoptions:{
                        value:"激活:激活;冻结:冻结"
                    }
                }
            ]
        }).jqGrid("navGrid","#"+idforalbum,{
            search:false,addtext:"添加",edittext:"修改",deltext:"删除"
        },
            {
                afterSubmit:function (response) {
                    var id = response.responseJSON.charpterId;
                    if(id!=null){
                        $.ajaxFileUpload({
                            url:"${path}/Chapter/upload",
                            fileElementId:"src",
                            data:{chapterId:id},
                            type:"post",
                            success:function () {
                                $("#"+tableid).jqGrid().trigger("reloadGrid");
                                $("#danger").show();
                                setTimeout(function () {
                                    $("#danger").hide();
                                },2000);

                            }
                        })
                    }
                    return response;
                }
            },
            {
                afterSubmit:function (response) {
                    var id = response.responseJSON.charpterId;
                    $.ajaxFileUpload({
                        url:"${path}/Chapter/upload",
                        fileElementId:"src",
                        data:{chapterId:id},
                        type:"post",
                        success:function () {
                            $("#table1").jqGrid().trigger("reloadGrid");
                            $("#danger").show();
                            setTimeout(function () {
                                $("#danger").hide();
                            },2000);

                        }
                    })
                    return response;
                }
            },
            {afterComplete: function (response) {
                    $("#table1").trigger("reloadGrid");
                    $("#danger").show();
                    setTimeout(function () {
                        $("#danger").hide();
                    },2000);
                }}
        )
        $("#t_"+tableid).html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<button style='border-radius: 34.2em;' class='button play' data-toggle='modal' data-target='#myModal' onclick=\"plax('"+tableid+"')\">播放</button>"+
            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"<button style='border-radius: 35.2em;' class='button like' onclick=\"down('"+tableid+"')\">下载</button>")
    }
    function plax(tableid) {
        //判断 用户是否选中一行 未选中就是null 选中就是发送被选中的id
        var id = $("#"+tableid).jqGrid("getGridParam", "selrow");
        if (id == null) {
            $("#dan").show();
            setTimeout(function () {
                $("#dan").hide();
            }, 2000);
        } else {
            //jqgrid提供方法 根据id拿到行数据
            var data = $("#"+tableid).jqGrid("getRowData", id);
            $("#myModal").modal('hide')
            $("#audiox").attr("src","${path}/music/"+data.src)
        }
    }
    function down(tableid) {
        //判断 用户是否选中一行 未选中就是null 选中就是发送被选中的id
        var id = $("#"+tableid).jqGrid("getGridParam", "selrow");
        if (id == null) {
            $("#dan").show();
            setTimeout(function () {
                $("#dan").hide();
            }, 2000);
        }else {
            //jqgrid提供方法 根据id拿到行数据
            var data = $("#"+tableid).jqGrid("getRowData",id);
            var src = data.src;
            location.href="${path}/Chapter/download?filename="+src;
        }
    }
</script>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">音乐播放器</h4>
            </div>
            <div class="modal-body">
                <audio autoplay controls src="" id="audiox"></audio>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="document.getElementById('audiox').pause()" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div id="a2" class="page-header">
    <h2>专辑区域</h2>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">专辑信息</a></li>
</ul>
<div id="danger" class="alert alert-info" style="display: none" role="alert">成功</div>
<div id="dan" class="alert alert-danger" style="display: none" role="alert">必须选中一条记录</div>
<table id="table1"></table>
<div id="page1"  style="height: 50px; width: 60px;"></div>
