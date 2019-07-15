package com.example.minh.animenotification.myService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.WindowManager;

import com.example.minh.animenotification.AnimeListener;
import com.example.minh.animenotification.MyGroupView;

public class IncomingCallReceiver extends BroadcastReceiver {
    private WindowManager mWindowManager;
    private MyGroupView mViewIcon;
    private WindowManager.LayoutParams layoutParamsIcon;
    private AnimeListener mListener;

    public IncomingCallReceiver() {
    }

    public IncomingCallReceiver(AnimeListener listener) {
        mListener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(final Context context, Intent intent) {
//        Log.d("TAGG", "phone-message");
        SharedPreferences sharedPreferences = context.getSharedPreferences("ANIME_NOTIFY", Context.MODE_PRIVATE);
        boolean isPhone = sharedPreferences.getBoolean("IS_PHONE", false);
        boolean isMessage = sharedPreferences.getBoolean("IS_MESSAGE", false);

        if (intent.getAction().equals("android.intent.action.PHONE_STATE") && isPhone == true) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String state = extras.getString(TelephonyManager.EXTRA_STATE);
                if (state.equals(TelephonyManager.EXTRA_STATE_RINGING) && ServiceIncomingCall.isAnimation == false) {
                    ServiceIncomingCall.isAnimation = true;
                    mListener.animeAnimation();
//                    String phoneNumber = extras
//                            .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                    Log.d("TAGG", "phone");
                }

                if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                    ServiceIncomingCall.isAnimation = false;
                    Log.d("TAGG", "stop");
                }

                if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                    ServiceIncomingCall.isAnimation = false;
                    Log.d("TAGG", "stop1");
                }
            }
        }

        if (intent.getAction().
                equals("android.provider.Telephony.SMS_RECEIVED") && isMessage == true
                && ServiceIncomingCall.isAnimation == false) {
            Log.d("TAGg","mess"+": "+ServiceIncomingCall.isAnimation);
            ServiceIncomingCall.isAnimation = true;
            mListener.animeAnimation();
        }
    }
}
