package com.example.ben.espresso_intent;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.ben.espresso_intent.matcher.ImageViewHasDrawableMatcher.hasDrawable;
import static org.hamcrest.Matchers.not;

@RunWith(JUnit4.class)
@LargeTest
public class ImagePickerTest {

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p/>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p/>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public final IntentsTestRule<ContactPickerActivity> mActivityRule = new IntentsTestRule<>(ContactPickerActivity.class);

    @Before
    public void stubPictureIntent() {
        //Fake result
        Intent resultData = new Intent();
        resultData.putExtra(ContactPickerActivity.KEY_IMAGE_DATA, BitmapFactory.decodeResource(
                mActivityRule.getActivity().getResources(), R.mipmap.ic_launcher));
        Instrumentation.ActivityResult activityResult
                = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);

        // Set up result stubbing when an intent sent to "contacts" is seen.
        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(activityResult);
    }

    @Test
    public void takePhoto_drawableIsApplied() {
        //Check that the imageview doesn't have a drawable applied
        onView(withId(R.id.activity_main_picture_pick)).check(matches(not(hasDrawable())));

        //Click on the button
        onView(withId(R.id.activity_main_picture_pick)).perform(click());

        //Check that the image has a drawable
        onView(withId(R.id.activity_main_picture_pick)).check(matches(hasDrawable()));

    }

}
