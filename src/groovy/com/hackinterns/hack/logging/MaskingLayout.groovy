package com.hackinterns.hack.logging

import groovy.transform.CompileStatic
import org.apache.log4j.Logger
import org.apache.log4j.PatternLayout
import org.apache.log4j.spi.LoggingEvent

@CompileStatic
class MaskingLayout extends PatternLayout
{

    public String format( LoggingEvent event ) {
        if ( event.getMessage() instanceof String ) {
            String message = event.getRenderedMessage()

            try{

                message = Masker.maskSmartly( message )
            } catch ( e ) {
                message = "Masking filter failed to mask output correctly: ${e.getMessage()}"
            }

            Throwable throwable = event.getThrowableInformation() != null ? event.getThrowableInformation().getThrowable() : null
            LoggingEvent maskedEvent = new LoggingEvent( event.fqnOfCategoryClass, Logger.getLogger( event.getLoggerName() ), event.timeStamp, event.getLevel(), message, throwable )
            return super.format( maskedEvent )
        }
        return super.format( event )
    }
}