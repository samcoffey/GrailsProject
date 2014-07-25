package interns

import com.virtualleap.webapp.types.NotifyPaymentResponse
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.GrailsApplication

@Transactional
class LeapService {

    GrailsApplication grailsApplication
    CommsService commsService

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


        // Populate the response retrieved from the platform, will be in JSON format
        NotifyPaymentResponse notifyPaymentResponse = new NotifyPaymentResponse(status: response.json.status)

        log.info("<<processPayment(), return=$notifyPaymentResponse")
        return notifyPaymentResponse
    }
}
