/**
 * Created by novy on 08.06.15.
 */

'use strict';

import DockerImageResource from './services/DockerImageResource.js'
import DockerImageController from './controllers/DockerImageController.js'

let DockerImageModule = angular.module('dockerManager.dockerImagesModule', ['restangular'])
  .factory('DockerImageResource', DockerImageResource)
  .controller('DockerImageController', DockerImageController);

export default DockerImageModule;
