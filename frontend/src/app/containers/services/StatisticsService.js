/**
 * Created by novy on 11.06.15.
 */
/* global SockJS, Stomp */
'use strict';

let StatisticsService = ($q, $timeout) => {

  let service = {},
    statisticsListener = $q.defer(),
    socket = {
      client: null,
      stomp: null
    };

  service.RECONNECT_TIMEOUT = 3000;
  service.SOCKER_URL = 'http://localhost:8080/docker';
  service.STATISTICS_TOPIC = '/containers/statistics';

  service.receivedStatistics = () => {
    return statisticsListener.promise;
  };

  let startListener = () => {
    socket.stomp.subscribe(service.STATISTICS_TOPIC, statisticsData => {
        statisticsListener.notify(
          JSON.parse(statisticsData.body)
        );
      }
    );
  };

  let initialize, reconnect;

  initialize = () => {
    socket.client = new SockJS(service.SOCKER_URL);
    socket.stomp = Stomp.over(socket.client);
    socket.stomp.debug = null;
    socket.stomp.connect({}, startListener);
    socket.stomp.onclose = reconnect;
  };

  reconnect = () => {
    $timeout(() => {
      initialize();
    }, service.RECONNECT_TIMEOUT);
  };

  initialize();

  return service;
};

StatisticsService.$inject = ['$q', '$timeout'];

export default StatisticsService;
