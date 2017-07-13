import { DbService } from './db.service';
import { Injectable } from '@angular/core';

@Injectable()
export class PublisherService extends DbService {

    selectByCategory(pblrId: number) {
        console.log("PublisherService SELECBYCATEGORY");

        return this.openSQLiteDatabase().then(() => {
            return this.db.executeSql("SELECT * FROM publisher_pblr WHERE pblr_ctgr_pk = " + pblrId, []).then(data => {
                var items = [];
                if (data.rows.length > 0) {
                    for (var i = 0; i < data.rows.length; i++) {
                        items.push({
                            id: data.rows.item(i).pblr_pk
                            , ctgrId: data.rows.item(i).pblr_ctgr_pk
                            , lang: data.rows.item(i).pblr_lang
                            , country: data.rows.item(i).pblr_country
                            , name: data.rows.item(i).pblr_name
                            , webUrl: data.rows.item(i).pblr_webUrl
                            , logoUrl: data.rows.item(i).pblr_logoUrl
                        });
                    }
                }

                // console.log("ITEMS: " + JSON.stringify(items));

                return items;
            });
        }).catch(e => { console.log(e); return null; });
    }

}
