package com.ben.testing.espresso.ressource;

import android.app.ActivityManager;
import android.content.Context;
import android.support.test.espresso.IdlingResource;

/**
 * A simple {@link IdlingResource} for managing the {@link SimpleIntentService} in Espresso tests.
 */
public class IntentServiceIdlingResource implements IdlingResource {

    private Context mContext;
    private ResourceCallback resourceCallback;

    public IntentServiceIdlingResource(Context context) {
        mContext = context;
    }

    @Override
    public String getName() {
        return IntentServiceIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        boolean idle = !isIntentServiceRunning();
        if (idle && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
         public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.resourceCallback = callback;
    }

    /**
     * Checks if a {@link SimpleIntentService}
     *
     * @return : True if the service is currently running, false otherwise.
     */
    private boolean isIntentServiceRunning() {
        ActivityManager manager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (SimpleIntentService.class.getName().equalsIgnoreCase(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
