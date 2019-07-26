package com.yash.training;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageBatchRequest;
import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.amazonaws.services.sqs.model.SendMessageBatchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SQSSendMessageFIFO {

    public static void main(String[] args) {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        sendMessageInBatch(sqsClient);
    }

    //SEND MESSAGES TO FIFO QUEUE IN BATCH
    private static void sendMessageInBatch(AmazonSQS sqsClient) {
        List<SendMessageBatchRequestEntry> messageEntries = new ArrayList<>();

        messageEntries.add(new SendMessageBatchRequestEntry()
                .withId("ID-1")
                .withMessageBody("Hello from Vivek...")
                .withMessageGroupId(UUID.randomUUID().toString()));        //Required in case of FIFO
        messageEntries.add(new SendMessageBatchRequestEntry()
                .withId("ID-2")
                .withMessageBody("Hello from Vivek Mishra...")
                .withMessageGroupId(UUID.randomUUID().toString()));        //Required in case of FIFO

        String queueUrl = sqsClient.getQueueUrl("yash-queue.fifo").getQueueUrl();

        SendMessageBatchRequest batchRequest =
                new SendMessageBatchRequest(queueUrl, messageEntries);

        SendMessageBatchResult sendMessageBatchResult = sqsClient.sendMessageBatch(batchRequest);

        System.out.println("send result status code: " + sendMessageBatchResult.getSdkHttpMetadata().getHttpStatusCode());
    }
}
