package com.mongodb.webhook.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.webhook.model.Alert;
import com.mongodb.webhook.repositories.AlertRepository;

@RestController
public class ApplicationController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AlertRepository repository;

    @PostMapping("/alert")
    public void alertWebhook(@RequestBody Alert alert) {
        logger.info(alert.toString());
        repository.save(alert);
    }
}
