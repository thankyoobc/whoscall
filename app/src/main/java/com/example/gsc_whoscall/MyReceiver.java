package com.example.gsc_whoscall;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Locale;

public class MyReceiver extends BroadcastReceiver {

    public static final String TAG = "PHONE STATE";
    private static String mLastState;

    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //Log.d(TAG,"onReceive()");


        /**
         * http://mmarvick.github.io/blog/blog/lollipop-multiple-broadcastreceiver-call-state/
         * 2번 호출되는 문제 해결
         */
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(mLastState)) {
            return;

        } else {
            mLastState = state;

        }

        Log.d("1","Onreceive");


        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            //String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            //final String phone_number = PhoneNumberUtils.formatNumber(incomingNumber, Locale.getDefault().getCountry());

            //Log.d("1", phone_number);

            Intent serviceIntent = new Intent(context, MyService.class);
            serviceIntent.putExtra(MyService.EXTRA_CALL_NUMBER, "phone_number");
            context.startService(serviceIntent);




        }

        throw new UnsupportedOperationException("Not yet implemented");
    }






}