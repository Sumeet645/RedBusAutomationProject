package utils;

import io.cucumber.java.Scenario;

public class ScenarioContext {
	
	private static final ThreadLocal<Scenario> scenarioThreadLocal=new ThreadLocal<>();
			
	public static void setScenario(Scenario scenario)
	{
		scenarioThreadLocal.set(scenario);
	}
	
	public static Scenario getScenario()
	{
		return scenarioThreadLocal.get();
	}
	
	public static void clear()
	{
		scenarioThreadLocal.remove();
	}

}
