package project;

import java.util.Scanner;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import VO.MainVO;

public class Main0117 {

	public static void main(String[] args) {
		Project a = new Project();
		GenericApplicationContext ac =
				new GenericXmlApplicationContext("VO/MainXml.xml");
		MainVO MainVO = new MainVO();
		//MainVO = ac.getBean("consoleToConsole", MainVO.class);
		//MainVO.sss();
		
		System.out.println("입력 형태를 정해주세요(console,file) : ");
		Scanner scan = new Scanner(System.in);
		String inputType = scan.nextLine();
		
		System.out.println("출력 형태를 정해주세요(console,file) : ");
		String outputType = scan.nextLine();
		
		switch(inputType) {
		case "console" :
			switch(outputType) {
			case "console" :
				MainVO = ac.getBean("consoleToConsole", MainVO.class);
				break;
			
			case "file" :
				MainVO = ac.getBean("consoleToFile", MainVO.class);
				break;
			}
			break;
		case "file" :
			switch(outputType) {
			case "console" :
				MainVO = ac.getBean("fileToConsole", MainVO.class);
				break;
			
			case "file" :
				MainVO = ac.getBean("fileToFile", MainVO.class);
				break;
			}
			break;
		}
		
		MainVO.run();
		scan.close();
	}
}
