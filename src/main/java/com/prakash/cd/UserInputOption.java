package com.prakash.cd;

public enum UserInputOption {

	FILE_BASED("FILE_BASED"," All Specifications In File"),CLI_1("CLI_1","END Will Terminate USer Input Session"),
	CLI_2("CLI_2","Default Option ,Take user input one at a time END terminates the session");
	private String optionName;
	private String optionDesc;
	
	private UserInputOption(String name,String desc) {
		this.optionDesc=desc;
		this.optionName=name;
	}
	
	
}
