package com.dublininterns.hack

class EventService {


    def logEventLogout(String user) {
        assert user != null, "User cannot be null when creating an event"

        Event event = new Event(eventType: EventEnum.LOGOUT, secuser: user, comment: "Just a small comment");
        event.save();
    }

    def notifyPayment(String user) {
        assert user != null, "User cannot be null when creating an event"

        Event event = new Event(eventType: EventEnum.NOTIFY_PAYMENT, secuser: user, comment: "Card has been processed");
        event.save();
    }

}
