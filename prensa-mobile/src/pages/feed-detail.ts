import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { NewDetailPage } from './new-detail';

import { ItemService } from './../app/database/item.service';
import { FeedReaderService } from './../app/feed/feed-reader.service';
import { FollowFeedService } from './../app/database/follow-feed.service';

@Component({
    selector: 'feed-detail',
    templateUrl: 'feed-detail.html'
})
export class FeedDetailPage {
    feed: any;
    newList: any[];

    constructor(public navCtrl: NavController, public navParams: NavParams
        , fdrdService: FeedReaderService, private flfdService: FollowFeedService, private itemService: ItemService) {
        this.feed = navParams.get("feed");

        console.log("read feed: " + this.feed.url);

        fdrdService.readFeed(this.feed).then(items => {
            this.newList = items;

            if (this.feed.followed) {
                console.log("save feed!");
                this.newList.map(item => {
                    // console.log("save item: " + JSON.stringify(item));
                    itemService.save(item, this.feed);
                });
            }
        });
    }

    viewNew(anew) {
        this.navCtrl.push(NewDetailPage, { new: anew });
    }

    followFeed() {
        this.flfdService.insert(this.feed);
    }

    unfollowFeed() {
        this.flfdService.delete(this.feed);
    }
}
