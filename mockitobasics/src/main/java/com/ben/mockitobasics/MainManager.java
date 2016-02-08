package com.ben.mockitobasics;

import javax.inject.Inject;

public class MainManager {

    private DataManager mDataManager;
    private UserManager mUserManager;

    @Inject
    public MainManager(DataManager dataManager, UserManager userManager) {
        mDataManager = dataManager;
        mUserManager = userManager;
    }

    public void doSomething(){
        String user = mUserManager.getUserName();
        mDataManager.saveUserName(user);
    }

}
