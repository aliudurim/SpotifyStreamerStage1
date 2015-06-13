package com.aliudurim.spotifystreamerstage1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aliudurim.spotifystreamerstage1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kaaes.spotify.webapi.android.models.Track;

/**
 * Created by DurimAliu on 07/06/15.
 */
public class TracksArtistAdapter extends RecyclerView.Adapter<TracksArtistAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Track> trackArrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTracksName, txtAlbumName;
        private ImageView ivForTracks;

        public ViewHolder(View v) {

            super(v);

            txtTracksName = (TextView) v.findViewById(R.id.txtTracksName);
            txtAlbumName = (TextView) v.findViewById(R.id.txtAlbumName);
            ivForTracks = (ImageView) v.findViewById(R.id.ivForTracks);
        }
    }

    public TracksArtistAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TracksArtistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_tracks_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtTracksName.setText("" + trackArrayList.get(position).name);
        holder.txtAlbumName.setText("" + trackArrayList.get(position).album.name);

        if (trackArrayList.get(position).album.images.size() > 0) {
            Picasso.with(context).load(trackArrayList.get(position).album.images.get(0).url).resize(dpToPx(60), dpToPx(60)).centerCrop().into(holder.ivForTracks);
        }
    }

    public void setTrackArrayList(ArrayList<Track> trackArrayList) {
        this.trackArrayList = trackArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.trackArrayList.size();
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
