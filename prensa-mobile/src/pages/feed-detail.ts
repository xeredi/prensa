import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { FeedReaderService } from './../app/feed/feed-reader.service';

import { NewDetailPage } from './new-detail';

@Component({
    selector: 'feed-detail',
    templateUrl: 'feed-detail.html'
})
export class FeedDetailPage {
    feed: any;
    newList: any[];

    constructor(public navCtrl: NavController, public navParams: NavParams, fdrdService: FeedReaderService) {
        this.feed = navParams.get("feed");

        fdrdService.readFeed(this.feed.url).then(items => {
            this.newList = items;

            this.newList.map(item => {
                console.log("new: " + JSON.stringify(item));
            });
        });
    }

    viewNew(anew) {
        this.navCtrl.push(NewDetailPage, { new: anew });
    }
}
