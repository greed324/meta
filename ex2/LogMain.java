package log;

public class LogMain {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long startUseMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		
		LogFile logf = new LogFile();
		logf.logAnalysis();
		
		logf.logAnalysisWrite();
		
		long end = System.currentTimeMillis();
		long endUseMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		
		long useMemory = (endUseMemory-startUseMemory);
		
		System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
		System.out.println("메모리 사용량 : " + useMemory/1000 + "kbyte");
		
	}

}
