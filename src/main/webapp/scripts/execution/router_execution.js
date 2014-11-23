'use strict';

mylafayApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/execution', {
                    templateUrl: 'views/executions.html',
                    controller: 'ExecutionController',
                    resolve:{
                        resolvedExecution: ['Execution', function (Execution) {
                            return Execution.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
