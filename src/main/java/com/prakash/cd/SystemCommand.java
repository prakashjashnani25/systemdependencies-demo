package com.prakash.cd;

public enum SystemCommand {

	DEPEND("DEPEND"),INSTALL("INSTALL"),REMOVE("REMOVE"),LIST("LIST"),END("END");
	
	private String commandName;
	private SystemCommand(String commandName) {
		this.commandName=commandName;
	}
	
}
