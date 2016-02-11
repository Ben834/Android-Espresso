package com.ben.mockitobasics;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * A simple example of dependency injection via Mockito.
 */
@SmallTest
public class MockitoInjection {

    @Mock
    BrewingManager mBrewingManager;

    @Mock
    PouringManager mPouringManager;

    @InjectMocks
    CoffeeManager mCoffeeManager;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void simpleMainManagerTest() {
        when(mBrewingManager.getUserName()).thenReturn("Espresso");
        mCoffeeManager.doSomething();
        verify(mPouringManager).pour("Espresso");
    }


}
