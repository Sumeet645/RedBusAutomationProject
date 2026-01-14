package Listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.Log;

public class FrameworkListener implements ITestListener {

	private static final Logger log = LogManager.getLogger(FrameworkListener.class);
	
	public void onStart(ITestContext context)
	{
	    log.info("=========TEST SUITE STARTED: " + context.getName() + "=============== ");
	}

	public void onTestStart(ITestResult result) 
	{
		log.info("-----SCENARIO STARTED:" + result.getName() + "-----");
	}

	public void onTestSuccess(ITestResult result) 
	{
		 log.info("SCENARIO PASSED: " + result.getName());
	}

	public void onTestFailure(ITestResult result) 
	{
		log.info("SCENARIO FAIILED: " + result.getName());
	}

	public void onTestSkipped(ITestResult result) 
	{
		log.info("SCENARIO SKIPPED: " + result.getName());
	}
	
	public void onFinish(ITestContext context) 
	{
		 log.info("=========TEST SUITE FINISHED: " + context.getName() + "=============== ");
	}

}
