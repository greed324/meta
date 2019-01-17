package project;

public class Main1 {

	public static void main(String[] args){
		Project a = new Project();
		try {
			a.fileToConsole();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}