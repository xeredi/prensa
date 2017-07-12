import { DbService } from './db.service';
import { Injectable } from '@angular/core';

@Injectable()
export class ItemService extends DbService {

    exists(item: any) {
        return this.openSQLiteDatabase().then(() => {
            return this.db.executeSql(
                "SELECT COUNT(1) FROM item_item WHERE item_link = '" + item.link + "' AND item_pblr_pk = " + item.pblrId
                , []).then(data => {
                    return data.rows.length > 0;
                });
        }).catch(e => { console.log(e); return null; });
    }

    insert(item: any) {
        this.openSQLiteDatabase().then(() => {
            this.db.executeSql(
                "INSERT INTO item_item (item_pblr_pk, item_link, item_pubDate, item_thumbnailUrl, item_imUrl, item_enclosureUrl, item_author, item_description) VALUES (" 
                + item.pblrId + ", '" + item.link + "', " + item.pubDate + ", '" + item.thumbnailUrl 
                + "', '" + item.item_imUrl + "', '" + item.item_enclosureUrl + "', '" + item.item_author + "', '" + item.item_description + "')"
                , []).then(data => data);
        }).catch(e => { console.log(e); return null; });
    }

    update(item: any) {
        this.openSQLiteDatabase().then(() => {
            this.db.executeSql(
                "UPDATE item_item SET " 
                + " item_pubDate = " + item.pubDate 
                + ", item_thumbnailUrl = '" + item.thumbnailUrl + "'" 
                + ", item_imUrl = '" + item.imUrl + "'" 
                + ", item_enclosureUrl = '" + item.enclosureUrl + "'" 
                + ", item_author = '" + item.author + "'" 
                + ", item_description = '" + item.description + "'" 
                + " WHERE item_pblr_pk = " + item.pblrId + " AND item_link = '" + item.link + "'"
                , []).then(data => data);
        }).catch(e => { console.log(e); return null; });
    }

	


}
