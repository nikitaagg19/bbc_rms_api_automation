package utils;

/**
 * 
 * This is API Utility class.This utility fetches the endpoint and performs various generic operations on the endpoint.
 */


import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtility {

	private static Logger log = LogManager.getRootLogger();
/**
 * This method fetches the url from config.properties field and returns the url to the calling method
 * @param endpointName
 * @return the endpoint URL
 */
	public static String getRMSApiUrl(String endpointName) {

		String apiUrl = "";
		String resourceFolderPath = "src/test/resources";

		try {
			Properties prop = new Properties();

			FileInputStream input;

			input = new FileInputStream(resourceFolderPath + "/config.properties");
			prop.load(input);
			apiUrl = prop.getProperty("rms_media_api_base_uri_env_test") + prop.getProperty(endpointName);
			log.info("API URL is:"+apiUrl);
		} catch (Exception e) {
			log.info("Failed in accessing the file or URL");
			e.printStackTrace();
		}

		return apiUrl;

	}
/**
 * Returns the response of an API
 * @param apiUrl
 * @param httpMethod
 * @param queryParams
 * @param requestPayload
 * @param requestHeaders
 * @return response
 */
	public static Response callApi(String apiUrl, String httpMethod,String queryParams, String requestPayload, Map<String,String> requestHeaders) {
		RequestSpecification request = given();
		Response response = null;
		if("GET".equalsIgnoreCase(httpMethod)) {
			response = request.when().get(apiUrl);
		}
		log.info("Response of API:\n"+"Response Header:\n"+response.getHeaders()+"\nResponse Body:\n"+response.getBody().toString());
		return response;
	}


}
