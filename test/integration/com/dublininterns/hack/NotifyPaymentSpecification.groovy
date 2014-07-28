package com.dublininterns.hack

import grails.test.spock.IntegrationSpec

class NotifyPaymentSpecification extends IntegrationSpec {

    public void "test notify payment API call"() {

        setup:
        HackController hack_ctrl = new HackController()

        when: "user tries to process payment"

        hack_ctrl.response.reset()
        hack_ctrl.params.clear()
        hack_ctrl.params.customerName = 'Tester'

        hack_ctrl.processPayment()

        assert hack_ctrl.response.contentType == "application/json;charset=UTF-8"
        def response = grails.converters.JSON.parse(hack_ctrl.response.contentAsString)

        then: "validate response which should return an exception"
        response != null

    }
}
