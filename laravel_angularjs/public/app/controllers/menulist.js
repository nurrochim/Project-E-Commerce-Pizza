app.controller('menuController', function($scope, $http, API_URL) {
    //retrieve order_header listing from API
    $http.get(API_URL + "menuweb")
            .success(function(response) {
                $scope.menulist = response;
            });
    
    
});
