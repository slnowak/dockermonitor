/**
 * Created by novy on 08.06.15.
 */

'use strict';

let lodash = require('lodash');

let DockerContainerController = ($scope, DockerContainerResource, StatisticsService) => {

  let appendStatistics = statisticsWithId => {
    let containerId = statisticsWithId.containerId;
    let statistics = statisticsWithId.statistics;

    $scope.statistics.set(containerId, statistics);
  };

  let getDockerContainers = () => {
    StatisticsService.receivedStatistics()
      .then(null, null, statistics => {
        appendStatistics(statistics);
      });

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

  $scope.statistics = new Map();
  $scope.getDockerContainers();

};

DockerContainerController.$inject = ['$scope', 'DockerContainerResource', 'StatisticsService'];

export default DockerContainerController;
