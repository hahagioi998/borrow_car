package org.car.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.car.controller.UsertableForCarController;
import org.car.controller.impl.UsertableForCarControllerImpl;
import org.car.util.BackImage;
import org.car.util.LocationForSwing;
import org.car.util.SendMessage;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.awt.Toolkit;

public class LoginByEmail extends JFrame {
    private JFrame jf;
    private JTextField textField;
    private JTextField textField_1;
    private JButton btnNewButton;
    private int randomNum;
    private int count=60;
    private boolean close=false;
    private String username;
    /**
     * Launch the application.
     */
    private class MyThread implements Runnable{
        private JButton jb;
        private MyThread(JButton jb) {
            this.jb=jb;

        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(count-->=0) {
                if(close) {
                    break;
                }

                jb.setText(count+"s");
                if(count==-1) {
                    jb.setText("发送验证码");
                    jb.setEnabled(true);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * Create the frame.
     */
    private void login() {
        String confirmPwd=textField_1.getText();
        if(randomNum==Integer.parseInt(confirmPwd)) {
            if(count==-2) {
                JOptionPane.showMessageDialog(null, "验证码已过期，请重新发送");
                return;
            }
            this.jf.dispose();
            this.dispose();
            new User(username);
        }else{
            JOptionPane.showMessageDialog(this, "验证码不正确");
        }
    }
    private void repeat() {
        textField.requestFocusInWindow();
        textField.selectAll();
        textField_1.setText("");

    }
    private void send() {
        if(count!=60) {
            count=60;
        }
        String email=textField.getText();
        UsertableForCarController uc=new UsertableForCarControllerImpl();
        username=uc.selectUsertableForCarByLoginByEmail(email);
        if(username!=null) {
            Random random=new Random();
            randomNum=random.nextInt(800000)+100000;
            try {
                new SendMessage(email, randomNum+"").send();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "发送失败");
                return;
            }

            new Thread(new MyThread(btnNewButton)).start();
            btnNewButton.setEnabled(false);
            return;
        }else {
            JOptionPane.showMessageDialog(this, "邮箱不存在");
            return;
        }

    }
    public LoginByEmail(JFrame jf) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(LoginByEmail.class.getResource("/org/car/view/picture/timg.jpg")));
        setTitle("\u90AE\u7BB1\u767B\u5F55");
        this.jf=jf;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                close=true;
            }
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 438, 347);
        setVisible(true);
        getContentPane().setLayout(null);

        URL url=LoginByEmail.class.getResource("picture/1.jpg");
        BackImage.setBackGround(this, url);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 435, 71);
        getContentPane().add(panel);
        panel.setOpaque(false);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel(" \u90AE  \u7BB1  \u767B  \u5F55");
        lblNewLabel.setForeground(new Color(255, 0, 0));
        lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
        lblNewLabel.setBounds(124, 0, 217, 71);
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setOpaque(false);
        panel_1.setBounds(0, 73, 435, 242);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("\u90AE\u7BB1:");
        lblNewLabel_1.setForeground(new Color(0, 0, 255));
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lblNewLabel_1.setBounds(57, 39, 46, 38);
        panel_1.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        textField.setBounds(117, 39, 178, 38);
        panel_1.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("\u9A8C\u8BC1\u7801:");
        lblNewLabel_2.setForeground(new Color(0, 0, 255));
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lblNewLabel_2.setBounds(60, 100, 53, 38);
        panel_1.add(lblNewLabel_2);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        textField_1.setBounds(117, 97, 63, 38);
        panel_1.add(textField_1);
        textField_1.setColumns(10);

        btnNewButton = new JButton("\u53D1\u9001\u9A8C\u8BC1\u7801");
        btnNewButton.setBackground(new Color(0, 0, 255));
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                send();
            }
        });
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 15));

        btnNewButton.setFocusPainted(false);//除去焦点的框

        btnNewButton.setBounds(182, 97, 113, 38);
        panel_1.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("\u767B\u5F55");
        btnNewButton_1.setForeground(new Color(0, 0, 255));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
        btnNewButton_1.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_1.setFocusPainted(false);//除去焦点的框
        btnNewButton_1.setBounds(67, 179, 113, 38);
        panel_1.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("\u91CD\u7F6E");
        btnNewButton_2.setForeground(new Color(0, 0, 255));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repeat();
            }
        });
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
        btnNewButton_2.setBounds(263, 179, 113, 38);
        btnNewButton_2.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_2.setFocusPainted(false);//除去焦点的框
        panel_1.add(btnNewButton_2);

        setResizable(false);
        LocationForSwing.setLocation(this);

    }
}
