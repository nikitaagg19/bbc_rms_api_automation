/**
 * 
 * This is a step definition file for feature file GetRequestResponseValidation
 * This step definition hits the API
 * and
 * verifies the status code and response body
 */

package stepdefinition;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.ApiUtility;

import static constants.RMSAPIAutomationConstants.*;



public class RMSApiGetRequestValidationStepDef {

	private Response response;
	private String url; 
	private long responseTime;
	private List<String> fieldValueList;
	private List<String> field2ValueList;
	private List<Boolean> booleanFieldValueList;
	private static Predicate<String> emptyCheck = id -> StringUtils.isNotEmpty(id);
	private static BiPredicate<String,String> valueCheck = (actualValue, expectedValue) -> expectedValue.equalsIgnoreCase(actualValue);
	private static BiPredicate<Boolean, Boolean> booleanValueCheck = (actualBooleanValue, expectedBooleanValue) -> actualBooleanValue == expectedBooleanValue;
	private String dateValue=null;
	private static Logger log = LogManager.getRootLogger();
	
	//----------------Background-------------------
	@Given("^User performs (.*) operation on endpoint (.*)$")
	public void user_performs_get_operation_on_endpoint(String httpMethod, String endpoint) {
		//Call to RMSUtility method to fetch the URL from config file
		 url = ApiUtility.getRMSApiUrl(endpoint);
		//Calling static method getResponseForGetRequest to fetch the response
		response =  ApiUtility.callApi(url, httpMethod, null, null, null);
	}
	

	@Then("^API responds successfully with status code (.*)$")
	public void api_responds_successfully_with_status_code(int expectedStatusCode) {
		log.info("Status code for api call is: "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), expectedStatusCode);
	}
	
	
	//----------------Scenario 1-------------------
	
	@Given("user has received a response")
	public void user_has_received_a_response() {
		//Checks for the response is not empty
		log.info("Checking user has received a response");
		Assert.assertTrue(jsonContentType.equalsIgnoreCase(response.getContentType()));
		Assert.assertTrue(response.getBody().toString().length() > 0 );
		log.info("User has received a valid response");
		
	}

	@When("user checks the response time of API")
	public void user_checks_the_response_time_of_api() {
		//gets the response time of the API
		responseTime = response.getTime();
		log.info("Response time for Get request is:"+responseTime+"milliseconds");
	}
	
	@Then("^response time for the request is under (.*) ms$")
	public void response_time_for_the_request_is_under_ms(long responseLimit) {
		//checks that response time is lesser or equal to the limit specified
		log.info("Desired response time for the api is:"+responseLimit);
		Assert.assertTrue(responseTime <= responseLimit);
	}

	
	
	//----------------Scenario 2-------------------

	@When("^User checks the value of fields (.*) and (.*) of every object under (.*) array$")
	public void user_checks_the_value_in_fields_and(String field1Name, String field2Name, String objectListName) {
		fieldValueList = response.jsonPath().get(objectListName+"."+field1Name);
		log.info("Data List for: \n"+objectListName+"."+field1Name+":"+fieldValueList);
		field2ValueList = response.jsonPath().get(objectListName+"."+field2Name);
		log.info("Data List for: \n"+objectListName+"."+field1Name+":"+field2ValueList);
	}

	@Then("Field id is never blank")
	public void field_id_is_never_blank() {
		Assert.assertTrue(fieldValueList.stream().allMatch(emptyCheck));
	}

	@Then("^Field segment_type always has value (.*)$")
	public void field_always_has_value(String expectedFieldValue) {
		assertTrue(field2ValueList.stream().allMatch(emptyCheck));
		assertEquals(field2ValueList.stream().filter(actualSegmentValue -> valueCheck.test(actualSegmentValue, expectedFieldValue)).count(),field2ValueList.size());
	}
	
	
	//----------------Scenario 3-------------------

	@When("^user checks field (.*) in (.*) object of every object under (.*) array$")
	public void user_checks_fields_in(String fieldName, String objectName, String objectListName) {
		fieldValueList = response.jsonPath().get(objectListName+"."+objectName+"."+fieldName);
	}

	@Then("field primary should not be empty")
	public void field_primary_should_not_be_empty() {
		Assert.assertTrue(fieldValueList.stream().allMatch(emptyCheck));
	}

	
	//----------------Scenario 4-------------------
	@When("^user checks the (.*) field in (.*) object of every object under (.*) array$")
	public void user_checks_the_field_in(String fieldName, String objectName, String objectListName) {
		log.info("I am here");
		booleanFieldValueList = response.jsonPath().getList(objectListName+"."+objectName+"."+fieldName);
		log.info("Retrieved the list"+booleanFieldValueList);
		
	}

	@Then("^it is set to (.*) only for (.*) record$")
	public void it_is_set_to_true_only_for_one_record(boolean expectedBoolValue, int expectedCount) {
		
		assertEquals(booleanFieldValueList.stream().filter(boolValue -> booleanValueCheck.test(boolValue, expectedBoolValue)).count(),expectedCount);
		
	   
	}

	
	//----------------Scenario 5-------------------
	@When("^user checks the (.*) value in Response Header$")
	public void user_checks_the_date_value_in_response_header(String headerName) {
		dateValue = response.getHeader(headerName);
	}


	@Then("Date should have valid value")
	public void date_should_have_valid_value() {
		try {
			//TemporalAccessor accessor = DateTimeFormatter.RFC_1123_DATE_TIME.parse(dateValue);
			log.info("Date in header is:"+dateValue);
			DateTimeFormatter.RFC_1123_DATE_TIME.parse(dateValue);
			assertTrue(true);
			
		}catch(DateTimeParseException e) {
			log.info("Date in Header is not in valid format");
			Assert.fail();
		}
		
	}

}
