package com.codeadventure.oncue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Reminder extends AppCompatActivity {

    int id;
    String type;
    int found_i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Intent i1 = getIntent();
        id = Integer.parseInt(i1.getExtras().get("id").toString());
        type = i1.getStringExtra("type").toString();
        final OfflineData od = new OfflineData();
        int i;
        od.main1();

        TextView title = (TextView) findViewById(R.id.title);
        ImageButton delete = (ImageButton) findViewById(R.id.delete);
        ImageButton edit = (ImageButton) findViewById(R.id.edit);
        TextView set = (TextView) findViewById(R.id.set);
        TextView body = (TextView) findViewById(R.id.body);

        if(type.equalsIgnoreCase("friend")){
            for(i=0;i<15;i++){
                if(od.rem_friend.get(i).id==id){
                    title.setText(od.rem_friend.get(i).title);
                    set.setText("Friend " + od.rem_friend.get(i).fid);
                    body.setText(od.rem_friend.get(i).description);
                    found_i = i;
                    break;

                }
            }
        }
        else{
            for(i=0;i<15;i++){
                if(od.rem_time.get(i).id==id){
                    title.setText(od.rem_time.get(i).title);
                    set.setText("Time "+od.rem_time.get(i).time);
                    body.setText(od.rem_time.get(i).description);
                    found_i = i;
                    break;

                }
            }

        }

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(type.equalsIgnoreCase("friend")){
                    od.rem_friend.remove(found_i);
                    Intent intent = new Intent(getApplicationContext(),Navigation.class);
                    startActivity(intent);

                }


            }
        });

        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddReminder.class);
                intent.putExtra("id",id);
                intent.putExtra("type","reminder");
                startActivity(intent);


            }
        });
    }


}
