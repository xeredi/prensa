import { Component, OnInit } from '@angular/core';

import { ItemService } from './../../app/database/item.service';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage implements OnInit {
  itemList: any[];
  offset: number;
  limit: number = 20;

  constructor(private itemService: ItemService) {
    this.offset = 1;
    this.itemList = [];
  }

  ngOnInit() {
    this.doSearch();
  }

  doInfinite(infiniteScroll) {
    console.log('Begin infiniteScroll');
    this.offset += this.limit;

    this.doSearch().then(listLength => {
      infiniteScroll.complete();

      if (listLength < this.limit) {
        console.log('No More Items!!!');

        infiniteScroll.enabled = false;
      }
    });
  }

  doRefresh(refresher) {
    console.log('Begin doRefresh', refresher);

    this.offset = 1;
    this.itemList = [];

    this.doSearch().then(listLength => {
      refresher.complete();
    });
  }

  private doSearch() {
    return this.itemService.selectList(this.limit, this.offset)
      .then(list => {
        list.map(item => this.itemList.push(item));

        // console.log('List: ' + JSON.stringify(this.itemList));

        return list.length;
      })
  }
}
