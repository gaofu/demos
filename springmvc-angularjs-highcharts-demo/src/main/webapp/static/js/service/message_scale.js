'use strict';

App.factory("MessageScaleService", ['$http', '$q', function ($http, $q) {

    return {
        fetchMessageScale: function (token, config) {
            return $http.get('/demo/' + token, config)
                .then(
                function (response) {
                    return response.data
                },
                function (errResponse) {
                    console.error("获取账号消息规模数据异常");
                    return $q.reject(errResponse)
                }
            )
        },

        fetchTopMessageScale: function (config) {
            return $http.get('/demo/top', config)
                .then(
                function (response) {
                    return response.data;
                },
                function (errResponse) {
                    console.error("获取账号消息规模排名靠前的账户数据异常");
                    return $q.reject(errResponse)
                }
            )
        }
    };

}]);
