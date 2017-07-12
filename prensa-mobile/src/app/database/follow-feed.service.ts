import { DbService } from './db.service';
import { Injectable } from '@angular/core';

@Injectable()
export class FollowFeedService extends DbService {

    insert(feed: any) {
        return this.openSQLiteDatabase().then(() => {
            return this.db.executeSql(
                "INSERT INTO follow_feed_flfd(flfd_feed_pk) VALUES ( " + feed.id + ")", []).then(data => feed.followed = 1);
        }).catch(e => { console.log(e); return null; });
    }

    delete(feed: any) {
        return this.openSQLiteDatabase().then(() => {
            return this.db.executeSql(
                "DELETE FROM follow_feed_flfd WHERE flfd_feed_pk = " + feed.id, []).then(data => feed.followed = undefined);
        }).catch(e => { console.log(e); return null; });
    }

}
