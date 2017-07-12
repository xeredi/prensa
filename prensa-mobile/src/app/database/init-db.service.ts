import { Platform } from 'ionic-angular';
import { DbService } from './db.service';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class InitDbService extends DbService {
    constructor(private http: Http, private platform: Platform) {
        super();
    }

    initDb() {
        console.log("INIT DB");

        this.platform.ready().then(readySource => {
            this.openSQLiteDatabase().then(() => {
                console.log("EXECUTE INIT DB");

                this.db.executeSql("DROP TABLE IF EXISTS category_ctgr", []);
                this.db.executeSql("CREATE TABLE category_ctgr(ctgr_pk INTEGER PRIMARY KEY, ctgr_name VARCHAR(20), ctgr_icon VARCHAR(20))", []);
                this.db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (1, 'Radio', 'musical-notes')", []);
                this.db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (2, 'Prensa General', 'paper')", []);
                this.db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (3, 'Prensa Digital', 'finger-print')", []);
                this.db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (4, 'Prensa Internacional', 'globe')", []);
                this.db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (5, 'Deportes', 'football')", []);
                this.db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (6, 'Economia', 'logo-usd')", []);
                this.db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (7, 'Cultura y Ciencia', 'logo-android')", []);
                this.db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (8, 'Viajes', 'paper-plane')", []);
                this.db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (9, 'Motor', 'car')", []);
                this.db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (10, 'Corazón', 'heart')", []);

                this.db.executeSql("DROP TABLE IF EXISTS publisher_pblr", []);
                this.db.executeSql("CREATE TABLE publisher_pblr(pblr_pk INTEGER PRIMARY KEY, pblr_ctgr_pk INTEGER, pblr_lang VARCHAR(2), pblr_country VARCHAR(2), pblr_name VARCHAR(50), pblr_webUrl VARCHAR(200), pblr_logoUrl VARCHAR(200))", []);

                this.http.get("assets/publishers.json").subscribe((res) => {
                    var publishers = res.json().publishers;

                    for (var i in publishers) {
                        this.db.executeSql(publishers[i], []);
                    }
                });

                this.db.executeSql("DROP TABLE IF EXISTS feed_feed", []);
                this.db.executeSql("CREATE TABLE feed_feed(feed_pk INTEGER PRIMARY KEY, feed_pblr_pk INTEGER, feed_url VARCHAR(200), feed_author VARCHAR(50), feed_copyright VARCHAR(50), feed_encoding VARCHAR(20), feed_feedType VARCHAR(20), feed_generator VARCHAR(50), feed_language VARCHAR(2), feed_link VARCHAR(200), feed_publishedDate DATETIME, feed_title VARCHAR(200), feed_uri VARCHAR(200), feed_imUrl VARCHAR(200), feed_imHeight INTEGER, feed_imWidth INTEGER, feed_podcast BOOLEAN, CONSTRAINT uq_feed_url UNIQUE (feed_url))", []);

                this.http.get("assets/feeds.json").subscribe((res) => {
                    var feeds = res.json().feeds;

                    for (var i in feeds) {
                        this.db.executeSql(feeds[i], []);
                    }
                });

                this.db.executeSql("CREATE TABLE IF NOT EXISTS item_item(item_pk INTEGER PRIMARY KEY, item_pblr_pk INTEGER, item_link VARCHAR(200), item_pubDate DATETIME, item_thumbnailUrl VARCHAR(200), item_imUrl VARCHAR(200), item_enclosureUrl VARCHAR(200), item_author VARCHAR(50), item_description VARCHAR(200), CONSTRAINT uq_item_link UNIQUE (item_pblr_pk, item_link))", []);

                this.db.executeSql("CREATE TABLE IF NOT EXISTS item_feed_itfd(itfd_item_pk INTEGER, itfd_feed_pk INTEGER, CONSTRAINT uq_itfd UNIQUE (itfd_item_pk, itfd_feed_pk))", []);

                this.db.executeSql("CREATE TABLE IF NOT EXISTS follow_feed_flfd(flfd_feed_pk INTEGER PRIMARY KEY)", []);
            }).catch(e => { console.log(e); });
        });
    }
}
