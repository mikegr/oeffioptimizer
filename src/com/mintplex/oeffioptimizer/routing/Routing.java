
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Routing implements Serializable {
   	/**
	 * 
	 */
	private static final long serialVersionUID = -5827198459041953831L;
	private List<Parameters> parameters;
   	private List<Trips> trips;

 	public List<Parameters> getParameters(){
		return this.parameters;
	}
	public void setParameters(List<Parameters> parameters){
		this.parameters = parameters;
	}
 	public List<Trips> getTrips(){
		return this.trips;
	}
	public void setTrips(List<Trips> trips){
		this.trips = trips;
	}
}
