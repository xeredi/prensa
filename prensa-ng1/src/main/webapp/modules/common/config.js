(function() {
    'use strict';

    angular.module(
            "prensa.common.config",
            [ "ngRoute", "ngSanitize", "pascalprecht.translate", "LocalStorageModule", "indexed-db",
                    "prensa.rss.rss", "prensa.database.database" ])

    .config(translateProvider_config)

    .config(localStorageServiceProvider_config)

    .config(indexeddb_config)

    .run(run)

    ;

    /* @ngInject */
    function translateProvider_config($translateProvider) {
        $translateProvider.useSanitizeValueStrategy('escape');
    }

    /* @ngInject */
    function localStorageServiceProvider_config(localStorageServiceProvider) {
        localStorageServiceProvider.setPrefix('argo');
    }

    function indexeddb_config(indexeddbProvider) {
        indexeddbProvider.setDbName("xeredi.prensa.db");
        indexeddbProvider.setDbVersion(1);

        var tables = [ {
            name : "publishers",
            fields : [ {
                name : '_id',
                keyPath : true
            }, {
                name : "name",
                unique : true
            }, {
                name : "countryCode"
            } ]
        }, {
            name : "channelGroups",
            fields : [ {
                name : '_id',
                keyPath : true
            }, {
                name : "name"
            }, {
                name : "publisherId"
            } ]
        }, {
            name : "channels",
            fields : [ {
                name : '_id',
                keyPath : true
            }, {
                name : "publisherId"
            }, {
                name : "title"
            }, {
                name : "selfLink",
                unique : true
            }, {
                name : "description"
            }, {
                name : "language"
            }, {
                name : "pubDate"
            }, {
                name : "lastBuildDate"
            }, {
                name : "ttl"
            }, {
                name : "imageLink"
            } ]
        }, {
            name : "items",
            fields : [ {
                name : '_id',
                keyPath : true
            }, {
                name : "link",
                unique : true
            }, {
                name : "publisherId"
            }, {
                name : "title"
            }, {
                name : "thumbnail"
            }, {
                name : "creator"
            }, {
                name : "pubDate"
            }, {
                name : "description"
            } ]
        } ];

        indexeddbProvider.setDbTables(tables);
    }

    /* @ngInject */
    function run($rootScope) {
        $rootScope.dateFormat = "dd/MM/yyyy";
        $rootScope.datetimeFormat = "dd/MM/yyyy HH:mm";
    }
})();