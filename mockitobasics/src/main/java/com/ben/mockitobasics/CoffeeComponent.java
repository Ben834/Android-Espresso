package com.ben.mockitobasics;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = CoffeeModule.class
)
public interface CoffeeComponent {
    CoffeeManager coffeeManager();

}
