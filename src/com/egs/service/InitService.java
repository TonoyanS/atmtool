package com.egs.service;

import com.egs.customexceptions.MandatoryFieldException;
import com.egs.enums.IssuerBank;
import com.egs.logging.ConsoleLogger;
import com.egs.model.Account;
import com.egs.model.Bank;
import com.egs.model.Person;
import com.egs.parser.AccountParser;
import com.egs.parser.PersonParser;
import java.text.ParseException;
import java.util.List;

public class InitService {

    private static final ConsoleLogger LOGGER = new ConsoleLogger(InitService.class.getName());
    private AccountParser accountParser = new AccountParser();
    private PersonParser personParser = new PersonParser();
    private List<Person> personList = null;


    public void initialize () throws MandatoryFieldException {

        setAccounts();
        personList = parsePersons();

    }

    public void setAccounts () throws MandatoryFieldException {

        Bank theBank;
        Account account;

        // Parsing accounts from xml and creating account holder's data in banks
        List<Account> accountList;

        accountList = accountParser.parse("accounts.xml");

        for (int i = 0; i < accountList.size(); i++) {

            account = accountList.get(i);
            account.validate();

            theBank = IssuerBank.valueOf(account.getIssuerBank()).getInstance();
            theBank.addBankCustomerAccount(theBank,account.getHolderID(),account);
        }
        LOGGER.debug("Accounts have been added");
    }

    public List<Person> parsePersons () {

        List<Person> personsList = null;
        // Parsing cards and taking random one
        try {

            personsList = personParser.parse("persons.xml");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return personsList;
    }

    public Person getRandomPerson () {

        int r = random(personList.size());

        Person person = personList.get(r);


        LOGGER.debug(person.getName() + " wants to withdraw money");

        return person;
    }



    public int random (int size) {

        int a = (int) (Math.random() * size);

        return a;
    }
}
