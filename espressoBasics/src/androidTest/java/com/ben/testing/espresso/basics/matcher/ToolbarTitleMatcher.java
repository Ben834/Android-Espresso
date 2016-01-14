package com.ben.testing.espresso.basics.matcher;


import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.is;

/**
 * A custom {@link org.hamcrest.Matcher} for Espresso that check the tool bar title.
 */
public class ToolbarTitleMatcher {

    public static BoundedMatcher<View, Toolbar> withToolBarTitle(final CharSequence substring){
        return withToolbarTitle(is(substring));
    }

    private static BoundedMatcher<View, Toolbar> withToolbarTitle(final Matcher<CharSequence> stringMatcher) {
        return new BoundedMatcher<View, Toolbar>(Toolbar.class) {
            @Override
            protected boolean matchesSafely(Toolbar toolbar) {
                return stringMatcher.matches(toolbar.getTitle());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with toolbar title");
            }
        };
    }

}
