package com.mongodb.webhook.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.webhook.model.Alert;
import com.mongodb.webhook.repositories.AlertRepository;

@RestController
public class ApplicationController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String KEY = "test";
    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    AlertRepository repository;

    @PostMapping("/alert")
    public void alertWebhook(@RequestHeader("X-MMS-Signature") String signature, @RequestBody String body) throws Exception {
        try {
            String result = hmacWithJava(body, KEY);
            logger.info(body);
            logger.info(signature);
            logger.info(result);
            if(!signature.equals(result)){
                throw new Exception("Invalid Signature");
            }
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Alert alert = mapper.readValue(body, Alert.class);
        logger.info(alert.toString());
        repository.save(alert);
    }
    public static String hmacWithJava(String data, String key)
            throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKeySpec);
        return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
    }
}
