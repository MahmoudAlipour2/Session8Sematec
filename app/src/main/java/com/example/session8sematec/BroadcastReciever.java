package com.example.session8sematec;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Airplane Mode is Activated!", Toast.LENGTH_SHORT).show();

    }
}
