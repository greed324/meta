package VO;

import project.Input;
import project.Project;

public class MainVO implements Runnable{
	Project a;
	private String inputType;
	private String outputType;

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	@Override
	public void run() {
		try {
			a = new Project();
			switch(inputType) {
			case "console" :
				switch(outputType) {
				case "console" :
					a.consoleToConsole();
					break;
				
				case "file" :
					a.consoleToFile();
					break;
				}
				break;
			case "file" :
				switch(outputType) {
				case "console" :
					a.fileToConsole();
					break;
				
				case "file" :
					a.fileToFile();
					break;
				}
				break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*//input
	public void sss() {
		input.input();
	}*/
}
