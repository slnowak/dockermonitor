/**
 * Created by novy on 08.06.15.
 */

'use strict';

let endpointUrl = 'http://localhost:8080/docker/containers';

let DockerContainerResource = function (Restangular) {

  return Restangular.withConfig(function (RestangularConfigurer) {
    RestangularConfigurer.setBaseUrl(endpointUrl);
  }).all('');

};

DockerContainerResource.$inject = ['Restangular'];

export default DockerContainerResource;
