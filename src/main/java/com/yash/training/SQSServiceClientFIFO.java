package com.yash.training;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;

import java.util.HashMap;
import java.util.Map;

public class SQSServiceClientFIFO {

    public static void main(String[] args) {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        createSQSFIFOQueue(sqsClient);
    }

    //CREATE FIFO QUEUE
    private static void createSQSFIFOQueue(AmazonSQS sqsClient) {
        Map<String, String> queueAttributes = new HashMap<>();
        queueAttributes.put("FifoQueue", "true");                        //REQUIRED to provide this attribute
        queueAttributes.put("ContentBasedDeduplication", "true");

        CreateQueueRequest createFifoQueueRequest = new CreateQueueRequest(
                "yash-queue.fifo").withAttributes(queueAttributes);       //REQUIRED .fifo
        String queueUrl = sqsClient.createQueue(createFifoQueueRequest).getQueueUrl();

        System.out.println("Queue Url for FIFO queue..." + queueUrl);
    }

}
