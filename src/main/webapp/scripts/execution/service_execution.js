'use strict';

mylafayApp.factory('Execution', function ($resource) {
        return $resource('app/rest/executions/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
