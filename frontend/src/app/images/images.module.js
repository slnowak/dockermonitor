/**
 * Created by novy on 08.06.15.
 */

'use strict';

import DockerImageResource from './services/DockerImageResource.js'
import DockerImageController from './controllers/DockerImageController.js'
import CreateContainerController from './controllers/CreateContainerController.js';
import CreateContainerModalController from './controllers/CreateContainerModalController.js';

let DockerImageModule = angular.module('dockerManager.dockerImagesModule', ['restangular'])
  .factory('DockerImageResource', DockerImageResource)
  .controller('DockerImageController', DockerImageController)
  .controller('CreateContainerController', CreateContainerController)
  .controller('CreateContainerModalController', CreateContainerModalController);

export default DockerImageModule;
