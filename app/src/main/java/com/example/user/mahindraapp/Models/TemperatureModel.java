package com.example.user.mahindraapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 29/6/17.
 */

public class TemperatureModel implements Parcelable
{
    public String Summer;
    public String Winter;
    public String getSummer()
    {
        return Summer;
    }

    public void setSummer(String summer)
    {
        Summer = summer;
    }

    public String getWinter()
    {
        return Winter;
    }

    public void setWinter(String winter)
    {
        Winter = winter;
    }



    protected TemperatureModel(Parcel in)
    {
        Summer = in.readString();
        Winter = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(Summer);
        dest.writeString(Winter);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<TemperatureModel> CREATOR = new Creator<TemperatureModel>()
    {
        @Override
        public TemperatureModel createFromParcel(Parcel in)
        {
            return new TemperatureModel(in);
        }

        @Override
        public TemperatureModel[] newArray(int size)
        {
            return new TemperatureModel[size];
        }
    };
}
