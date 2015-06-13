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
import com.aliudurim.spotifystreamerstage1.object.TopTrack;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by DurimAliu on 07/06/15.
 */
public class TracksArtistAdapter extends RecyclerView.Adapter<TracksArtistAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TopTrack> trackArrayList;

    public static class ViewHolder extends RecyclerView.ViewHolder {


        @InjectView(R.id.txtTracksName)
        TextView txtTracksName;
        @InjectView(R.id.txtAlbumName)
        TextView txtAlbumName;
        @InjectView(R.id.ivForTracks)
        ImageView ivForTracks;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
        }
    }

    public TracksArtistAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_tracks_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtTracksName.setText("" + trackArrayList.get(position).name);
        holder.txtAlbumName.setText("" + trackArrayList.get(position).albumName);

        Picasso.with(context).load(trackArrayList.get(position).url).resize(dpToPx(60), dpToPx(60)).centerCrop().into(holder.ivForTracks);
    }

    public void setTrackArrayList(ArrayList<TopTrack> trackArrayList) {
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
