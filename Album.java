package com.mycompany.imgurdl;

import java.awt.*;  
import java.awt.event.*;  
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

public class Album 
{
    java.util.List<String> urls;
    String url;
    String filepath;
    int count = 1;
    
    public static void main(String[] args) 
    {
        // init album object in main
        Album album = new Album();
    }
    // generate gui in ctor
    public Album() { this.generateGUI(); }

    private void getUrls()
    {
        // creates browser driver class object poiting to imgur album url
        Driver driver = new Driver(url);

        // clicks load button on album if/while it exists
        while(driver.getbutton())
        {
           driver.click();
        }

        // gets url(s) if they exist in album
        if(driver.getUrls())
        {
            urls = driver.urls;
        }

        // quits driver
        driver.driver.quit();
    }

    private void printUrls() // prints all urls in list
    {
        urls.forEach(url -> System.out.println(url));
    }

    private void generateGUI() 
    {
        // init gui values 
        Album album = this;
        Frame frame = new Frame("Imgur Album Download");
        Font font = new Font("SansSerif",Font.BOLD, 28);
        Button button = new Button("Download");
        final TextField urlText= new TextField();
        final TextField pathText= new TextField(); 
        urlText.setFont(font);
        pathText.setFont(font);
        button.setBounds(290, 65, 100, 40);
        urlText.setBounds(15, 45, 270, 40); 
        pathText.setBounds(15, 90, 270, 40); 

        // add listener to close window
        frame.addWindowListener(new WindowAdapter(){
            @Override 
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });

        // listener for execution signaling button click
        button.addActionListener(e -> {
            // get text and path from gui upon button click
            url = urlText.getText();
            filepath = pathText.getText();

            // set temp text
            urlText.setText("Processing...");

            // get urls from album
            album.getUrls();

            // print urls to console (mainly for debugging)
            album.printUrls();

            // download each image to path
            urls.forEach(img->downloadImg(img));

            // set text to let user know the operation is done
            urlText.setText("Complete"); 
        });

        // add settings / objects to frame, make visible
        frame.add(button);
        frame.add(urlText);
        frame.add(pathText);
        frame.setSize(400,150);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void downloadImg(String url) 
    {
        try // try block to catch if/when there's an error
        {
            // read url into url object
            URL urlObj = new URL(url);
            // read url into a bufferedimage object
            BufferedImage img = ImageIO.read(urlObj);
            // set file to object with full path
            File file = new File(filepath + count + "_" + url.split("/",-2)[3]);
            // write image to drive
            ImageIO.write(img, "jpg", file);
            // increment count var
            count += 1;
        }
        catch(Exception e) {}
    }
}
