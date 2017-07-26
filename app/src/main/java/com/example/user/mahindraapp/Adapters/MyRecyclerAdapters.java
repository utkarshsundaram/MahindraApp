package com.example.user.mahindraapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.mahindraapp.Models.ResortDetailsModels;
import com.example.user.mahindraapp.R;
import com.example.user.mahindraapp.Utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Recycler Adapter made to show the
 * resortlist present in the API
 * @author UtkarshSundaram
 */

public class MyRecyclerAdapters extends RecyclerView.Adapter<MyRecyclerAdapters.CustomViewHolder>
{
    private List<ResortDetailsModels>resortDetailsModelArrayList;
    private MyRecyclerAdapters.OnItemClickListener onItemClickListener;
    private Context context;

    public MyRecyclerAdapters(List<ResortDetailsModels> resortDetailsModelArrayList, OnItemClickListener onItemClickListener, Context context)
    {
        this.resortDetailsModelArrayList = resortDetailsModelArrayList;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

/**
* Call the view holder's constructor, and pass the view to it;
* return that new view holder
*/
    @Override
    public MyRecyclerAdapters.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerviewforresortlist, viewGroup, false);
        return new CustomViewHolder(view);
    }

/**
 * Called by RecyclerView to display the data at the specified position.
 * This method should update the contents of the itemView
 * to reflect the item at the given position.
 * @param holder,position
 */
    @Override
    public void onBindViewHolder(MyRecyclerAdapters.CustomViewHolder holder, final int position)
    {
        ResortDetailsModels resortDetailsModels=resortDetailsModelArrayList.get(position);
        holder.mRoomAvialable.setText(resortDetailsModels.getRoomType().toString());
        holder.mRoomRating.setText(""+resortDetailsModels.getReviewcount());
        holder.mRoomPlace.setText(""+resortDetailsModels.getState()+" | ");
        holder.mTerrain.setText(resortDetailsModels.getOffers()+"offers");
        holder.mState.setText(resortDetailsModels.getResortShortname()+" | ");
        holder.mTotalRoomAvailable.setText("Rooms available "+resortDetailsModels.getAvail());
        holder.mRoomsUnavailable.setVisibility(View.INVISIBLE);
        Picasso.with(context).load(resortDetailsModels.getAboutImgURL().get(0)).resize(300,300).into(holder.mResortPhoto);
        if(resortDetailsModels.getAvail()==0)
        {
            holder.mRoomsUnavailable.setVisibility(View.VISIBLE);
            holder.mRoomsUnavailable.setText("ROOMS UNAVAIALBALE");
            holder.mRoomsUnavailable.setTextColor(Color.GRAY);
            holder.mResortPhoto.setBackgroundColor(Color.YELLOW);

        }

        holder.viewGroup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onItemClickListener.onClick(position);
            }
        });
        holder.viewGroup.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                onItemClickListener.onItemLongClickListener(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return (null != resortDetailsModelArrayList ? resortDetailsModelArrayList.size() : 0);
    }
/**
 * interface with function for defining
 * the clickability of the views
* */
    public interface OnItemClickListener
    {
        void onClick(int position);

        void onItemLongClickListener(int position);
    }
    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     *
     * {@link RecyclerView.Adapter} implementations should subclass ViewHolder and add fields for caching
     * potentially expensive {@link View#findViewById(int)} results.
     *
     * hile {@link RecyclerView.LayoutParams} belong to the {@link RecyclerView.LayoutManager},
     * {@link RecyclerView.ViewHolder ViewHolders} belong to the adapter. Adapters should feel free to use
     * their own custom ViewHolder implementations to store data that makes binding view contents
     * easier. Implementations should assume that individual item views will hold strong references
     * to <code>ViewHolder</code> objects and that <code>RecyclerView</code> instances may hold
     * strong references to extra off-screen item views for caching purposes
     * Here the class CustomViewHolder is extending the ViewHolder and providing the id's of
     * desired views
     */
    public class CustomViewHolder extends RecyclerView.ViewHolder
    {
        TextView mRoomAvialable,mRoomRating,mRoomPlace,mTerrain,mState,mTotalRoomAvailable;
        TextView mRoomsUnavailable;
        ImageView mResortPhoto;
        public View viewGroup;
        public CustomViewHolder(View view)
        {
            super(view);

            mResortPhoto=(ImageView)view.findViewById(R.id.imageViewResortPhoto);
            mRoomAvialable=(TextView)view.findViewById(R.id.tv_roomsavailable);
            mTotalRoomAvailable=(TextView)view.findViewById(R.id.tv_totalroomavailble);
            mRoomRating=(TextView)view.findViewById(R.id.tv_review);
            mRoomPlace=(TextView)view.findViewById(R.id.tv_place);
            mTerrain=(TextView)view.findViewById(R.id.tv_terrain);
            mState=(TextView)view.findViewById(R.id.tv_state);
            mRoomsUnavailable=(TextView)view.findViewById(R.id.tv_roomsunavailable);
            this.viewGroup = (ViewGroup) view.findViewById(R.id.recyclerviewforresortlist);
        }
    }
}
