/**
 * Created by novy on 09.06.15.
 */

'use strict';

let CreateContainerController = ($scope, $modal) => {

  $scope.showCreationForm = (dockerImageId) => {

    let modalInstance = $modal.open({
      animation: true,
      templateUrl: 'app/images/partials/creating-container-form.html',
      controller: 'CreateContainerModalController',
      size: 'lg',
      resolve: {
        imageId: () => {
          return dockerImageId;
        }
      }
    });

    modalInstance.result
      .then(newContainerData => {
        console.log(newContainerData);
      });
  }
};

CreateContainerController.$inject = ['$scope', '$modal'];

export default CreateContainerController;
