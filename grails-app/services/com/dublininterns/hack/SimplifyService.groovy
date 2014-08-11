package com.dublininterns.hack

import org.codehaus.groovy.grails.commons.GrailsApplication

import com.simplify.payments.PaymentsApi
import com.simplify.payments.PaymentsMap
import com.simplify.payments.domain.Payment
import com.simplify.payments.exception.ApiException
import com.simplify.payments.exception.InvalidRequestException

class SimplifyService {

	GrailsApplication grailsApplication

	public String chargeWithCardDetails(Map cardValues) {

		String responseStatus = ''

		PaymentsApi.PUBLIC_KEY = grailsApplication.config.simplify.pub.key
		PaymentsApi.PRIVATE_KEY = grailsApplication.config.simplify.priv.key

		Payment payment

		try {
			payment = Payment.create(new PaymentsMap()
					.set("currency", cardValues.currency)
					.set("card.cvc", "123")
					.set("card.expMonth", 11)
					.set("card.expYear", 14)
					.set("card.number", "5555555555554444")
					.set("amount", 1000) // In cents e.g. $10.00
					.set("description", "prod description"));
			if ("APPROVED".equals(payment.get("paymentStatus"))) {
				log.info('Payment approved');
				responseStatus = 'approved'
			} else {
				responseStatus = 'failed'
			}
		}     catch (ApiException e) {
			System.out.println("Message: " + e.getMessage());
			System.out.println("Reference: " + e.getReference());
			System.out.println("Error code: " + e.getErrorCode());
			if (e instanceof InvalidRequestException) {
				InvalidRequestException e2 = (InvalidRequestException) e;
				if (e2.hasFieldErrors()) {
					for (InvalidRequestException.FieldError fe : e2.getFieldErrors()) {
						System.out.println(fe.getFieldName()
								+ ": '" + fe.getMessage()
								+ "' (" + fe.getErrorCode() + ")");
					}
				}
			}
		}

		return responseStatus
	}
}