package com.alatheer.noamany.Data.Local;

import android.content.Context;
import android.content.SharedPreferences;
import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.google.gson.Gson;

public class UserSharedPreference {
    Context context;
    SharedPreferences mPrefs;
    private static UserSharedPreference instance=null;


    public UserSharedPreference(Context context) {
        this.context = context;
    }

    public UserSharedPreference() {

    }

    public static  UserSharedPreference getInstance() {
        if (instance==null) {
            instance = new UserSharedPreference();
        }
        return instance;
    }

    public void Create_Update_UserData(Context context, UserModel userModel)
    {
        mPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userData = gson.toJson(userModel);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("user_data",userData);
        editor.apply();
        Create_Update_Session(context, "login");

    }

    public void Create_Update_Session(Context context,String session)
    {
        mPrefs = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("state",session);
        editor.apply();
    }


    public String getSession(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences("session",Context.MODE_PRIVATE);
        String session = preferences.getString("state", "logout");
        return session;
    }


    public UserModel Get_UserData(Context context){

        mPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String userData = mPrefs.getString("user_data", "");
        UserModel userModel=gson.fromJson(userData,UserModel.class);
        return userModel;


    }
    public void ClearData(Context context) {
        UserModel userModel = null;
        mPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userData = gson.toJson(userModel);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("user_data", userData);
        editor.apply();
        Create_Update_Session(context,"login");
    }
}