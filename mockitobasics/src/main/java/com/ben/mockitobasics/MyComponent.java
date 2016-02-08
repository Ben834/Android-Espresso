package com.ben.mockitobasics;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = MyModule.class
)
public interface MyComponent {
    MainManager myManager();
}
