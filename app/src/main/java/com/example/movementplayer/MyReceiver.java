package com.example.movementplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent i) {

        try
        {
            Bundle bundle=i.getExtras();
            String state = bundle.getString(TelephonyManager.EXTRA_STATE);
            Intent intent = new Intent("broadcast");
            //RINGING
            if (state.equals("RINGING"))
            {
                intent.putExtra("pause_game", "pause");
            }
            context.sendBroadcast(intent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}