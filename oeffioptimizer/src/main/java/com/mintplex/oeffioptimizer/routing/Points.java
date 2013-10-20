
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Points implements Serializable {
   	/**
	 * 
	 */
	private static final long serialVersionUID = -7374170727321077187L;
	private DateTime dateTime;
   	private String desc;
   	private List<Links> links;
   	private String name;
   	private String nameWithPlace;
   	private String omc;
   	private String place;
   	private String placeID;
   	private Ref ref;
   	private Stamp stamp;
   	private String usage;

 	public DateTime getDateTime(){
		return this.dateTime;
	}
	public void setDateTime(DateTime dateTime){
		this.dateTime = dateTime;
	}
 	public String getDesc(){
		return this.desc;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
 	public List<Links> getLinks(){
		return this.links;
	}
	public void setLinks(List<Links> links){
		this.links = links;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getNameWithPlace(){
		return this.nameWithPlace;
	}
	public void setNameWithPlace(String nameWithPlace){
		this.nameWithPlace = nameWithPlace;
	}
 	public String getOmc(){
		return this.omc;
	}
	public void setOmc(String omc){
		this.omc = omc;
	}
 	public String getPlace(){
		return this.place;
	}
	public void setPlace(String place){
		this.place = place;
	}
 	public String getPlaceID(){
		return this.placeID;
	}
	public void setPlaceID(String placeID){
		this.placeID = placeID;
	}
 	public Ref getRef(){
		return this.ref;
	}
	public void setRef(Ref ref){
		this.ref = ref;
	}
 	public Stamp getStamp(){
		return this.stamp;
	}
	public void setStamp(Stamp stamp){
		this.stamp = stamp;
	}
 	public String getUsage(){
		return this.usage;
	}
	public void setUsage(String usage){
		this.usage = usage;
	}
}
