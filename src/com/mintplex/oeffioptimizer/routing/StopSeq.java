
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class StopSeq implements Serializable {
   	/**
	 * 
	 */
	private static final long serialVersionUID = 5110896901943574295L;
	private String name;
   	private String nameWO;
   	private String nameWithPlace;
   	private String omc;
   	private String place;
   	private String placeID;
   	private String platformName;
   	private Ref ref;

 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getNameWO(){
		return this.nameWO;
	}
	public void setNameWO(String nameWO){
		this.nameWO = nameWO;
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
 	public String getPlatformName(){
		return this.platformName;
	}
	public void setPlatformName(String platformName){
		this.platformName = platformName;
	}
 	public Ref getRef(){
		return this.ref;
	}
	public void setRef(Ref ref){
		this.ref = ref;
	}
}
