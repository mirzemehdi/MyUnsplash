package com.mmk.unsplashapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class PicturePOJO implements Parcelable  {
    private String id;
    private String smallUrl;
    private String regularUrl;


    public PicturePOJO(String id, String smallUrl, String regularUrl) {
        this.id=id;
        this.smallUrl = smallUrl;
        this.regularUrl=regularUrl;
    }

    protected PicturePOJO(Parcel in) {
        id = in.readString();
        smallUrl = in.readString();
        regularUrl = in.readString();
    }

    public static final Creator<PicturePOJO> CREATOR = new Creator<PicturePOJO>() {
        @Override
        public PicturePOJO createFromParcel(Parcel in) {
            return new PicturePOJO(in);
        }

        @Override
        public PicturePOJO[] newArray(int size) {
            return new PicturePOJO[size];
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
