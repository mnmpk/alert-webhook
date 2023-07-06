package com.mongodb.webhook.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.webhook.model.Alert;

public interface AlertRepository extends MongoRepository<Alert, String> {

}