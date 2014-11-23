'use strict';

mylafayApp.controller('ExerciseController', function ($scope, resolvedExercise, Exercise) {

        $scope.exercises = resolvedExercise;

        $scope.create = function () {
            Exercise.save($scope.exercise,
                function () {
                    $scope.exercises = Exercise.query();
                    $('#saveExerciseModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.exercise = Exercise.get({id: id});
            $('#saveExerciseModal').modal('show');
        };

        $scope.delete = function (id) {
            Exercise.delete({id: id},
                function () {
                    $scope.exercises = Exercise.query();
                });
        };

        $scope.clear = function () {
            $scope.exercise = {identifier: null, type: null, name: null, commentary: null, id: null};
        };
    });
