import { Component } from '@angular/core';

import { CategoryListPage } from './../category-list';
import { ContactPage } from '../contact/contact';
import { HomePage } from '../home/home';

@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root = HomePage;
  tab2Root = CategoryListPage;
  tab3Root = ContactPage;

  constructor() {

  }
}
