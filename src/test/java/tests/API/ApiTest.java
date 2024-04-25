package tests.API;


import java.util.Map;

import base.ApiBaseTest;
import com.microsoft.playwright.APIResponse;
import dataProvider.DataProvider;
import org.testng.annotations.Test;
import utils.ApiResponseMapper;

import com.fasterxml.jackson.databind.JsonNode;

public class ApiTest extends ApiBaseTest {

  @Test(dataProviderClass = DataProvider.class,dataProvider = "userPage")
  public void getUsersList(String paramName,String paramValue){
    //create map with params
    Map<String, String> params = new java.util.HashMap<>();
    params.put(paramName,paramValue);
    //send request with params
    APIResponse response =userRequest.getUser(params);
    //map api response to json
    JsonNode jsonResponse = ApiResponseMapper.mapAPIResponse(response);
    System.out.println(jsonResponse.toPrettyString());
    //get values from json
    int total = jsonResponse.get("total").asInt();
    String firstUserLasName = jsonResponse.get("data").get(0).get("last_name").asText();
    String secondUserLasName = jsonResponse.get("data").get(1).get("last_name").asText();
    int numberOfUsers = jsonResponse.get("data").size();
    //assertions on values
    softAssert.assertEquals(firstUserLasName,"Lawson","Wrong first user last name ");
    softAssert.assertEquals(secondUserLasName,"Ferguson","Wrong second user last name ");
    softAssert.assertEquals(total,numberOfUsers,"Total number is not the same as number of users, " + total + " vs " + numberOfUsers);
    softAssert.assertTrue(jsonResponse.get("total").isInt());
    softAssert.assertTrue(jsonResponse.get("data").isArray());
    softAssert.assertTrue(jsonResponse.get("data").get(0).isObject());
    softAssert.assertTrue(jsonResponse.get("data").get(0).get("last_name").isTextual());
    softAssert.assertAll();
  }

  @Test(dataProviderClass = DataProvider.class,dataProvider = "user")
  public void createUser(String name, String job){
    long limit = 100;

    //create map with data
    Map<String, String> data = new java.util.HashMap<>();
    data.put("name",name);
    data.put("job",job);

    //send request with data
    long startTime = System.currentTimeMillis();
    APIResponse response =userRequest.createUser(data);
    long endTime = System.currentTimeMillis();
    //map api response to json
    JsonNode jsonResponse = ApiResponseMapper.mapAPIResponse(response);
    System.out.println(jsonResponse.toPrettyString());

    //TODO json schema validation


    //assertions on values
    softAssert.assertEquals(response.status(),201);
    System.out.println(jsonResponse.get("id").asText());
    softAssert.assertFalse(jsonResponse.get("id").isNull(),  "Id is empty");
    softAssert.assertNotNull(jsonResponse.get("createdAt"));
    softAssert.assertTrue((endTime - startTime) < limit, "Request loading time is higher than limit " + limit + "ms");
    softAssert.assertAll();
  }

}
