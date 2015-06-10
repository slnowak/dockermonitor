/**
 * Created by novy on 08.06.15.
 */

'use strict';

let DockerContainerController = ($scope, DockerContainerResource) => {

  let getDockerContainers = () => {
    DockerContainerResource.all('')
      .getList()
      .then(dockerContainers => $scope.dockerContainers = dockerContainers);
  };

  $scope.getDockerContainers = getDockerContainers;

  $scope.performContainerAction = (dockerContainerId, actionType) => {
    DockerContainerResource.one(dockerContainerId)
      .patch({
        value: actionType
      })
      .then(() => {
        getDockerContainers();
      });
  };

  $scope.toMegaBytes = (bytes) => {
    return bytes / (1024 * 1024);
  };

  $scope.getDockerContainers();

};

DockerContainerController.$inject = ['$scope', 'DockerContainerResource'];

export default DockerContainerController;
