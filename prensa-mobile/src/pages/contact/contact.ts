import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { NewDetailPage } from '../new-detail';

import * as feedparser from 'feedparser-promised';


@Component({
  selector: 'page-contact',
  templateUrl: 'contact.html'
})
export class ContactPage {
  resultList: any;

  constructor(public navCtrl: NavController) {
    console.log("CONTACTS PAGE!!!");

    // http://www.ondacero.es/rss/podcast/8502/podcast.xml
    // http://www.huffingtonpost.es/feeds/index.xml
    // https://recursosweb.prisaradio.com/podcasts/324p.xml


    feedparser.parse('https://recursosweb.prisaradio.com/podcasts/324p.xml').then((items) => {
      this.resultList = items;

      items.forEach(item => console.log('item:' + JSON.stringify(item)));
    }).catch(error => console.error('error: ', error));
  }


  viewNew(aNew) {
    this.navCtrl.push(NewDetailPage, { new: aNew });
  }
}
