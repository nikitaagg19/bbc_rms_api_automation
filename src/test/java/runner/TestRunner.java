package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber; 
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/featurefiles", glue= {"stepdefinition"},
monochrome=true,
plugin = {"json:target/cucumber.json"},
tags= "@APITest")
public class TestRunner {

}
