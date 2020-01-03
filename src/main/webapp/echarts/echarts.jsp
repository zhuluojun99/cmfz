<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="${path}/js/echarts.min.js"></script>
    <script src="${path}/boot/js/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <title>Document</title>

</head>

<body>

<div id="main" style="width: 1000px;height:400px;"></div>
</body>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        xAxis: {
            type: 'category',
            data: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [820, 932, 901, 934, 1290, 1330, 1320],
            type: 'line'
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
        $(function () {
            $.ajax({
                url:"${path}/Banner/getvalue",
                datatype:"json",
                success:function (data) {
                    console.log(data);
                    var option = {
                        series: [{
                            data: data
                        }]
                    };
                    myChart.setOption(option);
                }
            })
            var goEasy = new GoEasy({
                host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
                appkey: "BC-6317969708b942c694e45ddfa7b58aec", //替换为您的应用appkey
            });
            goEasy.subscribe({
                channel: "bannermonth", //替换为您自己的channel
                onMessage: function (message) {
                    console.log(message);
                    myChart.setOption({
                        series: [{
                            data: JSON.parse(message.content)
                        }]
                    });
                }
            });
    })
</script>
</html>