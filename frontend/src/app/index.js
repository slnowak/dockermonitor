'use strict';

import DockerImagesModule from './images/images.module.js';
import DockerContainerModule from './containers/containers.module.js';
import Routes from './routes.js';

angular.module('dockerManager', [
  DockerImagesModule.name,
  DockerContainerModule.name,
  'ngAnimate',
  'ngCookies',
  'ngTouch',
  'ngSanitize',
  'ui.router',
  'ui.bootstrap'
])
  .config(Routes);
