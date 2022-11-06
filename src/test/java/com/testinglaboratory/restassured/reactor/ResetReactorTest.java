package com.testinglaboratory.restassured.reactor;

import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class ResetReactorTest extends BaseReactorTest {
    private final Faker faker = new Faker(Locale.CANADA);

    protected String randomizeUser() {
        return faker.name().username();
    }

    @Test
    void shouldBeReset() {
        ReceptionistResponse receptionistResponse = given() //- przygotowanie requesta w given
                .body(Map.of("name", randomizeUser()))
                .post("/desk")
                .then().statusCode(201)
                .extract().as(ReceptionistResponse.class);
        when().get(String.format("/%s/reset_progress", receptionistResponse.key))
                .then().statusCode(HttpStatus.SC_OK).body("message", equalTo("Your reactor is good as new!"));
    }
}
