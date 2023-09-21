package Utlities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {
	
	public static Response getweatherDetails() {
        RestAssured.baseURI = "https://samples.openweathermap.org";

        Response response = RestAssured.given()
            .when()
            .get("/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22") ;

        response.then()
            .statusCode(200);

        return response;

}
}
