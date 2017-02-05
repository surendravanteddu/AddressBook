var ctrl = angular.module("weatherApp");

ctrl.controller("homeController", ["$scope","$http", function ($scope,$http) {
        
        $scope.message = "";
   
        
        $scope.addPerson = function(){
		$http.post('http://localhost:8080/webservices/AppUser', {name: $scope.name,address: $scope.address,phone: $scope.phone})
		.success(function(result){
			console.log("****-----------");
			console.log($scope.name);
            console.log();
			console.log(result);
                        if(result == "success"){
                        $scope.message = result+"fully added";
                    }
		})
		.error(function(data,status){
			console.log("********");
			console.log(status);
		});
	}
    }]);

ctrl.controller("forecastController", ["$scope", "$resource","$http", function ($scope, $resource,$http) {
        
        $http.get("http://localhost:8080/mobileApp/webapi/person/")
	.success(function(result){
		$scope.data = result;
	});
    }]);