package com.egs.parser;

import com.egs.cardmodel.Card;
import com.egs.cardmodel.CardBrand;
import com.egs.cardmodel.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class CardParser implements Parser{

    @Override
    public List<Card> parse(String path) throws ParseException {

        List<Card> cardList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Document document = null;

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(path));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        NodeList cardElements = document.getElementsByTagName("card");

        for (int i = 0; i < cardElements.getLength(); i++) {

            Node card = cardElements.item(i);

            Element eElement = (Element) card;


            cardList.add(new Card.CardBuilder()
                    .personID(Long.valueOf(eElement.getElementsByTagName("personid").item(0).getTextContent()))
                    .cardNumber(eElement.getElementsByTagName("cardnumber").item(0).getTextContent())
                    .cardBrand(CardBrand.valueOf(eElement.getElementsByTagName("cardbrand").item(0).getTextContent()))
                    .currency(Currency.valueOf(eElement.getElementsByTagName("currency").item(0).getTextContent()))
                    .issuerBank(eElement.getElementsByTagName("issuerbank").item(0).getTextContent())
                    .cardHolder(eElement.getElementsByTagName("cardholder").item(0).getTextContent())
                    .expiryDate((dateFormat.parse(eElement.getElementsByTagName("expiredate").item(0).getTextContent())))
                    .build());
        }

        return cardList;
    }
    }

