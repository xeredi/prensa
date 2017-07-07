import { Injectable } from '@angular/core';

import * as feedparser from 'feedparser-promised';

@Injectable()
export class FeedReaderService {

    readFeed(url: string) {
        return feedparser.parse(url)
            .then(items => items)
            .catch(error => {
                console.error('error: ', error);

                return null;
            });
    }
}
