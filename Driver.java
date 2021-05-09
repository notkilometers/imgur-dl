import java.util.*; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxBinary;

public class Driver {
    WebElement button;
    public WebDriver driver;
    ArrayList<String> urls;
    
    public boolButton(String url) 
    {
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions options = new FirefoxOptions();
        
        options.setBinary(firefoxBinary);
        options.setHeadless(true);  
        driver = new FirefoxDriver(options);
        urls = = new ArrayList<String>();
        
        driver.get(url);
    }
    
    public boolean getbutton()
    {
        try
        {
            button = driver.findElement(By.className("loadMore"));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    public void click()
    {
        try
        {
            button.click();
        }
        catch (Exception e) {}
    }
    
    public boolean getUrls()
    {
        try
        {
            List<WebElement> containers = driver.findElements(By.className("Gallery-Content--mediaContainer"));
        
            for (WebElement img : containers)
            {
                WebElement k = img.findElement(By.className("image-placeholder"));
                urls.add(k.getAttribute("src"));
            }
        
            return true;
        }
        catch(Exception e )
        {
            return false;
        }
    }
}
