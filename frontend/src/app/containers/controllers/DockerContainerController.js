/**
 * Created by novy on 08.06.15.
 */

'use strict';

let lodash = require('lodash');

let DockerContainerController = ($scope, DockerContainerResource, StatisticsService) => {

  let appendStatistics = statisticsWithId => {
    let containerId = statisticsWithId.containerId;
    let statistics = statisticsWithId.statistics;

    let dockerContainer = lodash.find(
      $scope.dockerContainers, container => container.basicData.containerId === containerId
    );

    if (dockerContainer !== undefined) {
      dockerContainer.statistics = statistics;
    }

  };

  let getDockerContainers = () => {
    DockerContainerResource.all('')
      .getList()
      .then(dockerContainers => $scope.dockerContainers = dockerContainers);

    StatisticsService.receivedStatistics()
      .then(null, null, statistics => {
        appendStatistics(statistics);
      });
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

DockerContainerController.$inject = ['$scope', 'DockerContainerResource', 'StatisticsService'];

export default DockerContainerController;
