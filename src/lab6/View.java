/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
package lab6;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*;

class View extends JPanel{
    //frame.repaint
    //paint
    //createImage
    //frame dimensions
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imageWidth = 165;
    final static int imageHeight = 165;
    final static int frameCount = 10;
    Direction direction;
    int xloc = 0;
    int yloc = 0;
    int picNum = 0;
    static BufferedImage[][] pics;
    JFrame frame;
    JButton jb;
    static boolean paused = false;
    
    public View(){
        frame = new JFrame();
        jb = new JButton("Pause");
        jb.setBounds(frameWidth-150,0,100,50);
        jb.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                paused = !paused;
            }
        });
        frame.add(jb);
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	
        String[] directions = {"north", "northeast", "east", "southeast", "south", "southwest",
        "west", "northwest"};
        
        pics = new BufferedImage[8][10];
        for(int i = 0; i < 8; i++){
            BufferedImage img = createImage(directions[i]);
            for(int j = 0; j < frameCount; j++)
                pics[i][j] = img.getSubimage(imageWidth*j, 0, imageWidth, imageHeight);
        }
        frame.setVisible(true);
    }
    
    public void paint(Graphics g){
        if(!paused){
            picNum = (picNum + 1) % frameCount;
        }
        g.drawImage(pics[direction.ordinal()][picNum], xloc, yloc, Color.gray, this);
    }
    
    private BufferedImage createImage(String aDirection){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_"+ 
                        aDirection +".png"));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
    
    public int getWidth(){
        return frameWidth;
    }
    public int getHeight(){
        return frameHeight;
    }
    public int getImageWidth(){
        return imageWidth;
    }
    public int getImageHeight(){
        return imageHeight;
    }
    public void update(int xlocation, int ylocation, Direction dir){
        direction = dir;
        xloc = xlocation;
        yloc = ylocation;
        frame.repaint();
        try {
            Thread.sleep(100);
    	} catch (InterruptedException e) {
            e.printStackTrace();
    	}
    }
}
