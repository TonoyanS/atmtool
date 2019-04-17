package com.egs.parser;

import com.egs.enums.CardBrand;
import com.egs.enums.Currency;
import com.egs.model.Card;
import com.egs.model.Person;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PersonParser implements Parser{

    @Override
    public List<Person> parse(String fileName) throws ParseException {

        List<Person> personList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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

        NodeList personElements = document.getElementsByTagName("person");

        for (int i = 0; i < personElements.getLength(); i++) {

            Node person = personElements.item(i);

            Element eElement = (Element) person;

            long personID = Long.valueOf(eElement.getElementsByTagName("id").item(0).getTextContent());
            String name = eElement.getElementsByTagName("name").item(0).getTextContent();
            List<Card> cardList = new ArrayList<>();

            Node cards = eElement.getElementsByTagName("cards").item(0);
            NodeList cardElements = cards.getChildNodes();

                for (int j = 0; j < cardElements.getLength(); j++) {

                    Node cardN = cardElements.item(j);

                    if (cardN.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) cardN;
                        cardList.add(new Card.CardBuilder()
                                .personID(Long.valueOf(element.getElementsByTagName("personid").item(0).getTextContent()))
                                .cardNumber(element.getElementsByTagName("cardnumber").item(0).getTextContent())
                                .cardBrand(CardBrand.valueOf(element.getElementsByTagName("cardbrand").item(0).getTextContent()))
                                .currency(Currency.valueOf(element.getElementsByTagName("currency").item(0).getTextContent()))
                                .issuerBank(element.getElementsByTagName("issuerbank").item(0).getTextContent())
                                .cardHolder(element.getElementsByTagName("cardholder").item(0).getTextContent())
                                .expiryDate((dateFormat.parse(eElement.getElementsByTagName("expiredate").item(0).getTextContent())))
                                .build());
                    }
                }
            personList.add(new Person(personID, name, cardList));
        }
        return personList;
    }
}
