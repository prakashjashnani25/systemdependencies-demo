package com.prakash.cd.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.security.sasl.SaslException;

import com.prakash.cd.SException;
import com.prakash.cd.SystemCommand;
import com.prakash.cd.SystemComponent;
import com.prakash.cd.UserInputOption;
import com.prakash.cd.SException.BusinessExceptionCode;
import com.prakash.cd.action.SystemAction;
import com.prakash.cd.config.SystemComponentConfig;
import com.prakash.cd.data.FileInput;
import com.prakash.cd.input.reader.SystemReader;
import com.prakash.cd.user.CommmandInterperter;
import com.prakash.cd.user.UserCommand;

public class Processor<T> {

	public void process(UserInputOption uiOption, T t) {

		switch (uiOption) {
		case CLI_1:
			cliReader_1.read(t, ci, sa);
		case CLI_2:
			cliReader_2.read(t, ci, sa);
		case FILE_BASED:
			fileReader.read((FileInput) t, ci, sa);
			break;
		default:
			throw new SException(BusinessExceptionCode.INVALID_PROCESS_OPTION);

		}
	}

	private SystemReader<FileInput> fileReader = (fileInput, ci, sa) -> {
		
		try (Scanner fileScannner = new Scanner(
				new java.io.BufferedReader(new java.io.FileReader(new File(fileInput.getFilePath()))))) {
			while (fileScannner.hasNextLine()) {
				String nextLine = fileScannner.nextLine();
				UserCommand uc = ci.interpretate(nextLine);
				sa.doExecute(uc);
			}
		} catch (FileNotFoundException e) {
			throw new SException(SException.BusinessExceptionCode.INVALID_FILE_PATH);
		}
		return "S";
	};

	private SystemReader<T> cliReader_1 = (x, ci, sa) -> {
		return null;
	};

	private SystemReader<T> cliReader_2 = (str, ci, sa) -> {
		return null;
	};

	private SystemAction sa = (userCommmand) -> {
		String output = "";
		SystemComponentConfig sc = SystemComponentConfig.getSC();
		switch (userCommmand.getSystemCommandIn()) {
		case DEPEND:
			System.out.println(userCommmand);
			SystemComponent mainComponent = userCommmand.getSystemComponentsIn().get(0);
			List<SystemComponent> dependentComponents = userCommmand.getSystemComponentsIn().subList(1,
					userCommmand.getSystemComponentsIn().size());
			SystemComponent component = sc.getSystemComponent(mainComponent.getComponentName());
			dependentComponents.stream().forEach(scmp -> {
				SystemComponent sys1 = sc.getSystemComponent(scmp.getComponentName());
				sc.getAvailableComponents().add(sys1);

				component.getDependsOn().add(sys1);
				component.getDependsOn().stream().forEach(cmp -> System.out.println(cmp));
			});
			sc.getAvailableComponents().add(component);
			System.out.println("Available Components " + sc.getAvailableComponents());
			break;
		case END:
			System.out.println("Instlled omponents Left  " + sc.getCompList());
			break;
		case INSTALL:
			System.out.println(userCommmand.toString());
			SystemComponent sys=userCommmand.getSystemComponentsIn().get(0);
			sys=sc.getSystemComponent(sys.getComponentName());
			if(sc.getCompList().contains(sys)) {
				System.out.println(sys+" is already installed");
			}
			else
				output = sc.addComponent(userCommmand.getSystemComponentsIn().get(0));
			break;
		case LIST:
			output = " List " + sc.getCompList() + " aavaailable Compos " + sc.getAvailableComponents();

			break;
		case REMOVE:
			System.out.println(userCommmand);

			Optional<SystemComponent> compExits = sc.getAvailableComponents().stream()
					.filter(sc1 -> sc1.equals(userCommmand.getSystemComponentsIn().get(0))).findFirst();

			if (!compExits.isPresent() || !sc.getCompList().contains(compExits.get()))
				System.out.println(userCommmand.getSystemComponentsIn().get(0) + " is not installed.");
			else
				sc.removeComponent(compExits.get());

			break;
		default:
			break;
		}
		System.out.println(output);
		return output;
	};

	private CommmandInterperter ci = (command) -> {
		UserCommand uc = new UserCommand();
//		assert command == null || "".equalsIgnoreCase(command.trim()) : "Invalid Input";
		String[] userInput = command.trim().split(" ");

		if(!Arrays.asList(SystemCommand.values()).contains(SystemCommand.valueOf(userInput[0]))) {
			System.out.println("Invalid Command Option "+userInput[0]);
			throw new SException(SException.BusinessExceptionCode.INVALID_COMMAND);
		}

		uc.setSystemCommandIn(SystemCommand.valueOf(userInput[0]));
		for (int i = 1; i < userInput.length; i++) {
			if (!"".equals(userInput[i].trim())) {
				SystemComponent sc = new SystemComponent();
				sc.setComponentName(userInput[i].trim().toCharArray());
				uc.getSystemComponentsIn().add(sc);
			}
		}
		return uc;
	};

}
