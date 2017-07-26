package com.example.user.mahindraapp.Models;

import android.app.ProgressDialog;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by user on 28/6/17.
 */

public class ResortDetailsModels implements Parcelable
{
    public int resortid;
    public int avail;
    public int reviewcount;
    public  String resnextAvailableDate;
    public  String ResortShortname;
    public  String State;
    public int offers;
    public ArrayList<String> RoomType;
    public ArrayList<String> AboutImgURL;
    public String Terrain;
    public String getTerrain()
    {
        return Terrain;
    }

    public void setTerrain(String terrain)
    {
        Terrain = terrain;
    }


    public int getResortid() {
        return resortid;
    }

    public void setResortid(int resortid) {
        this.resortid = resortid;
    }

    public int getAvail() {
        return avail;
    }

    public void setAvail(int avail) {
        this.avail = avail;
    }

    public int getReviewcount() {
        return reviewcount;
    }

    public void setReviewcount(int reviewcount) {
        this.reviewcount = reviewcount;
    }

    public String getResnextAvailableDate() {
        return resnextAvailableDate;
    }

    public void setResnextAvailableDate(String resnextAvailableDate) {
        this.resnextAvailableDate = resnextAvailableDate;
    }

    public String getResortShortname() {
        return ResortShortname;
    }

    public void setResortShortname(String resortShortname) {
        ResortShortname = resortShortname;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getOffers() {
        return offers;
    }

    public void setOffers(int offers) {
        this.offers = offers;
    }

    public ArrayList<String> getRoomType() {
        return RoomType;
    }

    public void setRoomType(ArrayList<String> roomType) {
        RoomType = roomType;
    }

    public ArrayList<String> getAboutImgURL() {
        return AboutImgURL;
    }

    public void setAboutImgURL(ArrayList<String> aboutImgURL) {
        AboutImgURL = aboutImgURL;
    }




    protected ResortDetailsModels(Parcel in) {
        resortid = in.readInt();
        avail = in.readInt();
        reviewcount = in.readInt();
        resnextAvailableDate = in.readString();
        ResortShortname = in.readString();
        State = in.readString();
        offers = in.readInt();
        RoomType = in.createStringArrayList();
        AboutImgURL = in.createStringArrayList();
        Terrain=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resortid);
        dest.writeInt(avail);
        dest.writeInt(reviewcount);
        dest.writeString(resnextAvailableDate);
        dest.writeString(ResortShortname);
        dest.writeString(State);
        dest.writeInt(offers);
        dest.writeStringList(RoomType);
        dest.writeStringList(AboutImgURL);
        dest.writeString(Terrain);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResortDetailsModels> CREATOR = new Creator<ResortDetailsModels>() {
        @Override
        public ResortDetailsModels createFromParcel(Parcel in) {
            return new ResortDetailsModels(in);
        }

        @Override
        public ResortDetailsModels[] newArray(int size) {
            return new ResortDetailsModels[size];
        }
    };
}
