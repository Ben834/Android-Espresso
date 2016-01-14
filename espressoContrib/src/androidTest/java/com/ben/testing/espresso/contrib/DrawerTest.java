package com.ben.testing.espresso.contrib;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import com.example.ben.espresso_contrib.R;
import com.ben.testing.espresso.contrib.action.NavigationViewActions;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(JUnit4.class)
public class DrawerTest {

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
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void navDrawerClick() {
        //Open the nav drawer
        onView(ViewMatchers.withId(R.id.activity_main_drawer_layout)).perform(DrawerActions.open());

        //Click on an item
        onView(withId(R.id.activity_main_navigation_view)).perform(NavigationViewActions.navigateTo(R.id.drawer_profile));

        //Check that the right activity is displayed by checking a view inside it
        onView(withId(R.id.activity_profile_text)).check(matches(isDisplayed()));
    }
}
