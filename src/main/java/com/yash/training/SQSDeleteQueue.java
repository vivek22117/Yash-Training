package com.yash.training;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteQueueResult;

public class SQSDeleteQueue {
    public static void main(String[] args) {
        AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        deleteSQSQueue(sqsClient);
    }

    //DELETE SQS QUEUE
    private static void deleteSQSQueue(AmazonSQS sqsClient) {
        String queueUrl = sqsClient.getQueueUrl("yash-queue").getQueueUrl();
        String fifoQueueUrl = sqsClient.getQueueUrl("yash-queue.fifo").getQueueUrl();

        DeleteQueueResult deleteQueueResult = sqsClient.deleteQueue(queueUrl);
        System.out.println("Standard queue deleted with status code: " + deleteQueueResult.getSdkHttpMetadata().getHttpStatusCode());

        DeleteQueueResult deleteQueueResult1 = sqsClient.deleteQueue(fifoQueueUrl);
        System.out.println("FIFO queue deleted with status code: " + deleteQueueResult1.getSdkHttpMetadata().getHttpStatusCode());
    }
}
