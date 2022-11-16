package org.alukyanau.client;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.alukyanau.config.Config;

public class AWSClients {

    public static final AmazonS3 S3;

    static {
        AmazonS3ClientBuilder s3ClientBuilder = AmazonS3Client.builder();
        s3ClientBuilder.setCredentials(Config.credentialsProvider);
        S3 = s3ClientBuilder.build();
    }

}
