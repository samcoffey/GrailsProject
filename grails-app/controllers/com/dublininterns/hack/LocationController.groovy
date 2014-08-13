package com.dublininterns.hack

import org.grails.plugin.geolocation.Coordinates
import org.grails.plugin.geolocation.GeoPosition
import org.grails.plugin.geolocation.GeolocationService
 

class LocationController 
{
 

	public boolean Range()
	 {
	    Coordinates start = new Coordinates()
	        start.setLatitude(31.634227951365595)
	        start.setLongitude(-8.00504207611084)
	
	    Coordinates stop = new Coordinates()
	        stop.setLatitude(53.271932)
	        stop.setLongitude(-6.202107)
			 
	    GeoPosition positionFrom = new GeoPosition()
	        positionFrom.setCoords(start)
	
	    GeoPosition positionTo = new GeoPosition()
	        positionTo.setCoords(stop)
	
	    GeolocationService g = new GeolocationService()
	     boolean test = g.isInRange(positionFrom, positionTo, 10)
		 
		 return test	 
	 }
}
