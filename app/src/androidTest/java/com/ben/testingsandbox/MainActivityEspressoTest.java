package com.ben.testingsandbox;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.app.ActionBar;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Toolbar;

import com.ben.testingsandbox.custom.matcher.ToolbarTitleMatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityEspressoTest {

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
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void checkInitialCount() {
        String defaultValue = InstrumentationRegistry.getTargetContext().getString(R.string.default_count);
        onView(withId(R.id.activity_main_count)).check(matches(withText(defaultValue)));
    }

    @Test
    public void increment() {
        //Click on the increment button
        onView(withId(R.id.activity_main_increment)).perform(click());
        //Check the counter
        onView(withId(R.id.activity_main_count)).check(matches(withText("1")));
    }

    @Test
    public void incrementTwiceAndRotateScreen() {
        onView(withId(R.id.activity_main_increment))
                .perform(click())
                .perform(click());
        onView(withId(R.id.activity_main_count)).check(matches(withText("2")));

        rotateScreen();

        onView(withId(R.id.activity_main_count)).check(matches(withText("2")));
    }

    /**
     * Checks that we have the right title in the toolbar for this activity.
     */
    @Test
    public void shouldBeAbleToLaunchMainScreen() {
        CharSequence title = InstrumentationRegistry.getTargetContext().getString(R.string.app_name);
        onView(isAssignableFrom(Toolbar.class)).check(matches(ToolbarTitleMatcher.withToolBarTitle(title)));
    }

    /**
     * A simple function for rotating the screen.
     */
    private void rotateScreen(){
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        Activity activity = mActivityRule.getActivity();
        activity.setRequestedOrientation(orientation == Configuration.ORIENTATION_PORTRAIT ?
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
