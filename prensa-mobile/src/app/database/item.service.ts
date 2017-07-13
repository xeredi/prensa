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
                            + " item_pubDate = ?, item_thumbnailUrl = ?, item_imUrl = ?, item_enclosureUrl = ?, item_author = ?, item_title = ?, item_description = ? "
                            + " WHERE item_pblr_pk = ? AND item_link = ? "
                            , [item.pubDate, item.thumbnailUrl, item.imUrl, item.enclosureUrl, item.author, item.title, item.description, item.pblrId, item.link])
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
                            "INSERT INTO item_item (item_pblr_pk, item_link, item_pubDate, item_thumbnailUrl, item_imUrl, item_enclosureUrl, item_author, item_title, item_description) "
                            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) "
                            , [item.pblrId, item.link, item.pubDate, item.thumbnailUrl, item.imUrl, item.enclosureUrl, item.author, item.title, item.description])
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

    selectList(limit: number, offset: number) {
        return this.openSQLiteDatabase().then(() => {
            return this.db.executeSql(
                "SELECT item_pk, item_pblr_pk, item_link, item_pubDate, item_thumbnailUrl, item_imUrl, item_enclosureUrl, item_author, item_title, item_description "
                + " , (SELECT pblr_name FROM publisher_pblr WHERE pblr_pk = item_pblr_pk) AS pblr_name "
                + " FROM item_item ORDER BY item_pubDate DESC LIMIT ? OFFSET ? "
                , [limit, offset]).then(data => {
                    var items = [];
                    if (data.rows.length > 0) {
                        for (var i = 0; i < data.rows.length; i++) {
                            items.push(this.readItem(data.rows.item(i)));
                        }
                    }

                    return items;
                });
        }).catch(e => { console.log(e); return null; });
    }

    private readItem(data: any) {
        return {
            id: data.item_pk
            , pblrId: data.item_pblr_pk
            , link: data.item_link
            , pubDate: data.item_pubDate
            , thumbnailUrl: data.item_thumbnailUrl
            , imUrl: data.item_imUrl
            , enclosureUrl: data.item_enclosureUrl
            , author: data.item_author
            , title: data.item_title
            , description: data.item_description
            , pblr: {
                id: data.item_pblr_pk
                , name: data.pblr_name
            }
        };
    }
}
