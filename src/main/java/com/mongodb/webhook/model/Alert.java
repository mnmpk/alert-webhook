package com.mongodb.webhook.model;

import java.util.Date;

import lombok.Data;

@Data
public class Alert {
    String id;
    String replicaSetName;
    String metricName;
    Date created;
    String groupId;
    String hostId;
    String hostnameAndPort;
    String clusterId;
    String humanReadable;
    String alertConfigId;
    String clusterName;
    String eventTypeName;
    String typeName;
    Link[] links;
    Date updated;
    String status;
    Value currentValue;
}
