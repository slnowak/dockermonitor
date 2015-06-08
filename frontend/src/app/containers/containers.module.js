/**
 * Created by novy on 08.06.15.
 */

'use strict';

import DockerContainerResource from './services/DockerContainerResource.js'
import DockerContainerController from './controllers/DockerContainerController.js'

let DockerContainerModule = angular.module('dockerManager.dockerContainersModule', ['restangular'])
  .factory('DockerContainerResource', DockerContainerResource)
  .controller('DockerContainerController', DockerContainerController);

export default DockerContainerModule;
