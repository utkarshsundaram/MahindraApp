package com.example.user.mahindraapp.Application;

import com.kelltontech.application.BaseApplication;
import com.kelltontech.volley.ext.RequestManager;

/**
 * Application Class defined to initialise
 * the Request Manager in the framework
 */

public class MyApplication extends BaseApplication
{
    @Override
    protected void initialize()
    {
        RequestManager.initializeWith(getApplicationContext(), new RequestManager.Config("data/data/DSD/pics", 5242880, 4));
    }
}
