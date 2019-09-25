package org.car.util;

import java.awt.Label;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.car.view.Login;

public class BackImage {
    public static void setBackGround(JFrame jf,URL url) {
		JLabel lable = new JLabel(new ImageIcon(url),Label.RIGHT);
		lable.setBounds(0, 0, jf.getWidth(), jf.getHeight());
		jf.getLayeredPane().setLayout(null);
		jf.getLayeredPane().add(lable,new Integer(Integer.MIN_VALUE));
		((JPanel)jf.getContentPane()).setOpaque(false);
    }
}
