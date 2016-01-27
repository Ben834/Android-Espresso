package com.ben.mockitobasics;

import android.test.suitebuilder.annotation.SmallTest;

import com.ben.mockitobasics.model.User;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 * @see http://docs.mockito.googlecode.com/hg/org/mockito/Mockito.html#6
 */
//TODO: Check ArgumentCaptor
@SmallTest
public class MockitoExamples {

    @Mock
    private User mockedUser;

    @Mock
    private List<Object> mockedArrayList;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void simpleVerification() {
        //mock creation
        //We use the mock object
        mockedUser.setUsername("Paul");
        //verification
        verify(mockedUser).setUsername("Paul");
    }

    @Test
    public void simpleStubbing() {
        when(mockedUser.getUsername()).thenReturn("Paul");
        when(mockedUser.getProfilePicture()).thenThrow(new RuntimeException());
        System.out.println(mockedUser.getUsername());
        System.out.println(mockedUser.getProfilePicture());
    }

    @Test
    public void stubbingVoid() {
        doThrow(new RuntimeException()).when(mockedArrayList).clear();
        mockedArrayList.clear();
    }


    //Argument matcher - Allow flexible verification or stubbing. Sometimes it can be better to use ArgumentCaptor
    class IsListOfTwoElements implements ArgumentMatcher<List> {
        @Override
        public boolean matches(Object list) {
            return ((List) list).size() == 2;
        }
    }

    @Test
    public void argumentMatcher() {
        //stubbing using built-in anyInt() argument Matcher
        when(mockedArrayList.get(anyInt())).thenReturn("Paul");
        //stubbing using hamcrest
        when(mockedArrayList.addAll(argThat(new IsListOfTwoElements()))).thenReturn(true);
        boolean result = mockedArrayList.addAll(Arrays.asList("Paul", "Gérard"));
        verify(mockedArrayList).addAll(argThat(new IsListOfTwoElements()));
        assertEquals(result, true);
    }

    //Capturing arguments for further assertions. Ok for verification but not for stubbing.
    @Test
    public void captureArguments(){
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        mockedUser.setUsername("Paul");
        verify(mockedUser).setUsername(captor.capture());
        assertEquals("Paul", captor.getValue());
    }

    @Test
    public void verificationOrder() {
        User firstMockedUser = mock(User.class);
        User secondMockedUser = mock(User.class);
        //We use the mocks
        firstMockedUser.setUsername("Paul");
        secondMockedUser.setUsername("Gérard");
        //create inOrder object passing any mocks that need to be verified in order
        InOrder inOrder = inOrder(firstMockedUser, secondMockedUser);
        //We make sure that the first mock was called before the second one
        inOrder.verify(firstMockedUser).setUsername("Paul");
        inOrder.verify(secondMockedUser).setUsername("Gérard");
    }

    @Test
    public void verificationNerverHappened() {
        mockedUser.setUuid("0");
        //ordinary verification
        verify(mockedUser).setUuid("0");
        //Verify that the method was never called on a mock
        verify(mockedUser, never()).setUuid("2");
    }

    //To be used occasionally
    @Test
    public void simpleSpy() {
        User user = new User("0","Franck","nothing");
        User spy = Mockito.spy(user);
        //Using the spy calls real methods
        spy.setUsername("Paul");
        //and we can make verification
        verify(spy).setUsername("Paul");
    }
}