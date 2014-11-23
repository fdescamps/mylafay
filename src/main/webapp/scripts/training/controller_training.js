'use strict';

mylafayApp.controller('TrainingController', function ($scope, resolvedTraining, Training) {

        $scope.trainings = resolvedTraining;

        $scope.create = function () {
            Training.save($scope.training,
                function () {
                    $scope.trainings = Training.query();
                    $('#saveTrainingModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.training = Training.get({id: id});
            $('#saveTrainingModal').modal('show');
        };

        $scope.delete = function (id) {
            Training.delete({id: id},
                function () {
                    $scope.trainings = Training.query();
                });
        };

        $scope.clear = function () {
            $scope.training = {identifier: null, training_number: null, user: null, type: null, commentary: null, date: null, duration: null, warming: null, stretching: null, id: null};
        };
    });
