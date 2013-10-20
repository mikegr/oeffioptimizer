
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Stamp implements Serializable {
   	/**
	 * 
	 */
	private static final long serialVersionUID = -8019333193516817306L;
	private String date;
   	private String time;

 	public String getDate(){
		return this.date;
	}
	public void setDate(String date){
		this.date = date;
	}
 	public String getTime(){
		return this.time;
	}
	public void setTime(String time){
		this.time = time;
	}
}
