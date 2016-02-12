package com.ben.mockitobasics;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * A simple demonstration of Dagger Mock
 * <p/>
 * The advantage of this library is that you don't have to create a test {@link CoffeeComponent}
 * and a test {@link dagger.Module} when you want to test with Dagger.
 *
 * @see https://github.com/fabioCollini/DaggerMock
 * @see https://medium.com/@fabioCollini/android-testing-using-dagger-2-mockito-and-a-custom-junit-rule-c8487ed01b56#.fve1v0w0m
 */
public class MockitoDagger {

    @Mock
    BrewingManager mBrewingManager;

    @Mock
    PouringManager mPouringManager;

    CoffeeManager mCoffeeManager;

    /**
     * When DaggerMockRule rule is instantiated, it looks for all @Mock annotated fields in your test
     * class and it replaces them with Mockito mocks if there is a provider method in your module for that class.
     * <p/>
     * The rule dynamically creates a new module that overrides {@link CoffeeModule} which returns
     * the mocks.
     */
    @Rule
    public DaggerMockRule<CoffeeComponent> rule =
            new DaggerMockRule<>(CoffeeComponent.class, new CoffeeModule())
                    .set(new DaggerMockRule.ComponentSetter<CoffeeComponent>() {
                        @Override
                        public void setComponent(CoffeeComponent coffeeComponent) {
                            mCoffeeManager = coffeeComponent.coffeeManager();
                        }
                    });

    @Test
    public void simpleMainManagerTest() {
        when(mBrewingManager.getUserName()).thenReturn("Espresso");
        mCoffeeManager.serveCoffee();
        verify(mPouringManager).pour("Espresso");
    }

}
