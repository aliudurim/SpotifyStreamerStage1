package com.aliudurim.spotifystreamerstage1.tasks;

import android.os.AsyncTask;

import com.aliudurim.spotifystreamerstage1.callbacks.SearchCallBack;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;

/**
 * Created by DurimAliu on 07/06/15.
 */
public class SearchArtistTask extends AsyncTask<String, Void, ArtistsPager> {

    private SearchCallBack searchCallBack;
    private boolean isArtistExist = false;

    public SearchArtistTask(SearchCallBack searchCallBack, boolean isArtistExist) {
        this.searchCallBack = searchCallBack;
        this.isArtistExist = isArtistExist;
    }

    @Override
    protected ArtistsPager doInBackground(String... params) {

        SpotifyApi api = new SpotifyApi();
        try {
            SpotifyService spotify = api.getService();
            return spotify.searchArtists(params[0]);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArtistsPager artistsPager) {

        if (artistsPager != null)
            searchCallBack.onSearchCallBack(artistsPager.artists.items, isArtistExist);
    }
}
