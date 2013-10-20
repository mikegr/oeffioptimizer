
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Legs implements Serializable {
   	/**
	 * 
	 */
	private static final long serialVersionUID = -393745210459844948L;
	private Frequency frequency;
   	private Interchange interchange;
   	private Mode mode;
   	private String path;
   	private List<Points> points;
   	private List<StopSeq> stopSeq;

 	public Frequency getFrequency(){
		return this.frequency;
	}
	public void setFrequency(Frequency frequency){
		this.frequency = frequency;
	}
 	public Interchange getInterchange(){
		return this.interchange;
	}
	public void setInterchange(Interchange interchange){
		this.interchange = interchange;
	}
 	public Mode getMode(){
		return this.mode;
	}
	public void setMode(Mode mode){
		this.mode = mode;
	}
 	public String getPath(){
		return this.path;
	}
	public void setPath(String path){
		this.path = path;
	}
 	public List<Points> getPoints(){
		return this.points;
	}
	public void setPoints(List<Points> points){
		this.points = points;
	}
 	public List<StopSeq> getStopSeq(){
		return this.stopSeq;
	}
	public void setStopSeq(List<StopSeq> stopSeq){
		this.stopSeq = stopSeq;
	}
}
