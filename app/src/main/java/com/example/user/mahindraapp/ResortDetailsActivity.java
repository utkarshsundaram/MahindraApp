package com.example.user.mahindraapp;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.mahindraapp.Adapters.ViewPageAdapters;
import com.example.user.mahindraapp.Fragments.AboutFragment;
import com.example.user.mahindraapp.Fragments.ReviewFragment;
import com.example.user.mahindraapp.Fragments.ThingstoDoFragment;
import com.example.user.mahindraapp.Fragments.WayToReachFragment;
import com.example.user.mahindraapp.Models.AboutResortModel;
import com.example.user.mahindraapp.Utilities.Constants;
import com.google.gson.JsonObject;
import com.kelltontech.ui.IScreen;
import com.kelltontech.utils.ConnectivityUtils;
import com.kelltontech.volley.ext.GsonObjectRequest;
import com.kelltontech.volley.ext.RequestManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.HashMap;

import static com.example.user.mahindraapp.Utilities.Constants.DATA_REQUEST_FOR_DETAIL;
import static com.example.user.mahindraapp.Utilities.Constants.resortdetailurl;
/*This activity is to show the resort detail along
*with the details,besttimetogo and other minute
* details with it with the help of Fragments and Tablayout
* @param resortid,autotoken,uniqueuserid
* @author UtkarshSundaram
*
*
*
*/
public class ResortDetailsActivity extends AppCompatActivity implements IScreen {
    public static String TAG = ResortDetailsActivity.class.getSimpleName();
    public int resort_id;
    public String message;
    public String name;
    Context context;
   private TabLayout tabLayout;
    private ViewPager viewPager;
    ViewPageAdapters viewPagerAdapter;
    private ImageView imageViewResortPhoto,mWifi;
    private  TextView mTextViewForName, mTextViewBestTime, mSummerTemperature, mWinterTemperature;
    private TextView mAboutTheHotel;
    private TextView mAdressofdata, mEmailofdata, mPhoneofdata;
    private TextView mHeadingAddress, mHeadingEmail, mHeadingPhone, mHeadingAbout;
    AboutResortModel aboutResortModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort_details);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPageAdapters((getSupportFragmentManager()));
        viewPagerAdapter.addFragments(new AboutFragment(), Constants.title1);
        viewPagerAdapter.addFragments(new ReviewFragment(), Constants.title2);
        viewPagerAdapter.addFragments(new ThingstoDoFragment(), Constants.title3);
        viewPagerAdapter.addFragments(new WayToReachFragment(), Constants.title4);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        Intent intent = getIntent();

        if (intent != null) {
            resort_id = getIntent().getIntExtra(Constants.resortid, 0);
            Log.d(TAG, "" + resort_id);
        }

        getData(DATA_REQUEST_FOR_DETAIL);


    }
/**
 * * This function isused for definingthe id'sof
* the views that are present in activity and fragments
**/
    private void setTheId() {
        mTextViewForName = (TextView) findViewById(R.id.nameTextViewid);
        imageViewResortPhoto = (ImageView) findViewById(R.id.imageViewResortPhotos);
        mTextViewBestTime = (TextView) findViewById(R.id.bestTimeToVisitTextView);
        mSummerTemperature = (TextView) findViewById(R.id.summerTextViewId);
        mWinterTemperature = (TextView) findViewById(R.id.winterTextViewId);
        mAboutTheHotel = (TextView) findViewById(R.id.tv_AboutTheHotel);
        mAdressofdata = (TextView) findViewById(R.id.tv_adressofdata);
        mEmailofdata = (TextView) findViewById(R.id.tv_emailofdata);
        mPhoneofdata = (TextView) findViewById(R.id.tv_Phoneofdata);
        mHeadingAddress = (TextView) findViewById(R.id.headingAddress);
        mHeadingEmail = (TextView) findViewById(R.id.headingEmail);
        mHeadingPhone = (TextView) findViewById(R.id.headingPhone);
        mHeadingAbout = (TextView) findViewById(R.id.headingAbout);
        mWifi=(ImageView)findViewById(R.id.textViewWifi);

    }

/**
 *This function is used to validate
* the response of the API that we have
* got and check whether it matches with our
* model class that we have made
*/
@Override
    public void updateUi(boolean status, int actionID, Object serviceResponse) {
        switch (actionID) {
            case DATA_REQUEST_FOR_DETAIL:
                if (serviceResponse instanceof AboutResortModel) {
                    aboutResortModel = (AboutResortModel) serviceResponse;
                    message = aboutResortModel.getResponseMessage();
                    name = aboutResortModel.getData().getResortShortname();
                    setTheId();
                    setTheLayout();
                    setTheFragmentLayout();
                    clickedOnArrow();
                    Log.d(TAG, message);
                    Log.d(TAG, name);

                }
                break;
            default:
        }

    }

    @Override
    public void onEvent(int eventId, Object eventData) {

    }
/**
 * This  function is used to set the view which are defined in
* the xml file of this activity
*
*/
public void setTheLayout() {
        mTextViewForName.setText(name);
        mTextViewBestTime.setText("Best time \n" + aboutResortModel.getData().getBestTimetoTravel());
        mSummerTemperature.setText("Summer \n" + aboutResortModel.getData().getTemperature().get(0).getSummer());
        mWinterTemperature.setText("Winter \n" + aboutResortModel.getData().getTemperature().get(0).getWinter());
        Picasso.with(getApplicationContext()).load(aboutResortModel.getData().getAboutImgURL().get(0)).into(imageViewResortPhoto);

    }
/*This function is calle to set the
* views which are defined in fragments
*
*/
public void setTheFragmentLayout() {
        mAboutTheHotel.setText("" + aboutResortModel.getData().getAboutResort());
        mAdressofdata.setText("" + aboutResortModel.getData().getResortAddress());
        mEmailofdata.setText("" + aboutResortModel.getData().getEmail());
        mPhoneofdata.setText("" + aboutResortModel.getData().getPhone());
   // Picasso.with(getApplicationContext()).load(aboutResortModel.getData().getAmenities().get(1).getIcon()).into(mWifi);

    }
/**
 *This function is called when
* we want to go back to the list of resort
* shown in previous activity
*/
private void clickedOnArrow()
    {
        mTextViewForName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(ResortDetailsActivity.this, ResortListActivity.class);
                startActivity(intent);
            }
        });
    }
    /*This function is used to create the connection
    * with the server by sending the parameters that
    * it requires for establishing
    *
    */
    @Override
    public void getData(int actionID) {
        if (!ConnectivityUtils.isNetworkEnabled(this)) {
            return;
        }
        if (actionID == DATA_REQUEST_FOR_DETAIL) {
            try {
                HashMap<String, String> header = new HashMap<>();
                header.put("token", Constants.authToken);
                header.put("unique_id_key", Constants.uniqueid);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("resortId", resort_id);
                RequestManager.addRequest(new GsonObjectRequest<AboutResortModel>(resortdetailurl, header, jsonObject.toString(), AboutResortModel.class, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ResortDetailsActivity.this, "" + error, Toast.LENGTH_LONG).show();
                        error.printStackTrace();

                    }
                }) {
                    /**
                     * This function is used to verify
                     * whether the data has came or not
                     * @param response
                     * */
                    @Override
                    protected void deliverResponse(AboutResortModel response) {
                        updateUi(true, DATA_REQUEST_FOR_DETAIL, response);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


}
