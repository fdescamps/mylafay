'use strict';

mylafayApp.factory('Repetition', function ($resource) {
        return $resource('app/rest/repetitions/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
