package com.prakash.cd.user;

import java.util.ArrayList;

import com.prakash.cd.SystemCommand;
import com.prakash.cd.SystemComponent;

public class UserCommand {

	public ArrayList<SystemComponent> getSystemComponentsIn() {
		if(this.systemComponentsIn==null)
			this.systemComponentsIn=new ArrayList<>();
		return systemComponentsIn;
	}

	@Override
	public String toString() {
		return ""+systemCommandIn+" "+systemComponentsIn.toString();
	}

	public void setSystemComponentsIn(ArrayList<SystemComponent> systemComponentsIn) {
		this.systemComponentsIn = systemComponentsIn;
	}

	public SystemCommand getSystemCommandIn() {
		return systemCommandIn;
	}

	public void setSystemCommandIn(SystemCommand systemCommandIn) {
		this.systemCommandIn = systemCommandIn;
	}

	public UserCommand() {
		super();
		this.systemCommandIn=null;
		this.systemComponentsIn=null;
	}

	private ArrayList<SystemComponent> systemComponentsIn;
	
	private SystemCommand systemCommandIn;
	
	public UserCommand(SystemCommand command) {
		this.systemCommandIn=command;
	}
	
	public UserCommand(SystemCommand command,ArrayList<SystemComponent> composIn) {
		this(command);
		this.systemComponentsIn=composIn;
	}
	
}
