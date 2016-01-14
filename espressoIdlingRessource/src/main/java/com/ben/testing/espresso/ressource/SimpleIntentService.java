package com.ben.testing.espresso.ressource;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.LocalBroadcastManager;

/**
 * A simple {@link SimpleIntentService} which just waits.
 */
public class SimpleIntentService extends IntentService {

    public static final String INTENT_SERVICE_ACTION = "SimpleIntentService.Action";
    public static final String KEY_DEMAND = "demand";
    public static final String KEY_RESPONSE = "response";

    @VisibleForTesting
    protected static final String BASE_RESPONSE = "Response to: ";

    public SimpleIntentService() {
        super(SimpleIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SystemClock.sleep(1000); //Fake task
        String demand = intent.getStringExtra(KEY_DEMAND);
        Intent response = new Intent(INTENT_SERVICE_ACTION);
        response.putExtra(KEY_RESPONSE, BASE_RESPONSE + demand);

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.sendBroadcast(response);
    }
}
