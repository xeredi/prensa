import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { InitDbService } from './../../app/database/init-db.service';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {

  constructor(public navCtrl: NavController, initDbService: InitDbService/**/) {
    initDbService.initDb();
  }

}
