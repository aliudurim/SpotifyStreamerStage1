package com.aliudurim.spotifystreamerstage1.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by DurimAliu on 13/06/15.
 */
public class TopTrack implements Parcelable {


    public String name, albumName, url;

    public TopTrack(String name, String albumName, String url) {
        this.name = name;
        this.albumName = albumName;
        this.url = url;
    }

    private TopTrack(Parcel in) {
        name = in.readString();
        albumName = in.readString();
        url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(albumName);
        dest.writeString(url);
    }

    public static final Creator<TopTrack> CREATOR = new Creator<TopTrack>() {
        public TopTrack createFromParcel(Parcel in) {
            return new TopTrack(in);
        }

        public TopTrack[] newArray(int size) {
            return new TopTrack[size];
        }
    };
}
