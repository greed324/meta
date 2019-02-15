package xml;

public class XmlMain {

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();
		
		XmlXpath xml = new XmlXpath();
		xml.getXmlData();
		
		long end = System.currentTimeMillis();
		System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");

	}
}
