package com.codeadventure.oncue;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    public static class FriendItem {
        final String id;
        final String name;
        final String image;

        public FriendItem(String id, String name, String image) {
            this.id = id;
            this.name = name;
            this.image = image;
        }
    }

    private final List<FriendItem> mValues;

    public FriendsAdapter(List<FriendItem> items) {
       mValues = items;
   }

    public FriendsAdapter() {
       mValues=null;
    }

    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_friends, parent, false);
        return new FriendsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FriendsAdapter.ViewHolder holder, int position) {
        final FriendItem friendItem = mValues.get(position);

        // set name and display profile pic
        holder.mName.setText(friendItem.name);
        displayProfilePic(holder.mProfilePic, friendItem.image);

        // check if user is following this friend and update follow button appearance
        Context c = holder.mView.getContext();

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mName;
        final Context context;
        final ImageView mProfilePic;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            context = view.getContext();
            mName = (TextView) view.findViewById(R.id.name);
            mProfilePic = (ImageView) view.findViewById(R.id.image);

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(getActivity(),Account.class);
//                    startActivity(intent);
                    //Toast.makeText(view.getContext(),getAdapterPosition(),Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(context,AddReminder.class);
//                    intent.putExtra("type","friend");
//                    intent.putExtra("id",Integer.parseInt(mValues.get(getAdapterPosition()).id));
//                    context.startActivity(intent);
                    AddReminder ar = new AddReminder();
                    final Bundle bundle = new Bundle();
                    bundle.putInt("id", Integer.parseInt(mValues.get(getAdapterPosition()).id));
                    ar.setArguments(bundle);
                    ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
                    viewPager.setCurrentItem(2);

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
                .placeholder(R.drawable.circle)
                .transform(transformation)
                .into(imageView);

    }

}
