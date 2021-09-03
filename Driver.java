package com.mycompany.imgurdl;

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
    ArrayList<String> urls = new ArrayList<String>();
    
    public Driver (String url) 
    {
        // init driver
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(firefoxBinary);
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        
        // get specified page
        driver.get(url);
    }
    
    public boolean getbutton() // get load button if present
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
    
    public void click() // click button if present
    {
        try
        {
            button.click();
        }
        catch (Exception e) {}
    }
    
    public boolean getUrls() // get urls of images 
    {
        try
        {
            // find elements that contain a media image
            List<WebElement> containers = driver.findElements(By.className("Gallery-Content--mediaContainer"));
        
            // for each container, find the image inside and add to list
            containers.forEach(img->{
                WebElement k = img.findElement(By.className("image-placeholder"));
                urls.add(k.getAttribute("src"));
            });
        
            return true;
        }
        catch(Exception e )
        {
            return false;
        }
    }
}
