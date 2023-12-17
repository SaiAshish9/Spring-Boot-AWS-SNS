package com.sai.sns;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AWSSNSController {

    private final AmazonSNSClient awsSnsClient;

    @Value("${AWS_SNS_TOPIC_ID}")
    private String AWS_SNS_TOPIC_ID;

    @PostMapping("/subscribe/{email}")
    public String subscribe(@PathVariable String email) {
        SubscribeRequest subscribeRequest = new SubscribeRequest(AWS_SNS_TOPIC_ID, "email", email);
        awsSnsClient.subscribe(subscribeRequest);
        return "Subscribed!";
    }

    @PostMapping("/publish")
    public String publish() {
        PublishRequest publishRequest = new PublishRequest(AWS_SNS_TOPIC_ID, "Hi Sai", "Notification");
        awsSnsClient.publish(publishRequest);
        return "Notification Sent!";
    }

}
