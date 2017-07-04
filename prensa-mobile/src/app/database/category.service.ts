import { Injectable } from '@angular/core';

import { SQLite, SQLiteObject } from '@ionic-native/sqlite';

@Injectable()
export class CategoryService {
    private db: SQLiteObject;
    private isOpen: boolean;

    constructor() {
        console.log("CategoryService CONSTRUCTOR");

        var sqlite = new SQLite();

        sqlite.create({
            name: 'press.db',
            location: 'default'
        })
            .then((db: SQLiteObject) => {
                console.log("CategoryService CONNECTED");

                this.db = db;
            })
            .catch(e => console.log(e));
    }

    selectAll() {
        console.log("CategoryService SELECTALL");

        return this.openSQLiteDatabase().then(() => {
            return this.db.executeSql("SELECT * FROM category_ctgr", []).then(data => {
                var items = [];
                if (data.rows.length > 0) {
                    for (var i = 0; i < data.rows.length; i++) {
                        items.push({
                            id: data.rows.item(i).ctgr_pk
                            , name: data.rows.item(i).ctgr_name
                            , icon: data.rows.item(i).ctgr_icon
                        });
                    }
                }

                console.log("ITEMS: " + JSON.stringify(items));

                return items;
            });
        }).catch(e => { console.log(e); return null; });
    }

    public openSQLiteDatabase() {
        return new Promise((resolve, reject) => {
            if (this.isOpen) {
                console.log("DB IS OPEN");
                resolve(this.isOpen);
            }

            else {
                console.log("DB IS NOT OPEN");
                var sqlite = new SQLite();

                sqlite.create({
                    name: 'press.db',
                    location: 'default'
                })
                    .then((db: SQLiteObject) => {
                        console.log("CategoryService CONNECTED");

                        this.db = db;
                        this.isOpen = true;
                        resolve(this.isOpen);
                    })
                    .catch(e => console.log(e));
            }
        });
    }

}
