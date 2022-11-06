package com.testinglaboratory.restassured.reactor;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;
import java.util.Map;

public abstract class BaseReactorTest implements WithAssertions {
    private final Faker faker = new Faker(Locale.CHINA);
    protected final String randomizedComradeName = faker.name().username();
    //protected static String randomizedComradeName;
    protected String getComradeKey(){
        return RestAssured
                .given()
                .body(Map.of("name", randomizedComradeName))
                .when().post("/desk")
                .thenReturn().getBody().jsonPath().getString("key");
    }




    @BeforeAll
    public static void setUp() {
        //randomizedComradeName = faker.name().username();
        RestAssured.baseURI = "http://localhost:8083";
        RestAssured.basePath = "/challenge/reactor";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build();

    }


//    private String key = getKeyFromDesk();
//
//    private String getKeyFromDesk(){
//    }

}
