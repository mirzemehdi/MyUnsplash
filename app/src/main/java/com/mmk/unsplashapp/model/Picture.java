package com.mmk.unsplashapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Picture  implements Parcelable  {
    private String id;
    private String smallUrl;
    private String regularUrl;


    public Picture(String id,String smallUrl,String regularUrl) {
        this.id=id;
        this.smallUrl = smallUrl;
        this.regularUrl=regularUrl;
    }

    protected Picture(Parcel in) {
        id = in.readString();
        smallUrl = in.readString();
        regularUrl = in.readString();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    public String getRegularUrl() {
        return regularUrl;
    }

    public void setRegularUrl(String regularUrl) {
        this.regularUrl = regularUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(smallUrl);
        dest.writeString(regularUrl);
    }
}
