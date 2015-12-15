package com.saugatligal.infodroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by macmini on 12/15/15.
 */
public class ParseBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION = "com.saugatligal.infodroid.MESSAGE";
    public static final String PARSE_EXTRA_DATA_KEY = "com.parse.Data";
    public static final String PARSE_JSON_CHANNEL_KEY = "com.parse.Channel";

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        String channel = intent.getExtras().getString(PARSE_JSON_CHANNEL_KEY);
        try {
            JSONObject json = new JSONObject(intent.getExtras().getString(PARSE_EXTRA_DATA_KEY));
            Log.e("ERROR",json.toString());
        }catch (JSONException e){
            Log.e("ERROR","ERROR");
        }


    }
}