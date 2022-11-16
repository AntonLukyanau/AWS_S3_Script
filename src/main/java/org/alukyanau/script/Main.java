package org.alukyanau.script;

import org.alukyanau.script.s3.CreateBucketScript;
import org.alukyanau.script.s3.DeleteBucketScript;

public class Main {
    public static void main(String[] args) {
        CreateBucketScript createBucketScript = new CreateBucketScript();
        DeleteBucketScript deleteBucketScript = new DeleteBucketScript();
        createBucketScript.execute();
        threadSleep(60);
        deleteBucketScript.execute();
    }

    static void threadSleep(long seconds) {
        try {
            long mls = 1000 * seconds;
            Thread.sleep(mls);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
