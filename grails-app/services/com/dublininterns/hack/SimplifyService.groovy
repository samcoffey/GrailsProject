package com.dublininterns.hack

import com.simplify.payments.PaymentsApi
import com.simplify.payments.PaymentsMap
import com.simplify.payments.domain.Payment
import org.codehaus.groovy.grails.commons.GrailsApplication

class SimplifyService {

    GrailsApplication grailsApplication

    public String chargeWithCardDetails(Map cardValues) {

        String responseStatus = ''

        PaymentsApi.PUBLIC_KEY = grailsApplication.config.simplify.private.key
        PaymentsApi.PRIVATE_KEY = grailsApplication.config.public.private.key

        Payment payment = Payment.create(new PaymentsMap()
                .set("currency", cardValues.currency)
                .set("card.cvc", "123")
                .set("card.expMonth", 11)
                .set("card.expYear", 13)
                .set("card.number", "5555555555554444")
                .set("amount", 1000) // In cents e.g. $10.00
                .set("description", "prod description"));

        if ("APPROVED".equals(payment.get("paymentStatus"))) {
            log.info('Payment approved');
            responseStatus = 'approved'
        } else {
            responseStatus = 'failed'
        }

        return responseStatus

    }
}
