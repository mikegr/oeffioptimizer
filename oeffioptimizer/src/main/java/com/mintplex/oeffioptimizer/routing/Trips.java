
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
	
	@Override
	public String toString() {
		List<Legs> legs = trip.getLegs();
		StringBuilder result = new StringBuilder();
		for(Legs l:legs) {
			if (result.length() > 0) {
				result.append("\n");
			}
			String line = l.getMode().getNumber();
			
			StringBuilder legString = new StringBuilder();
			legString.append(line);
			legString.append(" (");
			List<Points> points = l.getPoints();
			for(int i = 0; i < points.size(); i++) {
				Points p = points.get(i);
				if (i != 0) {
					legString.append(" - ");
				}
				legString.append(p.getName());
				legString.append(" ");
				legString.append(p.getDateTime().getTime());
			}
			legString.append(")");
			
			
			
			result.append(legString);
		}
		return result.toString();
	}
}
