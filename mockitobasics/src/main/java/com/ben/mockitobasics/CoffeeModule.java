package com.ben.mockitobasics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CoffeeModule {

    @Provides @Singleton
    protected BrewingManager provideManager(){
        return new BrewingManager();
    }

    @Provides @Singleton
    protected PouringManager provideDataManager(){
        return new PouringManager();
    }
}
