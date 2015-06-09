/**
 * Created by novy on 09.06.15.
 */

'use strict';

let CreateContainerModalController = ($scope, $modalInstance, imageId) => {

  $scope.containerData = {
    name: null,
    imageId: imageId,
    command: null,
    ports: null
  };

  $scope.submit = () => {
    $modalInstance.close($scope.containerData);
  };

  $scope.cancel = () => {
    $modalInstance.dismiss('cancel');
  };
};

CreateContainerModalController.$inject = ['$scope', '$modalInstance', 'imageId'];

export default CreateContainerModalController;
