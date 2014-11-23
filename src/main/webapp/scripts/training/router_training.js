'use strict';

mylafayApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/training', {
                    templateUrl: 'views/trainings.html',
                    controller: 'TrainingController',
                    resolve:{
                        resolvedTraining: ['Training', function (Training) {
                            return Training.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
