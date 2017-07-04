import { Injectable } from '@angular/core';
import { Platform } from 'ionic-angular';
import { SQLite, SQLiteObject } from '@ionic-native/sqlite';

@Injectable()
export class DbService {
    public database: SQLite;

    constructor(private platform: Platform) {
/*
        console.log("CONNECTING TO DB");

        this.platform.ready().then(() => {
            this.database = new SQLite();
            this.database.create({ name: "press.db", location: "default" }).then(() => {
                console.log("DATABASE OPENED");
            }, (error) => {
                console.log("ERROR CONNECTING: ", error);
            });
        });
*/
    }
}
