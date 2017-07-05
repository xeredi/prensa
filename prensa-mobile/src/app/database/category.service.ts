import { DbService } from './db.service';
import { Injectable } from '@angular/core';

@Injectable()
export class CategoryService extends DbService {

    selectAll() {
        console.log("CategoryService SELECTALL");

        return this.openSQLiteDatabase().then(() => {
            return this.db.executeSql("SELECT * FROM category_ctgr", []).then(data => {
                var items = [];
                if (data.rows.length > 0) {
                    for (var i = 0; i < data.rows.length; i++) {
                        items.push({
                            id: data.rows.item(i).ctgr_pk
                            , name: data.rows.item(i).ctgr_name
                            , icon: data.rows.item(i).ctgr_icon
                        });
                    }
                }

                console.log("ITEMS: " + JSON.stringify(items));

                return items;
            });
        }).catch(e => { console.log(e); return null; });
    }

}
