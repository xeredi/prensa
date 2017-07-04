import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { DbService } from './database/db.service';
import { InitDbService } from './database/init-db.service';
import { CategoryService } from './database/category.service';

import { AboutPage } from '../pages/about/about';
import { ContactPage } from '../pages/contact/contact';
import { HomePage } from '../pages/home/home';
import { TabsPage } from '../pages/tabs/tabs';
import { CategoryDetailPage } from '../pages/category-detail';
import { NewDetailPage } from '../pages/new-detail';

import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { IonicAudioModule, defaultAudioProviderFactory } from 'ionic-audio';
import { SQLite } from '@ionic-native/sqlite';

@NgModule({
  declarations: [
    MyApp,
    AboutPage,
    ContactPage,
    HomePage,
    TabsPage,
    CategoryDetailPage, NewDetailPage
  ],
  imports: [
    BrowserModule,
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
    , NewDetailPage
  ],
  providers: [
    DbService
    , InitDbService
    , CategoryService

    , StatusBar
    , SplashScreen
    , { provide: ErrorHandler, useClass: IonicErrorHandler }
  ]
})
export class AppModule { }
