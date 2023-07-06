package com.mongodb.webhook.model;

import java.util.Date;

import lombok.Data;

@Data
public class Alert {
    String id;
    String replicaSetName;
    Date created;
    String groupId;
    String hostnameAndPort;
    String clusterId;
    String humanReadable;
    String alertConfigId;
    String clusterName;
    String eventTypeName;
    Link[] links;
    Date updated;
    String status;
}