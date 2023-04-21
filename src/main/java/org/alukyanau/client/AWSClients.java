package org.alukyanau.client;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.alukyanau.config.Config;

public class AWSClients {

    public static final AmazonS3 S3;
    public static final AmazonEC2 EC2;
    public static final AmazonIdentityManagement IAM;

    static {
        AmazonS3ClientBuilder s3ClientBuilder = AmazonS3Client.builder();
        s3ClientBuilder.setCredentials(Config.credentialsProvider);
        S3 = s3ClientBuilder.build();

        AmazonEC2ClientBuilder ec2ClientBuilder = AmazonEC2ClientBuilder.standard();
        ec2ClientBuilder.setCredentials(Config.credentialsProvider);
        EC2 = ec2ClientBuilder.build();

        AmazonIdentityManagementClientBuilder identityBuilder = AmazonIdentityManagementClientBuilder.standard()
                .withCredentials(Config.adminCredentialsProvider);
        IAM = identityBuilder.build();
    }

}
