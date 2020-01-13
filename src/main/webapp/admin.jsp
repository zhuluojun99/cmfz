<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<style>
    #a1 {
        margin-top: -30px;
        margin-bottom: 10px;
    }
</style>
<script>
    $(function () {
        $("#table").jqGrid({
            url: "${path}/Admin/QueryAll",
            editurl: "${path}/Banner/Edit",
            styleUI: "Bootstrap",
            height: "400px",
            datatype: "json",
            caption: "admin管理",
            rowNum: 2,
            pager: "#page",
            rowList: [2, 5],
            viewrecords: true,
            autowith: true,
            multiselect: true,
            width: 1200,
            colNames: [
                "ID", "用户名", "密码", "状态"
            ],
            colModel: [
                {name: "id"},
                {name: "username", editable: true, editrules: {required: true}},
                {name: "password"},
                {
                    name: "status", editable: true, required: true, edittype: "select", editoptions: {
                        value: "激活:激活;冻结:冻结"
                    }
                }
            ]
        }).jqGrid("navGrid", "#page", {
                search: false, add: false, edit: false, del: false
            }
        )
    })
</script>
<div id="a1" class="page-header">
    <h2>欢迎超级管理员</h2>
</div>
<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">admin信息</a>
    </li>
</ul>
<table id="table"></table>
<div id="page" style="height: 50px; width: 60px;"></div>
<div id="msg" class="alert alert-info" style="display: none" role="alert">图片上传成功!!!</div>
<div id="upmsg" class="alert alert-info" style="display: none" role="alert">变更成功!!!</div>