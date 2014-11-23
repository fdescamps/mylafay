'use strict';

mylafayApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/repetition', {
                    templateUrl: 'views/repetitions.html',
                    controller: 'RepetitionController',
                    resolve:{
                        resolvedRepetition: ['Repetition', function (Repetition) {
                            return Repetition.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
