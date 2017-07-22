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

                    var msec = Date.now() - Date.parse(item.pubdate.toDateString());
                    var timeMessage = null;

                    if (msec > 0) {
                        if (msec < 3600000) {
                            timeMessage = 'Hace ' + Math.round(msec / 60000) + " minutos";
                        } else if (msec < (24 * 3600000)) {
                            timeMessage = 'Hace ' + Math.round(msec / 3600000) + " horas";
                        }
                    }

                    itemData.pblr = feed.pblr;
                    itemData.pblrId = feed.pblrId;
                    itemData.link = item.link;
                    itemData.title = item.title;
                    itemData.pubDate = item.pubdate;
                    itemData.author = item.author;
                    itemData.description = item.description;
                    itemData.msec = msec;
                    itemData.timeMessage = timeMessage;

                    if (item.image) {
                        itemData.thumbnailUrl = item.image.url
                        itemData.imUrl = item.image.url
                    }

                    if (item.enclosures[0]) {
                        var enclosureData: any = item.enclosures[0];

                        if (enclosureData.type.indexOf('audio') >= 0) {
                            itemData.enclosureUrl = enclosureData.url;
                            itemData.enclosureLength = enclosureData.length;

                            if (enclosureData.length) {
                                // console.log('Audio length: ' + enclosureData.length);
                            }
                        } else if (enclosureData.type.indexOf('image') >= 0) {
                            itemData.imUrl = enclosureData.url;

                            if (itemData.thumbnailUrl == null) {
                                itemData.thumbnailUrl = enclosureData.url;
                            }
                        } else {
                            console.log('Unknown enclosure Type: ' + enclosureData.type);
                        }
                    }

                    if (itemData.thumbnailUrl == null) {
                        let parser = new DOMParser();
                        let parsedHtml = parser.parseFromString(itemData.description, 'text/html');

                        if (parsedHtml.images[0]) {
                            itemData.thumbnailUrl = parsedHtml.images[0].src;

                            // console.log("Image scanned: " + itemData.thumbnailUrl);
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
