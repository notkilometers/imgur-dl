import java.util.*; 

public class Album 
{
    List<String> urls;
    String url;
	
	public static void main(String[] args) 
	{
		Album t = new Album("https://imgur.com/gallery/lOlu2qV"); // test album
		
		t.getUrls();
                
    t.printUrls();
	}
	
	public Album(String findUrl)
	{
		url = findUrl;
	}
	
	public Album() {}
	
	public void getUrls()
	{
            Driver d = new Driver(url);
                
            while(d.getbutton())
            {
               d.click();
            }
                
            if(d.getUrls())
            {
                urls = d.urls;
            }
            
            d.driver.quit();
	}
        
        public void printUrls()
        {
            for (String s : urls)
            {
                System.out.println(s);
            }
        }
	
	
}
