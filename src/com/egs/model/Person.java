package com.egs.model;

import com.egs.customexceptions.MandatoryFieldException;
import com.egs.logging.ConsoleLogger;

import java.util.List;

public class Person {

    private long id;
    private String name;
    private List<Card> cardList;

    private static final ConsoleLogger LOGGER = new ConsoleLogger(Person.class.getName());

    public Person(long id, String name, List<Card> cardList) {
        this.id = id;
        this.name = name;
        this.cardList = cardList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }



    public synchronized Card getRandomCard () throws MandatoryFieldException {

        int r = (int) (Math.random() * cardList.size());

        Card theCard = cardList.get(r);
//        LOGGER.debug("Card is taken, ID: " + r + ", Holder: " + name);
        theCard.validate();
        return theCard;
    }
}
