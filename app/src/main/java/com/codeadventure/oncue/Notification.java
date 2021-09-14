package com.codeadventure.oncue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Notification extends Fragment {

    View view;
    RecyclerView recyclerView;
    NotificationAdapter reminderAdapter;
    List<NotificationAdapter.NotificationItems> notificationItemsList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_notification, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.notification_list);
        final TextView noreminder = (TextView) view.findViewById(R.id.noreminder);


        notificationItemsList = new ArrayList<>();
        final OfflineData od = new OfflineData();
        od.main1();

        int i;
        for(i=0;i<15;i++) {
            NotificationAdapter.NotificationItems remind = new NotificationAdapter.NotificationItems(od.noti.get(i).id+"",od.noti.get(i).title, od.noti.get(i).description, false);
            notificationItemsList.add(remind);
        }


        Button clear = (Button) view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                od.noti.clear();
                notificationItemsList.clear();
                reminderAdapter = new NotificationAdapter(notificationItemsList);
                recyclerView.setAdapter(reminderAdapter);
                if (notificationItemsList.size() == 0) {
                    // show message when the list is empty
                    noreminder.setVisibility(View.VISIBLE);
                }
                Toast.makeText(getContext(),"Cleared",Toast.LENGTH_LONG).show();

            }
        });




        //fetchReminders();

        if (notificationItemsList.size() == 0) {
            // show message when the list is empty
            noreminder.setVisibility(View.VISIBLE);
        }

        // initialize the list view adapter
        reminderAdapter = new NotificationAdapter(notificationItemsList);
        recyclerView.setAdapter(reminderAdapter);

        return view;

    }


}