
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Interchange implements Serializable{ 
   	/**
	 * 
	 */
	private static final long serialVersionUID = 5540323302554185190L;
	private String desc;
   	private String path;
   	private String type;

 	public String getDesc(){
		return this.desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
 	public String getPath(){
		return this.path;
	}
	public void setPath(String path){
		this.path = path;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
