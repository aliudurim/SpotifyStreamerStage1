package com.aliudurim.spotifystreamerstage1.tasks;

import android.os.AsyncTask;

import com.aliudurim.spotifystreamerstage1.callbacks.Top10CallBack;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Tracks;

/**
 * Created by DurimAliu on 07/06/15.
 */
public class Top10Task extends AsyncTask<String, Void, Tracks> {

    private Top10CallBack top10CallBack;

    public Top10Task(Top10CallBack top10CallBack) {
        this.top10CallBack = top10CallBack;
    }

    @Override
    protected Tracks doInBackground(String... params) {

        SpotifyApi api = new SpotifyApi();
        try {
            SpotifyService spotify = api.getService();
            return spotify.getArtistTopTrack(params[0]);
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    protected void onPostExecute(Tracks tracks) {

        if (tracks != null)
            top10CallBack.onTop10CallBack(tracks.tracks);
    }
}
