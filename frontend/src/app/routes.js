/**
 * Created by novy on 08.06.15.
 */

'use strict';

var Routes = function ($stateProvider, $urlRouterProvider) {
  $stateProvider
    .state('home', {
      url: '/',
      templateUrl: 'app/main/partials/main.html'
    })
    .state('images', {
      url: '/images',
      templateUrl: 'app/images/partials/images.html',
      controller: 'DockerImageController'
    })
    .state('containers', {
      url: '/containers',
      templateUrl: 'app/containers/partials/containers.html'
    });

  $urlRouterProvider.otherwise('/');
};

Routes.$inject = ['$stateProvider', '$urlRouterProvider'];

export default Routes;
