'use strict';

mylafayApp.factory('Training', function ($resource) {
        return $resource('app/rest/trainings/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
