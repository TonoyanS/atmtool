package com.egs.parser;

import com.egs.model.Account;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AccountParser implements Parser{

    @Override
    public List<Account> parse(final String fileName) {

        List<Account> accountList = new ArrayList<>();
        Document document = null;

        InputStream exportFileInputStream =
                getClass().getClassLoader().getResourceAsStream("com/egs/resources/" + fileName);


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(exportFileInputStream);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        NodeList accountElements = document.getElementsByTagName("account");

        for (int i = 0; i < accountElements.getLength(); i++) {

            Node account = accountElements.item(i);

            Element eElement = (Element) account;

            accountList.add(new Account(
                    Long.valueOf(eElement.getElementsByTagName("holderid").item(0).getTextContent()),
                    eElement.getElementsByTagName("issuerbank").item(0).getTextContent(),
                    eElement.getElementsByTagName("cardnumber").item(0).getTextContent(),
                    Double.valueOf(eElement.getElementsByTagName("balance").item(0).getTextContent())));
        }
        return accountList;
    }
}
