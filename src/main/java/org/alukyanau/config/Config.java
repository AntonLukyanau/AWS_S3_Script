package org.alukyanau.config;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;

public class Config {

    public static final String bucketName;
    public static final String profileName;
    public static final ProfileCredentialsProvider credentialsProvider;
    public static final ProfileCredentialsProvider adminCredentialsProvider;

    static {
        bucketName = "s3-task2-anton-lukyanau";
        profileName = "user1";
        credentialsProvider = new ProfileCredentialsProvider(Config.profileName);
        adminCredentialsProvider = new ProfileCredentialsProvider("default");
    }

}
