package com.example.st.Database;

import static java.lang.Math.abs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class SleepTrackerDatabase extends SQLiteOpenHelper {

    //Name of database
    private static String databaseName = "SleepTrackerDb";

    //define the database tables
    private String UserTableCreation=
            "create table User(Email text not null primary key, UserName text not null" +
                    ",Password text not null, SleepTimeHour integer,SleepTimeMin integer," +
                    "WakeupTimeHour integer,WakeupTimeMin integer, SignupDay integer, SignupMonth integer, SignupYear integer)";


    private String SleepingScheduleTableCreation="create table SleepingSchedule(RecordId integer not null primary key autoincrement, SleepingTimeHour integer, "+
            "SleepingTimeMin integer,WakingUpTimeHour integer,WakingUpTimeMin integer, Duration integer, Cycles real, Rating text, Email text, Day integer," +
            " FOREIGN KEY(Email) REFERENCES User(Email))";

    //Create object from SQliteDatabase
    private SQLiteDatabase db;

    //Create the constructor
    public SleepTrackerDatabase(@Nullable Context context) {
        super(context, databaseName, null, 2);
        this.db = db;
    }

    //Override the onCreate method that creates the database tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTableCreation);
        db.execSQL(SleepingScheduleTableCreation);
    }

    //Override the onUpgrade method to update the database when a change is done
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User ");
        db.execSQL("drop table if exists SleepingSchedule");

    }

    //Function Insert to insert user to the database
    public Boolean InsertUser(String email,String uname, String password, int sleeptimehour,int sleeptimemin,int wakeuptimehour,int wakeuptimemin)
    {
        Date date=new Date();                                         //Object date to get the current date
        SQLiteDatabase DB= this.getWritableDatabase();                //Initialize database object to write in the database
        ContentValues contentValues=new ContentValues();              //Create a new object using ContentValues
        contentValues.put("Email",email);                             //Adding columns with its value
        contentValues.put("UserName",uname);
        contentValues.put("Password",password);
        contentValues.put("SleepTimeHour",sleeptimehour);
        contentValues.put("SleepTimeMin",sleeptimemin);
        contentValues.put("WakeupTimeHour",wakeuptimehour);
        contentValues.put("WakeupTimeMin",wakeuptimemin);
        contentValues.put("SignupDay",date.getDate());
        contentValues.put("SignupMonth",date.getMonth()+1);
        contentValues.put("SignupYear",date.getYear()+1900);
        long res=DB.insert("User",null,contentValues); //insert that object to the database "table user"
        DB.close();

        //Check whether the insert is done successfully
        if (res==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //Function Delete to delete the user from database
    public Boolean DeleteUser(String email)
    {
        SQLiteDatabase DB=this.getWritableDatabase();                  //initialize database object to update the database
        Cursor cursor= DB.rawQuery("Select * from User where Email =?",new String[]{email});   //Define Cursor that will hold the returned record with that email

        if (cursor.getCount()>0)   //Check if the cursor holds a record
        {
            long res= DB.delete("User","Email=?",new String[]{email}); //Delete that record from database
            DB.close();
            //Check whether the delete is done successfully
            if (res==-1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            DB.close();
            return false;
        }
    }

    //Function that checks whether a user with a specified email found in database or not
    public Boolean CheckUser(String email)
    {
        SQLiteDatabase DB=this.getReadableDatabase();                 //initialize database object to read from the database
        Cursor cursor=DB.rawQuery("Select * from User where Email=?",new String[]{email});   //Define Cursor that will hold the returned record with that given email
        if (cursor.getCount()>0)                      //Check if the cursor holds a record
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Function View to view the user data from database
    public Cursor ViewUserData()
    {
        SQLiteDatabase DB=this.getReadableDatabase();                //initialize database object to read from the database
        Cursor cursor=DB.rawQuery("Select * from User",null);   //Define Cursor that will hold the returned records to view them
        return cursor;
    }
    public Cursor ViewCurrentUserData(String email)
    {
        SQLiteDatabase DB=this.getReadableDatabase();                //initialize database object to read from the database
        Cursor cursor=DB.rawQuery("Select * from User where Email=?",new String[]{email});   //Define Cursor that will hold the returned records to view them
        return cursor;
    }

    //Function that checks the user password
    public Boolean CheckUsersPassword(String email,String password)
    {
        SQLiteDatabase DB=this.getReadableDatabase();                //initialize database object to read from the database
        Cursor cursor=DB.rawQuery("Select * from User where Email=? and Password=?", new String[]{email,password});  //Define Cursor that will hold the returned record with that given email and password
        if (cursor.getCount()>0)          //Check if the cursor holds a record
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Function that calculates the number of days since the user has signed in
    public int NumDays(String email)
    {
        db=getReadableDatabase();                                 //initialize database object to read from the database
        Cursor matchedUser=db.rawQuery("select * from User where Email=?", new String[]{email});  //Define Cursor that will hold the returned records with that given email
        matchedUser.moveToFirst();                          //Point to the first matched record

        //Get the index of the needed columns
        final int dayidx= matchedUser.getColumnIndex("SignupDay");
        final int monthidx= matchedUser.getColumnIndex("SignupMonth");
        final int yearidx= matchedUser.getColumnIndex("SignupYear");

        //Get the values to set the signed up date
        int StartDay=matchedUser.getInt(dayidx);
        int StartMonth=matchedUser.getInt(monthidx);
        int StartYear=matchedUser.getInt(yearidx);
        db.close();
        Date date=new Date();                                  //Object date to get the current date
        int days=0;
        //Calculate the numbers of days
        if((date.getMonth()+1)-StartMonth==0)
        {
            days+=( date.getDate()-StartDay);
            return days;
        }
        else {
            days += (30 - StartDay) + date.getDate();
        }
        if ((date.getYear()+1900)-StartYear==1)
        {
            days+=(date.getMonth()-StartMonth+12-1)*30;
        }
        else
        {
            days += (abs(date.getMonth() - StartMonth - 1)) * 30;
        }
        return days;
    }

    //Function view that views the personal data of the user using a specified email
    public Cursor ViewPersonalData(String email)
    {
        SQLiteDatabase DB=this.getReadableDatabase();                         //initialize database object to read from the database
        Cursor cursor=DB.rawQuery("Select * from SleepingSchedule where Email=?",new String[]{email});    //Define Cursor that will hold the returned records with that given email
        return cursor;
    }

    //Function to calculate the duration in minutes
    public int getDuration(int hour,int min)
    {
        int total=(60*hour)+min;
        return total;
    }

    //Function to calculate the number of sleeping cycles
    public float GetCycles(int duration)
    {
        float numcycles=duration/90;
        return numcycles;
    }

    //Function to get the rate of the user's sleep based on the number of cycles
    public String GetRating(float cycles)
    {
        if (cycles>=4.0 && cycles<=6.0)
        {
            return "Excellent";
        }
        else if ((cycles>6.0 && cycles <=7.0)||(cycles <4.0 && cycles>=3.0))
        {
            return "Good";
        }
        else
        {
            return "Bad";
        }
    }

    //Function Insert to insert the sleeping schedule for each user to the database
    public Boolean InsertSleepingSchedule(int sleeptimehour,int sleeptimemin,int wakeuptimehour,int wakeuptimemin,int duration,float cycles,String rating,String email,int day)
    {
        SQLiteDatabase DB= this.getWritableDatabase();          //initialize database object to write in the database
        ContentValues contentValues=new ContentValues();        //Create a new object using ContentValues
        contentValues.put("SleepingTimeHour",sleeptimehour);    //Adding columns with its value
        contentValues.put("SleepingTimeMin",sleeptimemin);
        contentValues.put("WakingUpTimeHour",wakeuptimehour);
        contentValues.put("WakingUpTimeMin",wakeuptimemin);
        contentValues.put("Duration",duration);
        contentValues.put("Cycles",cycles);
        contentValues.put("Rating",rating);
        contentValues.put("Email",email);
        contentValues.put("Day",day);
        long res=DB.insert("SleepingSchedule",null,contentValues);   //insert that object to the database "table sleeping schedule"
        DB.close();

        //Check whether the insert is done successfully
        if (res==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //Function that updates the default sleeping schedule entry of specific user
    public Boolean UpdateUserSleepingSchedule(String email, int sleeptimehour,int sleeptimemin)
    {
        SQLiteDatabase DB=this.getWritableDatabase();              //Initialize database object to write in the database
        ContentValues contentValues=new ContentValues();           //Create a new object using ContentValues
        contentValues.put("SleepTimeHour",sleeptimehour);      //update columns with its new value
        contentValues.put("SleepTimeMin",sleeptimemin);
        Cursor cursor=DB.rawQuery("Select * from User where Email=? ",new String[]{email});   //Define Cursor that will hold the returned record with that email
        if(cursor.getCount()>0) {
            long res = DB.update("User", contentValues, "Email=? ", new String[]{email});  //update that object in database "table sleeping schedule"

            // DB.close();
            //Check whether the update is done successfully
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //Function that updates the default waking schedule entry of specific user
    public Boolean UpdateUserWakingSchedule(String email,int wakeuptimehour, int wakeuptimemin)
    {
        SQLiteDatabase DB=this.getWritableDatabase();              //Initialize database object to write in the database
        ContentValues contentValues=new ContentValues();           //Create a new object using ContentValues
        contentValues.put("WakeupTimeHour",wakeuptimehour);       //update columns with its new value
        contentValues.put("WakeUpTimeMin",wakeuptimemin);
        Cursor cursor=DB.rawQuery("Select * from User where Email=? ",new String[]{email});   //Define Cursor that will hold the returned record with that email
        if(cursor.getCount()>0) {
            long res = DB.update("User", contentValues, "Email=? ", new String[]{email});  //update that object in database "table sleeping schedule"
            DB.close();
            //Check whether the update is done successfully
            if (res == -1) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //Function Delete to delete a specific sleeping schedule entry from the database
    public Boolean DeleteSleepingSchedule(String email)
    {
        SQLiteDatabase DB=this.getWritableDatabase();            //initialize database object to update the database
        int day=NumDays(email);                                  //get the day number
        String d=String.valueOf(day);
        Cursor cursor= DB.rawQuery("Select * from SleepingSchedule where Email=? and Day=?",new String[]{email,d});   //Define Cursor that will hold the returned record with that specific email and day number
        if(cursor.getCount()>0)       //Check if the cursor holds a record
        {
            long res=DB.delete("SleepingSchedule","Email=? and Day=?",new String[]{email,d});         //Delete that record from database
            DB.close();
            //Check whether the delete is done successfully
            if(res==-1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            DB.close();
            return false;
        }
    }

}