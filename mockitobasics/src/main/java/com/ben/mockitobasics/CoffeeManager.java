package com.ben.mockitobasics;

import javax.inject.Inject;

public class CoffeeManager {

    private PouringManager mPouringManager;
    private BrewingManager mBrewingManager;

    @Inject
    public CoffeeManager(PouringManager pouringManager, BrewingManager brewingManager) {
        mPouringManager = pouringManager;
        mBrewingManager = brewingManager;
    }

    public void doSomething(){
        String blackCoffee = mBrewingManager.getUserName();
        mPouringManager.pour(blackCoffee);
    }

}
