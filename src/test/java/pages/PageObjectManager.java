package pages;

public class PageObjectManager {
	
	private HomePage homepage;
	private BusResultsPage busresultsPage;
	
	public HomePage Homepage()
	{
		if(homepage == null)
		{
			homepage=new HomePage();
		}
		
		return homepage;
	}
	
	public BusResultsPage busresultsPage()
	{
		if(busresultsPage == null)
		{
			busresultsPage=new BusResultsPage();
		}
		
		return busresultsPage;
	}

}
