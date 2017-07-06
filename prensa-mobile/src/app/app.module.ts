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
import { FollowFeedService } from './database/follow-feed.service';

import { AboutPage } from '../pages/about/about';
import { ContactPage } from '../pages/contact/contact';
import { HomePage } from '../pages/home/home';
import { TabsPage } from '../pages/tabs/tabs';
import { CategoryDetailPage } from '../pages/category-detail';
import { PublisherDetailPage } from '../pages/publisher-detail';
import { NewDetailPage } from '../pages/new-detail';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { IonicAudioModule, defaultAudioProviderFactory } from 'ionic-audio';

@NgModule({
  declarations: [
    MyApp,
    AboutPage,
    ContactPage,
    HomePage,
    TabsPage,
    CategoryDetailPage, 
    NewDetailPage, 
    PublisherDetailPage
  ],
  imports: [
    BrowserModule, HttpModule,
    IonicModule.forRoot(MyApp),
    IonicAudioModule.forRoot(defaultAudioProviderFactory)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp
    , AboutPage
    , ContactPage
    , HomePage
    , TabsPage
    , CategoryDetailPage
    , PublisherDetailPage
    , NewDetailPage
  ],
  providers: [
    DbService
    , InitDbService
    , CategoryService
    , PublisherService
    , FeedService
    , FollowFeedService

    , StatusBar
    , SplashScreen
    , { provide: ErrorHandler, useClass: IonicErrorHandler }
  ]
})
export class AppModule { }
