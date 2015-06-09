/**
 * Created by novy on 08.06.15.
 */

'use strict';

import CommonModule from '../common/common.module.js';
import DockerContainerController from './controllers/DockerContainerController.js';

let DockerContainerModule = angular.module('dockerManager.dockerContainersModule', [
  CommonModule.name
])
  .controller('DockerContainerController', DockerContainerController);

export default DockerContainerModule;
