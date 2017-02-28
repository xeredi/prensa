(function() {
    'use strict';

    angular.module("prensa.rss.rss", [])

    .factory('RssService', RssService)

    .controller('RssController', RssController)

    ;

    function RssService($http, indexeddb, PublisherService, ChannelGroupService, ItemService) {
        function readChannel(channel) {
            console.log("Loading RSS: " + channel.url);

            return $http.get(channel.url, {
                transformResponse : function(cnv) {
                    var x2js = new X2JS({
                        enableToStringFunc : true
                    }/*
                         * { datetimeAccessFormPaths : [
                         * "rss.channel.item.pubDate" ] }
                         */);
                    var aftCnv = x2js.xml_str2json(cnv);
                    return aftCnv;
                }
            }).then(function(responseRss) {
                console.log("Loaded RSS: " + channel.url);
                console.log(responseRss.data);

                indexeddb.then(function(db) {
                    for (var i = 0; i < responseRss.data.rss.channel.item.length; i++) {
                        var item = responseRss.data.rss.channel.item[i];

                        ItemService.addItem(db, {
                            "title" : item.title.toString(),
                            "link" : item.link,
                            "thumbnail" : item.thumbnail ? item.thumbnail._url : null,
                            "creator" : item.creator ? item.creator.toString() : null,
                            "pubDate" : Date.parse(item.pubDate),
                            "description" : item.description ? item.description.toString() : null
                        });
                    }
                });

                return responseRss.data;
            });
        }

        function loadChannels(url) {
            console.log("Loading RSS Channels from: " + url);

            return $http.get(url).then(function(response) {
                var publishers = response.data;

                console.log("Loaded RSS channnels: " + url);
                console.log(publishers);

                if (publishers) {
                    indexeddb.then(function(db) {
                        publishers.forEach(function(publisher) {
                            console.log("publisher: " + publisher.name);

                            PublisherService.addPublisher(db, {
                                "name" : publisher.name,
                                "countryCode" : publisher.countryCode
                            }).then(function(publisherAdded) {
                                if (publisher.cathegories) {
                                    publisher.cathegories.forEach(function(cathegory) {
                                        console.log("cathegory: " + cathegory.name);

                                        ChannelGroupService.addChannelGroup(db, {
                                            "name" : cathegory.name,
                                            "publisherId" : publisherAdded._id
                                        });
                                    });
                                }
                            });
                        });
                    });
                }

                return publishers;
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

            if ($scope.publishers) {
                $scope.publishers.forEach(function(publisher) {
                    console.log("publisher: " + publisher.name);

                    RssService.readChannel(publisher.defaultChannel).then(function(data) {
                        $scope.news = $scope.news.concat(data.rss.channel.item)
                    });

                    if (publisher.cathegories) {
                        publisher.cathegories.forEach(function(cathegory) {
                            console.log("cathegory: " + cathegory.name);

                            cathegory.channels.forEach(function(channel) {
                                console.log("channel: " + channel.name);

                                RssService.readChannel(channel).then(function(data) {
                                    $scope.news = $scope.news.concat(data.rss.channel.item)
                                });
                            });
                        });
                    }
                });
            }
        });

        console.log($scope.news);
    }
})();