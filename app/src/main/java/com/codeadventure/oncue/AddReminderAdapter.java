package com.codeadventure.oncue;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by Deep on 8/13/2017.
 */

public class AddReminderAdapter extends RecyclerView.Adapter<AddReminderAdapter.ViewHolder> {

    private final List<ReminderItem> mValues;

    public static class ReminderItem {
        final String id;
        final String name;
        final String image;

        public ReminderItem(String id, String name, String image) {
            this.id = id;
            this.name = name;
            this.image = image;
        }
    }



    public AddReminderAdapter(List<ReminderItem> items) {
        mValues = items;
    }

    public AddReminderAdapter() {
        mValues=null;
    }

    @Override
    public AddReminderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recentfriends, parent, false);
        return new AddReminderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AddReminderAdapter.ViewHolder holder, int position) {
        final ReminderItem reminderItem = mValues.get(position);

        // set name and display profile pic
        displayProfilePic(holder.mProfilePic, reminderItem.image);

        // check if user is following this friend and update follow button appearance
        Context c = holder.mView.getContext();

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mProfilePic;
        private final Context context;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            context = view.getContext();
            //mName = (TextView) view.findViewById(R.id.name);
            mProfilePic = (ImageView) view.findViewById(R.id.profile_image);

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(getActivity(),Account.class);
//                    startActivity(intent);
                    //Toast.makeText(view.getContext(),getAdapterPosition(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,AddReminder.class);
                    intent.putExtra("id",Integer.parseInt(mValues.get(getAdapterPosition()).id));
                    intent.putExtra("type","friend");
                    context.startActivity(intent);
                }
            });
        }
    }

    private void displayProfilePic(ImageView imageView, String url) {
        // helper method to load the profile pic in a circular imageview
        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(false)
                .build();
        Picasso.with(imageView.getContext())
                .load(url)
                .transform(transformation)
                .into(imageView);

    }


}
