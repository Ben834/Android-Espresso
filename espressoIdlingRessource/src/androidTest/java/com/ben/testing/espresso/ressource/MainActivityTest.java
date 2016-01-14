package com.ben.testing.espresso.ressource;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.example.ben.espresso_idling_ressource.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
public class MainActivityTest {

    private IntentServiceIdlingResource mIntentServiceIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIntentServiceIdlingResource() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        mIntentServiceIdlingResource = new IntentServiceIdlingResource(instrumentation.getContext());
        Espresso.registerIdlingResources(mIntentServiceIdlingResource);
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(mIntentServiceIdlingResource);
    }

    @Test
    public void checkIntentResponse(){
        //Click on the button
        onView(ViewMatchers.withId(R.id.activity_main_launch_service)).perform(click());
        //Check that the text view display the intent service response
        onView(withId(R.id.activity_main_service_response))
                .check(matches(withText(SimpleIntentService.BASE_RESPONSE + MainActivity.SERVICE_DEMAND)));
    }

}
