package com.virtualleap.app

import grails.transaction.Transactional

class EventService {

    def grailsApplication

    def logEventLogin(String user) {
        assert user != null, "User cannot be null when creating an event"

        Event event = new Event(eventType: EventEnum.LOGIN,  secuser: user, comment: "User logging in");
        event.save();
    }


    def logEventLogout(String user) {
        assert user != null, "User cannot be null when creating an event"

        Event event = new Event(eventType: EventEnum.LOGOUT, secuser: user, comment: "User logging out");
        event.save();
    }

    def notifyPayment(String user) {
        assert user != null, "User cannot be null when creating an event"

        Event event = new Event(eventType: EventEnum.NOTIFY_PAYMENT, secuser: user, comment: "Card has been processed");
        event.save();
    }

}
