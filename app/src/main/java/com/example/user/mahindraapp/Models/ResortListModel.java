package com.example.user.mahindraapp.Models;

import java.util.ArrayList;

/**
 * Created by user on 28/6/17.
 */

public class ResortListModel
{
    public int status;
    public int responseCode;
    public String responseMessage;
    public int totalCount;
    public ArrayList<ResortDetailsModels>resorts;

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

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<ResortDetailsModels> getResorts() {
        return resorts;
    }

    public void setResorts(ArrayList<ResortDetailsModels> resorts) {
        this.resorts = resorts;
    }


    public ResortListModel(int status, int responseCode, String responseMessage, int totalCount, ArrayList<ResortDetailsModels> resorts) {
        this.status = status;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.totalCount = totalCount;
        this.resorts = resorts;
    }


}
