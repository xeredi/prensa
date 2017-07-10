import { DbService } from './db.service';
import { Injectable } from '@angular/core';

@Injectable()
export class FeedService extends DbService {

    selectByPublisher(pblrId: number) {
        console.log("selectByPublisher");

        return this.openSQLiteDatabase().then(() => {
            return this.db.executeSql(
                "SELECT feed_pk, feed_pblr_pk, feed_url, feed_author, feed_copyright, feed_encoding, feed_feedType, feed_generator "
                + " , feed_language, feed_link, feed_publishedDate, feed_title, feed_uri, feed_imUrl, feed_imHeight, feed_imWidth, feed_podcast "
                + " , (SELECT 1 FROM follow_feed_flfd WHERE flfd_feed_pk = feed_pk) AS feed_followed "
                + " FROM feed_feed WHERE feed_pblr_pk = " + pblrId
                , []).then(data => {
                    var items = [];
                    if (data.rows.length > 0) {
                        for (var i = 0; i < data.rows.length; i++) {
                            items.push(this.readItem(data.rows.item(i)));
                        }
                    }

                    console.log("ITEMS: " + JSON.stringify(items));

                    return items;
                });
        }).catch(e => { console.log(e); return null; });
    }

    private readItem(data: any) {
        return {
            id: data.feed_pk
            , pblrId: data.feed_pblr_pk
            , url: data.feed_url
            , author: data.feed_author
            , copyright: data.feed_copyright
            , encoding: data.feed_encoding
            , feedType: data.feed_feedType
            , generator: data.feed_generator
            , language: data.feed_language
            , link: data.feed_link
            , publishedDate: data.feed_publishedDate
            , title: data.feed_title
            , uri: data.feed_uri
            , imUrl: data.feed_imUrl
            , imHeight: data.feed_imHeight
            , imWidth: data.feed_imWidth
            , podcast: data.feed_podcast
            , followed: data.feed_followed
        };
    }
}
