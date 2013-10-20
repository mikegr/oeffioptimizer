
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Trips implements Serializable {
   	private Trip trip;

 	public Trip getTrip(){
		return this.trip;
	}
	public void setTrip(Trip trip){
		this.trip = trip;
	}
}
