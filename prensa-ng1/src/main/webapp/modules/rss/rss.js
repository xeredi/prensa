(function() {
    'use strict';

    angular.module("prensa.rss.rss", [])

    .factory('RssService', RssService)

    .controller('RssController', RssController)

    ;

    function RssService($http) {
        function readChannel(url) {
            console.log("Loading RSS: " + url);

            return $http.get(url, {
                transformResponse : function(cnv) {
                    var x2js = new X2JS(/*
                                         * { datetimeAccessFormPaths : [
                                         * "rss.channel.item.pubDate" ] }
                                         */);
                    var aftCnv = x2js.xml_str2json(cnv);
                    return aftCnv;
                }
            }).then(function(response) {
                console.log("Loaded RSS: " + url);
                console.log(response.data);

                return response.data;
            });
        }

        function loadChannels(url) {
            console.log("Loading RSS Channels from: " + url);

            return $http.get(url).then(function(response) {
                console.log("Loaded RSS channnels: " + url);
                console.log(response.data);

                return response.data;
            });
        }

        return {
            readChannel : readChannel,
            loadChannels : loadChannels
        }
    }

    /* @ngInject */
    function RssController($scope, RssService) {
        var vm = this;

        $scope.publishers = [];
        $scope.news = [];

        RssService.loadChannels("conf/channels.json").then(function(data) {
            console.log("RSS Channels loaded");
            console.log(data);

            $scope.publishers = data;

            $scope.publishers.forEach(function(publisher) {
                console.log("publisher: " + publisher.name);

                RssService.readChannel(publisher.defaultChannel.url).then(function(data) {
                    $scope.news = $scope.news.concat(data.rss.channel.item)
                });

                publisher.cathegories.forEach(function(cathegory) {
                    console.log("cathegory: " + cathegory.name);

                    cathegory.channels.forEach(function(channel) {
                        console.log("channel: " + channel.name);

                        RssService.readChannel(channel.url).then(function(data) {
                            $scope.news = $scope.news.concat(data.rss.channel.item)
                        });
                    });
                });
            });
        });

        console.log($scope.news);
    }
})();