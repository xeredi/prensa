import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';

import { CategoryDetailPage } from '../category-detail'

@Component({
  selector: 'page-about',
  templateUrl: 'about.html'
})
export class AboutPage {
  categoriesList: any[];

  constructor(public navCtrl: NavController) {
    this.categoriesList = [
      { name: 'Radio', icon: 'musical-notes' }
      , { name: 'Prensa General', icon: 'paper' }
      , { name: 'Prensa Internacional', icon: 'globe' }
      , { name: 'Deportes', icon: 'football' }
      , { name: 'Economia', icon: 'logo-usd' }
      , { name: 'Cultura y Ciencia', icon: 'logo-android' }
      , { name: 'Viajes', icon: 'paper-plane' }
      , { name: 'Motor', icon: 'car' }
      , { name: 'Corazón', icon: 'heart' }
    ];
  }

  viewCategory(category) {
    this.navCtrl.push(CategoryDetailPage, { category: category });
  }

}
