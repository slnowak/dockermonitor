/**
 * Created by novy on 08.06.15.
 */

'use strict';

let endpointUrl = 'http://localhost:8080/docker/images';

let DockerImageResource = function (Restangular) {

  return Restangular.withConfig(function (RestangularConfigurer) {
    RestangularConfigurer.setBaseUrl(endpointUrl);
  }).all('');

};

DockerImageResource.$inject = ['Restangular'];

export default DockerImageResource;
