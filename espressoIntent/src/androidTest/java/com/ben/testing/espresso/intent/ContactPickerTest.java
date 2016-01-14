package com.ben.testing.espresso.intent;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.ben.espresso_intent.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

//TODO: Write a test to verify that a particular outgoing intent is launched with the right extras.
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContactPickerTest {

    private final static String TEST_URI = "foo://bar?q=123";

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public final IntentsTestRule<ContactPickerActivity> mActivityRule = new IntentsTestRule<>(ContactPickerActivity.class);


    @Before
    public void stubContactIntent(){
        //We build the fake result
        Intent resultData = new Intent();
        resultData.setData(Uri.parse(TEST_URI));
        Instrumentation.ActivityResult activityResult
                = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // Set up result stubbing when an intent sent to "contacts" is seen.
        intending(allOf(
                hasData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI),
                hasAction(Intent.ACTION_PICK)))
                .respondWith(activityResult);
    }

    /**
     * Intent stubbing example. We provide a fake response for the pick contact activity.
     */
    @Test
    public void activityResultContact_IsHandledProperly(){
        //User action opening the contact. Launching activity expects an URI to be returned and the
        //phone number to be displayed
        onView(ViewMatchers.withId(R.id.activity_main_pick)).perform(click());

        //We assert that the data is well displayed
        onView(withId(R.id.activity_main_contact_uri)).check(matches(withText(TEST_URI)));
    }

}
