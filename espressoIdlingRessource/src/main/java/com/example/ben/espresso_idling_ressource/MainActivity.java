package com.example.ben.espresso_idling_ressource;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private LocalBroadcastManager manager;
    private BroadcastReceiver receiver;
    private IntentFilter intentFilter;

    @VisibleForTesting
    protected static final String SERVICE_DEMAND = "MainActivity.demand";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button launching the service
        Button launchServiceBtn = (Button) findViewById(R.id.activity_main_launch_service);
        launchServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SimpleIntentService.class);
                intent.putExtra(SimpleIntentService.KEY_DEMAND, SERVICE_DEMAND);
                startService(intent);
            }
        });

        //Text displaying the service response
        final TextView serviceResultTV = (TextView) findViewById(R.id.activity_main_service_response);

        manager = LocalBroadcastManager.getInstance(this);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String response = intent.getStringExtra(SimpleIntentService.KEY_RESPONSE);
                serviceResultTV.setText(response);
            }
        };
        intentFilter = new IntentFilter(SimpleIntentService.INTENT_SERVICE_ACTION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerReceiver(receiver, intentFilter);
    }
}
