package org.car.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.car.controller.UsertableForCarController;
import org.car.controller.impl.UsertableForCarControllerImpl;
import org.car.entity.UsertableForCar;
import org.car.util.BackImage;
import org.car.util.LocationForSwing;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class Login extends JFrame {
    private JTextField textField;
    private JPasswordField passwordField;



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
//			// Is your UI already created? So you will have to update the component-tree
//			// of your current frame (or actually all of them...)
//			SwingUtilities.updateComponentTreeUI(new Login());
//		} catch(Exception e) { /* Most of the time you're just going to ignore it */ }
        Login frame = new Login();



    }

    /**
     * Create the frame.
     */
    private void toLoginByEmail() {
        new LoginByEmail(this);
    }
    private void toRegister() {
        this.dispose();
        new Register();
    }
    private void login() {
        String username=textField.getText();
        String password=passwordField.getText();
        if(username.equals("")&&password.equals("")) {
            JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
            return;
        }
        UsertableForCarController uc=new UsertableForCarControllerImpl();
        UsertableForCar ut=new UsertableForCar(username, password, null);
        if(uc.selectUsertableForCarByLogin(ut)) {
            this.dispose();
            new User(username);
        }else {
            JOptionPane.showMessageDialog(null, "用户名不存在或密码错误");
            return;
        }
    }
    public Login() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int exit = JOptionPane.showConfirmDialog (null, "要退出该程序吗？", "友情提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION)
                {
                    System.exit (0);
                }else {
                    return;
                }

            }

        });
        setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/org/car/view/picture/timg.jpg")));
        setTitle("\u79DF\u8F66\u7BA1\u7406\u7CFB\u7EDF");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setVisible(true);

//		URL url=Login.class.getResource("picture/car.jpg");
//
//
//		BackImage.setBackGround(this, url);
        getContentPane().setLayout(null);



        JLabel lable = new JLabel(new ImageIcon("image/car.jpg"),Label.RIGHT);
        lable.setBounds(0, 0, getWidth(), getHeight());
        getLayeredPane().setLayout(null);
        getLayeredPane().add(lable,new Integer(Integer.MIN_VALUE));
        ((JPanel)getContentPane()).setOpaque(false);

        JLabel lblNewLabel = new JLabel("\u7528  \u6237  \u540D:");
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        lblNewLabel.setBounds(220, 171, 96, 36);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("\u5BC6        \u7801:");
        lblNewLabel_1.setForeground(Color.RED);
        lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        lblNewLabel_1.setBounds(220, 260, 96, 36);
        getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        textField.setBounds(343, 173, 197, 36);
        getContentPane().add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        passwordField.setBounds(343, 262, 197, 36);
        getContentPane().add(passwordField);

        JButton btnNewButton = new JButton("\u767B\u5F55");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton.setFocusPainted(false);//除去焦点的框



        btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 18));
        btnNewButton.setForeground(Color.RED);
        btnNewButton.setBounds(250, 355, 113, 27);
        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                passwordField.setText("");
            }
        });
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        });
        btnNewButton_1.setFocusPainted(false);//除去焦点的框
        btnNewButton_1.setContentAreaFilled(false);//除去默认的背景填充
        btnNewButton_1.setForeground(Color.RED);
        btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
        btnNewButton_1.setBounds(463, 355, 113, 27);
        getContentPane().add(btnNewButton_1);

        JLabel lblNewLabel_2 = new JLabel("\u90AE\u7BB1\u767B\u5F55");
        lblNewLabel_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                toLoginByEmail();
            }
        });
        lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lblNewLabel_2.setForeground(Color.BLUE);
        lblNewLabel_2.setBounds(579, 261, 72, 36);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("\u6CE8\u518C\u8D26\u53F7");

        lblNewLabel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                toRegister();
            }
        });
        lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
        lblNewLabel_3.setForeground(Color.BLUE);
        lblNewLabel_3.setBounds(579, 172, 72, 36);
        getContentPane().add(lblNewLabel_3);

        JCheckBox rdbtnNewCheckButton = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");

        rdbtnNewCheckButton.setFocusPainted(false);
        rdbtnNewCheckButton.setFont(new Font("微软雅黑", Font.BOLD, 15));
        rdbtnNewCheckButton.setForeground(Color.BLUE);
        rdbtnNewCheckButton.setContentAreaFilled(false);
        rdbtnNewCheckButton.setBounds(335, 319, 89, 27);
        getContentPane().add(rdbtnNewCheckButton);

        JCheckBox rdbtnNewCheckButton_1 = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
        rdbtnNewCheckButton_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
        rdbtnNewCheckButton_1.setFocusPainted(false);
        rdbtnNewCheckButton_1.setForeground(Color.BLUE);
        rdbtnNewCheckButton_1.setContentAreaFilled(false);
        rdbtnNewCheckButton_1.setBounds(430, 319, 89, 27);
        getContentPane().add(rdbtnNewCheckButton_1);





        setResizable(false);
        LocationForSwing.setLocation(this);

    }
}

