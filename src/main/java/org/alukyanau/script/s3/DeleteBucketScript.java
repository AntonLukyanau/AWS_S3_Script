package org.alukyanau.script.s3;

import org.alukyanau.client.AWSClients;
import org.alukyanau.config.Config;
import org.alukyanau.script.Script;

public class DeleteBucketScript implements Script {

    public static void main(String[] args) {
        new DeleteBucketScript().execute();
    }

    @Override
    public void execute() {
        AWSClients.S3.deleteBucket(Config.bucketName);
    }

}
