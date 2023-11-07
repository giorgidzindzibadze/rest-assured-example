package com.koroyan.restassuredexample.pojos.request;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlType(namespace = "http://tempuri.org")
@XmlRootElement(name = "GetListByName")
public class GetListByName {

    private String name;

    public GetListByName() {

    }

    public GetListByName(String name) {
        this.name = name;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}