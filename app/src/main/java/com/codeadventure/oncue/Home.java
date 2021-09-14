package com.codeadventure.oncue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Home extends Fragment {

    RecyclerView recyclerView;
    List<ReminderAdapter.ReminderItem> reminderList;
    TextView noreminder;
    public Home()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_home, container, false);

        TextView date1 = (TextView) view.findViewById(R.id.date2);
        Calendar c = Calendar.getInstance();

        TextView month = (TextView) view.findViewById(R.id.month);

        SimpleDateFormat df = new SimpleDateFormat("dd");
        SimpleDateFormat df1 = new SimpleDateFormat("MMM, yyyy");
        String formattedDate = df.format(c.getTime());
        String formattedMonth = df1.format(c.getTime());
        date1.setText(formattedDate);
        month.setText(formattedMonth);


        final OfflineData od = new OfflineData();

        ReminderNotification rm = new ReminderNotification();
        if(od.check)
            rm.notify(getContext(),"Hello",1,"Your Friend is near you",false,"Reminder Description");

        recyclerView = (RecyclerView) view.findViewById(R.id.reminder_list);
        noreminder = (TextView) view.findViewById(R.id.noreminder);

        reminderList = new ArrayList<>();

        od.main1();
        int i;
        for(i=0;i<15;i++) {
            ReminderAdapter.ReminderItem remind = new ReminderAdapter.ReminderItem(od.rem_friend.get(i).id+"", od.rem_friend.get(i).title, "https://www.w3schools.com/css/trolltunga.jpg");
            reminderList.add(remind);
        }


        //fetchReminders();

        if (reminderList.size() == 0) {
            // show message when the list is empty
            noreminder.setVisibility(View.VISIBLE);
        }

        // initialize the list view adapter
        ReminderAdapter reminderAdapter = new ReminderAdapter(reminderList);
        recyclerView.setAdapter(reminderAdapter);

        return view;
    }
}
