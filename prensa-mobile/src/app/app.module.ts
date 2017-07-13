import { NgModule, ErrorHandler } from '@angular/core';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { DbService } from './database/db.service';
import { InitDbService } from './database/init-db.service';
import { CategoryService } from './database/category.service';
import { PublisherService } from './database/publisher.service';
import { FeedService } from './database/feed.service';
import { ItemService } from './database/item.service';
import { FollowFeedService } from './database/follow-feed.service';
import { FeedReaderService } from './feed/feed-reader.service';

import { ContactPage } from '../pages/contact/contact';
import { HomePage } from '../pages/home/home';
import { TabsPage } from '../pages/tabs/tabs';
import { CategoryListPage } from './../pages/category-list';
import { CategoryDetailPage } from '../pages/category-detail';
import { PublisherDetailPage } from '../pages/publisher-detail';
import { FeedDetailPage } from './../pages/feed-detail';
import { NewDetailPage } from '../pages/new-detail';

import { ItemListComponent } from './component/item-list.component';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { IonicAudioModule, defaultAudioProviderFactory } from 'ionic-audio';

@NgModule({
  declarations: [
    MyApp,
    ContactPage,
    HomePage,
    TabsPage,
    CategoryListPage, 
    CategoryDetailPage, 
    FeedDetailPage,
    NewDetailPage, 
    PublisherDetailPage,

    ItemListComponent
  ],
  imports: [
    BrowserModule, HttpModule,
    IonicModule.forRoot(MyApp),
    IonicAudioModule.forRoot(defaultAudioProviderFactory)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp
    , ContactPage
    , HomePage
    , TabsPage
    , CategoryListPage
    , CategoryDetailPage
    , PublisherDetailPage
    , FeedDetailPage
    , NewDetailPage
  ],
  providers: [
    DbService
    , InitDbService
    , CategoryService
    , PublisherService
    , FeedService
    , ItemService
    , FollowFeedService
    , FeedReaderService

    , StatusBar
    , SplashScreen
    , { provide: ErrorHandler, useClass: IonicErrorHandler }
  ]
})
export class AppModule { }
