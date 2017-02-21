(function() {
    'use strict';

    angular.module("prensa.database.database", [])

    .factory('ItemService', ItemService)

    ;

    function ItemService($window, $q) {
        var indexedDB = $window.indexedDB;
        var db = null;
        var tableName = "items";
        var indexLinkName = "ix_items_link";

        function open() {
            var deferred = $q.defer();
            var version = 10;
            var request = indexedDB.open("xeredi.prensa.db");

            request.onupgradeneeded = function(e) {
                console.log("Upgrade Needed!!");

                db = e.target.result;

                e.target.transaction.onerror = indexedDB.onerror;

                if (db.objectStoreNames.contains(tableName)) {
                    db.deleteObjectStore(tableName);
                }

                // var store = db.createObjectStore(tableName, {
                // keyPath : "link"
                // });

                console.log("Create table: " + tableName);

                var store = db.createObjectStore(tableName, {
                    keyPath : "id",
                    autoIncrement : true
                });

                console.log("Create index: " + indexLinkName);

                store.createIndex(indexLinkName, "link", {
                    unique : false
                });
            };

            request.onsuccess = function(e) {
                console.log("Database Opened");

                db = e.target.result;
                deferred.resolve();
            };

            request.onerror = function(e) {
                console.log("Error opening Database");
                console.log(e.value);

                deferred.reject();
            };

            return deferred.promise;
        }

        function addItem(item) {
            console.log("- addItem");
            console.log(item);

            var deferred = $q.defer();

            if (db === null) {
                deferred.reject("IndexDB is not opened yet!");
            } else {
                var trans = db.transaction([ tableName ], "readwrite");
                var store = trans.objectStore(tableName);
                var request = store.put({
                    "link" : item.link,
                    "title" : item.title
                });

                request.onsuccess = function(e) {
                    deferred.resolve();
                };

                request.onerror = function(e) {
                    console.log(e.value);
                    deferred.reject("Todo item couldn't be added!: " + item);
                };
            }
            return deferred.promise;
        }

        function existsItem(item) {

        }

        function findItemByLink(link) {

        }

        return {
            open : open,
            addItem : addItem,
            existsItem : existsItem,
            findItemByLink : findItemByLink
        }
    }
})();