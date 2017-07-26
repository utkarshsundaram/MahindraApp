package com.example.user.mahindraapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 28/6/17.
 */

public class Logindatamodel implements Parcelable
{
    private String userUniqueId;
    private String authToken;

    public String getUserUniqueId()
    {
        return userUniqueId;
    }

    public void setUserUniqueId(String userUniqueId)
    {
        this.userUniqueId = userUniqueId;
    }

    public String getAuthToken()
    {
        return authToken;
    }

    public void setAuthToken(String authToken)
    {
        this.authToken = authToken;
    }



    protected Logindatamodel(Parcel in)
    {
        userUniqueId = in.readString();
        authToken = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(userUniqueId);
        dest.writeString(authToken);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<Logindatamodel> CREATOR = new Creator<Logindatamodel>()
    {
        @Override
        public Logindatamodel createFromParcel(Parcel in)
        {
            return new Logindatamodel(in);
        }

        @Override
        public Logindatamodel[] newArray(int size)
        {
            return new Logindatamodel[size];
        }
    };
}
