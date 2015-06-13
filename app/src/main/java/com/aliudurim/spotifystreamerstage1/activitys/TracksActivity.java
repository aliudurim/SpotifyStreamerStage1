package com.aliudurim.spotifystreamerstage1.activitys;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aliudurim.spotifystreamerstage1.R;
import com.aliudurim.spotifystreamerstage1.adapters.TracksArtistAdapter;
import com.aliudurim.spotifystreamerstage1.callbacks.Top10CallBack;
import com.aliudurim.spotifystreamerstage1.object.TopTrack;
import com.aliudurim.spotifystreamerstage1.tasks.Top10Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by DurimAliu on 07/06/15.
 */
public class TracksActivity extends ActionBarActivity implements Top10CallBack {


    private TracksArtistAdapter tracksArtistAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String spotifyId = "";
    private String nameArtist = "";
    private ArrayList<TopTrack> trackArrayList = new ArrayList<>();
    private Toast toast;

    @InjectView(R.id.rvTracksOfArtist)
    RecyclerView rvTracksOfArtist;
    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracks_screen);
        ButterKnife.inject(this);


        Intent intent = getIntent();
        spotifyId = intent.getStringExtra("spotifyId");
        nameArtist = intent.getStringExtra("nameArtist");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Top 10 Tracks");
        actionBar.setSubtitle(nameArtist);


        rvTracksOfArtist.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        rvTracksOfArtist.setLayoutManager(mLayoutManager);

        tracksArtistAdapter = new TracksArtistAdapter(TracksActivity.this);
        tracksArtistAdapter.setTrackArrayList(trackArrayList);
        rvTracksOfArtist.setAdapter(tracksArtistAdapter);


        if (savedInstanceState == null || !savedInstanceState.containsKey("trackList")) {

            if (isNetworkAvailable(getApplicationContext())) {
                new Top10Task(TracksActivity.this, progressBar).execute(spotifyId);
            } else {
                showToast();
            }

        } else {

            trackArrayList = savedInstanceState.getParcelableArrayList("trackList");
            tracksArtistAdapter.setTrackArrayList(trackArrayList);
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("trackList", trackArrayList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTop10CallBack(List<Track> tracksList) {

        trackArrayList.clear();
        for (int i = 0; i < tracksList.size(); i++) {
            if (tracksList.get(i).album.images.size() > 0) {
                trackArrayList.add(new TopTrack(tracksList.get(i).name, tracksList.get(i).album.name, tracksList.get(i).album.images.get(0).url));
            }
        }
        tracksArtistAdapter.setTrackArrayList(trackArrayList);
    }

    public void showToast() {
        cancelToast();
        toast = Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void cancelToast() {

        if (toast != null) {
            toast.cancel();
        }
    }

    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
