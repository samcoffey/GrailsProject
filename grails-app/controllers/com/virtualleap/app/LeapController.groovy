package com.virtualleap.app

import com.virtualleap.app.types.NotifyPaymentResponse
import grails.converters.JSON

class LeapController {

    LeapService leapService

    // Notes
    // 1. params are the request params in the body/URL. Understand HTTP request params
    // 2. This is a controller, understand the pattern MVC.
    // 3. Everything in here is at the HTTP request layer, so you have access to everything i.e cookies, session etc...

    public JSON processPayment() {
        log.info(">>processPayment(), params=$params")

        NotifyPaymentResponse response = leapService.processPayment(params as Map)

        // The response will be JSON format. So the POJO NotifyPaymentResponse will represented in JSON

        log.info("<<processPayment(), response=$response")
        render response as JSON

    }
}
