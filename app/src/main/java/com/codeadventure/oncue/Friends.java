package com.codeadventure.oncue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Friends extends Fragment {

    RecyclerView recyclerView;
    TextView emptyText;
    EditText editTextSearch;
    View view;
    List<FriendsAdapter.FriendItem> friendList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.activity_friends, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.friends_list);
        emptyText = (TextView) view.findViewById(R.id.nofriend);


        friendList = new ArrayList<>();
        final OfflineData od = new OfflineData();
        od.main1();

        fetchFriends();
        int i;

        for(i=0;i<15;i++) {
            FriendsAdapter.FriendItem remind = new FriendsAdapter.FriendItem(od.frien.get(i).fid,od.frien.get(i).name, "https://www.w3schools.com/css/trolltunga.jpg");
            friendList.add(remind);
        }


        if (friendList.size() == 0) {
            // show message when the list is empty
            emptyText.setVisibility(View.VISIBLE);
        }

        // initialize the list view adapter
        FriendsAdapter friendsAdapter = new FriendsAdapter(friendList);
        recyclerView.setAdapter(friendsAdapter);

        return view;
    }


    private void fetchFriends() {
        Profile p = Profile.getCurrentProfile();
        // make the API call to fetch friends list
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,picture");
        parameters.putInt("limit", 100);
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "me/friends",
                parameters,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        if (response.getError() != null) {
                            // display error message
                            Toast.makeText(getContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                            return;
                        }

                        // parse json data
                        JSONObject jsonResponse = response.getJSONObject();
                        System.out.println("OUT");
                        try {
                            JSONArray jsonData = jsonResponse.getJSONArray("data");
                            System.out.println(jsonData.length());
                            for (int i=0; i<jsonData.length(); i++) {
                                System.out.println("IN");
                                JSONObject jsonUser = jsonData.getJSONObject(i);
                                String id = jsonUser.getString("id");
                                String name = jsonUser.getString("name");
                                String image = jsonUser.getJSONObject("picture").getJSONObject("data").getString("url");

                                // insert new friend item in friendList
                                FriendsAdapter.FriendItem friend = new FriendsAdapter.FriendItem(id, name, image);
                                friendList.add(friend);
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("Error");
                        }





                    }
                }
        ).executeAsync();
    }

}
