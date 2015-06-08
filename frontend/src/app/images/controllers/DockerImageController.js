/**
 * Created by novy on 08.06.15.
 */

'use strict';

class DockerImageController {

  constructor($scope, DockerImageResource) {
    this.DockerImageResource = DockerImageResource;
    this.$scope = $scope;

    this.$scope.getDockerImages = this.getDockerImages;

    this.getDockerImages();
  }

  getDockerImages() {
    this.DockerImageResource
      .getList()
      .then(dockerImages => this.$scope.dockerImages = dockerImages);
  }
}

DockerImageController.$inject = ['$scope', 'DockerImageResource'];

export default DockerImageController;
