package com.codeadventure.oncue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

public class AppSetting extends AppCompatActivity {


    static boolean check = true;
    Switch s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_setting);

        s = (Switch) findViewById(R.id.notification);
        ImageButton done = (ImageButton) findViewById(R.id.done);
//        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(s.isChecked()){
//                    check = true;
//                    s.setChecked(true);
//                }
//                else{
//                    check=false;
//                    s.setChecked(false);
//                }
//            }
//        });

        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(s.isChecked()){
                    check = true;
                    s.setChecked(true);
                }
                else{
                    check = false;
                    s.setChecked(false);
                }
                Intent intent = new Intent(getApplicationContext(),Navigation.class);
                startActivity(intent);

            }
        });

        OfflineData od = new OfflineData();
        od.check = this.check;
}
}