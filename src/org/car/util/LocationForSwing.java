package org.car.util;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class LocationForSwing {
    public static void setLocation(JFrame jf) {
    	int width=Toolkit.getDefaultToolkit().getScreenSize().width;
    	int height=Toolkit.getDefaultToolkit().getScreenSize().height;
    	jf.setLocation((width-jf.getWidth())/2, (height-jf.getHeight())/2);
    }
}
