package com.example.user.mahindraapp;
/**
 * This activity is to show the resort list along
*with the details like roomavalability,ratings,roomtype etc
* details with it with the help of Fragments and Tablayout
* @param autotoken,uniqueuserid
* @author UtkarshSundaram
*/
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.mahindraapp.Adapters.MyRecyclerAdapters;
import com.example.user.mahindraapp.Models.ResortDetailsModels;
import com.example.user.mahindraapp.Models.ResortListModel;
import com.example.user.mahindraapp.Utilities.Constants;
import com.kelltontech.ui.IScreen;
import com.kelltontech.utils.ConnectivityUtils;
import com.kelltontech.volley.ext.GsonObjectRequest;
import com.kelltontech.volley.ext.RequestManager;
import java.util.ArrayList;
import java.util.HashMap;
import static com.example.user.mahindraapp.Utilities.Constants.DATA_REQUEST_FOR_RESORT;
import static com.example.user.mahindraapp.Utilities.Constants.resorturl;
import static com.example.user.mahindraapp.Utilities.Constants.totalCount;

public class ResortListActivity extends AppCompatActivity implements IScreen,MyRecyclerAdapters.OnItemClickListener,View.OnClickListener
{
    public ArrayList<ResortDetailsModels> arrayListResortDetail=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapters mRecycleAdapters;
    public String tokenkey;
    public String uniquekey;
    TextView mTotalResults,mBackButton;
    int resortid=0;
    ProgressDialog progressDialog;
    public final String TAG=ResortListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort_list);
        mTotalResults=(TextView)findViewById(R.id.tv_results);
        Intent intent =getIntent();
        if(intent!=null)
        {
            tokenkey=getIntent().getStringExtra(Constants.token);
            uniquekey=getIntent().getStringExtra(Constants.unique_id_key);


        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(ResortListActivity.this, layoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        getData(DATA_REQUEST_FOR_RESORT);
        setTheID();

    }
    /**
     * This function is
    * used for definingthe id'sof the views that are present
    * in activity
    * */
    private void setTheID()
    {
        mBackButton=(TextView)findViewById(R.id.tv_Back);
        OnClickofBackButton();
    }
    /**
     *This function is called when
     * we want to cloase the app
     * An AlertDialog is made to ask the user
     * about its confirmation to leave the app
     */
    private void OnClickofBackButton()
    {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResortListActivity.this);
                builder.setTitle(Html.fromHtml("<font color='#000000'>MahindraApp</font>")).setMessage(Html.fromHtml("<font color='#000000'>Do you want to close the list ?</font>"));

                builder .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                AlertDialog alert = builder.create();

                alert.show();
            }
        });
    }
    /**
     * This function is used to validate
    * the response of the API that we have
    * got and check whether it matches with our
    * model class that we have made
    * @param status,actionID,serviceResponse,TAG
    */
    @Override
    public void updateUi(boolean status, int actionID, Object serviceResponse)
    {
        switch (actionID)
        {
            case DATA_REQUEST_FOR_RESORT:
                if(serviceResponse instanceof ResortListModel)
                {
                    ResortListModel resortListModel=(ResortListModel)serviceResponse;
                    Constants.totalCount=resortListModel.getTotalCount();
                    arrayListResortDetail=resortListModel.getResorts();
                    String responsemessage=resortListModel.getResponseMessage();
                    Log.d(TAG,responsemessage);
                    Log.d(TAG,""+arrayListResortDetail);
                    Log.d(TAG,""+totalCount);
                    mTotalResults.setText(""+totalCount);
                    mRecycleAdapters = new MyRecyclerAdapters(arrayListResortDetail,this,getApplicationContext());
                    mRecyclerView.setAdapter(mRecycleAdapters);
                    mRecycleAdapters.notifyDataSetChanged();


                }
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
        if(actionID==DATA_REQUEST_FOR_RESORT)
        {
           try
           {

               HashMap< String, String> header = new HashMap<>();
               header.put("token",Constants.authToken);
               header.put("unique_id_key",Constants.uniqueid);
               RequestManager.addRequest(new GsonObjectRequest<ResortListModel>(resorturl,header,null,ResortListModel.class,new Response.ErrorListener()
               {
                   @Override
                   public void onErrorResponse(VolleyError error)
                   {
                       Toast.makeText(ResortListActivity.this,""+error,Toast.LENGTH_LONG).show();
                       error.printStackTrace();
                   }
               }) {
                   /**
                    * This function is used to verify
                    * whether the data has came or not
                    * @param response
                    * */
                   @Override
                   protected void deliverResponse(ResortListModel response)
                   {
                       updateUi(true,DATA_REQUEST_FOR_RESORT,response);
                       Log.d(TAG,"the data has camed");
                   }
               });
           }catch (Exception e)
           {
               e.printStackTrace();
           }
        }

    }

    @Override
    public void onClick(View v)
    {

    }
/**
 * This function defines the response of activity
 * whenever we click the items that are displayed through
 * recyclerview
**/
    @Override
    public void onClick(int position)
    {
        createProgressDialog();
        ResortDetailsModels resortDetailsModels =arrayListResortDetail.get(position);
        resortid =resortDetailsModels.getResortid();
        Intent intent = new Intent(ResortListActivity.this,ResortDetailsActivity.class);
        intent.putExtra(Constants.resortid,resortid);
        startActivity(intent);
        progressDialog.dismiss();


    }

    @Override
    public void onItemLongClickListener(int position)
    {

    }
    /**
     * This function is used to create progress
     * dialogbox
     *
     */
    public void createProgressDialog() {
        progressDialog = new ProgressDialog(ResortListActivity.this, R.style.Theme_AppCompat_Dialog_Alert);
        progressDialog.setMessage(Html.fromHtml("<font color='#00000'>Loading .....</font>"));
        progressDialog.show();


    }

}
