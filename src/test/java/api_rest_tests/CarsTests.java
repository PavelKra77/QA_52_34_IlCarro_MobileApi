package api_rest_tests;

import controllers.CarController;
import dto.Car;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CarFactory;

import static io.restassured.RestAssured.given;

public class CarsTests extends CarController {
    @Test
    public void addNewCarPositiveTest(){
        Car car = CarFactory.positiveCar();
        Assert.assertEquals(addNewCar(car).getStatusCode(), 200);
    }

    @Test
    public void getCarPositiveTest() {
        Car car = CarFactory.positiveCar();
        addNewCar(car);
        Response response = getCar(car);
        System.out.println("STATUS = " + response.getStatusCode());
        System.out.println("BODY = " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test
    public void getCarNegativeTestNoAuthorization() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL + GET_USER_CARS)
                .thenReturn();

        Assert.assertEquals(response.getStatusCode(), 403);
    }

    @Test
    public void getAllCities() {
        Response response = getCities();
        System.out.println("STATUS = " + response.getStatusCode());
        System.out.println("BODY = " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        }

    @Test
    public void deleteCarPositiveTest() {
        Car car = CarFactory.positiveCar();
        addNewCar(car);
        Response response = deleteCar(car.getSerialNumber());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}