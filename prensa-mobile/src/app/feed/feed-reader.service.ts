import { Injectable } from '@angular/core';

import * as feedparser from 'feedparser-promised';

@Injectable()
export class FeedReaderService {

    readFeed(feed: any) {
        return feedparser.parse(feed.url)
            .then(items => {
                var itemList = [];

                items.map(item => {
                    var itemData: any = {};

                    itemData.link = item.link;
                    itemData.title = item.title;
                    itemData.pubDate = item.pubdate;
                    itemData.author = item.author;
                    itemData.description = item.description;

                    if (item.image) {
                        itemData.thumbnailUrl = item.image.url
                        itemData.imUrl = item.image.url
                    }

                    if (item.enclosures[0]) {
                        var enclosureData: any = item.enclosures[0];

                        if (enclosureData.type.indexOf('audio') >= 0 ) {
                            itemData.enclosureUrl = enclosureData.url;
                        } else if (enclosureData.type.indexOf('image') >= 0 ) {
                            itemData.imUrl = enclosureData.url;

                            if (itemData.thumbnailUrl == null) {
                                itemData.thumbnailUrl = enclosureData.url;
                            }
                        } else {
                            console.log('Unknown enclosure Type: ' + enclosureData.type);
                        }
                    }

                    itemList.push(itemData);
                });

                return itemList;
            })
            .catch(error => {
                console.error('error: ', error);

                return null;
            });
    }
}
