
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Mode implements Serializable {
   	/**
	 * 
	 */
	private static final long serialVersionUID = 3648633576992270980L;
	private String code;
   	private String desc;
   	private String destID;
   	private String destination;
   	private Diva diva;
   	private String name;
   	private String number;
   	private String type;

 	public String getCode(){
		return this.code;
	}
	public void setCode(String code){
		this.code = code;
	}
 	public String getDesc(){
		return this.desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
 	public String getDestID(){
		return this.destID;
	}
	public void setDestID(String destID){
		this.destID = destID;
	}
 	public String getDestination(){
		return this.destination;
	}
	public void setDestination(String destination){
		this.destination = destination;
	}
 	public Diva getDiva(){
		return this.diva;
	}
	public void setDiva(Diva diva){
		this.diva = diva;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getNumber(){
		return this.number;
	}
	public void setNumber(String number){
		this.number = number;
	}
 	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
}
