app.controller('orderController', function($scope, $http, API_URL) {
    //retrieve order_header listing from API
    $http.get(API_URL + "order_header")
            .success(function(response) {
                $scope.order_headers = response;
            });
    
    //show modal form
    $scope.toggle = function(modalstate, id, subTotal, total, token) {
        $scope.modalstate = modalstate;
        $scope.form_title = "Order Detail "+ id;
        $scope.form_sub_total = subTotal;
        $scope.form_total = total;
        $scope.form_token = token;
                $scope.id = id;
                $http.get(API_URL + 'order_detail/'+id)
                        .success(function(response) {
                            console.log(response);
                            $scope.order_details = response;
                        });
        console.log(id);
        $('#myModal').modal('show');                
    };
    
    // update status order
    $scope.updateStatusOrder = function(idOrder, status,  mgBody, msgTitle) {
        var url = API_URL + "sendMessage/"+$scope.id+"/"+status+"/"+mgBody+"/"+msgTitle+"/"+$scope.form_token;
        
        $http({
            method: 'POST',
            url: url,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(response) {
            console.log(response);
            // edit status order
            var urlUpdate = API_URL + "order_header/"+$scope.id+"/"+status+"/"+msgTitle;
            $http({
                method: 'POST',
                url: urlUpdate,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }).success(function(response) {
                console.log(response);
                location.reload();
            }).error(function(response) {
                console.log(response);
                alert('This is embarassing. An error has occured. Please check the log for details');
            });
            
        }).error(function(response) {
            console.log(response);
            alert('This is embarassing. An error has occured. Please check the log for details');
        });
        
        
    };
});
