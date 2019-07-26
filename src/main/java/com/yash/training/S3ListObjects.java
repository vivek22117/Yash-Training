
package com.yash.training;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;

public class S3ListObjects {
    private static final String BUCKET = "yash-training-2019";

    public static void main(String[] args) {
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .build();

        ListObjectsRequest request = new ListObjectsRequest().withBucketName(BUCKET);
        ObjectListing objList = amazonS3.listObjects(request);

        System.out.println("Objects : " + objList.getObjectSummaries());
    }
}
