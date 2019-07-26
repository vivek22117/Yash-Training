package com.yash.training;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;

public class SQSServiceClientStandard {

    public static void main(String[] args) {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        cereateSQSStandardQueue(sqsClient);
    }

    //CREATE STANDARD QUEUE
    private static void cereateSQSStandardQueue(AmazonSQS sqsClient) {
        CreateQueueRequest queueRequest =
                new CreateQueueRequest("yash-queue")
                        .addAttributesEntry("DelaySeconds", "60")                  //DELAY for message to reach..
                        .addAttributesEntry("MessageRetentionPeriod", "86400");   //seconds

        String queueUrl = sqsClient.createQueue(queueRequest).getQueueUrl();

        System.out.print("Queue Url for standard queue..." + queueUrl);
    }
}
