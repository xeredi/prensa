import { Injectable } from '@angular/core';
import { Platform } from 'ionic-angular';
import { SQLite } from 'ionic-native';

@Injectable()
export class DbService {
    public database: SQLite;

    constructor(private platform: Platform) {
        this.platform.ready().then(() => {
            this.database = new SQLite();
            this.database.openDatabase({ name: "press.db", location: "default" }).then(() => {
                console.log("Database opened");
            }, (error) => {
                console.log("ERROR: ", error);
            });
        });
    }
}
