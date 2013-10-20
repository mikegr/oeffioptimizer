
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;

public class DateTime implements Serializable{
   	/**
	 * 
	 */
	private static final long serialVersionUID = -2368699052822013744L;
	
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
