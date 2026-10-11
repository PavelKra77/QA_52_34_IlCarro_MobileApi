package controllers;

import dto.UserLombok;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.BaseApi;

import static io.restassured.RestAssured.given;

public class AuthController implements BaseApi {

    public Response registrationLogin(UserLombok user, String url){
        return given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+url)
                .thenReturn();
    }

}
