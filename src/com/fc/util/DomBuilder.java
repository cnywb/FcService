package com.fc.util;
import org.w3c.dom.*;
/**
 * domBuilder.java 本程序演示如何从草稿中创建XML文件.
 * @author lq
 *
 */
public class DomBuilder {

	/** Prints the specified node， recursively. */
	public void printDOMTree(Node node) {
		int type = node.getNodeType();
		switch (type) {
		// print the document element
		case Node.DOCUMENT_NODE: {
			System.out.println("<?xml version=\"1.0\" ?>");
			printDOMTree(((Document) node).getDocumentElement());
			break;
		}
			// print element with attributes
		case Node.ELEMENT_NODE: {
			System.out.print("<");
			System.out.print(node.getNodeName());
			NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
			Node attr = attrs.item(i);
			System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue()
						+ "\"");
			}
			System.out.println(">");
			NodeList children = node.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				for (int i = 0; i < len; i++) {
					printDOMTree(children.item(i));
				}
			}
			break;
		}
			// handle entity reference nodes
		case Node.ENTITY_REFERENCE_NODE: {
			System.out.print("&");
			System.out.print(node.getNodeName());
			System.out.print(";");
			break;
		}
			// print cdata sections
		case Node.CDATA_SECTION_NODE: {
			System.out.print("<![CDATA[");
			System.out.print(node.getNodeValue());
			System.out.print("]]>");
			break;
		}
			// print text
		case Node.TEXT_NODE: {
			System.out.print(node.getNodeValue());
			break;
		}
			// print processing instruction
		case Node.PROCESSING_INSTRUCTION_NODE: {
			System.out.print("<?");
			System.out.print(node.getNodeName());
			String data = node.getNodeValue();
			{
				System.out.print(" ");
				System.out.print(data);
			}
			System.out.print("?>");
			break;
		}
		}
		if (type == Node.ELEMENT_NODE) {
			System.out.println();
			System.out.print("</");
			System.out.print(node.getNodeName());
			System.out.print('>');
		}
	}
	/** Main program entry point. */
	public static void main(String argv[]) {
//		if (argv.length == 1 && argv[0].equals("-help")) {
//			System.out.println("Usage: Java domBuilder");
//			System.out.println(" This code builds a DOM tree， then prints it.");
//		}
		try {
//			Document doc = (Document) Class.forName(
//					"org.apache.xerces.dom.DocumentImpl").newInstance();
//			Element root = doc.createElement("sonnet");
//			root.setAttribute("type","tfnew21");
//			Element author = doc.createElement("author");
//			Element lastName = doc.createElement("last-name");
//			lastName.appendChild(doc.createTextNode("天峰"));
//			author.appendChild(lastName);
//			Element firstName = doc.createElement("first-name");
//			firstName.appendChild(doc.createTextNode("三丰"));
//			author.appendChild(firstName);
//			Element nationality = doc.createElement("nationality");
//			nationality.appendChild(doc.createTextNode("北国"));
//			author.appendChild(nationality);
//			Element yearOfBirth = doc.createElement("year-of-birth");
//			yearOfBirth.appendChild(doc.createTextNode("1980"));
//			author.appendChild(yearOfBirth);
//			root.appendChild(author);
//			Element title = doc.createElement("title");
//			title.appendChild(doc.createTextNode("天峰个人诗歌散集"));
//			root.appendChild(title);
//			Element text = doc.createElement("text");
//			Element line01 = doc.createElement("line");
//			line01.appendChild(doc.createTextNode("我的爱人的眼睛完全不象太阳 ，"));
//			text.appendChild(line01);
//			Element line02 = doc.createElement("line");
//			line02.appendChild(doc.createTextNode("红与她的唇相比较，珊瑚更红 。"));
//			text.appendChild(line02);
//			Element line03 = doc.createElement("line");
//	 line03.appendChild(doc.createTextNode("雪是白色的，那么为什么她的眼晴是那样的让我着迷 ，"));
//			text.appendChild(line03);
//			Element line04 = doc.createElement("line");
//			line04.appendChild(doc.createTextNode("如果头发是电线，黑色的电线在她的头上生长 ."));
//			text.appendChild(line04);
//			Element line05 = doc.createElement("line");
//			line05.appendChild(doc.createTextNode("我已经看见玫瑰，红和白色 ，"));
//			text.appendChild(line05);
//			Element line06 = doc.createElement("line");
//			line06.appendChild(doc.createTextNode("这样玫瑰并不看见在面颊内的我 ."));
//			text.appendChild(line06);
//			Element line07 = doc.createElement("line");
//			line07.appendChild(doc.createTextNode("并且在一些香水里在那里更多的高兴 "));
//			text.appendChild(line07);
//			Element line08 = doc.createElement("line");
//			line08.appendChild(doc.createTextNode("比在从我的主妇发臭的气息里 .."));
//			text.appendChild(line08);
//			Element line09 = doc.createElement("line");
//			line09.appendChild(doc.createTextNode("我喜欢听到她讲话，然而好我知道 "));
//			text.appendChild(line09);
//			Element line10 = doc.createElement("line");
//			line10.appendChild(doc.createTextNode("那音乐远更使满意好 ."));
//			text.appendChild(line10);
//			Element line11 = doc.createElement("line");
//			line11.appendChild(doc.createTextNode("我承认我从未看见女神去 ，"));
//			text.appendChild(line11);
//			Element line12 = doc.createElement("line");
//		line12.appendChild(doc.createTextNode("她散步时，无论在什么时候，没有看到她在踩地 ."));
//			text.appendChild(line12);
//			Element line13 = doc.createElement("line");
//			line13.appendChild(doc.createTextNode("然而，通过天堂，我有时思考我的爱 "));
//			text.appendChild(line13);
//			Element line14 = doc.createElement("line");
//			line14.appendChild(doc.createTextNode("没有任何与她落空和有错误作比较 ."));
//			text.appendChild(line14);
//			root.appendChild(text);
//			doc.appendChild(root);
//			DomBuilder db = new DomBuilder();
//			db.printDOMTree(doc);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
