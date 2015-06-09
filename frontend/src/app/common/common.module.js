/**
 * Created by novy on 09.06.15.
 */

'use strict';

import DockerContainerResource from './services/DockerContainerResource.js'

let CommonModule = angular.module('dockerManager.commonModule', ['restangular'])
  .factory('DockerContainerResource', DockerContainerResource);

export default CommonModule;
