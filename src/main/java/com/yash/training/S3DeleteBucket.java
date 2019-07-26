package com.yash.training;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3DeleteBucket {

    private static final String BUCKET = "yash-training-2019";

    public static void main(String[] args) {
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .build();

        amazonS3.deleteBucket(BUCKET);
        System.out.println("Bucket: " + BUCKET + " deleted successfully.");
    }
}
