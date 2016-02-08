package com.ben.mockitobasics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {

    @Provides @Singleton
    UserManager provideManager(){
        return new UserManager();
    }

    @Provides @Singleton
    DataManager provideDataManager(){
        return new DataManager();
    }
}
