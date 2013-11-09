
package com.mintplex.oeffioptimizer.routing;

import java.io.Serializable;
import java.util.List;

public class Diva implements Serializable {
   	/**
	 * 
	 */
	private static final long serialVersionUID = 3974713313809281037L;
	
	
	private String branch;
   	private String dir;
   	private String line;
   	private String network;
   	private String opCode;
   	private String operator;
   	private String project;
   	private String stateless;
   	private String supplement;

 	public String getBranch(){
		return this.branch;
	}
	public void setBranch(String branch){
		this.branch = branch;
	}
 	public String getDir(){
		return this.dir;
	}
	public void setDir(String dir){
		this.dir = dir;
	}
 	public String getLine(){
		return this.line;
	}
	public void setLine(String line){
		this.line = line;
	}
 	public String getNetwork(){
		return this.network;
	}
	public void setNetwork(String network){
		this.network = network;
	}
 	public String getOpCode(){
		return this.opCode;
	}
	public void setOpCode(String opCode){
		this.opCode = opCode;
	}
 	public String getOperator(){
		return this.operator;
	}
	public void setOperator(String operator){
		this.operator = operator;
	}
 	public String getProject(){
		return this.project;
	}
	public void setProject(String project){
		this.project = project;
	}
 	public String getStateless(){
		return this.stateless;
	}
	public void setStateless(String stateless){
		this.stateless = stateless;
	}
 	public String getSupplement(){
		return this.supplement;
	}
	public void setSupplement(String supplement){
		this.supplement = supplement;
	}
}
