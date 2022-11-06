package com.testinglaboratory.restassured.reactor;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

public class FlagSneakyRatTest extends BaseReactorTest{
    @Test
    public void sneakyRat(){
        Response response = when().get("/abc/control_room").prettyPeek().andReturn();
        assertThat(response.getBody().jsonPath().getString("message"))
                .isEqualTo("You're can't get pass this door comrade! ${flag_sneaky_rat}");
    }

    @Test
    void shouldGetReactorInfo(){
        Response response = when()
                .get("/" + getComradeKey() + "/control_room/analysis")
                .prettyPeek()
                .andReturn();

        assertThat(response.getBody().jsonPath().getString("message"))
                .contains("the reactor is in state Operational! It's power is on level");
    }

}
