import { ItemService } from './../app/database/item.service';
import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
// import { AudioProvider } from 'ionic-audio';

@Component({
    selector: 'new-detail',
    templateUrl: 'new-detail.html'
})
export class NewDetailPage {
    new: any;
    audio: any;

    constructor(private navCtrl: NavController, private navParams: NavParams, private itemService: ItemService/*, private _audioProvider: AudioProvider*/) {
        this.new = this.navParams.get("new");

        if (this.new.enclosureUrl) {
            // console.log("AUDIO!!!");
            this.audio = {
                src: this.new.enclosureUrl
                , artist: this.new.author
                , title: this.new.title
                , art: this.new.thumbnailUrl
                , preload: 'metadata'
            };
            // console.log("AUDIO: " + JSON.stringify(this.audio));
        }

        this.new.readDate = new Date();

        console.log("mark read");
        this.itemService.markRead(this.new.id, this.new.readDate);
    }

    ionViewWillLeave() {
        /*
                console.log("Looks like I'm about to leave :(");
                console.log("Track: " + this._audioProvider.current);
        
                if (this._audioProvider.current) {
                    this._audioProvider.stop(this._audioProvider.current);
                }
        
                this._audioProvider.tracks.map(track => {
                    console.log("Destroy Track... : " + track.title);
                    track.stop();
                    track.destroy();
                });
                this._audioProvider.tracks.pop();
                this._audioProvider = null;
        */
    }
}
