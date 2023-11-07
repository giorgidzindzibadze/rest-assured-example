package com.koroyan.restassuredexample.repository;

import com.koroyan.restassuredexample.pojos.response.GetListByNameResult;
import com.koroyan.restassuredexample.pojos.response.PersonIdentification;

import java.util.ArrayList;
import java.util.List;

public class PersonData {
    private static List<PersonIdentification> personIdentificationList;

    public static GetListByNameResult initializeDatabase() {
        personIdentificationList = new ArrayList<>();
            personIdentificationList.add(new PersonIdentification(89, "Xavier,Bob D.", "708-96-6126", "1966-08-11"));
            personIdentificationList.add(new PersonIdentification(10, "Xavier,Joe I.", "640-94-6892", "2013-12-12"));
            personIdentificationList.add(new PersonIdentification(41, "Xavier,Jose V.", "611-16-6306", "1929-01-07"));
            personIdentificationList.add(new PersonIdentification(116, "Xavier,Orson Q.", "261-54-9130", "1966-06-08"));

        return new GetListByNameResult(personIdentificationList);
    }




}
