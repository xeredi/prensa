import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { CategoryService } from './../../app/database/category.service';
import { CategoryDetailPage } from '../category-detail'

@Component({
  selector: 'page-about',
  templateUrl: 'about.html'
})
export class AboutPage {
  categoriesList: any[];

  constructor(public navCtrl: NavController, ctgrService: CategoryService) {
    console.log("CATEGORY LIST!!");

    ctgrService.selectAll().then(items => this.categoriesList = items);
  }

  viewCategory(category) {
    this.navCtrl.push(CategoryDetailPage, { category: category });
  }

}
