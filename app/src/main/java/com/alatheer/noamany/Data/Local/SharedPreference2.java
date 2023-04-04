package com.alatheer.noamany.Data.Local;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.alatheer.noamany.Data.Remote.AuthenticationModels.UserModel;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreference2 {
    Context context;
    SharedPreferences mPrefs;
    private static SharedPreference2 instance=null;


    public SharedPreference2(Context context) {
        this.context = context;
    }

    public SharedPreference2() {

    }

    public static  SharedPreference2 getInstance()
    {
        if (instance==null)
        {
            instance = new SharedPreference2();
        }
        return instance;
    }

    public void Create_Update_UserData(Context context, Govern govern)
    {
        mPrefs = context.getSharedPreferences("govern", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userData = gson.toJson(govern);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("govern_data",userData);
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


    public Govern Get_UserData(Context context){

        mPrefs = context.getSharedPreferences("govern", Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String userData = mPrefs.getString("govern_data", "");
        Govern govern=gson.fromJson(userData,Govern.class);
        return govern;


    }
    public void ClearData(Context context) {
        Govern govern = null;
        mPrefs = context.getSharedPreferences("govern", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userData = gson.toJson(govern);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("govern_data", userData);
        editor.apply();
        Create_Update_Session(context,"login");
    }
}
