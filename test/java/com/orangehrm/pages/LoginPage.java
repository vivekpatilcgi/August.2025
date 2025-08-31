package com.orangehrm.pages;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoginPage {
	WebDriver driver;
	By uname;
	By pword;
	By loginbutton;
	
	public LoginPage(WebDriver driver) throws ParserConfigurationException, SAXException, IOException {
		this.driver = driver;
		
		String projectPath = System.getProperty("user.dir");
		File file = new File(projectPath + "\\dataa.xml");
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("user");
		if (nList.getLength() > 0) {
			Element element = (Element) nList.item(0);
			String uname1 = element.getElementsByTagName("usertag").item(0).getTextContent();
			String pword1 = element.getElementsByTagName("passtag").item(0).getTextContent();
			String loginbutton1 = element.getElementsByTagName("continuetag").item(0).getTextContent();

			uname = By.name(uname1);
			pword = By.name(pword1);
			loginbutton = By.xpath(loginbutton1);
		} else {
			throw new RuntimeException("No 'user' element found in the XML file.");
		}
	}
	 
	public void enterUsername(String username) {
		driver.findElement(uname).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(pword).sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		driver.findElement(loginbutton).click();
	}
}
