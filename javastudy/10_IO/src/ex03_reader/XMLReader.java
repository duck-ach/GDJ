package ex03_reader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {

	public static void main(String[] args) {
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File("C:\\storage", "product.xml");
			Document document = builder.parse(file); // product.xml 파싱(분석)
			
			// 최상위 요소(root)
			Element root = document.getDocumentElement(); 
			System.out.println("최상위 요소 : " + root.getNodeName()); 
			
			List<Product> products = new ArrayList<Product>();
			
			// 최상위 요소의 자식 노드들
			NodeList nodeList = root.getChildNodes(); // 자식노드들 가져오기
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i); // 줄바꿈(#text)과 <product> 태그로 구성 노드리스트 안에 들어있는 노드들.
//				System.out.println(node.getNodeName()); //#text product #text product #text product #text
				if(node.getNodeType() == Node.ELEMENT_NODE) { // ELEMENT_NODE : Element요소인지 확인하는 것 (줄바꿈(#text) 뺴기위함)
//					System.out.println(node.getNodeName()); // <product> 태그의 자식노드(줄바꿈 (#text), <number>, <name>, <price> 태그)
					NodeList nodeList2 = node.getChildNodes();
					Product product = new Product();
					for(int j = 0; j < nodeList2.getLength(); j++) {
						Node node2 = nodeList2.item(j);
						if(node2.getNodeType() == Node.ELEMENT_NODE) {
							switch(node2.getNodeName()) {
							case "number" : product.setNumber(node2.getTextContent()); break;
							case "name" : product.setName(node2.getTextContent()); break;
							case "price" : product.setPrice(Integer.parseInt(node2.getTextContent())); break;
							}
						}
					}
					products.add(product);
				}
				
				//textContent = 태그와 태그사이의 값을 가리키는 말
				
				// 줄바끔도 Node로 인식된다.
			}
			
			// ArrayList 확인
			for(Product product : products) {
				System.out.println(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

}
