package com.ben.mockitobasics;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@SmallTest
public class MainManagerTest {

    @Mock
    UserManager mUserManager;

    @Mock
    DataManager mDataManager;

    @InjectMocks
    MainManager mMainManager;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void simpleMainManagerTest(){
        when(mUserManager.getUserName()).thenReturn("Jean-Paul");
        mMainManager.doSomething();
        verify(mDataManager).saveUserName("Jean-Paul");
    }


}
