/**
 * Created by novy on 08.06.15.
 */

'use strict';

let endpointUrl = 'http://localhost:8080/docker/containers';

let DockerContainerResource = (Restangular) => {

  return Restangular.withConfig((RestangularConfigurer) => {
    RestangularConfigurer.setBaseUrl(endpointUrl);
  });

};

DockerContainerResource.$inject = ['Restangular'];

export default DockerContainerResource;
