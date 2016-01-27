package com.ben.mockitobasics.model;

/**
 * A simple model representing a user
 */
public class User {

    private String mUuid;

    private String mUsername;

    private String mProfilePicture;

    public User(String uuid, String username, String profilePicture) {
        mUuid = uuid;
        mUsername = username;
        mProfilePicture = profilePicture;
    }

    public String getUuid() {
        return mUuid;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getProfilePicture() {
        return mProfilePicture;
    }

    public void setUuid(String uuid) {
        mUuid = uuid;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public void setProfilePicture(String profilePicture) {
        mProfilePicture = profilePicture;
    }

    public void setUser(User user){
        mUuid = user.getUuid();
        mUsername = user.getUsername();
        mProfilePicture = user.getProfilePicture();
    }

}
