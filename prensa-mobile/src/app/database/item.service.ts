import { DbService } from './db.service';
import { Injectable } from '@angular/core';

@Injectable()
export class ItemService extends DbService {

    save(item: any, feed: any) {
        this.openSQLiteDatabase().then(() => {
            this.db.executeSql(
                "SELECT item_pk FROM item_item WHERE item_link = ? AND item_pblr_pk = ?"
                , [item.link, item.pblrId])
                .then(data => {
                    if (data.rows.length > 0) {
                        // console.log('update');
                        item.id = data.rows.item(0).item_pk;

                        // console.log('id: ' + item.id);
                        this.db.executeSql(
                            "UPDATE item_item SET "
                            + " item_pubDate = ?, item_thumbnailUrl = ?, item_imUrl = ?, item_enclosureUrl = ?, item_author = ?, item_description = ? "
                            + " WHERE item_pblr_pk = ? AND item_link = ? "
                            , [item.pubDate, item.thumbnailUrl, item.item_imUrl, item.item_enclosureUrl, item.item_author, item.item_description, item.pblrId, item.link])
                            .then(data => data)
                            ;

                        this.db.executeSql(
                            "SELECT 1 FROM item_feed_itfd WHERE itfd_item_pk = ? AND itfd_feed_pk = ? "
                            , [item.id, feed.id])
                            .then(data => {
                                if (data.rows.length <= 0) {
                                    this.db.executeSql(
                                        "INSERT INTO item_feed_itfd (itfd_item_pk, itfd_feed_pk) VALUES (?, ?) "
                                        , [item.id, feed.id])
                                        .then(data => data)
                                        ;
                                }
                            })
                            ;
                    } else {
                        // console.log('insert');
                        this.db.executeSql(
                            "INSERT INTO item_item (item_pblr_pk, item_link, item_pubDate, item_thumbnailUrl, item_imUrl, item_enclosureUrl, item_author, item_description) "
                            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?) "
                            , [item.pblrId, item.link, item.pubDate, item.thumbnailUrl, item.item_imUrl, item.item_enclosureUrl, item.item_author, item.item_description])
                            .then(data => {
                                // console.log('inserted: ' + JSON.stringify(data));
                                item.id = data.insertId;

                                // console.log('id: ' + item.id);
                                this.db.executeSql(
                                    "INSERT INTO item_feed_itfd (itfd_item_pk, itfd_feed_pk) VALUES (?, ?) "
                                    , [item.id, feed.id])
                                    .then(data => data)
                                    ;

                                return data;
                            })
                            ;
                    }
                })
                ;
        }).catch(e => { console.log(e); return null; });
    }
}
