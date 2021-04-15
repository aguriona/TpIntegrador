package com.example.tpintegrador;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentFilter;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent i = new Intent();
        i.putExtra("data1","INTENT SERVICE");
        i.putExtra("data2","TimeStamp: "+System.currentTimeMillis());
        i.setAction(MyReciver.EVENTO_01);
        sendBroadcast(i);
        this.sendBroadcast(i);

    }




}
