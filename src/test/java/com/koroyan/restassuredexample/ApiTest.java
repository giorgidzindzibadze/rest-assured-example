package com.koroyan.restassuredexample;

import com.koroyan.restassuredexample.data.dataproviders.DataProviders;
import com.koroyan.restassuredexample.data.models.MathOperation;
import com.koroyan.restassuredexample.enums.EndPoint;
import com.koroyan.restassuredexample.enums.SOAPAction;
import com.koroyan.restassuredexample.pojos.request.Envelope;
import com.koroyan.restassuredexample.pojos.response.FindPersonResult;
import com.koroyan.restassuredexample.pojos.response.GetListByNameResult;
import com.koroyan.restassuredexample.repository.GetListByNameRep;
import com.koroyan.restassuredexample.repository.GetListByNameRepositoryImpl;
import com.koroyan.restassuredexample.repository.PersonRepository;
import com.koroyan.restassuredexample.repository.PersonRepositoryImpl;
import com.koroyan.restassuredexample.services.RequestService;
import com.koroyan.restassuredexample.steps.Step;
import io.restassured.RestAssured;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class ApiTest {

    Step step = new Step();
    PersonRepository personRepository = new PersonRepositoryImpl();

    @Test(dataProvider = "mathOperations",dataProviderClass = DataProviders.class)
    public void addIntegerTest(MathOperation mathOperation){
        int apiResult = step.addInteger(mathOperation.getArg1(), mathOperation.getArg2());
        Assert.assertEquals(apiResult+1,mathOperation.addResult());
    }

    @Test(dataProvider = "mathOperations",dataProviderClass = DataProviders.class)
    public void addIntegerXmlTest(MathOperation mathOperation) throws IOException {
        int apiResult = step.addIntegerXml(mathOperation.getArg1(), mathOperation.getArg2());
        Assert.assertEquals(apiResult,mathOperation.addResult());
    }

    @Test(dataProvider = "mathOperations",dataProviderClass = DataProviders.class)
    public void addIntegerStringTest(MathOperation mathOperation) throws IOException {
        int apiResult = step.addIntegerString(mathOperation.getArg1(), mathOperation.getArg2());
        Assert.assertEquals(apiResult,mathOperation.addResult());
    }

    @Test
    public void findPersonTest() throws JSONException{
        String personId="1";
        FindPersonResult apiPerson = step.findPerson(personId);

        FindPersonResult databasePerson = personRepository.getPersonById(personId);

        JSONAssert.assertEquals(apiPerson.toString(),databasePerson.toString(),false);
    }

    @Test
    public FindPersonResult findPerson(String id) {
        Envelope findPersonRequestModel = RequestService.getFindPersonRequestModel(id);
        RestAssured.baseURI = EndPoint.BASE_URL.toString();
        return given()
                .contentType("text/xml;charset=UTF-8").and()
                .header("SOAPAction", SOAPAction.FIND_PERSON.toString())
                .body(findPersonRequestModel)
                .when().log().all()
                .post(EndPoint.BASE_URL.toString())
                .then()
                .log().ifError()
                .assertThat()
                .statusCode(200)
                .extract()
                .body().xmlPath().getObject("Envelope.Body.FindPersonResponse.FindPersonResult",
                        FindPersonResult.class);
    }



    GetListByNameRep getListByNameRepository = new GetListByNameRepositoryImpl();
    @Test
    public void testPersonListByName() throws JSONException {
        GetListByNameResult ListOfPeople = step.getListByName("Xavier");
        GetListByNameResult database = getListByNameRepository.getListByName();
//        step.getListByName();

        JSONAssert.assertEquals(ListOfPeople.toString(), database.toString(), false);
    }

}
