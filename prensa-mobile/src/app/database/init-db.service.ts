import { DbService } from './db.service';
import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class InitDbService extends DbService {
    constructor(private http: Http) {
        super();
    }

    initDb() {
        console.log("INIT DB");

        this.openSQLiteDatabase().then(() => {
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
            this.db.executeSql("CREATE TABLE publisher_pblr(pblr_pk INTEGER PRIMARY KEY AUTOINCREMENT, pblr_ctgr_pk INTEGER, pblr_lang VARCHAR(2), pblr_country VARCHAR(2), pblr_name VARCHAR(50), pblr_webUrl VARCHAR(200), pblr_logoUrl VARCHAR(200))", []);
            this.db.executeSql("INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (5, 'es', 'ES', 'Marca', 'http://www.marca.com/deporte/rss/', 'http://e00-marca.uecdn.es/assets/v6/img/logo-marca.png')", []);
            this.db.executeSql("INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (5, 'es', 'ES', 'Sport', 'http://www.sport.es/es/rss/', 'http://www.sport.es/img/logo.png')", []);
            this.db.executeSql("INSERT INTO publisher_pblr(pblr_ctgr_pk, pblr_lang, pblr_country, pblr_name, pblr_webUrl, pblr_logoUrl) VALUES (1, 'es', 'ES', 'Onda Cero', 'http://www.ondacero.es/podcast/', NULL)", []);

            this.db.executeSql("DROP TABLE IF EXISTS feed_feed", []);
            this.db.executeSql("CREATE TABLE feed_feed(feed_pk INTEGER PRIMARY KEY AUTOINCREMENT, feed_pblr_pk INTEGER, feed_url VARCHAR(200), feed_author VARCHAR(50), feed_copyright VARCHAR(50), feed_description VARCHAR(200), feed_encoding VARCHAR(20), feed_feedType VARCHAR(20), feed_generator VARCHAR(50), feed_language VARCHAR(2), feed_link VARCHAR(200), feed_publishedDate DATETIME, feed_title VARCHAR(200), feed_uri VARCHAR(200), feed_imUrl VARCHAR(200), feed_imHeight INTEGER, feed_imWidth INTEGER, feed_podcast BOOLEAN, feed_subtitle VARCHAR(200), CONSTRAINT uq_feed_url UNIQUE (feed_url))", []);

            this.http.get("assets/feeds.json").subscribe((res) => {
                var feeds = res.json().feeds;

                for (var i in feeds) {
                    this.db.executeSql(feeds[i], []);
                }
            });
        }).catch(e => { console.log(e); });
    }
}
