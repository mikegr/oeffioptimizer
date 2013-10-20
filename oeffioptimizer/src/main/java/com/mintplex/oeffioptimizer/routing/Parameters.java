
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Parameters implements Serializable {
   	/**
	 * 
	 */
	private static final long serialVersionUID = 4618172309946157863L;
	private String name;
   	private String value;

 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getValue(){
		return this.value;
	}
	public void setValue(String value){
		this.value = value;
	}
}
