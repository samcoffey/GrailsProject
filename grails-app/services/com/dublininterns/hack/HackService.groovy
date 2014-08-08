package com.dublininterns.hack

import com.dublininterns.hack.types.NotifyPaymentResponse
import org.codehaus.groovy.grails.commons.GrailsApplication

class HackService {

    GrailsApplication grailsApplication
    CommsService commsService
    EventService eventService
    SimplifyService simplifyService

    /**
     * Return the status of the call from the Simplify API platform
     *
     * @param requestParams
     * @return
     */
    public NotifyPaymentResponse processPayment(Map requestParams) {
        log.info(">>processPayment(), return=$requestParams")

        // Merchant ID used, stored in Config.groovy
        String merchantId = grailsApplication.config.merchant.id

        // [] = List (ArrayList)
        // [:] = Map Map/Set

        // Call out to the commsservice to invoke a request on the simplify platform.
        Map response = commsService.getFromSimplify(
                [merchantId: merchantId, cardNumber: requestParams.cardNumber, cardName: requestParams.cardHolderName],
                grailsApplication.config.simplify.processpayment)

        // Log event to database
        eventService.notifyPayment('Test');

        // Call out to Simplify service
        simplifyService.chargeWithCardDetails([currency: 'USD'])

        // Populate the response retrieved from the platform, will be in JSON format
        NotifyPaymentResponse notifyPaymentResponse = new NotifyPaymentResponse(status: response.json.status)

        log.info("<<processPayment(), return=$notifyPaymentResponse")
        return notifyPaymentResponse
    }
}
