'use strict';

import DockerImagesModule from './images/images.module.js';
import Routes from './routes.js';

angular.module('dockerManager', [
  DockerImagesModule.name,
  'ngAnimate',
  'ngCookies',
  'ngTouch',
  'ngSanitize',
  'ui.router',
  'ui.bootstrap'
])
  .config(Routes);
