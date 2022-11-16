package org.alukyanau.script.s3;

import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.actions.S3Actions;
import com.amazonaws.services.s3.model.*;
import org.alukyanau.client.AWSClients;
import org.alukyanau.config.Config;
import org.alukyanau.script.Script;

import java.io.File;
import java.util.UUID;

public class StaticWebsiteScript implements Script {

    public static void main(String[] args) {
        StaticWebsiteScript staticWebsiteScript = new StaticWebsiteScript();
//        staticWebsiteScript.execute();
        staticWebsiteScript.setWebsitePolicy();
    }

    @Override
    public void execute() {
        uploadFiles();
        enableStaticWebsite();
        makeWebsitePublic();
        setWebsitePolicy();
    }

    public void uploadFiles() {
        AWSClients.S3.putObject(
                Config.bucketName,
                "styles/style.css",
                new File("src/main/resources/web-app/styles/styles.css"));
        AWSClients.S3.putObject(
                Config.bucketName,
                "index.html",
                new File("src/main/resources/web-app/index.html"));
    }

    public void enableStaticWebsite() {
        BucketWebsiteConfiguration websiteConfiguration = new BucketWebsiteConfiguration()
                .withIndexDocumentSuffix("index.html");
//        BucketWebsiteConfiguration websiteConfiguration =
//                AWSClients.S3.getBucketWebsiteConfiguration("anton-lukyanau-task1-static-website");
        websiteConfiguration.setIndexDocumentSuffix("index.html");
        AWSClients.S3.setBucketWebsiteConfiguration(Config.bucketName, websiteConfiguration);
    }

    public void makeWebsitePublic() {
        AWSClients.S3.setPublicAccessBlock(new SetPublicAccessBlockRequest()
                .withBucketName(Config.bucketName)
                .withPublicAccessBlockConfiguration(new PublicAccessBlockConfiguration()
                        .withBlockPublicAcls(false)
                        .withIgnorePublicAcls(false)
                        .withBlockPublicPolicy(false)
                        .withRestrictPublicBuckets(false)));
    }

    public void setWebsitePolicy() {
        Policy policy = new Policy()
                .withId("Policy" + UUID.randomUUID())
                .withStatements(
                        new Statement(Statement.Effect.Allow)
                                .withPrincipals(Principal.AllUsers)
                                .withActions(S3Actions.GetObject)
                                .withResources(new Resource(
                                        "arn:aws:s3:::" + Config.bucketName + "/*")));

        String bucketPolicy = policy.toJson();
        AWSClients.S3.setBucketPolicy(Config.bucketName, bucketPolicy);

    }
}
