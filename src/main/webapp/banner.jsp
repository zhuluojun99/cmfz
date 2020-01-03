<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<style>
    #a1{
        margin-top: -30px;
        margin-bottom: 10px;
    }
</style>
<script>
    $(function () {
        $("#table").jqGrid({
            url:"${path}/Banner/QueryAll",
            editurl:"${path}/Banner/Edit",
            styleUI:"Bootstrap",
            height:"400px",
            datatype:"json",
            caption:"轮播图管理",
            rowNum:2,
            pager:"#page",
            rowList:[2,5],
            viewrecords:true,
            autowith:true,
            multiselect:true,
            width:1200,
            colNames:[
                "ID","标题","上传时间","状态","图片"
            ],
            colModel:[
                {name:"id"},
                {name:"title",editable:true,editrules:{required:true}},
                {name:"create_date"},
                {name:"status",editable:true,required:true,edittype:"select", editoptions:{
                        value:"激活:激活;冻结:冻结"
                    }
                },
                {name:"img",editable:true,edittype:"file",formatter:function (cellvalue, options, rowObject) {
                        return "<img style='width: 100%;height: 100px' src='${path}/images/"+cellvalue+"'/>"
                    }}
            ]
        }).jqGrid("navGrid","#page",{
                search:false,addtext:"添加",edittext:"修改",deltext:"删除"
            },
            /*
                修改
             */
            {
                afterSubmit:function (response) {
                    var id = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${path}/Banner/upload",
                        fileElementId:"img",
                        data:{bannerId:id},
                        type:"post",
                        success:function () {
                            $("#table").jqGrid().trigger("reloadGrid");
                            $("#upmsg").show();
                            setTimeout(function () {
                                $("#upmsg").hide();
                            },2000);

                        }
                    })
                    return response;
                }
            },
            /**
             * 添加
             */
            {
                afterSubmit:function (response) {
                    var id = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${path}/Banner/upload",
                        fileElementId:"img",
                        data:{bannerId:id},
                        type:"post",
                        success:function () {
                            $("#table").jqGrid().trigger("reloadGrid");
                            $("#msg").show();
                            setTimeout(function () {
                                $("#msg").hide();
                            },2000);

                        }
                    })
                    return response;
                }
            },
            {}
        )
    })
</script>
<div id="a1" class="page-header">
    <h2>轮播图区域</h2>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">轮播图信息</a></li>
</ul>
<table id="table"></table>
<div id="page"  style="height: 50px; width: 60px;"></div>
<div id="msg" class="alert alert-info" style="display: none" role="alert">图片上传成功!!!</div>
<div id="upmsg" class="alert alert-info" style="display: none" role="alert">变更成功!!!</div>