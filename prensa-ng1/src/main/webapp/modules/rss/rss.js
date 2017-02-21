(function() {
    'use strict';

    angular.module("prensa.rss.rss", [])

    .factory('RssService', RssService)

    .controller('RssController', RssController)

    ;

    function RssService($http, $indexedDB) {
        function readChannel(url) {
            console.log("Loading RSS: " + url);

            return $http.get(url, {
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
                console.log("Loaded RSS: " + url);
                console.log(responseRss.data);

                $indexedDB.openStore("items", function(store) {
                    console.log("Storing items");

                    var items = [];

                    for (var i = 0; i < responseRss.data.rss.channel.item.length; i++) {
                        var item = responseRss.data.rss.channel.item[i];

                        items.push({
                            "title" : item.title.toString(),
                            "link" : item.link,
                            "thumbnail" : item.thumbnail ? item.thumbnail._url : null,
                            "creator" : item.creator ? item.creator.toString() : null,
                            "pubDate" : Date.parse(item.pubDate),
                            "description" : item.description ? item.description.toString() : null
                        });
                    }

                    //
                    // for (var i = 0; i <
                    // responseRss.data.rss.channel.item.length; i++) {
                    // var item = responseRss.data.rss.channel.item[i];
                    //
                    // console.log("Exists: " + item.link);
                    //
                    // store.findBy("ix_items_link",
                    // item.ĺink).then(function(foundItem) {
                    // if (foundItem != null) {
                    // console.log("- Duplicate entry: ");
                    // console.log(foundItem);
                    //
                    // items.push({
                    // "title" : item.title.toString(),
                    // "link" : item.link,
                    // "thumbnail" : item.thumbnail ? item.thumbnail._url :
                    // null,
                    // "creator" : item.creator ? item.creator.toString() :
                    // null,
                    // "pubDate" : Date.parse(item.pubDate),
                    // "description" : item.description ?
                    // item.description.toString() : null
                    // });
                    // }
                    // });
                    // }

                    console.log(items);

                    if (items.length > 0) {
                        store.insert(items).then(function(inserted) {
                            console.log("Inserted");
                            console.log(inserted);
                        });
                    }
                });

                return responseRss.data;
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

            if ($scope.publishers) {
                $scope.publishers.forEach(function(publisher) {
                    console.log("publisher: " + publisher.name);

                    RssService.readChannel(publisher.defaultChannel.url).then(function(data) {
                        $scope.news = $scope.news.concat(data.rss.channel.item)
                    });

                    if (publisher.cathegories) {
                        publisher.cathegories.forEach(function(cathegory) {
                            console.log("cathegory: " + cathegory.name);

                            cathegory.channels.forEach(function(channel) {
                                console.log("channel: " + channel.name);

                                RssService.readChannel(channel.url).then(function(data) {
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