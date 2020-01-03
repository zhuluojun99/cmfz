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

<div id="main" style="width: 800px;height:400px;"></div>
</body>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['day1', 'day2', 'day3', 'day4', 'day5', 'day6', 'day7'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'直接访问',
                type:'bar',
                barWidth: '60%',
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
        $(function () {
            $.ajax({
                url:"${path}/Banner/getday",
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
                channel: "day", //替换为您自己的channel
                onMessage: function (message) {
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