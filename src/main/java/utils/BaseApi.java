package utils;

public interface BaseApi {
    String BASE_URL = "https://ilcarro-backend.herokuapp.com";
    String REGISTRATION_URL = "/v1/user/registration/usernamepassword";
    String LOGIN_URL = "/v1/user/login/usernamepassword";
    String ADD_CAR_URL = "/v1/cars";
    String GET_USER_CARS = "/v1/cars/my";
    String GET_ALL_CITIES = "/v1/cars/cities";
    String DELETE_CAR_BY_SERIAL_NUMBER = "/v1/cars/";

    String AUTH = "Authorization";
}
