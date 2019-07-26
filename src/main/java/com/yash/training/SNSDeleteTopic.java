package com.yash.training;


import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sns.model.DeleteTopicResult;

public class SNSDeleteTopic {

    public static void main(String[] args) {
        AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .withRegion(Regions.US_EAST_1).build();

        addSubscription(snsClient);
    }

    //DELETE SNS TOPIC
    private static void addSubscription(AmazonSNS snsClient) {
        String topicArn = "arn:aws:sns:us-east-1:ACC-NUM:yash-sns-topic";

        DeleteTopicRequest request = new DeleteTopicRequest().withTopicArn(topicArn);

        DeleteTopicResult deleteTopicResult = snsClient.deleteTopic(request);
        System.out.println("Deleted result: " + deleteTopicResult.getSdkHttpMetadata().getHttpStatusCode());
    }
}
