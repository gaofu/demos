'use strict';

App.controller('myCtrl', ['$scope', '$filter', 'MessageScaleService', function ($scope, $filter, MessageScaleService) {
    $scope.accounts = [];
    $scope.currentAccount = '';

    $scope.startTime = $filter('date')((new Date() - 6 * 24 * 3600 * 1000), 'yyyy-MM-dd');
    $scope.endTime = $filter('date')(new Date(), 'yyyy-MM-dd');

    $scope.fetchMessageScale = function (token) {
        var config = {
            params: {'dateType': 'daily', 'begin': $scope.startTime, 'end': $scope.endTime}
        };

        MessageScaleService.fetchMessageScale(token, config)
            .then(
            function (data) {
                //$scope.messages = data;

                var categories = [];
                var seriesSize = [];
                var seriesCount = [];

                for (var i = 0; i < data.length; i++) {
                    categories[i] = data[i]['date'];
                    seriesSize[i] = data[i]['size'] / 1024;
                    seriesCount[i] = data[i]['count'];
                }

                $scope.chartAccount.xAxis = {"categories": categories};
                $scope.chartAccount.series = [
                    {name: 'Message Count(个)', data: seriesCount},
                    {name: 'Message Size(KB)', data: seriesSize}
                ];
            },
            function (err) {
                console.error('Error while fetching Currencies');
            }
        )
    };

    $scope.fetchTopMessageScale = function () {
        var config = {
            params: {'dateType': 'daily', 'date': $scope.endTime, 'top': 10}
        };

        MessageScaleService.fetchTopMessageScale(config)
            .then(
            function (data) {
                //$scope.topScales = data;

                if (data.length > 0) {
                    $scope.currentAccount = data[0]['name'];
                    $scope.fetchMessageScale(data[0]['token']);
                }

                var categories = [];
                var seriesSize = [];
                var seriesCount = [];

                for (var i = 0; i < data.length; i++) {
                    categories[i] = data[i]['name'];
                    seriesSize[i] = data[i]['size'] / 1024;
                    seriesCount[i] = data[i]['count'];
                }

                $scope.chartTop.xAxis = {"categories": categories};
                $scope.chartTop.series = [
                    {name: 'Message Count(个)', data: seriesCount},
                    {name: 'Message Size(KB)', data: seriesSize}
                ];
            },
            function (err) {
                console.error('Error while fetching Currencies');
            }
        )
    };

    $scope.changeAccount = function (token, name) {
        $scope.currentAccount = name;
        $scope.fetchMessageScale(token);
    };

    $scope.fetchTopMessageScale();

    $scope.chartAccount = {
        options: {
            chart: {
                type: 'line',
                zoomType: 'x'
            }
        },
        title: {
            text: '消息规模统计数据'
        },
        xAxis: {},
        yAxis: {
            min: 0,
            title: {
                text: "消息量"
            },
            labels: {
                overflow: 'justify'
            }
        },
        series: [],
        loading: false
    };

    $scope.chartTop = {
        options: {
            chart: {
                type: 'bar'
            }
        },
        title: {
            text: '消息量排名前十的微信账号'
        },
        xAxis: {
            //categories: $scope.topCategories
        },
        yAxis: {
            min: 0,
            title: {
                text: "消息量"
            },
            labels: {
                overflow: 'justify'
            }
        },
        series: [],
        loading: false
    };
}]);
