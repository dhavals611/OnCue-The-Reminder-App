package com.codeadventure.oncue;

import java.util.ArrayList;

/**
 * Created by Deep on 8/27/2017.
 */

public class OfflineData {
    static ArrayList<Reminder_Friend> rem_friend = new ArrayList<Reminder_Friend>();
    static ArrayList<Reminder_Time> rem_time = new ArrayList<Reminder_Time>();
    static ArrayList<notification1> noti = new ArrayList<notification1>();
    static ArrayList<user> u = new ArrayList<user>();
    static ArrayList<friend> frien = new ArrayList<friend>();
    static boolean check=true;

    public static void main1()
    {
        int i;
        Reminder_Friend rf;
        Reminder_Time rt;
        notification1 nt;
        user us;
        friend fr;
        for(i=0;i<15;i++){
            rf = new Reminder_Friend((i+1),"Title "+i+"","Description "+i+"",""+(i+1)+"","left");
            rem_friend.add(rf);
            rt = new Reminder_Time((i+1),"Title "+i+"","Description "+i+"",""+(i+1)+"","left");
            rem_time.add(rt);
            nt = new notification1((i+1),"Title "+i+"","Description "+i+"");
            noti.add(nt);
            us = new user((i+1)+"",(i+1)+"","Fname "+i+"","LName "+i+"","Email "+i+"","Dob "+i+"","Gender "+i+"");
            u.add(us);
            fr = new friend((i+1)+"",(i+1)+"","Name "+i+"");
            frien.add(fr);
        }
    }
}

class Reminder_Friend{
    int id;
    String title;
    String description;
    String fid;
    String status;

    Reminder_Friend(int id,String title,String description,String fid,String status){
        this.id = id;
        this.title = title;
        this.description = description;
        this.fid = fid;
        this.status = status;
    }
}

class Reminder_Time{
    int id;
    String title;
    String description;
    String time;
    String status;

    Reminder_Time(int id, String title, String description, String time, String status){
        this.id = id;
        this.title = title;
        this.description = description;
        this.time = time;
        this.status = status;
    }
}

class notification1{
    int id;
    String title;
    String description;

    notification1(int id,String title,String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }
}

class user{
    String fid;
    String macid;
    String fname;
    String lname;
    String email;
    String dob;
    String gender;

    user(String fid,String macid,String fname,String lname, String email, String gender, String dob){
        this.fid = fid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.macid = macid;
    }

}

class friend{
    String fid;
    String name;
    String macid;

    friend(String fid,String macid,String name){
        this.fid = fid;
        this.macid = macid;
        this.name = name;
    }
}
