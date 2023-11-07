package com.koroyan.restassuredexample.enums;

public enum  SOAPAction {
    Get_By_Name("GetListByName"),
    ADD_INTEGER("AddInteger"),
    DIVIDE_INTEGER("DivideInteger"),
    FIND_PERSON("FindPerson");

    private final String value;

    SOAPAction(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
