package com.example.user.mahindraapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by user on 29/6/17.
 */

public class ResortDescriptionModel implements Parcelable
{
    public String ResortShortname;
    public String ResortAddress;
    public int Resort_id;
    public String Phone;
    public String Email;
    public String AboutResort;
    public ArrayList<AmenitiesModel> Amenities;
    public ArrayList<String> AboutImgURL;
    public ArrayList<TemperatureModel>Temperature;
    public String BestTimetoTravel;
    public String getResortShortname() {
        return ResortShortname;
    }

    public void setResortShortname(String resortShortname) {
        ResortShortname = resortShortname;
    }

    public String getResortAddress() {
        return ResortAddress;
    }

    public void setResortAddress(String resortAddress) {
        ResortAddress = resortAddress;
    }

    public int getResort_id() {
        return Resort_id;
    }

    public void setResort_id(int resort_id) {
        Resort_id = resort_id;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAboutResort() {
        return AboutResort;
    }

    public void setAboutResort(String aboutResort) {
        AboutResort = aboutResort;
    }

    public ArrayList<AmenitiesModel> getAmenities() {
        return Amenities;
    }

    public void setAmenities(ArrayList<AmenitiesModel> amenities) {
        Amenities = amenities;
    }

    public ArrayList<String> getAboutImgURL() {
        return AboutImgURL;
    }

    public void setAboutImgURL(ArrayList<String> aboutImgURL) {
        AboutImgURL = aboutImgURL;
    }

    public ArrayList<TemperatureModel> getTemperature() {
        return Temperature;
    }

    public void setTemperature(ArrayList<TemperatureModel> temperature) {
        Temperature = temperature;
    }
    public String getBestTimetoTravel() {
        return BestTimetoTravel;
    }

    public void setBestTimetoTravel(String bestTimetoTravel) {
        BestTimetoTravel = bestTimetoTravel;
    }


    protected ResortDescriptionModel(Parcel in) {
        ResortShortname = in.readString();
        ResortAddress = in.readString();
        Resort_id = in.readInt();
        Phone = in.readString();
        Email = in.readString();
        AboutResort = in.readString();
        Amenities = in.createTypedArrayList(AmenitiesModel.CREATOR);
        AboutImgURL = in.createStringArrayList();
        Temperature = in.createTypedArrayList(TemperatureModel.CREATOR);
        BestTimetoTravel = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ResortShortname);
        dest.writeString(ResortAddress);
        dest.writeInt(Resort_id);
        dest.writeString(Phone);
        dest.writeString(Email);
        dest.writeString(AboutResort);
        dest.writeTypedList(Amenities);
        dest.writeStringList(AboutImgURL);
        dest.writeTypedList(Temperature);
        dest.writeString(BestTimetoTravel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResortDescriptionModel> CREATOR = new Creator<ResortDescriptionModel>() {
        @Override
        public ResortDescriptionModel createFromParcel(Parcel in) {
            return new ResortDescriptionModel(in);
        }

        @Override
        public ResortDescriptionModel[] newArray(int size) {
            return new ResortDescriptionModel[size];
        }
    };
}
