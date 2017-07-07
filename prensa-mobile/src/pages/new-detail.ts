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

    constructor(public navCtrl: NavController, public navParams: NavParams/*, private _audioProvider: AudioProvider*/) {
        this.new = navParams.get("new");

        if (this.new.enclosures[0] && this.new.enclosures[0].type == 'audio/mpeg') {
            // console.log("AUDIO!!!");
            this.audio = {
                src: this.new.enclosures[0].url
                , artist: this.new.meta.title
                , title: this.new.title
                , art: this.new.image.url
                , preload: 'metadata'
            };
            // console.log("AUDIO: " + JSON.stringify(this.audio));
        }
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
