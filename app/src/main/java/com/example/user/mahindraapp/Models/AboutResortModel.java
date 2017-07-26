package com.example.user.mahindraapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by user on 29/6/17.
 */

public class AboutResortModel implements Parcelable
{
    public int status;
    public int responseCode;
    public String responseMessage;
    public ResortDescriptionModel data;



    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResortDescriptionModel getData() {
        return data;
    }

    public void setData(ResortDescriptionModel data) {
        this.data = data;
    }


    protected AboutResortModel(Parcel in) {
        status = in.readInt();
        responseCode = in.readInt();
        responseMessage = in.readString();


    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeInt(responseCode);
        dest.writeString(responseMessage);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AboutResortModel> CREATOR = new Creator<AboutResortModel>() {
        @Override
        public AboutResortModel createFromParcel(Parcel in) {
            return new AboutResortModel(in);
        }

        @Override
        public AboutResortModel[] newArray(int size) {
            return new AboutResortModel[size];
        }
    };
}
