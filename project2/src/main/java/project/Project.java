package project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Project extends Reader implements ProjectInterface {

	private String inputFilePath;
	private String outputFilePath;
	//"C:\\Dev\\workspace\\txt\\test.txt"
	
	public Project() {
		inputFilePath = "C:\\Dev\\workspace\\txt\\test.txt";
		outputFilePath = "C:\\Dev\\workspace\\txt\\out.txt";
	}
	
	public String getInputFilePath() {
		return inputFilePath;
	}

	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}

	public String getOutputFilePath() {
		return outputFilePath;
	}

	public void setOutputFilePath(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	@Override
	public String fileToConsole() throws Exception {
		
		File file = new File(inputFilePath);
		try {
			FileReader fileReader = new FileReader(file);
			int count =0;
			String readString = new String();
			
			String[] strArr = new String[2];
			strArr = count(fileReader);
			
			readString = strArr[0];
			count = Integer.valueOf(strArr[1]);
			
			System.out.println("파일의 문자열 : "+readString);
			System.out.println("문자열 길이 : "+count);
		} catch (FileNotFoundException e) {
			System.out.println("잘못됨");
		}
		return null;
	}

	@Override
	public String fileToFile() throws Exception {
		
		File inputFile = new File(inputFilePath);
		FileReader fileReader = new FileReader(inputFile);
		FileWriter fw = new FileWriter(outputFilePath);
		int read = 0, count =0;
		String readString = new String();
		
		try {
			
			/*int count =0;
			String readString = new String();*/
			
			String[] strArr = new String[2];
			strArr = count(fileReader);
			
			readString = strArr[0];
			count = Integer.valueOf(strArr[1]);
			/*while((read = fileReader.read()) != -1) {
				count ++;
				readString +=(char)read;
			}*/
		} catch (FileNotFoundException e) {
			System.out.println("잘못됨");
		}finally {
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("수정된 문자열 : "+readString);
				bw.newLine();
				bw.write("문자열의 길이 : "+count);
				bw.close();
		}
		return null;
	}

	@Override
	public String consoleToFile() throws Exception{
		
		Scanner input = new Scanner(System.in);
		System.out.println("문자를 입력해주세요 : ");
		String str = input.nextLine();
		FileWriter fw = new FileWriter(outputFilePath);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("저장된 문자열 : "+str);
		bw.newLine();
		bw.write("문자열의 길이 : "+str.length());
		bw.close();
		
		return null;
	}

	@Override
	public String consoleToConsole() {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("문자를 입력해주세요 : ");
			String str = input.nextLine();
			System.out.println("입력된 문자열 : "+str);
			System.out.println("문자열 길이 : "+str.length());
		} catch (Exception e) {
			System.out.println("잘못됨");
		}
		return null;
	}

}
