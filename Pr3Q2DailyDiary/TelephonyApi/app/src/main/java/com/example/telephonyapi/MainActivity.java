package com.example.telephonyapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.MenuInflater;
import android.widget.TextView;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    PhoneStateListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.tv);
        TelephonyManager tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        listener=new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                String PhoneState="N/A";

                switch (state)
                {
                    case TelephonyManager.CALL_STATE_IDLE:
                        PhoneState="IDLE";
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        PhoneState="RINGING";
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        PhoneState="OFFHOOK";
                        break;

                }
                tv.append(String.format("\n Call state change: %s",PhoneState));
            }
        };
        tm.listen(listener,PhoneStateListener.LISTEN_CALL_STATE);

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},0);
        }

        String PhoneType="N/A";
        switch (tm.getPhoneType())
        {
            case TelephonyManager.PHONE_TYPE_GSM:
                PhoneType="GSM";
                break;
            case TelephonyManager.PHONE_TYPE_CDMA:
                PhoneType="CDMA";
                break;
            case TelephonyManager.PHONE_TYPE_SIP:
                PhoneType="SIP";
                break;
            case TelephonyManager.PHONE_TYPE_NONE:
                PhoneType="NONE";
                break;
        }
        tv.append("\n IMEI number: "+tm.getDeviceId());
        tv.append("\n SIM Serial number: "+tm.getSimSerialNumber());
        tv.append("\n SIM Country ISO: "+tm.getSimCountryIso());
        tv.append("\n Network Country ISO: "+tm.getNetworkCountryIso());
        tv.append("\n Voice Mail number: "+tm.getVoiceMailNumber());
        tv.append("\n Roaming: "+tm.isNetworkRoaming());
        tv.append("\n Phone Type: "+PhoneType);
    }
}
