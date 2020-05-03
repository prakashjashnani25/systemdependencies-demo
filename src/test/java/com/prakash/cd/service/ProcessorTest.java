package com.prakash.cd.service;

import org.junit.Before;
import org.junit.Test;

import com.prakash.cd.SException;
import com.prakash.cd.UserInputOption;
import com.prakash.cd.data.FileInput;


public class ProcessorTest {
	
	Processor<FileInput> testProcessor;
	FileInput fi;
	String filePath;
	@Before
	public void setup() {
		testProcessor=new Processor<>();
		fi=new FileInput();
		filePath="./src/test/resources/";
	}
	
	public void testInValidUIOption() {
//		testProcessor.process(3, new DummyInput());
		//Practically not possible to invoke with invalid ui option because of Enum
	}

	@Test(expected=IllegalArgumentException.class)
	public void testInvalidCommand() {
		
		fi.setFilePath(filePath+"invalidCommand.txt");
		testProcessor.process(UserInputOption.FILE_BASED, fi);

		
	}
	
	@Test(expected=Exception.class)
	public void testSystemComponentGreaterThan10() {
		fi.setFilePath(filePath+"systemComponentSizeGreaterThan10.txt");
		testProcessor.process(UserInputOption.FILE_BASED,fi);
	}
	@Test
	public void testCorrectFile() {
		fi.setFilePath(filePath+"input.txt");
		testProcessor.process(UserInputOption.FILE_BASED, fi);
	}
}
