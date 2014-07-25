package com.virtualleap.app

import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, includePackage = false)
enum EventEnum {
    LOGIN, LOGOUT, UPDATEPRODUCTS, STUB_REQUEST, PRODUCT_REQUEST, RAKATUEN_FEED, ZIP_GENERATED
}