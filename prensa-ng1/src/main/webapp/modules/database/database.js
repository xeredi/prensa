(function() {
    'use strict';

    angular.module("prensa.database.database", [])

    .factory('PublisherService', PublisherService)

    .factory('ChannelGroupService', ChannelGroupService)

    .factory('ChannelService', ChannelService)

    .factory('ItemService', ItemService)

    ;

    function PublisherService(indexeddb) {
        function addPublisher(db, publisher) {
            return db.models.publishers.select("name").equal(publisher.name).find().then(
                    function(publisherFound) {
                        if (publisherFound) {
                            console.log("Duplicated publisher!");
                            console.log(publisherFound);

                            return publisherFound;
                        } else {
                            console.log("New publisher!");

                            return db.models.publishers.add(publisher).then(function(inserted) {
                                console.log("inserted");
                                console.log(inserted);

                                return inserted;
                            });
                        }
                    });
        }

        return {
            addPublisher : addPublisher
        }
    }

    function ChannelGroupService(indexeddb) {
        function addChannelGroup(db, channelGroup) {
            return db.models.channelGroups.select("name").equal(channelGroup.name).find().then(
                    function(channelGroupFound) {
                        if (channelGroupFound) {
                            console.log("Duplicated channelGroup!");

                            return channelGroupFound;
                        } else {
                            console.log("New channelGroup!");

                            return db.models.channelGroups.add(channelGroup).then(function(inserted) {
                                console.log("inserted");

                                return inserted;
                            });
                        }
                    });
        }

        return {
            addChannelGroup : addChannelGroup
        }
    }

    function ChannelService(indexeddb) {
        function addChannel(db, channel) {
            return db.models.channels.select("link").equal(channel.link).find().then(function(channelFound) {
                if (channelFound) {
                    console.log("Duplicated channel!");

                    return channelFound;
                } else {
                    console.log("New channel!");

                    return db.models.channels.add(channel).then(function(inserted) {
                        console.log("inserted");

                        return inserted;
                    });
                }
            });
        }

        return {
            addChannel : addChannel
        }
    }

    function ItemService(indexeddb) {
        function addItem(db, item) {
            console.log("Add item");

            return db.models.items.select("link").equal(item.link).find().then(function(itemFound) {
                if (itemFound) {
                    console.log("Duplicated item!");

                    return null;
                } else {
                    console.log("New item!");

                    return db.models.items.add(item).then(function(inserted) {
                        console.log("inserted");

                        return inserted;
                    });
                }
            });
        }

        return {
            addItem : addItem
        }
    }

})();