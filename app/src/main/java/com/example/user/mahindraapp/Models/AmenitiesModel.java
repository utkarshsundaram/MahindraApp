package com.example.user.mahindraapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 29/6/17.
 */

public class AmenitiesModel implements Parcelable
{
    public int SrNo;
    public String Name;
    public String Icon;
    public String inactive;

    public int getSrNo()
    {
        return SrNo;
    }

    public void setSrNo(int srNo)
    {
        SrNo = srNo;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getIcon()
    {
        return Icon;
    }

    public void setIcon(String icon)
    {
        Icon = icon;
    }

    public String getInactive()
    {
        return inactive;
    }

    public void setInactive(String inactive)
    {
        this.inactive = inactive;
    }



    protected AmenitiesModel(Parcel in)
    {
        SrNo = in.readInt();
        Name = in.readString();
        Icon = in.readString();
        inactive = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(SrNo);
        dest.writeString(Name);
        dest.writeString(Icon);
        dest.writeString(inactive);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<AmenitiesModel> CREATOR = new Creator<AmenitiesModel>()
    {
        @Override
        public AmenitiesModel createFromParcel(Parcel in)
        {
            return new AmenitiesModel(in);
        }

        @Override
        public AmenitiesModel[] newArray(int size)
        {
            return new AmenitiesModel[size];
        }
    };
}
