/**
 * Created by novy on 08.06.15.
 */

'use strict';

class DockerContainerController {

  constructor($scope, DockerContainerResource) {
    this.DockerContainerResource = DockerContainerResource;
    this.$scope = $scope;

    this.$scope.getDockerContainers = this.getDockerContainers;

    this.getDockerContainers();
  }

  getDockerContainers() {
    this.DockerContainerResource
      .getList()
      .then(dockerContainers => this.$scope.dockerContainers = dockerContainers);
  }
}

DockerContainerController.$inject = ['$scope', 'DockerContainerResource'];

export default DockerContainerController;
