package com.codeadventure.oncue;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


public class Account extends AppCompatActivity {

    AccessToken accessToken;
    Button log_out;
    TextView fname_set, lname_set, gender_set, dob_set, email_set;
    ImageView profile1;
    Profile profile_curr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        accessToken = AccessToken.getCurrentAccessToken();

        fname_set = (TextView) findViewById(R.id.fname_set);
        lname_set = (TextView) findViewById(R.id.lname_set);
        gender_set = (TextView) findViewById(R.id.gender_set);
        dob_set = (TextView) findViewById(R.id.dob_set);
        email_set = (TextView) findViewById(R.id.email_set);
        log_out = (Button) findViewById(R.id.log_out);
        profile1 = (ImageView) findViewById(R.id.profile_image);

        final ProfileTracker mProfileTracker;

        if (accessToken != null) {
            if(Profile.getCurrentProfile()==null){
                Intent intent = getIntent();
                startActivity(intent);
                finish();
            }
            else {
                profile_curr = Profile.getCurrentProfile();
                displayProfileInfo(profile_curr);
            }
        }

        else {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }

        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile == null) {
                    Intent intent = getIntent();
                    startActivity(intent);
                    finish();

                } else {
                    profile_curr = currentProfile;
                    displayProfileInfo(currentProfile);
                }
            }
        };

        log_out = (Button) findViewById(R.id.log_out);

//        log_out.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                logo
//            }
//        });
    }


        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(
                AccessToken oldAccessToken,
                AccessToken currentAccessToken) {

            if (currentAccessToken != null) {
                if(Profile.getCurrentProfile()==null){
                    Intent intent = getIntent();
                    startActivity(intent);
                    finish();

                }
                else {
                    profile_curr = Profile.getCurrentProfile();
                    displayProfileInfo(profile_curr);
                }
            }
            else {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }

        }
    };


    public void displayProfileInfo(Profile profile) {
        fname_set.setText(profile.getFirstName());
        lname_set.setText(profile.getLastName());

        Uri profilePicUri = profile.getProfilePictureUri(100, 100);
        displayProfilePic(profilePicUri);
        if (isNetworkStatusAvialable(getApplicationContext())) {
            GraphRequest request = GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            try {
                                String email = object.getString("email");
                                String birthday = object.getString("birthday"); // 01/31/1980 format
                                String gender = object.getString("gender");

                                email_set.setText(email);
                                dob_set.setText(birthday);
                                gender_set.setText(gender.toUpperCase());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender,birthday,picture");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }

    private void displayProfilePic(Uri uri) {
        // helper method to load the profile pic in a circular imageview
        com.squareup.picasso.Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(false)
                .build();
        Picasso.with(Account.this)
                .load(uri)
                .transform(transformation)
                .into(profile1);
    }

    public void logout(View view) {

                    LoginManager.getInstance().logOut();
                    startActivity(new Intent(getApplicationContext(), Login.class));
    }

    protected void onResume()
    {
        profile_curr = Profile.getCurrentProfile();
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
            {
                return netInfos.isConnected();
            }
        }
        return false;
    }

}
