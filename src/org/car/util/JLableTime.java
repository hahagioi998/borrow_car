package org.car.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class JLableTime extends JLabel implements Runnable {
    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    @Override
    public void run() {
        while(true) {
            setText(sdf.format(new Date()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
