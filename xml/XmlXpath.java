package xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.dsig.Transform;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlXpath {

	// T_BASEFILE_TB 파일 가져와서 FILE_ID 값 저장
	public void getXmlData() {
		Document doc = null;
		List<String> fileIdList = new ArrayList<String>();
		try {
			InputSource is = new InputSource(new FileReader("C:\\Dev\\workspace\\1.XML 파일 분석\\T_BASEFILE_TB.xml"));
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder(); // 빌더 생성
			doc = dBuilder.parse(is); // 생성된 빌더를 통해서 xml문서를 Document객체로 파싱해서 가져온다

			// xpath 생성
			XPath xpath = XPathFactory.newInstance().newXPath();
			doc.normalize(); // 문서 정규화 - 비어 있는 텍스트 제거 
			NodeList rowList = (NodeList) xpath.evaluate("TABLE/ROWS/ROW", doc, XPathConstants.NODESET);

			for (int i = 0; i < rowList.getLength(); i++) {
				NodeList child = rowList.item(i).getChildNodes();
				for (int j = 0; j < child.getLength(); j++) {
					Node node = child.item(j);
					if (node.getNodeName() == "FILE_ID") {
						//System.out.println("FILE_ID : " + node.getTextContent());
						fileIdList.add(node.getTextContent());
						break;
					}
				}
			}
			xmlInOut(fileIdList);
		} catch (Exception e) {

		}
		
	}

	// FILE_ID
	public void xmlInOut(List<String> fileIdList) {
		try {
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder(); // 빌더 생성
			Document doc,docp = null;

			XPath xpath = XPathFactory.newInstance().newXPath();
			for (int i = 0; i < fileIdList.size(); i++) {
				fileIdList.get(i);
				InputSource is = new InputSource(new FileReader("C:\\Dev\\workspace\\1.XML 파일 분석\\F_" + fileIdList.get(i) + "_TB.xml"));
				doc = dBuilder.parse(is);
				InputSource ps = new InputSource(new FileReader("C:\\Dev\\workspace\\1.XML 파일 분석\\P_" + fileIdList.get(i) + "_TB.xml"));
				docp = dBuilder.parse(ps);

				NodeList rowList = (NodeList) xpath.evaluate("TABLE/ROWS/ROW[SIMILAR_RATE div 100 > 15]", doc, XPathConstants.NODESET);
				//System.out.println("rowList : " + rowList.getLength());
				for (int j=0; j < rowList.getLength(); j++) {

					Node fIdNode = (Node) xpath.evaluate("//P_ID", rowList.item(j), XPathConstants.NODE);
					
					if(fIdNode != null) {
						NodeList pIdNode = (NodeList) xpath.evaluate("TABLE/ROWS/ROW/P_ID", docp, XPathConstants.NODESET);
						//System.out.println("p P_ID : " + fIdNode.getTextContent());
	
						for(int k=0; k<pIdNode.getLength(); k++) {
							// F_P_ID_ 와 P_PID 같을 경우
							//System.out.println("p_pid f_pid : " + pIdNode.item(k).getTextContent());
							if(fIdNode.getTextContent().compareTo(pIdNode.item(k).getTextContent()) == 0) {
								//System.out.println("p_pid  : " + pIdNode.item(k).getTextContent() + "f_pid : " + fIdNode.getTextContent());
								Node licensepIdNode = (Node) xpath.evaluate("LICENSE_ID", pIdNode.item(k).getParentNode(), XPathConstants.NODE);							
								Node commentNode = (Node) xpath.evaluate("COMMENT", rowList.item(j), XPathConstants.NODE);
								commentNode.setTextContent(licensepIdNode.getTextContent());
								break;
							}
						}
					}
				}
				// Xml 파일로 쓰기
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new FileOutputStream(new File("C:\\Dev\\workspace\\XMLSort\\T_"+ fileIdList.get(i) +"_TB.xml")));
				transformer.transform(source, result);
				
			}

		} catch (Exception e) {
				e.printStackTrace();
		}

	}


}
