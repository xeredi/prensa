import { Component, OnInit } from '@angular/core';

import { ItemService } from './../../app/database/item.service';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage implements OnInit {
  itemList: any[];

  constructor(private itemService: ItemService) {
  }

  ngOnInit() {
    this.itemService.selectList(20, 1).then(list => {
      this.itemList = list;

      // console.log('List: ' + JSON.stringify(this.itemList));
    });
  }
}
