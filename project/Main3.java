package project;

public class Main3{

	public static void main(String[] args) {
		Project a = new Project();
		a.setOutputFilePath("C:\\Dev\\workspace\\txt\\out2.txt");
		try {
			a.consoleToFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
