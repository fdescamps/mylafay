'use strict';

mylafayApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/exercise', {
                    templateUrl: 'views/exercises.html',
                    controller: 'ExerciseController',
                    resolve:{
                        resolvedExercise: ['Exercise', function (Exercise) {
                            return Exercise.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
