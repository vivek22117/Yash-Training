package com.yash.training;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

public class SQSReadFromQueue {

    public static void main(String[] args) {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        readMessageFromQueue(sqsClient);
    }

    //READ DATA FROM FIFO QUEUE
    private static void readMessageFromQueue(AmazonSQS sqsClient) {
        String queueUrl = sqsClient.getQueueUrl("yash-queue.fifo").getQueueUrl();

        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl)
                .withWaitTimeSeconds(10)          //used to allow our read request wait for some more messages...
                .withMaxNumberOfMessages(10);

        List<Message> sqsMessages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();

        //DO PROCESSING
        sqsMessages.forEach(msg -> {
            System.out.println(msg.getAttributes());
            System.out.println(msg.getBody());
        });

        //DELETE MSG FROM QUEUE after receiving...
        sqsMessages.forEach(msg -> {
            sqsClient.deleteMessage(new DeleteMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withReceiptHandle(msg.getReceiptHandle()));
        });
    }
}
