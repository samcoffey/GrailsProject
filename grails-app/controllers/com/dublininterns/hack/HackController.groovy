package com.dublininterns.hack

import com.dublininterns.hack.types.NotifyPaymentResponse

import grails.converters.JSON

class HackController {

    HackService hackService

    // Notes
    // 1. params are the request params in the body/URL. Understand HTTP request params
    // 2. This is a controller, understand the pattern MVC.
    // 3. Everything in here is at the HTTP request layer, so you have access to everything i.e cookies, session etc...

    // Only supports GET
    public JSON processPayment() {
        log.info(">>processPayment(), params=$params")

        NotifyPaymentResponse response = hackService.processPayment(params as Map)

        log.debug("processPayment(), customerName=$params.customerName")

        // The response will be JSON format. So the POJO NotifyPaymentResponse will represented in JSON

        log.info("<<processPayment(), response=$response")
        render response as JSON
    }

    // Only supports POST
    public JSON checkout() {
        log.info(">>checkout(), params=$params")

        render ([msg: 'not yet implemented']) as JSON
    }
}
