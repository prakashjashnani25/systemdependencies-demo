package com.prakash.cd.MAIN;

import com.prakash.cd.UserInputOption;
import com.prakash.cd.data.FileInput;
import com.prakash.cd.service.Processor;

public class Application {

	public static void main(String[] args) {
		Processor<FileInput> processor=new Processor<>();
		if(args.length==0)
		{
			System.out.println("USING DEFAULT  SYSTEM OPTIONS - TAKING INPUT USER INPUT ONE AT A TIME");
			
			
			FileInput fi=new FileInput();
			fi.setFilePath("./src/main/resources/input.txt");
			processor.process(UserInputOption.FILE_BASED,fi );
		}else if(args.length>0) {
			switch(args[0]) {
			case "1":
				if(args.length>1) {
					System.out.println("Invalid Program input,Please Passed Input File Path Possible Values are  - 1 <file_path>");
					break;
				}
				FileInput fi=new FileInput();
				fi.setFilePath(args[1].trim());
				processor.process(UserInputOption.FILE_BASED, fi);
			
			case "2":
				
				System.out.println("This UI option Not Supported, Please USE FILE");
				break;
			default:
				System.out.println("Invalid Program input , Possible Values are  - 1 <file_path>");
			}
				
		}
	}

}
