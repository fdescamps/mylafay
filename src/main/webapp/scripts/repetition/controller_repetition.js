'use strict';

mylafayApp.controller('RepetitionController', function ($scope, resolvedRepetition, Repetition) {

        $scope.repetitions = resolvedRepetition;

        $scope.create = function () {
            Repetition.save($scope.repetition,
                function () {
                    $scope.repetitions = Repetition.query();
                    $('#saveRepetitionModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.repetition = Repetition.get({id: id});
            $('#saveRepetitionModal').modal('show');
        };

        $scope.delete = function (id) {
            Repetition.delete({id: id},
                function () {
                    $scope.repetitions = Repetition.query();
                });
        };

        $scope.clear = function () {
            $scope.repetition = {set_identifier: null, training_identifier: null, exercice_identifier: null, nb_of_repetitions: null, id: null};
        };
    });
