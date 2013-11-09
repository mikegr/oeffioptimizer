
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Frequency implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = 3726388010315309036L;
	private String avDuration;
   	private String avTimeGap;
   	private String hasFrequency;
   	private String maxDuration;
   	private String maxTimeGap;
   	private String minDuration;
   	private String minTimeGap;
   	private List<Modes> modes;
   	private String tripIndex;

 	public String getAvDuration(){
		return this.avDuration;
	}
	public void setAvDuration(String avDuration){
		this.avDuration = avDuration;
	}
 	public String getAvTimeGap(){
		return this.avTimeGap;
	}
	public void setAvTimeGap(String avTimeGap){
		this.avTimeGap = avTimeGap;
	}
 	public String getHasFrequency(){
		return this.hasFrequency;
	}
	public void setHasFrequency(String hasFrequency){
		this.hasFrequency = hasFrequency;
	}
 	public String getMaxDuration(){
		return this.maxDuration;
	}
	public void setMaxDuration(String maxDuration){
		this.maxDuration = maxDuration;
	}
 	public String getMaxTimeGap(){
		return this.maxTimeGap;
	}
	public void setMaxTimeGap(String maxTimeGap){
		this.maxTimeGap = maxTimeGap;
	}
 	public String getMinDuration(){
		return this.minDuration;
	}
	public void setMinDuration(String minDuration){
		this.minDuration = minDuration;
	}
 	public String getMinTimeGap(){
		return this.minTimeGap;
	}
	public void setMinTimeGap(String minTimeGap){
		this.minTimeGap = minTimeGap;
	}
 	public List<Modes> getModes(){
		return this.modes;
	}
	public void setModes(List<Modes> modes){
		this.modes = modes;
	}
 	public String getTripIndex(){
		return this.tripIndex;
	}
	public void setTripIndex(String tripIndex){
		this.tripIndex = tripIndex;
	}
}
