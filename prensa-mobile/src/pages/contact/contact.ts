import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { NewDetailPage } from '../new-detail';

import { FeedReaderService } from './../../app/feed/feed-reader.service';

@Component({
  selector: 'page-contact',
  templateUrl: 'contact.html'
})
export class ContactPage {
  resultList: any[];

  constructor(public navCtrl: NavController, fdrdService: FeedReaderService) {
    console.log("CONTACTS PAGE!!!");

    // http://www.ondacero.es/rss/podcast/8502/podcast.xml
    // http://www.huffingtonpost.es/feeds/index.xml
    // https://recursosweb.prisaradio.com/podcasts/324p.xml


    fdrdService.readFeed('https://recursosweb.prisaradio.com/podcasts/324p.xml').then(items => this.resultList = items);
  }


  viewNew(aNew) {
    this.navCtrl.push(NewDetailPage, { new: aNew });
  }
}
