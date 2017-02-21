(function() {
    'use strict';

    angular.module(
            "prensa.common.config",
            [ "ngRoute", "ngSanitize", "pascalprecht.translate", "LocalStorageModule", "indexedDB",
                    "prensa.rss.rss", "prensa.database.database" ])

    .config(translateProvider_config)

    .config(localStorageServiceProvider_config)

    .config(indexedDB_config)

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

    /* @ngInject */
    function indexedDB_config($indexedDBProvider) {
        $indexedDBProvider.connection("xeredi.prensa.db").upgradeDatabase(1, function(event, db, tx) {
            console.log("Create table: items");

            var store = db.createObjectStore("items", {
                keyPath : "id",
                autoIncrement : true
            });

            console.log("Create index: ix_items_link");

            store.createIndex("ix_items_link", "link", {
                unique : false
            });
        });
    }

    /* @ngInject */
    function run($rootScope) {
        $rootScope.dateFormat = "dd/MM/yyyy";
        $rootScope.datetimeFormat = "dd/MM/yyyy HH:mm";
    }
})();