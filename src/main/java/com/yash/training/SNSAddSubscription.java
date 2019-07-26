package com.yash.training;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SubscribeRequest;

public class SNSAddSubscription {

    public static void main(String[] args) {
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        addSubscription(snsClient, args[0]);
    }

    //ADD SUBSCRIPTION ON SNS TOPIC
    private static void addSubscription(AmazonSNS snsClient, String address) {
        String topicArn = "arn:aws:sns:us-east-1:ACC-NUM:yash-sns-topic";

        System.out.println("ARN for SNS Topic is: " + topicArn);

        snsClient.subscribe(new SubscribeRequest(topicArn, "email", address));

        System.out.println("Subscription request initiated...");
    }
}
