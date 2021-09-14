package com.codeadventure.oncue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddReminder extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<AddReminderAdapter.ReminderItem> friendList;
    int id=0;
    String type="";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_add_reminder, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recentfriends);
        friendList = new ArrayList<>();

        final OfflineData od = new OfflineData();
        od.main1();

        int i;
        for(i=0;i<15;i++) {
            AddReminderAdapter.ReminderItem remind = new AddReminderAdapter.ReminderItem(od.frien.get(i).fid, od.frien.get(i).name, "http://industribune.net/wp-content/uploads/2015/02/industribune-default-no-profile-pic.jpg");
            friendList.add(remind);
        }

        EditText titlemain = (EditText) view.findViewById(R.id.titlemain);
        EditText description = (EditText) view.findViewById(R.id.description_edit);
        //Intent in = getActivity().getIntent();
//        if(in.hasExtra("id")) {
//            id = Integer.parseInt(in.getExtras().get("id").toString());
//            type = in.getExtras().get("type").toString();
//        }

        int value = getArguments().getInt("id");

//        for(i=0;i<15;i++){
//            if(od.rem_friend.get(i).id==id){
//                titlemain.setText(od.rem_friend.get(i).title);
//                //set.setText("Friend " + od.rem_friend.get(i).fid);
//                description.setText(od.rem_friend.get(i).description);
//                break;
//
//            }
//        }

        if(type.equalsIgnoreCase("friend")){

        }




        //fetchReminders();

        if (friendList.size() == 0) {
            // show message when the list is empty
            //noreminder.setVisibility(View.VISIBLE);
        }

        // initialize the list view adapter
        AddReminderAdapter reminderAdapter = new AddReminderAdapter(friendList);
        recyclerView.setAdapter(reminderAdapter);

        final TimePicker tp = (TimePicker) view.findViewById(R.id.timePicker);
        final DatePicker dp = (DatePicker) view.findViewById(R.id.datePicker);

        ImageButton time = (ImageButton) view.findViewById(R.id.time);
        final ImageButton location = (ImageButton) view.findViewById(R.id.location);
        ImageButton friend = (ImageButton) view.findViewById(R.id.friend);

        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               dp.setVisibility(View.VISIBLE);
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate = df1.format(c.getTime());
                //dp.setMinDate(Long.parseLong(formattedDate));

            }
        });

        location.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Currently this feature is unavailable",Toast.LENGTH_LONG).show();

            }
        });

        friend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


            }
        });

        return view;

    }
}
