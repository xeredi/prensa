import { Injectable } from '@angular/core';

import { ItemService } from './../database/item.service';
import { FeedReaderService } from './feed-reader.service';
import { FeedService } from './../database/feed.service';

@Injectable()
export class FeedReloaderService {

    constructor(private feedService: FeedService, private itemService: ItemService, private feedReaderService: FeedReaderService) {
    }

    readFeeds() {
        this.feedService.selectFollowed().then(feedList => {
            feedList.map(feed => {
                console.log('Reload feed: ' + JSON.stringify(feed));

                this.feedReaderService.readFeed(feed).then(newList => {
                    newList.map(item => {
                        this.itemService.save(item, feed);
                    });
                });
            });
        });
    }
}
