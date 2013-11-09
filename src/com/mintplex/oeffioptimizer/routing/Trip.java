
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Trip implements Serializable {
   	/**
	 * 
	 */
	private static final long serialVersionUID = 5018582929383165591L;
	private List<Attrs> attrs;
   	private String desc;
   	private String duration;
   	private String interchange;
   	private List<Legs> legs;

 	public List<Attrs> getAttrs(){
		return this.attrs;
	}
	public void setAttrs(List<Attrs> attrs){
		this.attrs = attrs;
	}
 	public String getDesc(){
		return this.desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
 	public String getDuration(){
		return this.duration;
	}
	public void setDuration(String duration){
		this.duration = duration;
	}
 	public String getInterchange(){
		return this.interchange;
	}
	public void setInterchange(String interchange){
		this.interchange = interchange;
	}
 	public List<Legs> getLegs(){
		return this.legs;
	}
	public void setLegs(List<Legs> legs){
		this.legs = legs;
	}
}
