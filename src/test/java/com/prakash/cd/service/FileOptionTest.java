package com.prakash.cd.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prakash.cd.SException;
import com.prakash.cd.UserInputOption;
import com.prakash.cd.data.FileInput;

/**
 * 
 * Validate the File Option Provided By User
 *
 */
public class FileOptionTest {

	Processor<FileInput> testProcessor;
	String filePath;
	@Before
	public void setup() {
		testProcessor=new Processor<>();
		filePath="./src/test1/resources/input.txt";
	}
	@Test(expected=SException.class)
	public void testInvalidFilePath() {
		FileInput fi=new FileInput();
		fi.setFilePath(filePath);
		testProcessor.process(UserInputOption.FILE_BASED, fi);
	}
	
	
	@After
	public void tearDown() {
		testProcessor=null;
	}
}
