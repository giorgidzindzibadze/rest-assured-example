package com.koroyan.restassuredexample.repository;
import com.koroyan.restassuredexample.pojos.response.GetListByNameResult;

public class GetListByNameRepositoryImpl implements GetListByNameRep {


    @Override
    public GetListByNameResult getListByName() {
        return PersonData.initializeDatabase();
    }

}
