**bbc_rms_api_automation**
Run Status:
[![Run test on a schedule](https://github.com/nikitaagg19/bbc_rms_api_automation/actions/workflows/maven.yml/badge.svg)](https://github.com/nikitaagg19/bbc_rms_api_automation/actions/workflows/maven.yml)
**Tools and Framework used for automation**

1. Java
2. Cucumber
3. Junit
4. Maven
5. Maven-Cucumber-Reporting Plugin for reporting
6. Rest assured
7.log4j for logging

**Features**

1. The scenarios are written in Gherkin and same are glued to the stepdefinition file written in Java
2. A test runner file is created to run the test. Currently test tagged with tag name APITest are run by the Runner file.
3. The logging is done use log4j
4. Reporting of the automation run is done using Maven-cucumber-reporting plugin and HTML and JSON reports are generated.
5. Manual Scenarios which were not to be automated are tagged with tag @ManualTest in the same feature file.

**Setup**

1. Clone the repository in local system using Git Clone
2. Go to cmd/terminal
3. Navigate to the directory where the repository is downloaded
4. Run command mvn clean install on your terminal/cmd OR mvn clean test verify

**Reporting**

JSON Report is generated under path: target/cucumber.json

<img width="362" alt="image" src="https://github.com/nikitaagg19/bbc_rms_api_automation/assets/142045827/351a59ac-6168-41dd-958f-92b78d5b388a">


HTML report is generated under path: target/cucumber-report-html/cucumber-html-reports/APITest.html


<img width="360" alt="image" src="https://github.com/nikitaagg19/bbc_rms_api_automation/assets/142045827/68223c6e-4e66-442e-b94b-aa52778de6cf">

**Reports are generated in below format**


<img width="1440" alt="image" src="https://github.com/nikitaagg19/bbc_rms_api_automation/assets/142045827/1935c986-f133-4331-92fe-7c3806f38806">


**Logging**
Log4j is used for logging purpose. Log report rms_media_api_automation.log is generated in the root folder

<img width="355" alt="image" src="https://github.com/nikitaagg19/bbc_rms_api_automation/assets/142045827/95602567-dadf-4079-8e4d-a530adee39b5">


