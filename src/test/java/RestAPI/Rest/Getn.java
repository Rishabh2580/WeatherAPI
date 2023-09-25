package RestAPI.Rest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utlities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class Getn {
  
	RestUtils restutils = new RestUtils();
	
	
	@Test(priority=1)
	public void validateWeatherId500AndDescription() throws InterruptedException {

	Response response = restutils.getweatherDetails();
	Map<String, Object> ExpectedDetails = new HashMap<>();
	ExpectedDetails.put("id", "500");
	ExpectedDetails.put("description", "light rain");
	Object WeatherId = response.then().assertThat().extract().path("list[92].weather[0].id");
	Object WeatherDescription = response.then().assertThat().extract().path("list[92].weather[0].description");

	//Assertion
	Assert.assertEquals(WeatherId.toString(), ExpectedDetails.get("id"));
	System.out.println("Weather Id: " + WeatherId);
	Assert.assertEquals(WeatherDescription.toString(), ExpectedDetails.get("description"));
	System.out.println("WeatherDescription: " + WeatherDescription);
	}

	@Test(priority=2)
	public void validateWeatherId800AndDescription() throws InterruptedException {

	Response response = restutils.getweatherDetails();
	Map<String, Object> ExpectedDetails = new HashMap<>();
	ExpectedDetails.put("id", "800");
	ExpectedDetails.put("description", "clear sky");
	Object WeatherId = response.then().assertThat().extract().path("list[4].weather[0].id");
	Object WeatherDescription = response.then().assertThat().extract().path("list[4].weather[0].description");

	//Assertion
	Assert.assertEquals(WeatherId.toString(), ExpectedDetails.get("id"));
	System.out.println("Weather Id: " + WeatherId);
	Assert.assertEquals(WeatherDescription.toString(), ExpectedDetails.get("description"));
	System.out.println("WeatherDescription: " + WeatherDescription);
	}
	
	
	

	
	
	
	@Test(priority=3)
    public void testForecastInHourlyInterval() {
		Response response = restutils.getweatherDetails();
        response.then().statusCode(200);

        List<Integer> timestamps = response.jsonPath().getList("list");
        System.out.println(timestamps);
        int expectedTimestamp = timestamps.get(0);
        for (int timestamp : timestamps) {
            Assert.assertEquals(expectedTimestamp, timestamp);
            expectedTimestamp += 3600;
            
            
        }
    }
	
	
	
	@Test(priority=4)
    public void testResponseContains4DaysOfData() {
		Response response = restutils.getweatherDetails();
        response.then().statusCode(200);

 

        int numberOfDataPoints = response.jsonPath().getList("list").size();
        Assert.assertEquals(4 * 24, numberOfDataPoints); // Assuming hourly data
        
        System.out.println("numberOfDataPoints" + numberOfDataPoints);
    }
	
	

	   @Test(priority=5)

	    public void testTemperatureRange() {

		   Response response = restutils.getweatherDetails();

		   response.then().statusCode(200);

		   List<Double> temperature = response.jsonPath().getList("list.main.temp", Double.class);
		   List<Double> tempMin = response.jsonPath().getList("list.main.temp_min", Double.class);
		   List<Double> tempMax = response.jsonPath().getList("list.main.temp_max", Double.class);

		   for (int i = 0; i < temperature.size(); i++) {
		       Double temp = temperature.get(i);
		       Double minTemp = tempMin.get(i);
		       Double maxTemp = tempMax.get(i);

		      
		        Assert.assertTrue(temp >= minTemp);
		        Assert.assertTrue(temp <= maxTemp);
		        
		        
		        System.out.println(temp);		       
		        System.out.println(minTemp);
		        System.out.println(maxTemp);
		        
		       } 
		     
		      
		   }
	        

	    }
	





	