package com.codeadventure.oncue;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_setting, container, false);

        TextView acc = (TextView) v.findViewById(R.id.account);
        TextView help = (TextView) v.findViewById(R.id.helpin);
        TextView about = (TextView) v.findViewById(R.id.aboutin);
        TextView refer = (TextView) v.findViewById(R.id.refernearn);
        TextView setting = (TextView) v.findViewById(R.id.appsetting);

        acc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Account.class);
                startActivity(intent);

            }
        });

        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Login.class);
                i.putExtra("check","help");
                startActivity(i);

            }
        });

        about.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = "http://oncue.codeadventure.in";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        refer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"No referral scheme currently available\nStay Tuned",Toast.LENGTH_LONG).show();

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AppSetting.class);
                startActivity(intent);

            }
        });

        return v;
    }

}
