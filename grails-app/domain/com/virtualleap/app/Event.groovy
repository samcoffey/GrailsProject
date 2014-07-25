package com.virtualleap.app

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includePackage = false)
class Event implements Serializable {

    EventEnum eventType;

    String comment;
    Date dateCreated

    static mapping = {
        table 'event'
        sort 'dateCreated', order: 'asc'
    }

}

