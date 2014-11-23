'use strict';

mylafayApp.factory('Exercise', function ($resource) {
        return $resource('app/rest/exercises/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
