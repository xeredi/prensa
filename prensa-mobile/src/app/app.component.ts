import { FeedReloaderService } from './feed/feed-reloader.service';
import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { TabsPage } from '../pages/tabs/tabs';

import { InitDbService } from './database/init-db.service';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  rootPage: any = TabsPage;

  constructor(platform: Platform, statusBar: StatusBar, splashScreen: SplashScreen, initDbService: InitDbService, feedReloaderService: FeedReloaderService) {
    platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleDefault();
      splashScreen.hide();
      initDbService.initDb().then(value => {
        if (value) {
          console.log('Reload Feeds');
          feedReloaderService.readFeeds();
        }
      });
    });
  }
}
