package com.example.user.mahindraapp.Models;

/**
 * Created by user on 28/6/17.
 */

public class LoginResponseModel
{
    public Logindatamodel data;
    public int status;
    public int responseCode;
    public String responseMessage;

    public LoginResponseModel(Logindatamodel data, int status, int responseCode, String responseMessage)
    {
        this.data = data;
        this.status = status;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }


    public Logindatamodel getData()
    {
        return data;
    }

    public void setData(Logindatamodel data)
    {
        this.data = data;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public int getResponseCode()
    {
        return responseCode;
    }

    public void setResponseCode(int responseCode)
    {
        this.responseCode = responseCode;
    }

    public String getResponseMessage()
    {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage)
    {
        this.responseMessage = responseMessage;
    }



}
