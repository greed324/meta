package project;

import java.io.FileReader;
import java.io.IOException;

public class Reader {

	public String[] count(FileReader fileReader) throws IOException {
		String[] strArr = new String[2];
		int read = 0, count =0;
		String readString = new String();
		
		while((read = fileReader.read()) != -1) {
			count ++;
			readString +=(char)read;
		}
		
		strArr[0] = readString;
		strArr[1] = String.valueOf(count);
		
		return strArr;
	}
	
	public int count(String str) {
		
		return str.length();
	}

}
