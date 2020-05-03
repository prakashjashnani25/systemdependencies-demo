package com.prakash.cd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.prakash.cd.service.FileOptionTest;
import com.prakash.cd.service.ProcessorTest;

@RunWith(Suite.class)

@SuiteClasses({
	ProcessorTest.class,FileOptionTest.class
})

public class TestSuite {

}
