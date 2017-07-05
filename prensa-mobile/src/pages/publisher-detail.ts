import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { FeedService } from './../app/database/feed.service';

@Component({
    selector: 'publisher-detail',
    templateUrl: 'publisher-detail.html'
})
export class PublisherDetailPage {
    publisher: any;
    feedList: any[];

    constructor(public navCtrl: NavController, public navParams: NavParams, feedService: FeedService) {
        this.publisher = navParams.get("publisher");

        feedService.selectByPublisher(this.publisher.id).then(items => this.feedList = items);
    }

}
