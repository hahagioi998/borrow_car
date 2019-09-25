package org.car.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;
import java.net.URL;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.car.controller.UsertableForCarController;
import org.car.controller.impl.UsertableForCarControllerImpl;
import org.car.entity.UsertableForCar;
import org.car.util.BackImage;
import org.car.util.JudgeEmail;
import org.car.util.LocationForSwing;
import org.car.util.SendMessage;


import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Register extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JTextField textField_2;
    private JTextField textField_3;
    private JPasswordField passwordField;
    private JPasswordField passwordField1;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JLabel lblNewLabel_5;
    private JTextField textField_4;
    private int randomNum;
    private volatile int count=60;
    private volatile boolean close=false;
    private JButton btnNewButton_2 ;
    private JCheckBox chckbxNewCheckBox;
    /**
     * Launch the application.
     */
    private class MyThread implements Runnable{
        private JButton jb;
        public MyThread(JButton jb) {
            this.jb=jb;
        }
        @Override
        public void run() {
            while(count-->=0) {
                if(close) {
                    break;
                }
                System.out.println(count);
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


    private void toLogin() {
        this.dispose();
        new Login();
    }
    private void send() {
        if(count!=60) {
            count=60;
        }
        String email=textField_1.getText();
        if(JudgeEmail.Judge(email)) {
            Random random=new Random();
            randomNum=random.nextInt(800000)+100000;
            try {
                new SendMessage(email,randomNum+"").send();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "发送失败");
                return;
            }
            new Thread(new MyThread(btnNewButton_2)).start();
            btnNewButton_2.setEnabled(false);
            return;
        }else {
            JOptionPane.showMessageDialog(this, "不是一个合法的邮箱");
            return;
        }
    }
    private void register() {
        String username=textField.getText();
        String email=textField_1.getText();
        String confirmPwd=textField_4.getText();
        String password=passwordField.getText();
        String password1=passwordField1.getText();
        if(username.equals("请输入用户名")) {
            JOptionPane.showMessageDialog(this, "用户名不能为空");
            return;
        }
        if("请输入邮箱".equals(email)) {
            JOptionPane.showMessageDialog(this, "邮箱不能为空");
            return;
        }
        if("验证码".equals(confirmPwd)) {
            JOptionPane.showMessageDialog(this, "验证码不能为空");
            return;
        }
        if(password.equals("")){
            JOptionPane.showMessageDialog(this, "密码不能为空");
            return;
        }
        if(!password.equals(password1)) {
            JOptionPane.showMessageDialog(this, "两次密码不一致");
            return;
        }
        if(!chckbxNewCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(this, "请阅读用户协议");
            return;
        }
        if(randomNum==Integer.parseInt(confirmPwd)) {
            if(count==-2) {
                JOptionPane.showMessageDialog(null, "验证码已过期，请重新发送");
                return;
            }
            UsertableForCarController uc=new UsertableForCarControllerImpl();
            UsertableForCar ut=new UsertableForCar(username, password, email);
            if(uc.selectUsertableForCarByRegister(ut)) {
                JOptionPane.showMessageDialog(null, "用户名已存在");
                return;
            }
            if(uc.selectUsertableForCarByRegister(email)) {
                System.out.println(uc.selectUsertableForCarByRegister(email));
                JOptionPane.showMessageDialog(this, "邮箱已存在");
                return;
            }
            if(uc.insertUsertableForCar(ut)) {
                JOptionPane.showMessageDialog(null, "注册成功");
                return;
            }else {
                JOptionPane.showMessageDialog(null, "注册失败");
                return;
            }
        }else {
            JOptionPane.showMessageDialog(null, "验证码错误");
            return;
        }

    }
    public Register() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                close=true;
            }
        });
        setIconImage(Toolkit.getDefaultToolkit().getImage(Register.class.getResource("/org/car/view/picture/timg.jpg")));
        setTitle("\u6CE8\u518C\u9875\u9762");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(100, 100, 800, 546);
        setVisible(true);

        URL url=Login.class.getResource("picture/register_background.jpg");
        BackImage.setBackGround(this, url);

        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Register.class.getResource("/org/car/view/picture/register_user.jpg")));
        lblNewLabel.setBounds(259, 108, 44, 39);
        getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textField.getText().equals("请输入用户名")) {
                    textField.setText("");
                    textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                    textField.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(textField.getText().equals("")) {
                    textField.setFont(new Font("微软雅黑", Font.BOLD, 15));
                    textField.setForeground(Color.LIGHT_GRAY);
                    textField.setText("请输入用户名");
                }
            }
        });
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setFont(new Font("微软雅黑", Font.BOLD, 15));
        textField.setText("\u8BF7\u8F93\u5165\u7528\u6237\u540D");
        textField.setBounds(300, 108, 194, 39);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Register.class.getResource("/org/car/view/picture/register_email.jpg")));
        lblNewLabel_1.setBounds(259, 171, 44, 39);
        getContentPane().add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textField_1.getText().equals("请输入邮箱")) {
                    textField_1.setText("");
                    textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                    textField_1.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(textField_1.getText().equals("")) {
                    textField_1.setForeground(Color.LIGHT_GRAY);
                    textField_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
                    textField_1.setText("请输入邮箱");
                }
            }
        });
        textField_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
        textField_1.setForeground(Color.LIGHT_GRAY);
        textField_1.setText("\u8BF7\u8F93\u5165\u90AE\u7BB1");
        textField_1.setBounds(300, 171, 194, 39);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Register.class.getResource("/org/car/view/picture/register_pw.jpg")));
        lblNewLabel_2.setBounds(259, 298, 44, 39);
        getContentPane().add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(Register.class.getResource("/org/car/view/picture/register_pw.jpg")));
        lblNewLabel_3.setBounds(259, 364, 44, 39);
        getContentPane().add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("\u7528     \u6237     \u6CE8      \u518C");
        lblNewLabel_4.setForeground(Color.RED);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("微软雅黑", Font.BOLD, 36));
        lblNewLabel_4.setBounds(84, 13, 595, 72);
        getContentPane().add(lblNewLabel_4);

        textField_2 = new JTextField();
        textField_2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField_2.setText("");
                getContentPane().remove(textField_2);
                passwordField = new JPasswordField();
                passwordField.setBounds(300, 298, 194, 39);
                getContentPane().add(passwordField);
            }
            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        textField_2.setForeground(Color.LIGHT_GRAY);
        textField_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
        textField_2.setText("\u8BF7\u8F93\u5165\u5BC6\u7801");
        textField_2.setBounds(300, 298, 194, 39);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();

        textField_3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField_3.setText("");
                getContentPane().remove(textField_3);
                passwordField1 = new JPasswordField();
                passwordField1.setBounds(300, 364, 194, 39);
                getContentPane().add(passwordField1);
            }
            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        textField_3.setText("\u8BF7\u8F93\u5165\u786E\u8BA4\u5BC6\u7801");
        textField_3.setForeground(Color.LIGHT_GRAY);
        textField_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
        textField_3.setBounds(300, 364, 194, 39);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        chckbxNewCheckBox = new JCheckBox("\u6211\u5DF2\u9605\u8BFB\u5E76\u540C\u610F<<\u7528\u6237\u534F\u8BAE>>");
        chckbxNewCheckBox.setForeground(Color.BLUE);
        chckbxNewCheckBox.setFocusPainted(false);//除去焦点的框
        chckbxNewCheckBox.setContentAreaFilled(false);
        chckbxNewCheckBox.setSelected(true);
        chckbxNewCheckBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        chckbxNewCheckBox.setBounds(293, 412, 201, 27);
        getContentPane().add(chckbxNewCheckBox);

        btnNewButton = new JButton("\u7ACB\u5373\u6CE8\u518C");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                register();

            }
        });
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton.setForeground(Color.BLUE);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 18));
        btnNewButton.setBounds(289, 448, 172, 39);
        getContentPane().add(btnNewButton);

        btnNewButton_1 = new JButton("");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toLogin();

            }
        });
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_1.setMargin(new Insets(0,0,0,0));
        btnNewButton_1.setFocusPainted(false);//除去焦点的框
        btnNewButton_1.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_1.setIcon(new ImageIcon(Register.class.getResource("/org/car/view/picture/register_return.jpg")));
        btnNewButton_1.setBounds(719, 0, 75, 77);
        getContentPane().add(btnNewButton_1);

        lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon(Register.class.getResource("/org/car/view/picture/register_email.jpg")));
        lblNewLabel_5.setBounds(258, 235, 44, 39);
        getContentPane().add(lblNewLabel_5);

        textField_4 = new JTextField();
        textField_4.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(textField_4.getText().equals("验证码")) {
                    textField_4.setText("");
                    textField_4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
                    textField_4.setForeground(Color.black);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(textField_4.getText().equals("")) {
                    textField_4.setFont(new Font("微软雅黑", Font.BOLD, 15));
                    textField_4.setForeground(Color.LIGHT_GRAY);
                    textField_4.setText("验证码");
                }
            }
        });
        textField_4.setForeground(Color.LIGHT_GRAY);
        textField_4.setFont(new Font("微软雅黑", Font.BOLD, 15));
        textField_4.setText("\u9A8C\u8BC1\u7801");
        textField_4.setBounds(300, 235, 75, 39);
        getContentPane().add(textField_4);
        textField_4.setColumns(10);

        btnNewButton_2 = new JButton("\u53D1\u9001\u9A8C\u8BC1\u7801");
        btnNewButton_2.setBackground(new Color(0, 0, 255));
        btnNewButton_2.setForeground(new Color(255, 255, 255));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                send();
            }
        });
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_2.setFont(new Font("微软雅黑", Font.BOLD, 15));

        btnNewButton_2.setBounds(377, 235, 117, 39);
        getContentPane().add(btnNewButton_2);

        setResizable(false);
        LocationForSwing.setLocation(this);
    }
}
