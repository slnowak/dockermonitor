/**
 * Created by novy on 09.06.15.
 */

'use strict';

let objectAssign = require('object-assign');

let CreateContainerModalController = ($scope, $modalInstance, imageId) => {

  $scope.containerData = {
    imageId: imageId
  };

  let nonEmptyValue = (value) => {
    return value !== undefined && value !== null && value !== '';
  };

  let withoutEmptyFields = (containerData) => {
    let nullableFields = ['name', 'command'];

    let containerDataCopy = objectAssign({}, containerData);
    nullableFields.forEach(field => {
      if (!nonEmptyValue(containerDataCopy[field])) {
        delete containerDataCopy[field];
      }
    });

    let portsProperty = 'ports';
    if (containerDataCopy.hasOwnProperty(portsProperty) &&
      (!nonEmptyValue(containerDataCopy[portsProperty].outer) || !nonEmptyValue(containerDataCopy[portsProperty].inner))) {

      delete containerDataCopy[portsProperty]
    }

    return containerDataCopy;
  };

  $scope.submit = () => {
    $modalInstance.close(withoutEmptyFields($scope.containerData));
  };

  $scope.cancel = () => {
    $modalInstance.dismiss('cancel');
  };

  $scope.nonEmptyValue = nonEmptyValue;
};

CreateContainerModalController.$inject = ['$scope', '$modalInstance', 'imageId'];

export default CreateContainerModalController;
