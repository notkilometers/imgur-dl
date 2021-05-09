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
            Frame f = new Frame("Button Ex");
            Button b = new Button("Submit");
            final TextField tf=new TextField();  
            b.setBounds(50,100,80,30);
            tf.setBounds(50,50, 150,20); 
            
            b.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e)
            {
                url = tf.getText();
                tf.setText("Processing...");
                    
                t.getUrls();
                    
                t.printUrls();
                    
                for (String s : urls)
                {
                    downloadImg(s);
                }
                    
                tf.setText(""); 
            }  
            });
            
            f.add(b);
            f.add(tf);
            f.setSize(400,400);
            f.setLayout(null);
            f.setVisible(true);
        }
        
        private void downloadImg(String url) 
        {
            try
            {
                URL surl = new URL(url);
                BufferedImage img = ImageIO.read(surl);
                File file = new File("C:\\Users\\Miles\\Desktop\\java\\test\\" + count + "_" + url.split("/",-2)[3]);
                ImageIO.write(img, "jpg", file);
                count += 1;
            }
            catch(Exception e) {}
        }
}
