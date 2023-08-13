#Author: nikitaofficial1989@gmail.com
## This feature file contains scenario for verifying the response data for a get request on endpoint
##Endpoint:https://testapi.io/api/rmstest/media
#
Feature: Verifying the status, response time and response of a GET Request

  Background: user is able to successfully hit the endpoint
    Given User performs GET operation on endpoint rms_media_api_endpoint_env_test
    Then API responds successfully with status code 200

  @APITest
  Scenario: Check response time of GET Request
    Given user has received a response
    When user checks the response time of API
    Then response time for the request is under 3000 ms

  @APITest
  Scenario: Check values for response fields: id and segment_type
    Given user has received a response
    When User checks the value of fields id and segment_type  of every object under data array
    Then Field id is never blank
    And Field segment_type always has value music

  @APITest
  Scenario: Check primary field in title list has value
    Given user has received a response
    When user checks field primary in title_list object of every object under data array
    Then field primary should not be empty

  @APITest
  Scenario: Check now_playing is set to true for only one track
    Given user has received a response
    When user checks the now_playing field in offset object of every object under data array
    Then it is set to true only for 1 record

	@APITest
  Scenario: Verify date field in response header is current date and time
    Given user has received a response
    When user checks the Date value in Response Header
    Then Date should have valid value
