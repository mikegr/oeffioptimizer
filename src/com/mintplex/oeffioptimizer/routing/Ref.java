
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Ref implements Serializable { 
   	/**
	 * 
	 */
	private static final long serialVersionUID = -4194274220758483827L;
	private String naPTANID;
   	private String area;
   	private List<Attrs> attrs;
   	private String coords;
   	private String id;
   	private String platform;

 	public String getNaPTANID(){
		return this.naPTANID;
	}
	public void setNaPTANID(String naPTANID){
		this.naPTANID = naPTANID;
	}
 	public String getArea(){
		return this.area;
	}
	public void setArea(String area){
		this.area = area;
	}
 	public List<Attrs> getAttrs(){
		return this.attrs;
	}
	public void setAttrs(List<Attrs> attrs){
		this.attrs = attrs;
	}
 	public String getCoords(){
		return this.coords;
	}
	public void setCoords(String coords){
		this.coords = coords;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getPlatform(){
		return this.platform;
	}
	public void setPlatform(String platform){
		this.platform = platform;
	}
}
