package com.dublininterns.hack

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includePackage = false)
enum EventEnum {
    LOGIN, LOGOUT, NOTIFY_PAYMENT
}