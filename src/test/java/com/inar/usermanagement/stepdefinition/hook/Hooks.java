package com.inar.usermanagement.stepdefinition.hook;

import com.inar.usermanagement.context.TestContext;
import com.inar.usermanagement.stepdefinition.BaseSteps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {

	private final TestContext testContext;

	private static final Logger logger = LogManager.getLogger(Hooks.class);

	public Hooks(TestContext context) {
		this.testContext = context;
	}

	@Before
	public void setUp(Scenario scenario) {
		logger.info("::::::::::::::: TEST INFORMARION :::::::::::::::");
		logger.info("Executing scenario: " + scenario.getName());
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			Response response = testContext.getResponse();
			if (response != null) {
				logger.error("Scenario failed! Logging response body for failed scenario: " + scenario.getName());
				logger.error(response.getBody().prettyPrint());
			}
			else {
				logger.error("Scenario failed! But no response was set in the TestContext.");
			}
		}
		logger.info("Finished scenario: " + scenario.getName());
		logger.info("::::::::::::::::::::::::::::::::::::::::::::::::");
	}

}
