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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AlterPwd extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private String username;
    private int randomNum;
    private JButton btnNewButton ;
    private volatile boolean close=false;
    private  volatile int count=60;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
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
    private void send() {
        if(count!=60) {
            count=60;
        }
        String email=textField.getText();
        UsertableForCarController uc=new UsertableForCarControllerImpl();
        boolean flag=uc.selectUsertableForCarByUser(username,email);
        if(flag) {
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
            JOptionPane.showMessageDialog(this, "邮箱错误");
            return;
        }
    }
    private void repeat() {
        textField.requestFocusInWindow();
        textField.selectAll();
        textField_1.setText("");
        passwordField.setText("");
        passwordField_1.setText("");
    }
    private void alter() {
        //int IDCode=Integer.parseInt(textField_1.getText());
        String email=textField.getText();
        String IDCode=textField_1.getText();
        String pwd=passwordField.getText();
        String confirmPwd=passwordField_1.getText();
        if("".equals(email)) {
            JOptionPane.showMessageDialog(this, "邮箱不能为空");
            return;
        }if("".equals(IDCode)) {
            JOptionPane.showMessageDialog(this, "验证码密码不能为空");
            return;
        }
        if(!pwd.equals(confirmPwd)) {
            JOptionPane.showMessageDialog(this, "两次密码不一致");
            return;
        }if("".equals(pwd)) {
            JOptionPane.showMessageDialog(this, "密码不能为空");
            return;
        }
        int idCode=Integer.parseInt(IDCode);
        if(idCode==randomNum) {
            if(count==-2) {
                JOptionPane.showMessageDialog(null, "验证码已过期，请重新发送");
                return;
            }
            UsertableForCarController uc=new UsertableForCarControllerImpl();
            boolean flag=uc.updateUsertableForCarByAlterPwd(username, pwd);
            if(flag) {
                JOptionPane.showMessageDialog(null, "修改成功");
                return;
            }else {
                JOptionPane.showMessageDialog(null, "修改失败");
                return;
            }
        }else {
            JOptionPane.showMessageDialog(null, "验证码错误");
            return;
        }
    }
    public AlterPwd(String username) {
        setIconImage(Toolkit.getDefaultToolkit().getImage(AlterPwd.class.getResource("/org/car/view/picture/timg.jpg")));
        setTitle("\u4FEE\u6539\u9875\u9762");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                close=true;
            }
        });
        this.username=username;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 449, 419);
        setVisible(true);
        getContentPane().setLayout(null);

        URL url=this.getClass().getResource("picture/alterpwd_background.jpg");
        BackImage.setBackGround(this, url);

        JLabel lblNewLabel = new JLabel("\u90AE\u7BB1:");
        lblNewLabel.setForeground(new Color(0, 255, 255));
        lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lblNewLabel.setBounds(25, 53, 53, 38);
        getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        textField.setBounds(92, 53, 167, 38);
        getContentPane().add(textField);
        textField.setColumns(10);

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
        btnNewButton.setBounds(192, 120, 113, 38);
        btnNewButton.setFocusPainted(false);//除去焦点的框
        getContentPane().add(btnNewButton);

        JLabel lblNewLabel_1 = new JLabel("\u9A8C\u8BC1\u7801:");
        lblNewLabel_1.setForeground(new Color(0, 255, 255));
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lblNewLabel_1.setBounds(25, 120, 53, 38);
        getContentPane().add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        textField_1.setBounds(92, 120, 86, 38);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JButton btnNewButton_1 = new JButton("\u4FEE\u6539");
        btnNewButton_1.setForeground(new Color(0, 255, 255));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alter();
            }
        });
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_1.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_1.setFocusPainted(false);//除去焦点的框
        btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
        btnNewButton_1.setBounds(14, 301, 113, 38);
        getContentPane().add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("\u91CD\u7F6E");
        btnNewButton_2.setForeground(new Color(0, 255, 255));
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
        btnNewButton_2.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_2.setFocusPainted(false);//除去焦点的框
        btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
        btnNewButton_2.setBounds(273, 301, 113, 38);
        getContentPane().add(btnNewButton_2);

        JLabel lblNewLabel_2 = new JLabel("\u5BC6\u7801:");
        lblNewLabel_2.setForeground(new Color(0, 255, 255));
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lblNewLabel_2.setBounds(25, 184, 59, 38);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801:");
        lblNewLabel_3.setForeground(new Color(0, 255, 255));
        lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lblNewLabel_3.setBounds(25, 251, 69, 38);
        getContentPane().add(lblNewLabel_3);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordField.setBounds(92, 185, 167, 38);
        getContentPane().add(passwordField);

        passwordField_1 = new JPasswordField();
        passwordField_1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    alter();
                }
            }
        });
        passwordField_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordField_1.setBounds(92, 252, 167, 38);
        getContentPane().add(passwordField_1);

        setResizable(false);
        LocationForSwing.setLocation(this);
    }
}
