<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>demo</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="http://apps.bdimg.com/libs/jquery/1.8.2/jquery.min.js"></script>
    <script src="http://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.angularjs.org/1.4.8/angular.min.js"></script>
    <script src="./static/js/highcharts-ng.js"></script>
    <script src="./static/js/app.js"></script>
    <script src="./static/js/service/message_scale.js"></script>
    <script src="./static/js/controller/message_scale.js"></script>
    <style>
        .generic-container {
            width: 60%;
            margin: 20px auto;
            padding: 20px;
            background-color: #EAE7E7;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-shadow: 0 0 30px black;
        }
    </style>
</head>
<body ng-app="myApp">
<h2 class="generic-container">新报表演示</h2>

<div class="generic-container" ng-controller="myCtrl">
    <%--<div class="panel panel-default">--%>
    <%--<div class="panel-heading"><span class="lead">演示账号列表</span></div>--%>
    <%--<div class="panel panel-group" >--%>
    <%--<table class="table table-hover">--%>
    <%--<tr ng-repeat="a in accounts">--%>
    <%--<td>--%>
    <%--<button type="button" ng-click="changeAccount(a.token, a.name)" class="btn btn-success custom-width">--%>
    <%--<span class="glyphicon glyphicon-user" ng-bind="a.name"></span>--%>
    <%--</button>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--</table>--%>
    <%--</div>--%>
    <%--</div>--%>

    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">消息规模统计数据（{{ currentAccount }}）</span></div>
        <span ng-bind="startTime"></span><br>
        <span ng-bind="endTime"></span>
        <highchart id="chartAccount" config="chartAccount"></highchart>

    </div>

    <div class="panel panel-default">
        <div class="panel-heading"><span class="lead">今天消息量前十的微信账号</span></div>
        <highchart id="chartTop" config="chartTop"></highchart>
    </div>
</div>

</body>
</html>
