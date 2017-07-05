import { DbService } from './db.service';
import { Injectable } from '@angular/core';

@Injectable()
export class FeedService extends DbService {

    selectByPublisher(pblrId: number) {
        console.log("selectByPublisher");

        return this.openSQLiteDatabase().then(() => {
            return this.db.executeSql("SELECT * FROM feed_feed WHERE feed_pblr_pk = " + pblrId, []).then(data => {
                var items = [];
                if (data.rows.length > 0) {
                    for (var i = 0; i < data.rows.length; i++) {
                        items.push({
                            id: data.rows.item(i).feed_pk
                            , pblrId: data.rows.item(i).feed_pblr_pk
                            , url: data.rows.item(i).feed_url
                            , author: data.rows.item(i).feed_author
                            , copyright: data.rows.item(i).feed_copyright
                            , description: data.rows.item(i).feed_description
                            , encoding: data.rows.item(i).feed_encoding
                            , feedType: data.rows.item(i).feed_feedType
                            , generator: data.rows.item(i).feed_generator
                            , language: data.rows.item(i).feed_language
                            , link: data.rows.item(i).feed_link
                            , publishedDate: data.rows.item(i).feed_publishedDate
                            , title: data.rows.item(i).feed_title
                            , uri: data.rows.item(i).feed_uri
                            , imUrl: data.rows.item(i).feed_imUrl
                            , imHeight: data.rows.item(i).feed_imHeight
                            , imWidth: data.rows.item(i).feed_imWidth
                            , podcast: data.rows.item(i).feed_podcast
                            , subtitle: data.rows.item(i).feed_subtitle
                        });
                    }
                }

                console.log("ITEMS: " + JSON.stringify(items));

                return items;
            });
        }).catch(e => { console.log(e); return null; });
    }

}
