import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { PublisherService } from './../app/database/publisher.service';
import { PublisherDetailPage } from './publisher-detail';

@Component({
    selector: 'category-detail',
    templateUrl: 'category-detail.html'
})
export class CategoryDetailPage {
    category: any;
    publisherList: any[];

    constructor(public navCtrl: NavController, public navParams: NavParams, pblrService: PublisherService) {
        this.category = navParams.get("category");

        pblrService.selectByCategory(this.category.id).then(items => this.publisherList = items);
    }

    viewPublisher(publisher) {
        this.navCtrl.push(PublisherDetailPage, { publisher: publisher });
    }

}
