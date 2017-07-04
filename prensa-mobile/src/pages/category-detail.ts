import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

@Component({
    selector: 'category-detail',
    templateUrl: 'category-detail.html'
})
export class CategoryDetailPage {
    category: any;
    publisherList: any[];

    constructor(public navCtrl: NavController, public navParams: NavParams) {
        this.category = navParams.get("category");

        this.publisherList = [
            { name: 'Cadena Ser', thumbnail: 'http://cope-cdnmed.agilecontent.com//img/default-small.jpg' }
            , { name: 'Cadena Cope', thumbnail: 'http://cope-cdnmed.agilecontent.com//img/default-small.jpg' }
            , { name: 'Onda Cero', thumbnail: 'http://cope-cdnmed.agilecontent.com//img/default-small.jpg' }
            , { name: 'Rac1', thumbnail: 'http://cope-cdnmed.agilecontent.com//img/default-small.jpg' }
        ];
    }

}
