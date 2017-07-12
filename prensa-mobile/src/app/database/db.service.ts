import { Injectable } from '@angular/core';
import { SQLite, SQLiteObject } from '@ionic-native/sqlite';

@Injectable()
export class DbService {
    public db: SQLiteObject;
    private isOpen: boolean;

    public openSQLiteDatabase() {
        return new Promise((resolve, reject) => {
            if (this.isOpen) {
                // console.log("DB IS OPEN");
                resolve(this.isOpen);
            }

            else {
                // console.log("DB IS NOT OPEN");
                var sqlite = new SQLite();

                sqlite.create({
                    name: 'press.db',
                    location: 'default'
                })
                    .then((db: SQLiteObject) => {
                        // console.log("DB CONNECTED");

                        this.db = db;
                        this.isOpen = true;
                        resolve(this.isOpen);
                    })
                    .catch(e => console.log(e));
            }
        });
    }
}
