import { Injectable } from '@angular/core';

import { SQLite, SQLiteObject } from '@ionic-native/sqlite';

@Injectable()
export class InitDbService {
    constructor(/*private sqlite: SQLite*/) { }

    initDb() {
        console.log("INIT DB");

        var sqlite = new SQLite();

        sqlite.create({
            name: 'press.db',
            location: 'default'
        })
            .then((db: SQLiteObject) => {
                console.log("CONNECTED");

                db.executeSql("DROP TABLE IF EXISTS category_ctgr", []);
                db.executeSql("CREATE TABLE category_ctgr(ctgr_pk INTEGER PRIMARY KEY, ctgr_name VARCHAR(20), ctgr_icon VARCHAR(20))", []);
                db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (1, 'Radio', 'musical-notes')", []);
                db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (2, 'Prensa General', 'paper')", []);
                db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (3, 'Prensa Digital', 'finger-print')", []);
                db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (4, 'Prensa Internacional', 'globe')", []);
                db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (5, 'Deportes', 'football')", []);
                db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (6, 'Economia', 'logo-usd')", []);
                db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (7, 'Cultura y Ciencia', 'logo-android')", []);
                db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (8, 'Viajes', 'paper-plane')", []);
                db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (9, 'Motor', 'car')", []);
                db.executeSql("INSERT INTO category_ctgr(ctgr_pk, ctgr_name, ctgr_icon) VALUES (10, 'Corazón', 'heart')", []);
            })
            .catch(e => console.log(e));


    }

}
