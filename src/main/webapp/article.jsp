<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<meta charset="UTF-8">
<meta name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<script charset="utf-8" src="${path}/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
<style>
    .modal-body {
        position: relative;
        padding: 15px 24px;
    }
</style>
<style>
    .button {
        display: inline-block;
        white-space: nowrap;
        background-color: #ccc;
        background-image: linear-gradient(top, #eee, #ccc);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorStr='#eeeeee', EndColorStr='#cccccc');
        border: 1px solid #777;
        padding: 0 1.5em;
        margin: -0.5em;
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
        font-size: -0.5em;
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
    $(function(){
        var editor = KindEditor.create("#body",{
            imageSizeLimit: '10MB',
            imageUploadLimit: 30,
            width: '550px',
            minWidth:550,
            height: '200px',
            allowFileManager:true,
            resizeType:0,
            filePostName:'img',
            uploadJson:'${path}/Article/uploadImg',
            fileManagerJson:'${path}/Article/getImg',
            afterBlur: function(){this.sync()}
        });
    });
    $(function(){
        var editor = KindEditor.create("#body1",{
            imageSizeLimit: '10MB',
            imageUploadLimit: 30,
            width: '550px',
            minWidth:550,
            height: '200px',
            allowFileManager:true,
            resizeType:0,
            filePostName:'img',
            uploadJson:'${path}/Article/uploadImg',
            fileManagerJson:'${path}/Article/getImg',
            afterBlur: function(){this.sync()}
        });
    });
</script>
<style>
    #a3{
        margin-top: -30px;
        margin-bottom: 10px;
    }
</style>
<script>
    $(function () {
        $("#artciletable").jqGrid({
            url:"${path}/Article/QueryAll",
            editurl:"${path}/Article/Edit",
            styleUI:"Bootstrap",
            height:"400px",
            datatype:"json",
            caption:"文章信息",
            rowNum:2,
            pager:"#page",
            rowList:[2,5],
            viewrecords:true,
            autowith:true,
            multiselect:true,
            width:1200,
            colNames:[
                "ID","标题","状态","上传时间","操作"
            ],
            colModel:[
                {name:"id"},
                {name:"title"},
                {name:"status"},
                {name:"create_date"},

                {name:"option",formatter:function (callvalue,options,rowobject) {
                        return "&nbsp;&nbsp;<button style='border-radius: 30.2em;' data-toggle='modal' data-target='#myModal' class='button next' onclick=\"xiugai('"+rowobject['id']+"')\">查看</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button style='border-radius: 30.2em;'  class='button edit' data-toggle='modal' data-target='#upModal' onclick=\"chaxun('"+rowobject['id']+"')\">修改</button>";
                    }}

            ],
        }).jqGrid("navGrid","#page",{
            add:false,edit:false,search:false,deltext:"删除"
        })
    })
</script>
<div class="body-section">
<div id="a3" class="page-header">
    <h2>文章区域</h2>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">文章信息</a></li>
    <li><a href="#home" data-toggle='modal' data-target='#Modal'  >添加文章</a></li>
</ul>
<div id="danger" class="alert alert-info" style="display: none" role="alert">成功</div>
<table id="artciletable"></table>
<div id="page"  style="height: 50px; width: 60px;"></div>
<div class="modal fade" id="Modal"  role="dialog" data-backdrop="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">添加文章</h4>
            </div>
            <div class="modal-body">
                <form action="" class="form-inline" id="frmo">
                    <div class="form-group">
                        <label for="exampleInputName2">标题&nbsp;&nbsp;&nbsp;</label>
                        <input type="text" class="form-control" id="exampleInputName2" placeholder="请输入标题">
                    </div>
                    <br> <br>
                    <div class="form-group">
                        <label for="exampleInputName2">状态&nbsp;&nbsp;&nbsp;</label>
                        <select  id="select" class="form-control">
                            <option value="激活">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;激活&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                            <option value="冻结">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;冻结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                        </select>
                    </div>
                    <br> <br>
                    <textarea  id="body"  style="width:700px;height:300px;">
                         请输入内容
                    </textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="bc()">保存</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="upModal"  role="dialog" data-backdrop="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改文章</h4>
            </div>
            <div class="modal-body">
                <form action="" class="form-inline" id="frmox">
                    <div class="form-group">
                        <label for="exampleInputName2">id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                        <input type="text" readonly="readonly" class="form-control" id="exampleInputName10">
                    </div>
                    <br> <br>
                    <div class="form-group">
                        <label for="exampleInputName2">标题&nbsp;&nbsp;&nbsp;</label>
                        <input type="text" class="form-control" id="exampleInputName3" placeholder="请输入标题">
                    </div>
                    <br> <br>
                    <div class="form-group">
                        <label for="exampleInputName2">状态&nbsp;&nbsp;&nbsp;</label>
                        <select  id="select1" class="form-control">
                            <option value="激活">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;激活&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                            <option value="冻结">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;冻结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
                        </select>
                    </div>
                    <br> <br>
                    <textarea  id="body1"  style="width:700px;height:300px;"></textarea>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="up()">保存</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal"  role="dialog" data-backdrop="false" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    详细信息
                </h4>
            </div>
            <div class="modal-body" style="height: 1200px">
                <center><h3 id="authod">标题: </h3></center>
                <center id="content"></center>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    function bc() {
        var input1 = $("#exampleInputName2").val();
        var text = $("#body").val();
        var select = $("#select").val();
        $.ajax({
            url:"${path}/Article/insert",
            data:{title:input1,content:text,status:select},
            success:function () {
                document.getElementById("frmo").reset();
                KindEditor.html("#body","请输入内容");
                $("#Modal").hide();
                $("#artciletable").trigger("reloadGrid");
            }
        })
    }
    function up() {
        var input1 = $("#exampleInputName3").val();
        var text = $("#body1").val();
        var select = $("#select1").val();
        var id = $("#exampleInputName10").val();
        $.ajax({
            url:"${path}/Article/updata",
            data:{title:input1,content:text,status:select,id:id},
            success:function () {
                document.getElementById("frmox").reset();
                $("#upModal").hide();
                $("#artciletable").trigger("reloadGrid");
            }
        })
    }
        function xiugai(id) {
            $.ajax({
                url:"${path}/Article/queryByid",
                data:{id:id},
                success:function (result) {
                    $("#authod").empty();
                    $("#authod").append(result.title)
                    $("#content").empty();
                    $("#content").append(result.content)
                }
            })
        }

    function chaxun(id) {
        $.ajax({
            url:"${path}/Article/queryByid",
            data:{id:id},
            success:function (result) {
                $("#exampleInputName3").val(result.title);
                $("#exampleInputName10").val(result.id);
                $("#select1").val(result.status);
                KindEditor.html("#body1",result.content);
            }
        })
    }
</script>