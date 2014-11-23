'use strict';

mylafayApp.controller('ExecutionController', function ($scope, resolvedExecution, Execution) {

        $scope.executions = resolvedExecution;

        $scope.create = function () {
            Execution.save($scope.execution,
                function () {
                    $scope.executions = Execution.query();
                    $('#saveExecutionModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.execution = Execution.get({id: id});
            $('#saveExecutionModal').modal('show');
        };

        $scope.delete = function (id) {
            Execution.delete({id: id},
                function () {
                    $scope.executions = Execution.query();
                });
        };

        $scope.clear = function () {
            $scope.execution = {training_identifier: null, exercice_identifier: null, set: null, commentary: null, difficulty: null, id: null};
        };
    });
