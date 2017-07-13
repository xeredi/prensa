import { Component, Input } from '@angular/core';

import { NavController } from 'ionic-angular';

import { NewDetailPage } from './../../pages/new-detail';

@Component({
    selector: 'item-list',
    templateUrl: 'item-list.component.html'
})
export class ItemListComponent {
    @Input() itemList: any[];

    constructor(private navCtrl: NavController) {
    }

    viewNew(anew) {
        this.navCtrl.push(NewDetailPage, { new: anew });
    }
}
