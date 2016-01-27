package com.ben.mockitobasics;

import android.test.suitebuilder.annotation.SmallTest;

import com.ben.mockitobasics.model.User;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
//TODO: Check ArgumentCaptor
@SmallTest
public class MockitoExamples {

    //1-Verification
    @Test
    public void simpleVerification() {
        //mock creation
        User mockedUser = mock(User.class);
        //We use the mock object
        mockedUser.setUsername("Paul");
        //verification
        verify(mockedUser).setUsername("Paul");
    }

    //2-Stubbing 1
    @Test
    public void simpleStubbing() {
        User mockedUser = mock(User.class);
        when(mockedUser.getUsername()).thenReturn("Paul");
        when(mockedUser.getProfilePicture()).thenThrow(new RuntimeException());
        System.out.println(mockedUser.getUsername());
        System.out.println(mockedUser.getProfilePicture());
    }

    //3- Argument matcher - Allow flexible verification or stubbing. Sometimes it can be better to
    //user ArgumentCaptor
    class IsListOfTwoElements implements ArgumentMatcher<List> {
        @Override
        public boolean matches(Object list) {
            return ((List) list).size() == 2;
        }
    }

    @Test
    public void argumentMatcher() {
        ArrayList mockedArrayList = mock(ArrayList.class);
        //stubbing using built-in anyInt() argument Matcher
        when(mockedArrayList.get(anyInt())).thenReturn("Paul");
        //stubbing using hamcrest
        when(mockedArrayList.addAll(argThat(new IsListOfTwoElements()))).thenReturn(true);
        System.out.println(mockedArrayList.addAll(Arrays.asList("Paul", "Gérard")));
        verify(mockedArrayList).addAll(argThat(new IsListOfTwoElements()));
    }

    //4-Verification order
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

    //5- Making sure interaction never happened
    @Test
    public void verificationNerverHappened() {
        User mockedUser = mock(User.class);
        mockedUser.setUuid("0");
        //ordinary verification
        verify(mockedUser).setUuid("0");
        //Verify that the method was never called on a mock
        verify(mockedUser, never()).setUuid("2");
    }

}