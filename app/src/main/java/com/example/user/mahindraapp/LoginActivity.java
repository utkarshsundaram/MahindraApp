package com.example.user.mahindraapp;
/**
 * * This activity is to login into the app
* with the default username,password that is
* send to the API as header
* @author UtkarshSundaram
*/
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.mahindraapp.Models.LoginResponseModel;
import com.example.user.mahindraapp.Models.Logindatamodel;
import com.example.user.mahindraapp.Utilities.Constants;
import com.google.gson.JsonObject;
import com.kelltontech.ui.IScreen;
import com.kelltontech.utils.ConnectivityUtils;
import com.kelltontech.volley.ext.GsonObjectRequest;
import com.kelltontech.volley.ext.RequestManager;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.user.mahindraapp.R.drawable.error;
import static com.example.user.mahindraapp.Utilities.Constants.DATA_REQUEST;

public class LoginActivity extends AppCompatActivity implements IScreen
{
    private EditText meditTextForUserId;
    private EditText meditTextForPassword;
    private Button mbuttonForLogin;
    ProgressDialog progressDialog;
    String mLoginText;
    String mPassword;

    public final String TAG=LoginActivity.class.getSimpleName();
    public String tokens;
    public String unique_id_keys;
    public int Status;
    public int response =0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        meditTextForUserId=(EditText)findViewById(R.id.editTextUserName);
        meditTextForPassword=(EditText)findViewById(R.id.loginPasswordId);
        mbuttonForLogin=(Button)findViewById(R.id.loginButtonId);
        onloginButtonClick();

        meditTextForUserId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                {
                    meditTextForUserId.setGravity(Gravity.LEFT | Gravity.TOP);
                }
                else
                {
                    meditTextForUserId.setGravity(Gravity.CENTER);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
/**
 * This function is used to check the username and password
 * edittextbox and send the token and unique_id_key to another activity
 * via intent if the text entered in all the fields is correct
 * */
    private void onloginButtonClick()
    {
        mbuttonForLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mLoginText = meditTextForUserId.getText().toString();
                mPassword = meditTextForPassword.getText().toString();
                if (mLoginText.equals(Constants.username) && mPassword.equals(Constants.password))
                {
                    createProgressDialog();
                    getData(DATA_REQUEST);

                           Intent goTODisplayIntent = new Intent(LoginActivity.this, ResortListActivity.class);
                           goTODisplayIntent.putExtra(Constants.token, tokens);
                           goTODisplayIntent.putExtra(Constants.unique_id_key, unique_id_keys);
                           startActivity(goTODisplayIntent);



                }
                else if (!mLoginText.equals(Constants.username))
                {
                    meditTextForUserId.requestFocus();
                    meditTextForUserId.setError(Constants.wrongUserIdError);

                }
                else if (!mPassword.equals(Constants.userNamePassword))
                {
                    meditTextForPassword.requestFocus();
                    meditTextForPassword.setError(Constants.wrongPasswordError);

                }
            }
        });

    }
    /**
     * This function is used to validate
     * the response of the API that we have
     * got and check whether it matches with our
     * model class that we have made
     * @param status,actionID,serviceResponse
     */

    @Override
    public void updateUi(boolean status, int actionID, Object serviceResponse)
    {

       switch (actionID)
       {
           case Constants.DATA_REQUEST:
               if(serviceResponse instanceof LoginResponseModel)
               {

                   LoginResponseModel loginResponseModel=(LoginResponseModel) serviceResponse;
                   Status=loginResponseModel.getStatus();
                   tokens=loginResponseModel.getData().getAuthToken();
                   unique_id_keys=loginResponseModel.getData().getUserUniqueId();
                   Log.d(TAG, "result is here");

               }
               break;
           default:
       }


    }



    @Override
    public void onEvent(int eventId, Object eventData)
    {

    }
    /**
     * This function is used to create the connection
     * with the server by sending the parameters that
     * it requires for establishing
     * @param actionID
     */
    @Override
    public void getData(int actionID)
    {
        if (!ConnectivityUtils.isNetworkEnabled(this))
        {
            return;
        }
        if (actionID==DATA_REQUEST)
        {
            try{
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("userId",mLoginText);
                jsonObject.addProperty("password",mPassword);
                RequestManager.addRequest(new GsonObjectRequest<LoginResponseModel>(Constants.loginurl, new HashMap<String, String>(),jsonObject.toString(), LoginResponseModel.class, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(LoginActivity.this,""+error,Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }) {
                    /**
                     * This function is used to verify
                     * whether the data has came or not
                     * @param response
                     * */
                    @Override
                    protected void deliverResponse(LoginResponseModel response)
                    {
                        progressDialog.dismiss();
                        updateUi(true,DATA_REQUEST,response);
                    }
                });
            }catch (Exception e)
            {
               e.printStackTrace();
            }

        }

    }

    /**
     * This function is used to create progress
     * dialogbox and it is been called in onCreate() method
     */
    public void createProgressDialog()
    {
        progressDialog = new ProgressDialog(LoginActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
        progressDialog.setMessage(Html.fromHtml("<font color='#00000'>Loading .....</font>"));
        progressDialog.show();


    }


}
