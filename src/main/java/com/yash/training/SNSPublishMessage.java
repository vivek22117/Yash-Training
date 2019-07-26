package com.yash.training;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import java.util.Date;

public class SNSPublishMessage {

    public static void main(String[] args) {
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        publishToTopic(snsClient);
    }

    //PUBLISH MESSAGE TO TOPIC
    private static void publishToTopic(AmazonSNS snsClient) {
        String topicArn = "arn:aws:sns:us-east-1:ACC-NUM:yash-sns-topic";

        PublishRequest publishReq = new PublishRequest()
                .withTopicArn(topicArn)
                .withMessage("Notification sent at by Vivek " + new Date());
        PublishResult publish = snsClient.publish(publishReq);

        System.out.println("Publish result: " + publish.getMessageId());
    }
}
