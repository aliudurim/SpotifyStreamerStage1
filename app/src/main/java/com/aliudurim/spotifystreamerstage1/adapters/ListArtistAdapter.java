package com.aliudurim.spotifystreamerstage1.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aliudurim.spotifystreamerstage1.R;
import com.aliudurim.spotifystreamerstage1.activitys.TracksActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by DurimAliu on 07/06/15.
 */
public class ListArtistAdapter extends RecyclerView.Adapter<ListArtistAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Artist> artistArrayList = new ArrayList<>();

    public void setArtistArrayList(ArrayList<Artist> artistArrayList) {
        this.artistArrayList = artistArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @InjectView(R.id.llCellForArtist)
        LinearLayout llCellForArtist;
        @InjectView(R.id.txtNameArtist)
        TextView txtNameArtist;
        @InjectView(R.id.ivForArtist)
        ImageView ivForArtist;

        public ViewHolder(View v) {

            super(v);
            ButterKnife.inject(this, v);
        }

    }

    public ListArtistAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_artist_adapter, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.txtNameArtist.setText("" + artistArrayList.get(position).name);

        if (artistArrayList.get(position).images.size() > 0) {
            Picasso.with(context).load(artistArrayList.get(position).images.get(0).url).resize(dpToPx(60), dpToPx(60)).centerCrop().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.ivForArtist);
        }

        holder.llCellForArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TracksActivity.class);
                intent.putExtra("spotifyId", artistArrayList.get(position).id);
                intent.putExtra("nameArtist", artistArrayList.get(position).name);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return artistArrayList.size();
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
