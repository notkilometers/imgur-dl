package com.mycompany.imgurdl;

import com.google.common.io.Files;
import java.util.*; 
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
		Album t = new Album();
		
                t.genPage();
	}
	
	public Album() {}
	
	private void getUrls()
	{
            Driver b = new Driver(url);
                
            while(b.getbutton())
            {
               b.click();
            }
                
            if(b.getUrls())
            {
                urls = b.urls;
            }
            
            b.driver.quit();
	}
        
        private void printUrls()
        {
            for (String s : urls)
            {
                System.out.println(s);
            }
        }
	
	public void genPage() 
        {
            Album t = this;
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
            
            frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
            });
            
            button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent ee)
            {
                url = urlText.getText();
                filepath = pathText.getText();
                
                urlText.setText("Processing...");
                    
                t.getUrls();
                    
                t.printUrls();
                    
                for (String s : urls)
                {
                    downloadImg(s);
                }
                    
                urlText.setText(""); 
            }  
            });
            
            frame.add(button);
            frame.add(urlText);
            frame.add(pathText);
            frame.setSize(400,150);
            frame.setLayout(null);
            frame.setVisible(true);
        }
        
        private void downloadImg(String url) 
        {
            try
            {
                URL surl = new URL(url);
                BufferedImage img = ImageIO.read(surl);
                File file = new File(filepath + count + "_" + url.split("/",-2)[3]);
                ImageIO.write(img, "jpg", file);
                count += 1;
            }
            catch(Exception e) {}
        }
}

