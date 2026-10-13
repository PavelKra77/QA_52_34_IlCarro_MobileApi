package api_rest_tests;

import controllers.AuthController;
import dto.UserLombok;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;
import utils.PropertiesReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

import static utils.UserFactory.*;

public class RegistrationLoginTests extends AuthController {
    @Test
    public void registrationPositiveTest(){
        UserLombok user = positiveUser();
        Assert.assertEquals(registrationLogin(user, REGISTRATION_URL).getStatusCode(), 200);
    }

    @Test
    public void loginPositiveTest(){
        UserLombok user = UserLombok.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();
        Response response = registrationLogin(user, LOGIN_URL);
//        System.out.println(response.getBody().asString());
//        TokenDto tokenDto = response.getBody().as(TokenDto.class);
//        System.out.println(tokenDto.getAccessToken());
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test
    public void loginNegativeWrongPasswordFieldTest(){
        UserLombok user = UserLombok.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password("AAbsent45!")
                .build();

        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);

    }

    @Test
    public void loginNegativeWrongEmailFieldTest(){
        UserLombok user = UserLombok.builder()
                .username("pav.kravets86gmail.com")
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();

        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);

    }

    @Test
    public void loginEmptyEmailNegativeTest(){
        UserLombok user = UserLombok.builder()
                .username("")
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();

        Response response = registrationLogin(user, LOGIN_URL);
        Assert.assertEquals(response.getStatusCode(), 401);

    }

    @Test
    public void loginWrongFormatNegativeTest() {
        String invalidJson = "{\"username\": [], \"password\": \"12345678\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(invalidJson)
                .when()
                .post(BaseApi.BASE_URL + BaseApi.LOGIN_URL)
                .thenReturn();

        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void loginWrongKeyEmailNegativeTest() {
        Map<String, String> invalidData = new HashMap<>();
        invalidData.put("email", PropertiesReader.getProperty("base.properties", "email"));
        invalidData.put("password", PropertiesReader.getProperty("base.properties", "password"));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(invalidData)
                .when()
                .post(BaseApi.BASE_URL + LOGIN_URL)
                .thenReturn();
        Assert.assertEquals(response.getStatusCode(), 400);
    }


    @Test
    public void registrationWrongPasswordNegativeTest() {
        UserLombok user = UserLombok.builder()
                .username(PropertiesReader.getProperty("base.properties", "email"))
                .password("qwerty123!")
                .build();

        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test
    public void registrationWrongEmailNegativeTest() {
        UserLombok user = UserLombok.builder()
                .username("pav.kravets86gmail.com")
                .password(PropertiesReader.getProperty("base.properties", "password"))
                .build();

        Response response = registrationLogin(user, REGISTRATION_URL);
        Assert.assertEquals(response.getStatusCode(), 400);
    }


    @Test
    public void registrationWrongFormatNegativeTest() {
        String invalidJson = "{\"username\": [], \"password\": \"12345678\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(invalidJson)
                .when()
                .post(BaseApi.BASE_URL + REGISTRATION_URL)
                .thenReturn();

        Assert.assertEquals(response.getStatusCode(), 500);
    }

}
