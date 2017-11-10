package com.csye6225.demo.controllers;


import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.csye6225.demo.pojo.Account;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import java.util.Date;

@Controller
public class HomeController {

  private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

  @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public String welcome() {

    JsonObject jsonObject = new JsonObject();

    if (SecurityContextHolder.getContext().getAuthentication() != null
            && SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
      jsonObject.addProperty("message", "you are not logged in!!!");
    } else {
      jsonObject.addProperty("message", "you are logged in. current time is " + new Date().toString());
    }

    return jsonObject.toString();
  }

  @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  public String test() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("message", "authorized for /test");
    return jsonObject.toString();
  }

  @RequestMapping(value = "/testPost", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public String testPost() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("message", "authorized for /testPost");
    return jsonObject.toString();
  }

  @RequestMapping(value = "/forgot-password", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public String reset(@RequestBody Account account) {

    JsonObject jsonObject = new JsonObject();

    if (SecurityContextHolder.getContext().getAuthentication() != null
            && SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {

      String email = account.getEmail();

      String topicArn = "arn:aws:sns:us-east-1:365423270567:password_reset";
      AmazonSNSClient snsClient = new AmazonSNSClient();

      //subscribe to an SNS topic
      SubscribeRequest subRequest = new SubscribeRequest(topicArn, "email", email);
      snsClient.subscribe(subRequest);

      //publish to an SNS topic
      String msg = "My text published to SNS topic with email endpoint";
      PublishRequest publishRequest = new PublishRequest(topicArn, msg);
      PublishResult publishResult = snsClient.publish(publishRequest);

      jsonObject.addProperty("message", "Please confirm your subscription!!!");
    } else {
      jsonObject.addProperty("message", "you are logged in. current time is " + new Date().toString());
    }

    return jsonObject.toString();
  }
}


