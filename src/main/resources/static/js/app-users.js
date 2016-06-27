var taskManagerModule = angular.module('taskManagerApp', ['ngAnimate']);

taskManagerModule.controller('taskManagerController', function ($scope, $http) {
	
	var urlBase="";
	$scope.toggle=true;
	$scope.selection = [];
	$scope.statuses=['ACTIVE','COMPLETED'];
	$scope.priorities=['HIGH','LOW','MEDIUM'];
	$http.defaults.headers.post["Content-Type"] = "application/json";

    function findAllUsers() {
        // get all users and display initially
        $http.get(urlBase + '/users/search/findByDeleted?deleted=0').
            success(function (data) {
                if (data._embedded != undefined) {
                    $scope.users = data._embedded.users;
                } else {
                    $scope.users = [];
                }
                
                for (var i = 0; i < $scope.users.length; i++) {
                    if ($scope.users[i].deleted == 1) {
                        $scope.selection.push($scope.users[i].userId);
                    }
                }
                
                $scope.userName="";
                $scope.firstName="";
                $scope.lastName="";
                $scope.email="";
                $scope.toggle='!toggle';
            });
    }

    findAllUsers();

	// Add a new user
	$scope.addUser = function addUser() {
		if ($scope.userName=="" || $scope.firstName=="" || $scope.lastName == "" || $scope.email == "") {
			alert("Insufficient data! please provide values for user name, first name, last name and email");
		}
		else {
		 $http.post(urlBase + '/users', {
			 userName: $scope.userName,
			 firstName: $scope.firstName,
			 lastName: $scope.lastName,
			 email: $scope.email
         }).
		  success(function(data, status, headers) {
			 alert("User added");
             findAllUsers();
		    });
		}
	};
		
	// toggle selection for a given user by user id
	$scope.toggleSelection = function toggleSelection(taskUri) {
	    var idx = $scope.selection.indexOf(taskUri);

	    // is currently selected
	    if (idx > -1) {
	      $scope.selection.splice(idx, 1);
	    }
	    else {
	      $scope.selection.push(taskUri);
	    }
	    
	  };
	  
	  // Delete marked users
	  $scope.deleteUsers = function deleteUsers() {
		  
          $scope.selection.forEach( function(taskUri) {
              if (taskUri != undefined) {
                  $http.patch(taskUri, {deleted : 1}).
                  success(function(data) {
                	  console.log("Deleted user: " + taskUri);
                	  findAllUsers();
                  });
              }
          });
          
          alert("Successfully Deleted");
          findAllUsers();
	  };
	
});

//Angularjs Directive for confirm dialog box
taskManagerModule.directive('ngConfirmClick', [

    function() {
    	
         return {
             link: function (scope, element, attr) {
                 var msg = attr.ngConfirmClick || "Are you sure?";
                 var clickAction = attr.confirmedClick;
                 
                 element.bind('click',function (event) {
                     if ( window.confirm(msg) ) {
                         scope.$eval(clickAction);
                     }
                 });
             }
         };
 }] );
